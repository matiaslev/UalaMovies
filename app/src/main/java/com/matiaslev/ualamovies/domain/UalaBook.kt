package com.matiaslev.ualamovies.domain

sealed class UalaBook

data class UalaBookPreview(
    val nombre: String,
    val autor: String,
    private val disponibilidad: Boolean,
    private val popularidad: Int,
    val imagen: String
) : UalaBook() {
    val estaDisponible: String
        get() = if (disponibilidad) "yes!" else "no!"
    val cuanPopularEs: String
        get() = popularidad.toString()
}

data class UalaBookError(
    val errorMessage: String = "ups!"
) : UalaBook()
