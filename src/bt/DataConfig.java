/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 *
 * @author Víctor
 */
class DataConfig {
    
    //Indica si los incendios están permitidos
    private boolean IncendiosPermitidos;
    
    //Indica si hay viento en la partida
    private boolean viento;
    
    //Indica la dirección del viento, es un entero entre 1 y 6 que indica la cara de los hexágonos hacia la que sopla el viento.
    private int direccion_viento;
    
    //Indica si los ataques físicos están permitidos
    private boolean ataquesFisicosPermitidos;
    
    //Indica se se contemplan los efectos del calor en la partida
    private boolean faseDeCalor;
    
    //Indica si se pueden devastar bosques
    private boolean devastarBosquesPermitido;
    
    //Indica si se pueden derrumbar edificios
    private boolean derrumbarEdificiosPermitido;
    
    //Indica si se realizan chequeos de pilotaje
    private boolean chequeosDePilotaje;
    
    //Indica si se realizará un chequeo de pilotaje si un BattleMech recibe más de 20 puntos de daño en un turno.
    private boolean chequeoDamageEnTurno;
    
    //Indica si se realizan chequeos para determinar si un BattleMech se desconecta a causa del calor.
    private boolean chequeoDesconexion;
    
    //Indica si están permitidos los impactos críticos
    private boolean impactosCriticosPermitidos;
    
    //Indica si se pueden producir explosiones de munición.
    private boolean explosionDeMunicionPermitida;
    
    //Indica si se permite apagar radiadores
    private boolean apagarRadiadoresPermitido;
    
    //Indica si la respuesta de jugadores automáticos tiene ímite de tiempo
    private boolean tiempoDeRespuestaLimitado;
    
    //Especifica el límite de tiempo de respuesta para jugadores automáticos, en segundos
    private int tiempoLimiteDeRespuesta;

    /**
     * @return the IncendiosPermitidos
     */
    public boolean isIncendiosPermitidos() {
        return IncendiosPermitidos;
    }

    /**
     * @param IncendiosPermitidos the IncendiosPermitidos to set
     */
    public void setIncendiosPermitidos(boolean IncendiosPermitidos) {
        this.IncendiosPermitidos = IncendiosPermitidos;
    }

    /**
     * @return the viento
     */
    public boolean isViento() {
        return viento;
    }

    /**
     * @param viento the viento to set
     */
    public void setViento(boolean viento) {
        this.viento = viento;
    }

    /**
     * @return the direccion_viento
     */
    public int getDireccion_viento() {
        return direccion_viento;
    }

    /**
     * @param direccion_viento the direccion_viento to set
     */
    public void setDireccion_viento(int direccion_viento) {
        this.direccion_viento = direccion_viento;
    }

    /**
     * @return the ataquesFisicosPermitidos
     */
    public boolean isAtaquesFisicosPermitidos() {
        return ataquesFisicosPermitidos;
    }

    /**
     * @param ataquesFisicosPermitidos the ataquesFisicosPermitidos to set
     */
    public void setAtaquesFisicosPermitidos(boolean ataquesFisicosPermitidos) {
        this.ataquesFisicosPermitidos = ataquesFisicosPermitidos;
    }

    /**
     * @return the faseDeCalor
     */
    public boolean isFaseDeCalor() {
        return faseDeCalor;
    }

    /**
     * @param faseDeCalor the faseDeCalor to set
     */
    public void setFaseDeCalor(boolean faseDeCalor) {
        this.faseDeCalor = faseDeCalor;
    }

    /**
     * @return the devastarBosquesPermitido
     */
    public boolean isDevastarBosquesPermitido() {
        return devastarBosquesPermitido;
    }

    /**
     * @param devastarBosquesPermitido the devastarBosquesPermitido to set
     */
    public void setDevastarBosquesPermitido(boolean devastarBosquesPermitido) {
        this.devastarBosquesPermitido = devastarBosquesPermitido;
    }

