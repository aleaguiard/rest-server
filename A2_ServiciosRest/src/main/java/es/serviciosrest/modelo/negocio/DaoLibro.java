package es.serviciosrest.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.serviciosrest.modelo.entidad.Libro;

@Component
public class DaoLibro {	

		//Atributos de la clase. El atributo contador nos servirá para asignar un ID de forma aleatoria
		//con esto evitaremos tenerdos libros con el mismo ID.
		public List<Libro> lista;
		public int contador;
		
		//Creamos una Lista de libros y los cargamos.
		public DaoLibro() {
			System.out.println("Cargando la lista con los libros");
			lista = new ArrayList<Libro>();
			Libro l1 = new Libro(contador++,"El Principito", "Salamandra", 9);
			Libro l2 = new Libro(contador++,"Elmer", " Beascoa", 8);
			Libro l3 = new Libro(contador++,"Matilda", "Alfaguara", 7);
			Libro l4 = new Libro(contador++,"El Pollo Pepe", "SM", 6);
			Libro l5 = new Libro(contador++,"Las aventuras del Capitán Calzoncillos","SM", 5);
			lista.add(l1);
			lista.add(l2);
			lista.add(l3);
			lista.add(l4);
			lista.add(l5);
		}
		
		//Añadir un libro nuevo a la lista.Método que recibe como parámetro un objeto libro y lo añadirá a
		//la lista siempre que no exista ya en la lista un objeto libro con el mismo título.		
				public void añadirLibro(Libro libro) {
				
					if(!lista.contains(libro) && !existeTitulo(libro.getTitulo())) {
						libro.setId(contador++);
						lista.add(libro);
				}
				}	
		

		//Dar de baja un libro de la lista.Método que recibe por parámetro la posición que ocupa un objeto libro 
		//y si existe da de baja el libro, de lo contratio salta una excepción.
		public Libro bajaLibro(int id) {
			try {
				return lista.remove(id);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("baja -> Libro fuera de rango");
				return null;
			}
		}
		
		//Modificar los atributos de un libro de la lista 
			public Libro modificarLibro(Libro libro) {				
				try {
					Libro aux = lista.get(libro.getId());
					aux.setTitulo(libro.getTitulo());
					aux.setEditorial(libro.getEditorial());
					aux.setNota(libro.getNota());
					return aux;
					
				} catch (IndexOutOfBoundsException iobe) {
					System.out.println("modificar -> Libro fuera de rango");
					return null;
				}				
							
			}
		
		//Buscar un libro pasando por parámetro su atributo id. Si existe devolverá el libro y sino 
		//dará una excepción con un mensaje.
		public Libro get(int id) {
			try {
				return lista.get(id);
			} catch (IndexOutOfBoundsException iobe) {
				System.out.println("Libro fuera de rango");
				return null;
			}
		}		
		
		//Listar todos los libros
		public List<Libro> list() {
			return lista;
		}
				
				
		//Métodos adicionales 
		
		//Método para comprobor si existen en la lista dos libros con el mismo titulo
		public boolean existeTitulo(String titulo) {
			for(Libro libro:lista) {
				if(libro.getTitulo().equalsIgnoreCase(titulo)) {
			return true;
		}
	}
		return false;
}
}







