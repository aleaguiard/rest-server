package serviciorest.servidor.modelo.persistencia;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;

import serviciorest.servidor.modelo.entidad.Libro;

/**
 * Componente de Spring que actúa como un Data Access Object (DAO) para la entidad Libro.
 * Contiene operaciones CRUD básicas para manipular libros en una lista.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
@Component
public class DaoLibro {
	
	public List<Libro> listaLibros;
	public int contador;
	/**
    * Constructor de la clase DaoLibro.
    * Crea la lista de libros e inicializa algunos libros de muestra.
    */
	public DaoLibro() {
		System.out.println("Daolibro -> Creando la lista de libros!");
		listaLibros = new ArrayList<Libro>();
		Libro p1 = new Libro(contador++,"EL SEÑOR DE LOS ANILLOS", "MINOTAURO", 10);//ID: 0
		Libro p2 = new Libro(contador++,"FRAY PERICO Y SU BORRICO","EDICIONES SM", 9);//ID: 1
		Libro p3 = new Libro(contador++,"CEMENTERIO DE ANIMALES","DEBOLSILLO", 10);//ID: 2
		Libro p4 = new Libro(contador++,"DON QUIJOTE DE LA MANCHA", "VICENS-VIVES", 10);//ID:3
		Libro p5 = new Libro(contador++,"EL MONSTRUO DE COLORES", "FLAMBOYANT", 9);//ID:4
		listaLibros.add(p1);
		listaLibros.add(p2);
		listaLibros.add(p3);
		listaLibros.add(p4);
		listaLibros.add(p5);
	}
	
	//1.Dar de alta un libro
	 /**
     * Añade un nuevo libro a la lista.
     *
     * @param l El libro a añadir.
     * @return El libro añadido, o null si ya existe un libro con el mismo título.
     */
	public Libro add(Libro l) {
		//si el nombre del libro no existe que se aquege a la lista
		//compruebo con el metodo existeLibroConTitulo con el titulo del objeto
		if (!existeLibroConTitulo(l.getTitulo())){
			l.setId(contador++);
			listaLibros.add(l);
			return l;
		}
		return null;
		
	}
		
	
	//2.Dar de baja un libro por ID
	/**
     * Elimina un libro de la lista por su ID.
     *
     * @param id El ID del libro a eliminar.
     * @return El libro eliminado, o null si el ID está fuera de rango.
     */
	public Libro delete(int id) {
		try {
			return listaLibros.remove(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> L fuera de rango");
			return null;
		}
	}
	
	//3.Modificar un libro por ID
	/**
     * Modifica un libro de la lista por su ID.
     *
     * @param l El libro con los nuevos datos.
     * @return El libro modificado, o null si el ID está fuera de rango.
     */
	public Libro update(Libro l) {
		try {
			Libro lAux = listaLibros.get(l.getId());
		//	pAux.setId(p.getId());
			lAux.setTitulo(l.getTitulo());
			lAux.setEditorial(l.getEditorial());
			lAux.setNota(l.getNota());

			return lAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Libro fuera de rango");
			return null;
		}
	}
	
	//4.Obtener un libro por ID
	/**
     * Obtiene un libro de la lista por su ID.
     *
     * @param id El ID del libro a obtener.
     * @return El libro encontrado, o null si el ID está fuera de rango.
     */
	public Libro get(int id) {
		try {
			return listaLibros.get(id);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Libro fuera de rango");
			return null;
		}
	}
	
	
	
	//5.Listar todos los libros
	/**
     * Lista todos los libros en la lista.
     *
     * @return La lista de libros.
     */
	public List<Libro> list() {
		return listaLibros;
	}
	
	
	// Método para comprobar si hay un libro con un título específico
	 /**
     * Comprueba si hay un libro con un título específico en la lista.
     *
     * @param tituloBuscado El título del libro a buscar.
     * @return true si hay un libro con el mismo título, false en caso contrario.
     */
    public boolean existeLibroConTitulo(String tituloBuscado) {
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equalsIgnoreCase(tituloBuscado)) {
                // Se encontró un libro con el mismo título
                return true;
            }
        }
        // No se encontró ningún libro con el mismo título
        return false;
    }


}
