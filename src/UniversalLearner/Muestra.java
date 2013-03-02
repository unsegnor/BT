/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalLearner;

import java.util.Arrays;

/**
 * es una muestra de entradas al UL
 * @author Víctor
 */
public class Muestra {
    //Tiene un apartado para la hora
    long t;
    
    //Y la muestra en sí que la representaremos con Integers
    Integer[] muestra;
    //Hace copia de la muestra original
    private void init(long tiempo, Integer[] muestra){
        t = tiempo;
        this.muestra = Arrays.copyOf(muestra, muestra.length);
    }
    
    public Muestra(Integer[] muestra){
        init(System.currentTimeMillis(), muestra);
    }    
    
    public Muestra(long tiempo, Integer[] muestra){
        init(tiempo, muestra);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(t).append(": ");
        for (int i = 0; i < muestra.length-1; i++) {
            sb.append(muestra[i]);
            sb.append(" ");
        }
        sb.append(muestra[muestra.length-1]);//Imprimir el último
        return sb.toString();
    }
}
