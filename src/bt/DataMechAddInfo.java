/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 *
 * @author Víctor
 */
class DataMechAddInfo {
    
    //Puntos de movimiento actuales para Andar
    private int puntos_andar;
    
    //Puntos para correr
    private int puntos_correr;
    
    //Puntos para saltar
    private int puntos_saltar;
    
    //Radiadores operativos encendidos
    private int radiadores_encendidos;
    
    //Radiadores operativos apagados
    private int radiadores_apagados;
    
    //Heridas MechWarrior
    private int heridas_warrior;
    
    //Warrior consciente
    private boolean warrior_consciente;
    
    //Slots impactados
    private boolean[] slots_impactados_brazo_izq = new boolean[12];
    private boolean[] slots_impactados_torso_izq = new boolean[12];
    private boolean[] slots_impactados_pierna_izq = new boolean[6];
    private boolean[] slots_impactados_pierna_der = new boolean[6];
    private boolean[] slots_impactados_torso_der = new boolean[12];
    private boolean[] slots_impactados_brazo_der = new boolean[12];
    private boolean[] slots_impactados_torso_central = new boolean[12];
    private boolean[] slots_impactados_cabeza = new boolean[6];
    
    //Localizaciones desde las que se ha disparado un arma
    private boolean disparo_de_brazo_izq;
    private boolean disparo_de_torso_izq;
    private boolean disparo_de_pierna_izq;
    private boolean disparo_de_pierna_der;
    private boolean disparo_de_torso_der;
    private boolean disparo_de_brazo_der;
    private boolean disparo_de_torso_central;
    private boolean disparo_de_cabeza;
    
    //Número de municiones preparadas para expulsar
    private int municiones_preparadas_para_expulsar;
    
    //Municiones a expulsar
    private DataMunicionAExpulsar[] municiones_a_expulsar;

    /**
     * @return the puntos_andar
     */
    public int getPuntos_andar() {
        return puntos_andar;
    }

    /**
     * @param puntos_andar the puntos_andar to set
     */
    public void setPuntos_andar(int puntos_andar) {
        this.puntos_andar = puntos_andar;
    }

    /**
     * @return the puntos_correr
     */
    public int getPuntos_correr() {
        return puntos_correr;
    }

    /**
     * @param puntos_correr the puntos_correr to set
     */
    public void setPuntos_correr(int puntos_correr) {
        this.puntos_correr = puntos_correr;
    }

    /**
     * @return the puntos_saltar
     */
    public int getPuntos_saltar() {
        return puntos_saltar;
    }

    /**
     * @param puntos_saltar the puntos_saltar to set
     */
    public void setPuntos_saltar(int puntos_saltar) {
        this.puntos_saltar = puntos_saltar;
    }

    /**
     * @return the radiadores_encendidos
     */
    public int getRadiadores_encendidos() {
        return radiadores_encendidos;
    }

    /**
     * @param radiadores_encendidos the radiadores_encendidos to set
     */
    public void setRadiadores_encendidos(int radiadores_encendidos) {
        this.radiadores_encendidos = radiadores_encendidos;
    }

    /**
     * @return the radiadores_apagados
     */
    public int getRadiadores_apagados() {
        return radiadores_apagados;
    }

    /**
     * @param radiadores_apagados the radiadores_apagados to set
     */
    public void setRadiadores_apagados(int radiadores_apagados) {
        this.radiadores_apagados = radiadores_apagados;
    }

    /**
     * @return the heridas_warrior
     */
    public int getHeridas_warrior() {
        return heridas_warrior;
    }

    /**
     * @param heridas_warrior the heridas_warrior to set
     */
    public void setHeridas_warrior(int heridas_warrior) {
        this.heridas_warrior = heridas_warrior;
    }

    /**
     * @return the warrior_consciente
     */
    public boolean isWarrior_consciente() {
        return warrior_consciente;
    }

