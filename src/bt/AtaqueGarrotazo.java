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
public class AtaqueGarrotazo extends AtaqueArmaFisica {
    
    public AtaqueGarrotazo(Phexagono objetivo, TiposObjetivo tipoObjetivo){
        this.localizacion = Reglas.Localizacion.BIBD;
        this.slot = 3000;
        this.objetivo = objetivo;
        this.tipoObjetivo = tipoObjetivo;
    }
    
}
