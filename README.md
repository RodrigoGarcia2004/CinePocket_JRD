# 🎬 CinePocket_JRD

> Tu gestor personal de películas en Android

CinePocket_JRD es una aplicación móvil diseñada para los amantes del cine que quieren descubrir, organizar y compartir sus películas favoritas de manera sencilla.

---

## 👥 Equipo de Desarrollo

- **Javier **
- **Rodrigo **  
- **Diego **

---

## 📖 Sobre el Proyecto

CinePocket_JRD es una aplicación Android desarrollada para consolidar conocimientos en desarrollo móvil moderno, utilizando las mejores prácticas con **Jetpack Compose**, navegación entre pantallas, consumo de APIs externas y funcionalidades nativas del sistema.

### 🎯 Objetivos

- Crear una experiencia de usuario fluida e intuitiva
- Integrar datos reales mediante una API de películas
- Implementar navegación clara entre múltiples vistas
- Ofrecer funcionalidades de compartir y favoritos

---

## ✨ Funcionalidades

### 🔐 Autenticación
Pantalla de inicio de sesión que permite el acceso mediante email y contraseña (simulada), preparada para una futura integración con autenticación real.

### 🎥 Explorar Películas
- Listado ordenado por calificación
- Información clave: título, fecha de estreno y valoración
- Importar películas desde API externa
- Marcar películas como favoritas
- Gestión del listado completo

### 📱 Detalles de Película
Vista ampliada con:
- Información detallada de cada película
- Compartir mediante WhatsApp o Google
- Realizar llamadas
- Abrir información adicional en el navegador

### ⭐ Favoritos
Acceso rápido a tus películas marcadas como favoritas desde una sección dedicada.

---

## 🏗️ Estructura del Proyecto

```
app/src/main/java/com/example/cinepocket/
│
├── 📂 data/
│   ├── 📂 di/
│   │   └── AppModule.kt                    # Inyección de dependencias (Hilt/Dagger)
│   │
│   ├── 📂 local/
│   │   ├── 📂 dao/                         # Data Access Objects
│   │   ├── 📂 databases/                   # Configuración de Room Database
│   │   └── 📂 entity/                      # Entidades de base de datos local
│   │
│   ├── 📂 remote/
│   │   ├── 📂 datasource/                  # Fuentes de datos remotas
│   │   ├── 📂 model/                       # Modelos de respuesta de la API
│   │   └── 📂 retrofit/                    # Configuración de Retrofit
│   │
│   └── 📂 repository/
│       └── MovieRepository.kt              # Repositorio principal de películas
│
├── 📂 navigation/
│   ├── AppNavHost.kt                       # Host de navegación principal
│   └── Routes.kt                           # Definición de rutas
│
└── 📂 ui/
    ├── 📂 screens/
    │   ├── LoginScreens.kt                 # Pantalla de inicio de sesión
    │   ├── HomeScreens.kt                  # Pantalla principal con listado
    │   ├── DetailScreen.kt                 # Pantalla de detalle de película
    │   └── FavoritesScreen.kt              # Pantalla de favoritos
    │
    ├── 📂 theme/
    │   ├── AppMoviesTheme.kt               # Tema principal de la app
    │
    ├── 📂 utils/
    │   ├── ConnectivityObserver.kt         # Interfaz de observación de conectividad
    │   ├── NetworkConnectivityObserver.kt  # Implementación de observador de red
    │   ├── Intents.kt                      # Gestión de intents del sistema
    │   └── ShareUtils.kt                   # Utilidades para compartir contenido
    │
    └── 📂 viewmodel/
        └── (ViewModels de las pantallas)
```

### 📋 Descripción de Componentes

#### **Data Layer**
- **DI (Dependency Injection):** Configuración de módulos para inyección de dependencias
- **Local:** Gestión de persistencia local con Room Database
- **Remote:** Comunicación con APIs externas mediante Retrofit
- **Repository:** Patrón repositorio para abstraer las fuentes de datos

#### **Navigation**
- Configuración de navegación con Navigation Compose
- Definición de rutas y argumentos entre pantallas

#### **UI Layer**
- **Screens:** Pantallas principales de la aplicación
- **Theme:** Sistema de diseño y estilos
- **Utils:** Utilidades reutilizables (conectividad, intents, compartir)
- **ViewModel:** Lógica de presentación siguiendo arquitectura MVVM

---

## 🛠️ Tecnologías

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose
- **Navegación:** Navigation Compose
- **Inyección de Dependencias:** Hilt/Dagger
- **Persistencia Local:** Room Database
- **Networking:** Retrofit + OkHttp
- **API:** REST API para datos de películas
- **Arquitectura:** MVVM (Model-View-ViewModel)
- **Otros:** Android Intents (llamadas, compartir, navegación web)

---

## 🚀 Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/RodrigoGarcia2004/CinePocket_JRD.git
   ```

2. **Abrir en Android Studio**
   - Abre el proyecto con Android Studio Arctic Fox o superior

3. **Sincronizar dependencias**
   - Espera a que Gradle sincronice automáticamente

4. **Ejecutar**
   - Conecta un dispositivo físico o inicia un emulador
   - Presiona el botón Run ▶️

---

## 🎨 Diseño

- Interfaz minimalista centrada en la experiencia de usuario
- Diseño basado en tarjetas (cards) para mejor legibilidad
- Navegación intuitiva entre pantallas
- Separación clara de responsabilidades (login, listado, detalle)
- Arquitectura MVVM para separación de capas

---

## 📚 Documentación

La documentación completa del código generada con Dokka está disponible en:

```
build/dokka/html/index.html
```

Para visualizar la documentación:
1. Navega a la carpeta `build/dokka/html/`
2. Abre `index.html` en tu navegador
3. O accede directamente durante el desarrollo en: `http://localhost:63342/CinePocket/build/dokka/html/index.html`

### Generar Documentación

Para regenerar la documentación:
```bash
./gradlew dokkaHtml
```

---

## 🏛️ Arquitectura

El proyecto sigue la arquitectura **MVVM (Model-View-ViewModel)** con las siguientes capas:

```
┌─────────────────────────────────────┐
│         UI Layer (Compose)          │
│  Screens + ViewModels + Navigation  │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│       Domain Layer (Optional)       │
│        Use Cases + Models           │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│          Data Layer                 │
│  Repository + DataSources + Models  │
└──────────────┬──────────────────────┘
               │
       ┌───────┴────────┐
       │                │
┌──────▼──────┐  ┌──────▼──────┐
│   Remote    │  │    Local    │
│  (Retrofit) │  │   (Room)    │
└─────────────┘  └─────────────┘
```

---

## 📄 Licencia

Este proyecto ha sido desarrollado con fines educativos.

---

## 📞 Contacto

Si tienes preguntas o sugerencias, no dudes en contactar al equipo de desarrollo.

---

**Desarrollado con ❤️ usando Kotlin y Jetpack Compose**
