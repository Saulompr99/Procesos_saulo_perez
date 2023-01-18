/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package A1_2.Procesos_saulo_perez;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saulo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void ejecutaComando(String comando, String argumentos, String metodo) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando, argumentos, metodo);
            Process p = pb.start();
            p.waitFor();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.out.println("El proceso ha sido interrumpido");
        }
    }
    public void ejecutaComandoyComprueba(String comando, String argumentos, String metodo) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando, argumentos, metodo);
            Process p = pb.start();
            while(p.isAlive()) {
                System.out.println("Esperando...");
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
