package es.libraryclient.menu;

import java.io.PrintStream;

public class Menu {

    // Método estático para mostrar el menú
    public static void mostrarMenu(PrintStream salida) {
        // Imprime las opciones del menú
        salida.println("MENU:");
        salida.println("1. Dar de alta un libro");
        salida.println("2. Dar de baja un libro por ID");
        salida.println("3. Modificar un libro por ID");
        salida.println("4. Obtener un libro por ID");
        salida.println("5. Listar todos los libros");
        salida.println("6. Salir");
        salida.println("Elija una opción:");
    }	
}
