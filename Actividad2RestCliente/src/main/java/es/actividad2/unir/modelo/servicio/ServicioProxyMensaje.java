package es.actividad2.unir.modelo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 * Clase que actúa como un cliente proxy para interactuar con un servicio REST que proporciona mensajes.
 * Proporciona un método para obtener un mensaje mediante una petición HTTP GET al servicio REST correspondiente.
 * 
 * <p>Esta clase está anotada con @Service para ser gestionada por el contenedor de Spring como
 * un servicio.</p>
 * 
 * <p>La URL base del servicio REST se define como constante {@link #URL}.</p>
 * 
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
@Service
public class ServicioProxyMensaje {

	//La URL base del servicio REST
	public static final String URL = "http://localhost:8080/";
	
	//Inyectamos el objeto de tipo RestTemplate que nos ayudará
	//a hacer las peticiones HTTP al servicio REST
	@Autowired
	private RestTemplate restTemplate;
	 /**
     * Obtiene un mensaje desde el servicio REST mediante una petición HTTP GET.
     *
     * @param path El path adicional para la URL del servicio REST.
     * @return El mensaje obtenido, o null si la operación falla.
     */
	public String obtener(String path){
		//Con el método getForObject del objeto restTemplate podemos hacer
		//peticiones HTTP a un servicio REST. Tenemos que pasarle la URL
		//Y el tipo que nos va a devolver (String)
		//URL Ej: http://localhost:8080/mensaje
		//Este método usara el verbo GET para hacer la request
		//de manera implicita
		
		String pathFinal = URL + path;
		System.out.println("obtener -> Ruta final: " + pathFinal);
		String mensaje = restTemplate.getForObject(pathFinal, String.class);
		return mensaje;
	}

}
