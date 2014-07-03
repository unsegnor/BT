/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.Comparator;

/**
 *
 * @author Víctor
 */
class ComparaPosicionesConArmas implements Comparator<PosEval> {

    @Override
    public int compare(PosEval o1, PosEval o2) {

        int respuesta = 0;
        //Comparamos con armas así que tenemos en cuenta la LDV, los niveles, etc
        //Si o1 es mejor que o2 se responde -1

        //En caso de que una tenga LDV y la otra no, ¿cuál es mejor?

        //La mayor ventaja que podemos tener sobre el enemigo es pillarlo de espaldas
        //El que tenga un enemigo con más giros necesarios para encararle mejor
        //Estar detrás nos garantiza que nos disparará con menos fuerza y que nuestros disparos harán más daño
        if (noencarable(o1) && !noencarable(o2)) {
            respuesta = -1;
        } else if (!noencarable(o1) && noencarable(o2)) {
            respuesta = 1;
        }

        if (respuesta == 0) {
            //Después que nosotros lo podamos encarar
            if (podemosencarar(o1) && !podemosencarar(o2)) {
                respuesta = -1;
            } else if (!podemosencarar(o1) && podemosencarar(o2)) {
                respuesta = 1;
            }
        }

        //Si empatan
        if (respuesta == 0) {
            //Entonces ya miramos que puedan disparar al enemigo con ventaja
            if (disparoventaja(o1) && !disparoventaja(o2)) {
                respuesta = -1;
            } else if (!disparoventaja(o1) && disparoventaja(o2)) {
                respuesta = 1;
            }
        }

        //TODO Si empatan porque ninguna es la leche o las dos lo son entonces coger la que no tena LDV

        //Si empatan cogemos aquella que más encare al enemigo
        if (respuesta == 0) {
            return o1.giros_para_ecarar_enemigo - o2.giros_para_ecarar_enemigo;
        }

        //Si empatan escogemos la posición más elevada
        if (respuesta == 0) {
            return o2.niveles_por_ecima_del_enemigo - o1.niveles_por_ecima_del_enemigo;
        }

        //Si empatan cogemos la posición más cercana al enemigo
        if (respuesta == 0) {
            return o1.distancia_al_enemigo - o2.distancia_al_enemigo;
        }


        return respuesta;
    }

    private boolean noencarable(PosEval o1) {
        //Somos no encarables si el enemigo tiene que hacer más de 1 giro para apuntarnos
        return o1.giros_enemigo_para_encararme > 1;
    }

    private boolean disparoventaja(PosEval o1) {
        //Es un disparo con ventaja cuando podemos disparar a un enemigo descubierto desde una posición cubierta
        return o1.enemigo_visible && o1.ofrece_cobertura && !o1.enemigo_cubierto;
    }

    private boolean podemosencarar(PosEval o1) {
        //Podemos encarar si los giros necesarios son menores o iguales a 1
        return o1.giros_para_ecarar_enemigo < 2;
    }
}
