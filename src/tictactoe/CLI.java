package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class CLI {
    private char[][] tablero;
    private char jugadorActual;


    public CLI() {
        tablero = new char[3][3];
        jugadorActual = 'X';
        // inicializarTablero();
        start();
    }

    // Inicializa el tablero con espacios vacíos
    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    // Muestra el tablero en consola
    public void imprimirTablero() {
        System.out.println("Tablero:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Cambia de jugador
    public void cambiarJugador() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }

    // Coloca una marca en el tablero
    public boolean colocarMarca(int fila, int columna) {
        if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == '-') {
            tablero[fila][columna] = jugadorActual;
            return true;
        }
        return false;
    }


    // Verifica si hay un ganador
    public boolean hayGanador() {
        // Comprobar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) {
                return true;
            }
        }

        // Comprobar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == jugadorActual && tablero[1][i] == jugadorActual && tablero[2][i] == jugadorActual) {
                return true;
            }
        }

        // Comprobar diagonales
        if (tablero[0][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][2] == jugadorActual) {
            return true;
        }

        if (tablero[0][2] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][0] == jugadorActual) {
            return true;
        }

        return false;
    }
       // Verifica si hay un empate
    public boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public void ponerRandom() {
        Random random = new Random();
        int fila, columna;

        // Encuentra una posición vacía al azar
        do {
            fila = random.nextInt(3);     // Genera un número aleatorio entre 0 y 2
            columna = random.nextInt(3);  // Genera un número aleatorio entre 0 y 2
        } while (tablero[fila][columna] != '-'); // Repite hasta encontrar una posición vacía

        tablero[fila][columna] = jugadorActual; // Coloca la marca del jugador actual en la posición aleatoria
    }

    public void defender() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            // Verificar fila
            if (verificarLinea(tablero[i][0], tablero[i][1], tablero[i][2])) {
                if (tablero[i][0] == '-') tablero[i][0] = jugadorActual;
                else if (tablero[i][1] == '-') tablero[i][1] = jugadorActual;
                else if (tablero[i][2] == '-') tablero[i][2] = jugadorActual;
                return;
            }
    
            // Verificar columna
            if (verificarLinea(tablero[0][i], tablero[1][i], tablero[2][i])) {
                if (tablero[0][i] == '-') tablero[0][i] = jugadorActual;
                else if (tablero[1][i] == '-') tablero[1][i] = jugadorActual;
                else if (tablero[2][i] == '-') tablero[2][i] = jugadorActual;
                return;
            }
        }
    
        // Verificar diagonal principal
        if (verificarLinea(tablero[0][0], tablero[1][1], tablero[2][2])) {
            if (tablero[0][0] == '-') tablero[0][0] = jugadorActual;
            else if (tablero[1][1] == '-') tablero[1][1] = jugadorActual;
            else if (tablero[2][2] == '-') tablero[2][2] = jugadorActual;
            return;
        }
    
        // Verificar diagonal secundaria
        if (verificarLinea(tablero[0][2], tablero[1][1], tablero[2][0])) {
            if (tablero[0][2] == '-') tablero[0][2] = jugadorActual;
            else if (tablero[1][1] == '-') tablero[1][1] = jugadorActual;
            else if (tablero[2][0] == '-') tablero[2][0] = jugadorActual;
            return;
        }
    
        // Si no hay amenaza, hacer un movimiento aleatorio
        ponerRandom();
    }
    
    // Método para verificar si hay dos marcas iguales y una posición vacía
    private boolean verificarLinea(char a, char b, char c) {
        return (a == b && a != '-' && c == '-') ||
               (a == c && a != '-' && b == '-') ||
               (b == c && b != '-' && a == '-');
    }
    
    public void intentarGanar() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            // Verificar fila
            if (verificarGanarLinea(tablero[i][0], tablero[i][1], tablero[i][2])) {
                if (tablero[i][0] == '-') tablero[i][0] = jugadorActual;
                else if (tablero[i][1] == '-') tablero[i][1] = jugadorActual;
                else if (tablero[i][2] == '-') tablero[i][2] = jugadorActual;
                return;
            }
    
            // Verificar columna
            if (verificarGanarLinea(tablero[0][i], tablero[1][i], tablero[2][i])) {
                if (tablero[0][i] == '-') tablero[0][i] = jugadorActual;
                else if (tablero[1][i] == '-') tablero[1][i] = jugadorActual;
                else if (tablero[2][i] == '-') tablero[2][i] = jugadorActual;
                return;
            }
        }
    
        // Verificar diagonal principal
        if (verificarGanarLinea(tablero[0][0], tablero[1][1], tablero[2][2])) {
            if (tablero[0][0] == '-') tablero[0][0] = jugadorActual;
            else if (tablero[1][1] == '-') tablero[1][1] = jugadorActual;
            else if (tablero[2][2] == '-') tablero[2][2] = jugadorActual;
            return;
        }
    
        // Verificar diagonal secundaria
        if (verificarGanarLinea(tablero[0][2], tablero[1][1], tablero[2][0])) {
            if (tablero[0][2] == '-') tablero[0][2] = jugadorActual;
            else if (tablero[1][1] == '-') tablero[1][1] = jugadorActual;
            else if (tablero[2][0] == '-') tablero[2][0] = jugadorActual;
            return;
        }
    
        // Si no hay oportunidad de ganar, hacer un movimiento aleatorio
        ponerRandom();
    }
    
    // Método para verificar si hay una oportunidad de ganar
    private boolean verificarGanarLinea(char a, char b, char c) {
        return (a == b && a == jugadorActual && c == '-') ||
               (a == c && a == jugadorActual && b == '-') ||
               (b == c && b == jugadorActual && a == '-');
    }
    



    private void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Solitario (S) o Multiplayer (M)?");
        String gameMode = scanner.nextLine();
        if (gameMode.equalsIgnoreCase("s")) {
            solitario();
        } else if (gameMode.equalsIgnoreCase("m")) {
            multiplayer();
        } else {
            System.out.println("Error");
            start();
        }
        scanner.close();
    }

    private  void solitario() {
        dificultad();
    }

    private  void dificultad() {
        Scanner in = new Scanner(System.in);
        System.out.println("En que dificultad deseas jugar (1/2/3)?");
        Juego juego;
        int dificultad = in.nextInt();
        if (dificultad == 1 || dificultad == 2 || dificultad == 3) {
            System.out.println(dificultad);
            juego = new Juego(dificultad,0);
        }
        else {
            System.out.println("Error");
            dificultad();
        } 

        in.close();
    }

    private void multiplayer() {
        Scanner scanner = new Scanner(System.in);
        // juego.imprimirTablero();
        while (true) {
            int fila, columna;

            System.out.println("Jugador " + jugadorActual + ", ingresa fila y columna (0-2): ");
            fila = scanner.nextInt();
            columna = scanner.nextInt();

            if (colocarMarca(fila, columna)) {
                imprimirTablero();

                if (hayGanador()) {
                    System.out.println("¡Jugador " + jugadorActual + " gana!");
                    break;
                } else if (esEmpate()) {
                    System.out.println("¡Es un empate!");
                    break;
                }

                cambiarJugador();
            } else {
                System.out.println("Movimiento inválido, intenta nuevamente.");
            }
        }
        scanner.close();
    }
    

}
