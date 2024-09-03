/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tictactoe;

import java.util.Scanner;

/**
 *
 * @author lcarl
 */
public class Tictactoe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // GUI v1= new GUI();
        
        
        while (true) {
            System.out.println("Quieres  jugar con Terminal O GUI? (Responde '1|2' )");
            int mode = in.nextInt();
    
            if(mode == 1) {
                CLI programa = new CLI();
                break;
            } else if (mode == 2) {
                GUI v1 = new GUI(1);
                // v1.setVisible(true);
                break;
            } 

        }


    }
    
}
