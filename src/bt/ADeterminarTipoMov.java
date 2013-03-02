/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Víctor
 */
public class ADeterminarTipoMov extends Agente {

    
    AInmovil AgenteInmovil = new AInmovil();
    
    @Override
    boolean preCondiciones(State estado) {
        return estado.getFaseActual() == Fase.Movimiento
                //Y no esté determinada ya
                && estado.getModalidadMovimiento() == Reglas.tiposDeMovimiento.Indefinido;
        
    }

    @Override
    protected Collection<? extends AccionResultado> getAcciones(State estado) {
        ArrayList<AccionResultado> respuesta = new ArrayList<AccionResultado>();

        
        //Inmovil
        respuesta.addAll(AgenteInmovil.getAcciones(estado));
        
        //Andar
        Movimiento aA = new Movimiento();
        aA.setTipo(Reglas.tiposDeMovimiento.Andar);
        //Correr
        
        //Saltar
        
        return respuesta;
    }
    
}
