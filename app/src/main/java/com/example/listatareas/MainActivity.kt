package com.example.listatareas

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listatareas.ui.theme.ListaTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaTareasTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversacion()
                }
            }
        }
    }
}

data class Message(val autor: String, val body:String)

@Composable
fun MessageCard(sms: Message, index: Int){
    Row {
        //Guardamos el estado del check para modificar cuando checkeemos
        var isChecked by remember { mutableStateOf(false)}
        Checkbox(checked = isChecked, onCheckedChange = { isChecked = it },
            modifier = Modifier.padding(8.dp),
            colors = CheckboxDefaults.colors(checkedColor = Color.LightGray),
        )
        //Añadimos un espacio entre la imagen y el texto

        // Realizamos un seguimiento si el mensaje se expande o no en este
        // variable
        var isExpanded by remember {mutableStateOf(false)}
        // el color de la superficie se actualizará gradualmente de un color al otro
        val colorSurface by animateColorAsState(
            if (isExpanded && !isChecked) MaterialTheme.colors.primary
            else if (isChecked) Color.LightGray
            else MaterialTheme.colors.surface
        )
        Column (modifier = Modifier.clickable { isChecked = !isChecked }) {

            Text(
                text = sms.autor,
                color = MaterialTheme.colors.secondaryVariant,
                style = if (!isChecked) MaterialTheme.typography.subtitle2
                else TextStyle(textDecoration = TextDecoration.LineThrough)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface (
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // el color del color de la superficie cambiará gradualmente de primario a superficial
                color = colorSurface,
                // animateContentSize cambiará el tamaño de la superficie gradualmente
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = sms.body,
                    modifier = Modifier
                        .padding(all = 4.dp)
                        .clickable { isExpanded = !isExpanded },
                    // Si el mensaje esta expandido, mostramos todo su contenido
                    // de lo contrario, solo mostramos la primera línea
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = if (!isChecked) MaterialTheme.typography.body2
                    else TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
        }
    }
}

@Composable
fun Conversacion(){
    val listaTareas = remember { mutableStateListOf<Message>() }
    Column(modifier = Modifier.fillMaxHeight()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            var texto by remember {
                mutableStateOf("")
            }
            var texto2 by remember {
                mutableStateOf("")
            }
            Column(modifier = Modifier
                .weight(3f)
                .padding(start = 20.dp)

            ) {
                OutlinedTextField(
                    value = texto,
                    label = { Text(text = "Título tarea") },
                    onValueChange = { texto = it },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.primary,
                        focusedLabelColor = MaterialTheme.colors.primary)
                )
                OutlinedTextField(
                    value = texto2,
                    onValueChange = { newText -> texto2 = newText },
                    label = { Text(text = "Descripción") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.primary,
                        focusedLabelColor = MaterialTheme.colors.primary),
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    listaTareas.add(Message(texto,texto2))
                    texto = ""
                    texto2 = ""

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier
                    .weight(1f)
                    .padding(top=40.dp,end=10.dp)

            ) {
                Text(text = "+")
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Surface() {
            LazyColumn {
                itemsIndexed(listaTareas){index, item ->
                    MessageCard(sms = item, index)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConversacion() {
    ListaTareasTheme {
        Conversacion()
    }
}