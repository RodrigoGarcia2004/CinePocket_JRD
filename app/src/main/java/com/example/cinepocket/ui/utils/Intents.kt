package com.example.cinepocket.ui.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Comparte texto directamente en WhatsApp.
 *
 * Si WhatsApp no está instalado, abre el selector de apps.
 *
 * @param context Contexto para lanzar el intent
 * @param text Texto a compartir
 */
fun shareMovieWhatsApp(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
        setPackage("com.whatsapp")
    }
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        context.startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                },
                "Compartir con..."
            )
        )
    }
}

/**
 * Abre el marcador telefónico con un número prellenado.
 *
 * @param context Contexto para lanzar el intent
 * @param phone Número de teléfono (ej: "123456789")
 */
fun openDial(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phone")
    }
    context.startActivity(intent)
}

/**
 * Abre una URL en el navegador.
 *
 * @param context Contexto para lanzar el intent
 * @param url URL completa (debe incluir http:// o https://)
 */
fun openWeb(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}