package com.luthfirr.submissioncompose1.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luthfirr.submissioncompose1.R
import com.luthfirr.submissioncompose1.ui.theme.SubmissionCompose1Theme

@Composable
fun ButtonFavorite(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    orderCount: Int,
    onAddToFavorite: (count: Int) -> Unit,
) {
    Button(
        shape = MaterialTheme.shapes.medium,
        onClick = { if (orderCount > 0) onAddToFavorite(0) else onAddToFavorite(orderCount + 1) },
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Text(
            text = if (orderCount > 0) stringResource(R.string.remove_from_favorite) else stringResource(
                R.string.add_to_favorite),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

//@Composable
//@Preview(showBackground = true)
//fun OrderButtonPreview() {
//    SubmissionCompose1Theme {
//        FavoriteButton(
//            text = "Order",
//            onClick = {}
//        )
//    }
//}