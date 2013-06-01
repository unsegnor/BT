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
    
    double valor = 0;
    
    public Evaluacion(T cosa, double valor){
        this.cosa = cosa;
        this.valor = valor;
    }
    
}
