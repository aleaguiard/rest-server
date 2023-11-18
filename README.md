# rest-server

Pautas de elaboración

Requerimiento 1

Se pide hacer un servicio REST, dicho servicio gestionará una serie de Libros.

Los libros tendrán un ID, un título, una editorial y una nota. Los libros se encontrarán alojados en el servidor REST. Dicho servidor cuando arranque tendrá 5 libros preestablecidos con todos los datos rellenos. Los libros se guardarán en memoria en cualquier tipo de estructura de datos (como puede ser una lista).

El servicio REST proporcionará un servicio CRUD completo, y podrá ser consumido mediante un cliente como Postman y todo el intercambio de mensajes se hará a través de JSON.

Además, se pide a través de Postman realizar las siguientes tareas y apreciar los resultados:

Dar de alta un libro
Dar de baja un libro por ID
Modificar un libro por ID
Obtener un libro por ID
Listar todos los libros
Es importante entender el funcionamiento del cliente Postman, como puede ser entender las URLs de acceso a los diferentes puntos de acceso de nuestro servicio REST, los verbos y métodos HTTP que tenemos emplear para cada una de las peticiones, así como en que parte del mensaje HTTP viaja la información.

Requerimiento 2

Se pide que no pueda haber dos libros con ID o título repetido

Requerimiento 3

Se pide crear una aplicación java que sea capaz de trabajar con el servidor REST. La aplicación mostrara un menú que sea capaz de realizar todas las operaciones del servidor, como puede ser:

Dar de alta un libro
Dar de baja un libro por ID
Modificar un libro por ID
Obtener un libro por ID
Listar todos los libros
Salir
Para cada opción, se tendrá que pedir los datos necesarios al usuario y/o mostrar los resultados pertinentes. La aplicación se ejecutará hasta que se pulse la opción de “salir”.
