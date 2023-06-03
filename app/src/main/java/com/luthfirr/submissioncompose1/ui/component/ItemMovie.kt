package com.luthfirr.submissioncompose1.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemMovie(
    posterPath: String?,
    modifier: Modifier = Modifier
) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        AsyncImage(
            model = baseUrl + posterPath,
            contentDescription = posterPath,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun itemMoviePreview() {
    ItemMovie(
        "/2klQ1z1fcHGgQPevbEQdkCnzyuS.jpg"
    )
}