/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Total Artificial Intelligence
 *
 * @author Víctor
 */
public class TAI {

    /*
     * Función que a partir de un estado devuelve todas las acciones posibles
     * 
     *
     */
    
    HashSet<Agente> agentes = new HashSet<>();
    
    public void inscribirAgente(Agente agente){
        agentes.add(agente);
    }
    
    public AccionResultado[] accionesPosibles(State estado){
        ArrayList<AccionResultado> respuesta = new ArrayList<>();
        
        //Consultamos a todos los agentes inscritos para que
        //añadan las acciones que ellos estipulan posibles
        //Nota: Las acciones que devuelven los agentes deberían ser exclusivas
        //es decir que normalmente sólo responderá uno de ellos con una lista de acciones posibles
        //sin embargo entre ellos sí se pueden llamar para componer acciones?
        for(Agente agente : agentes){
            respuesta.addAll(agente.getAccionesPosibles(estado));
        }
        
        //Aquí tendremos una lista con todas las acciones y sus posibles resultados con la probabilidad 
        //con la que pueden suceder
        
        return respuesta.toArray(new AccionResultado[respuesta.size()]);
    }
    
}
