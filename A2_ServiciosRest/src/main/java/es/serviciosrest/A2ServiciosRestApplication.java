package es.serviciosrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class A2ServiciosRestApplication {

	public static void main(String[] args) {
		System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
		SpringApplication.run(A2ServiciosRestApplication.class, args);
		System.out.println("Servicio Rest -> Contexto de Spring cargado!");
	}

}
