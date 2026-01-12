package com.example.cinepocket.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinepocket.data.FakeMovies

@Composable
fun DetailScreen(
    movieId: Int,
    onBack: () -> Unit
) {
    val movie = FakeMovies.findById(movieId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = movie?.title ?: "Película no encontrada",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.padding(6.dp))

        if (movie != null) {
            Text(text = "Año: ${movie.year}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Género: ${movie.genre}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.padding(8.dp))

            Text(text = "Sinopsis", style = MaterialTheme.typography.titleMedium)
            Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.padding(12.dp))

        Button(onClick = onBack) {
            Text("Volver")
        }
    }
}
