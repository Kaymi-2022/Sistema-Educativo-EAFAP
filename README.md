# Sistema de Gestión Educativa

API REST desarrollada con **Java 21 y Spring Boot** para la gestión académica de una institución educativa.

El sistema permite administrar usuarios, roles, categorías, cursos, docentes, discentes, matrículas, evaluaciones, notas, resultados académicos y horarios.

## 🚀 Tecnologías

* Java 21
* Spring Boot
* Spring Data JPA / Hibernate
* Oracle 19c
* Maven
* MapStruct
* Jakarta Validation
* JUnit 5 / Mockito
* Docker / Docker Compose
* Postman

## 📋 Funcionalidades

* Gestión de usuarios y roles.
* Gestión de categorías: **MORAL, MILITAR y ACADÉMICO**.
* Gestión de cursos y docentes responsables.
* Matrícula de discentes en cursos.
* Registro y consulta de evaluaciones.
* Registro y actualización de notas.
* Cálculo del resultado final del curso.
* Determinación de estudiantes aprobados y desaprobados.
* Gestión de aulas, actividades y horarios.
* Consulta de información académica mediante Dashboard.
* Generación de reportes académicos por estudiante.

## 🏗️ Estructura

```text
src/main/java/
└── fap/SistemaGestionEducativa/
    ├── config/
    ├── controller/
    ├── dto/
    │   ├── request/
    │   └── response/
    ├── exception/
    ├── mapper/
    ├── model/
    ├── repository/
    ├── service/
    │   └── impl/
    └── util/
```

## ⚙️ Requisitos

Antes de ejecutar el proyecto se necesita:

* Java 21
* Maven
* Docker
* Docker Compose
* Git
* Postman

Verificar las instalaciones:

```bash
java -version
mvn -version
docker --version
docker compose version
git --version
```

## 📥 1. Clonar el proyecto

```bash
git clone https://github.com/Kaymi-2022/Sistema-Educativo-EAFAP.git
```

## 🐳 2. Crear y levantar Oracle con Docker

El proyecto utiliza Oracle como base de datos.

Ejecutar desde la raíz del proyecto:

```bash
docker compose up -d
```

Verificar que el contenedor esté ejecutándose:

```bash
docker ps
```

Para revisar los logs:

```bash
docker logs -f sistema-gestion-educativa-db
```

Esperar hasta que Oracle se encuentre disponible.

## 🗄️ 3. Crear la base de datos

Una vez iniciado Oracle, ejecutar los scripts SQL del proyecto para crear:

* Secuencias.
* Tablas.
* Claves primarias y foráneas.
* Restricciones.
* Datos iniciales.

Las tablas principales son:

```text
USUARIO
ROL
USUARIO_ROL
CATEGORIA
CURSO
CURSO_DISCENTE
EVALUACION
NOTA
RESULTADO_CURSO
SEMANA_ACADEMICA
AULA
BLOQUE_HORARIO
ACTIVIDAD
HORARIO
```

## 🔧 4. Configurar Spring Boot

Configurar `application.yml` con los datos de conexión de Oracle:

```yaml
spring:
  application:
    name: sistema-gestion-educativa

  datasource:
    url: jdbc:oracle:thin:@localhost:1521/{DB_SERVICE_NAME}
    username: [NOMBRE_DE_USUARIO]
    password: [CONTRASEÑA]
    driver-class-name: oracle.jdbc.OracleDriver

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

server:
  port: 8080
```

> Ajustar usuario, contraseña, servicio y puerto de Oracle según el `docker-compose.yml`.

## ▶️ 5. Compilar y ejecutar

Compilar:

```bash
mvn clean install
```

Ejecutar pruebas:

```bash
mvn test
```

Iniciar la aplicación:

```bash
mvn spring-boot:run
```

La API estará disponible en:

```text
http://localhost:8080
```

## 📮 6. Configurar Postman

Crear un Environment:

```text
Nombre: SGE - Local

BASE_URL = http://localhost:8080
```

Las peticiones utilizarán:

```text
{{BASE_URL}}
```

Ejemplo:

```text
GET {{BASE_URL}}/api/usuarios
```

## 🧪 7. Pruebas de endpoints

Se recomienda probar los módulos en el siguiente orden:

```text
Usuario
   ↓
Rol
   ↓
UsuarioRol
   ↓
Categoría
   ↓
Curso
   ↓
CursoDiscente
   ↓
Evaluación
   ↓
Nota
   ↓
ResultadoCurso
   ↓
Horario
   ↓
Dashboard
   ↓
Reporte
```

Este orden permite respetar las relaciones existentes entre las entidades.

## 🔄 8. Detener el entorno

Detener los contenedores:

```bash
docker compose stop
```

Eliminar los contenedores:

```bash
docker compose down
```

Para eliminar también los datos persistidos de Oracle:

```bash
docker compose down -v
```

> `down -v` elimina el volumen de la base de datos.

## 📌 Flujo general

```text
Git Clone
    ↓
Docker Compose
    ↓
Oracle 19c
    ↓
Scripts SQL
    ↓
Spring Boot
    ↓
API REST
    ↓
Postman
    ↓
Pruebas de endpoints
```

## 👨‍💻 Proyecto

**Sistema de Gestión Educativa**

Proyecto académico desarrollado con Java, Spring Boot y Oracle 19c.
