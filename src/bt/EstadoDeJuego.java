/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;

/**
 * Un estado es toda la información que define el juego en un instante concreto.
 * Quizá no sea necesaria la clase EstadoDeJuego sino simplemente Juego y crear varios para representar cada estado.
 * Lo necesitamos para poder simular con una acción a qué estados podemos llegar y con qué probabilidad.
 * Después necesitaremos una función de evaluación del estado. Con heurísticas.
 * Tenemos que poder sacar los vecinos de cada estado.
 * Y evaluar cada estado.
 * @author Víctor
 */
public class EstadoDeJuego {
    
    //Mapa del momento
    Mapa mapa;
    
    //Fase
    Fase fase;
    
    //Iniciativa??? necesaria para saber los siguientes jugadores
    int orden[];
    
    //Jugador actual
    int jugador;
    
    //Jugadores siguientes
    int siguientes[];
    
    //Estado de Jugadores
    EstadoDeJugador jugadores[];
    
    //Datos de los mechs
    DataMechs datos_mechs;
    
    //Datos de iniciativa
    int[] iniciativa;
    
    //Definiciones de meches por jugador
    DataDefMech[] datos_def_mechs;
    
    public DataMech getMechActual(){
        DataMech respuesta = null;

        for (DataMech mech : datos_mechs.getMechs()) {
            if (mech.getnJugador() == jugador) {
                respuesta = mech;
            }
        }
        
        
        return respuesta;
    }
    
}
