package com.matiaslev.ualamovies.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookLocal(
    @PrimaryKey val id: Int,
    val nombre: String,
    val autor: String,
    val disponibilidad: Boolean,
    val popularidad: Int,
    val imagen: String
)