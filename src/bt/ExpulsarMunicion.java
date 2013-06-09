/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Localizacion;

/**
 *
 * @author Víctor
 */
class ExpulsarMunicion {
    
    Localizacion localizacion;
    int slot;
    
    public ExpulsarMunicion(){}
    
    public ExpulsarMunicion(Localizacion localizacion, int slot){
        this.localizacion = localizacion;
        this.slot = slot;
    }
    
}
