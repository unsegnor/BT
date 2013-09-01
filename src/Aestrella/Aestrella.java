/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aestrella;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author Víctor
 */
public class Aestrella {

    public Resultado calcular(I_Grafo grafo, I_Nodo origen, I_Nodo destino) {
        Resultado respuesta = new Resultado();

        I_Nodo actual = origen;

        ComparadorDePosiciones cp = new ComparadorDePosiciones();

        PriorityQueue<I_Nodo> abiertos = new PriorityQueue<I_Nodo>(10, cp); //Comienza con 10 elementos

        ArrayList<I_Nodo> cerrados = new ArrayList<I_Nodo>();


        //Hasta que alcancemos el destino hacer
        while (actual != null && actual != destino) {
            //Abrir el nodo actual y obtener sus vecinos
            ArrayList<I_Nodo> vecinos = grafo.getVecinos(actual);
            ArrayList<I_Nodo> vecinosValidos = new ArrayList<I_Nodo>();

            //Tratamos a los vecinos
            for (I_Nodo vecino : vecinos) {
                //Si el vecino está en la lista de cerrados o abiertos lo ignoramos
                if (cerrados.contains(vecino) || abiertos.contains(vecino)) {
                } else {
                    //Sino lo calculamos y lo pasamos a la lista válida


                    //Los vecinos vienen con el costeReal calculado
                    I_Coste costeReal = vecino.getCosteReal();


                    //Calcular coste real hasta el vecino
                    //I_Coste costeReal = actual.getCosteReal().sumar(grafo.calcularCosteReal(actual, vecino));
                    //vecino.setCosteReal(costeReal);

                    //Calcular coste heurístico
                    I_Coste costeHeuristico = grafo.calcularCosteHeuristico(vecino, destino);
                    vecino.setCosteHeuristico(costeHeuristico);

                    //Obtener y establecer el coste total
                    I_Coste costeTotal = costeReal.sumar(costeHeuristico);
                    vecino.setCosteTotal(costeTotal);

                    //Añadir a los vecinos válidos
                    vecinosValidos.add(vecino);
                }
            }

            //Añadimos los vecinos a la lista de abiertos
            abiertos.addAll(vecinosValidos);

            //Metemos el nodo actual en la lista de cerrados
            cerrados.add(actual);

            //Seleccionamos y sacamos el nuevo nodo actual
            actual = abiertos.poll();
        }

        //Anotar el coste real de la ruta
        respuesta.coste = actual.getCosteReal();

        //Componer la ruta de respuesta
        respuesta.ruta = new ArrayList<I_Nodo>();


        //Incluimos el destino
        respuesta.ruta.add(actual);

        //Volvemos hacia atrás desde el destino por el camino más corto
        while (actual != origen) {
            //Obtener vecinos del siguiente
            ArrayList<I_Nodo> vecinos = grafo.getVecinos(actual);

            //De los vecinos escogemos aquél que esté en la lista de cerrados y tenga menor costeReal
            I_Coste menorCosteReal = actual.getCosteReal();
            I_Nodo siguiente = null;
            for (I_Nodo vecino : vecinos) {
                if (cerrados.contains(vecino)) {
                    if (vecino.getCosteReal().compareTo(menorCosteReal) < 0) {
                        menorCosteReal = vecino.getCosteReal();
                        siguiente = vecino;
                    }
                }
            }

            //Ya tenemos al siguiente
            respuesta.ruta.add(siguiente);

            //Tratamos ahora al siguiente
            actual = siguiente;
        }

        //Aquí ya tenemos la ruta pero al revés, le damos la vuelta
        Collections.reverse(respuesta.ruta);

        //Voilà

        return respuesta;
    }

