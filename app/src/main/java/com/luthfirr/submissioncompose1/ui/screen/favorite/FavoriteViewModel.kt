package com.luthfirr.submissioncompose1.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luthfirr.submissioncompose1.data.MovieRepository
import com.luthfirr.submissioncompose1.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FavoriteState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavoriteState>>
        get() = _uiState

    fun getAddedFavoriteMovie() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            movieRepository.getAddedFavoriteMovies()
                .collect { favoriteMovie ->
                    val totalRequiredPoint =
                        favoriteMovie.sumOf { it.movie.voteAverage?.toInt()!! * it.count }
                    _uiState.value = UiState.Success(FavoriteState(favoriteMovie, totalRequiredPoint))
                }
        }
    }

    fun updateFavoriteMovie(movieId: Int, count: Int) {
        viewModelScope.launch {
            movieRepository.updateFavoriteMovies(movieId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedFavoriteMovie()
                    }
                }
        }
    }
}