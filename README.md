#Programación de Servicios y Procesos

AE-2. Servicios REST

**ACTIVIDAD ALBERTO ARROYO**

https://github.com/AlbertoArroyoS/Actividad2_Servicios_Rest.git

**Pautas de elaboración**

Requerimiento 1

Se pide hacer un servicio REST, dicho servicio gestionará una serie de Libros.

Los libros tendrán un ID, un título, una editorial y una nota. Los libros se encontrarán alojados en el servidor REST. Dicho servidor cuando arranque tendrá 5 libros preestablecidos con todos los datos rellenos. Los libros se guardarán en memoria en cualquier tipo de estructura de datos (como puede ser una lista).

El servicio REST proporcionará un servicio CRUD completo, y podrá ser consumido mediante un cliente como Postman y todo el intercambio de mensajes se hará a través de JSON.

Además, se pide a través de Postman realizar las siguientes tareas y apreciar los resultados:

Dar de alta un libro Dar de baja un libro por ID Modificar un libro por ID Obtener un libro por ID Listar todos los libros Es importante entender el funcionamiento del cliente Postman, como puede ser entender las URLs de acceso a los diferentes puntos de acceso de nuestro servicio REST, los verbos y métodos HTTP que tenemos emplear para cada una de las peticiones, así como en que parte del mensaje HTTP viaja la información.

Requerimiento 2

Se pide que no pueda haber dos libros con ID o título repetido

Requerimiento 3

Se pide crear una aplicación java que sea capaz de trabajar con el servidor REST. La aplicación mostrara un menú que sea capaz de realizar todas las operaciones del servidor, como puede ser:

Dar de alta un libro Dar de baja un libro por ID Modificar un libro por ID Obtener un libro por ID Listar todos los libros Salir Para cada opción, se tendrá que pedir los datos necesarios al usuario y/o mostrar los resultados pertinentes. La aplicación se ejecutará hasta que se pulse la opción de “salir”.

**CAPTURAS ACTIVIDAD**

Servidor Spring

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.001.png)


Desde Postman

Listo todas las películas

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.002.png)

Pido id 1

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.003.png)

Pido persona 6 que no existe

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.004.png)

Borramos un libro por id

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.005.png)

Ya no aparece el de id 0

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.006.png)

Añado un nuevo libro

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.007.png)

Aparece en la lista

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.008.png)

Modifico el último libro metido

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.009.png)

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.010.png)

Error si metes un libro con el mismo nombre

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.011.png)

Solo se introduce una vez

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.012.png)

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.013.png)

Pruebo comunicación con el ControladorMensaje del servidor

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.014.png)


**Desde el cliente Spring**

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.015.png)

Comprobación de la aplicación cliente:

Menú

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.016.png)

**1. Dar de alta un libro**

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.017.png)

Comprobamos si está en la lista

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.018.png)

Intentamos introducir un libro con el mismo nombre

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.019.png)

Control de excepciones si se introducen caracteres en lugar de un número en la nota y si esta fuera del rango de 0 a 10.

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.020.png)

**2. Dar de baja un libro por id**

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.021.png)

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.022.png)

Dar de baja un id que no existe

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.023.png)

Control de excepciones si se introducen caracteres en lugar del id

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.024.png)

**3. Modificar un libro por id**

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.025.png)

Modificamos el id 4

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.026.png)

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.027.png)

Control de excepciones si se introducen caracteres en lugar del id

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.028.png)

Modificar uno que no existe el id

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.029.png)

**4. Obtener un libro por id**

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.030.png)

Libro con id 1

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.031.png)

Control de excepciones si se introducen caracteres en lugar del id

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.032.png)

**5. Listar todos los libros**

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.033.png)

**6. Salir**

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.034.png)

![Imagen](imgReadme/Aspose.Words.1275d754-a126-4338-9320-32d64a1ff187.035.png)

