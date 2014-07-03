/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Almacena la información de un archivo defmech
 *
 * @author Víctor
 */
public class DataDefMech {

    /*
     * Texto	"defmechSBT" Texto	Nombre
     */ private String nombre;/*
     * Texto	Modelo
     */ private String modelo;/*
     * Entero	Toneladas
     */ private int toneladas;/*
     * Entero	Potencia
     */ private int potencia;/*
     * Entero	Número de Radiadores Internos
     */ private int nRadiadoresInternos;/*
     * Entero	Número de Radiadores
     */ private int nRadiadores;/*
     * Lógico	¿Tiene MASC?
     */ private boolean tieneMasc;/*
     * Lógico	¿DACMTD?
     */ private boolean DACMTD;/*
     * Lógico	¿DACMTI?
     */ private boolean DACMTI;/*
     * Lógico	¿DACMTC?
     */ private boolean DACMTC;/*
     * Entero	Máximo Calor Generado
     */ private int maxCalorGenerado;/*
     * Lógico	¿Con Brazos?
     */ private boolean conBrazos;/*
     * Lógico	¿Con Hombro Izquierdo?
     */ private boolean conHombroIzquierdo;/*
     * Lógico	¿Con Brazo Izquierdo?
     */ private boolean conBrazoIzquierdo;/*
     * Lógico	¿Con Antebrazo Izquierdo?
     */ private boolean conAntebrazoIzquierdo;/*
     * Lógico	¿Con Mano Izquierda?
     */ private boolean conManoIzquierda;/*
     * Lógico	¿Con Hombro Derecho?
     */ private boolean conHombroDerecho;/*
     * Lógico	¿Con Brazo Derecho?
     */ private boolean conBrazoDerecho;/*
     * Lógico	¿Con Antebrazo Derecho?
     */ private boolean conAntebrazoDerecho;/*
     * Lógico	¿Con Mano Derecha?
     */ private boolean conManoDerecha;/*
     * Entero	Blindaje brazo izquierdo
     */ private int blindajeBrazoIzquierdo;/*
     * Entero	Blindaje torso izquierdo
     */ private int blindajeTorsoIzquierdo;/*
     * Entero	Blindaje pierna izquierda
     */ private int blindajePiernaIzquierda;/*
     * Entero	Blindaje pierna derecha
     */ private int blindajePiernaDerecha;/*
     * Entero	Blindaje torso derecho
     */ private int blindajeTorsoDerecho;/*
     * Entero	Blindaje brazo derecho
     */ private int blindajeBrazoDerecho;/*
     * Entero	Blindaje torso central
     */ private int blindajeTorsoCentral;/*
     * Entero	Blindaje cabeza
     */ private int blindajeCabeza;/*
     * Entero	Blindaje parte de atrás del torso izquierdo
     */ private int blindajeAtrasTorsoIzquierdo;/*
     * Entero	Blindaje parte de atrás del torso derecho
     */ private int blindajeAtrasTorsoDerecho;/*
     * Entero	Blindaje parte de atrás del torso central
     */ private int blindajeAtrasTorsoCentral;/*
     * Entero	Puntos estructura interna brazo izquierdo
     */ private int puntosInternosBrazoIzquierdo;/*
     * Entero	Puntos estructura interna torso izquierdo
     */ private int puntosInternosTorsoIzquierdo;/*
     * Entero	Puntos estructura interna pierna izquierda
     */ private int puntosInternosPiernaIzquierda;/*
     * Entero	Puntos estructura interna pierna derecha
     */ private int puntosInternosPiernaDerecha;/*
     * Entero	Puntos estructura interna torso derecho
     */ private int puntosInternosTorsoDerecho;/*
     * Entero	Puntos estructura interna brazo derecho
     */ private int puntosInternosBrazoDerecho;/*
     * Entero	Puntos estructura interna torso central
     */ private int puntosInternosTorsoCentral;/*
     * Entero	Puntos estructura interna cabeza
     */ private int puntosInternosCabeza;/*
     * Entero	Número de componentes equipados
     */ private DataComponente[] componentesEquipados;/*
     * Entero	Número de armas
     */ private int nArmas;/*
     * Entero	Número de actuadores
     */ private DataActuador[] actuadores;/*
     * Localizaciones
     */ private DataLocalizacion[] localizacion;/*
     * Entero	Puntos de movimiento andando
     */ private int puntosMovAndando;/*
     * Entero	Puntos de movimiento corriendo
     */ private int puntosMovCorriendo;/*
     * Entero	Puntos de movimiento saltando
     */ private int puntosMovSaltando;/*
     * Entero	Tipo de radiadores (0=Simple,1=Doble)
     */ private int tipoRadiadores;/*
     */


