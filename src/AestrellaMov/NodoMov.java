/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaMov;

import Aestrella.Nodo;
import bt.Paso;
import bt.Posicion;

/**
 *
 * @author Víctor
 */
public class NodoMov extends Nodo {

    //Identificador de la posicion a la que representa
    private Posicion p;
    private Paso paso;

    public NodoMov(Posicion posicion) {
        this.p = posicion;
        this.setCosteReal(new CosteMov(0));
        this.setCosteHeuristico(new CosteMov(0));
        this.setCosteTotal(new CosteMov(0));
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
        if (!(obj instanceof NodoMov)) {
            return false;
        }

        NodoMov rhs = (NodoMov) obj;

        return (p.equals(rhs.p));
    }
}
