package com.luthfirr.submissioncompose1.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luthfirr.submissioncompose1.di.Injection
import com.luthfirr.submissioncompose1.model.FavoriteMovies
import com.luthfirr.submissioncompose1.ui.ViewModelFactory
import com.luthfirr.submissioncompose1.ui.common.UiState
import com.luthfirr.submissioncompose1.ui.component.ItemMovie

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int?) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRewards()
            }
            is UiState.Success -> {
                HomeContent(
                    favoriteMovies = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    favoriteMovies: List<FavoriteMovies>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int?) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(favoriteMovies) {data ->
            ItemMovie(
                posterPath = data.movie.posterPath,
                modifier = Modifier.clickable {
                    navigateToDetail(data.movie.id)
                }
            )
        }
    }
}