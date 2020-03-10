package com.matiaslev.ualamovies.domain

import arrow.core.Either

interface BookRepository {
    fun getBooksPreview() : Either<UalaBookError, List<UalaBookPreview>>
}