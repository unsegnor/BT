/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.Collection;

/**
 * 
 * @author Víctor
 */
public abstract class Agente {

    
    /*
     * En realidad los agentes no quieren un estado entero, quieren una parte del estado
     * evalúan una parte del estado e indican qué es lo que cambiará si se lleva a cabo una acción
     * indican las acciones posibles según esa parte del estado y en qué cambiará el siguiente
     * podrían informar de la parte del estado que van a querer y la que van a modificar
     * para que se puedan evitar evaluaciones innecesarias
     */
    
    abstract boolean preCondiciones(State estado);
    
    Collection<? extends AccionResultado> getAccionesPosibles(State estado){
        if(preCondiciones(estado)) return getAcciones(estado);
        else return null;
    }
    
    protected abstract Collection<? extends AccionResultado> getAcciones(State estado);
    
    
}
