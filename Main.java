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
        ejecutaComando("cmd.exe", "/c", "ver");
        ejecutaComandoyComprueba("cmd.exe", "/c", "hostname");
        ejecutaComandoyEspera("cmd.exe", "/c", "netstat");
        ejecutaComandoDirectorio("ping", "8.8.8.8", "C:/");
        buscayGuarda("\"saulo\"", "usuarios.txt", "users.txt");
    }
    
    public static void ejecutaComando(String comando, String argumentos, String metodo) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando, argumentos, metodo);
            Process p = pb.start();
            p.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String linea;
            while((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.out.println("El proceso ha sido interrumpido");
        }
    }
    public static void ejecutaComandoyComprueba(String comando, String argumentos, String metodo) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando, argumentos, metodo);
            Process p = pb.start();
            while(p.isAlive()) {
                System.out.println("Esperando...");
                sleep(5000);
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void ejecutaComandoyEspera(String comando, String argumentos, String metodo) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando, argumentos, metodo);
            Process p = pb.start();
            sleep(5000);
            if(p.isAlive()) {
                p.destroy();
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ejecutaComandoDirectorio(String comando, String argumentos, String directorio) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando, argumentos);
            pb.directory(new File(directorio));
            Process p = pb.start();
            p.waitFor();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            System.out.println("El proceso se ha ejecutado en: " + pb.directory());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void buscayGuarda(String palabra, String entrada, String salida) {
        ProcessBuilder pb = new ProcessBuilder("find", palabra, entrada);
        File fSalida = new File(salida);
        
        try {
            Process p = pb.start();
            p.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            if(!fSalida.exists()) {
                fSalida.createNewFile();
            }
            OutputStreamWriter osw = new FileWriter(fSalida, true);
            BufferedWriter bw = new BufferedWriter(osw);
            while((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
