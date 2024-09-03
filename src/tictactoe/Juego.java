package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Juego {

    /**
     * @return the tablero
     */
    public char[][] getTablero() {
        return tablero;
    }

    /**
     * @param tablero the tablero to set
     */
    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    /**
     * @return the dificultad
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * @param dificultad the dificultad to set
     */
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * @return the jugadorActual
     */
    public char getJugadorActual() {
        return jugadorActual;
    }

    /**
     * @param jugadorActual the jugadorActual to set
     */
    public void setJugadorActual(char jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    private char[][] tablero;
    private int dificultad;
    private char jugadorActual;

    public Juego(int dificultad) {
        this.dificultad = dificultad;
        this.tablero = new char[3][3];
        this.jugadorActual = 'X'; // Suponemos que 'X' siempre empieza
        inicializarTablero();
        // start();!solo para cuando la interfaz en CLI
    }

        // Método auxiliar para imprimir el tablero
    private static void imprimirTablero(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Inicializa el tablero con espacios vacíos
    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    // Cambia de jugador
    public void cambiarJugador() {
        if(getJugadorActual() == 'O') {
            setJugadorActual('X');
        } else if(getJugadorActual() == 'X') {
            setJugadorActual('O');
        }
    }

    // Coloca una marca en el tablero
    public boolean colocarMarca(int fila, int columna) {
        if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == '-') {
            tablero[fila][columna] = jugadorActual;
            cambiarJugador();
            return true;
        }
        return false;
    }

    // Verifica si hay un ganador
    public boolean hayGanador() {
        for (int j = 0; j < 2; j++) {//ciclo para asegurar comprobar ambos jugadores si ganaron
            for (int i = 0; i < 3; i++) {
                // Comprobar filas
                if (tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) {
                    return true;
                }
                //Comprobar Columnas
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
            cambiarJugador();
        }

        return false;
    }

    // Verifica si hay un ganador
    public boolean hayGanador(char jugadorActual) {
            for (int i = 0; i < 3; i++) {
                // Comprobar filas
                if (tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) {
                    return true;
                }
                //Comprobar Columnas
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

    // Selecciona la dificultad y ejecuta el nivel correspondiente
    public void dificultad() {
        if (dificultad == 1) {
            nivel1();
        } else if (dificultad == 2) {
            nivel2();
        } else if (dificultad == 3) {
            nivel3();
        } else {
            System.out.println("Error: Dificultad no válida");
        }
        cambiarJugador();
    }

    // Nivel 1: IA juega aleatoriamente
    private void nivel1() {

            if (getJugadorActual() == 'O') {
                ponerRandom(); // Asumiendo que tienes esta función implementada
            }

        
    }

    // Nivel 2: IA se defiende
    private void nivel2() {

        if (jugadorActual == 'O') {
            defender(); // Asumiendo que tienes esta función implementada
        }
        
    }

    // Nivel 3: IA ataca y se defiende
    private void nivel3() {

            if (jugadorActual == 'O') {
                if (!intentarGanar()) {
                    defender(); // Asumiendo que tienes esta función implementada
                }
            
        }
    }

    // Coloca una marca en una posición aleatoria
    private void ponerRandom() {
        Random rand = new Random();
        int fila, columna;
        do {
            fila = rand.nextInt(3);
            columna = rand.nextInt(3);
        } while (tablero[fila][columna] != '-');
        tablero[fila][columna] = jugadorActual;
    }

    // Verifica si la IA puede ganar en su próximo turno
    private boolean intentarGanar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    tablero[i][j] = jugadorActual;
                    if (hayGanador()) {
                        return true;
                    }
                    tablero[i][j] = '-'; // Revertir movimiento si no es ganador
                }
            }
        }
        return false;
    }

    // Defiende contra el jugador
    private void defender() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    tablero[i][j] = (jugadorActual == 'X') ? 'O' : 'X';
                    if (hayGanador()) {
                        tablero[i][j] = jugadorActual;
                        return;
                    }
                    tablero[i][j] = '-'; // Revertir movimiento si no es necesario bloquear
                }
            }
        }
        ponerRandom(); // Si no hay nada que bloquear, juega aleatoriamente
    }


    private void start() {
        Scanner scanner = new Scanner(System.in);
                // Loop del juego

        boolean partidaTerminada = false;
        while (!partidaTerminada) {

            System.out.println("\nTablero actual:");
            imprimirTablero(getTablero());
            // Verificar si hay ganador
            if (hayGanador()) {
                System.out.println("\nTablero final:");
                imprimirTablero(getTablero());
                System.out.println("¡Jugador " + getJugadorActual() + " ha ganado!");
                partidaTerminada = true;
                break;
            } else if (esEmpate()) {
                System.out.println("\nTablero final:");
                imprimirTablero(getTablero());
                System.out.println("¡Es un empate!");
                partidaTerminada = true;
                break;
            } 

            // Turno del jugador
            if (getJugadorActual() == 'X') {
                System.out.println("Tu turno (X). Ingresa fila y columna (0, 1 o 2):");
                int fila = scanner.nextInt();
                int columna = scanner.nextInt();
                if (!colocarMarca(fila, columna)) {
                    System.out.println("Movimiento no válido, intenta de nuevo.");
                    continue;
                }
            } else if (getJugadorActual() == 'O') {
                System.out.println("Turno de la IA (O).");
                dificultad();
                // System.out.println(hayGanador());
            }
        }
        scanner.close();
    }
}
