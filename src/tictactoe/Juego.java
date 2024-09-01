package tictactoe;

import java.util.Random;

public class Juego {

    private char[][] tablero;
    private int dificultad;
    private char jugadorActual;

    public Juego(int dificultad) {
        this.dificultad = dificultad;
        this.tablero = new char[3][3];
        this.jugadorActual = 'X'; // Suponemos que 'X' siempre empieza
        inicializarTablero();
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

    // Selecciona la dificultad y ejecuta el nivel correspondiente
    private void dificultad() {
        if (dificultad == 1) {
            nivel1();
        } else if (dificultad == 2) {
            nivel2();
        } else if (dificultad == 3) {
            nivel3();
        } else {
            System.out.println("Error: Dificultad no válida");
        }
    }

    // Nivel 1: IA juega aleatoriamente
    private void nivel1() {
        while (!hayGanador() && !esEmpate()) {
            if (jugadorActual == 'O') {
                ponerRandom(); // Asumiendo que tienes esta función implementada
            }
            cambiarJugador();
        }
    }

    // Nivel 2: IA se defiende
    private void nivel2() {
        while (!hayGanador() && !esEmpate()) {
            if (jugadorActual == 'O') {
                defender(); // Asumiendo que tienes esta función implementada
            }
            cambiarJugador();
        }
    }

    // Nivel 3: IA ataca y se defiende
    private void nivel3() {
        while (!hayGanador() && !esEmpate()) {
            if (jugadorActual == 'O') {
                if (!intentarGanar()) {
                    defender(); // Asumiendo que tienes esta función implementada
                }
            }
            cambiarJugador();
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
}
