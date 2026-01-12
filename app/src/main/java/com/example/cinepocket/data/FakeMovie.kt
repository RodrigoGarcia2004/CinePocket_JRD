package com.example.cinepocket.data

object FakeMovies {
    val list = listOf(
        Movie(
            id = 1,
            title = "Interstellar",
            year = 2014,
            genre = "Sci‑Fi",
            overview = "Un grupo de exploradores viaja a través de un agujero de gusano para buscar un nuevo hogar.",
            rating = 8.6
        ),
        Movie(
            id = 2,
            title = "Inception",
            year = 2010,
            genre = "Acción / Sci‑Fi",
            overview = "Un ladrón roba secretos infiltrándose en los sueños de otras personas.",
            rating = 8.8
        ),
        Movie(
            id = 3,
            title = "The Batman",
            year = 2022,
            genre = "Acción",
            overview = "Batman investiga una serie de crímenes que revelan una trama mayor en Gotham.",
            rating = 7.8
        ),
        Movie(
            id = 4,
            title = "Spirited Away",
            year = 2001,
            genre = "Animación",
            overview = "Chihiro entra en un mundo espiritual y debe salvar a sus padres.",
            rating = 8.6
        )
    )

    fun findById(id: Int): Movie? = list.firstOrNull { it.id == id }
}
