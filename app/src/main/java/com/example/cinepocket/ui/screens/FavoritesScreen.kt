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
 * Pantalla de películas favoritas
 *
 * Muestra solo las películas marcadas como favoritas
 * Si no hay ninguna, muestra un mensaje informativo
 *
 * @param vm ViewModel que expone las películas
 * @param onMovieClick Se ejecuta al pulsar una película
 * @param onBack Se ejecuta al pulsar "Volver"
 */
@Composable
fun FavoritesScreen(
    vm: HomeViewModel,
    onMovieClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()
    val favorites = state.movies.filter { it.isFavorite }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favoritos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        if (favorites.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Aún no tienes favoritos",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Marca películas con el corazón para verlas aquí",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Volver")
                }
            }

        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(favorites) { movie ->
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
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = movie.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = "${movie.releaseDate ?: "----"} · ⭐ ${"%.2f".format(movie.rating)}"
                                )
                            }

                            IconButton(
                                onClick = { vm.toggleFavorite(movie.id) }
                            ) {
                                Icon(
                                    imageVector = if (movie.isFavorite)
                                        Icons.Default.Favorite
                                    else
                                        Icons.Default.FavoriteBorder,
                                    contentDescription = "Quitar de favoritos",
                                    tint = if (movie.isFavorite)
                                        MaterialTheme.colorScheme.error  // ← Ahora es rojo
                                    else
                                        MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}