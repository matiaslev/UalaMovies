package com.matiaslev.ualamovies.domain

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class GetBooksPreviewTest {

    val bookRepository = mockk<BookRepository>(relaxed = true)
    private val getBooksPreviewImpl = GetBooksPreviewImpl(bookRepository)

    @Test
    fun `should delegate his job to the repository`() {
        getBooksPreviewImpl()
        verify(exactly = 1) { bookRepository.getBooksPreview() }
        confirmVerified(bookRepository)
    }
}