package es.serviciosrest.cliente.servicio;

	import java.util.Arrays;
	import java.util.List;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Service;
	import org.springframework.web.client.HttpClientErrorException;
	import org.springframework.web.client.RestTemplate;

import es.serviciosrest.cliente.entidad.Libro;

	
	/* Con @Service damos de alta un objeto de tipo ServicioProxyLibro 
	 * único dentro del contexto de Spring.
	 */
	@Service
	public class ServicioProxyLibro {
		//URL
		public static final String URL = "http://localhost:8080/libros/";		
		
		 /* Inyectamos el objeto de tipo RestTemplate que nos ayudará a hacer las peticiones 
		 * HTTP al servicio REST.
		 */
		@Autowired
		private RestTemplate restTemplate;		
		/**
		 * Método que devuelve un libro del servicio REST a partir de un id
		 * En caso de que el id no exita arrojaria una expcepción que se captura
		 * para sacar el codigo de respuesta
		 * 
		 * @param id que queremos obtener
		 * @return retorna el libro que estamos buscando, null en caso de que el
		 * libro no se encuentre en el servidor (error 404) o haya habido algún
		 * otro error.
		 */
		public Libro buscarLibro(int id) {
		    try {
		        ResponseEntity<Libro> re = restTemplate.getForEntity(URL + id, Libro.class);
		        HttpStatus hs = re.getStatusCode();
		        if (hs == HttpStatus.OK) {
		            return re.getBody();
		        } else {
		            System.out.println("buscar -> Respuesta no contemplada");
		            return null;
		        }
		    } catch (HttpClientErrorException e) {		    	
		        System.out.println("buscar -> No existe libro con id: " + id);
		        System.out.println("buscar -> Codigo de respuesta: " + e.getStatusCode());
		        return null;
		    }
		}
	
		/**
		 * Método que da de alta un libro en el servicio REST.
		 * 
		 * @param libro, libro que vamos a dar de alta.
		 * @return libro con el id actualizado que se ha dado de alta en el
		 * servicio REST. Null en caso de que no se haya podido dar de alta.
		 */
		public Libro añadirLibro(Libro libro){
			
			try {			
				ResponseEntity<Libro> result = restTemplate.postForEntity(URL, libro, Libro.class);
				System.out.println("alta -> Codigo de respuesta " + result.getStatusCode());
				return result.getBody();
			} catch (HttpClientErrorException e) {
				System.out.println("alta -> No se ha podido dar de alta el libro con id: " + libro);
			    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
			    return null;
			}
		}
		
		/** 
		 * Modifica un libro de la lista en el servicio REST.
		 * 
		 * @param libro, objeto queremos modificar, se hara a partir del id.
		 * @return true en caso de que se haya podido modificar el libro. 
		 * false en caso contrario.
		 */
		public boolean modificar(Libro libro){
			try {				
				restTemplate.put(URL + libro.getId(), libro, Libro.class);
				return true;
			} catch (HttpClientErrorException e) {
				System.out.println("modificar -> No se ha podido modificar el libro con ID: " + libro.getId());
			    System.out.println("modificar -> Codigo de respuesta: " + e.getStatusCode());
			    return false;
			}
		}
		
		/** 
		 * Baja de un libro de la lista en el servicio REST.
		 * 
		 * @param id el id del libro que queremos borrar.
		 * @return true en caso de que se haya podido dar de baja el libro. 
		 * false en caso contrario.
		 */
		public boolean bajaLibro(int id){
			try {				
				restTemplate.delete(URL + id);
				return true;
			} catch (HttpClientErrorException e) {
				System.out.println("borrar -> No se ha podido dar de baja el libro con id: " + id);
			    System.out.println("borrar -> Codigo de respuesta: " + e.getStatusCode());
			    return false;
			}
		}
		
		/**
		 * Metodo que devuelve todos los libros de la lista  
		 * @return el listado de libros o null en caso de algun error con el servicio REST.
		 */
		public List<Libro> listar() {
	        try {
	            ResponseEntity<Libro[]> response =
	                restTemplate.getForEntity(URL, Libro[].class);
	            Libro[] arrayLibros = response.getBody();
	            return Arrays.asList(arrayLibros);
	        } catch (HttpClientErrorException e) {
	            System.out.println("listar -> Error al obtener la lista");
	            System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
	            return null;
	        }
		}
	}


