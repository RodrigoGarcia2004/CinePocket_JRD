package com.example.cinepocket.ui.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver
{
    val isConnected: Flow<Boolean>
}