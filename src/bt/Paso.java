/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.tiposDePaso;

/**
 * Representa un paso en la ruta del movimiento.
 * @author Víctor
 */
class Paso {

    /**
     * @return the tipoDePaso
     */
    public tiposDePaso getTipoDePaso() {
        return tipoDePaso;
    }

    /**
     * @param tipoDePaso the tipoDePaso to set
     */
    public void setTipoDePaso(tiposDePaso tipoDePaso) {
        this.tipoDePaso = tipoDePaso;
        if (this.tipoDePaso == tiposDePaso.CuerpoATierra){
            veces_o_lado = 1;
        }
    }

    /**
     * @return the veces_o_lado
     */
    public int getVeces_o_lado() {
        return veces_o_lado;
    }

    /**
     * @param veces_o_lado the veces_o_lado to set
     */
    public void setVeces_o_lado(int veces_o_lado) {
        this.veces_o_lado = veces_o_lado;
    }
    
    //Tipo de paso
    private tiposDePaso tipoDePaso;
    
    //Número de veces o Lado, debe ser 1 si es cuerpo a tierra, si es levantarse indica el lado
    private int veces_o_lado;
    
    @Override
    public String toString(){
    
        StringBuilder sb = new StringBuilder();
        
        switch(tipoDePaso){
            case Adelante:
                sb.append("Adelante");
            break;
            case Atras:
                sb.append("Atras");
            break;
            case Izquierda:
                sb.append("Izquierda");
            break;
            case Derecha:
                sb.append("Derecha");
            break;
            case Levantarse:
                sb.append("Levantarse");
            break;
            case CuerpoATierra:
                sb.append("Cuerpo A Tierra");
            break;      
        }
        sb.append("\n");
        sb.append(this.getVeces_o_lado());
        
        return sb.toString();
    }
    
}
