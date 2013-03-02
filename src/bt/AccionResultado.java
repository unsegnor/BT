/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;

/**
 * Almacena una acción y sus posibles resultados con la probabilidad de que se den
 * @author Víctor
 */
public class AccionResultado {
    
    private Accion accion;
    
    //Estados siguientes con la probabilidad de que ocurran
    private ArrayList<Resultado> resultados = new ArrayList<Resultado>();

    /**
     * @return the accion
     */
    public Accion getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    /**
     * @return the resultados
     */
    public ArrayList<Resultado> getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(ArrayList<Resultado> resultados) {
        this.resultados = resultados;
    }
    
}
