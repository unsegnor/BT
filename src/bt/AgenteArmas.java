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
public class AgenteArmas extends Agente{

    @Override
    boolean preCondiciones(State estado) {
        //Estar en la fase de ataque con armas al menos
        return (estado.getFaseActual() == Reglas.Fase.AtaqueArmas);
    }

    @Override
    protected Collection<? extends AccionResultado> getAcciones(State estado) {
        //Devolver todas las acciones posibles
        
        //Consultar las armas de que disponemos
        
        //Construir una acción de disparo para cada arma y a cada posible objetivo o hexágono
        
        return null;
        
    }
    
}
