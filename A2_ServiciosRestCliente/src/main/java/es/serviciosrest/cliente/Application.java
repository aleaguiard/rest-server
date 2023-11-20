package es.serviciosrest.cliente;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import es.serviciosrest.cliente.entidad.Libro;
import es.serviciosrest.cliente.servicio.ServicioProxyLibro;

	/*Implementamos la interfaz ComandLineRunner, y sus métodos, que nos permitirá utilizar los atributos de la clase
	 *en el método main, con el método SpringApplication.run.De lo contratrio no podríamos hacerlo directamente en main 
	 *éte un método estático y los atributos de la clase A2ServiciosRestClienteApplication dinámicos.
	 */
	@SpringBootApplication
	public class Application implements CommandLineRunner {
		
		//Inyectamos un objeto de la clase ServicioProxiLibro.	
		@Autowired	
		private ServicioProxyLibro spl;
		
		//Inyectamos un objeto al propio contexto de SpringBoot.
		@Autowired 	
		private ApplicationContext context;		
		
		
		Scanner leer = new Scanner(System.in);		
		
		
		 /* Objeto que será utilizado por ServicioProxi para hacer las peticiones HTTP a nuestro servicio REST.
		 * Dará de alta el objeto que le pasemos en el contexto Spring.
		 * Éste objeto hará las conexiones con A2_ServiciosRest.
		 */	
		
		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}
			
		
		public static void main(String[] args) {
			System.out.println("Cargando el contexto de Spring");
			SpringApplication.run(Application.class, args);
		}
	
		@Override
		public void run(String... args) throws Exception {
			
		//	Elaboración de un menu con 6 opciones.
			
			 Scanner leer = new Scanner(System.in);
			 int opcion;
			 
			 do{		
				 mostrarMenu();
				 System.out.println("Escoja una opción: ");
		         opcion = leer.nextInt();		 
		           
		            switch (opcion) {
		                case 1:
		                    altaLibro();
		                    break;
		                case 2:
		                    bajaLibro();
		                    break;
		                case 3:
		                    modificarLibro();
		                    break;
		                case 4:
		                    buscarLibro();
		                    break;
		                case 5:
		                    listarLibros();
		                    break;
		                case 6:
		                    System.out.println("Salir del menu y cierre de la aplicación");		                   
		                    pararAplicacion();             
		                    break;
		                default:
		                    System.out.println("Opción no válida");
		                    break;
		            }   
			 }while (opcion!=6);
		}
		
		//Método que muestra el menú de la aplicación.
		
		private void mostrarMenu() {
			System.out.println("*******************MENU******************");
	        System.out.println("1. Dar de alta un libro");
	        System.out.println("2. Dar de baja un libro por ID");
	        System.out.println("3. Modificar un libro por ID");
	        System.out.println("4. Buscar un libro por ID");
	        System.out.println("5. Listar todos los libros");
	        System.out.println("6. Salir");
		} 
		
		//Métodos del menu :
		
		private void altaLibro() {	
			//Creamos un objeto de la clase Libro.
	        Libro libro = new Libro();
	        
	        System.out.println("Título del libro: ");	        
			libro.setTitulo(leer.nextLine());
	        System.out.println("Introduzca la editorial del libro: ");
	        libro.setEditorial(leer.nextLine());
	        System.out.println("Ponga una nota al libro: ");
	        libro.setNota(leer.nextInt());
	        Libro libroAlta = spl.añadirLibro(libro);
	        System.out.println("Libro dado de alta en la lista: " + libroAlta);
	    }

	    private void bajaLibro() {	        
	        System.out.println("Introduzca el ID del libro a dar de baja: ");
	        int idBaja = leer.nextInt();
	        boolean eliminar = spl.bajaLibro(idBaja);
	        System.out.println("Libro dado de baja: " + eliminar);
	    }

	    private void modificarLibro() {
	    	
	    	System.out.println("Introduce el ID del libro a modificar: ");
    	    int id = leer.nextInt();
    	    leer.nextLine();
    	    System.out.println("Introduce el nuevo título del libro: ");
    	    String titulo = leer.nextLine();
    	    System.out.println("Introduce la nueva editorial del libro: ");
    	    String editorial = leer.nextLine();
    	    System.out.println("Introduce la nueva nota del libro: ");
    	    int nota = leer.nextInt();

    	    Libro libro = new Libro();
    	    libro.setId(id);
    	    libro.setTitulo(titulo);
    	    libro.setEditorial(editorial);
    	    libro.setNota(nota);
	              
	        boolean modificado = spl.modificar(libro);
	        System.out.println("Libro modificado: " + modificado);
	    }

	    private void buscarLibro() {	        
	        System.out.println("Introduce el ID del libro que desea consultar: ");
	        int idBuscar = leer.nextInt();
	        Libro libro= spl.buscarLibro(idBuscar);
	        System.out.println("Libro con ID " + idBuscar + ": " + libro );
	    }

	    private void listarLibros() {
	       
	    	 List<Libro> lista = spl.listar();
	    	    if (lista != null) {	    	        
	    	        lista.forEach(System.out::println);
	    	    } else {
	    	        System.out.println("Error al obtener la lista");
	    	    }
	    }
	    public void pararAplicacion() {
	        SpringApplication.exit(context, () -> 0);
	    }
	}
		
			
			
	