/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Es un Componente
 *
 * @author Víctor
 */
class DataComponente {

    /*
     * Para cada componente
     *
     * Entero	Código
     */ private int codigo;/*
     * Texto	Nombre
     */ private String nombre;/*
     * Texto	Clase (NADA,ARMA,MUNICION,EQUIPO,ACTUADOR,ARMADURA,ARMAFISICA)
     */ private String clase;/*
     * Lógico	¿El arma está montada en la parte trasera?
     */ private boolean montadaEnParteTrasera;/*
     * Entero	Localizacion del ítem
     * (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)
     */ private int localizacion;/*
     * Entero	Localizacion secundaria del item
     * (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)
     */ private int localizacionSecundaria;/*
     * Texto	Tipo de Arma (Nada, Energía, Balística, Misiles)
     */ private String tipoDeArma;/*
     * Entero	Calor
     */ private int calor;/*
     * Entero	Daño
     */ private int damage;/*
     * Entero	Disparos por Turno
     */ private int disparosPorTurno;/*
     * Entero	Distancia mínima
     */ private int distanciaMinima;/*
     * Entero	Distancia Corta
     */ private int distanciaCorta;/*
     * Entero	Distancia Media
     */ private int distanciaMedia;/*
     * Entero	Distancia Larga
     */ private int distanciaLarga;/*
     * Lógico	¿Equipo operativo?
     */ private boolean operativo;/*
     * Entero	Código del Arma para la que se usa la munición (En caso de que sea
     * munición)
     */ private int municion_codArma;/*
     * Entero	Cantidad (En caso de que sea munición)
     */ private int municion_cantidad;/*
     * Lógico	¿Munición Especial? (En caso de que sea munición)
     */ private boolean municionEspecial;/*
     * Entero	Modificador de Disparo (En caso de que sea munición)
     */ private int municion_modificadorDeDisparo;/*
     */


