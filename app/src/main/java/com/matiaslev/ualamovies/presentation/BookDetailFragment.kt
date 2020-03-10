package com.matiaslev.ualamovies.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.matiaslev.ualamovies.R
import com.matiaslev.ualamovies.domain.GetBooksPreview
import com.matiaslev.ualamovies.domain.UalaBookError
import com.matiaslev.ualamovies.domain.UalaBookPreview
import kotlinx.android.synthetic.main.fragment_book_detail.book_author
import kotlinx.android.synthetic.main.fragment_book_detail.book_available
import kotlinx.android.synthetic.main.fragment_book_detail.book_image
import kotlinx.android.synthetic.main.fragment_book_detail.book_name
import kotlinx.android.synthetic.main.fragment_book_detail.book_popularity
import org.koin.android.ext.android.inject

class BookDetailFragment : Fragment() {

    val getBooksPreview: GetBooksPreview by inject()
    val booksViewModel by activityViewModels<BooksViewModel> {
        BooksViewModelFactory(getBooksPreview)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        booksViewModel.getBooks().observe(viewLifecycleOwner, Observer { books ->
            when (val book = books[booksViewModel.selectedBook]) {
                is UalaBookError -> {
                    book_name.text = book.errorMessage
                }
                is UalaBookPreview -> {
                    book_image.apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            transitionName = book.imagen
                        }
                        Glide.with(requireContext())
                            .load(book.imagen)
                            .into(this)
                    }
                    book_name.text = book.nombre
                    book_author.text = book.autor
                    book_available.text = book.estaDisponible
                    book_popularity.text =  book.cuanPopularEs
                }
            }
        })
    }
}
