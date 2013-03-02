/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Neuro;

/**
 *
 * @author Víctor
 */
public class Dendrita {
    
    //Receptores que determinan el peso de la entrada en la dendrita
    double peso;
    
    //Axón asociado
    Axon axon;
    
    //Neurona asociada
    Neuron neurona;
    
    //Constructor
    public Dendrita(Neuron neurona, Axon axon, double peso){
        this.neurona = neurona;
        this.axon = axon;
        this.peso = peso;
    }
    
    
}
