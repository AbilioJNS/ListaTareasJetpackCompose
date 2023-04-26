package com.example.listatareas

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

sealed class menu_Items(
    val icon: ImageVector,
    val title: String?,
    val ruta: String
    ) {
    object items1:
    menu_Items(Icons.Default.Home,
           "", "home"
        )
    object items3:
        menu_Items(Icons.Default.Search,
            "/Buscar","pantalla2"
        )
    object items2:
        menu_Items(Icons.Default.Share,
            "/Compartir","pantalla3"
        )
    object items4:
        menu_Items(Icons.Default.Send,
            "/Enviar", "pantalla4"
        )
}

