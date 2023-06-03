package com.luthfirr.submissioncompose1.ui.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luthfirr.submissioncompose1.di.Injection
import com.luthfirr.submissioncompose1.ui.ViewModelFactory
import com.luthfirr.submissioncompose1.ui.common.UiState
import com.luthfirr.submissioncompose1.R
import com.luthfirr.submissioncompose1.ui.component.FavoriteButton
import com.luthfirr.submissioncompose1.ui.component.ItemMovieFavorite

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedFavoriteMovie()
            }
            is UiState.Success -> {
                FavoriteContent(
                    uiState.data,
                    onProductCountChanged = { movieId, count ->
                        viewModel.updateFavoriteMovie(movieId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    state: FavoriteState,
    onProductCountChanged: (id: Int, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shareMessage = stringResource(
        R.string.share_message,
        state.favoriteMovies.count(),
        state.totalRequiredPoint
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        FavoriteButton(
            text = stringResource(R.string.total_order, state.totalRequiredPoint),
            enabled = state.favoriteMovies.isNotEmpty(),
            onClick = {
                onOrderButtonClicked(shareMessage)
            },
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.favoriteMovies, key = { it.movie.id?: 1 }) { item ->
                Divider(
                    modifier.padding(bottom = 4.dp)
                )
                ItemMovieFavorite(
                    posterPath = item.movie.posterPath,
                    voteAverage = item.movie.voteAverage,
                    originalTitle = item.movie.originalTitle,
                    releaseDate = item.movie.releaseDate,
                    overview = item.movie.overview
                )
            }
        }
    }
}