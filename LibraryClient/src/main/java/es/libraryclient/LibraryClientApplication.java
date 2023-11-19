package es.libraryclient;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.libraryclient.entidad.Libro;
import es.libraryclient.menu.Menu;
import es.libraryclient.serviciorest.ServicioProxyLibro;

@SpringBootApplication
public class LibraryClientApplication implements CommandLineRunner {

	// Inyección de dependencia para el servicio proxy del libro
	@Autowired
	private ServicioProxyLibro spl;

	// Inyección de dependencia para el contexto de la aplicación
	@Autowired
	private ApplicationContext context;

	// Configuración de RestTemplate como un Bean
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// Scanner para leer la entrada del usuario
	private static Scanner sc;
	static {
		sc = new Scanner(System.in);
	}

	// Método principal para iniciar la aplicación
	public static void main(String[] args) {
		System.out.println("CLIENTE -> Cargando el contexto de Spring");
		SpringApplication.run(LibraryClientApplication.class, args);
	}

	// Implementación del método run de CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		System.out.println("****** Arrancando el CLIENTE REST ******");

		try {
			boolean continuar = true;

			// Bucle principal del programa
			do {
				Menu.mostrarMenu(System.out);
				String opcion = sc.nextLine();

				// Switch para manejar las opciones del menú
				switch (opcion) {
				case "1":
					altaLibro();
					break;
				case "2":
					bajaLibro();
					break;
				case "3":
					modificarLibro();
					break;
				case "4":
					obtenerLibro();
					break;
				case "5":
					listarLibros();
					break;
				case "6":
					continuar = false;
					System.out.println("Saliendo de la aplicación...");
					pararAplicacion();
					break;
				default:
					System.out.println("Opción no válida. Inténtelo de nuevo.");
				}

			} while (continuar);

			System.out.println("******************************************");
			System.out.println("******** Parando el CLIENTE REST *********");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para realizar la operación de dar de alta un libro
	private void altaLibro() {
		System.out.println("Introduce el ID, Título, Editorial y Nota (separados por coma):");
		String[] datosLibro = sc.nextLine().split(",");
		Libro libro = new Libro();
		libro.setId(Integer.parseInt(datosLibro[0].trim()));
		libro.setTitulo(datosLibro[1].trim());
		libro.setEditorial(datosLibro[2].trim());
		libro.setNota(Double.parseDouble(datosLibro[3].trim()));

		// Llamada al servicio REST para dar de alta el libro
		spl.alta(libro);
	}

	// Método para realizar la operación de dar de baja un libro
	private void bajaLibro() {
		System.out.println("Introduce el ID del libro a eliminar:");
		int idLibro = Integer.parseInt(sc.nextLine().trim());

		// Llamada al servicio REST para dar de baja el libro
		boolean borrada = spl.borrar(idLibro);
		System.out.println("Libro con ID " + idLibro + " borrado? " + borrada);
	}

	// Método para realizar la operación de modificar un libro
	private void modificarLibro() {
		System.out.println("Introduce el ID del libro a modificar:");
		int idLibro = Integer.parseInt(sc.nextLine().trim());

		Libro libroModificar = new Libro();
		libroModificar.setId(idLibro);

		System.out.println("Introduce el nuevo Título:");
		libroModificar.setTitulo(sc.nextLine().trim());

		System.out.println("Introduce la nueva Editorial:");
		libroModificar.setEditorial(sc.nextLine().trim());

		System.out.println("Introduce la nueva Nota:");
		libroModificar.setNota(Double.parseDouble(sc.nextLine().trim()));

		// Llamada al servicio REST para modificar el libro
		boolean modificado = spl.modificar(libroModificar);
		System.out.println("Libro con ID " + idLibro + " modificado? " + modificado);
	}

	// Método para realizar la operación de obtener un libro por ID
	private void obtenerLibro() {
		System.out.println("Introduce el ID del libro a obtener:");
		int idLibro = Integer.parseInt(sc.nextLine().trim());

		// Llamada al servicio REST para obtener el libro
		Libro libro = spl.obtener(idLibro);
		System.out.println("Libro con ID " + idLibro + ": " + libro);
	}

	// Método para realizar la operación de listar libros
	private void listarLibros() {
		System.out.println("Introduce el título o vacío para ver todos los libros:");
		String titulo = sc.nextLine().trim();

		List<Libro> listaLibros;

		if (titulo.isEmpty()) {
			// Llamada al servicio REST para listar todos los libros
			listaLibros = spl.listar(null);
		} else {
			// Llamada al servicio REST para listar libros por título
			listaLibros = spl.listar(titulo);
		}

		// Mostrar la lista de libros obtenida
		if (listaLibros != null) {
			if (listaLibros.isEmpty()) {
				System.out.println("No se encontraron libros con el título proporcionado.");
			} else {
				if (titulo.isEmpty()) {
					System.out.println("Lista de todos los libros:");
				} else {
					System.out.println("Lista de libros con el título \"" + titulo + "\":");
				}
				listaLibros.forEach(System.out::println);
			}
		} else {
			System.err.println("Error al obtener la lista de libros.");
		}
	}

	// Método para parar la aplicación
	private void pararAplicacion() {
		SpringApplication.exit(context, () -> 0);
	}
}
