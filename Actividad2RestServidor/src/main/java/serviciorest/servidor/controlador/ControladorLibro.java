package serviciorest.servidor.controlador;


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

import serviciorest.servidor.modelo.entidad.Libro;
import serviciorest.servidor.modelo.persistencia.DaoLibro;
/**
 * Controlador que gestiona las operaciones CRUD para la entidad Libro mediante
 * peticiones REST.
 *
 * <p>Este controlador proporciona métodos para dar de alta, dar de baja, modificar,
 * obtener por ID y listar todos los libros.</p>
 *
 * @RestController Indica que esta clase es un controlador REST.
 * @RequestMapping Establece la raíz de la URL para todas las solicitudes mapeadas en este controlador.
 * @Autowired Inyecta una instancia de DaoLibro en el controlador.
 *
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
@RestController
public class ControladorLibro {
	

	@Autowired
	private DaoLibro daoLibro;
	
	
	
	//1.Dar de alta un libro
	//POST 
	//"http://localhost:8080/libros" y el metodo a usar seria POST
	//Pasandole el libro sin el ID dentro del body del HTTP request
	/**
     * Método para dar de alta un libro.
     *
     * @param l El libro que se va a dar de alta.
     * @return ResponseEntity con el libro dado de alta y el código de estado correspondiente.
     *         HttpStatus.OK si la operación es exitosa, HttpStatus.BAD_REQUEST si hay un error.
     */
	@PostMapping(path="libros",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro l) {
		
		Libro libroNuevo = daoLibro.add(l);
		if(libroNuevo != null) {
			System.out.println("altaLibro: objeto libro: " + l);
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			System.out.println("No se ha dado de alta el libro: " + l);
			return new ResponseEntity<Libro>(HttpStatus.BAD_REQUEST);//404 NOT FOUND
		}
	}
	
	//2.Dar de baja un libro por ID
	
	//DELETE
	//Aqui vamos a borar un libro a traves de un ID que le pasemos en el
	//PATH.
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros/ID" y el metodo a usar seria DELETE
	/**
     * Método para dar de baja un libro por ID.
     *
     * @param id El ID del libro que se va a dar de baja.
     * @return ResponseEntity con el libro dado de baja y el código de estado correspondiente.
     *         HttpStatus.OK si la operación es exitosa, HttpStatus.NOT_FOUND si hay un error.
     */
	@DeleteMapping(path="libros/{id}")
	public ResponseEntity<Libro> borrarLibro(@PathVariable("id") int id) {
		System.out.println("ID a borrar: " + id);
		Libro l = daoLibro.delete(id);
		if(l != null) {
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//3. Modificar un libro por ID
	
	//PUT
	//Si todo ha ido bien devolvemos el codigo de respuesta de 200 OK,
	//si id de el libro no existe devolvemos 404 NOT FOUND
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros/ID" y el metodo a usar seria PUT
	//Pasandole el libro sin el ID dentro del body del HTTP request
	/**
     * Método para modificar un libro por ID.
     *
     * @param id El ID del libro que se va a modificar.
     * @param l El libro con los datos actualizados.
     * @return ResponseEntity con el código de estado correspondiente.
     *         HttpStatus.OK si la operación es exitosa, HttpStatus.NOT_FOUND si hay un error.
     */
	@PutMapping(path="libros/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> modificarLibro(
			@PathVariable("id") int id, 
			@RequestBody Libro l) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + l);
		l.setId(id);
		Libro pUpdate = daoLibro.update(l);
		if(pUpdate != null) {
			return new ResponseEntity<Libro>(HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//4. Obtener un libro por ID

	//GET libro POR ID
	//el codigo 200 "OK" si existe o 404 NOT FOUND si no existe
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros/ID" y el metodo a usar seria GET
	//ID sería el identificador que queremos buscar
	/**
     * Método para obtener un libro por ID.
     *
     * @param id El ID del libro que se va a obtener.
     * @return ResponseEntity con el libro obtenido y el código de estado correspondiente.
     *         HttpStatus.OK si la operación es exitosa, HttpStatus.NOT_FOUND si hay un error.
     */
	@GetMapping(path="libros/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Libro> getlibro(@PathVariable("id") int id) {
		System.out.println("Buscando libro con id: " + id);
		Libro l = daoLibro.get(id);
		if(l != null) {
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//5. Listar todos los libros
	
	//GET LISTA libros
	
	//La URL para acceder a este metodo en caso de querer todas las libros
	//sería: 
	//"http://localhost:8080/libros" y el metodo a usar seria GET
	 /**
     * Método para listar todos los libros o filtrar por nombre.
     *
     * @param nombre El nombre para filtrar la lista de libros (opcional).
     * @return ResponseEntity con la lista de libros y el código de estado correspondiente.
     *         HttpStatus.OK si la operación es exitosa, HttpStatus.NOT_FOUND si hay un error.
     */
	@GetMapping(path="libros",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> listaLibros(
			@RequestParam(name="nombre",required=false) String nombre) {
		List<Libro> listaLibros = null;
		//Si no me viene el nombre, devolvemos toda la lista
		if(nombre == null) {
			System.out.println("Listando los libros");
			listaLibros = daoLibro.list();			
		}
		System.out.println(listaLibros);
		return new ResponseEntity<List<Libro>>(listaLibros,HttpStatus.OK);
	}
	

}
