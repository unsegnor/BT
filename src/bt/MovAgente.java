/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Víctor
 */
public class MovAgente extends Agente {

    /*
     * Devolverá todas las acciones que sean posibles desde el estado actual
     * relacionadas con el movimiento
     */

    @Override
    boolean preCondiciones(State estado) {
        return estado.getFaseActual() == Reglas.Fase.Movimiento;
    }

    @Override
    protected Collection<? extends AccionResultado> getAcciones(State estado) {
        ArrayList<AccionResultado> respuesta = new ArrayList<AccionResultado>();
            
        /*
         * Este va a llamar a otros tres agentes que van a devolver las acciones posibles relativas
         * a andar, correr y saltar, respectivamente para sumarlas
         * más adelante necesitaremos determinar los estados a los que podemos llegar
         * ejecutando cada acción... no sería mejor calcularlos y devolverlos mientras calculamos
         * las acciones posibles? qué ventajas e inconvenientes presenta cada opción?
         */
        
        //Debe determinar el tipo de movimiento antes de seguir
        
        
        
            
        
        return respuesta;
    }
    
}
