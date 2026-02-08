package com.example.cinepocket.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.cinepocket.data.local.entity.MovieEntity
import com.example.cinepocket.ui.utils.openDial
import com.example.cinepocket.ui.utils.openWeb
import com.example.cinepocket.ui.utils.shareMovie
import com.example.cinepocket.ui.viewmodel.HomeViewModel

/**
 * Pantalla de detalle de una película.
 *
 * Muestra información completa de la película: título, fecha, rating,
 * sinopsis y botones para llamar, compartir o ver en la web.
 *
 * @param movieId ID de la película a mostrar
 * @param vm ViewModel para obtener los datos
 * @param onBack Se ejecuta al pulsar "Volver"
 */
@Composable
fun DetailScreen(
    movieId: Int,
    vm: HomeViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var movie by remember { mutableStateOf<MovieEntity?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(movieId) {
        isLoading = true
        movie = vm.getMovieById(movieId)
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator()
            }

            movie == null -> {
                Text(
                    text = "Película no encontrada",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            else -> {
                Text(
                    text = movie!!.title,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AssistChip(
                        onClick = {},
                        label = { Text(movie!!.releaseDate ?: "----") }
                    )

                    AssistChip(
                        onClick = {},
                        label = { Text("⭐ ${"%.2f".format(movie!!.rating)}") }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Sinopsis",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = movie!!.overview,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { openDial(context, "932289600") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Llamar")
                    }

                    Button(
                        onClick = {
                            shareMovie(
                                context = context,
                                title = movie!!.title,
                                overview = movie!!.overview
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Compartir")
                    }

                    OutlinedButton(
                        onClick = {
                            openWeb(
                                context,
                                "https://www.themoviedb.org/movie/${movie!!.id}"
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ver en la web")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(250.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}