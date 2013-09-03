/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Contiene la información de un mech del archivo mechs
 * @author Víctor
 */
public class DataMech {
    
    //Número de jugador que lo controla
    private int nJugador;
    
    //Operativo
    private boolean operativo;
    
    //Desconectado
    private boolean desconectado;
    
    //Atascado
    private boolean atascado;
    
    //En el suelo
    private boolean enelsuelo;
    
    //Fila del hexagono
    private int fila;
    
    //Columna del hexagono
    private int columna;
    
    //Lado de encaramiento del Mech
    private int encaramientoMech;
    
    //Lado de encaramiento del torso
    private int encaramientoTorso;
    
    //Temperatura
    private int temperatura;
    
    //Ardiendo
    private boolean ardiendo;
    
    //Con garrote
    private boolean conGarrote;
    
    //Tipogarrote
    private int tipoGarrote;
    
    //Puntos de blindaje
    private int blindaje_brazo_izq;
    private int blindaje_torso_izq;
    private int blindaje_pierna_izq;
    private int blindaje_pierna_der;
    private int blindaje_torso_der;
    private int blindaje_brazo_der;
    private int blindaje_torso_central;
    private int blindaje_cabeza;
    private int blindaje_torso_izq_atras;
    private int blindaje_torso_der_atras;
    private int blindaje_torso_central_atras;
    
    //Puntos de estructura interna
    private int puntos_estructura_brazo_izq;
    private int puntos_estructura_torso_izq;
    private int puntos_estructura_pierna_izq;
    private int puntos_estructura_pierna_der;
    private int puntos_estructura_torso_der;
    private int puntos_estructura_brazo_der;
    private int puntos_estructura_torso_central;
    private int puntos_estructura_cabeza;
    
    //Si es el Mech del Jugador podemos tener más información
    private DataMechAddInfo informacion_adicional = null;
    
    //Narcs colocados por el Mech actual en el indizado por el vector
    private boolean[] narc_colocado;
    
    //iNarc colocado por el Mech actual en el indizado por el vector
    private boolean[] iNarc_colocado;

    /**
     * @return the nJugador
     */
    public int getnJugador() {
        return nJugador;
    }

