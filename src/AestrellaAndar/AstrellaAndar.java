/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaAndar;

import Aestrella.*;
import bt.EstadoDeJuego;
import bt.Mapa;
import bt.Phexagono;
import bt.Posicion;

/**
 *
 * @author Víctor
 */
public class AstrellaAndar {

    public static Resultado calcularRutaAndando(Posicion a, Posicion b, EstadoDeJuego estado) {
        Resultado respuesta = new Resultado();

        //Cargamos grafo de andar
        GrafoAndar grafo = new GrafoAndar(estado);

        NodoAndar origen = grafo.getNodo(a);
        NodoAndar destino = grafo.getNodo(b);

        respuesta = Aestrella.calcular(grafo, origen, destino);

        return respuesta;
    }

    public static NodosEnRango obtenerEnRango(EstadoDeJuego estado, Posicion origen, int radio) {
        CosteAndar costemaximo = new CosteAndar(radio);

        //Cargamos grafo de andar
        GrafoAndar grafo = new GrafoAndar(estado);
        
        NodoAndar nodo_origen = grafo.getNodo(origen);

        return Aestrella.getNodosEnRango(grafo, nodo_origen, costemaximo);
    }
    
    public static Resultado calcularRutaAndando(EstadoDeJuego estado, Posicion destino, NodosEnRango lista){
        GrafoAndar grafo = new GrafoAndar(estado);
        NodoAndar nodo_destino = grafo.getNodo(destino);
        return Aestrella.rutaHasta(grafo, nodo_destino, lista);
    }
}
