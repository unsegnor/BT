/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Una tarea tiene precondiciones, acciones y resultados.
 * @author Víctor
 */
public abstract class Tarea {
    
    //Precondiciones
    abstract boolean precondiciones(EstadoDeJuego estado);
    
    //Acciones posibles
    abstract Accion[] acciones(EstadoDeJuego estado);
    
}
