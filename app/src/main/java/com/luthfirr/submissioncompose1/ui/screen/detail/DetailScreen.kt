package com.luthfirr.submissioncompose1.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.luthfirr.submissioncompose1.R
import com.luthfirr.submissioncompose1.di.Injection
import com.luthfirr.submissioncompose1.ui.ViewModelFactory
import com.luthfirr.submissioncompose1.ui.common.UiState
import com.luthfirr.submissioncompose1.ui.component.ButtonFavorite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieId: Int,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
//    navigateToFavorite: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMovieById(movieId)
            }
            is UiState.Success -> {
                val data = uiState.data
                data.movie.voteAverage?.let {
                    DetailContent(
                        data.movie.backdropPath,
                        data.movie.title,
                        data.movie.overview,
                        it.toInt(),
                        data.count,
                        onBackClick = navigateBack,
                        onAddToFavorite = { count ->
                            viewModel.addToFavorite(data.movie, count)
                        }
                    )
                }
            }
            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun DetailContent(
    backdropPath: String?,
    title: String?,
    overview: String?,
    voteAverage: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToFavorite: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    var totalPoint by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = baseUrl + backdropPath,
                    contentDescription = backdropPath,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                title?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                    )
                }
                Text(
                    text = stringResource(R.string.vote_average, voteAverage),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = overview.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Gray))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
//            FavoriteButton(
//                text = if (orderCount > 0) stringResource(R.string.remove_from_favorite) else stringResource(R.string.add_to_favorite),
//                onClick = {
//                    if (orderCount > 0) onAddToCart(0) else onAddToCart(orderCount + 1)
//                }
//            )
            ButtonFavorite(
                orderCount = orderCount,
                onAddToFavorite = onAddToFavorite,
            )
        }
    }
}