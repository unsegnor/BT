/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.tiposDeMovimiento;

/**
 *
 * @author Víctor
 */
class Movimiento extends Accion {
    
    //Encontrar caminos mínimos a todos los terrenos donde puede moverse
    //A* hasta que se abra un nodo con coste superior al número de puntos de movimiento
    
    //De momento seleccionar un terreno adyacente aleatorio y moverse a él
    
    //TODO cómo se comporta el simulador cuando el battlemech de un jugador automático cae en un chequeo de pilotaje enmedio de su ruta?
    //TODO programar parte que se dedica a ofrecer todas las acciones posibles.
    
    //Cómo se describe un movimiento??
    
    //Tipo de movimiento
    private tiposDeMovimiento tipo;
    
    //Con una ruta
    private Ruta ruta;
    
    //Hexágono de destino
    private Phexagono destino;
    
    //Lado de destino
    private int lado_destino;
    
    //Usa MASC?
    private boolean usaMASC;

    /**
     * @return the tipo
     */
    public tiposDeMovimiento getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(tiposDeMovimiento tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the ruta
     */
    public Ruta getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the destino
     */
    public Phexagono getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Phexagono destino) {
        this.destino = destino;
    }

    /**
     * @return the lado_destino
     */
    public int getLado_destino() {
        return lado_destino;
    }

    /**
     * @param lado_destino the lado_destino to set
     */
    public void setLado_destino(int lado_destino) {
        this.lado_destino = lado_destino;
    }

    /**
     * @return the usaMASC
     */
    public boolean isUsaMASC() {
        return usaMASC;
    }

    /**
     * @param usaMASC the usaMASC to set
     */
    public void setUsaMASC(boolean usaMASC) {
        this.usaMASC = usaMASC;
    }
}
