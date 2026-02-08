package com.example.cinepocket.navigation
/**
 * Rutas de navegación de la aplicación.
 *
 * Contiene los nombres de las pantallas y helpers para rutas con parámetros.
 */
object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val DETAIL = "detail/{movieId}"
    const val FAVORITES = "favorites"

    /**
     * Devuelve la ruta al detalle de una película concreta.
     *
     * @param movieId id de la película
     */
    fun detail(movieId: Int) = "detail/$movieId"
}