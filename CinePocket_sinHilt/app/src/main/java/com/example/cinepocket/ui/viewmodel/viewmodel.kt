package com.example.cinepocket.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinepocket.data.MovieRepository
import com.example.cinepocket.data.local.MovieEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class HomeUiState(
    val movies: List<MovieEntity> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)

class HomeViewModel(private val repo: MovieRepository) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repo.observeMovies().collect { list ->
                _state.update { it.copy(movies = list) }
            }
        }
    }

    fun importMovies() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null) }
            try {
                repo.importPopular()
            } catch (e: Exception) {
                _state.update { it.copy(error = "No se pudieron importar películas") }
            } finally {
                _state.update { it.copy(loading = false) }
            }
        }
    }
    fun getMovieById(id: Int): MovieEntity? {
        return state.value.movies.firstOrNull { it.id == id }
    }
    fun toggleFavorite(id: Int) {
        viewModelScope.launch { repo.toggleFavorite(id) }
    }

    fun deleteAll() {
        viewModelScope.launch { repo.deleteAll() }
    }
}
