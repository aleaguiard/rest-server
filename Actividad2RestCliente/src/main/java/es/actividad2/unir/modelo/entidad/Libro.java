package es.actividad2.unir.modelo.entidad;

import java.util.Objects;

import org.springframework.stereotype.Component;

//Javabeans o pojo
/**
 * Clase que representa un libro en el sistema. Cada libro tiene un identificador único,
 * un título, una editorial y una valoración.
 * 
 * <p>La clase implementa los métodos equals y hashCode basados en el identificador y el título
 * del libro para facilitar la comparación y el manejo de colecciones.</p>
 * 
 * <p>Además, proporciona un método toString que devuelve una representación en cadena del libro.</p>
 * 
 * <p>Esta clase está anotada con @Component para ser gestionada por el contenedor de Spring como
 * un componente.</p>
 * 
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
@Component
public class Libro {
	private int id;
	private String titulo;
	private String editorial;
	private int nota;
	
	
	
	
	public Libro() {
		super();
	}
	public Libro(int id, String titulo, String editorial, int nota) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editorial = editorial;
		this.nota = nota;
	}
	
	
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
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", editorial=" + editorial + ", nota=" + nota + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, titulo);
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
		return id == other.id && Objects.equals(titulo, other.titulo);
	}
		
}