    public void leer(BufferedReader br) throws IOException {
        /*
         * Texto	Nombre
         */
        this.setNombre(leer.s(br));/*
         * Texto	Modelo
         */ this.setModelo(leer.s(br));/*
         * Entero	Toneladas
         */ this.setToneladas(leer.i(br));/*
         * Entero	Potencia
         */ this.setPotencia(leer.i(br));/*
         * Entero	Número de Radiadores Internos
         */ this.setnRadiadoresInternos(leer.i(br));/*
         * Entero	Número de Radiadores
         */ this.setnRadiadores(leer.i(br));/*
         * Lógico	¿Tiene MASC?
         */ this.setTieneMasc(leer.b(br));/*
         * Lógico	¿DACMTD?
         */ this.setDACMTD(leer.b(br));/*
         * Lógico	¿DACMTI?
         */ this.setDACMTI(leer.b(br));/*
         * Lógico	¿DACMTC?
         */ this.setDACMTC(leer.b(br));/*
         * Entero	Máximo Calor Generado
         */ this.setMaxCalorGenerado(leer.i(br));/*
         * Lógico	¿Con Brazos?
         */ this.setConBrazos(leer.b(br));/*
         * Lógico	¿Con Hombro Izquierdo?
         */ this.setConHombroIzquierdo(leer.b(br));/*
         * Lógico	¿Con Brazo Izquierdo?
         */ this.setConBrazoIzquierdo(leer.b(br));/*
         * Lógico	¿Con Antebrazo Izquierdo?
         */ this.setConAntebrazoIzquierdo(leer.b(br));/*
         * Lógico	¿Con Mano Izquierda?
         */ this.setConManoIzquierda(leer.b(br));/*
         * Lógico	¿Con Hombro Derecho?
         */ this.setConHombroDerecho(leer.b(br));/*
         * Lógico	¿Con Brazo Derecho?
         */ this.setConBrazoDerecho(leer.b(br));/*
         * Lógico	¿Con Antebrazo Derecho?
         */ this.setConAntebrazoDerecho(leer.b(br));/*
         * Lógico	¿Con Mano Derecha?
         */ this.setConManoDerecha(leer.b(br));/*
         * Entero	Blindaje brazo izquierdo
         */ this.setBlindajeBrazoIzquierdo(leer.i(br));/*
         * Entero	Blindaje torso izquierdo
         */ this.setBlindajeTorsoIzquierdo(leer.i(br));/*
         * Entero	Blindaje pierna izquierda
         */ this.setBlindajePiernaIzquierda(leer.i(br));/*
         * Entero	Blindaje pierna derecha
         */ this.setBlindajePiernaDerecha(leer.i(br));/*
         * Entero	Blindaje torso derecho
         */ this.setBlindajeTorsoDerecho(leer.i(br));/*
         * Entero	Blindaje brazo derecho
         */ this.setBlindajeBrazoDerecho(leer.i(br));/*
         * Entero	Blindaje torso central
         */ this.setBlindajeTorsoCentral(leer.i(br));/*
         * Entero	Blindaje cabeza
         */ this.setBlindajeCabeza(leer.i(br));/*
         * Entero	Blindaje parte de atrás del torso izquierdo
         */ this.setBlindajeAtrasTorsoIzquierdo(leer.i(br));/*
         * Entero	Blindaje parte de atrás del torso derecho
         */ this.setBlindajeAtrasTorsoDerecho(leer.i(br));/*
         * Entero	Blindaje parte de atrás del torso central
         */ this.setBlindajeAtrasTorsoCentral(leer.i(br));/*
         * Entero	Puntos estructura interna brazo izquierdo
         */ this.setPuntosInternosBrazoIzquierdo(leer.i(br));/*
         * Entero	Puntos estructura interna torso izquierdo
         */ this.setPuntosInternosTorsoIzquierdo(leer.i(br));/*
         * Entero	Puntos estructura interna pierna izquierda
         */ this.setPuntosInternosPiernaIzquierda(leer.i(br));/*
         * Entero	Puntos estructura interna pierna derecha
         */ this.setPuntosInternosPiernaDerecha(leer.i(br));/*
         * Entero	Puntos estructura interna torso derecho
         */ this.setPuntosInternosTorsoDerecho(leer.i(br));/*
         * Entero	Puntos estructura interna brazo derecho
         */ this.setPuntosInternosBrazoDerecho(leer.i(br));/*
         * Entero	Puntos estructura interna torso central
         */ this.setPuntosInternosTorsoCentral(leer.i(br));/*
         * Entero	Puntos estructura interna cabeza
         */ this.setPuntosInternosCabeza(leer.i(br));/*
         * Entero	Número de componentes equipados
         */ int nComponentes = leer.i(br);/*
         * Para cada componente
         */
        this.componentesEquipados = new DataComponente[nComponentes];
        for (int i = 0; i < nComponentes; i++) {
            DataComponente nuevo = new DataComponente();
            nuevo.leer(br);
            componentesEquipados[i] = nuevo;
        }/*
         * Entero	Número de armas
         */ this.setnArmas(leer.i(br));/*
         * Entero	Número de actuadores
         */ int nActuadores = leer.i(br);/*
         * Para cada actuador
         */
        this.actuadores = new DataActuador[nActuadores];
        for (int i = 0; i < nActuadores; i++) {
            DataActuador nuevo = new DataActuador();
            nuevo.leer(br);
            actuadores[i] = nuevo;
        }
        /*
         * Para cada localización (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB)
*
         */
        int nLocalizaciones = 8;
        this.localizacion = new DataLocalizacion[nLocalizaciones];
        for (int i = 0; i < nLocalizaciones; i++) {
            DataLocalizacion nueva = new DataLocalizacion();
            nueva.leer(br);
            localizacion[i] = nueva;
        }/*
         * Entero	Puntos de movimiento andando
         */ this.setPuntosMovAndando(leer.i(br));/*
         * Entero	Puntos de movimiento corriendo
         */ this.setPuntosMovCorriendo(leer.i(br));/*
         * Entero	Puntos de movimiento saltando
         */ this.setPuntosMovSaltando(leer.i(br));/*
         * Entero	Tipo de radiadores (0=Simple,1=Doble)
         */ this.setTipoRadiadores(leer.i(br));
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
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the toneladas
     */
    public int getToneladas() {
        return toneladas;
    }

    /**
     * @param toneladas the toneladas to set
     */
    public void setToneladas(int toneladas) {
        this.toneladas = toneladas;
    }

    /**
     * @return the potencia
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * @param potencia the potencia to set
     */
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    /**
     * @return the nRadiadoresInternos
     */
    public int getnRadiadoresInternos() {
        return nRadiadoresInternos;
    }

    /**
     * @param nRadiadoresInternos the nRadiadoresInternos to set
     */
    public void setnRadiadoresInternos(int nRadiadoresInternos) {
        this.nRadiadoresInternos = nRadiadoresInternos;
    }

    /**
     * @return the nRadiadores
     */
    public int getnRadiadores() {
        return nRadiadores;
    }

    /**
     * @param nRadiadores the nRadiadores to set
     */
    public void setnRadiadores(int nRadiadores) {
        this.nRadiadores = nRadiadores;
    }

    /**
     * @return the tieneMasc
     */
    public boolean isTieneMasc() {
        return tieneMasc;
    }

    /**
     * @param tieneMasc the tieneMasc to set
     */
    public void setTieneMasc(boolean tieneMasc) {
        this.tieneMasc = tieneMasc;
    }

    /**
     * @return the DACMTD
     */
    public boolean isDACMTD() {
        return DACMTD;
    }

    /**
     * @param DACMTD the DACMTD to set
     */
    public void setDACMTD(boolean DACMTD) {
        this.DACMTD = DACMTD;
    }

    /**
     * @return the DACMTI
     */
    public boolean isDACMTI() {
        return DACMTI;
    }

    /**
     * @param DACMTI the DACMTI to set
     */
    public void setDACMTI(boolean DACMTI) {
        this.DACMTI = DACMTI;
    }

    /**
     * @return the DACMTC
     */
    public boolean isDACMTC() {
        return DACMTC;
    }

    /**
     * @param DACMTC the DACMTC to set
     */
    public void setDACMTC(boolean DACMTC) {
        this.DACMTC = DACMTC;
    }

    /**
     * @return the maxCalorGenerado
     */
    public int getMaxCalorGenerado() {
        return maxCalorGenerado;
    }

    /**
     * @param maxCalorGenerado the maxCalorGenerado to set
     */
    public void setMaxCalorGenerado(int maxCalorGenerado) {
        this.maxCalorGenerado = maxCalorGenerado;
    }

    /**
     * @return the conBrazos
     */
    public boolean isConBrazos() {
        return conBrazos;
    }

    /**
     * @param conBrazos the conBrazos to set
     */
    public void setConBrazos(boolean conBrazos) {
        this.conBrazos = conBrazos;
    }

    /**
     * @return the conHombroIzquierdo
     */
    public boolean isConHombroIzquierdo() {
        return conHombroIzquierdo;
    }

    /**
     * @param conHombroIzquierdo the conHombroIzquierdo to set
     */
    public void setConHombroIzquierdo(boolean conHombroIzquierdo) {
        this.conHombroIzquierdo = conHombroIzquierdo;
    }

    /**
     * @return the conBrazoIzquiero
     */
    public boolean isConBrazoIzquierdo() {
        return conBrazoIzquierdo;
    }

    /**
     * @param conBrazoIzquiero the conBrazoIzquiero to set
     */
    public void setConBrazoIzquierdo(boolean conBrazoIzquierdo) {
        this.conBrazoIzquierdo = conBrazoIzquierdo;
    }

    /**
     * @return the conAntebrazoIzquierdo
     */
    public boolean isConAntebrazoIzquierdo() {
        return conAntebrazoIzquierdo;
    }

    /**
     * @param conAntebrazoIzquierdo the conAntebrazoIzquierdo to set
     */
    public void setConAntebrazoIzquierdo(boolean conAntebrazoIzquierdo) {
        this.conAntebrazoIzquierdo = conAntebrazoIzquierdo;
    }

    /**
     * @return the conManoIzquierda
     */
    public boolean isConManoIzquierda() {
        return conManoIzquierda;
    }

    /**
     * @param conManoIzquierda the conManoIzquierda to set
     */
    public void setConManoIzquierda(boolean conManoIzquierda) {
        this.conManoIzquierda = conManoIzquierda;
    }

    /**
     * @return the conHombroDerecho
     */
    public boolean isConHombroDerecho() {
        return conHombroDerecho;
    }

    /**
     * @param conHombroDerecho the conHombroDerecho to set
     */
    public void setConHombroDerecho(boolean conHombroDerecho) {
        this.conHombroDerecho = conHombroDerecho;
    }

    /**
     * @return the conBrazoDerecho
     */
    public boolean isConBrazoDerecho() {
        return conBrazoDerecho;
    }

    /**
     * @param conBrazoDerecho the conBrazoDerecho to set
     */
    public void setConBrazoDerecho(boolean conBrazoDerecho) {
        this.conBrazoDerecho = conBrazoDerecho;
    }

    /**
     * @return the conAntebrazoDerecho
     */
    public boolean isConAntebrazoDerecho() {
        return conAntebrazoDerecho;
    }

    /**
     * @param conAntebrazoDerecho the conAntebrazoDerecho to set
     */
    public void setConAntebrazoDerecho(boolean conAntebrazoDerecho) {
        this.conAntebrazoDerecho = conAntebrazoDerecho;
    }

    /**
     * @return the conManoDerecha
     */
    public boolean isConManoDerecha() {
        return conManoDerecha;
    }

    /**
     * @param conManoDerecha the conManoDerecha to set
     */
    public void setConManoDerecha(boolean conManoDerecha) {
        this.conManoDerecha = conManoDerecha;
    }

    /**
     * @return the blindajeBrazoIzquierdo
     */
    public int getBlindajeBrazoIzquierdo() {
        return blindajeBrazoIzquierdo;
    }

    /**
     * @param blindajeBrazoIzquierdo the blindajeBrazoIzquierdo to set
     */
    public void setBlindajeBrazoIzquierdo(int blindajeBrazoIzquierdo) {
        this.blindajeBrazoIzquierdo = blindajeBrazoIzquierdo;
    }

    /**
     * @return the blindajeTorsoIzquierdo
     */
    public int getBlindajeTorsoIzquierdo() {
        return blindajeTorsoIzquierdo;
    }

    /**
     * @param blindajeTorsoIzquierdo the blindajeTorsoIzquierdo to set
     */
    public void setBlindajeTorsoIzquierdo(int blindajeTorsoIzquierdo) {
        this.blindajeTorsoIzquierdo = blindajeTorsoIzquierdo;
    }

    /**
     * @return the blindajePiernaIzquierda
     */
    public int getBlindajePiernaIzquierda() {
        return blindajePiernaIzquierda;
    }

    /**
     * @param blindajePiernaIzquierda the blindajePiernaIzquierda to set
     */
    public void setBlindajePiernaIzquierda(int blindajePiernaIzquierda) {
        this.blindajePiernaIzquierda = blindajePiernaIzquierda;
    }

    /**
     * @return the blindajePiernaDerecha
     */
    public int getBlindajePiernaDerecha() {
        return blindajePiernaDerecha;
    }

    /**
     * @param blindajePiernaDerecha the blindajePiernaDerecha to set
     */
    public void setBlindajePiernaDerecha(int blindajePiernaDerecha) {
        this.blindajePiernaDerecha = blindajePiernaDerecha;
    }

    /**
     * @return the blindajeTorsoDerecho
     */
    public int getBlindajeTorsoDerecho() {
        return blindajeTorsoDerecho;
    }

    /**
     * @param blindajeTorsoDerecho the blindajeTorsoDerecho to set
     */
    public void setBlindajeTorsoDerecho(int blindajeTorsoDerecho) {
        this.blindajeTorsoDerecho = blindajeTorsoDerecho;
    }

    /**
     * @return the blindajeBrazoDerecho
     */
    public int getBlindajeBrazoDerecho() {
        return blindajeBrazoDerecho;
    }

    /**
     * @param blindajeBrazoDerecho the blindajeBrazoDerecho to set
     */
    public void setBlindajeBrazoDerecho(int blindajeBrazoDerecho) {
        this.blindajeBrazoDerecho = blindajeBrazoDerecho;
    }

    /**
     * @return the blindajeTorsoCentral
     */
    public int getBlindajeTorsoCentral() {
        return blindajeTorsoCentral;
    }

    /**
     * @param blindajeTorsoCentral the blindajeTorsoCentral to set
     */
    public void setBlindajeTorsoCentral(int blindajeTorsoCentral) {
        this.blindajeTorsoCentral = blindajeTorsoCentral;
    }

    /**
     * @return the blindajeCabeza
     */
    public int getBlindajeCabeza() {
        return blindajeCabeza;
    }

    /**
     * @param blindajeCabeza the blindajeCabeza to set
     */
    public void setBlindajeCabeza(int blindajeCabeza) {
        this.blindajeCabeza = blindajeCabeza;
    }

    /**
     * @return the blindajeAtrasTorsoIzquierdo
     */
    public int getBlindajeAtrasTorsoIzquierdo() {
        return blindajeAtrasTorsoIzquierdo;
    }

    /**
     * @param blindajeAtrasTorsoIzquierdo the blindajeAtrasTorsoIzquierdo to set
     */
    public void setBlindajeAtrasTorsoIzquierdo(int blindajeAtrasTorsoIzquierdo) {
        this.blindajeAtrasTorsoIzquierdo = blindajeAtrasTorsoIzquierdo;
    }

    /**
     * @return the blindajeAtrasTorsoDerecho
     */
    public int getBlindajeAtrasTorsoDerecho() {
        return blindajeAtrasTorsoDerecho;
    }

    /**
     * @param blindajeAtrasTorsoDerecho the blindajeAtrasTorsoDerecho to set
     */
    public void setBlindajeAtrasTorsoDerecho(int blindajeAtrasTorsoDerecho) {
        this.blindajeAtrasTorsoDerecho = blindajeAtrasTorsoDerecho;
    }

    /**
     * @return the blindajeAtrasTorsoCentral
     */
    public int getBlindajeAtrasTorsoCentral() {
        return blindajeAtrasTorsoCentral;
    }

    /**
     * @param blindajeAtrasTorsoCentral the blindajeAtrasTorsoCentral to set
     */
    public void setBlindajeAtrasTorsoCentral(int blindajeAtrasTorsoCentral) {
        this.blindajeAtrasTorsoCentral = blindajeAtrasTorsoCentral;
    }

    /**
     * @return the puntosInternosBrazoIzquierdo
     */
    public int getPuntosInternosBrazoIzquierdo() {
        return puntosInternosBrazoIzquierdo;
    }

    /**
     * @param puntosInternosBrazoIzquierdo the puntosInternosBrazoIzquierdo to
     * set
     */
    public void setPuntosInternosBrazoIzquierdo(int puntosInternosBrazoIzquierdo) {
        this.puntosInternosBrazoIzquierdo = puntosInternosBrazoIzquierdo;
    }

    /**
     * @return the puntosInternosTorsoIzquierdo
     */
    public int getPuntosInternosTorsoIzquierdo() {
        return puntosInternosTorsoIzquierdo;
    }

    /**
     * @param puntosInternosTorsoIzquierdo the puntosInternosTorsoIzquierdo to
     * set
     */
    public void setPuntosInternosTorsoIzquierdo(int puntosInternosTorsoIzquierdo) {
        this.puntosInternosTorsoIzquierdo = puntosInternosTorsoIzquierdo;
    }

    /**
     * @return the puntosInternosPiernaIzquierda
     */
    public int getPuntosInternosPiernaIzquierda() {
        return puntosInternosPiernaIzquierda;
    }

    /**
     * @param puntosInternosPiernaIzquierda the puntosInternosPiernaIzquierda to
     * set
     */
    public void setPuntosInternosPiernaIzquierda(int puntosInternosPiernaIzquierda) {
        this.puntosInternosPiernaIzquierda = puntosInternosPiernaIzquierda;
    }

    /**
     * @return the puntosInternosPiernaDerecha
     */
    public int getPuntosInternosPiernaDerecha() {
        return puntosInternosPiernaDerecha;
    }

    /**
     * @param puntosInternosPiernaDerecha the puntosInternosPiernaDerecha to set
     */
    public void setPuntosInternosPiernaDerecha(int puntosInternosPiernaDerecha) {
        this.puntosInternosPiernaDerecha = puntosInternosPiernaDerecha;
    }

    /**
     * @return the puntosInternosTorsoDerecho
     */
    public int getPuntosInternosTorsoDerecho() {
        return puntosInternosTorsoDerecho;
    }

    /**
     * @param puntosInternosTorsoDerecho the puntosInternosTorsoDerecho to set
     */
    public void setPuntosInternosTorsoDerecho(int puntosInternosTorsoDerecho) {
        this.puntosInternosTorsoDerecho = puntosInternosTorsoDerecho;
    }

    /**
     * @return the puntosInternosBrazoDerecho
     */
    public int getPuntosInternosBrazoDerecho() {
        return puntosInternosBrazoDerecho;
    }

    /**
     * @param puntosInternosBrazoDerecho the puntosInternosBrazoDerecho to set
     */
    public void setPuntosInternosBrazoDerecho(int puntosInternosBrazoDerecho) {
        this.puntosInternosBrazoDerecho = puntosInternosBrazoDerecho;
    }

    /**
     * @return the puntosInternosTorsoCentral
     */
    public int getPuntosInternosTorsoCentral() {
        return puntosInternosTorsoCentral;
    }

    /**
     * @param puntosInternosTorsoCentral the puntosInternosTorsoCentral to set
     */
    public void setPuntosInternosTorsoCentral(int puntosInternosTorsoCentral) {
        this.puntosInternosTorsoCentral = puntosInternosTorsoCentral;
    }

    /**
     * @return the puntosInternosCabeza
     */
    public int getPuntosInternosCabeza() {
        return puntosInternosCabeza;
    }

    /**
     * @param puntosInternosCabeza the puntosInternosCabeza to set
     */
    public void setPuntosInternosCabeza(int puntosInternosCabeza) {
        this.puntosInternosCabeza = puntosInternosCabeza;
    }

    /**
     * @return the componentesEquipados
     */
    public DataComponente[] getComponentesEquipados() {
        return componentesEquipados;
    }

    /**
     * @param componentesEquipados the componentesEquipados to set
     */
    public void setComponentesEquipados(DataComponente[] componentesEquipados) {
        this.componentesEquipados = componentesEquipados;
    }

    /**
     * @return the nArmas
     */
    public int getnArmas() {
        return nArmas;
    }

    /**
     * @param nArmas the nArmas to set
     */
    public void setnArmas(int nArmas) {
        this.nArmas = nArmas;
    }

    /**
     * @return the actuadores
     */
    public DataActuador[] getActuadores() {
        return actuadores;
    }

    /**
     * @param actuadores the actuadores to set
     */
    public void setActuadores(DataActuador[] actuadores) {
        this.actuadores = actuadores;
    }

    /**
     * @return the localizacion (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB)
     */
    public DataLocalizacion[] getLocalizacion() {
        return localizacion;
    }

    /**
     * @param localizacion the localizacion to set
     */
    public void setLocalizacion(DataLocalizacion[] localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * @return the puntosMovAndando
     */
    public int getPuntosMovAndando() {
        return puntosMovAndando;
    }

    /**
     * @param puntosMovAndando the puntosMovAndando to set
     */
    public void setPuntosMovAndando(int puntosMovAndando) {
        this.puntosMovAndando = puntosMovAndando;
    }

    /**
     * @return the puntosMovCorriendo
     */
    public int getPuntosMovCorriendo() {
        return puntosMovCorriendo;
    }

    /**
     * @param puntosMovCorriendo the puntosMovCorriendo to set
     */
    public void setPuntosMovCorriendo(int puntosMovCorriendo) {
        this.puntosMovCorriendo = puntosMovCorriendo;
    }

    /**
     * @return the puntosMovSaltando
     */
    public int getPuntosMovSaltando() {
        return puntosMovSaltando;
    }

    /**
     * @param puntosMovSaltando the puntosMovSaltando to set
     */
    public void setPuntosMovSaltando(int puntosMovSaltando) {
        this.puntosMovSaltando = puntosMovSaltando;
    }

    /**
     * @return the tipoRadiadores
     */
    public int getTipoRadiadores() {
        return tipoRadiadores;
    }

    /**
     * @param tipoRadiadores the tipoRadiadores to set
     */
    public void setTipoRadiadores(int tipoRadiadores) {
        this.tipoRadiadores = tipoRadiadores;
    }
}
