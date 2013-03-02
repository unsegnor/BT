/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;
import bt.Reglas.tiposDeMovimiento;

/**
 * Un estado
 * @author Víctor
 */
public class State implements StateInterfaceP {
    static final int nvariables_de_estado = 1;
    static final int _fase_actual = 0;
    
    //int propiedades[] = new int[1];
    
    private Fase faseActual;
    private int PMAndar; //Puntos de movimiento restantes para andar del mech actual
    private int PMCorrer; //Puntos de movimiento restantes para correr
    private Mapa mapa; //Mapa actual
    private Phexagono hexagonoActual; //Hexágono actual
    private int ladoActual; //Indica el lado hacia el que mira el mech actual
    private boolean mechCuerpoATierra; //Indica si el mech actual se encuentra cuerpo a tierra
    private tiposDeMovimiento modalidadMovimiento;
    private Ruta rutaActual; //Es la ruta que estamos montando en caso de la fase de movimiento
    
    public State shallowCopy(){
        State nuevo = new State(faseActual);
        nuevo.setPMAndar(PMAndar);
        nuevo.setPMCorrer(PMCorrer);
        nuevo.setHexagonoActual(hexagonoActual);
        nuevo.setLadoActual(ladoActual);
        nuevo.setMapa(mapa);
        nuevo.setMechCuerpoATierra(mechCuerpoATierra);
        nuevo.setModalidadMovimiento(modalidadMovimiento);
        nuevo.setRutaActual(rutaActual);
        
        return nuevo;
    }
    
    public State(Fase fase){
        init(fase);
    }
    
    
    private void init(Fase fase){
        setFaseActual(fase);
    }

    /**
     * @return the faseActual
     */
    @Override
    public Fase getFaseActual() {
        return faseActual;
    }

    /**
     * @param faseActual the faseActual to set
     */
    @Override
    public void setFaseActual(Fase faseActual) {
        this.faseActual = faseActual;
    }

    /**
     * @return the PMAndar
     */
    public int getPMAndar() {
        return PMAndar;
    }

    /**
     * @param PMAndar the PMAndar to set
     */
    public void setPMAndar(int PMAndar) {
        this.PMAndar = PMAndar;
    }

    /**
     * @return the mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @param mapa the mapa to set
     */
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    /**
     * @return the hexagonoActual
     */
    public Phexagono getHexagonoActual() {
        return hexagonoActual;
    }

    /**
     * @param hexagonoActual the hexagonoActual to set
     */
    public void setHexagonoActual(Phexagono hexagonoActual) {
        this.hexagonoActual = hexagonoActual;
    }

    /**
     * @return the mechCuerpoATierra
     */
    public boolean isMechCuerpoATierra() {
        return mechCuerpoATierra;
    }

    /**
     * @param mechCuerpoATierra the mechCuerpoATierra to set
     */
    public void setMechCuerpoATierra(boolean mechCuerpoATierra) {
        this.mechCuerpoATierra = mechCuerpoATierra;
    }

    /**
     * @return the modalidadMovimiento
     */
    public tiposDeMovimiento getModalidadMovimiento() {
        return modalidadMovimiento;
    }

    /**
     * @param modalidadMovimiento the modalidadMovimiento to set
     */
    public void setModalidadMovimiento(tiposDeMovimiento modalidadMovimiento) {
        this.modalidadMovimiento = modalidadMovimiento;
    }

    /**
     * @return the ladoActual
     */
    public int getLadoActual() {
        return ladoActual;
    }

    /**
     * @param ladoActual the ladoActual to set
     */
    public void setLadoActual(int ladoActual) {
        this.ladoActual = ladoActual;
    }

    /**
     * @return the rutaActual
     */
    public Ruta getRutaActual() {
        return rutaActual;
    }

    /**
     * @param rutaActual the rutaActual to set
     */
    public void setRutaActual(Ruta rutaActual) {
        this.rutaActual = rutaActual;
    }

    /**
     * @return the PMCorrer
     */
    public int getPMCorrer() {
        return PMCorrer;
    }

    /**
     * @param PMCorrer the PMCorrer to set
     */
    public void setPMCorrer(int PMCorrer) {
        this.PMCorrer = PMCorrer;
    }
}
