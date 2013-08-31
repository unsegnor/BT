/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aestrella;

import java.util.Comparator;

/**
 *
 * @author Víctor
 */
class ComparadorDePosiciones implements Comparator<I_Nodo> {

    @Override
    public int compare(I_Nodo o1, I_Nodo o2) {
        return o1.getCosteTotal().compareTo(o2.getCosteTotal());
    }
    
}
