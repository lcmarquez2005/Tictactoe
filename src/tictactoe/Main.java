package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a Tic Tac Toe");
        
        // Selección de dificultad
        System.out.println("Seleccione la dificultad:");
        System.out.println("1 - Fácil (IA juega aleatoriamente)");
        System.out.println("2 - Medio (IA se defiende)");
        System.out.println("3 - Difícil (IA se defiende y ataca)");
        int dificultad = scanner.nextInt();
        
        Juego juego = new Juego(dificultad);
        
        // Loop del juego
        boolean partidaTerminada = false;
        int i = 0;
        while (!partidaTerminada) {

            System.out.println("\nTablero actual:");
            imprimirTablero(juego.getTablero());
            // Verificar si hay ganador
            if (juego.hayGanador()) {
                System.out.println("\nTablero final:");
                imprimirTablero(juego.getTablero());
                System.out.println("¡Jugador " + juego.getJugadorActual() + " ha ganado!");
                partidaTerminada = true;
                break;
            } else if (juego.esEmpate()) {
                System.out.println("\nTablero final:");
                imprimirTablero(juego.getTablero());
                System.out.println("¡Es un empate!");
                partidaTerminada = true;
                break;
            } 




            // Turno del jugador
            if (juego.getJugadorActual() == 'X') {
                System.out.println("Tu turno (X). Ingresa fila y columna (0, 1 o 2):");
                int fila = scanner.nextInt();
                int columna = scanner.nextInt();
                if (!juego.colocarMarca(fila, columna)) {
                    System.out.println("Movimiento no válido, intenta de nuevo.");
                    continue;
                }
                System.out.println(i);
            } else if (juego.getJugadorActual() == 'O') {
                System.out.println("Turno de la IA (O).");
                juego.dificultad();
                System.out.println(juego.hayGanador());
                System.out.println(i);
            }

            i++;
        }
        scanner.close();
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
}
