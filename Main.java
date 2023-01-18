/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package A1_2.Procesos_saulo_perez;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import static java.lang.Thread.sleep;
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
                sleep(5000);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ejecutaComandoyEspera(String comando, String argumentos, String metodo) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando, argumentos, metodo);
            Process p = pb.start();
            sleep(5000);
            if(p.isAlive()) {
                p.destroy();
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ejecutaComandoDirectorio(String comando, String argumentos, String directorio) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando, argumentos);
            pb.directory(new File(directorio));
            Process p = pb.start();
            p.waitFor();
            System.out.println("El proceso se ha ejecutado en: " + pb.directory());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buscayGuarda(String palabra, String entrada, String salida) {
        ProcessBuilder pb = new ProcessBuilder("find", "", entrada, salida);
        File fSalida = new File(salida);
        
        try {
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            if(!fSalida.exists()) {
                fSalida.createNewFile();
            }
            OutputStreamWriter osw = new FileWriter(fSalida);
            BufferedWriter bw = new BufferedWriter(osw);
            while((linea = br.readLine()) != null) {
                bw.write(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
