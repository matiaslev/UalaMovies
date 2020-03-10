package com.matiaslev.ualamovies.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.matiaslev.ualamovies.R
import com.matiaslev.ualamovies.domain.GetBooksPreview
import com.matiaslev.ualamovies.domain.UalaBookError
import com.matiaslev.ualamovies.domain.UalaBookPreview
import kotlinx.android.synthetic.main.main_activity.books_sync
import org.koin.android.ext.android.inject

class BooksActivity : AppCompatActivity() {

    val getBooksPreview: GetBooksPreview by inject()
    val booksViewModel: BooksViewModel by viewModels() {
        BooksViewModelFactory(getBooksPreview)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        booksViewModel.syncPreviews()
        books_sync.setOnClickListener {
            booksViewModel.syncPreviews()
        }
        booksViewModel.getBooks().observe(this, Observer { books ->
            when (books.first()) {
                is UalaBookError -> {
                    books_sync.isChecked = false
                }
                is UalaBookPreview -> {
                    books_sync.isChecked = true
                }
            }
        })
    }
}
