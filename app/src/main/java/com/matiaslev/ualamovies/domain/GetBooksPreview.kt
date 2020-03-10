package com.matiaslev.ualamovies.domain

import arrow.core.Either

interface GetBooksPreview {
    operator fun invoke() : Either<UalaBookError, List<UalaBookPreview>>
}

class GetBooksPreviewImpl(
    private val bookRepository: BookRepository
): GetBooksPreview {
    override fun invoke() = bookRepository.getBooksPreview()
}