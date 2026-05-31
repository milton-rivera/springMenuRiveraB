Markdown
# 🍽️ Sistema de Gestión de Menú - Restaurante ITU

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

## 📖 Resumen del Proyecto
El **Gestor de Menú - Restaurante ITU** es una aplicación web dinámica orientada a la administración integral de la oferta gastronómica de un restaurante. Permite a los gerentes digitalizar el menú, gestionando de forma centralizada los platos, sus recetas, los ingredientes necesarios y el equipo de chefs a cargo. El sistema garantiza la integridad de los datos y ofrece una interfaz gráfica fluida e intuitiva.

## 🚀 Características Principales
* **Gestión Completa de Platos (CRUD):** Creación, lectura, actualización y eliminación de alimentos con carga de imágenes dinámicas.
* **Manejo de Relaciones Complejas:** Asignación de recetas a platos, vinculación de múltiples ingredientes por receta y asignación de chefs responsables.
* **Integridad de Base de Datos:** Sistema de borrado en cascada y remoción de registros huérfanos (Orphan Removal) para evitar datos residuales.
* **Validaciones en Tiempo Real:** Intercepciones en el cliente (JavaScript) para evitar envíos de formularios con precios negativos o sin chefs asignados.
* **Operaciones Asíncronas (AJAX):** Creación y eliminación de ingredientes y chefs sin recargar la página mediante Fetch API.
* **Interfaz Profesional:** Diseño responsivo con notificaciones estéticas (SweetAlert2) y estado de carga optimizado.

## 🏗️ Cómo está desarrollado (Arquitectura)
El proyecto está construido bajo el patrón arquitectónico **MVC (Modelo-Vista-Controlador)**:
* **Modelo:** Mapeo objeto-relacional (ORM) robusto utilizando JPA/Hibernate para representar la lógica de negocio (`Alimento`, `Receta`, `Chef`, `Ingrediente`).
* **Vista:** Renderizado del lado del servidor utilizando Thymeleaf, potenciado con interacciones del lado del cliente vía JavaScript puro.
* **Controlador:** Endpoints RESTful en Spring Boot que manejan tanto el enrutamiento de vistas web como las respuestas JSON para las peticiones AJAX.

## 🛠️ Tecnologías y Herramientas
**Backend:**
* Java 17+
* Spring Boot (Web, Data JPA)
* Hibernate (ORM)
* Maven (Gestión de dependencias)

**Frontend:**
* HTML5, CSS3, JavaScript Vanilla
* Thymeleaf (Motor de plantillas)
* Bootstrap 5 (UI Framework)
* SweetAlert2 (Gestión de alertas)

**Base de Datos:**
* MySQL (Motor relacional)

## 📋 Requisitos Previos (Para ejecutar localmente)
Para correr este proyecto en tu entorno local, necesitarás tener instalado:
* **Java JDK 17** (o superior).
* **Apache Maven**.
* **MySQL Server** (y opcionalmente una interfaz como phpMyAdmin o MySQL Workbench).
* Un IDE compatible (IntelliJ IDEA, Eclipse, VS Code).

## 📂 Estructura del Proyecto
```text
gestor-menu-restaurante/
├── src/
│   ├── main/
│   │   ├── java/com/example/springMenuRivera/
│   │   │   ├── controller/      # Controladores web y API REST (AJAX)
│   │   │   ├── modelo/          # Entidades JPA (Alimento, Chef, Receta, etc.)
│   │   │   └── repository/      # Interfaces de Spring Data JPA
│   │   └── resources/
│   │       ├── static/          # Archivos estáticos (CSS, JS, Logos)
│   │       ├── templates/       # Vistas HTML (Thymeleaf)
│   │       └── application.properties # Configuración del servidor y DB
├── uploads/                     # Carpeta de almacenamiento de imágenes dinámicas
├── restaurante_db.sql           # Respaldo inicial de la base de datos (Estructura y Datos)
└── pom.xml                      # Archivo de configuración de Maven
⚙️ Instrucciones de Instalación
Sigue estos pasos para desplegar el proyecto en tu máquina:

Clonar el repositorio:

Bash
git clone [https://github.com/TU_USUARIO/TU_REPOSITORIO.git](https://github.com/TU_USUARIO/TU_REPOSITORIO.git)
Restaurar la Base de Datos:

Abre tu gestor de MySQL.

Crea una base de datos vacía llamada restaurante_db.

Importa el archivo restaurante_db.sql (ubicado en la raíz del proyecto) para generar las tablas y poblar los datos maestros.

Configurar Credenciales:

Abre el archivo src/main/resources/application.properties.

Modifica las variables spring.datasource.username y spring.datasource.password con los datos de tu servidor MySQL local.

Ejecutar la Aplicación:

Compila y ejecuta el proyecto utilizando tu IDE o mediante Maven:

Bash
mvn spring-boot:run
Acceder al Sistema:

Abre tu navegador y dirígete a http://localhost:8080.

💡 Sobre el Proyecto
Este sistema fue diseñado con el objetivo de crear soluciones robustas que unan los procesos operativos físicos (la cocina, el inventario, la preparación) con herramientas de software eficientes y escalables.

Desarrollado en Mendoza, Argentina, con estándares de calidad de Software Factory. 🚀


*(Nota: En la sección "Instrucciones de Instalación", recuerda cambiar `TU_USUARIO/TU_REPOSITORIO` por el enlace real de tu GitHub).*
