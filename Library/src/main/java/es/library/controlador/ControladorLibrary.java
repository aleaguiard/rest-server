package es.library.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.library.javabean.Libro;
import es.library.persistencia.DaoLibrary;

@RestController
public class ControladorLibrary {

	// Inyección de dependencia del objeto DaoLibrary
	@Autowired
	private DaoLibrary daoLibrary;

	// Endpoint para obtener un libro por su ID
	@GetMapping(path = "library/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> obtenerPorId(@PathVariable("id") int id) {
		// Imprime un mensaje en la consola
		System.out.println("Buscando libro con ID: " + id);
		// Obtiene el libro por ID a través del DaoLibrary
		Libro li = daoLibrary.obtenerPorId(id);
		if (li != null) {
			// Si se encuentra el libro, devuelve una respuesta con el libro y estado HTTP
			// 200 OK
			return new ResponseEntity<Libro>(li, HttpStatus.OK);
		} else {
			// Si el libro no se encuentra, imprime un mensaje en la consola y devuelve
			// estado HTTP 404 NOT FOUND
			System.err.println("Libro con ID: " + id + " no encontrado");
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint para dar de alta un libro
	@PostMapping(path = "library", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> altaLibro(@RequestBody Libro li) {
		try {
			// Imprime un mensaje en la consola
			System.out.println("Alta: " + li.toString());
			// Intenta agregar el libro a través del DaoLibrary y devuelve la respuesta
			ResponseEntity<?> response = daoLibrary.agregarLibro(li);
			return response;
		} catch (Exception e) {
			// Si ocurre un error, imprime un mensaje en la consola y devuelve un error
			// interno del servidor
			System.err.println("Error al dar de alta " + li.toString());
			return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Endpoint para listar libros, opcionalmente filtrando por título
	@GetMapping(path = "library", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> listarPersonas(@RequestParam(name = "titulo", required = false) String titulo) {
		// Lista de libros que se va a devolver
		List<Libro> listaLibros = null;
		// Si no se proporciona un título, devuelve toda la lista de libros
		if (titulo == null) {
			System.out.println("Listando libros");
			listaLibros = daoLibrary.list();
		} else {
			// Si se proporciona un título, devuelve la lista filtrada por título
			System.out.println("Listando libros por titulo: " + titulo);
			listaLibros = daoLibrary.listByTitle(titulo);
		}
		// Imprime la lista de libros en la consola y devuelve la respuesta con la lista
		// y estado HTTP 200 OK
		System.out.println(listaLibros);
		return new ResponseEntity<List<Libro>>(listaLibros, HttpStatus.OK);
	}

	// Endpoint para modificar un libro por su ID
	@PutMapping(path = "library/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> modificarLibro(@PathVariable("id") int id, @RequestBody Libro li) {
		// Imprime mensajes en la consola
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + li.toString());
		// Actualiza el ID del libro y modifica el libro a través del DaoLibrary
		li.setId(id);
		Libro liUpdate = daoLibrary.modificarLibro(id, li);
		if (liUpdate != null) {
			// Si se actualiza correctamente, devuelve estado HTTP 200 OK
			return new ResponseEntity<Libro>(HttpStatus.OK);
		} else {
			// Si el libro no se encuentra, devuelve estado HTTP 404 NOT FOUND
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint para borrar un libro por su ID
	@DeleteMapping(path = "library/{id}")
	public ResponseEntity<Void> borrarLibro(@PathVariable("id") int id) {
		// Imprime un mensaje en la consola
		System.out.println("ID a borrar: " + id);

		// Intenta dar de baja el libro a través del DaoLibrary y devuelve la respuesta
		if (daoLibrary.bajaLibro(id)) {
			// Si se borra correctamente, devuelve estado HTTP 200 OK
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			// Si el libro no se encuentra, imprime un mensaje en la consola y devuelve
			// estado HTTP 404 NOT FOUND
			System.err.println("Libro con ID: " + id + " no encontrado");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
