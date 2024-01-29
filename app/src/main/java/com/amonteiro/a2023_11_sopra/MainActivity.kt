package com.amonteiro.a2023_11_sopra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amonteiro.a2023_11_sopra.model.MainViewModel
import com.amonteiro.a2023_11_sopra.ui.screens.DetailScreen
import com.amonteiro.a2023_11_sopra.ui.screens.SearchScreen
import com.amonteiro.a2023_11_sopra.ui.theme.A2023_11_sopraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A2023_11_sopraTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
                    //Démarrage de l'application
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {

    //Classe de gestion de la navigation
    val navController : NavHostController = rememberNavController()

    //Classe de gestion des données
    val mainViewModel : MainViewModel = viewModel()



    //Import version avec Composable
    NavHost(navController = navController, startDestination = Routes.SearchScreen.route) {

        //Route 1 vers notre SearchScreen
        composable(Routes.SearchScreen.route) {
            //on peut passer le navController à un écran s'il déclenche des navigations
            SearchScreen(navController, mainViewModel)
        }

        //Route 2 vers un écran de détail
        composable(
            route = Routes.DetailScreen.route,
            arguments = listOf(navArgument("data") { type = NavType.IntType })
        ) {
            val position = it.arguments?.getInt("data") ?: 0
            DetailScreen(position, navController, mainViewModel)
        }

    }
}