/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Es una línea recta
 * @author Víctor
 */
public class Recta2D {
    
    //Puntos de corte
    double corteY;
    double corteX;
    
    //Pendiente
    double pendiente;
    
    //Puntos A y B
    Punto a;
    Punto b;
    
    
    //TODO hacer función X e Y que llamen a Y1,2 y X1,2 y hagan la media de los dos resultados.
    
    //Función para calcular X en función de Y
    double Y(double x){
        return (-x*(a.y-b.y)+(b.x*a.y)-(a.x*b.y))/(b.x-a.x);
    }
        //Función para calcular X en función de Y
    double Y2(double x){
        return (-x*(b.y-a.y)+(a.x*b.y)-(b.x*a.y))/(a.x-b.x);
    }
        //Función para calcular X en función de Y
    double X(double y){
        return (-y*(b.x-a.x)+(b.x*a.y)-(a.x*b.y))/(a.y-b.y);
    }
        //Función para calcular X en función de Y
    double X2(double y){
        return (-y*(a.x-b.x)+(a.x*b.y)-(b.x*a.y))/(b.y-a.y);
    }
    
    //Función que devuelve el punto de corte entre las dos rectas
    Punto corte(Recta2D otra){
        //TODO calcular el punto de corte: uno, ninguno o todos.
        
        //Si son paralelas entonces sólo pueden ser ninguno o todos.
        if(Op.iguales(this.pendiente, otra.pendiente)){
            
        }
        
        return null;
    }
    
    public Recta2D(){}
    public Recta2D(Punto origen, Punto destino){
        
        a = origen;
        b = destino;
        
        //Calcular pendiente
        //La pendiente es lo que avanza en X entre lo que avanza en Y
        pendiente = (destino.x-origen.x)/(destino.y-origen.y);
        //Calcular el corte
        //Cuando X es cero, cuánto vale Y?
        
    }
    
}
