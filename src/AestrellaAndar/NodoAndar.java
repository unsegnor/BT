/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaAndar;

import Aestrella.Nodo;
import bt.Paso;
import bt.Posicion;

/**
 *
 * @author Víctor
 */
public class NodoAndar extends Nodo {

    //Identificador de la posicion a la que representa
    private Posicion p;
    private Paso paso;

    NodoAndar(Posicion posicion) {
        this.p = posicion;
        this.setCosteReal(new CosteAndar(0));
        this.setCosteHeuristico(new CosteAndar(0));
        this.setCosteTotal(new CosteAndar(0));
    }

    /**
     * @return the p
     */
    public Posicion getPosicion() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setPosicion(Posicion posicion) {
        this.p = posicion;
    }

    /**
     * @return the paso
     */
    public Paso getPaso() {
        return paso;
    }

    /**
     * @param paso the paso to set
     */
    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    @Override
    public int hashCode() {
        return p.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NodoAndar)) {
            return false;
        }

        NodoAndar rhs = (NodoAndar) obj;

        return (p.equals(rhs.p));
    }
}
