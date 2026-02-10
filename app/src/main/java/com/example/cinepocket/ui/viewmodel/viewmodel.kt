package com.example.cinepocket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinepocket.data.local.entity.MovieEntity
import com.example.cinepocket.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Estado de la interfaz de usuario de la pantalla principal.
 *
 * Representa todos los datos necesarios para renderizar HomeScreen.
 *
 * @property movies Lista de películas a mostrar
 * @property loading Indica si hay una operación en curso
 * @property error Mensaje de error si ocurrió algún problema (null si no hay error)
 * @property currentPage Página actual de la paginación de TMDB
 */
data class HomeUiState(
    val movies: List<MovieEntity> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1
)

/**
 * ViewModel para la pantalla principal de películas.
 *
 * Gestiona la lógica de negocio y el estado de la UI.
 * Coordina las operaciones entre el Repository y la vista.
 *
 * Inyectado automáticamente por Hilt.
 *
 * @property repo Repositorio de películas
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel()
{
    private val _state = MutableStateFlow(HomeUiState())

    /**
     * Estado observable de la UI.
     *
     * La vista se suscribe a este Flow para recibir actualizaciones.
     */
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init
    {
        // Observa cambios en la base de datos y actualiza el estado
        viewModelScope.launch {
            repo.observeMovies().collect { list ->
                _state.update { it.copy(movies = list) }
            }
        }
    }

    /**
     * Importa la primera página de películas populares desde TMDB.
     *
     * Resetea la paginación a la página 1 y descarga películas frescas.
     * Actualiza el estado con loading/error según corresponda.
     */
    fun importMovies()
    {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null, currentPage = 1) }
            try {
                val movies = repo.getPopularMovies(page = 1)
                repo.insertMovies(movies)
            } catch (e: Exception)
            {
                _state.update { it.copy(error = "No se pudieron importar películas") }
            } finally
            {
                _state.update { it.copy(loading = false) }
            }
        }
    }

    /**
     * Carga la siguiente página de películas populares.
     *
     * Incrementa el contador de página y obtiene más películas.
     * Las nuevas películas se añaden a la base de datos existente.
     */
    fun loadMoreMovies()
    {
        viewModelScope.launch{
            _state.update { it.copy(loading = true, error = null) }
            try {
                val nextPage = _state.value.currentPage + 1
                val movies = repo.getPopularMovies(page = nextPage)
                repo.insertMovies(movies)
                _state.update { it.copy(currentPage = nextPage) }
            } catch (e: Exception)
            {
                _state.update { it.copy(error = "No se pudieron cargar más películas") }
            } finally
            {
                _state.update { it.copy(loading = false) }
            }
        }
    }

    /**
     * Obtiene una película específica por su ID.
     *
     * @param id Identificador de la película
     * @return La película si existe, null si no se encuentra
     */
    suspend fun getMovieById(id: Int): MovieEntity?
    {
        return repo.getMovieById(id)
    }

    /**
     * Alterna el estado de favorito de una película.
     *
     * @param id Identificador de la película a marcar/desmarcar
     */
    fun toggleFavorite(id: Int)
    {
        viewModelScope.launch { repo.toggleFavorite(id) }
    }

    /**
     * Elimina todas las películas de la base de datos.
     *
     * También resetea la paginación a la página 1.
     */
    fun deleteAll()
    {
        viewModelScope.launch {
            repo.deleteAll()
            _state.update { it.copy(currentPage = 1) }
        }
    }
}