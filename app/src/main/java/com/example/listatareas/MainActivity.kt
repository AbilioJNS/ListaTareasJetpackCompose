package com.example.listatareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listatareas.R
import com.example.listatareas.ui.theme.ListaTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaTareasTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversacion(listOf(
                        Message("Limpiar","Limpiar toda la casa, incluida la cocina, baños" +
                                "y el polvo de las habitaciones y salón"),
                        Message("Compra", "Hacer la compra de la lista que tenemos hecha") ,
                        Message("Planchar", "Planchar la ropa blanca y llevar cuidado con la " +
                                "temperatura que hay prendas que podrían quemarse")
                    ))
                }
            }
        }
    }
}

data class Message(val autor: String, val body:String)

@Composable
fun MessageCard(sms: Message){
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
                    modifier = Modifier.padding(all = 4.dp)
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
fun Conversacion(mensajes: List<Message>){
    LazyColumn {
        items(mensajes) { mensaje ->
            MessageCard(mensaje)
        }
    }
}

@Preview
@Composable
fun PreviewConversacion() {
    ListaTareasTheme() {
        Conversacion(listOf(
            Message("android","buen lenguaje de programación"),
            Message("Jetpack Compose", "mejora muchisimo esta programación")
        ))
    }
}