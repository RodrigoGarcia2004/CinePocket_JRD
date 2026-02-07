package com.example.cinepocket.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinepocket.ui.viewmodel.HomeViewModel

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

        // Aviso de conexión
        if (!state.isConnected) {
            Text(
                text = "Sin conexión",
                color = MaterialTheme.colorScheme.error
            )
            Spacer(Modifier.height(8.dp))
        }

        // Botones superiores
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { vm.importMovies() },
                enabled = !state.loading && state.isConnected
            ) {
                Text("Importar")
            }

            Spacer(Modifier.width(8.dp))

            OutlinedButton(
                onClick = { vm.deleteAll() },
                enabled = !state.loading
            ) {
                Text("Borrar todo")
            }

            Spacer(Modifier.width(8.dp))

            Button(onClick = onFavoritesClick) {
                Text("Favoritos")
            }
        }

        // Loading / error
        if (state.loading) {
            Spacer(Modifier.height(10.dp))
            CircularProgressIndicator()
        }

        state.error?.let { msg ->
            Spacer(Modifier.height(8.dp))
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(12.dp))

        // Lista
        LazyColumn(
            modifier = Modifier.fillMaxSize()
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
                            Text(movie.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = "${movie.releaseDate ?: "----"} · ⭐ ${movie.rating}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        IconButton(onClick = { vm.toggleFavorite(movie.id) }) {
                            Icon(
                                imageVector = if (movie.isFavorite)
                                    Icons.Default.Favorite
                                else
                                    Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito"
                            )
                        }
                    }
                }
            }
        }
    }
}
