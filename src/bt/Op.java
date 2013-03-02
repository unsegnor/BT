/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Operaciones
 * @author Víctor
 */
public class Op {
    
    //Error permitido
    static double error = 0.0001;
    
    //Compara dos números
    public static boolean iguales(double a, double b){
        double vabsoluto = Math.abs(a-b);
        return (vabsoluto < error);
    }
        //Compara dos números con el error indicado
    public static boolean iguales(double a, double b, double error){
        double vabsoluto = Math.abs(a-b);
        return (vabsoluto < error);
    }
    
    public static boolean mayorque(double a, double b){
        return a > b+(error/2);
    }
    
    public static boolean menorque(double a, double b){
        return a < b-(error/2);
    }
    
    
}