package com.matiaslev.ualamovies.data

import arrow.core.Either
import com.matiaslev.ualamovies.domain.BookRepository
import com.matiaslev.ualamovies.domain.UalaBookError
import com.matiaslev.ualamovies.domain.UalaBookPreview

class BookRepositoryImpl(
    private val api: Api,
    private val database: AppDatabase
) : BookRepository {

    override fun getBooksPreview(): Either<UalaBookError, List<UalaBookPreview>> {
        val booksLocal = database.bookDao().getAll()
        return when  {
            booksLocal.isEmpty() -> {
                api.getBooks()
                    .fold(
                        { bookRemoteError -> Either.left(UalaBookError()) },
                        { booksRemote ->
                            val bookLocal = booksRemote.map { BookLocal(it.id, it.nombre, it.autor, it.disponibilidad, it.popularidad, it.imagen) }
                            database.bookDao().insertAll(bookLocal)
                            Either.right(booksRemote.map { UalaBookPreview(it.nombre, it.autor, it.disponibilidad, it.popularidad, it.imagen) })
                        }
                    )
                }
            else -> Either.right(booksLocal.map { UalaBookPreview(it.nombre, it.autor, it.disponibilidad, it.popularidad, it.imagen) })
        }
    }
}