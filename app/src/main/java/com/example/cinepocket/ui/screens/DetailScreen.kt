package com.example.cinepocket.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val primaryColor = Color(0xFF2196F3)
    val cardColor = Color(0xFFFAFAFA)
    val textColor = Color(0xFF333333)
    val ratingColor = Color(0xFFFF9800)

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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = primaryColor)
                }
            }

            movie == null -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Película no encontrada",
                        color = textColor
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = onBack,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor
                        )
                    ) {
                        Text("Volver")
                    }
                }
            }

            else -> {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = movie!!.title,
                            color = textColor,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(Modifier.height(8.dp))

                        Row {
                            Text(
                                text = movie!!.releaseDate ?: "Sin fecha",
                                color = Color.Gray
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "•",
                                color = Color.Gray
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "⭐ ${"%.1f".format(movie!!.rating)}",
                                color = ratingColor
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        Text(
                            text = "Sinopsis",
                            color = primaryColor,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = movie!!.overview,
                            color = textColor
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Column {
                    Button(
                        onClick = { openDial(context, "932289600") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor
                        )
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Llamar")
                    }

                    Spacer(Modifier.height(8.dp))

                    Button(
                        onClick = { shareMovie(context, movie!!.title, movie!!.overview) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor
                        )
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Compartir")
                    }

                    Spacer(Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = { openWeb(context, "https://www.themoviedb.org/movie/${movie!!.id}") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ver en la web")
                    }
                }

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
                    )
                ) {
                    Text("Volver")
                }
            }
        }
    }
}