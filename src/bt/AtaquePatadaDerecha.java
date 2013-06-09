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
public class AtaquePatadaDerecha extends AtaqueArmaFisica {
    
    public AtaquePatadaDerecha(Phexagono objetivo, TiposObjetivo tipoObjetivo){
        this.localizacion  = Reglas.Localizacion.PD;
        this.slot = 2000;
        this.objetivo = objetivo;
        this.tipoObjetivo = tipoObjetivo;
    }
    
}
