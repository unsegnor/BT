/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Información sobre la munición que se va a expulsar: localización y slot
 * @author Víctor
 */
class DataMunicionAExpulsar {
    
    //Localizacion (BI, BD, PI, PD, TC, TI, TD, CAB)
    private String localizacion;
    
    //Slot de la munición en la localización
    private int slot;

    /**
     * @return the localizacion
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * @param localizacion the localizacion to set
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * @return the slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * @param slot the slot to set
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }
    
}
