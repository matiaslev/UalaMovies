package com.matiaslev.ualamovies

import com.matiaslev.ualamovies.data.BookLocal
import com.matiaslev.ualamovies.data.BookRemoteData

object TestUtils {
    fun getBookRemoteDataList() = listOf(
        BookRemoteData(
            id = 1,
            nombre = "Type Driven Development with Idris",
            autor = "Edwyn Brady",
            disponibilidad = false,
            popularidad = 10,
            imagen = ""
        )
    )

    fun getBookLocalList() = listOf(
        BookLocal(
            id = 1,
            nombre = "Type Driven Development with Idris",
            autor = "Edwyn Brady",
            disponibilidad = false,
            popularidad = 10,
            imagen = ""
        )
    )
}