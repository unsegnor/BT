/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 *
 * @author Víctor
 */
public class Segmento extends Recta {
    
    //Puntos que determinan el inicio y el final del segmento
    Punto a;
    Punto b;
    
    double maxX;
    double minX;
    double maxY;
    double minY;
    
    public Segmento(Punto a, Punto b){
        super(a,b);
        this.a = a;
        this.b = b;
        maxX = Math.max(a.x, b.x);
        minX = Math.min(a.x, b.x);
        maxY = Math.max(a.y, b.y);
        minY = Math.min(a.y, b.y);
    }
    
    @Override
    public Punto corte(Recta r){
        Punto p = super.corte(r);
        if(p!=null){
            //Si el punto está fuera del rango que define el segmento entonces fuera, no se cortan
            if(p.x > maxX || p.x < minX || p.y > maxY || p.y < minY) p=null;
        }
        return p;
    }
    
    public Punto corte(Segmento s){
        Punto p = super.corte(s);
        if(p!=null){
            //Si el punto está fuera del rango que define el segmento propio o el otro entonces fuera, no se cortan
            if(Op.mayorque(p.x,maxX) 
                    || Op.menorque(p.x, minX) 
                    || Op.mayorque(p.y, maxY) 
                    || Op.menorque(p.y,minY) 
                    || Op.mayorque(p.x,s.maxX) 
                    || Op.menorque(p.x,s.minX) 
                    || Op.mayorque(p.y,s.maxY) 
                    || Op.menorque(p.y,s.minY)) p=null;
        }
        return p;
    }
    
    
}
