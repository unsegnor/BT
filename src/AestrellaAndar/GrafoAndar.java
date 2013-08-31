/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaAndar;

import Aestrella.I_Coste;
import Aestrella.I_Grafo;
import Aestrella.I_Nodo;
import bt.Mapa;
import bt.Posicion;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Víctor
 */
class GrafoAndar implements I_Grafo {

    Mapa mapa;
    //Correspondencia entre posiciones del mapa y nodos que los representan
    /*
     * Hemos sobreescrito la función HashCode y Equals de Posición para poder utilizarla fácilmente como índice en Hash
     */
    HashMap<Posicion, NodoAndar> nodos;

    GrafoAndar(Mapa mapa) {
        this.mapa = mapa;
        nodos = new HashMap<Posicion, NodoAndar>();
    }

    /**
     * Devuelve el nodo correspondiente a la posición si existe. Sino crea uno
     * nuevo y lo devuelve.
     *
     * @param p
     * @return
     */
    private NodoAndar getNodo(Posicion p) {
        NodoAndar respuesta = nodos.get(p);
        if (respuesta == null) {
            respuesta = new NodoAndar(p);
            nodos.put(p, respuesta);
        }
        return respuesta;
    }

    @Override
    public I_Coste calcularCosteReal(I_Nodo actual, I_Nodo vecino) {
        /*
         * Esta es la más compleja, aquí tenemos que determinar la acción que se ha llevado a cabo en función del orígen y el destino
         * y establecer el coste de esa acción en esas casillas concretas
         */
        CosteAndar respuesta = new CosteAndar();
        
        //Casteamos los nodos y recuperamos las posiciones
        Posicion a = ((NodoAndar) actual).p;
        Posicion b = ((NodoAndar) vecino).p;
        
        
        //Determinar si es un giro, un avance o un retroceso
        if(a.getHexagono().getColumna() == b.getHexagono().getColumna()
                && a.getHexagono().getFila() == b.getHexagono().getFila()
                && a.getLado() != b.getLado()){
            //Es un giro
            respuesta.setPuntos_de_movimiento(1);
        }else{
            //Es un cambio de hexágono. Determinar si es hacia alante o hacia atrás
            
            
           
        }
        
        
        
        
        return respuesta;
    }

    @Override
    public I_Coste calcularCosteHeuristico(I_Nodo vecino, I_Nodo destino) {
        //El coste heurístico es la distancia entre los dos hexágonos independientemente de su encaramiento
        CosteAndar coste = new CosteAndar();

        //Casteamos los nodos
        NodoAndar a = (NodoAndar) vecino;
        NodoAndar b = (NodoAndar) destino;

        int distancia = mapa.distanciaCasillas(a.p.getHexagono(), b.p.getHexagono());

        coste.setPuntos_de_movimiento(distancia);

        return coste;
    }

    @Override
    public ArrayList<I_Nodo> getVecinos(I_Nodo nodo) {
        ArrayList<I_Nodo> respuesta = new ArrayList<I_Nodo>();

        //Extraemos la posición del nodo
        Posicion p = ((NodoAndar) nodo).p;

        //Si andamos podemos girar izquierda, girar derecha, avanzar o retroceder
        respuesta.add(this.getNodo(p.giroIzquierda()));
        respuesta.add(this.getNodo(p.giroDerecha()));

        //Alante y atrás sólo las añadimos si las casillas existen
        Posicion delante = p.delante();
        if (mapa.valido(delante.getHexagono())) {
            respuesta.add(this.getNodo(delante));
        }
        Posicion detras = p.atras();
        if (mapa.valido(p.getHexagono())) {
            respuesta.add(this.getNodo(detras));
        }

        return respuesta;
    }
}
