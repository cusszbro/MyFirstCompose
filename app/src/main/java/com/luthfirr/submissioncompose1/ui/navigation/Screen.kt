package com.luthfirr.submissioncompose1.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Favorite: Screen("favorite")
    object About: Screen("about")
    object Detail : Screen("home/{movieId}") {
        fun createRoute(movieId: Int?) = "home/$movieId"
    }
}
