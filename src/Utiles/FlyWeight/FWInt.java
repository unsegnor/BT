/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FlyWeight;

/**
 * FlyWeightInt
 * @author Víctor
 */
public class FWInt {
    
    Integer valorInterno;
    
    boolean isRef=true;
    
    public Integer get(){
        return valorInterno;
    }
    
    public void set(int valor){
        if(isRef){
        valorInterno = new Integer(valor);
        isRef = false;
        }else{
            valorInterno = valor;
        }
    }
}
