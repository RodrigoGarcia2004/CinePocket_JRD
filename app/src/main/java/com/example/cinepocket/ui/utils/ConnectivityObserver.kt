package com.example.cinepocket.ui.utils

import kotlinx.coroutines.flow.Flow

/**
 * Interfaz para observar el estado de la conexión a internet
 *
 * Implementaciones de esta interfaz emiten true cuando hay conexión
 * y false cuando se pierde
 */
interface ConnectivityObserver {
    /**
     * Flow que emite el estado de conexión
     * - true: Hay internet
     * - false: No hay internet
     */
    val isConnected: Flow<Boolean>
}