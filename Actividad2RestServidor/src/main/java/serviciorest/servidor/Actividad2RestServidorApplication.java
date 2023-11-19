package serviciorest.servidor;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Clase principal que inicia la aplicación Spring Boot para el servicio REST del servidor.
 * Anotada con @SpringBootApplication, esta clase habilita la autoconfiguración de Spring Boot y
 * escanea automáticamente los componentes y configuraciones en el paquete actual y sus subpaquetes.
 * 
 * <p>Esta clase contiene el método main que arranca la aplicación Spring Boot.</p>
 * 
 * <p>La anotación @SpringBootApplication combina varias anotaciones, incluyendo @Configuration,
 * @EnableAutoConfiguration y @ComponentScan, para simplificar la configuración de la aplicación.</p>
 * 
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
@SpringBootApplication
public class Actividad2RestServidorApplication {
	/**
     * Método principal que arranca la aplicación Spring Boot para el servicio REST del servidor.
     *
     * @param args Argumentos de línea de comandos proporcionados al ejecutar la aplicación.
     */
	public static void main(String[] args) {
		System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
		SpringApplication.run(Actividad2RestServidorApplication.class, args);
		System.out.println("Servicio Rest -> Contexto de Spring cargado!");
	}

}
