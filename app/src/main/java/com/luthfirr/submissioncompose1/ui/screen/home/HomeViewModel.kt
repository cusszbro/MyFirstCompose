package com.luthfirr.submissioncompose1.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luthfirr.submissioncompose1.data.MovieRepository
import com.luthfirr.submissioncompose1.model.FavoriteMovies
import com.luthfirr.submissioncompose1.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<FavoriteMovies>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FavoriteMovies>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            movieRepository.getAllMovies()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}