package com.example.listatareas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.listatareas.menu_Items.*

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = items1.ruta)
    {
        composable(items1.ruta){
            PantallaPrincipal()
        }
        composable(items2.ruta + "/{titulo}",
            arguments = listOf(
                navArgument(name = "titulo"){
                    type = NavType.StringType
                })
        ){
            Pantalla1(it.arguments?.getString("titulo"))
        }
        composable(items3.ruta + "/{titulo}",
            arguments = listOf(
                navArgument(name = "titulo"){
                    type = NavType.StringType
                })
        ){
            Pantalla2(it.arguments?.getString("titulo"))
        }
        composable(items4.ruta + "/{titulo}",
            arguments = listOf(
                navArgument(name = "titulo"){
                    type = NavType.StringType
                })
        ){
            Pantalla1(it.arguments?.getString("titulo"))
        }

    }

}