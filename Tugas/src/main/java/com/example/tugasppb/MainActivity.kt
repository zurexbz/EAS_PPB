package com.example.tugasppb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") { SplashScreen(navController)}
                composable("login") { LoginScreen(navController) }
                composable("homescreen") { HomeScreen(navController) }
                composable("detailfilm") { DetailFilm(navController)}
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    val navController = rememberNavController()
    DetailFilm(navController = navController)
}
