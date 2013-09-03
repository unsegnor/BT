/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Clase que sirve para evaluar y rankear cosas
 * @author Victor
 */
class Evaluacion<T> {
    
    T cosa;
    
    I_Eval valor;
    
    public Evaluacion(T cosa, I_Eval valor){
        this.cosa = cosa;
        this.valor = valor;
    }
    
}
