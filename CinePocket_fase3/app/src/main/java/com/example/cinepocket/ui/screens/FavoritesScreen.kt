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
        Text("Favoritos", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(12.dp))

        if (favorites.isEmpty()) {
            Text("No tienes películas favoritas todavía.")
            Spacer(Modifier.weight(1f))
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
                                Text(movie.title, style = MaterialTheme.typography.titleMedium)
                                Text("${movie.releaseDate ?: "----"} · ⭐ ${movie.rating}")
                            }
                            IconButton(onClick = { vm.toggleFavorite(movie.id) }) {
                                Icon(
                                    imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Quitar de favoritos"
                                )
                            }
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
