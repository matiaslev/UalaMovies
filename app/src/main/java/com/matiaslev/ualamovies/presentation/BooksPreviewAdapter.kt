package com.matiaslev.ualamovies.presentation

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaslev.ualamovies.R
import com.matiaslev.ualamovies.domain.UalaBook
import com.matiaslev.ualamovies.domain.UalaBookError
import com.matiaslev.ualamovies.domain.UalaBookPreview
import kotlinx.android.synthetic.main.item_book_preview.view.*

class BooksPreviewAdapter(
    private val context: Context,
    var ualaBooksPreview: List<UalaBook> = listOf(),
    var onBookClickListener: (Int, String) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<BooksPreviewAdapter.BookPreviewViewHolder>() {

    class BookPreviewViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, transitionName: String, listener: (Int, String) -> Unit) {
            view.setOnClickListener { listener(position, transitionName) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookPreviewViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_preview, parent, false)
        return BookPreviewViewHolder(item)
    }

    override fun getItemCount(): Int = ualaBooksPreview.size

    override fun onBindViewHolder(holder: BookPreviewViewHolder, position: Int) {
        when(val ualaBook = ualaBooksPreview[position]) {
            is UalaBookPreview -> {
                holder.view.apply {
                    name_view.text = ualaBook.nombre
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        book_image.transitionName = ualaBook.imagen
                    }
                    Glide.with(context)
                        .load(ualaBook.imagen)
                        .into(book_image)
                    holder.bind(position, ualaBook.imagen, onBookClickListener)
                }
            }
            is UalaBookError -> holder.view.name_view.text = ualaBook.errorMessage
        }
    }
}