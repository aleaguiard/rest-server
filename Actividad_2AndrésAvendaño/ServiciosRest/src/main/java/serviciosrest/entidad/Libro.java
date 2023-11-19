package serviciosrest.entidad;

import java.util.Objects;

/**
 * Entidad que representa un libro en la aplicación.
 * Contiene información como ID, título, editorial y nota.
 */
public class Libro {

	private int id;
	private String titulo, editorial;
	private float nota;
	
	/**
     * Constructor para crear un nuevo libro con todos los detalles.
     * 
     * @param id        Identificador único del libro.
     * @param titulo    Título del libro.
     * @param editorial Editorial del libro.
     * @param nota      Calificación o nota del libro.
     */
	public Libro(int id, String titulo, String editorial, float nota) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editorial = editorial;
		this.nota = nota;
	}

	/**
     * Constructor por defecto para crear un libro vacío.
     */
	public Libro() {
		super();
	}

	// Métodos getters y setters
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getEditorial() {
		return editorial;
	}



	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}



	public float getNota() {
		return nota;
	}



	public void setNota(float nota) {
		this.nota = nota;
	}


	// Métodos hashCode, equals y toString
	@Override
	public int hashCode() {
		return Objects.hash(editorial, id, nota, titulo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(editorial, other.editorial) && id == other.id
				&& Float.floatToIntBits(nota) == Float.floatToIntBits(other.nota)
				&& Objects.equals(titulo, other.titulo);
	}



	@Override
	public String toString() {
		return "Libro " + id + ". " + titulo + ". Ed.:" + editorial + ". Calificación: " + nota;
	}
	
	
}
