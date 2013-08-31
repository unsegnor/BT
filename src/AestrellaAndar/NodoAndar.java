/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaAndar;


import Aestrella.Nodo;
import bt.Posicion;

/**
 *
 * @author Víctor
 */
public class NodoAndar extends Nodo {
    
    //Identificador de la posicion a la que representa
    Posicion p;

    NodoAndar(Posicion posicion) {
        this.p = posicion;
    }
    
}
