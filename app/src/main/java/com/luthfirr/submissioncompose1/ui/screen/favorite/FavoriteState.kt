package com.luthfirr.submissioncompose1.ui.screen.favorite

import com.luthfirr.submissioncompose1.model.FavoriteMovies

data class FavoriteState(
    val favoriteMovies: List<FavoriteMovies>,
    val totalRequiredPoint: Int
)
