/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.tiposDeMovimiento;

/**
 * Contiene los datos necesarios para responder a la fase de movimiento
 * @author Víctor
 */
public class DataMov {

    /**
     * @return the tipoDeMovimiento
     */
    public tiposDeMovimiento getTipoDeMovimiento() {
        return tipoDeMovimiento;
    }

    /**
     * @param tipoDeMovimiento the tipoDeMovimiento to set
     */
    public void setTipoDeMovimiento(tiposDeMovimiento tipoDeMovimiento) {
        this.tipoDeMovimiento = tipoDeMovimiento;
    }

    /**
     * @return the hexagono_destino
     */
    public Phexagono getHexagono_destino() {
        return hexagono_destino;
    }

    /**
     * @param hexagono_destino the hexagono_destino to set
     */
    public void setHexagono_destino(Phexagono hexagono_destino) {
        this.hexagono_destino = hexagono_destino;
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
     * @return the UsaMASC
     */
    public boolean isUsaMASC() {
        return UsaMASC;
    }

    /**
     * @param UsaMASC the UsaMASC to set
     */
    public void setUsaMASC(boolean UsaMASC) {
        this.UsaMASC = UsaMASC;
    }

    /**
     * @return the numero_de_pasos
     */
    public int getNumero_de_pasos() {
        return numero_de_pasos;
    }

    /**
     * @param numero_de_pasos the numero_de_pasos to set
     */
    public void setNumero_de_pasos(int numero_de_pasos) {
        this.numero_de_pasos = numero_de_pasos;
    }

    /**
     * @return the pasos
     */
    public Paso[] getPasos() {
        return pasos;
    }

    /**
     * @param pasos the pasos to set
     */
    public void setPasos(Paso[] pasos) {
        this.pasos = pasos;
    }
    
    //Tipo de movimiento
    private tiposDeMovimiento tipoDeMovimiento;
    
    //Hexagono de destino
    private Phexagono hexagono_destino;
    
    //Lado destino
    private int lado_destino;
    
    //True si el BattleMech dispone de MASC operativo, va a correr y correrá usándolo.
    private boolean UsaMASC;
    
    //Número de pasosn de los que se compone la ruta a seguir
    private int numero_de_pasos;
    
    //Conjunto de pasos
    private Paso pasos[];
    
}
