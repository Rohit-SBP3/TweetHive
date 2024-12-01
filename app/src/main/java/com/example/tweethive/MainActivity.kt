package com.example.tweethive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweethive.screens.CategoryScreen
import com.example.tweethive.screens.TweetScreen
import com.example.tweethive.ui.theme.TweetHiveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetHiveTheme {
                App()
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category"){
        composable(route = "category"){
            CategoryScreen{
                navController.navigate("tweet/${it}")
            }
        }
        composable(route = "tweet/{category}",
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType
                }
            )
        ){
            TweetScreen()
        }
    }
}




