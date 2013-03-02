/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Un punto en el espacio tridimensional.
 * @author Víctor
 */
class Punto {
    //Posición en dimensiones X, Y y Z
    double x;
    double y;
    double z;
    
    public Punto(){}
    public Punto(double X, double Y, double Z){
        x = X;
        y = Y;
        z = Z;
    }
    public String toString(){
        return "("+x+", "+y+", "+z+")";
    }
}
