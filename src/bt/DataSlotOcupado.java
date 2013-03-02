/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Víctor
 */
class DataSlotOcupado {
    /*
     * Para cada slot ocupado 
     * Texto 	Clase
     */ private String clase;/*
     * Entero	Cantidad (sólo para municiones)
     */ private int municion_cantidad;/*
     * Entero	Código
     */ private int codigo;/*
     * Texto	Nombre
     */ private String nombre;/*
     * Entero	Índice de componente (en qué posición aparece en la lista de componentes)
     */ private int indiceComponente;/*
     * Entero	Índice de actuador (en qué posición aparece en la lista de actuadores)
     */ private int indiceActuador;/*
     * Entero	Daño de la munición en caso de crítico (solo aplicable a munición)
     */ private int municion_damageCritico;/*
     */

     public void leer(BufferedReader br) throws IOException{
         setClase(br.readLine().trim());
         setMunicion_cantidad(Integer.parseInt(br.readLine().trim()));
         setCodigo(Integer.parseInt(br.readLine().trim()));
         setNombre(br.readLine().trim());
         setIndiceComponente(Integer.parseInt(br.readLine().trim()));
         setIndiceActuador(Integer.parseInt(br.readLine().trim()));
         setMunicion_damageCritico(Integer.parseInt(br.readLine().trim()));
     }
     
    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the municion_cantidad
     */
    public int getMunicion_cantidad() {
        return municion_cantidad;
    }

    /**
     * @param municion_cantidad the municion_cantidad to set
     */
    public void setMunicion_cantidad(int municion_cantidad) {
        this.municion_cantidad = municion_cantidad;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the Índice de componente (en qué posición aparece en la lista de componentes)
     */
    public int getIndiceComponente() {
        return indiceComponente;
    }

    /**
     * @param indiceComponente the indiceComponente to set
     */
    public void setIndiceComponente(int indiceComponente) {
        this.indiceComponente = indiceComponente;
    }

    /**
     * @return the Índice de actuador (en qué posición aparece en la lista de actuadores)
     */
    public int getIndiceActuador() {
        return indiceActuador;
    }

    /**
     * @param indiceActuador the indiceActuador to set
     */
    public void setIndiceActuador(int indiceActuador) {
        this.indiceActuador = indiceActuador;
    }

    /**
     * @return the Daño de la munición en caso de crítico (solo aplicable a munición)
     */
    public int getMunicion_damageCritico() {
        return municion_damageCritico;
    }

    /**
     * @param municion_damageCritico the municion_damageCritico to set
     */
    public void setMunicion_damageCritico(int municion_damageCritico) {
        this.municion_damageCritico = municion_damageCritico;
    }

}
