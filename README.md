# Inmobiliaria

Aplicación de escritorio en Java para la gestión de una red de agencias inmobiliarias. Permite realizar operaciones completas de creación, lectura, actualización y eliminación (CRUD) sobre todas las entidades del sistema: agencias, titulares, vendedores e inmuebles (pisos y locales comerciales). La aplicación se conecta a una base de datos SQLite mediante JDBC.

---

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado el siguiente software:

| Herramienta | Versión mínima | Notas |
|---|---|---|
| Java JDK | 17 | Configurado con compilador java25; compatible desde JDK 17 |
| SQLite | 3 | No requiere servidor; la base de datos es un archivo local |
| Maven | 3.8+ | Para compilación y gestión de dependencias |
| IDE | — | Se recomienda IntelliJ IDEA (soporte nativo para Maven y Java) |

> La dependencia JDBC de SQLite (`sqlite-jdbc 3.45.1.0`) se descarga automáticamente con Maven; no es necesario instalarla manualmente.

---

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/Senku51/inmobiliaria.git
```

### 2. Configurar la base de datos

Al ejecutar la aplicación por primera vez, el archivo `inmobiliaria.db` se crea automáticamente en la raíz del proyecto si no existe.

**Ejecutar el script de creación de tablas** (si dispones de un `schema.sql`):

```bash
sqlite3 inmobiliaria.db < schema.sql
```

**Cargar datos iniciales de prueba** (si los hay):

```bash
sqlite3 inmobiliaria.db < data.sql
```

**Cambiar la cadena de conexión** (opcional): la conexión está definida en `src/main/java/conexion/ConexionBD.java`. Por defecto apunta a `inmobiliaria.db` en el directorio raíz. Para cambiar la ruta, edita la constante `URL` en esa clase.

---

## Compilación y ejecución

### Con Maven (línea de comandos)

```bash
# Compilar y empaquetar
mvn clean package

# Ejecutar
java -cp target/inmobiliaria-1.0-SNAPSHOT.jar app.app
```

### Desde IntelliJ IDEA

1. Abre el proyecto: `File → Open` y selecciona la carpeta `inmobiliaria`.
2. IntelliJ detectará el `pom.xml` automáticamente.
3. Ejecuta la clase `app.app` desde el editor (botón junto al método `main`).

---

## Estructura del proyecto

```
inmobiliaria/
├── src/
│   └── main/
│       └── java/
│           ├── app/
│           │   └── app.java                    # Punto de entrada; menú principal
│           ├── conexion/
│           │   └── ConexionBD.java             # Gestión de la conexión JDBC a SQLite
│           ├── dao/
│           │   ├── AgenciaDAO.java             # Interfaz CRUD para Agencia
│           │   ├── AgenciaDAOImpl.java         # Implementación de AgenciaDAO
│           │   ├── InmuebleDAO.java            # Interfaz CRUD para Inmueble
│           │   ├── InmuebleDAOImpl.java        # Implementación de InmuebleDAO
│           │   ├── LocalComercialDAO.java      # Interfaz CRUD para LocalComercial
│           │   ├── LocalComercialDAOImpl.java  # Implementación de LocalComercialDAO
│           │   ├── PisoDAO.java                # Interfaz CRUD para Piso
│           │   ├── PisoDAOImpl.java            # Implementación de PisoDAO
│           │   ├── TitularDAO.java             # Interfaz CRUD para Titular
│           │   ├── TitularDAOImpl.java         # Implementación de TitularDAO
│           │   ├── VendedorDAO.java            # Interfaz CRUD para Vendedor
│           │   └── VendedorDAOImpl.java        # Implementación de VendedorDAO
│           └── modelo/
│               ├── Agencia.java               # Entidad Agencia (dirección, teléfonos, fax, zona)
│               ├── Inmueble.java              # Entidad base Inmueble
│               ├── LocalComercial.java        # Especialización: licencia de apertura
│               ├── Piso.java                  # Especialización: habitaciones, baños, gas
│               ├── Titular.java               # Titular de una agencia
│               └── Vendedor.java              # Vendedor asignado a una agencia
├── inmobiliaria.db                            # Base de datos SQLite (generada en ejecución)
├── pom.xml                                    # Configuración de Maven y dependencias
└── README.md
```

---

## Equipo

| Nombre | Rol |
|---|---|
| Iker Iglesias | Jefe de Proyecto · Responsable de Calidad y Documentación |
| Sergio Perez | Diseñador de Base de Datos |
| Manuel Jesús | Desarrollador Backend |
| Carlos Martin | Desarrollador Backend |
| Dani Lagares | Desarrollador Backend · Desarrollador de Interfaz |
