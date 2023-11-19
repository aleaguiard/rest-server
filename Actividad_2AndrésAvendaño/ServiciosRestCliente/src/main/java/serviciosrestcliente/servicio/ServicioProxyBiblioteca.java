package serviciosrestcliente.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import serviciosrestcliente.entidad.Libro;


/**
 * Servicio proxy que se comunica con el servicio REST del servidor.
 * Proporciona métodos para realizar operaciones CRUD sobre libros utilizando solicitudes HTTP.
 */
@Service
public class ServicioProxyBiblioteca {
	public static final String URL = "http://localhost:8080/biblioteca/";

	@Autowired
	private RestTemplate restTemplate; // Inyección de RestTemplate para realizar llamadas HTTP.
	
	 /**
     * Añade un nuevo libro mediante una solicitud POST.
     *
     * @param l El libro a añadir.
     * @return El libro añadido o null en caso de error.
     */
	public Libro add (Libro l) {
		try {
			// Realiza una solicitud POST al servicio REST con el libro.
			ResponseEntity<Libro> re = restTemplate.postForEntity(URL , l, Libro.class);
			HttpStatus hs = re.getStatusCode();
			// Verifica si el estado HTTP es CREATED.
			if (hs == HttpStatus.CREATED) {
				// Retorna el cuerpo de la respuesta si la creación fue exitosa.
				return re.getBody();
			}else {
				// Retorna null si el estado no es CREATED.
				return null;
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println("La persona no se ha dado de alta.");
			System.out.println(e.getStatusCode());
			return null;
		}
	}
	
	/**
     * Elimina un libro mediante una solicitud DELETE.
     *
     * @param id El ID del libro a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
	public boolean remove (int id) {
		try {
			// Realiza una solicitud DELETE al servicio REST para el libro especificado por ID.
			restTemplate.delete(URL + id);
			// Retorna true si la eliminación fue exitosa.
			return true;
			
		} catch (HttpClientErrorException e) {
			System.out.println("La persona no se ha encontrado.");
			System.out.println(e.getStatusCode());
			return false;
		}
	}
	
	 /**
     * Actualiza un libro existente mediante una solicitud PUT.
     *
     * @param l El libro con la información actualizada.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
	public boolean update (Libro l) {
		try {
			// Realiza una solicitud PUT al servicio REST con el libro actualizado.
			restTemplate.put(URL + l.getId(), l, Libro.class);
			// Retorna true si la actualización fue exitosa.
			return true;
			
		} catch (HttpClientErrorException e) {
			System.out.println("La persona no se ha dado de alta.");
			System.out.println(e.getStatusCode());
			return false;
		}
	}
	
	/**
     * Obtiene un libro mediante una solicitud GET.
     *
     * @param id El ID del libro a obtener.
     * @return El libro encontrado o null en caso de error.
     */
	public Libro get (int id) {
		try {
			// Realiza una solicitud GET al servicio REST para el libro especificado por ID.
			ResponseEntity<Libro> re = restTemplate.getForEntity(URL + id, Libro.class);
			HttpStatus hs = re.getStatusCode();
			// Verifica si el estado HTTP es FOUND.
			if (hs == HttpStatus.FOUND) {
				// Retorna el cuerpo de la respuesta si se encontró el libro.
				return re.getBody();
			}else {
				return null;
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println("La persona no se ha encontrado.");
			System.out.println(e.getStatusCode());
			return null;
		}
	}
	
	/**
     * Lista todos los libros mediante una solicitud GET.
     *
     * @return Lista de libros o null en caso de error.
     */
	public List<Libro> list () {
		try {
			// Realiza una solicitud GET al servicio REST para obtener todos los libros.
			ResponseEntity<Libro[]> re = restTemplate.getForEntity(URL, Libro[].class);	
			Libro[] arrayLibros = re.getBody();
			// Retorna la lista de libros.
			return Arrays.asList(arrayLibros);
			
		} catch (HttpClientErrorException e) {
			System.out.println("La persona no se ha encontrado.");
			System.out.println(e.getStatusCode());
			return null;
		}
	}
}
