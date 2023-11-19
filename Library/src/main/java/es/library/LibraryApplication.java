package es.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		System.out.println("SERVER REST -> Cargando el contexto de Spring...");

		SpringApplication.run(LibraryApplication.class, args);

		System.out.println("SERVER REST -> Contexto de Spring cargado!");
	}

}
