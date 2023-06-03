package com.luthfirr.submissioncompose1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luthfirr.submissioncompose1.ui.component.BottomBar
import com.luthfirr.submissioncompose1.ui.theme.SubmissionCompose1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SubmissionCompose1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyMovieApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyItunesApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
   Scaffold(
       bottomBar = {
                   BottomBar(navController = navController)
       },
       topBar = {
           MediumTopAppBar(
               title ={
                   Text(text = "My iTunes App")
               }
           )
       }
   ) { contentPadding ->
       NavHost(navController = navController,
           startDestination = "home",
           modifier = Modifier.padding(contentPadding)
       ) {
           composable("home") {
//               HomeScreen()
           }
           composable("favorite") {
//               FavoriteScreen()
           }
           composable("profile") {
//               ProfileScreen()
           }
       }

   }
}
