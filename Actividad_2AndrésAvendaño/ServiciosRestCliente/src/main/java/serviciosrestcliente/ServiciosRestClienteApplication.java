package serviciosrestcliente;

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

import serviciosrestcliente.entidad.Libro;
import serviciosrestcliente.servicio.ServicioProxyBiblioteca;

/**
 * Aplicación cliente Spring Boot para interactuar con el servicio REST.
 */
@SpringBootApplication
public class ServiciosRestClienteApplication implements CommandLineRunner {
	
	@Autowired
	private ServicioProxyBiblioteca spb;
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiciosRestClienteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 Scanner leer = new Scanner(System.in);
	        boolean salir = false;
	        int opcion;

	        while (!salir) {
	        	System.out.println("******************************");
	            System.out.println("Menú de la Biblioteca:");
	            System.out.println("1. Dar de alta un libro");
	            System.out.println("2. Dar de baja un libro por ID");
	            System.out.println("3. Modificar un libro por ID");
	            System.out.println("4. Obtener un libro por ID");
	            System.out.println("5. Listar todos los libros");
	            System.out.println("6. Salir");
	            System.out.println("Elige una opción: ");

	            opcion = leer.nextInt();
	            leer.nextLine();

	            switch (opcion) {
	                case 1: {
	                	Libro l = new Libro();
	                    System.out.println("Título:");
	                    String titulo = leer.nextLine();
	                    l.setTitulo(titulo);
	    	            System.out.println("Editorial:");
	    	            String editorial = leer.nextLine();
	                    l.setEditorial(editorial);
	    	            System.out.println("Nota:");
	    	            Float nota = leer.nextFloat();
	                    l.setNota(nota);
	                    System.out.println("Libro añadido :" + spb.add(l));
	                    break;
	                }
	                case 2: {
	                	System.out.println("ID del libro:");
	                    int id = leer.nextInt();
	                    Libro lEliminado = spb.get(id);
	                    spb.remove(id);
	                    System.out.println("Eliminado libro :" + lEliminado);
	                    break;
	                }    
	                case 3: {
	                	Libro l = new Libro();
	                	System.out.println("ID del libro original:");
	                    int id = leer.nextInt();
	                    leer.nextLine();
	                    l.setId(id);
	                    Libro lOriginal = spb.get(id);
	                    System.out.println("Vas a actualizar: " + lOriginal.getId());
	                    System.out.println("Título:");
	                    String titulo = leer.nextLine();
	                    l.setTitulo(titulo);
	    	            System.out.println("Editorial:");
	    	            String editorial = leer.nextLine();
	                    l.setEditorial(editorial);
	    	            System.out.println("Nota:");
	    	            Float nota = leer.nextFloat();
	                    l.setNota(nota);
	                    spb.update(l);
	                    System.out.println("Actualizado el libro con ID: " + id 
	                    					+ "\nOriginal: " + lOriginal
	                    					+ "\nActualizado: " + l);
	                    break;
	                }
	                case 4: {
	                	System.out.println("ID del libro:");
	                    int id = leer.nextInt();
	                    Libro lObtenido = spb.get(id);
	                    System.out.println(lObtenido);
	                    // Lógica para obtener un libro por ID
	                    break;
	                }    
	                case 5: {
	                    List<Libro> lista = spb.list();
	                    for (Libro libro : lista) {
							System.out.println(libro);
						}
	                    break;
	                }    
	                case 6:
	                    salir = true;
	                    System.out.println("Saliendo");
	                    pararAplicacion();
	                    break;
	                default:
	                    System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 6.");
	            }
	        }

	        leer.close();
	        System.out.println("Fin de programa");
	    }
	public void pararAplicacion () {
		SpringApplication.exit(context, () -> 0);
	}
	
}
