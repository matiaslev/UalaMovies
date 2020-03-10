package com.matiaslev.ualamovies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.matiaslev.ualamovies.domain.GetBooksPreview
import com.matiaslev.ualamovies.domain.UalaBook
import com.matiaslev.ualamovies.domain.UalaBookError
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val getBooksPreview: GetBooksPreview
) : ViewModel() {

    private val booksLiveData = MutableLiveData<List<UalaBook>>()
    var selectedBook = 0

    fun syncPreviews() {
        viewModelScope.launch(IO) {
            val books = getBooksPreview()

            withContext(Main) {
                books.fold(
                    {
                        booksLiveData.value = listOf(UalaBookError())
                    },
                    { books ->
                        booksLiveData.value = books
                    }
                )
            }
        }
    }

    fun getBooks(): LiveData<List<UalaBook>> = booksLiveData
}

open class BooksViewModelFactory(
    private val getBooksPreview: GetBooksPreview
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BooksViewModel(getBooksPreview) as T
    }
}
