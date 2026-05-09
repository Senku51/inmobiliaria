Inmobiliaria
Aplicación de escritorio en Java para la gestión de una red de agencias inmobiliarias. Permite realizar operaciones
completas de creación, lectura, actualización y eliminación (CRUD) sobre todas las entidades del sistema: agencias,
titulares, vendedores e inmuebles (pisos y locales comerciales). La aplicación se conecta a una base de datos SQLite
mediante JDBC.

Requisitos previos
Asegúrate de tener instalado el siguiente software antes de ejecutar el proyecto:

Java JDK 17 o superior (el proyecto está configurado con el compilador java25, pero es compatible desde JDK 17)
SQLite 3 (no requiere servidor; la base de datos es un archivo local inmobiliaria.db)
Maven 3.8+ para la compilación y gestión de dependencias
IDE recomendado: IntelliJ IDEA (incluye soporte nativo para Maven y Java)

La dependencia JDBC de SQLite (sqlite-jdbc 3.45.1.0) se descarga automáticamente con Maven; no es necesario instalarla
manualmente.

Clonar el repositorio
git clone https://github.com/Senku51/inmobiliaria.git

Configuración de la base de datos

Crear la base de datos: al ejecutar la aplicación por primera vez, el archivo inmobiliaria.db se crea automáticamente en
la raíz del proyecto si no existe.
Ejecutar el script SQL de creación de tablas: si dispones de un script schema.sql, ejecútalo con:

bash sqlite3 inmobiliaria.db < schema.sql

Cargar datos iniciales de prueba (si los hay):

bash sqlite3 inmobiliaria.db < data.sql

Configurar la cadena de conexión: la conexión está definida en src/main/java/conexion/ConexionBD.java. Por defecto
apunta a inmobiliaria.db en el directorio raíz. Si quieres cambiar la ruta, edita la constante URL en esa clase.

Compilación y ejecución
Con Maven (línea de comandos):
bash# Compilar y empaquetar
mvn clean package

# Ejecutar

java -cp target/inmobiliaria-1.0-SNAPSHOT.jar app.app
Desde IntelliJ IDEA:

Abre el proyecto (File → Open y selecciona la carpeta inmobiliaria).
IntelliJ detectará el pom.xml automáticamente.
Ejecuta la clase app.app desde el editor (botón ▶ junto al método main).

Estructura del proyecto
inmobiliaria/
├── src/
│ └── main/
│ └── java/
│ ├── app/
│ │ └── app.java # Punto de entrada; menú principal de la aplicación
│ ├── conexion/
│ │ └── ConexionBD.java # Gestión de la conexión JDBC a SQLite
│ ├── dao/
│ │ ├── AgenciaDAO.java # Interfaz CRUD para Agencia
│ │ ├── AgenciaDAOImpl.java # Implementación de AgenciaDAO
│ │ ├── InmuebleDAO.java # Interfaz CRUD para Inmueble
│ │ ├── InmuebleDAOImpl.java # Implementación de InmuebleDAO
│ │ ├── LocalComercialDAO.java # Interfaz CRUD para LocalComercial
│ │ ├── LocalComercialDAOImpl.java # Implementación de LocalComercialDAO
│ │ ├── PisoDAO.java # Interfaz CRUD para Piso
│ │ ├── PisoDAOImpl.java # Implementación de PisoDAO
│ │ ├── TitularDAO.java # Interfaz CRUD para Titular
│ │ ├── TitularDAOImpl.java # Implementación de TitularDAO
│ │ ├── VendedorDAO.java # Interfaz CRUD para Vendedor
│ │ └── VendedorDAOImpl.java # Implementación de VendedorDAO
│ └── modelo/
│ ├── Agencia.java # Entidad Agencia (dirección, teléfonos, fax, zona)
│ ├── Inmueble.java # Entidad base Inmueble
│ ├── LocalComercial.java # Especialización de Inmueble (licencia de apertura)
│ ├── Piso.java # Especialización de Inmueble (habitaciones, baños, gas)
│ ├── Titular.java # Titular de una agencia
│ └── Vendedor.java # Vendedor asignado a una agencia
├── inmobiliaria.db # Base de datos SQLite (generada en ejecución)
├── pom.xml # Configuración de Maven y dependencias
└── README.md # Documentación del proyecto

Equipo
Iker Iglesias Jefe de Proyecto
Sergio Perez Diseñador de Base de Datos
Manuel Jesús, Carlos Martin y Dani Lagares Desarrollador Backend
Dani Lagares Desarrollador de Interfaz
Iker Iglesias Responsable de Calidad y Documentación

