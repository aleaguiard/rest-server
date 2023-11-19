package serviciosrest.servicio;

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
import org.springframework.web.bind.annotation.RestController;

import serviciosrest.entidad.Libro;
import serviciosrest.persistencia.BibliotecaDao;

/**
 * Clase controladora REST que crea y maneja endpoints HTTP para operaciones relacionadas con libros.
 */
@RestController
public class ServicioWeb {

	@Autowired
	private BibliotecaDao bibliotecaDao; // Inyección del componente BibliotecaDao.
	
	// Métodos que mapean las operaciones CRUD a endpoints REST.
	
	/**
     * Maneja las solicitudes POST para agregar un nuevo libro.
     * Verifica si el libro ya existe antes de añadirlo.
     *
     * @param l El libro a añadir.
     * @return ResponseEntity con el libro añadido o un error.
     */
	@PostMapping(path = "biblioteca", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> addLibro(@RequestBody Libro l) {
		System.out.println("Dando alta libro...");
		List<Libro> biblioteca = bibliotecaDao.list();
		// Verifica si el libro es nulo.
		if(l != null) {
			boolean tituloExiste = false;
			// Comprueba si el título del libro ya existe.
			for (Libro i : biblioteca) {
				if (i.getTitulo().equals(l.getTitulo())) {
					tituloExiste = true;
					break;
				}
			}
			if (!tituloExiste) {
				bibliotecaDao.add(l);
				System.out.println("Libro añadido");
				return new ResponseEntity<Libro>(l, HttpStatus.CREATED);
			}else {
				System.out.println("El título no puede coincidir con uno ya existente");
				return new ResponseEntity<Libro>(HttpStatus.BAD_REQUEST);
			}
			
		}else {
			System.out.println("El libro no puede estar vacío");
			return new ResponseEntity<Libro>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
     * Maneja las solicitudes DELETE para eliminar un libro por su ID.
     * Verifica si el libro existe antes de intentar eliminarlo.
     *
     * @param id El ID del libro a eliminar.
     * @return ResponseEntity con el libro eliminado o un error.
     */
	@DeleteMapping(path = "biblioteca/{id}")
	public ResponseEntity<Libro> removeLibro(@PathVariable("id") int id) {
		Libro l = bibliotecaDao.getById(id);
		System.out.println("Dando de baja un libro: " + l);
		 // Intenta obtener el libro con el ID proporcionado.
		if(l != null) {
			bibliotecaDao.remove(id);
			return new ResponseEntity<Libro>(l, HttpStatus.OK);
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
     * Maneja las solicitudes PUT para actualizar un libro existente.
     * Verifica si el libro existe y si el nuevo título no está en uso.
     *
     * @param id El ID del libro a actualizar.
     * @param l El libro con los detalles actualizados.
     * @return ResponseEntity con el libro actualizado o un error.
     */
	@PutMapping(path = "biblioteca/{id}")
	public ResponseEntity<Libro> updateLibro(@PathVariable("id") int id, @RequestBody Libro l) {
		Libro lOriginal = bibliotecaDao.getById(id);
		System.out.println("Actualizando un libro: " + lOriginal);
		List<Libro> biblioteca = bibliotecaDao.list();
		 // Verifica si el libro proporcionado es nulo o no existe.
		if(l != null) {
			boolean tituloExiste = false;
			// Comprueba si el título del libro ya existe en otro libro distinto del que se pretende actualizar.
			for (Libro i : biblioteca) {
				if (i.getTitulo().equals(l.getTitulo()) && i.getId() != l.getId()) {
					tituloExiste = true;
					break;
				}
			}
			if (!tituloExiste) {
				bibliotecaDao.update(l);
				System.out.println("Libro actualizado");
				return new ResponseEntity<Libro>(l, HttpStatus.CREATED);
			}else {
				System.out.println("El título no puede coincidir con uno ya existente");
				return new ResponseEntity<Libro>(HttpStatus.BAD_REQUEST);
			}
			
		}else {
			System.out.println("El libro no se ha encontrado");
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
     * Maneja las solicitudes GET para obtener un libro por su ID.
     *
     * @param id El ID del libro a obtener.
     * @return ResponseEntity con el libro encontrado o un error.
     */
	@GetMapping(path = "biblioteca/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> getLibro(@PathVariable("id") int id) {
		System.out.println("Buscando libro por id: " + id);
		Libro l = bibliotecaDao.getById(id);
		if(l != null) {
			System.out.println(l);
			return new ResponseEntity<Libro>(l, HttpStatus.FOUND);
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
     * Maneja las solicitudes GET para listar todos los libros en la biblioteca.
     *
     * @return ResponseEntity con la lista de libros o un error.
     */
	@GetMapping(path = "biblioteca", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> listBiblioteca() {
		System.out.println("Lista de libros de la biblioteca");
		List<Libro> list = bibliotecaDao.list();
		return new ResponseEntity<List<Libro>>(list, HttpStatus.OK);
	}
	

}
