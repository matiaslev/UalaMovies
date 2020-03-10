package com.matiaslev.ualamovies.di

import androidx.room.Room
import com.matiaslev.ualamovies.data.Api
import com.matiaslev.ualamovies.data.AppDatabase
import com.matiaslev.ualamovies.data.BookRepositoryImpl
import com.matiaslev.ualamovies.domain.*
import com.matiaslev.ualamovies.presentation.BooksPreviewAdapter
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val ualaModule = module {
    single { Api() }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "uala-movies-database"
        ).build()
    }

    single { BooksPreviewAdapter(androidContext()) }

    single<BookRepository> { BookRepositoryImpl(get(), get()) }

    single<GetBooksPreview> { GetBooksPreviewImpl(get()) }
}