package race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NewMain {

    static boolean primeraPartida = true;
    final static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {

        Carreras.descripcionJuego();
        boolean condicion = true;
        while (condicion) {
            
            String pregunta=primeraPartida?"¿Deseas empezar la partida?":"¿Deseas volver a jugar?";
            System.out.println(pregunta);
            String respuesta = SC.nextLine().trim().toLowerCase();
            int n = Carreras.validarRespuesta(respuesta);
            switch (n) {
                case 1 -> {
                    desarrolloPartida();
                    primeraPartida = false;
                }

                case 2 -> {
                    System.out.println("Gracias por utilizar el programa.");
                    condicion = false;
                }
                case -1 ->
                    System.out.println("Error. Indica sí o no.");
            }
        }

    }

    public static void desarrolloPartida() {
        String nom1 = obtenerDatos();
        String nom2 = obtenerDatos();
        List<String> nombres = asignacionYBienvenida(nom1, nom2);
        Carreras carre = new Carreras(nombres.get(0), nombres.get(1));
        while (!carre.condicionFin()) {
            carre.etapa();
            System.out.println("_______________");
        }
        System.out.println("\n==FIN DE LA PARTIDA==");
        List<String> ganador = carre.getGanador();
        if (ganador.size() == 1) {
            System.out.println("¡Enhorabuena " + ganador.get(0) + " has ganado la carrera!\n");
        } else {
            System.out.println("!Enhorabuena a los dos, ha sido un empate!\n");
        }

    }

    public static String obtenerDatos() {
        while (true) {
            System.out.println("Indica tu nombre.");
            String nombre = SC.nextLine().trim();
            if (nombre.length() < 2 || nombre.length() > 10) {
                System.out.println("Error. El nombre debe tener entre 2 y 10 caracteres.");
            } else if (!Character.isLetter(nombre.charAt(0))) {
                System.out.println("Error. El primer caracter debe ser una letra.");
            } else {
                return nombre;
            }
        }

    }

    public static List<String> asignacionYBienvenida(String nom1, String nom2) {
        List<String> nombres = new ArrayList<>(List.of(nom1, nom2));
        Collections.shuffle(nombres);
        System.out.println("-------------------------------");
        System.out.println("Bienvenido/a " + nombres.get(0) + ", jugarás como la tortuga.");
        System.out.println("Bienvenido/a " + nombres.get(1) + ", jugarás como la liebre.");
        System.out.println("-------------------------------");
        return nombres;

    }

}
