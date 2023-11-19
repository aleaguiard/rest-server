package serviciorest.servidor.modelo.entidad;

import java.util.Objects;


//Javabeans o pojo

/**
 * Clase que representa la entidad Libro.
 * Esta clase sigue el patrón de diseño JavaBeans o POJO (Plain Old Java Object).
 * 
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
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
