package com.example.cinepocket.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.cinepocket.data.local.MovieEntity
import com.example.cinepocket.ui.utils.openDial
import com.example.cinepocket.ui.utils.openWeb
import com.example.cinepocket.ui.utils.shareMovieWhatsApp
import com.example.cinepocket.ui.viewmodel.HomeViewModel

@Composable
fun DetailScreen(
    movieId: Int,
    vm: HomeViewModel,
    onBack: () -> Unit
) {
    // OJO: si getMovieById es suspend/Flow, esto hay que hacerlo distinto.
    val movie: MovieEntity? = vm.getMovieById(movieId)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = movie?.title ?: "Película no encontrada",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (movie != null) {
            Text(
                text = "Fecha: ${movie.releaseDate ?: "----"}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Rating: ${movie.rating}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Sinopsis", style = MaterialTheme.typography.titleMedium)
            Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Acciones (alineadas y ordenadas)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { openDial(context, "932289600") }) {
                    Icon(Icons.Default.Call, contentDescription = "Llamar")
                }
                Text("Llamar", style = MaterialTheme.typography.labelMedium)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {
                    shareMovieWhatsApp(context, "Te recomiendo: ${movie?.title ?: "esta peli"}")
                }) {
                    Icon(Icons.Default.Share, contentDescription = "Compartir")
                }
                Text("Compartir", style = MaterialTheme.typography.labelMedium)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    openWeb(context, "https://www.themoviedb.org/movie/$movieId")
                }) {
                    Text("Web", style = MaterialTheme.typography.labelMedium)
                }

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
