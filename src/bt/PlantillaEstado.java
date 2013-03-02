/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bt;

/**
 * Indica un subconjunto de propiedades de un State
 * @author Víctor
 */
public class PlantillaEstado implements StateInterface{

    /* Necesitamos un booleano por cada propiedad del estado
     * para indicar si se utiliza o no
     * 
     * Puedo definir cada plantilla que vaya a existir... no mejor no
     */
    
    boolean plantilla[] = new boolean[State.nvariables_de_estado];
        
    //Definir un booleano por cada variable del state
    //Puede extender de Estado y sobreescribir los gets y sets con booleanos
    //O hacer una interfaz que me obligue a responder a gets de todas las variables del state, eso va a ser, cuando lo acabe
    
}
