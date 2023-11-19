package es.libraryclient.serviciorest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.libraryclient.entidad.Libro;

@Service
public class ServicioProxyLibro {

	// URL del servicio REST
	public static final String URL = "http://localhost:8080/library";

	// Inyección de dependencia para RestTemplate
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Obtiene un libro por su ID a través del servicio REST.
	 *
	 * @param id ID del libro a obtener.
	 * @return Objeto Libro si la operación es exitosa, o null si no se encuentra el
	 *         libro.
	 */
	public Libro obtener(int id) {
		try {
			ResponseEntity<Libro> re = restTemplate.getForEntity(URL + "/" + id, Libro.class);
			HttpStatus hs = re.getStatusCode();
			if (hs == HttpStatus.OK) {
				return re.getBody();
			} else {
				System.out.println("Respuesta no contemplada");
				return null;
			}
		} catch (HttpClientErrorException e) {
			System.out.println("El libro con " + id + " NO se ha encontrado");
			System.out.println("Código de respuesta: " + e.getStatusCode());
			return null;
		}
	}

	/**
	 * Da de alta un libro a través del servicio REST.
	 *
	 * @param li Objeto Libro a dar de alta.
	 * @return Objeto Libro dado de alta si la operación es exitosa, o null si hay
	 *         un error.
	 */
	public Libro alta(Libro li) {
		try {
			ResponseEntity<Libro> re = restTemplate.postForEntity(URL, li, Libro.class);
			System.out.println("Libro dado de alta con ID: " + re.getBody().getId() + ", Código de respuesta: "
					+ re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("El libro con ID: " + li.getId() + " NO se ha dado de alta, Código de respuesta: "
					+ e.getStatusCode());
			return null;
		}
	}

	/**
	 * Modifica un libro a través del servicio REST.
	 *
	 * @param li Objeto Libro con la información actualizada.
	 * @return true si la operación es exitosa, false si hay un error.
	 */
	public boolean modificar(Libro li) {
		try {
			restTemplate.put(URL + "/" + li.getId(), li, Libro.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("El libro con ID: " + li.getId() + " NO se ha modificado, Código de respuesta: "
					+ e.getStatusCode());
			return false;
		}
	}

	/**
	 * Borra un libro por su ID a través del servicio REST.
	 *
	 * @param id ID del libro a borrar.
	 * @return true si la operación es exitosa, false si hay un error.
	 */
	public boolean borrar(int id) {
		try {
			restTemplate.delete(URL + "/" + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out
					.println("El libro con ID: " + id + " NO se ha borrado, Código de respuesta: " + e.getStatusCode());
			return false;
		}
	}

	/**
	 * Lista libros filtrados por título a través del servicio REST.
	 *
	 * @param titulo Título por el cual filtrar la lista de libros.
	 * @return Lista de objetos Libro si la operación es exitosa, o null si hay un
	 *         error.
	 */
	public List<Libro> listar(String titulo) {
		String queryParams = "";
		if (titulo != null) {
			queryParams += "?titulo=" + titulo;
		}

		try {
			// Ejemplo de URL resultante: http://localhost:8080/library?titulo=harry
			ResponseEntity<Libro[]> response = restTemplate.getForEntity(URL + queryParams, Libro[].class);
			Libro[] arrayLibros = response.getBody();
			return Arrays.asList(arrayLibros);
		} catch (HttpClientErrorException e) {
			System.out.println("Error al obtener la lista de libros, Código de respuesta: " + e.getStatusCode());
			return null;
		}
	}
}
