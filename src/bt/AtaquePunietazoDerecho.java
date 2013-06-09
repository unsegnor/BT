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
public class AtaquePunietazoDerecho extends AtaqueArmaFisica {

    public AtaquePunietazoDerecho(Phexagono objetivo, TiposObjetivo tipoObjetivo) {
        this.localizacion = Reglas.Localizacion.BD;
        this.slot = 1000;
        this.objetivo = objetivo;
        this.tipoObjetivo = tipoObjetivo;
    }
}
