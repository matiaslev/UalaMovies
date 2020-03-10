package com.matiaslev.ualamovies.domain

import junit.framework.Assert.assertEquals
import org.junit.Test

class UalaBookPreviewTest {

    private val bookAvailable = UalaBookPreview(
        nombre = "Type Driven Development with Idris",
        autor = "Edwyn Brady",
        disponibilidad = true,
        popularidad = 10,
        imagen = ""
    )

    private val bookNotAvailable = UalaBookPreview(
        nombre = "Type Driven Development with Idris",
        autor = "Edwyn Brady",
        disponibilidad = false,
        popularidad = 10,
        imagen = ""
    )

    @Test
    fun `should return yes! when is available`() {
        assertEquals("yes!", bookAvailable.estaDisponible)
    }

    @Test
    fun `should return no! when is not available`() {
        assertEquals("no!", bookNotAvailable.estaDisponible)
    }

    @Test
    fun `should return a string when popularity is required`() {
        assertEquals(String::class, bookAvailable.cuanPopularEs::class)
    }
}