package com.example.cinepocket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinepocket.data.local.entity.MovieEntity
import com.example.cinepocket.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val movies: List<MovieEntity> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

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
                val movies = repo.getPopularMovies()
                repo.insertMovies(movies)
            } catch (e: Exception) {
                _state.update { it.copy(error = "No se pudieron importar películas") }
            } finally {
                _state.update { it.copy(loading = false) }
            }
        }
    }

    suspend fun getMovieById(id: Int): MovieEntity? {
        return repo.getMovieById(id)
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch { repo.toggleFavorite(id) }
    }

    fun deleteAll() {
        viewModelScope.launch { repo.deleteAll() }
    }
}