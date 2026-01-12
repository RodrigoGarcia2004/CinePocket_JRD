# CinePocket — App de Películas

Proyecto de Android (Kotlin + Jetpack Compose) basado en la guía del curso. En esta primera fase se implementa una app **funcional y navegable** con 3 pantallas: Login → Home (lista fake) → Detalle (fake).
# NoMorePay — Gestor de suscripciones y pruebas gratis

NoMorePay es una app Android pensada para ayudar a no pagar de más: organiza **suscripciones**, **pruebas gratis** y **promociones**, y permite llevar un control para recordar cuándo pueden empezar a cobrarte.
## Autores
- Javier Escudero Garcia
- Diego Forteza
- Rodrigo 

## Introducción y objetivos
El objetivo es desarrollar una aplicación de películas aplicando diseño de pantallas con Jetpack Compose y navegación entre pantallas.   
En la **Fase 1** se prioriza que la app compile, sea usable y permita navegar correctamente entre Login, Home y Detalle con datos simulados.
El objetivo del proyecto es crear una app funcional con varias pantallas y navegación, aplicando buenas prácticas de desarrollo en Android.  
En esta primera fase se prioriza tener una app **navegable**, con interfaz cuidada y datos de ejemplo para validar el flujo.

## Estado del proyecto 
Implementado según requisitos de la fase:
- Pantalla **Login** con autenticación ficticia y navegación a Home. 
- Pantalla **Home** con listado de películas en `LazyColumn` y tarjetas (`Card`) con información mínima. 
- Pantalla **Detalle** con información ampliada de una película (datos fake) y botón para volver. 
- Navegación básica con `navigation-compose`.
- Categorías superiores: Suscripciones, Pruebas gratis, Promociones, Apps, Servicios. 
- Pantalla principal con lista de servicios (ejemplo: Netflix, Spotify, Amazon Prime, etc.). 
- Botón principal “Empezar”.

## Decisiones clave de diseño/desarrollo
- Datos “fake” en memoria para centrarnos en UI + navegación (sin Room ni API todavía). 
- Estructura por capas simple:
  - `data/` (modelo Movie + lista fake)
  - `ui/screens/` (LoginScreen, HomeScreen, DetailScreen)
  - `navigation/` (rutas y NavHost)
- Interfaz base con diseño tipo home (header + chips/categorías + lista). 
- Listado de elementos con tarjetas (Cards) para cada servicio.
- Navegación preparada para ampliar a:
  - Alta/edición de una suscripción
  - Vista de detalle
  - Alertas/recordatorios (fases posteriores)

## Requisitos técnicos
- Android Studio + Kotlin
- Jetpack Compose (Material 3)
- Jetpack Compose 
- Navigation Compose

## Cómo ejecutar
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/TU_USUARIO/TU_REPO.git

## Conclusiones
En esta fase se ha conseguido una app navegable y estable con las pantallas mínimas requeridas, dejando preparada la base para añadir MVVM, persistencia y red en fases posteriores.
