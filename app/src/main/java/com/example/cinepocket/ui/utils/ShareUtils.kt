package com.example.cinepocket.ui.utils

import android.content.Context
import android.content.Intent

/**
 * Comparte información de una película mediante el selector de Android.
 *
 * El usuario puede elegir la app (WhatsApp, Telegram, Email, etc.)
 *
 * @param context Contexto para lanzar el intent
 * @param title Título de la película
 * @param overview Sinopsis de la película
 */
fun shareMovie(context: Context, title: String, overview: String) {
    val text = "Te recomiendo: $title\n\n$overview"
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Compartir con"))
}