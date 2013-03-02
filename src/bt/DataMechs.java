/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Contiene los datos que se leen del fichero de mechs
 * @author Víctor
 */
class DataMechs {
    
    //Número de mechs
    private int nMechs;
    
    //Vector de mechs
    private DataMech[] mechs;

    /**
     * @return the nMechs
     */
    public int getnMechs() {
        return nMechs;
    }

    /**
     * @param nMechs the nMechs to set
     */
    public void setnMechs(int nMechs) {
        this.nMechs = nMechs;
    }

    /**
     * @return the mechs
     */
    public DataMech[] getMechs() {
        return mechs;
    }

    /**
     * @param mechs the mechs to set
     */
    public void setMechs(DataMech[] mechs) {
        this.mechs = mechs;
    }
}
