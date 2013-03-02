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
        FileReader fr;
        BufferedReader bf;
        while (!isFinalizar()) {
            try {
                //Esperar un tiempo entre comprobación y comprobación
                Thread.currentThread().sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Comprobar si existe el fichero "jugada"
            if (f_jugada.exists()) {
                try {
                    
                    //Esperar por si se está escribiendo
                    Thread.currentThread().sleep(200);
                    
                    //Leer la primera línea
                    fr = new FileReader("jugada");
                    bf = new BufferedReader(fr);
                    String primeraLinea = bf.readLine();
                    
                    //Separarla por comas
                    String[] parametros = primeraLinea.split(",");
                    
                    //Crear y lanzar una jugada con los parámetros
                    new Thread(new Jugada(parametros[0], parametros[1], parametros[2])).start();
                    
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
