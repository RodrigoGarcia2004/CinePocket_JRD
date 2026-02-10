package com.example.cinepocket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Pantalla de inicio de sesión.
 *
 * Muestra un formulario con email y contraseña. Al pulsar "Entrar"
 * se ejecuta la acción que recibe como parámetro.
 *
 * @param onLoginSuccess Se ejecuta cuando el usuario pulsa "Entrar"
 */

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (pass, setPass) = remember { mutableStateOf("") }

    val primaryColor = Color(0xFF2196F3)
    val backgroundColor = Color(0xFFF5F5F5)
    val textColor = Color(0xFF333333)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a CinePocket",
            style = MaterialTheme.typography.headlineMedium,
            color = textColor,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Descubre y guarda tus películas favoritas",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = primaryColor,
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pass,
            onValueChange = setPass,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = primaryColor,
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onLoginSuccess,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor
            )
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Sin registros · Sin anuncios",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}