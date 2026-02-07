package com.example.cinepocket.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinepocket.data.Movie
import com.example.cinepocket.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel,
    onMovieClick: (Int) -> Unit
) {
    val state by vm.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text="Películas por califiación",
            style = MaterialTheme.typography.headlineMedium
        )


        Spacer(Modifier.height(12.dp))

        Row {
            Button(onClick = { vm.importMovies() }, enabled = !state.loading) {
                Text("Importar")
            }
            Spacer(Modifier.width(8.dp))
            OutlinedButton(onClick = { vm.deleteAll() }, enabled = !state.loading) {
                Text("Borrar todo")
            }
        }

        if (state.loading) {
            Spacer(Modifier.height(10.dp))
            CircularProgressIndicator()
        }
        state.error?.let { msg ->
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn {
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
                            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = "${movie.releaseDate ?: "----"} · ⭐ ${movie.rating}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        IconButton(onClick = { vm.toggleFavorite(movie.id) }) {
                            Icon(
                                imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieItem(
    movie: Movie,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Póster",
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${movie.year} · ${movie.genre} · ⭐ ${movie.rating}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
