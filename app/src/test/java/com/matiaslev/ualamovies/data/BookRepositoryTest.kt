package com.matiaslev.ualamovies.data

import arrow.core.Either
import com.matiaslev.ualamovies.TestUtils.getBookLocalList
import com.matiaslev.ualamovies.TestUtils.getBookRemoteDataList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class BookRepositoryTest {

    val api = mockk<Api>(relaxed = true)
    val database = mockk<AppDatabase>(relaxed = true)
    private val bookRepository = BookRepositoryImpl(api, database)

    @Test
    fun `should return the books if we have it locally without calling the api`() {
        every { database.bookDao().getAll() } returns getBookLocalList()
        bookRepository.getBooksPreview()
        verify(exactly = 0) { api.getBooks() }
    }

    @Test
    fun `should call the api and store them in the database if we not have it`() {
        every { database.bookDao().getAll() } returns listOf()
        every { api.getBooks() } returns Either.right(getBookRemoteDataList())
        bookRepository.getBooksPreview()
        verify(exactly = 1) { api.getBooks() }
        verify { database.bookDao().insertAll(getBookLocalList()) }
    }
}