package com.example.cinepocket.ui.utils


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

fun shareMovieWhatsApp(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
        setPackage("com.whatsapp")
    }
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // Fallback: share normal
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

fun openDial(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:$phone".toUri()
    }
    context.startActivity(intent)
}

fun openWeb(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(intent)
}
