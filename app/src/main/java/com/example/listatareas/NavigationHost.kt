package com.example.listatareas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.listatareas.menu_Items.*

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = items1.ruta)
    {
        composable(items1.ruta){
            PantallaPrincipal()
        }
        composable(items2.ruta){
            Pantalla1()
        }
        composable(items3.ruta){
            Pantalla2()
        }
        composable(items4.ruta){
            Pantalla1()
        }

    }

}