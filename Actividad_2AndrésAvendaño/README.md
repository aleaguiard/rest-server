# Proyecto de Servicios REST

## Estructura del Proyecto Postman-Servidor

Se ha creado una aplicación servidor (`serviciosrest`), que interactúa con la aplicación POSTMAN. 
A través de esta última interactuamos con la aplicación servidor introduciendo la URL donde se levanta el servicio (`http://localhost:8080/biblioteca`) y configurando el método HTTP, así como el body con los métodos en los que fuere necesario.

![Estructura](Capturas/00.%20Estructura%20proyectos%20Postman.png){width=50%}


### Aplicación Servidor: `serviciosrest`

#### Paquetes y Clases Principales

1. **Paquete `serviciosrest`:**
   - `ServiciosRestApplication`: Clase principal que inicia la aplicación Spring Boot.

2. **Paquete `serviciosrest.entidad`:**
   - `Libro`: Entidad que representa un libro con atributos como `id`, `titulo`, `editorial`, y `nota`.

3. **Paquete `serviciosrest.persistencia`:**
   - `BibliotecaDao`: Componente que simula una base de datos en memoria con operaciones CRUD para la entidad `Libro`.

4. **Paquete `serviciosrest.servicio`:**
   - `ServicioWeb`: Controlador REST que expone endpoints HTTP para realizar operaciones CRUD en la entidad `Libro`.

### Aplicación Servidor

- Al ejecutar `ServiciosRestApplication`, se inicia una aplicación Spring Boot que pone en marcha el servidor.
- El servidor define un modelo `Libro` y una pseudo base de datos `BibliotecaDao` para almacenar libros.
- `ServicioWeb` expone endpoints REST para interactuar con los libros (agregar, eliminar, actualizar, obtener por ID y listar).

### Para el Servidor

- Ejecutar `ServiciosRestApplication` para iniciar el servidor.
- El servidor estará escuchando en `localhost:8080`.

![App Servidor](Capturas/01.%20Ejecución%20Aplicación%20del%20Servidor.png)

### Para el cliente POSTMAN

- Iniciamos la aplicación e iniciamos una pestaña de `Request`.
- Introducimos la URL (`http://localhost:8080/biblioteca`)
- Configuramos la petición con un método HTTP (`GET`,`DELETE`,`POST` o `PUT`).
- Completamos el body de la petición en los métodos que lo requieren.

### Endpoints del Servidor

Así, configuramos la `request` en POSTMAN adecuando el `verbo` y el `path` al método buscado.
- **Agregar Libro:** POST `/biblioteca`

![Añadir Libro](Capturas/09.%20Añadir%20Libro%20Postman.png)
![Añadir Libro Servidor](Capturas/09.%20Añadir%20Libro%20Postman%20-%20Server.png)

- **Eliminar Libro:** DELETE `/biblioteca/{id}`

![Eliminar Libro](Capturas/10.%20Eliminar%20Libro%20Postman.png)
![Eliminar Libro Servidor](Capturas/10.%20Eliminar%20Libro%20Postman%20-%20Server.png)

- **Actualizar Libro:** PUT `/biblioteca/{id}`

![Actualizar Libro](Capturas/11.%20Actualizar%20Libro%20Postman.png)
![Actualizar Libro Servidor](Capturas/11.%20Actualizar%20Libro%20Postman%20-%20Server.png)

- **Obtener Libro por ID:** GET `/biblioteca/{id}`

![Obtener Libro por ID](Capturas/12.%20Obtener%20Libro%20por%20ID%20Postman.png)
![Obtener Libro por ID Servidor](Capturas/12.%20Obtener%20Libro%20por%20ID%20Postman%20-%20Server.png)

- **Listar Libros:** GET `/biblioteca`

![Listar Libros](Capturas/13.%20Listar%20Libros%20Postman.png)
![Listar Libros Servidor](Capturas/13.%20Listar%20Libros%20Postman%20-%20Server.png)

---

## Estructura del Proyecto Cliente-Servidor

El proyecto se compone de dos aplicaciones principales: una aplicación de servidor (`serviciosrest`) y una aplicación de cliente (`serviciosrestcliente`). Cada una de estas aplicaciones tiene su propia estructura de paquetes y clases.

![Estructura](Capturas/00.%20Estructura%20proyectos.png)

### Aplicación Servidor: `serviciosrest`

#### Paquetes y Clases Principales

1. **Paquete `serviciosrest`:**
   - `ServiciosRestApplication`: Clase principal que inicia la aplicación Spring Boot.

