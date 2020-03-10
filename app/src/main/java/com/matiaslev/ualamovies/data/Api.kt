package com.matiaslev.ualamovies.data

import android.util.Log
import arrow.core.Either
import com.github.kittinunf.fuel.core.extensions.cUrlString
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.matiaslev.ualamovies.domain.BookRepository

class Api {
    fun getBooks(): Either<BookRemoteError, List<BookRemoteData>> {
        val (_, _, result) = "https://qodyhvpf8b.execute-api.us-east-1.amazonaws.com/test/books"
            .httpGet()
            .also { Log.d(BookRepository::class.java.canonicalName, it.cUrlString()) }
            .responseObject(BookRemoteData.ListDeserializer)



        return when (result) {
            is Result.Success -> {
                Either.right(result.value)
            }
            is Result.Failure -> {
                Either.left(BookRemoteError)
            }
        }
    }
}