/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.*;

/**
 *
 * @author Víctor
 */
public class Reaccion extends Accion {

    private TiposDeReaccion tipo;
    
    @Override
    Reglas.Fase getFase() {
        return Fase.Reaccion;
        
        
        
    }

    /**
     * @return the tipo
     */
    public TiposDeReaccion getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TiposDeReaccion tipo) {
        this.tipo = tipo;
    }
    
}