    public void leer(BufferedReader br) throws IOException {
        /*
         * Entero Código
         */ this.setCodigo(Integer.parseInt(br.readLine().trim()));/*
         * Texto	Nombre
         */ this.setNombre(br.readLine().trim());/*
         * Texto	Clase (NADA,ARMA,MUNICION,EQUIPO,ACTUADOR,ARMADURA,ARMAFISICA)
         */ this.setClase(br.readLine().trim());/*
         * Lógico	¿El arma está montada en la parte trasera?
         */ this.setMontadaEnParteTrasera(Boolean.parseBoolean(br.readLine().trim()));/*
         * Entero	Localizacion del ítem (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)
         */ this.setLocalizacion(Integer.parseInt(br.readLine().trim()));/*
         * Entero	Localizacion secundaria del item (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)
         */ this.setLocalizacionSecundaria(Integer.parseInt(br.readLine().trim()));/*
         * Texto	Tipo de Arma (Nada, Energía, Balística, Misiles)
         */ this.setTipoDeArma(br.readLine().trim());/*
         * Entero	Calor
         */ this.setCalor(Integer.parseInt(br.readLine().trim()));/*
         * Entero	Daño
         */ this.setDamage(Integer.parseInt(br.readLine().trim()));/*
         * Entero	Disparos por Turno
         */ this.setDisparosPorTurno(Integer.parseInt(br.readLine().trim()));/*
         * Entero	Distancia mínima
         */ this.setDistanciaMinima(Integer.parseInt(br.readLine().trim()));/*
         * Entero	Distancia Corta
         */ this.setDistanciaCorta(Integer.parseInt(br.readLine().trim()));/*
         * Entero	Distancia Media
         */ this.setDistanciaMedia(Integer.parseInt(br.readLine().trim()));/*
         * Entero	Distancia Larga
         */ this.setDistanciaLarga(Integer.parseInt(br.readLine().trim()));/*
         * Lógico	¿Equipo operativo?
         */ this.setOperativo(Boolean.parseBoolean(br.readLine().trim()));/*
         * Entero	Código del Arma para la que se usa la munición (En caso de que
         * sea munición)
         */ this.setMunicion_codArma(Integer.parseInt(br.readLine().trim()));/*
         * Entero	Cantidad (En caso de que sea munición)
         */ this.setMunicion_cantidad(Integer.parseInt(br.readLine().trim()));/*
         * Lógico	¿Munición Especial? (En caso de que sea munición)
         */ this.setMunicionEspecial("Si".equals(br.readLine().trim()));/*
         * Entero	Modificador de Disparo (En caso de que sea munición)
         */ this.setMunicion_modificadorDeDisparo(Integer.parseInt(br.readLine().trim()));/*
         */
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
     * @return the clase (NADA,ARMA,MUNICION,EQUIPO,ACTUADOR,ARMADURA,ARMAFISICA)
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
     * @return the montadaEnParteTrasera
     */
    public boolean isMontadaEnParteTrasera() {
        return montadaEnParteTrasera;
    }

    /**
     * @param montadaEnParteTrasera the montadaEnParteTrasera to set
     */
    public void setMontadaEnParteTrasera(boolean montadaEnParteTrasera) {
        this.montadaEnParteTrasera = montadaEnParteTrasera;
    }

    /**
     * @return the localizacion (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)
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
     * @return the localizacionSecundaria (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)
     */
    public int getLocalizacionSecundaria() {
        return localizacionSecundaria;
    }

    /**
     * @param localizacionSecundaria the localizacionSecundaria to set
     */
    public void setLocalizacionSecundaria(int localizacionSecundaria) {
        this.localizacionSecundaria = localizacionSecundaria;
    }

    /**
     * @return the Tipo de Arma (Nada, Energía, Balística, Misiles)
     */
    public String getTipoDeArma() {
        return tipoDeArma;
    }

    /**
     * @param tipoDeArma the tipoDeArma to set
     */
    public void setTipoDeArma(String tipoDeArma) {
        this.tipoDeArma = tipoDeArma;
    }

    /**
     * @return the calor que genera
     */
    public int getCalor() {
        return calor;
    }

    /**
     * @param calor the calor to set
     */
    public void setCalor(int calor) {
        this.calor = calor;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the disparosPorTurno
     */
    public int getDisparosPorTurno() {
        return disparosPorTurno;
    }

    /**
     * @param disparosPorTurno the disparosPorTurno to set
     */
    public void setDisparosPorTurno(int disparosPorTurno) {
        this.disparosPorTurno = disparosPorTurno;
    }

    /**
     * @return the distanciaMinima
     */
    public int getDistanciaMinima() {
        return distanciaMinima;
    }

    /**
     * @param distanciaMinima the distanciaMinima to set
     */
    public void setDistanciaMinima(int distanciaMinima) {
        this.distanciaMinima = distanciaMinima;
    }

    /**
     * @return the distanciaCorta
     */
    public int getDistanciaCorta() {
        return distanciaCorta;
    }

    /**
     * @param distanciaCorta the distanciaCorta to set
     */
    public void setDistanciaCorta(int distanciaCorta) {
        this.distanciaCorta = distanciaCorta;
    }

    /**
     * @return the distanciaMedia
     */
    public int getDistanciaMedia() {
        return distanciaMedia;
    }

    /**
     * @param distanciaMedia the distanciaMedia to set
     */
    public void setDistanciaMedia(int distanciaMedia) {
        this.distanciaMedia = distanciaMedia;
    }

    /**
     * @return the distanciaLarga
     */
    public int getDistanciaLarga() {
        return distanciaLarga;
    }

    /**
     * @param distanciaLarga the distanciaLarga to set
     */
    public void setDistanciaLarga(int distanciaLarga) {
        this.distanciaLarga = distanciaLarga;
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
     * @return the Código del Arma para la que se usa la munición (En caso de que sea
     * munición)
     */
    public int getMunicion_codArma() {
        return municion_codArma;
    }

    /**
     * @param municion_codArma the municion_codArma to set
     */
    public void setMunicion_codArma(int municion_codArma) {
        this.municion_codArma = municion_codArma;
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
     * @return the municionEspecial
     */
    public boolean isMunicionEspecial() {
        return municionEspecial;
    }

    /**
     * @param municionEspecial the municionEspecial to set
     */
    public void setMunicionEspecial(boolean municionEspecial) {
        this.municionEspecial = municionEspecial;
    }

    /**
     * @return the municion_modificadorDeDisparo
     */
    public int getMunicion_modificadorDeDisparo() {
        return municion_modificadorDeDisparo;
    }

    /**
     * @param municion_modificadorDeDisparo the municion_modificadorDeDisparo to
     * set
     */
    public void setMunicion_modificadorDeDisparo(int municion_modificadorDeDisparo) {
        this.municion_modificadorDeDisparo = municion_modificadorDeDisparo;
    }
}
