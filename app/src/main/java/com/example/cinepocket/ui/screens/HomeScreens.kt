package com.example.cinepocket.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinepocket.ui.viewmodel.HomeViewModel

/**
 * Pantalla principal con la lista de películas
 *
 * Permite importar películas, borrar todo, ver favoritos
 * y navegar al detalle de cada película.
 *
 * @param vm ViewModel que gestiona el estado y las acciones
 * @param onMovieClick Se ejecuta al pulsar una película (recibe su ID)
 * @param onFavoritesClick Se ejecuta al pulsar "Ver Favoritos"
 */
@Composable
fun HomeScreen(
    vm: HomeViewModel,
    onMovieClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit
) {
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Películas por calificación",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { vm.importMovies() },
                enabled = !state.loading,
                modifier = Modifier.weight(1f)
            ) {
                Text("Importar")
            }

            OutlinedButton(
                onClick = { vm.deleteAll() },
                enabled = !state.loading,
                modifier = Modifier.weight(1f)
            ) {
                Text("Borrar todo")
            }
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onFavoritesClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(Icons.Default.Favorite, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            val favCount = state.movies.count { it.isFavorite }
            Text("Ver Favoritos ($favCount)")
        }

        if (state.loading) {
            Spacer(Modifier.height(10.dp))
            CircularProgressIndicator()
        }

        state.error?.let { msg ->
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(state.movies) { movie ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onMovieClick(movie.id) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${movie.releaseDate ?: "----"} · ⭐ ${"%.2f".format(movie.rating)}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        IconButton(onClick = { vm.toggleFavorite(movie.id) }) {
                            Icon(
                                imageVector = if (movie.isFavorite)
                                    Icons.Default.Favorite
                                else
                                    Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = if (movie.isFavorite)
                                    MaterialTheme.colorScheme.error
                                else
                                    MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        if (state.movies.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            OutlinedButton(
                onClick = { vm.loadMoreMovies() },
                enabled = !state.loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cargar más películas (Página ${state.currentPage + 1})")
            }
        }
    }
}