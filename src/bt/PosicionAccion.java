/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import AestrellaMov.CosteMov;
import bt.Reglas.TipoRecorrido;
import bt.Reglas.tiposDeMovimiento;

/**
 * Relaciona una posición alcanzable con la acción necesaria para llegar hasta ella
 * @author Víctor
 */
class PosicionAccion {
    
    Posicion posicion;
    tiposDeMovimiento tipoMovimiento = tiposDeMovimiento.Indefinido;
    private TipoRecorrido recorrido;
    CosteMov coste_movimiento;
    
    PosicionAccion (Posicion posicion, tiposDeMovimiento tipo_de_movimiento){
        this.posicion = posicion;
        this.tipoMovimiento = tipo_de_movimiento;
    }

    /**
     * @return the recorrido
     */
    public TipoRecorrido getRecorrido() {
        return recorrido;
    }

    /**
     * @param recorrido the recorrido to set
     */
    public void setRecorrido(TipoRecorrido recorrido) {
        this.recorrido = recorrido;
    }
    
}
