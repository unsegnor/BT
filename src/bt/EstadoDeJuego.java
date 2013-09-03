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
    public Mapa mapa;
    
    //Fase
    public Fase fase;
    
    //Iniciativa??? necesaria para saber los siguientes jugadores
    public int orden[];
    
    //Jugador actual
    public int jugador;
    
    //Jugadores siguientes
    public int siguientes[];
    
    //Estado de Jugadores
    public EstadoDeJugador jugadores[];
    
    //Datos de los mechs
    public DataMechs datos_mechs;
    
    //Datos de iniciativa
    public int[] iniciativa;
    
    //Definiciones de meches por jugador
    public DataDefMech[] datos_def_mechs;
    
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
