/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Neuro;

import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
class Axon {
    
    //Neurona origen
    Neuron origen;
    
    //Neuronas destino
    ArrayList<Neuron> destinos = new ArrayList<Neuron>();
    
    //Constructor
    public Axon(Neuron n){
        origen = n;
    }
}
