package serviciorest.servidor.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controlador para gestionar mensajes.
 * Este controlador proporciona un endpoint para obtener un mensaje específico.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 */
@RestController
public class ControladorMensaje {
	
	
	@GetMapping(value = "mensaje")
	public String obtenerMensaje() {
		return "Comunicacion con el servidor de Alberto ";
	}

}
