package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {

    private Juego juego;
    private JButton[][] botones;

    public TicTacToeGUI(int dificultad) {
        juego = new Juego(dificultad);
        botones = new JButton[3][3];

        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // Usamos un GridLayout para los botones

        // Inicializar botones y agregar ActionListeners
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton("-");
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                final int fila = i;
                final int columna = j;
                botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (botones[fila][columna].getText().equals("-") && juego.getJugadorActual() == 'X') {
                            botones[fila][columna].setText("X");
                            juego.colocarMarca(fila, columna);
                            verificarEstadoJuego();
                            juego.cambiarJugador();
                            realizarMovimientoIA();
                        }
                    }
                });
                add(botones[i][j]);
            }
        }

        setVisible(true);
    }

    private void realizarMovimientoIA() {
        juego.cambiarJugador();
        juego.dificultad(); // IA realiza su movimiento según la dificultad
        actualizarTablero();
        System.out.println(juego.getDificultad());
        verificarEstadoJuego();

    }

    private void actualizarTablero() {
        char[][] tablero = juego.getTablero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText(String.valueOf(tablero[i][j]));
            }
        }
    }

    private void verificarEstadoJuego() {
        if (juego.hayGanador()) {
            JOptionPane.showMessageDialog(this, "¡Jugador " + juego.getJugadorActual() + " ha ganado!");
            reiniciarJuego();
        } else if (juego.esEmpate()) {
            JOptionPane.showMessageDialog(this, "¡Es un empate!");
            reiniciarJuego();
        }
    }

    private void reiniciarJuego() {
        juego.cambiarJugador();
        juego = new Juego(juego.getDificultad()); // Reiniciar el juego
        dispose();
        TicTacToeGUI v1 = new TicTacToeGUI(juego.getDificultad());
        v1.setVisible(true);

    }

    public static void main(String[] args) {
        // Solicitar la dificultad al usuario antes de iniciar la interfaz
        String[] opciones = {"Fácil", "Medio", "Difícil"};
        int dificultad = JOptionPane.showOptionDialog(
                null, 
                "Seleccione la dificultad:", 
                "Tic Tac Toe", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                opciones, 
                opciones[0]);

        if (dificultad == -1) {
            System.exit(0); // Si el usuario cierra el diálogo, salir
        } else {
            dificultad += 1; // Convertir la opción seleccionada a 1, 2 o 3
            new TicTacToeGUI(dificultad);
        }
    }
}
