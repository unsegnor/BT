/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalLearner;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Víctor
 */
public class Muestreador implements Runnable {

    UL ul;
    Integer[] entradas;
    long periodo = 1000;
    boolean finalizar = false;

    public Muestreador(UL ul, Integer[] entradas, long frecuencia_sec) {
        this.ul = ul;
        this.entradas = entradas;
        this.periodo = 1000 / frecuencia_sec;
    }
    
    public void finalizar(){
        finalizar = true;
    }

    @Override
    public void run() {

        while (!finalizar) {
            try {
                Thread.currentThread().sleep(periodo);
            } catch (InterruptedException ex) {
                Logger.getLogger(Muestreador.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Muestrear y llamar a la función del procesador
            Muestra muestra = new Muestra(entradas);
            ul.muestra(muestra);
        }
    }
}
