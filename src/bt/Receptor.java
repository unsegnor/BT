/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Víctor
 */
public class Receptor implements Runnable {

    private boolean finalizar = false;

    @Override
    public void run() {
        File f_jugada = new File("jugada");
        File f_partida = new File("partida");
        FileReader fr;
        BufferedReader bf;
        while (!isFinalizar()) {
            
            if(!f_partida.exists()){
                finalizar = true;
            }
            
            try {
                //Esperar un tiempo entre comprobación y comprobación
                Thread.currentThread().sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Comprobar si existe el fichero "jugada"
            //System.out.println("Comprobando si existe jugada...");
            if (f_jugada.exists()) {
                try {
                    
                    System.out.println("Detectada jugada...");
                    //Esperar por si se está escribiendo / borrando
                    Thread.currentThread().sleep(200);
                    
                    System.out.println("Leyendo jugada...");
                    //Leer la primera línea
                    fr = new FileReader(f_jugada);
                    bf = new BufferedReader(fr);
                    String primeraLinea = bf.readLine();
                    
                    //Separarla por comas
                    String[] parametros = primeraLinea.split(",");
                    
                    if(parametros.length == 3){
                    
                    //Crear y lanzar una jugada con los parámetros
                    new Thread(new Jugada(parametros[0], parametros[1], parametros[2])).start();
                    
                    }else{
                        
                        System.err.println("Numero de parametros de entrada incorrectos en la llamada");
                        
                    }
                    //Eliminar el archivo
                    bf.close();
                    fr.close();
                    f_jugada.delete();
                    
                    //Esperar un tiempo antes de volver a comprobar
                    Thread.currentThread().sleep(500);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @return the finalizar
     */
    public boolean isFinalizar() {
        return finalizar;
    }

    /**
     * @param finalizar the finalizar to set
     */
    public void setFinalizar(boolean finalizar) {
        this.finalizar = finalizar;
    }
}
