package com.matiaslev.ualamovies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matiaslev.ualamovies.R
import com.matiaslev.ualamovies.domain.GetBooksPreview
import com.matiaslev.ualamovies.domain.UalaBookError
import com.matiaslev.ualamovies.domain.UalaBookPreview
import kotlinx.android.synthetic.main.books_preview_fragment.books_preview_recycler
import kotlinx.android.synthetic.main.fragment_book_detail.book_image
import kotlinx.android.synthetic.main.item_book_preview.name_view
import org.koin.android.ext.android.inject

class BooksPreviewFragment : Fragment() {

    val getBooksPreview: GetBooksPreview by inject()
    val booksViewModel by activityViewModels<BooksViewModel> {
        BooksViewModelFactory(getBooksPreview)
    }

    val booksPreviewAdapter: BooksPreviewAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.books_preview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        books_preview_recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = booksPreviewAdapter
        }

        booksPreviewAdapter.onBookClickListener = { position, transitionName ->
            booksViewModel.selectedBook = position
            val extras = FragmentNavigatorExtras(book_image to transitionName)
            findNavController().navigate(
                 R.id.action_booksPreviewFragment_to_bookDetailFragment,
                null,
                null,
                extras
            )
        }

        booksViewModel.getBooks().observe(viewLifecycleOwner, Observer { books ->
            books.forEach { book ->
                when (book) {
                    is UalaBookError -> {
                        booksPreviewAdapter.ualaBooksPreview = books
                        booksPreviewAdapter.notifyDataSetChanged()
                    }
                    is UalaBookPreview -> {
                        booksPreviewAdapter.ualaBooksPreview = books
                        booksPreviewAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}
