package es.actividad2.unir;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Clase que extiende SpringBootServletInitializer y proporciona configuración adicional
 * para la inicialización de la aplicación Spring Boot cuando se despliega como un archivo WAR.
 * Esta clase se utiliza al empaquetar la aplicación como un archivo de aplicación web (WAR) y
 * se despliega en un contenedor de servlets, como Apache Tomcat.
 *
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Actividad2Application.class);
	}

}
