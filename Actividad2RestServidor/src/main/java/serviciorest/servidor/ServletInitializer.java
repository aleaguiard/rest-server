package serviciorest.servidor;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Clase que extiende SpringBootServletInitializer para permitir la inicialización de la aplicación
 * como un servlet en un contenedor web externo, como Tomcat.
 * 
 * <p>Esta clase proporciona un método configure que especifica la clase principal de la aplicación
 * Spring Boot ({@link Actividad2RestServidorApplication}) para ser utilizada al configurar el servlet.</p>
 * 
 * <p>La existencia de esta clase es necesaria para la implementación de la interfaz
 * {@link org.springframework.boot.web.servlet.support.SpringBootServletInitializer}, permitiendo
 * la inicialización de la aplicación como un servlet.</p>
 * 
 * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer
 * @see Actividad2RestServidorApplication
 * 
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Actividad2RestServidorApplication.class);
	}

}
