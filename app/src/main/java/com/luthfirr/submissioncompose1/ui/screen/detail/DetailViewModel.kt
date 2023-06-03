package com.luthfirr.submissioncompose1.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luthfirr.submissioncompose1.data.MovieRepository
import com.luthfirr.submissioncompose1.model.FavoriteMovies
import com.luthfirr.submissioncompose1.model.Movie
import com.luthfirr.submissioncompose1.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel (
    private val repository: MovieRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FavoriteMovies>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavoriteMovies>>
    get() = _uiState

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFavoriteMoviesById(movieId))
        }
    }

    fun addToFavorite(movie: Movie, count: Int) {
        viewModelScope.launch {
            repository.updateFavoriteMovies(movie.id, count)
        }
    }
}