    /**
     * @param warrior_consciente the warrior_consciente to set
     */
    public void setWarrior_consciente(boolean warrior_consciente) {
        this.warrior_consciente = warrior_consciente;
    }

    /**
     * @return the slots_impactados_brazo_izq
     */
    public boolean[] getSlots_impactados_brazo_izq() {
        return slots_impactados_brazo_izq;
    }

    /**
     * @param slots_impactados_brazo_izq the slots_impactados_brazo_izq to set
     */
    public void setSlots_impactados_brazo_izq(boolean[] slots_impactados_brazo_izq) {
        this.slots_impactados_brazo_izq = slots_impactados_brazo_izq;
    }

    /**
     * @return the slots_impactados_torso_izq
     */
    public boolean[] getSlots_impactados_torso_izq() {
        return slots_impactados_torso_izq;
    }

    /**
     * @param slots_impactados_torso_izq the slots_impactados_torso_izq to set
     */
    public void setSlots_impactados_torso_izq(boolean[] slots_impactados_torso_izq) {
        this.slots_impactados_torso_izq = slots_impactados_torso_izq;
    }

    /**
     * @return the slots_impactados_pierna_izq
     */
    public boolean[] getSlots_impactados_pierna_izq() {
        return slots_impactados_pierna_izq;
    }

    /**
     * @param slots_impactados_pierna_izq the slots_impactados_pierna_izq to set
     */
    public void setSlots_impactados_pierna_izq(boolean[] slots_impactados_pierna_izq) {
        this.slots_impactados_pierna_izq = slots_impactados_pierna_izq;
    }

    /**
     * @return the slots_impactados_pierna_der
     */
    public boolean[] getSlots_impactados_pierna_der() {
        return slots_impactados_pierna_der;
    }

    /**
     * @param slots_impactados_pierna_der the slots_impactados_pierna_der to set
     */
    public void setSlots_impactados_pierna_der(boolean[] slots_impactados_pierna_der) {
        this.slots_impactados_pierna_der = slots_impactados_pierna_der;
    }

    /**
     * @return the slots_impactados_torso_der
     */
    public boolean[] getSlots_impactados_torso_der() {
        return slots_impactados_torso_der;
    }

    /**
     * @param slots_impactados_torso_der the slots_impactados_torso_der to set
     */
    public void setSlots_impactados_torso_der(boolean[] slots_impactados_torso_der) {
        this.slots_impactados_torso_der = slots_impactados_torso_der;
    }

    /**
     * @return the slots_impactados_brazo_der
     */
    public boolean[] getSlots_impactados_brazo_der() {
        return slots_impactados_brazo_der;
    }

    /**
     * @param slots_impactados_brazo_der the slots_impactados_brazo_der to set
     */
    public void setSlots_impactados_brazo_der(boolean[] slots_impactados_brazo_der) {
        this.slots_impactados_brazo_der = slots_impactados_brazo_der;
    }

    /**
     * @return the slots_impactados_torso_central
     */
    public boolean[] getSlots_impactados_torso_central() {
        return slots_impactados_torso_central;
    }

    /**
     * @param slots_impactados_torso_central the slots_impactados_torso_central to set
     */
    public void setSlots_impactados_torso_central(boolean[] slots_impactados_torso_central) {
        this.slots_impactados_torso_central = slots_impactados_torso_central;
    }

    /**
     * @return the slots_impactados_cabeza
     */
    public boolean[] getSlots_impactados_cabeza() {
        return slots_impactados_cabeza;
    }

    /**
     * @param slots_impactados_cabeza the slots_impactados_cabeza to set
     */
    public void setSlots_impactados_cabeza(boolean[] slots_impactados_cabeza) {
        this.slots_impactados_cabeza = slots_impactados_cabeza;
    }

    /**
     * @return the disparo_de_brazo_izq
     */
    public boolean isDisparo_de_brazo_izq() {
        return disparo_de_brazo_izq;
    }

