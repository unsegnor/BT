/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FlyWeight;

/**
 * Es como un wrapper para una clase
 * @author Víctor
 */
public class FW<T> {
    
    T punteroInterno;
    boolean esReferencia=true; //Comienza siendo referencia
    
    public final T get(){
        return punteroInterno;
    }
    
    public void set(T valor){
        /*
        if(esReferencia){
            //Entonces el punteroInterno apunta al valor, sino también?
        }
        */
        
        punteroInterno = valor;
    }
    /*
    public final T getWritable(){
        //Si no es propio hacemos copia propia
        if(esReferencia){
        //Hacemos la copia del puntero interno
        //Y llamamos a getWritable
            punteroInterno.getWritable();
        }
        
    }
    */
}
