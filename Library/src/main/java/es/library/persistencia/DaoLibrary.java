package es.library.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import es.library.javabean.Libro;

@Component
public class DaoLibrary {

    // Lista que almacena los libros en memoria
    private List<Libro> listaLibros;

    // Constructor de la clase DaoLibrary
    public DaoLibrary() {
        System.out.println("Cargando librería");

        listaLibros = new ArrayList<>();

        // Libros de ejemplo
        Libro libro1 = new Libro(1, "Cien años de soledad", "Sudamericana", 4.7);
        Libro libro2 = new Libro(2, "Don Quijote de la Mancha", "Espasa Calpe", 4.9);
        Libro libro3 = new Libro(3, "Rayuela", "Alianza Editorial", 4.5);
        Libro libro4 = new Libro(4, "Crónica de una muerte anunciada", "Sudamericana", 4.3);
        Libro libro5 = new Libro(5, "El laberinto de la soledad", "Fondo de Cultura Económica", 4.6);

        listaLibros.add(libro1);
        listaLibros.add(libro2);
        listaLibros.add(libro3);
        listaLibros.add(libro4);
        listaLibros.add(libro5);

        System.out.println("LIBRERÍA CARGADA CORRECTAMENTE");
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param id El ID del libro que se busca.
     * @return El libro con el ID proporcionado, o null si no se encuentra.
     */
    public Libro obtenerPorId(int id) {
        try {
            for (Libro libro : listaLibros) {
                if (libro.getId() == id) {
                    return libro;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el libro con ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Obtiene todos los libros almacenados.
     *
     * @return Lista de libros.
     */
    public List<Libro> list() {
        return listaLibros;
    }

    /**
     * Obtiene una lista de libros filtrada por título.
     *
     * @param titulo El título por el cual filtrar la lista de libros.
     * @return Lista de libros con el título proporcionado.
     */
    public List<Libro> listByTitle(String titulo) {
        List<Libro> listaLibroAux = new ArrayList<>();
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                listaLibroAux.add(libro);
            }
        }
        return listaLibroAux;
    }

    /**
     * Agrega un nuevo libro a la lista.
     *
     * @param nuevoLibro El nuevo libro que se va a agregar.
     * @return ResponseEntity que indica el resultado de la operación.
     */
    public ResponseEntity<?> agregarLibro(Libro nuevoLibro) {
        try {
            if (!existeId(nuevoLibro.getId()) && !existeTitulo(nuevoLibro.getTitulo())) {
                listaLibros.add(nuevoLibro);
                return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
            } else {
                return ResponseEntity.badRequest().body("Error: ID o título duplicado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el nuevo libro: " + e.getMessage());
        }
    }

    /**
     * Modifica la información de un libro existente.
     *
     * @param id El ID del libro a modificar.
     * @param libroActualizado Los nuevos datos del libro.
     * @return El libro modificado, o null si no se encuentra el libro con el ID proporcionado.
     */
    public Libro modificarLibro(int id, Libro libroActualizado) {
        Libro existingLibro = obtenerPorId(id);

        if (existingLibro != null) {
            List<Libro> librosConMismoTitulo = listByTitle(libroActualizado.getTitulo());

            if (librosConMismoTitulo.isEmpty() || (librosConMismoTitulo.size() == 1 && librosConMismoTitulo.get(0).getId() == id)) {
                existingLibro.setTitulo(libroActualizado.getTitulo());
                existingLibro.setEditorial(libroActualizado.getEditorial());
                existingLibro.setNota(libroActualizado.getNota());
                return existingLibro;
            } else {
                System.err.println("Error al modificar el libro. El título ya existe para otro libro.");
                return null;
            }
        } else {
            System.err.println("Error al modificar el libro. NO se encontró el ID: " + id);
            return null;
        }
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id El ID del libro que se va a eliminar.
     * @return true si se encuentra y elimina el libro, false si no se encuentra o hay un error.
     */
    public boolean bajaLibro(int id) {
        try {
            for (Libro libro : listaLibros) {
                if (libro.getId() == id) {
                    listaLibros.remove(libro);
                    System.out.println("Libro con ID " + id + " eliminado exitosamente.");
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al dar de baja el libro: " + e.getMessage());
        }
        return false;
    }

    /**
     * Verifica la existencia de un ID en la lista de libros.
     *
     * @param id El ID que se va a verificar.
     * @return true si el ID existe, false si no.
     */
    private boolean existeId(int id) {
        try {
            for (Libro libro : listaLibros) {
                if (libro.getId() == id) {
                    System.err.println("ID existente");
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al verificar la existencia de ID: " + e.getMessage());
        }
        return false;
    }

    /**
     * Verifica la existencia de un título en la lista de libros.
     *
     * @param titulo El título que se va a verificar.
     * @return true si el título existe, false si no.
     */
    private boolean existeTitulo(String titulo) {
        try {
            for (Libro libro : listaLibros) {
                if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                    System.err.println("Título existente");
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al verificar la existencia de título: " + e.getMessage());
        }
        return false;
    }
}
