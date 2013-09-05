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

    public static Resultado calcular(I_Grafo grafo, I_Nodo origen, I_Nodo destino) {
        Resultado respuesta = new Resultado();

        I_Nodo actual = origen;

        ComparadorDePosiciones cp = new ComparadorDePosiciones();

        PriorityQueue<I_Nodo> abiertos = new PriorityQueue<I_Nodo>(10, cp); //Comienza con 10 elementos

        ArrayList<I_Nodo> cerrados = new ArrayList<I_Nodo>();


        //Hasta que alcancemos el destino hacer
        while (actual != null && !actual.equals(destino)) {

            //Si actual ya está en cerrados pasamos al siguiente
            if (!cerrados.contains(actual)) {

                //Abrir el nodo actual y obtener sus vecinos
                ArrayList<I_Nodo> vecinos = grafo.getVecinos(actual);
                ArrayList<I_Nodo> vecinosValidos = new ArrayList<I_Nodo>();

                //Tratamos a los vecinos
                for (I_Nodo vecino : vecinos) {
                    //Si el vecino está en la lista de cerrados lo ignoramos
                    if (!cerrados.contains(vecino)) {
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

                        //TODO se establecen los padres para siempre pero si es un salto imposible o muy caro ya se queda así...
                        //hay que sustituirlos
                        //Establecemos el padre de los nodos vecinos para saber de dónde vienen
                        vecino.setPadre(actual);

                    }
                }

                //Añadimos los vecinos a la lista de abiertos
                abiertos.addAll(vecinosValidos);

                //Metemos el nodo actual en la lista de cerrados
                cerrados.add(actual);

            }
            //Seleccionamos y sacamos el nuevo nodo actual
            actual = abiertos.poll();

        }

        //Si actual es distinto de null es que hemos encontrado ruta
        if (actual != null) {

            //Anotar el coste real de la ruta
            respuesta.setCoste(actual.getCosteReal());

            //Componer la ruta de respuesta
            respuesta.setRuta(new ArrayList<I_Nodo>());


            //Incluimos el destino
            respuesta.getRuta().add(actual);

            //Volvemos hacia atrás desde el destino por el camino más corto
            while (actual != origen) {
                //Obtener vecinos del siguiente
                I_Nodo siguiente = actual.getPadre();

                //Ya tenemos al siguiente
                respuesta.getRuta().add(siguiente);

                //Tratamos ahora al siguiente
                actual = siguiente;
            }

            //Aquí ya tenemos la ruta pero al revés, le damos la vuelta
            Collections.reverse(respuesta.getRuta());

            //Voilà
        } else {
            respuesta = null; //Devolvemos null para indicar que no se ha encontrado la ruta
        }


        return respuesta;
    }

    public static NodosEnRango getNodosEnRango(I_Grafo grafo, I_Nodo origen, I_Coste coste_maximo) {
        NodosEnRango respuesta = new NodosEnRango();

        I_Nodo actual = origen;

        ComparadorDePosiciones cp = new ComparadorDePosiciones();

        PriorityQueue<I_Nodo> abiertos = new PriorityQueue<I_Nodo>(10, cp); //Comienza con 10 elementos

        ArrayList<I_Nodo> cerrados = new ArrayList<I_Nodo>();


        //Mientras el coste real del actual sea menor o igual que el coste máximo permitido continuamos
        while (actual != null && actual.getCosteReal().compareTo(coste_maximo) <= 0) {

            //Si actual ya está en cerrados pasamos al siguiente
            if (!cerrados.contains(actual)) {

                //Abrir el nodo actual y obtener sus vecinos
                ArrayList<I_Nodo> vecinos = grafo.getVecinos(actual);
                ArrayList<I_Nodo> vecinosValidos = new ArrayList<I_Nodo>();

                //Tratamos a los vecinos
                for (I_Nodo vecino : vecinos) {
                    //Si el vecino está en la lista de cerrados o abiertos lo ignoramos
                    if (!cerrados.contains(vecino)) {
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

                        //Establecemos el padre de los nodos vecinos para saber de dónde vienen
                        vecino.setPadre(actual);
                    }
                }

                //Añadimos los vecinos a la lista de abiertos
                abiertos.addAll(vecinosValidos);

                //Metemos el nodo actual en la lista de cerrados
                cerrados.add(actual);
            }
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

    public static Resultado rutaHasta(I_Grafo grafo, I_Nodo destino, NodosEnRango lista) {
        Resultado respuesta = new Resultado();


        //Anotar el coste real de la ruta
        respuesta.setCoste(destino.getCosteReal());

        I_Nodo actual = destino;
        I_Nodo origen = lista.origen;

        //Componer la ruta de respuesta
        respuesta.setRuta(new ArrayList<I_Nodo>());


        //Incluimos el destino
        respuesta.getRuta().add(actual);

        //Volvemos hacia atrás desde el destino por el camino más corto
        while (actual != origen) {
            //Obtener vecinos del siguiente
            I_Nodo siguiente = actual.getPadre();

            //Ya tenemos al siguiente
            respuesta.getRuta().add(siguiente);

            //Tratamos ahora al siguiente
            actual = siguiente;
        }

        //Aquí ya tenemos la ruta pero al revés, le damos la vuelta
        Collections.reverse(respuesta.getRuta());

        //Voilà

        return respuesta;
    }
}
