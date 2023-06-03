package com.luthfirr.submissioncompose1.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.luthfirr.submissioncompose1.R

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val listRoute = listOf(
            "home",
            "favorite",
            "about"
        )

        val listImage = listOf(
            Icons.Default.Home,
            Icons.Default.Favorite,
            Icons.Default.Info
        )

        listImage.mapIndexed { index, image ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = listImage[index],
                        contentDescription = listRoute[index],
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                selected = currentRoute == listRoute[index], onClick = {
                    navController.navigate(listRoute[index]) {
                        popUpTo(navController.graph.findStartDestination().id,) {
                            saveState

                        }

                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
