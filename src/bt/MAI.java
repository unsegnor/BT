/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.Random;

/**
 * Movement Artificial Intelligence
 * @author Víctor
 */
public class MAI {
    
    /*
     * Partimos de un hexágono y queremos determinar si existe algún hexágono alcanzable en el turno
     * que sea mejor evaluado por las heurísticas para movernos a él.
     * 
     * Podríamos...
     * 
     * Comenzar a analizar movimientos posibles desde la posición actual, posibles acciones y sus 
     * posibles conscuencias teniendo en cuenta las probabilidades.
     */
    
    //TODO necesito una función que me devuelva a partir de un hexágono y un battlemech (puntos de movimiento)
    //una lista de posiciones que puede alcanzar con la ruta para alcanzarlas y las posiciones que probablemente pueda
    //alcanzar por error, junto con la probabilidad de alcanzarlas.
    
    Random r = new Random(System.currentTimeMillis());
    
    ///Función de prueba, elabora una ruta aleatoria pero realizable en el mapa
    public Movimiento muevete(Mapa mapa, int jugador){
        Movimiento respuesta = new Movimiento();
        
        //Decidir el tipo de movimiento
        int tipoMov = r.nextInt(5);
        
        //Consultar puntos de movimiento
        //Buscar en las características del mech del jugador
            //Puntos de movimiento
            //Posición del mech
            //Necesito ya el ESTADODELJUEGO
        
        switch(tipoMov){
            case 0:
                respuesta.setTipo(Reglas.tiposDeMovimiento.Inmovil);
            break;
            case 1:
                respuesta.setTipo(Reglas.tiposDeMovimiento.Andar);
            break;
        }
        
        return respuesta;
    }
    
    
    //Necesito una función que mediante un A* devuelva todas las posiciones accesibles desde una posición dada
    //con unos puntos de movimiento dados
    
    
    
}
