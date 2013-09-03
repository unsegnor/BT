/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;

/**
 * Almacena los pasos necesarios para cumplir una ruta en el mapa.
 * @author Víctor
 */
public class Ruta {
    
    private ArrayList<Paso> pasos = new ArrayList<Paso>();

    /**
     * @return the pasos
     */
    public ArrayList<Paso> getPasos() {
        return pasos;
    }

    /**
     * @param pasos the pasos to set
     */
    public void setPasos(ArrayList<Paso> pasos) {
        this.pasos = pasos;
    }
    
}
