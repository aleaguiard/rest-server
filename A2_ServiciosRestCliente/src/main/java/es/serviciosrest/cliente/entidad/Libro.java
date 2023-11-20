package es.serviciosrest.cliente.entidad;

import java.util.Objects;

public class Libro {
	
	//Defino los atributos de la clase privados
	private int id,nota;
	private String titulo, editorial;
	
	//Constructor vacío
	public Libro() {
		super();
	}	
	
	//Constructor con todos los parámetros.
	public Libro(int id, String titulo, String editorial, int nota) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editorial = editorial;
		this.nota = nota;
	}

	//Getters y setters
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


	//Método ToString
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", editorial=" + editorial + ", nota=" + nota + "]";
	}

	//Método HashCode
	@Override
	public int hashCode() {
		return Objects.hash(id, titulo);
	}

	//Método equals
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