    /**
     * @param nJugador the nJugador to set
     */
    public void setnJugador(int nJugador) {
        this.nJugador = nJugador;
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
     * @return the desconectado
     */
    public boolean isDesconectado() {
        return desconectado;
    }

    /**
     * @param desconectado the desconectado to set
     */
    public void setDesconectado(boolean desconectado) {
        this.desconectado = desconectado;
    }

    /**
     * @return the atascado
     */
    public boolean isAtascado() {
        return atascado;
    }

    /**
     * @param atascado the atascado to set
     */
    public void setAtascado(boolean atascado) {
        this.atascado = atascado;
    }

    /**
     * @return the enelsuelo
     */
    public boolean isEnelsuelo() {
        return enelsuelo;
    }

    /**
     * @param enelsuelo the enelsuelo to set
     */
    public void setEnelsuelo(boolean enelsuelo) {
        this.enelsuelo = enelsuelo;
    }

    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * @return the encaramientoMech
     */
    public int getEncaramientoMech() {
        return encaramientoMech;
    }

    /**
     * @param encaramientoMech the encaramientoMech to set
     */
    public void setEncaramientoMech(int encaramientoMech) {
        this.encaramientoMech = encaramientoMech;
    }

    /**
     * @return the encaramientoTorso
     */
    public int getEncaramientoTorso() {
        return encaramientoTorso;
    }

    /**
     * @param encaramientoTorso the encaramientoTorso to set
     */
    public void setEncaramientoTorso(int encaramientoTorso) {
        this.encaramientoTorso = encaramientoTorso;
    }

    /**
     * @return the temperatura
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the ardiendo
     */
    public boolean isArdiendo() {
        return ardiendo;
    }

    /**
     * @param ardiendo the ardiendo to set
     */
    public void setArdiendo(boolean ardiendo) {
        this.ardiendo = ardiendo;
    }

    /**
     * @return the conGarrote
     */
    public boolean isConGarrote() {
        return conGarrote;
    }

    /**
     * @param conGarrote the conGarrote to set
     */
    public void setConGarrote(boolean conGarrote) {
        this.conGarrote = conGarrote;
    }

    /**
     * @return the tipoGarrote
     */
    public int getTipoGarrote() {
        return tipoGarrote;
    }

    /**
     * @param tipoGarrote the tipoGarrote to set
     */
    public void setTipoGarrote(int tipoGarrote) {
        this.tipoGarrote = tipoGarrote;
    }

    /**
     * @return the blindaje_brazo_izq
     */
    public int getBlindaje_brazo_izq() {
        return blindaje_brazo_izq;
    }

    /**
     * @param blindaje_brazo_izq the blindaje_brazo_izq to set
     */
    public void setBlindaje_brazo_izq(int blindaje_brazo_izq) {
        this.blindaje_brazo_izq = blindaje_brazo_izq;
    }

    /**
     * @return the blindaje_torso_izq
     */
    public int getBlindaje_torso_izq() {
        return blindaje_torso_izq;
    }

    /**
     * @param blindaje_torso_izq the blindaje_torso_izq to set
     */
    public void setBlindaje_torso_izq(int blindaje_torso_izq) {
        this.blindaje_torso_izq = blindaje_torso_izq;
    }

    /**
     * @return the blindaje_pierna_izq
     */
    public int getBlindaje_pierna_izq() {
        return blindaje_pierna_izq;
    }

    /**
     * @param blindaje_pierna_izq the blindaje_pierna_izq to set
     */
    public void setBlindaje_pierna_izq(int blindaje_pierna_izq) {
        this.blindaje_pierna_izq = blindaje_pierna_izq;
    }

    /**
     * @return the blindaje_pierna_der
     */
    public int getBlindaje_pierna_der() {
        return blindaje_pierna_der;
    }

    /**
     * @param blindaje_pierna_der the blindaje_pierna_der to set
     */
    public void setBlindaje_pierna_der(int blindaje_pierna_der) {
        this.blindaje_pierna_der = blindaje_pierna_der;
    }

    /**
     * @return the blindaje_torso_der
     */
    public int getBlindaje_torso_der() {
        return blindaje_torso_der;
    }

    /**
     * @param blindaje_torso_der the blindaje_torso_der to set
     */
    public void setBlindaje_torso_der(int blindaje_torso_der) {
        this.blindaje_torso_der = blindaje_torso_der;
    }

    /**
     * @return the blindaje_brazo_der
     */
    public int getBlindaje_brazo_der() {
        return blindaje_brazo_der;
    }

    /**
     * @param blindaje_brazo_der the blindaje_brazo_der to set
     */
    public void setBlindaje_brazo_der(int blindaje_brazo_der) {
        this.blindaje_brazo_der = blindaje_brazo_der;
    }

    /**
     * @return the blindaje_torso_central
     */
    public int getBlindaje_torso_central() {
        return blindaje_torso_central;
    }

    /**
     * @param blindaje_torso_central the blindaje_torso_central to set
     */
    public void setBlindaje_torso_central(int blindaje_torso_central) {
        this.blindaje_torso_central = blindaje_torso_central;
    }

    /**
     * @return the blindaje_cabeza
     */
    public int getBlindaje_cabeza() {
        return blindaje_cabeza;
    }

    /**
     * @param blindaje_cabeza the blindaje_cabeza to set
     */
    public void setBlindaje_cabeza(int blindaje_cabeza) {
        this.blindaje_cabeza = blindaje_cabeza;
    }

    /**
     * @return the blindaje_torso_izq_atras
     */
    public int getBlindaje_torso_izq_atras() {
        return blindaje_torso_izq_atras;
    }

    /**
     * @param blindaje_torso_izq_atras the blindaje_torso_izq_atras to set
     */
    public void setBlindaje_torso_izq_atras(int blindaje_torso_izq_atras) {
        this.blindaje_torso_izq_atras = blindaje_torso_izq_atras;
    }

    /**
     * @return the blindaje_torso_der_atras
     */
    public int getBlindaje_torso_der_atras() {
        return blindaje_torso_der_atras;
    }

    /**
     * @param blindaje_torso_der_atras the blindaje_torso_der_atras to set
     */
    public void setBlindaje_torso_der_atras(int blindaje_torso_der_atras) {
        this.blindaje_torso_der_atras = blindaje_torso_der_atras;
    }

    /**
     * @return the blindaje_torso_central_atras
     */
    public int getBlindaje_torso_central_atras() {
        return blindaje_torso_central_atras;
    }

    /**
     * @param blindaje_torso_central_atras the blindaje_torso_central_atras to set
     */
    public void setBlindaje_torso_central_atras(int blindaje_torso_central_atras) {
        this.blindaje_torso_central_atras = blindaje_torso_central_atras;
    }

    /**
     * @return the puntos_estructura_brazo_izq
     */
    public int getPuntos_estructura_brazo_izq() {
        return puntos_estructura_brazo_izq;
    }

    /**
     * @param puntos_estructura_brazo_izq the puntos_estructura_brazo_izq to set
     */
    public void setPuntos_estructura_brazo_izq(int puntos_estructura_brazo_izq) {
        this.puntos_estructura_brazo_izq = puntos_estructura_brazo_izq;
    }

    /**
     * @return the puntos_estructura_torso_izq
     */
    public int getPuntos_estructura_torso_izq() {
        return puntos_estructura_torso_izq;
    }

    /**
     * @param puntos_estructura_torso_izq the puntos_estructura_torso_izq to set
     */
    public void setPuntos_estructura_torso_izq(int puntos_estructura_torso_izq) {
        this.puntos_estructura_torso_izq = puntos_estructura_torso_izq;
    }

    /**
     * @return the puntos_estructura_pierna_izq
     */
    public int getPuntos_estructura_pierna_izq() {
        return puntos_estructura_pierna_izq;
    }

    /**
     * @param puntos_estructura_pierna_izq the puntos_estructura_pierna_izq to set
     */
    public void setPuntos_estructura_pierna_izq(int puntos_estructura_pierna_izq) {
        this.puntos_estructura_pierna_izq = puntos_estructura_pierna_izq;
    }

    /**
     * @return the puntos_estructura_pierna_der
     */
    public int getPuntos_estructura_pierna_der() {
        return puntos_estructura_pierna_der;
    }

    /**
     * @param puntos_estructura_pierna_der the puntos_estructura_pierna_der to set
     */
    public void setPuntos_estructura_pierna_der(int puntos_estructura_pierna_der) {
        this.puntos_estructura_pierna_der = puntos_estructura_pierna_der;
    }

    /**
     * @return the puntos_estructura_torso_der
     */
    public int getPuntos_estructura_torso_der() {
        return puntos_estructura_torso_der;
    }

    /**
     * @param puntos_estructura_torso_der the puntos_estructura_torso_der to set
     */
    public void setPuntos_estructura_torso_der(int puntos_estructura_torso_der) {
        this.puntos_estructura_torso_der = puntos_estructura_torso_der;
    }

    /**
     * @return the puntos_estructura_brazo_der
     */
    public int getPuntos_estructura_brazo_der() {
        return puntos_estructura_brazo_der;
    }

    /**
     * @param puntos_estructura_brazo_der the puntos_estructura_brazo_der to set
     */
    public void setPuntos_estructura_brazo_der(int puntos_estructura_brazo_der) {
        this.puntos_estructura_brazo_der = puntos_estructura_brazo_der;
    }

    /**
     * @return the puntos_estructura_torso_central
     */
    public int getPuntos_estructura_torso_central() {
        return puntos_estructura_torso_central;
    }

    /**
     * @param puntos_estructura_torso_central the puntos_estructura_torso_central to set
     */
    public void setPuntos_estructura_torso_central(int puntos_estructura_torso_central) {
        this.puntos_estructura_torso_central = puntos_estructura_torso_central;
    }

    /**
     * @return the puntos_estructura_cabeza
     */
    public int getPuntos_estructura_cabeza() {
        return puntos_estructura_cabeza;
    }

    /**
     * @param puntos_estructura_cabeza the puntos_estructura_cabeza to set
     */
    public void setPuntos_estructura_cabeza(int puntos_estructura_cabeza) {
        this.puntos_estructura_cabeza = puntos_estructura_cabeza;
    }

    /**
     * @return the informacion_adicional
     */
    public DataMechAddInfo getInformacion_adicional() {
        return informacion_adicional;
    }

    /**
     * @param informacion_adicional the informacion_adicional to set
     */
    public void setInformacion_adicional(DataMechAddInfo informacion_adicional) {
        this.informacion_adicional = informacion_adicional;
    }

    /**
     * @return the narc_colocado
     */
    public boolean[] getNarc_colocado() {
        return narc_colocado;
    }

    /**
     * @param narc_colocado the narc_colocado to set
     */
    public void setNarc_colocado(boolean[] narc_colocado) {
        this.narc_colocado = narc_colocado;
    }

    /**
     * @return the iNarc_colocado
     */
    public boolean[] getiNarc_colocado() {
        return iNarc_colocado;
    }

    /**
     * @param iNarc_colocado the iNarc_colocado to set
     */
    public void setiNarc_colocado(boolean[] iNarc_colocado) {
        this.iNarc_colocado = iNarc_colocado;
    }
    
}
