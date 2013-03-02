/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Representa una posición en el mapa, con hexágono y lado
 * @author Víctor
 */
class Posicion {
    
    private Phexagono hexagono;
    
    private int lado;

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
    
}
