package com.luthfirr.submissioncompose1.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.luthfirr.submissioncompose1.R
import com.luthfirr.submissioncompose1.ui.component.ItemDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.profile_photo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            alignment = Alignment.Center,
        )

        ItemDescription(title = "Name                 : ", description = "Luthfirrahman Dzulkarnain")
        ItemDescription(title = "Email                 : ", description = "luthfirluthfir@gmail.com")
        ItemDescription(title = "Learning path : ", description = "Android")

    }

}