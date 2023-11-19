package es.libraryclient.entidad;

//Definición de la clase Libro en el paquete es.library.javabean
public class Libro {

	// Atributos de la clase Libro
	private int id;
	private String titulo;
	private String editorial;
	private double nota;

	// Constructor sin parámetros
	public Libro() {
		super();
	}

	// Constructor con parámetros
	public Libro(int id, String titulo, String editorial, double nota) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editorial = editorial;
		this.nota = nota;
	}

	// Métodos de acceso (getters y setters) para los atributos de la clase

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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	// Método toString
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", editorial=" + editorial + ", nota=" + nota + "]";
	}

}