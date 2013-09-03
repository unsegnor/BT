/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aestrella;

/**
 *
 * @author Víctor
 */
public class Nodo implements I_Nodo{

    I_Coste real;
    I_Coste heuristico;
    I_Coste total;
    I_Nodo padre;
    
    @Override
    public I_Coste getCosteReal() {
        return real;
    }

    @Override
    public void setCosteReal(I_Coste coste) {
        real = coste;
    }

    @Override
    public void setCosteHeuristico(I_Coste coste) {
        heuristico = coste;
    }

    @Override
    public void setCosteTotal(I_Coste coste) {
        total = coste;
    }

    @Override
    public I_Coste getCosteTotal() {
        return total;
    }

    @Override
    public I_Nodo getPadre() {
       return this.padre;
    }

    @Override
    public void setPadre(I_Nodo nodo) {
        this.padre = nodo;
    }
    
}
