package serviciosrest.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import serviciosrest.entidad.Libro;

/**
 * Clase DAO que maneja la lógica de persistencia para los libros.
 * Contiene métodos para agregar, eliminar, actualizar y recuperar libros.
 * Es un componente de Spring que gestiona una colección de libros.
 */
@Component
public class BibliotecaDao {

	public static List<Libro> biblioteca; // Lista que almacena los libros.
	public static int numId; // Contador para asignar ID a los libros.
	
	/**
     * Constructor que inicializa la lista de libros y el identificador.
     * Precarga algunos libros en memoria.
     */
	public BibliotecaDao() {
		biblioteca = new ArrayList<Libro>();
		numId = 1;
		Libro libro1 = new Libro(numId++, "Don Quijote de la Mancha", "Editorial Cervantes", 10.0f);
		Libro libro2 = new Libro(numId++, "Cien Años de Soledad", "Editorial García Márquez", 9.2f);
		Libro libro3 = new Libro(numId++, "La Casa de Bernarda Alba", "Editorial Lorca", 9.7f);
		Libro libro4 = new Libro(numId++, "El Amor en los Tiempos del Cólera", "Editorial García Márquez", 8.9f);
		Libro libro5 = new Libro(numId++, "El Lazarillo de Tormes", "Editorial Anónimo", 8.5f);
		biblioteca.add(libro1);
		biblioteca.add(libro2);
		biblioteca.add(libro3);
		biblioteca.add(libro4);
		biblioteca.add(libro5);
	}
	
	// Métodos para manejar las operaciones CRUD (crear, leer, actualizar y eliminar).
	
	/**
     * Añade un nuevo libro a la biblioteca.
     * Asigna un ID único al libro y lo agrega a la lista.
     *
     * @param libro El libro a añadir.
     * @return El libro añadido o null si ocurre una excepción.
     */
	public Libro add(Libro libro) {
		try {
			libro.setId(numId++); // Asigna un ID único al libro incrementando numId.
			System.out.println(libro);
			biblioteca.add(libro); // Añade el libro a la lista.
			return libro;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
     * Elimina un libro de la biblioteca basándose en su ID.
     *
     * @param id El ID del libro a eliminar.
     * @return true si se eliminó con éxito, false en caso contrario.
     */
	public boolean remove(int id) {
		try {
			Libro libro = biblioteca.get(--id); // Obtiene el libro por su ID.
			biblioteca.remove(libro); // Elimina el libro de la lista.
			System.out.println("Libro eliminado");
			return true; // Retorna true si la eliminación fue exitosa
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
     * Actualiza los detalles de un libro existente.
     * Encuentra el libro por ID y actualiza su título, editorial y nota.
     *
     * @param libro El libro con los detalles actualizados.
     * @return El libro actualizado o null si no se encuentra.
     */
	public Libro update(Libro libro) {
		int index = libro.getId() - 1; // Calcula el índice del libro en la lista. 
		//Restamos 1 porque hemos determinado que el primer ID es el 1 y no 0 como el índice del array. 
		Libro lOriginal = biblioteca.get(index);  // Obtiene el libro original.
		Libro lActualizado = libro;
		// Actualiza los detalles del libro si el introducido no es nulo.
		if (lOriginal != null) {
			lActualizado.setId(lOriginal.getId());
			lOriginal.setTitulo(libro.getTitulo());
			lOriginal.setEditorial(libro.getEditorial());
			lOriginal.setNota(libro.getNota());
			System.out.println(lActualizado);
			return lActualizado;  // Retorna el libro actualizado.
		} 
		System.out.println("Formato de libro incorrecto");
		return null;
	}
	
	/**
     * Recupera un libro de la biblioteca por su ID.
     *
     * @param id El ID del libro a recuperar.
     * @return El libro si se encuentra, o null en caso contrario.
     */
	public Libro getById(int id) {
		try {
			Libro libro = biblioteca.get(--id); // Obtiene y retorna el libro por su ID.
			return libro;
		} catch (Exception e) {
			System.out.println("Libro no encontrado");
			return null;
		}
	}
	
	/**
     * Devuelve la lista completa de libros.
     *
     * @return La lista de libros.
     */
	public List<Libro> list() {
		try {
			return biblioteca;  // Retorna una copia de la lista de libros.
		} catch (Exception e) {
			System.out.println("Biblioteca vacía"); 
			return null;
		}
	}
	
}
