/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aestrella;

import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
public class Resultado {
    
    private I_Coste coste;
    
    private ArrayList<I_Nodo> ruta;

    /**
     * @return the coste
     */
    public I_Coste getCoste() {
        return coste;
    }

    /**
     * @param coste the coste to set
     */
    public void setCoste(I_Coste coste) {
        this.coste = coste;
    }

    /**
     * @return the ruta
     */
    public ArrayList<I_Nodo> getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(ArrayList<I_Nodo> ruta) {
        this.ruta = ruta;
    }
    
}
