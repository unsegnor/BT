/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 *
 * @author Víctor
 */
public class Dados {
    
    double[] p2D6 = {0,0,1/36,2/36,3/36,4/36,5/36,6/36,5/36,4/36,3/36,2/36,1/36};
    
    
    //Devuelve la probabilidad de que salga un número con 2Dados de 6
    public double p2D6(int resultado){
        double respuesta = 0;
        if(resultado > 0 && resultado < p2D6.length){
            respuesta = p2D6[resultado];
        }
        return respuesta;
    }
    
}
