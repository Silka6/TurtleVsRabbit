package race;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Carreras {

    private int posicionTortuga;
    private int posicionLiebre;
    private final String tortuga;
    private final String liebre;
    private static final int MAX_CASILLAS = 50;
    private final Random r = new Random();
    private final List<String> ganador = new ArrayList<>();

    public Carreras(String nom1, String nom2) {
        tortuga = nom1;
        liebre = nom2;
        posicionTortuga = 0;
        posicionLiebre = 0;
    }

    public static void descripcionJuego() {
        System.out.println("""
                           La carrera se desarrollará en varias etapas dentro de un circuito de 50 casillas.
                           Ambos animales comenzarán en la casilla de salida, que sería la casilla 0.
                           Cada etapa se dividirá en turnos, uno corresponderá al movimiento de la tortuga y
                           otro al de la liebre, en función de las siguientes reglas:
                           
                           •	Movimiento de la Tortuga:
                           	-En cada etapa podrá avanzar entre 1 y 3 casillas, de forma aleatoria.
                           	-No podrá retroceder casillas.
                           
                           •	Movimiento de la Liebre:
                           	Tendrá diferentes comportamientos que se determinarán de forma aleatoria.
                           	Se lanzará un dado del 0 al 9.
                           	-El cero representará que la liebre se ha dormido y por tanto no avanza.
                           	-Si el resultado está entre 1 y 3, la liebre retrocede tantas casillas como el valor
                                    obtenido en el dado. Nunca se podrá salir del circuito por lo que nunca se podrá retroceder
                                    de la casilla de salida.
                           	-Si el resultado está entre 4 y 9, la liebre avanzará tantas casillas como el valor obtenido
                                    en el dado.
                           
                           •	La etapa se considerará finalizada cuando ambos animales hayan finalizado su turno.
                           •	El primer animal que llegue a la meta (llegar o superar la casilla 50) será declarado ganador.
                           
                           """);
    }

    public static int validarRespuesta(String resp) {
        if (resp.equalsIgnoreCase("sí") || resp.equalsIgnoreCase("si")) {
            return 1;
        } else if (resp.equalsIgnoreCase("no")) {
            return 2;
        } else {
            return -1;
        }
    }

    public void etapa() {
        System.out.println("La tortuga se encuentra en la posición " + turnoTortuga() + "\n");
        System.out.println("La liebre se encuentra en la posición " + turnoLiebre() + "\n");
        if (posicionTortuga > posicionLiebre) {
            System.out.println("¡La tortuga va en cabeza!");
        } else if (posicionLiebre > posicionTortuga) {
            System.out.println("¡La liebre va en cabeza!");
        } else {
            System.out.println("¡Están empatados!");
        }
    }

    public int turnoTortuga() {
        int dadoT = r.nextInt(3) + 1;
        System.out.println("Dado de la tortuga: " + dadoT);
        posicionTortuga += dadoT;
        return posicionTortuga;

    }

    public int turnoLiebre() {
        int dadoL = r.nextInt(10);
        System.out.println("Dado de la liebre: " + dadoL);
        if (dadoL >= 1 && dadoL <= 3) {
            posicionLiebre -= dadoL;
            if (posicionLiebre < 0) {
                posicionLiebre = 0;
            }
        } else if (dadoL >= 4 && dadoL <= 9) {
            posicionLiebre += dadoL;
        }

        return posicionLiebre;

    }

    public boolean condicionFin() {
        if (posicionTortuga >= MAX_CASILLAS && posicionLiebre >= MAX_CASILLAS) {
            ganador.add(liebre);
            ganador.add(tortuga);
            return true;
        }

        if (posicionLiebre >= MAX_CASILLAS) {
            ganador.add(liebre);
            return true;
        }
        if (posicionTortuga >= MAX_CASILLAS) {
            ganador.add(tortuga);
            return true;
        }

        return false;
    }

    public List<String> getGanador() {
        return ganador;
    }

}
