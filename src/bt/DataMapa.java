/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Los datos de un mapa leídos de fichero
 * @author Víctor
 */
class DataMapa {
    
    //Alto del mapa
    private int alto;
    
    //Ancho del mapa
    private int ancho;
    
    //Hexágonos que componen el mapa
    private DataHexagono[] hexagonos;

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the hexagonos
     */
    public DataHexagono[] getHexagonos() {
        return hexagonos;
    }

    /**
     * @param hexagonos the hexagonos to set
     */
    public void setHexagonos(DataHexagono[] hexagonos) {
        this.hexagonos = hexagonos;
    }
            
            
    
}