    public NodosEnRango getNodosEnRango(I_Grafo grafo, I_Nodo origen, I_Coste coste_maximo) {
        NodosEnRango respuesta = new NodosEnRango();

        I_Nodo actual = origen;

        ComparadorDePosiciones cp = new ComparadorDePosiciones();

        PriorityQueue<I_Nodo> abiertos = new PriorityQueue<I_Nodo>(10, cp); //Comienza con 10 elementos

        ArrayList<I_Nodo> cerrados = new ArrayList<I_Nodo>();


        //Mientras el coste real del actual sea menor o igual que el coste máximo permitido continuamos
        while (actual != null && actual.getCosteReal().compareTo(coste_maximo) <= 0) {
            //Abrir el nodo actual y obtener sus vecinos
            ArrayList<I_Nodo> vecinos = grafo.getVecinos(actual);
            ArrayList<I_Nodo> vecinosValidos = new ArrayList<I_Nodo>();

            //Tratamos a los vecinos
            for (I_Nodo vecino : vecinos) {
                //Si el vecino está en la lista de cerrados o abiertos lo ignoramos
                if (cerrados.contains(vecino) || abiertos.contains(vecino)) {
                } else {
                    //Sino lo calculamos y lo pasamos a la lista válida


                    //Los vecinos vienen con el costeReal calculado
                    I_Coste costeReal = vecino.getCosteReal();


                    //Calcular coste real hasta el vecino
                    //I_Coste costeReal = actual.getCosteReal().sumar(grafo.calcularCosteReal(actual, vecino));
                    //vecino.setCosteReal(costeReal);

                    //En esta ocasión no nos inmporta el coste heurístico
                    //I_Coste costeHeuristico = grafo.calcularCosteHeuristico(vecino, destino);
                    //vecino.setCosteHeuristico(costeHeuristico);

                    //Obtener y establecer el coste total
                    I_Coste costeTotal = costeReal;
                    vecino.setCosteTotal(costeTotal);

                    //Añadir a los vecinos válidos
                    vecinosValidos.add(vecino);
                }
            }

            //Añadimos los vecinos a la lista de abiertos
            abiertos.addAll(vecinosValidos);

            //Metemos el nodo actual en la lista de cerrados
            cerrados.add(actual);

            //Seleccionamos y sacamos el nuevo nodo actual
            actual = abiertos.poll();
        }

        /* 
         * En este momento hemos seleccionado el primer nodo cuyo coste supera el coste máximo permitido
         * esto significa que en "cerrados" tenemos todos los nodos con coste menor o igual al actual
         * devolveremos la lista de nodos "cerrados" que es la única necesaria para calcular las rutas hasta cada
         * uno de los nodos de la lista
         */

        respuesta.origen = origen;
        respuesta.lista = cerrados;


        return respuesta;
    }

    public Resultado rutaHasta(I_Grafo grafo, I_Nodo destino, NodosEnRango lista) {
        Resultado respuesta = new Resultado();


        //Anotar el coste real de la ruta
        respuesta.coste = destino.getCosteReal();

        I_Nodo actual = destino;
        I_Nodo origen = lista.origen;

        //Componer la ruta de respuesta
        respuesta.ruta = new ArrayList<I_Nodo>();


        //Incluimos el destino
        respuesta.ruta.add(actual);

        //Volvemos hacia atrás desde el destino por el camino más corto
        while (actual != origen) {
            //Obtener vecinos del siguiente
            ArrayList<I_Nodo> vecinos = grafo.getVecinos(actual);

            //De los vecinos escogemos aquél que esté en la lista de cerrados y tenga menor costeReal
            I_Coste menorCosteReal = actual.getCosteReal();
            I_Nodo siguiente = null;
            for (I_Nodo vecino : vecinos) {
                if (lista.lista.contains(vecino)) {
                    if (vecino.getCosteReal().compareTo(menorCosteReal) < 0) {
                        menorCosteReal = vecino.getCosteReal();
                        siguiente = vecino;
                    }
                }
            }

            //Ya tenemos al siguiente
            respuesta.ruta.add(siguiente);

            //Tratamos ahora al siguiente
            actual = siguiente;
        }

        //Aquí ya tenemos la ruta pero al revés, le damos la vuelta
        Collections.reverse(respuesta.ruta);

        //Voilà

        return respuesta;
    }
}
