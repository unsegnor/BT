/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.TiposObjetivo;

/**
 *
 * @author Víctor
 */
public class AtaquePatadaIzquierda extends AtaqueArmaFisica {
    
    public AtaquePatadaIzquierda(Phexagono objetivo, TiposObjetivo tipoObjetivo){
        this.localizacion  = Reglas.Localizacion.PI;
        this.slot = 2000;
        this.objetivo = objetivo;
        this.tipoObjetivo = tipoObjetivo;
    }
    
}
