package com.matiaslev.ualamovies.di

import arrow.core.Either
import com.matiaslev.ualamovies.domain.GetBooksPreview
import com.matiaslev.ualamovies.domain.GetBooksPreviewImpl
import com.matiaslev.ualamovies.domain.UalaBookError
import com.matiaslev.ualamovies.domain.UalaBookPreview
import com.matiaslev.ualamovies.presentation.BooksPreviewAdapter
import io.mockk.every
import io.mockk.mockk
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val testModuleError = module {

    single { BooksPreviewAdapter(androidContext()) }

    single<GetBooksPreview> {
        val getBooksPreview = mockk<GetBooksPreviewImpl>()
        every { getBooksPreview() } returns Either.left(UalaBookError())
        getBooksPreview
    }
}

val testModule = module {

    single { BooksPreviewAdapter(androidContext()) }

    single<GetBooksPreview> {
        val getBooksPreview = mockk<GetBooksPreviewImpl>()
        every { getBooksPreview() } returns Either.Right(
            listOf(
                UalaBookPreview(
                    nombre = "mocked book!",
                    autor = "autor",
                    disponibilidad = true,
                    popularidad = 10,
                    imagen = ""
                )
            )
        )
        getBooksPreview
    }
}

