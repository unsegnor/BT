/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aestrella;

import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
public interface I_Grafo {

    public I_Coste calcularCosteReal(I_Nodo a, I_Nodo b);

    public I_Coste calcularCosteHeuristico(I_Nodo a, I_Nodo b);
    
    public ArrayList<I_Nodo> getVecinos(I_Nodo nodo);
    
}
