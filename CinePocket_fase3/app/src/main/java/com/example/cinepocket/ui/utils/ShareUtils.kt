package com.example.cinepocket.ui.utils

import android.content.Context
import android.content.Intent

fun shareMovie(context: Context, title: String, overview: String) {
    val text = "Te recomiendo: $title\n\n$overview"
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Compartir con"))
}
