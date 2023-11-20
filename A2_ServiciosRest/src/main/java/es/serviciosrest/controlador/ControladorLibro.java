package es.serviciosrest.controlador;

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

import ch.qos.logback.core.net.SyslogOutputStream;
import es.serviciosrest.modelo.entidad.Libro;
import es.serviciosrest.modelo.negocio.DaoLibro;

	// Damos de alta el objeto para que reciba peticiones de los usuarios HTTP.
	@RestController
	public class ControladorLibro {
		
		//Inyectamos un solo objeto DaoLibro, el que dimos de alta antes con @Component.
		@Autowired
		private DaoLibro daoLibro;	
		
		
		//Dar de alta un libro en la lista,usamos la anotación @PostMapping.
		//La URL será: http://localhost:8080/libros
		
		@PostMapping(path="libros",consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
		
		public ResponseEntity<Libro>altaLibro(@RequestBody Libro libro) {
			
			System.out.println("Alta libro :  " + libro);			
			
			//Si el título del libro que queremos añadir ya está en la lista nos devolverá el error 409.
			if(daoLibro.existeTitulo(libro.getTitulo())) {
				return new ResponseEntity<Libro>(libro,HttpStatus.CONFLICT);//409 CONFLICT
			}
			
			//Si no existe el título, entonces se añade el objeto libro a la lista.
			daoLibro.añadirLibro(libro);
			return new ResponseEntity<Libro>(libro,HttpStatus.CREATED);//201 CREATED
		}
		
		//Buscamos un libro por ID para darlo de baja de la lista.Usamos la anotación @DeleteMapping.
		//Las busquedas por clave primaria van en el PATH de la URL.
		//La URL será: http://localhost:8080/libros/id
		
		@DeleteMapping(path="libros/{id}")
		public ResponseEntity<Libro>bajaLibro(@PathVariable("id") int id) {
			System.out.println("Libro a dar de baja en la lista: " + id);
			Libro libro = daoLibro.bajaLibro(id);
			if(libro != null) {
				return new ResponseEntity<Libro>(libro,HttpStatus.OK);//200 OK
			}else {
				return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
			}
		}
		
		//Modificar un libro del listado. Usamos la anotación @PutMapping
		//"http://localhost:8080/libros/ID" 		
		@PutMapping(path="libros/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
		
		public ResponseEntity<Libro>modificarLibro(@PathVariable("id") int id,@RequestBody Libro libro) {	
			
	        // Validar que no haya otro libro con el mismo título
	        if (daoLibro.existeTitulo(libro.getTitulo())) {
	            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 CONFLICT
	        }
	        
	        //Si no existe el título entonces modifico el libro	        
	        libro.setId(id);
	        Libro lUpdate = daoLibro.modificarLibro(libro);
	        if (lUpdate != null) {
	            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
	        }
	    }
		
		
		//Usamos el metodo GET para devolver un libro por su identificador.
		//Las busquedas por clave primaria van en el PATH de la URL
		//URL: http://localhost:8080/libros/id
		@GetMapping(path="libros/{id}",produces = MediaType.APPLICATION_JSON_VALUE)			
		
		public ResponseEntity<Libro>getLibro(@PathVariable("id") int id) {				
			try {
		        Libro libro = daoLibro.get(id);
		        if (libro != null) {
		            return new ResponseEntity<>(libro, HttpStatus.OK); // 200 OK
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 INTERNAL SERVER ERROR
		    }
		}

	
		//Listar todos los libros 
		//URL: http://localhost:8080/libros
		@GetMapping(path = "libros", produces = MediaType.APPLICATION_JSON_VALUE)
		
		public ResponseEntity<List<Libro>> listarLibros() {			
		    System.out.println("Listando todos los libros");		    
		    List<Libro> lista = daoLibro.list();
		    System.out.println(lista);		    
		    return new ResponseEntity<>(lista, HttpStatus.OK);
		}
	}
