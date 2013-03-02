/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.Arrays;

/**
 * Datos de un hexágono
 * @author Víctor
 */
class DataHexagono {
    
    //Nivel del hexágono
    private int nivel;
    
    //Tipo de terreno
    private int tipo_de_terreno;
    
    //Objeto en el terreno
    private int objeto;
    
    //FCE del edificio (0 si no hay)
    private int FCE;
    
    //Edificio derrumbado
    private boolean edificio_derrumbado;
    
    //Fuego
    private boolean fuego;
    
    //Humo
    private boolean humo;
    
    //Número de garrotes caídos y disponibles
    private int numero_de_garrotes;
    
    //Caras con río hasta el centro
    private boolean[] caras_con_rio;
    
    //Caras con carretera hasta el centro
    private boolean[] caras_con_carretera;

    
    //Copiar
    public void copiaDura(DataHexagono otro){
        this.FCE = otro.FCE;
        this.caras_con_carretera = Arrays.copyOf(otro.caras_con_carretera, otro.caras_con_carretera.length);
        this.caras_con_rio = Arrays.copyOf(otro.caras_con_rio, otro.caras_con_rio.length);
        this.edificio_derrumbado = otro.edificio_derrumbado;
        this.fuego = otro.fuego;
        this.humo = otro.humo;
        this.nivel = otro.nivel;
        this.numero_de_garrotes = otro.numero_de_garrotes;
        this.objeto = otro.objeto;
        this.tipo_de_terreno = otro.tipo_de_terreno;
    }
    
    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the tipo_de_terreno
     */
    public int getTipo_de_terreno() {
        return tipo_de_terreno;
    }

    /**
     * @param tipo_de_terreno the tipo_de_terreno to set
     */
    public void setTipo_de_terreno(int tipo_de_terreno) {
        this.tipo_de_terreno = tipo_de_terreno;
    }

    /**
     * @return the objeto
     */
    public int getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(int objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the FCE
     */
    public int getFCE() {
        return FCE;
    }

    /**
     * @param FCE the FCE to set
     */
    public void setFCE(int FCE) {
        this.FCE = FCE;
    }

    /**
     * @return the edificio_derrumbado
     */
    public boolean isEdificio_derrumbado() {
        return edificio_derrumbado;
    }

    /**
     * @param edificio_derrumbado the edificio_derrumbado to set
     */
    public void setEdificio_derrumbado(boolean edificio_derrumbado) {
        this.edificio_derrumbado = edificio_derrumbado;
    }

    /**
     * @return the fuego
     */
    public boolean isFuego() {
        return fuego;
    }

    /**
     * @param fuego the fuego to set
     */
    public void setFuego(boolean fuego) {
        this.fuego = fuego;
    }

    /**
     * @return the humo
     */
    public boolean isHumo() {
        return humo;
    }

    /**
     * @param humo the humo to set
     */
    public void setHumo(boolean humo) {
        this.humo = humo;
    }

    /**
     * @return the numero_de_garrotes
     */
    public int getNumero_de_garrotes() {
        return numero_de_garrotes;
    }

    /**
     * @param numero_de_garrotes the numero_de_garrotes to set
     */
    public void setNumero_de_garrotes(int numero_de_garrotes) {
        this.numero_de_garrotes = numero_de_garrotes;
    }

    /**
     * @return the caras_con_rio
     */
    public boolean[] getCaras_con_rio() {
        return caras_con_rio;
    }

    /**
     * @param caras_con_rio the caras_con_rio to set
     */
    public void setCaras_con_rio(boolean[] caras_con_rio) {
        this.caras_con_rio = caras_con_rio;
    }

    /**
     * @return the caras_con_carretera
     */
    public boolean[] getCaras_con_carretera() {
        return caras_con_carretera;
    }

    /**
     * @param caras_con_carretera the caras_con_carretera to set
     */
    public void setCaras_con_carretera(boolean[] caras_con_carretera) {
        this.caras_con_carretera = caras_con_carretera;
    }
}
