CinePoket_JRD — Gestor personal de películas

CinePoket_JRD es una aplicación Android orientada a los amantes del cine que desean descubrir, guardar y organizar películas de forma sencilla. La app permite iniciar sesión, importar películas desde una API externa, consultar valoraciones, acceder a información detallada y compartir contenido fácilmente mediante aplicaciones como WhatsApp o servicios de Google.

Autores

Javier Escudero García

Rodrigo García Heredia

Diego Forteza

Introducción y objetivos

El objetivo del proyecto CinePoket_JRD es desarrollar una aplicación Android funcional que integre múltiples pantallas, navegación entre vistas y conexión con una API para el consumo de datos externos, aplicando buenas prácticas de desarrollo y diseño de interfaces.

El proyecto busca consolidar conocimientos en desarrollo móvil, especialmente en el uso de Jetpack Compose, navegación, consumo de APIs y uso de funcionalidades del sistema Android. En esta fase se prioriza disponer de una aplicación estable, navegable y con una experiencia de usuario clara.

Estado del proyecto

La aplicación cuenta actualmente con las siguientes funcionalidades:

Pantalla de login para acceso de usuario.

Listado principal de películas ordenadas por calificación.

Importación de películas desde una API externa.

Vista de detalle de cada película.

Sistema de favoritos.

Opción para compartir películas mediante WhatsApp o Google.

Integración con acciones del sistema (llamadas y navegación web).

La app se encuentra en un estado funcional y preparada para futuras ampliaciones.

Funcionalidades principales
Login de usuario

La aplicación dispone de una pantalla inicial de autenticación simulada, donde el usuario introduce su email y contraseña para acceder a la plataforma. Esta pantalla actúa como punto de entrada al resto de la aplicación y garantiza un flujo de navegación claro, sirviendo como base para una futura implementación de un sistema de autenticación real.
Listado de películas

Tras el inicio de sesión, el usuario accede a una pantalla con un listado de películas. Cada elemento del listado muestra:

Título de la película

Fecha de estreno

Valoración media

Botón para marcar como favorita

Desde esta pantalla se puede:

Importar películas desde la API.

Borrar el listado completo.

Acceder a la sección de favoritos.

El diseño se basa en tarjetas para facilitar la lectura y mejorar la experiencia de usuario.

Vista de detalle de película

Al seleccionar una película del listado, se accede a una vista de detalle que muestra información ampliada obtenida desde la API:

Título

Fecha de estreno

Valoración


Desde esta pantalla el usuario puede:

Realizar una llamada.

Compartir la película mediante WhatsApp o aplicaciones de Google.

Abrir información adicional en un navegador web.

Volver a la pantalla anterior.

Favoritos

La aplicación permite marcar películas como favoritas y consultarlas posteriormente desde una vista dedicada, facilitando la organización y acceso rápido a los contenidos preferidos del usuario.

API y consumo de datos

CinePoket_JRD está enlazada a una API externa de películas, lo que permite trabajar con datos reales y actualizados. A través de esta conexión se obtienen:

Listados de películas

Fechas de estreno

Valoraciones

El uso de una API real aporta mayor realismo al proyecto y demuestra la integración de servicios externos en una aplicación Android.

Decisiones clave de diseño y desarrollo

Interfaz limpia y minimalista, priorizando la claridad visual.

Uso de tarjetas para representar cada película.

Navegación sencilla entre pantallas mediante Navigation Compose.

Separación clara entre vistas principales (login, listado y detalle).

Base preparada para ampliaciones futuras como:

Persistencia local de datos

Arquitectura MVVM

Filtros y búsquedas avanzadas

Recomendaciones personalizadas

Requisitos técnicos

Android Studio

Kotlin

Jetpack Compose

Navigation Compose

Consumo de API REST

Uso de intents del sistema Android

Cómo ejecutar el proyecto

Clonar el repositorio:

git clone https://github.com/TU_USUARIO/CinePoket_JRD.git

Abrir el proyecto en Android Studio.

Sincronizar las dependencias del proyecto.

Ejecutar la aplicación en un emulador o dispositivo físico.

Conclusiones

En esta fase del proyecto se ha conseguido desarrollar una aplicación Android funcional, estable y con una navegación clara, que integra autenticación, consumo de datos externos y múltiples vistas.

CinePoket sienta una base sólida para futuras mejoras y ampliaciones, permitiendo seguir evolucionando el proyecto tanto a nivel técnico como funcional en próximas fases.
