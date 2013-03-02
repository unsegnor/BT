/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 *
 * @author Víctor
 */
public class Recta {
    
    //Pendiente m
    double m;
    
    //Cortes en y y en x
    double cy;
    double cx;
    
    public Recta(Punto a, Punto b){    
        //Calculamos la pendiente y el corte
        m = (b.y-a.y)/(b.x-a.x);
        //Corte en Y
        cy = a.y - (m*a.x);
        //Corte en x
        cx = a.x - (a.y/m);
    }
    
    public double y(double x){
        return (x*m)+cy;
    }
    
    public double x(double y){
        return (y/m)+cx;
    }
    
    public boolean igual(Recta otra){
        return (this.paralela(otra) && Op.iguales(this.cy, otra.cy));
    }
    
    public boolean paralela(Recta otra){
        return Op.iguales(this.m, otra.m);
    }
    
    public Punto corte(Recta otra){
        Punto p = null;
        if(!this.paralela(otra)){
            double X,Y;
            //Detectamos casos raros
            //Si la pendiente de una de ellas es 0 entonces ya sabemos que la y va a ser su cy
            if(this.m == 0) {
                //La Y está vendidad
                Y = this.cy;
                if(Double.isInfinite(otra.m)){
                    //Se cruzan X e Y vendidas
                    X = otra.cx;
                }else{
                    //Sino buscar punto de corte
                    X = otra.x(Y);
                }
            }else if(otra.m == 0){
                Y = otra.cy;
                if(Double.isInfinite(this.m)){
                    X = this.cx;
                }else{
                    //Buscar punto de corte
                    X = this.x(Y);
                }
            }else if(Double.isInfinite(this.m)){
                //TODO Parte en la que detectamos verticales m = infinito
                //La X está vendida
                X = this.cx;
                if(otra.m == 0){
                    //Y también vendida
                    Y = otra.cy;
                }else{
                    //Y se calcula
                    Y = otra.y(X);
                }
            }else if(Double.isInfinite(otra.m)){
                //La X está vendida
                X = otra.cx;
                if(this.m == 0){
                    //Y también vendida
                    Y = this.cy;
                }else{
                    //Y se calcula
                    Y = this.y(X);
                }
            }else{
            //Suponiendo las Ys iguales la X debería ser (cy2-cy1) / (m1-m2)
            X = (otra.cy-this.cy)/(this.m-otra.m);
            Y = this.y(X);
            }
            p = new Punto(X, Y, 0);
        }
        return p;
    }
    
    public String toString(){
        return "y="+m+"x*+"+cy;
    }
    
}
