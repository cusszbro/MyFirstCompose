package com.luthfirr.submissioncompose1

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luthfirr.submissioncompose1.ui.component.BottomBar
import com.luthfirr.submissioncompose1.ui.navigation.Screen
import com.luthfirr.submissioncompose1.ui.screen.about.AboutScreen
import com.luthfirr.submissioncompose1.ui.screen.detail.DetailScreen
import com.luthfirr.submissioncompose1.ui.screen.favorite.FavoriteScreen
import com.luthfirr.submissioncompose1.ui.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMovieApp(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController as NavHostController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController as NavHostController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { movieId ->
                        navController.navigate(Screen.Detail.createRoute(movieId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                val context = LocalContext.current
                FavoriteScreen(
                    onOrderButtonClicked = { message ->
                        shareMovie(context, message)
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType} ),
            ) {
                val id = it.arguments?.getInt("movieId") ?: 1
                DetailScreen(
                    movieId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

private fun shareMovie(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.favorite_movies))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.favorite_movies)
        )
    )
}
