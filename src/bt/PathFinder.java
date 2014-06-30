/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class PathFinder {

    static Ruta buscaRutaAndando(Mapa mapa, Phexagono p_inicio, Phexagono p_fin, int pm) {
        Ruta respuesta = null;

        //Partimos de la posición inicial

        //Evaluamos la posición actual


        //Si es igual a la posición final -> hemos terminado
        //Si no es igual a la posición final
        //Entonces evaluamos vecinos
        //Seleccionamos el vecino más prometedor 
        //(comprobando lo que nos cuesta llegar a él
        //y lo cerca que está del fin según la heurística)




        return respuesta;
    }

    static Ruta buscaRutaCorriendo(Mapa mapa, Phexagono posicion_mech, Phexagono pe, int pm) {
        return null;
    }

    static boolean buscaRutaSaltando(Mapa mapa, Phexagono p_inicio, Phexagono p_fin, int pm) {
        boolean respuesta = true;

        //Trazar una línea recta entre el origen y el destino
        //Obtener los hexágonos intermedios
        ArrayList<ArrayList<Phexagono>> intermedios = mapa.intermedios(p_inicio, p_fin);

        //Comprobar si es posible
        //Si cualquiera de los hexágonos intermedios tiene una altura superior al número de puntos de salto
        // -> entonces el salto no puede ejecutarse
        boolean posible = true;
        int distancia = 0;
        int nivel_actual = mapa.casilla(p_inicio).getNivel();
        for (int i = 1; i < intermedios.size(); i++) { //Comenzamos en el 1 para obviar el primero
            ArrayList<Phexagono> p = intermedios.get(i);
            distancia += 1;

            for (Phexagono hex : p) {
                int desnivel = (mapa.casilla(hex).getNivel() - nivel_actual);
                if (desnivel > pm) {
                    //Si el desnivel hacia arriba de alguna casilla del camino es mayor que el número de pm -> el salto no es posible
                    posible = false;
                }
            }
        }

        //Si la ruta es posible
        if (distancia > pm || !posible) {
            respuesta = false;
        }

        String esposible = posible ? "posible" : "imposible";

        //System.out.println("Salto desde " + p_inicio + " a " + p_fin + " es " + esposible + ". Distancia: " + distancia + " PM: " + pm);

        return respuesta;
    }
}
