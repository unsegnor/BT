/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Neuro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Es una neurona.
 *
 * @author Víctor
 */
public class Neuron {

    //Random
    static Random random = new Random(System.currentTimeMillis());
    //Excita o inhibe - inicializado de forma aleatoria
    boolean excita = random.nextBoolean();
    //Dendritas
    ArrayList<Dendrita> dendritas = new ArrayList<Dendrita>();
    //Correspondencia entre axón y dendritas, el entero es el índice de la dendrita
    HashMap<Axon, Integer> dendrita = new HashMap<Axon, Integer>();
    //Umbral de disparo - threshold
    public double umbral = 1000; //TODO ajustar
    //Carga
    double carga;
    //Axón
    Axon axon = new Axon(this);
    //Último t
    double t0 = System.currentTimeMillis();
    //Factor RC
    public double RC = random.nextDouble()*10; //TODO ajustar

    
    public Neuron(double t){
        t0 = t;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("[").append(carga).append(", ")
                .append(umbral).append(", ")
                .append(excita).append(", ")
                .append(t0).append(", ")
                .append(RC).append("]");
        
        return sb.toString();
    }
    
    //Sinapsis t es el momento en el que se produce
    //n es la neurona que genera los neurotransmisores y t es el momento
    public void synapsis(Neuron n, double t) {
        actualizar(t);
        //Comprobar a qué dendrita llegan los neurotransmisores
        //Mirar si tiene dendrita asinada
        Integer indice_dendrita = dendrita.get(n.axon);
        if (indice_dendrita == null) {
            //Si no tiene dendrita asignada le asignamos una
            //double peso = random.nextDouble(); //Le asignamos un peso aleatorio entre 0 y 1
            double peso = 100; //TODO ajustar
            dendritas.add(new Dendrita(this, n.axon, peso));
            indice_dendrita = dendritas.size() - 1;
            dendrita.put(n.axon, indice_dendrita);
        }

        //Aquí seguro que tiene dendrita asignada
        //Así que ejecutamos la synapsis
        //Aumentamos o reducimos la carga en función de si el axon es de una neurona que excita o inhibe
        if (n.excita) {
            //Si excita aumentamos
            this.carga += dendritas.get(indice_dendrita).peso; //Sumamos el peso
            //TODO quizá habría que multiplicar en función de la carga para que no se flipe (acotar a un máximo)
        } else {
            //Si inhibe reducimos
            this.carga -= dendritas.get(indice_dendrita).peso;
        }

        //Si se supera el umbral disparamos
        if(carga >= this.umbral){
            disparar(t);
            reiniciar(t);
        }

    }
    
    public void disparar(double t){
        //Disparar a todas las neuronas siguientes
        for(int i=0; i<axon.destinos.size();i++){
            axon.destinos.get(i).synapsis(this, t);
        }
    }
    
    public void reiniciar(double t){
        //Recinicar carga
        this.carga = 0;
    }
    
    public void actualizar(double t){
        //La carga será la carga que había en el último t multiplicada por (e^(-(t-ut))/RC)
        //Si la carga es casi cero la hacemos cero
        if(Math.abs(carga)<0.0001){
            carga = 0;
        }else{
            carga *= Math.abs(Math.exp(t0-t)/RC);
        }
    }
    
    
}
