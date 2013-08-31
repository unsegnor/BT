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
public interface I_Nodo {


    
    public I_Coste getCosteReal();

    public void setCosteReal(I_Coste coste);

    public void setCosteHeuristico(I_Coste coste);

    public void setCosteTotal(I_Coste coste);
    
    public I_Coste getCosteTotal();
    
}
