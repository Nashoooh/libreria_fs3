# Microservicio Biblioteca

Este proyecto es un microservicio desarrollado en Spring Boot para la gestión de libros en una biblioteca. Permite realizar operaciones CRUD sobre la entidad Libro y está preparado para ser ejecutado en Docker.

## Características
- CRUD completo para libros (GET, POST, PUT, DELETE)
- Conexión a base de datos MySQL (puede adaptarse a Oracle)
- Arquitectura con patrón Service Layer
- Configuración CORS para frontend en Angular
- Arquetipo Maven propio

## Entidad Libro
- id (Long)
- titulo (String)
- autor (String)
- anioPublicacion (Integer)
- genero (String)

## Endpoints REST
- `GET /api/libros` - Listar todos los libros
- `GET /api/libros/{id}` - Obtener libro por ID
- `POST /api/libros` - Crear libro
- `PUT /api/libros/{id}` - Actualizar libro
- `DELETE /api/libros/{id}` - Eliminar libro

## Patrón de diseño implementado
- **Service Layer Pattern**: La lógica de negocio está en la clase `LibroService`, separada del controlador y el repositorio.

## Requisitos
- Java 21
- Maven
- MySQL (o Oracle)
- Docker

## Configuración de base de datos
Edita `src/main/resources/application.properties` con tus credenciales de MySQL u Oracle.

## Ejecución local
```bash
./mvnw spring-boot:run
```

## Ejecución con Docker
1. Construye la imagen:
   ```bash
   docker build -t libreria-app .
   ```
2. Ejecuta el contenedor:
   ```bash
   docker run -p 8080:8080 --env SPRING_DATASOURCE_URL=jdbc:mysql://<host>:<port>/<db> --env SPRING_DATASOURCE_USERNAME=<user> --env SPRING_DATASOURCE_PASSWORD=<pass> libreria-app
   ```

## Ejemplo de uso con curl
```bash
# Crear libro
curl -X POST http://localhost:8080/api/libros \
     -H "Content-Type: application/json" \
     -d '{"titulo":"Ejemplo","autor":"Autor","anioPublicacion":2024,"genero":"Ficción"}'

# Listar todos los libros
curl -X GET http://localhost:8080/api/libros

# Obtener libro por ID (ejemplo: id=1)
curl -X GET http://localhost:8080/api/libros/1

# Actualizar libro por ID (ejemplo: id=1)
curl -X PUT http://localhost:8080/api/libros/1 \
     -H "Content-Type: application/json" \
     -d '{"titulo":"Nuevo Título","autor":"Nuevo Autor","anioPublicacion":2025,"genero":"No Ficción"}'

# Eliminar libro por ID (ejemplo: id=1)
curl -X DELETE http://localhost:8080/api/libros/1
```

## Autor
Ignacio Andana De Lapeyra
