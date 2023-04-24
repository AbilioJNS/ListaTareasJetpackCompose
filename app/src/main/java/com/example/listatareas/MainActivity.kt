package com.example.listatareas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.listatareas.menu_Items.*
import com.example.listatareas.ui.theme.ListaTareasTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaTareasTheme {
                Principal()
            }
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun Principal() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {/*TODO*/ },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Filled.Create, contentDescription = null)
            }
        },
        bottomBar = {
            BarraNavegacionInferior(navController)
        }
    ) {
        NavigationHost(navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewConversacion() {
    ListaTareasTheme {
        Principal()
    }
}

@Composable
fun BarraNavegacionInferior(navController: NavHostController) {
    var menu_Items = listOf<menu_Items>(items1, items4)
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        )
    ) {
        menu_Items.forEach() { item ->
            BottomNavigationItem(
                selected = true,
                onClick = {
                          navController.navigate(item.ruta)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}
