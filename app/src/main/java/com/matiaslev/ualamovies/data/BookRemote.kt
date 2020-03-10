package com.matiaslev.ualamovies.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

data class BookRemoteData(
    val id: Int,
    val nombre: String,
    val autor: String,
    val disponibilidad: Boolean,
    val popularidad: Int,
    val imagen: String
) {
    object ListDeserializer : ResponseDeserializable<List<BookRemoteData>> {
        override fun deserialize(reader: Reader): List<BookRemoteData> {
            val type = object : TypeToken<List<BookRemoteData>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}

object BookRemoteError