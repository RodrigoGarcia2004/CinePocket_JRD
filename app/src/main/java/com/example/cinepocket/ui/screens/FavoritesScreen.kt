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
import androidx.compose.ui.graphics.Color
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
)
{
    val state by vm.state.collectAsState()
    val favorites = state.movies.filter { it.isFavorite }

    val primaryColor = Color(0xFF2196F3)
    val favoriteColor = Color(0xFFFF4081)
    val cardColor = Color(0xFFFAFAFA)
    val textColor = Color(0xFF333333)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        Text(
            text = "Favoritos",
            color = primaryColor,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (favorites.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "No tienes favoritos",
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor
                )

                Text(
                    text = "Marca películas con el corazón para verlas aquí",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
                    )
                )
                {
                    Text("Volver")
                }
            }
        } else
        {
            Text(
                text = "Mostrando ${favorites.size} favoritos",
                color = Color.Gray,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                items(favorites)
                { movie ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onMovieClick(movie.id) },
                        colors = CardDefaults.cardColors(
                            containerColor = cardColor
                        )
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Column(
                                modifier = Modifier.weight(1f)
                            )
                            {
                                Text(
                                    text = movie.title,
                                    color = textColor,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                )
                                {
                                    Text(
                                        text = movie.releaseDate ?: "Sin fecha",
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "•",
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "⭐ ${"%.1f".format(movie.rating)}",
                                        color = Color(0xFFFF9800)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier.clickable { vm.toggleFavorite(movie.id) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Quitar de favoritos",
                                    tint = favoriteColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

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