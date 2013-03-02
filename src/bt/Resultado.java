/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Un estado con una probabilidad de que suceda
 * @author Víctor
 */
public class Resultado {
    
    //Estado o modificaciones del estado?
    private State estado;
    
    //Probabilidad
    private float probabilidad;

    /**
     * @return the estado
     */
    public State getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(State estado) {
        this.estado = estado;
    }

    /**
     * @return the probabilidad
     */
    public float getProbabilidad() {
        return probabilidad;
    }

    /**
     * @param probabilidad the probabilidad to set
     */
    public void setProbabilidad(float probabilidad) {
        this.probabilidad = probabilidad;
    }
    
}
