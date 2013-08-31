/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaAndar;

import Aestrella.I_Coste;

/**
 *
 * @author Víctor
 */
public class CosteAndar implements I_Coste {

    private int puntos_de_movimiento = 0;
    
    
    @Override
    public I_Coste sumar(I_Coste coste) {
        
        //Casteamos el Coste a un CosteAndar
        CosteAndar otro = (CosteAndar) coste;
        
        CosteAndar respuesta = new CosteAndar();
        
        respuesta.setPuntos_de_movimiento(getPuntos_de_movimiento() + otro.getPuntos_de_movimiento());
        
        return respuesta;
    }

    @Override
    public int compareTo(I_Coste coste) {
        //Castear el otro
        CosteAndar otro = (CosteAndar) coste;
        
        return getPuntos_de_movimiento() - otro.getPuntos_de_movimiento();
    }

    /**
     * @return the puntos_de_movimiento
     */
    public int getPuntos_de_movimiento() {
        return puntos_de_movimiento;
    }

    /**
     * @param puntos_de_movimiento the puntos_de_movimiento to set
     */
    public void setPuntos_de_movimiento(int puntos_de_movimiento) {
        this.puntos_de_movimiento = puntos_de_movimiento;
    }
    
}
