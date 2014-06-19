/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaMov;

import Aestrella.*;
import bt.EstadoDeJuego;
import bt.Mapa;
import bt.Phexagono;
import bt.Posicion;

/**
 *
 * @author Víctor
 */
public class AstrellaMov {

    public static Resultado calcularRutaAndando(Posicion a, Posicion b, EstadoDeJuego estado) {
        Resultado respuesta = new Resultado();

        //Cargamos grafo de andar
        GrafoAndar grafo = new GrafoAndar(estado);

        NodoMov origen = grafo.getNodo(a);
        NodoMov destino = grafo.getNodo(b);

        respuesta = Aestrella.calcular(grafo, origen, destino);

        return respuesta;
    }

    public static NodosEnRango obtenerEnRangoAndando(EstadoDeJuego estado, Posicion origen, int radio) {
        CosteMov costemaximo = new CosteMov(radio);
        costemaximo.chequeos_de_pilotaje = 1000;

        //Cargamos grafo de andar
        GrafoAndar grafo = new GrafoAndar(estado);

        NodoMov nodo_origen = grafo.getNodo(origen);

        return Aestrella.getNodosEnRango(grafo, nodo_origen, costemaximo);
    }

    public static Resultado calcularRutaAndando(EstadoDeJuego estado, Posicion destino, NodosEnRango lista) {
        GrafoAndar grafo = new GrafoAndar(estado);
        NodoMov nodo_destino = grafo.getNodo(destino);
        return Aestrella.rutaHasta(grafo, nodo_destino, lista);
    }

    public static Resultado calcularRutaCorriendo(Posicion a, Posicion b, EstadoDeJuego estado) {
        Resultado respuesta = new Resultado();

        //Cargamos grafo de andar
        GrafoCorrer grafo = new GrafoCorrer(estado);

        NodoMov origen = grafo.getNodo(a);
        NodoMov destino = grafo.getNodo(b);

        respuesta = Aestrella.calcular(grafo, origen, destino);

        return respuesta;
    }

    public static NodosEnRango obtenerEnRangoCorriendo(EstadoDeJuego estado, Posicion origen, int radio) {
        CosteMov costemaximo = new CosteMov(radio);
        costemaximo.chequeos_de_pilotaje = 1000; //ponemos esto para que en la comparación con el máximo no tenga en cuenta los chequeos de pilotaje
        
        //Cargamos grafo de andar
        GrafoCorrer grafo = new GrafoCorrer(estado);

        NodoMov nodo_origen = grafo.getNodo(origen);

        return Aestrella.getNodosEnRango(grafo, nodo_origen, costemaximo);
    }

    public static Resultado calcularRutaCorriendo(EstadoDeJuego estado, Posicion destino, NodosEnRango lista) {
        GrafoCorrer grafo = new GrafoCorrer(estado);
        NodoMov nodo_destino = grafo.getNodo(destino);
        return Aestrella.rutaHasta(grafo, nodo_destino, lista);
    }
}
