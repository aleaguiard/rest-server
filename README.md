# REST Server

Este proyecto implementa un servicio REST en Java para la gestión de libros, proporcionando un CRUD completo. El servidor REST se inicia con 5 libros predefinidos y permite las siguientes operaciones:

- Crear un libro.
- Eliminar un libro por ID.
- Modificar un libro por ID.
- Obtener un libro por ID.
- Listar todos los libros.

El intercambio de datos se realiza en formato JSON y es consumible mediante clientes como Postman. Adicionalmente, se ha desarrollado una aplicación Java que interactúa con el servidor REST a través de un menú, permitiendo realizar todas las operaciones mencionadas anteriormente.

## Requerimientos

- Operaciones CRUD: Los libros tienen un ID, un título, una editorial y una nota.
- Validación: No se permiten IDs o títulos duplicados.
- Cliente Java: Aplicación con menú para interactuar con el servidor.

## Tecnologías

- Java
- Spring Boot
- JSON

## Uso

### Servidor REST

1. Clona el repositorio.
2. Navega al directorio del proyecto.
3. Ejecuta el servidor con `mvn spring-boot:run`.

### Cliente Java

Compila y ejecuta la aplicación cliente desde el directorio A2_ServiciosRestCliente.
