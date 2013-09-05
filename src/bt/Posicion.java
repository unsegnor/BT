/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Representa una posición en el mapa, con hexágono y lado
 *
 * @author Víctor
 */
public class Posicion {

    private Phexagono hexagono;
    private int lado;

    Posicion(){}
    
    Posicion(int columna, int fila, int cara){
        Phexagono h = new Phexagono(columna, fila);
        init(h,cara);
    }
    
    Posicion(Phexagono hexagono, int cara){
        init(hexagono, cara);
        
    }
    
    private void init(Phexagono hexagono, int lado){
        this.hexagono = hexagono;
        this.lado = lado;
    }
    
    /**
     * @return the hexagono
     */
    public Phexagono getHexagono() {
        return hexagono;
    }

    /**
     * @param hexagono the hexagono to set
     */
    public void setHexagono(Phexagono hexagono) {
        this.hexagono = hexagono;
    }

    /**
     * @return the lado
     */
    public int getLado() {
        return lado;
    }

    /**
     * @param lado the lado to set
     */
    public void setLado(int lado) {
        this.lado = lado;
    }

    @Override
    public int hashCode() {
        /*
         * 
         * 8 bits para la columna, 8 bits para la fila y 3 bits para la cara
         */

        int c = this.hexagono.getColumna();
        int f = this.hexagono.getFila();
        int l = this.lado;

        return (c << 11) | (f << 3) | (l);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Posicion)) {
            return false;
        }

        Posicion rhs = (Posicion) obj;

        return (this.hexagono.getColumna() == rhs.hexagono.getColumna()
                && this.hexagono.getFila() == rhs.hexagono.getFila()
                && this.lado == rhs.lado);
    }

    public Posicion delante() {
        Posicion respuesta = new Posicion();

        Phexagono h = Mapa.cara(this.hexagono, this.lado);
        respuesta.hexagono = h;
        //El lado es el mismo
        respuesta.lado = this.lado;

        return respuesta;

    }

    public Posicion atras() {
        Posicion respuesta = new Posicion();
        //Nos movemos al de atrás
        int caracontraria = Mapa.caracontraria(this.lado);
        Phexagono h = Mapa.cara(this.hexagono, caracontraria);
        respuesta.hexagono = h;
        //Terminamos mirando hacia el mismo sitio
        respuesta.lado = this.lado;

        return respuesta;
    }

    public Posicion giroIzquierda() {
        Posicion respuesta = new Posicion();

        int nuevoLado = Mapa.caraIzquierda(this.lado);
        respuesta.setLado(nuevoLado);
        //Mismo hexágono
        respuesta.setHexagono(this.hexagono);

        return respuesta;
    }

    public Posicion giroDerecha() {
        Posicion respuesta = new Posicion();

        int nuevoLado = Mapa.caraDerecha(this.lado);
        respuesta.setLado(nuevoLado);
        //Mismo hexágono
        respuesta.setHexagono(this.hexagono);

        return respuesta;
    }
    
    public static int distanciaEntreCaras(int caraA, int caraB){
        
        //TODO resumir en fórmula matemática
        
        int respuesta = 0;
        int diff = Math.abs(caraA-caraB);
        if(diff==5){//Será que está comprobando entre 1 y 6
            respuesta =1;
        }else if(diff ==4){
            //Está comprobando entr 2 y 6 o entre 1 y 5
            respuesta = 2;
        }else{
            respuesta = diff;
        }
        
        
        return respuesta;
    }
    
    @Override
    public String toString(){
        return this.hexagono.toString() + "->" + this.lado;
    }
}
