/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.tiposDeMovimiento;

/**
 * Relaciona una posición alcanzable con la acción necesaria para llegar hasta ella
 * @author Víctor
 */
class PosicionAccion {
    
    Posicion posicion;
    tiposDeMovimiento tipoMovimiento = tiposDeMovimiento.Indefinido;
 
    
    PosicionAccion (Posicion posicion, tiposDeMovimiento tipo_de_movimiento){
        this.posicion = posicion;
        this.tipoMovimiento = tipo_de_movimiento;
    }
    
}
