/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;
import bt.Reglas.*;
/**
 *
 * @author Víctor
 */
class DisparoArma {
    
    //Localización del arma (BD, BI...)
    Localizacion localizacion_arma;
    //Slot en el que se encuentra
    int slot_arma;
    //Va a disparar con doble cadencia?
    boolean dobleCadencia;
    //¿Dónde está la munición?
    Localizacion localizacion_municion;
    int slot_municion;
    //Hexágono al que dispara
    Phexagono objetivo;
    //Dispara al suelo, al mech o a ninguno?
    TiposObjetivo tipoObjetivo;
}