    /**
     * @return the derrumbarEdificiosPermitido
     */
    public boolean isDerrumbarEdificiosPermitido() {
        return derrumbarEdificiosPermitido;
    }

    /**
     * @param derrumbarEdificiosPermitido the derrumbarEdificiosPermitido to set
     */
    public void setDerrumbarEdificiosPermitido(boolean derrumbarEdificiosPermitido) {
        this.derrumbarEdificiosPermitido = derrumbarEdificiosPermitido;
    }

    /**
     * @return the chequeosDePilotaje
     */
    public boolean isChequeosDePilotaje() {
        return chequeosDePilotaje;
    }

    /**
     * @param chequeosDePilotaje the chequeosDePilotaje to set
     */
    public void setChequeosDePilotaje(boolean chequeosDePilotaje) {
        this.chequeosDePilotaje = chequeosDePilotaje;
    }

    /**
     * @return the chequeoDamageEnTurno
     */
    public boolean isChequeoDamageEnTurno() {
        return chequeoDamageEnTurno;
    }

    /**
     * @param chequeoDamageEnTurno the chequeoDamageEnTurno to set
     */
    public void setChequeoDamageEnTurno(boolean chequeoDamageEnTurno) {
        this.chequeoDamageEnTurno = chequeoDamageEnTurno;
    }

    /**
     * @return the chequeoDesconexion
     */
    public boolean isChequeoDesconexion() {
        return chequeoDesconexion;
    }

    /**
     * @param chequeoDesconexion the chequeoDesconexion to set
     */
    public void setChequeoDesconexion(boolean chequeoDesconexion) {
        this.chequeoDesconexion = chequeoDesconexion;
    }

    /**
     * @return the impactosCriticosPermitidos
     */
    public boolean isImpactosCriticosPermitidos() {
        return impactosCriticosPermitidos;
    }

    /**
     * @param impactosCriticosPermitidos the impactosCriticosPermitidos to set
     */
    public void setImpactosCriticosPermitidos(boolean impactosCriticosPermitidos) {
        this.impactosCriticosPermitidos = impactosCriticosPermitidos;
    }

    /**
     * @return the explosionDeMunicionPermitida
     */
    public boolean isExplosionDeMunicionPermitida() {
        return explosionDeMunicionPermitida;
    }

    /**
     * @param explosionDeMunicionPermitida the explosionDeMunicionPermitida to set
     */
    public void setExplosionDeMunicionPermitida(boolean explosionDeMunicionPermitida) {
        this.explosionDeMunicionPermitida = explosionDeMunicionPermitida;
    }

    /**
     * @return the apagarRadiadoresPermitido
     */
    public boolean isApagarRadiadoresPermitido() {
        return apagarRadiadoresPermitido;
    }

    /**
     * @param apagarRadiadoresPermitido the apagarRadiadoresPermitido to set
     */
    public void setApagarRadiadoresPermitido(boolean apagarRadiadoresPermitido) {
        this.apagarRadiadoresPermitido = apagarRadiadoresPermitido;
    }

    /**
     * @return the tiempoDeRespuestaLimitado
     */
    public boolean isTiempoDeRespuestaLimitado() {
        return tiempoDeRespuestaLimitado;
    }

    /**
     * @param tiempoDeRespuestaLimitado the tiempoDeRespuestaLimitado to set
     */
    public void setTiempoDeRespuestaLimitado(boolean tiempoDeRespuestaLimitado) {
        this.tiempoDeRespuestaLimitado = tiempoDeRespuestaLimitado;
    }

    /**
     * @return the tiempoLimiteDeRespuesta
     */
    public int getTiempoLimiteDeRespuesta() {
        return tiempoLimiteDeRespuesta;
    }

    /**
     * @param tiempoLimiteDeRespuesta the tiempoLimiteDeRespuesta to set
     */
    public void setTiempoLimiteDeRespuesta(int tiempoLimiteDeRespuesta) {
        this.tiempoLimiteDeRespuesta = tiempoLimiteDeRespuesta;
    }
}
