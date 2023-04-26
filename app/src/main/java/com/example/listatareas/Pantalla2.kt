package com.example.listatareas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Pantalla2(titulo:String?){
    Column() {
        titulo?.let {
            Text(text = "${it}")
        }
    }
}