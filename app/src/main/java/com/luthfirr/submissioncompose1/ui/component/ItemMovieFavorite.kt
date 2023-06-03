package com.luthfirr.submissioncompose1.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luthfirr.submissioncompose1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemMovieFavorite(
    posterPath: String?,
    voteAverage: Double?,
    originalTitle: String?,
    releaseDate: String?,
    overview: String?,
    modifier: Modifier = Modifier
) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"

    Row {
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = modifier
                .size(140.dp, 200.dp)
        ) {
            AsyncImage(
                model = baseUrl + posterPath,
                contentDescription = posterPath,
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier.padding(4.dp)
        ) {
            Text(
                text = originalTitle.toString(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Rate: $voteAverage",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Release date: $releaseDate",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Description: \n$overview",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 8
            )
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun itemMoviePreview() {
//    ItemMovieFavorite(
//        "/2klQ1z1fcHGgQPevbEQdkCnzyuS.jpg"
//    )
//}