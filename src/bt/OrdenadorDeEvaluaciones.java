/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.Comparator;

/**
 *
 * @author Victor
 */
class OrdenadorDeEvaluaciones implements Comparator<Evaluacion> {

    public OrdenadorDeEvaluaciones() {
    }

    @Override
    public int compare(Evaluacion t, Evaluacion t1) {
        return t.valor.compareTo(t1.valor);
    }
}