    /**
     * @param disparo_de_brazo_izq the disparo_de_brazo_izq to set
     */
    public void setDisparo_de_brazo_izq(boolean disparo_de_brazo_izq) {
        this.disparo_de_brazo_izq = disparo_de_brazo_izq;
    }

    /**
     * @return the disparo_de_torso_izq
     */
    public boolean isDisparo_de_torso_izq() {
        return disparo_de_torso_izq;
    }

    /**
     * @param disparo_de_torso_izq the disparo_de_torso_izq to set
     */
    public void setDisparo_de_torso_izq(boolean disparo_de_torso_izq) {
        this.disparo_de_torso_izq = disparo_de_torso_izq;
    }

    /**
     * @return the disparo_de_pierna_izq
     */
    public boolean isDisparo_de_pierna_izq() {
        return disparo_de_pierna_izq;
    }

    /**
     * @param disparo_de_pierna_izq the disparo_de_pierna_izq to set
     */
    public void setDisparo_de_pierna_izq(boolean disparo_de_pierna_izq) {
        this.disparo_de_pierna_izq = disparo_de_pierna_izq;
    }

    /**
     * @return the disparo_de_pierna_der
     */
    public boolean isDisparo_de_pierna_der() {
        return disparo_de_pierna_der;
    }

    /**
     * @param disparo_de_pierna_der the disparo_de_pierna_der to set
     */
    public void setDisparo_de_pierna_der(boolean disparo_de_pierna_der) {
        this.disparo_de_pierna_der = disparo_de_pierna_der;
    }

    /**
     * @return the disparo_de_torso_der
     */
    public boolean isDisparo_de_torso_der() {
        return disparo_de_torso_der;
    }

    /**
     * @param disparo_de_torso_der the disparo_de_torso_der to set
     */
    public void setDisparo_de_torso_der(boolean disparo_de_torso_der) {
        this.disparo_de_torso_der = disparo_de_torso_der;
    }

    /**
     * @return the disparo_de_brazo_der
     */
    public boolean isDisparo_de_brazo_der() {
        return disparo_de_brazo_der;
    }

    /**
     * @param disparo_de_brazo_der the disparo_de_brazo_der to set
     */
    public void setDisparo_de_brazo_der(boolean disparo_de_brazo_der) {
        this.disparo_de_brazo_der = disparo_de_brazo_der;
    }

    /**
     * @return the disparo_de_torso_central
     */
    public boolean isDisparo_de_torso_central() {
        return disparo_de_torso_central;
    }

    /**
     * @param disparo_de_torso_central the disparo_de_torso_central to set
     */
    public void setDisparo_de_torso_central(boolean disparo_de_torso_central) {
        this.disparo_de_torso_central = disparo_de_torso_central;
    }

    /**
     * @return the disparo_de_cabeza
     */
    public boolean isDisparo_de_cabeza() {
        return disparo_de_cabeza;
    }

    /**
     * @param disparo_de_cabeza the disparo_de_cabeza to set
     */
    public void setDisparo_de_cabeza(boolean disparo_de_cabeza) {
        this.disparo_de_cabeza = disparo_de_cabeza;
    }

    /**
     * @return the municiones_preparadas_para_expulsar
     */
    public int getMuniciones_preparadas_para_expulsar() {
        return municiones_preparadas_para_expulsar;
    }

    /**
     * @param municiones_preparadas_para_expulsar the municiones_preparadas_para_expulsar to set
     */
    public void setMuniciones_preparadas_para_expulsar(int municiones_preparadas_para_expulsar) {
        this.municiones_preparadas_para_expulsar = municiones_preparadas_para_expulsar;
    }

    /**
     * @return the municiones_a_expulsar
     */
    public DataMunicionAExpulsar[] getMuniciones_a_expulsar() {
        return municiones_a_expulsar;
    }

    /**
     * @param municiones_a_expulsar the municiones_a_expulsar to set
     */
    public void setMuniciones_a_expulsar(DataMunicionAExpulsar[] municiones_a_expulsar) {
        this.municiones_a_expulsar = municiones_a_expulsar;
    }    
}
