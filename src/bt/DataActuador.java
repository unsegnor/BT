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
class DataActuador {
    /*
     * Para cada actuador
     *
     * Entero	Código
     */ private int codigo;/*
     * Texto	Nombre
     */ private String nombre;/*
     * Entero	Localizacion del ítem (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)
     */ private int localizacion;/*
     * Lógico	¿Operativo?
     */ private boolean operativo;/*
     * Entero	Número de impactos
     */ private int nImpactos;
     
     public void leer(BufferedReader br) throws IOException{
         setCodigo(Integer.parseInt(br.readLine().trim()));
         setNombre(br.readLine().trim());
         setLocalizacion(Integer.parseInt(br.readLine().trim()));
         setOperativo(Boolean.parseBoolean(br.readLine().trim()));
         setnImpactos(Integer.parseInt(br.readLine().trim()));
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
     * @return the localizacion
     */
    public int getLocalizacion() {
        return localizacion;
    }

    /**
     * @param localizacion the localizacion to set
     */
    public void setLocalizacion(int localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * @return the operativo
     */
    public boolean isOperativo() {
        return operativo;
    }

    /**
     * @param operativo the operativo to set
     */
    public void setOperativo(boolean operativo) {
        this.operativo = operativo;
    }

    /**
     * @return the nImpactos
     */
    public int getnImpactos() {
        return nImpactos;
    }

    /**
     * @param nImpactos the nImpactos to set
     */
    public void setnImpactos(int nImpactos) {
        this.nImpactos = nImpactos;
    }
}
