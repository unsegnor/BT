/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaAndar;

import Aestrella.I_Coste;

/**
 *
 * @author Víctor
 */
public class CosteAndar implements I_Coste {

    public boolean imposible = false;
    
    private int puntos_de_movimiento = 0;
    public int chequeos_de_pilotaje = 0;
    
    //TODO anotar calor generado???

    CosteAndar(){}
    
    CosteAndar(int puntos) {
        puntos_de_movimiento = puntos;
    }
    
    
    @Override
    public I_Coste sumar(I_Coste coste) {
        
        //Casteamos el Coste a un CosteAndar
        CosteAndar otro = (CosteAndar) coste;
        
        CosteAndar respuesta = new CosteAndar();
        
        respuesta.setPuntos_de_movimiento(getPuntos_de_movimiento() + otro.getPuntos_de_movimiento());
        
        //Sumamos también los chequeos de pilotaje
        respuesta.chequeos_de_pilotaje += otro.chequeos_de_pilotaje;
        
        //Si alguno de los dos es imposible el resultado también
        if(imposible || otro.imposible){
            respuesta.imposible = true;
        }
        
        return respuesta;
    }

    @Override
    public int compareTo(I_Coste coste) {
        int respuesta = 0;
        
        //Castear el otro
        CosteAndar otro = (CosteAndar) coste;
        
        //Si es imposible se pone detrás
        if(this.imposible && !otro.imposible){
            respuesta = 1;
        }else if(!this.imposible && otro.imposible){
            respuesta = -1;
        }else if(this.imposible && otro.imposible){
            respuesta = 0;
        }else{
            respuesta = getPuntos_de_movimiento() - otro.getPuntos_de_movimiento();
            
            //Si empatan en puntos de movimiento miramos los chequeos de pilotaje
            if(respuesta == 0){
                respuesta = this.chequeos_de_pilotaje - otro.chequeos_de_pilotaje;
            }
        }
        
        return respuesta;
    }

    /**
     * @return the puntos_de_movimiento
     */
    public int getPuntos_de_movimiento() {
        return puntos_de_movimiento;
    }

    /**
     * @param puntos_de_movimiento the puntos_de_movimiento to set
     */
    public void setPuntos_de_movimiento(int puntos_de_movimiento) {
        this.puntos_de_movimiento = puntos_de_movimiento;
    }
    
}