2. **Paquete `serviciosrest.entidad`:**
   - `Libro`: Entidad que representa un libro con atributos como `id`, `titulo`, `editorial`, y `nota`.

3. **Paquete `serviciosrest.persistencia`:**
   - `BibliotecaDao`: Componente que simula una base de datos en memoria con operaciones CRUD para la entidad `Libro`.

4. **Paquete `serviciosrest.servicio`:**
   - `ServicioWeb`: Controlador REST que expone endpoints HTTP para realizar operaciones CRUD en la entidad `Libro`.

### Aplicación Cliente: `serviciosrestcliente`

#### Paquetes y Clases Principales

1. **Paquete `serviciosrestcliente`:**
   - `ServiciosRestClienteApplication`: Clase principal que inicia la aplicación cliente y ofrece un menú interactivo para interactuar con la aplicación servidor.

2. **Paquete `serviciosrestcliente.entidad`:**
   - `Libro`: Entidad similar a la del servidor, utilizada para mapear los datos recibidos.

3. **Paquete `serviciosrestcliente.servicio`:**
   - `ServicioProxyBiblioteca`: Servicio que actúa como un cliente HTTP para comunicarse con la aplicación servidor y realizar operaciones sobre la entidad `Libro`.

## Funcionamiento

### Aplicación Servidor

- Al ejecutar `ServiciosRestApplication`, se inicia una aplicación Spring Boot que pone en marcha el servidor.
- El servidor define un modelo `Libro` y una pseudo base de datos `BibliotecaDao` para almacenar libros.
- `ServicioWeb` expone endpoints REST para interactuar con los libros (agregar, eliminar, actualizar, obtener por ID y listar).

### Aplicación Cliente

- Al ejecutar `ServiciosRestClienteApplication`, se inicia una aplicación Spring Boot con un menú interactivo en la consola.
- El usuario puede realizar acciones como añadir, eliminar, actualizar, obtener y listar libros, que se comunican con el servidor mediante `ServicioProxyBiblioteca`.
- `ServicioProxyBiblioteca` utiliza `RestTemplate` para realizar llamadas HTTP al servidor y gestionar las respuestas.

## Uso

### Para el Servidor

- Ejecutar `ServiciosRestApplication` para iniciar el servidor.
- El servidor estará escuchando en `localhost:8080`.

![App Servidor](Capturas/01.%20Ejecución%20Aplicación%20del%20Servidor.png)

### Para el Cliente

- Ejecutar `ServiciosRestClienteApplication`.
- Usar el menú interactivo en la consola para realizar acciones.

![App Cliente](Capturas/02.%20Ejecución%20Aplicación%20del%20cliente.png)

### Endpoints del Servidor

- **Agregar Libro:** POST `/biblioteca`

![Añadir Libro](Capturas/03.%20Añadir%20Libro.png)
![Añadir Libro Servidor](Capturas/03.%20Añadir%20Libro%20-%20Server.png)

- **Eliminar Libro:** DELETE `/biblioteca/{id}`

![Eliminar Libro](Capturas/04.%20Eliminar%20Libro.png)
![Eliminar Libro Servidor](Capturas/04.%20Eliminar%20Libro%20-%20Server.png)

- **Actualizar Libro:** PUT `/biblioteca/{id}`

![Actualizar Libro](Capturas/05.%20Actualizar%20Libro.png)
![Actualizar Libro Servidor](Capturas/05.%20Actualizar%20Libro%20-%20Server.png)

- **Obtener Libro por ID:** GET `/biblioteca/{id}`

![Obtener Libro por ID](Capturas/06.%20Obtener%20Libro%20por%20ID.png)
![Obtener Libro por ID Servidor](Capturas/06.%20Obtener%20Libro%20por%20ID%20-%20Server.png)

- **Listar Libros:** GET `/biblioteca`

![Listar Libros](Capturas/07.%20Listar%20Libros.png)
![Listar Libros Servidor](Capturas/07.%20Listar%20Libros%20-%20Server.png)

### Salir de la aplicación del cliente

![Salir de la aplicación](Capturas/08.%20Salir.png)

## Conclusión

Este proyecto demuestra un ejemplo de cómo implementar una aplicación cliente-servidor utilizando Spring Boot, donde el servidor gestiona una "biblioteca" de libros y el cliente interactúa con esta biblioteca a través de POSTMAN o una aplicación cliente que consume servicios REST.
