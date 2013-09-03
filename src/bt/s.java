/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.Random;

/**
 * Stadistics
 * @author Víctor
 */
public class s {
    
    static Random r = new Random(System.currentTimeMillis());
    
    //Devuelve true con una probabilidad probabilidad
    public static boolean p(float probabilidad){
        return (r.nextFloat() < probabilidad);
    }
    
}
