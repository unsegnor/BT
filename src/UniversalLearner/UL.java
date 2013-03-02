/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalLearner;

/**
 * Universal Learner.
 *
 * @author Víctor
 */
public class UL {

    //TODO Tendremos una frecuencia de muestreo independiente para cada variable de entrada
    //Muestreador
    Muestreador muestreador;
    //Frecuencia de muestreo en (tantos por segundo)
    long frecuencia = 1;
    
    //TODO presta atención a lo que se mueve(cambia)
    //y con más frecuencia (con más detalle) a lo que se mueve más rápido
    //se puede hacer que la frecuencia de mueestreo sea función del cambio en la señal de forma continua, exponencial
    //que responda a los cambios de entrada con cambios en la frecuencia de muestreo
    //TODO después tiene que buscar patrones en lo que está muestreando
    //Ventana de atención
    

    //Muestrear
    public void muestra(Muestra muestra) {
        System.out.println("Muestreando: " + muestra);
    }

    //Muestrear por referencia
    public void muestrear(Integer[] entradas) {
        this.muestrear(entradas, this.frecuencia);
    }

    //Muestrear por referencia con frecuencia
    public void muestrear(Integer[] entradas, long frecuencia) {
        //Finalizar el muestreador anterior
        if (muestreador != null) {
            muestreador.finalizar();
        }
        //Arrrancar muestreador 
        muestreador = new Muestreador(this, entradas, frecuencia);
        (new Thread(muestreador)).start();
    }

    public String toString(Integer[] muestra) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < muestra.length; i++) {
            sb.append(" ");
            sb.append(muestra[i]);
        }
        return sb.toString();
    }
}
