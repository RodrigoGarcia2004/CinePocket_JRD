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

    val primaryColor = Color(0xFF2196F3)
    val favoriteColor = Color(0xFFFF4081)
    val cardColor = Color(0xFFFAFAFA)
    val textColor = Color(0xFF333333)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tus Películas",
            color = primaryColor,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Button(
                onClick = { vm.importMovies() },
                enabled = !state.loading,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                )
            )
            {
                Text("Importar")
            }

            Button(
                onClick = { vm.deleteAll() },
                enabled = !state.loading,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                )
            )
            {
                Text("Borrar todo")
            }
        }

        val favCount = state.movies.count { it.isFavorite }
        Button(
            onClick = onFavoritesClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            colors = ButtonDefaults.buttonColors(favoriteColor)
        )
        {
            Icon(Icons.Default.Favorite, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Favoritos ($favCount)")
        }

        if (state.loading)
        {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = primaryColor)
            }
        }

        state.error?.let{ msg ->
            Text(
                text = msg,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.movies) { movie ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMovieClick(movie.id) },
                    colors = CardDefaults.cardColors(cardColor)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = movie.title,
                                color = textColor,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(Modifier.height(4.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
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
                        )
                        {
                            Icon(
                                imageVector = if (movie.isFavorite)
                                    Icons.Default.Favorite
                                else
                                    Icons.Default.FavoriteBorder,
                                contentDescription = "marcar/desmarcar favorito",
                                tint = if (movie.isFavorite)
                                    favoriteColor
                                else
                                    Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }

        if (state.movies.isNotEmpty() && !state.loading) {
            Button(
                onClick = { vm.loadMoreMovies() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                colors = ButtonDefaults.buttonColors(contentColor = primaryColor)
            ) {
                Text("Ver más películas")
            }
        }

        if (state.movies.isNotEmpty()) {
            Text(
                text = "Mostrando ${state.movies.size} películas",
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}