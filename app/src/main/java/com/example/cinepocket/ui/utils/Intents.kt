package com.example.cinepocket.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Comparte información de una película.
 *
 * @param context Contexto para lanzar el intent
 * @param title Título de la película
 * @param overview Sinopsis de la película
 */
fun shareMovie(context: Context, title: String, overview: String)
{
    val text = "Te recomiendo: $title\n"
    val intent = Intent(Intent.ACTION_SEND).apply{
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Compartir con"))
}

/**
 * Abre el marcador telefónico con un número prellenado.
 *
 * @param context Contexto para lanzar el intent
 * @param phone Número de teléfono
 */
fun openDial(context: Context, phone: String)
{
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
    context.startActivity(intent)
}

/**
 * Abre una URL en el navegador.
 *
 * @param context Contexto para lanzar el intent
 * @param url URL completa (debe incluir http:// o https://)
 */
fun openWeb(context: Context, url: String)
{
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}