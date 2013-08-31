/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;

/**
 * Clase que alberga las operaciones relacionadas con la LDV
 *
 * @author Víctor
 */
public class LDV {

    public enum IntervencionLDV {

        Nada, DificultaLeve, DificultaGrave, Bloquea
    };

    public static ResultadoLDV calcularLDV(Mapa mapa, int columna_origen, int fila_origen, int nivel_origen, int columna_destino, int fila_destino, int nivel_destino) {
        //Crear Phexágonos y llamar
        Phexagono origen = new Phexagono(columna_origen, fila_origen);
        Phexagono destino = new Phexagono(columna_destino, fila_destino);
        return calcularLDV(mapa, origen, nivel_origen, destino, nivel_destino);
    }

    public static ResultadoLDV calcularLDV(Mapa mapa, Phexagono origen, int nivel_origen, Phexagono destino, int nivel_destino) {
        ResultadoLDV respuesta = new ResultadoLDV();
        ArrayList<Phexagono> caminoelegido = new ArrayList<Phexagono>();
        ArrayList<ArrayList<Phexagono>> intermedios = null;
        Hexagono hex_origen = mapa.casilla(origen);
        Hexagono hex_destino = mapa.casilla(destino);
        int total_nivel_origen = hex_origen.getNivel() + nivel_origen; //El nivel de la casilla más la suma que nos indican
        int total_nivel_destino = hex_destino.getNivel() + nivel_destino;
        //Si uno de los puntos está sumergido y el otro no, no hay LDV //Sumamos uno porque el nivel vendrá para la LDV y se considera 1 si está en pi y 0 si está tirado
        boolean origen_sumergido = sumergido(mapa, origen, nivel_origen + 1);
        boolean destino_sumergido = sumergido(mapa, destino, nivel_destino + 1);
        if (origen_sumergido || destino_sumergido) { // ||(no permite que se vean si cualquiera de las dos está sumergida) antes != (permite que se vean si ambas están debajo del agua)
            respuesta.LDV = false;
        } else {
            //Obtener intermedios del mapa
            intermedios = mapa.intermedios(origen, destino);
            //Comprobar la LDV
            //Si los puntos son adyacentes y comparten el mismo medio
            if (intermedios.size() == 2) {
                //Entonces hay LDV
                respuesta.LDV = true;
            } else {
                //Comparten medio y no son adyacentes
                //Si las dos están sumergidas no hay LDV
                if (origen_sumergido) {
                    //Entonces las dos están sumergidas y no son adyacentes -> no hay LDV
                    respuesta.LDV = false;
                } else {
                    //Las dos están en tierra y no son adyacentes

                    //Si hay algun hexágono entre los intermedios que sea más alto que el orígen o el destino entonces no hay LDV
                    //Recorrer todas las opciones de intermedios buscándolo.
                    boolean hmasaltoenmedio = false;


                    //Altura para que un intermedio bloquee
                    int maxAltura = Math.max(total_nivel_origen, total_nivel_destino);

                    //Dificultad de LDV
                    int TotalLDVDiff = 0; //Comienza siendo cero que es: ninguna
                    //Se irá sumando 2 por cada bosque disperso
                    // y 3 por cada bosque denso o humo
                    //si llega a 6 o lo pasa entonces no hay LDV

                    boolean interviene_elemento = false;

                    for (int h = 1; h < intermedios.size() - 1 && TotalLDVDiff < 6; h++) {
                        //comprobar si alguna de las opciones es más alta que los dos
                        ArrayList<Phexagono> aux = intermedios.get(h);
                        //Nos quedamos con el mayor incremento de bloqueo
                        int maxLDVDiff = 0;
                        //Índice del hexágono que elegimos
                        int imax = 0;
                        for (int i = 0; i < aux.size(); i++) {
                            //Inicializamos el incremento de dificultad
                            int LDVDiff = 0;
                            Hexagono hexagono = mapa.casilla(aux.get(i));
                            int altura_hexagono = hexagono.getNivel();
                            interviene_elemento = false;
                            //Si la altura del hexágono es mayor que la máxima entonces bloquea, sumamos 6
                            if (altura_hexagono > maxAltura) {
                                LDVDiff += 6;
                            } else if (altura_hexagono + 2 > maxAltura) {
                                //Podría intervenir si tuviera un bosque o algo
                                interviene_elemento = true;
                            }

                            //Si es adyacente al orígen y mide más que éste bloquea, sumamos 6
                            if (h == 1) {
                                //Es adyacente al orígen
                                if (altura_hexagono > total_nivel_origen) {
                                    //Es más alto que el orígen
                                    LDVDiff += 6;
                                } else if (altura_hexagono + 2 > total_nivel_origen) {
                                    //Puede intervenir
                                    interviene_elemento = true;
                                }
                            }

                            //Si es adyacente al destino y más alto que éste -> bloquea
                            if (h == intermedios.size() - 2) {
                                //Es adyacente al destino
                                if (altura_hexagono > total_nivel_destino) {
                                    //Es más alto que el destino -> bloquea
                                    LDVDiff += 6;
                                } else if (altura_hexagono > total_nivel_destino) {
                                    interviene_elemento = true;
                                }
                            }

                            //Si interviene su elemento vamos a ver de qué forma
                            if (interviene_elemento) {
                                switch (hexagono.getTipoobjeto()) {
                                    case BosqueLigero:
                                        LDVDiff += 2;
                                        break;
                                    case BosqueDenso:
                                        LDVDiff += 4;
                                        break;
                                }

                                //Si tiene humo sumamos su dificultad a la LDV
                                if (hexagono.isHumo()) {
                                    LDVDiff += 4;
                                }
                            }

                            //Establecer el máximo
                            if (LDVDiff > maxLDVDiff) {
                                maxLDVDiff = LDVDiff;
                                imax = i;
                            }
                        }
                        //Sumamos el máximo al total
                        TotalLDVDiff += maxLDVDiff;
                        //Almacenamos el hexágono que seleccionamos
                        caminoelegido.add(intermedios.get(h).get(imax));
                    }
                    //Si el incremento total de bloqueo de visión es menor que 6 entonces hay LDV
                    if (TotalLDVDiff < 6) {
                        respuesta.LDV = true;
                    } else {
                        //Sino no hay LDV
                        respuesta.LDV = false;
                    }
                }
            }
        }

        //Ya tenemos el camino elegido y si hay o no LDV
        respuesta.hexagonos = caminoelegido.toArray(new Phexagono[caminoelegido.size()]);

        //Cobertura parcial
        //Si está en un hexágono de de profundidad 1 y no está sumergido, es decir, su nivel es 1
        //Hay cobertura parcial si hay LDV,
        //si el hexágono objetivo es adyacente a
        //un hexágono de elevación igual a sí mismo que es intermedio
        //y además la altura del atacante es igual o inferior a la del objetivo


        //Comienza siendo false
        respuesta.CPdirecta = false;
        respuesta.CPinversa = false;

        //Si hay LDV
        if (respuesta.LDV) {
            //Calculamos la CPdirecta
            //cobertura del objetivo respecto al atacante

            //Si no hemos calculado los intermedios lo hacemos ahora
            if (intermedios == null) {
                intermedios = mapa.intermedios(origen, destino);
            }

            //Si existen intermedios entonces continuamos, sino no hay cobertura
            int nintermedios = intermedios.size();
            if (nintermedios > 2) {
                ArrayList<Phexagono> adyacentes_al_destino = intermedios.get(nintermedios - 2);
                ArrayList<Phexagono> adyacentes_al_origen = intermedios.get(1);


                respuesta.CPdirecta = CP(
                        mapa,
                        hex_destino,
                        nivel_destino,
                        total_nivel_destino,
                        total_nivel_origen,
                        adyacentes_al_destino);

                //Y ya que estamos calculamos también la CPinversa
                //cobertura del atacante respecto al objetivo
                respuesta.CPinversa = CP(
                        mapa,
                        hex_origen,
                        nivel_origen,
                        total_nivel_origen,
                        total_nivel_destino,
                        adyacentes_al_origen);

                /*
                 * //Si está en un hexágono de agua de profundidad 1 y no está
                 * sumergido -> entonces sí if (hex_destino.getProfundidad() ==
                 * total_nivel_destino) { respuesta.CPdirecta = true; } else {
                 * //Si la altura del atacante es mayor que la del defensor no
                 * hay CP if (total_nivel_destino > total_nivel_origen) {
                 * respuesta.CPdirecta = false; } else { //Puede tenerla si hay
                 * un hexágono adyacente del mismo tamaño //Si no están
                 * calculados los intermedios los calculamos if (intermedios ==
                 * null) { intermedios = mapa.intermedios(origen, destino); }
                 * //Hexágonos adyacentes al orígen son los de la primera
                 * posición de los intermedios //Si cualquiera de los adyacentes
                 * ofrece cobertura parcial entonces la hay ArrayList<Phexagono>
                 * adyacentes_al_destino = intermedios.get(intermedios.size() -
                 * 2); for (int a = 0; a < adyacentes_al_destino.size(); a++) {
                 * //Obtenemos el hexágono Hexagono h =
                 * mapa.casilla(adyacentes_al_destino.get(a)); //Obtenemos su
                 * nivel int nivel_hexagono = h.getNivel(); //Si el nivel del
                 * hexágono es igual al del destino entonces hay cobertura
                 * parcial directa (del defensor respecto al atacante) if
                 * (nivel_hexagono == total_nivel_origen) { respuesta.CPdirecta
                 * = true; } } } }
                 */
            }
        }

        //Anotamos los intermedios
        respuesta.intermedios = intermedios;

        return respuesta;
    }

    public static boolean CP(
            Mapa mapa,
            Hexagono hex_destino,
            int suma_nivel_destino,
            int total_nivel_destino,
            int total_nivel_origen,
            ArrayList<Phexagono> adyacentes_al_destino) {
        boolean respuesta = false;
        //Calculamos la CP
        //Si está en el suelo no hay CP
        if (suma_nivel_destino > 0) {
            //Si está en un hexágono de agua de profundidad 1 y no está sumergido -> entonces sí
            if (hex_destino.getProfundidad() == suma_nivel_destino) {
                respuesta = true;
            } else {
                //Si la altura del atacante es mayor que la del defensor no hay CP
                if (total_nivel_destino < total_nivel_origen) {
                    respuesta = false;
                } else {
                    //Puede tenerla si hay un hexágono adyacente del mismo tamaño              
                    //Si cualquiera de los adyacentes ofrece cobertura parcial entonces la hay
                    for (int a = 0; a < adyacentes_al_destino.size(); a++) {
                        //Obtenemos el hexágono
                        Hexagono h = mapa.casilla(adyacentes_al_destino.get(a));
                        //Obtenemos su nivel
                        int nivel_hexagono = h.getNivel();
                        //Si el nivel del hexágono es igual al del destino entonces hay cobertura parcial directa (del defensor respecto al atacante)
                        if (nivel_hexagono == total_nivel_destino) {
                            respuesta = true;
                        }
                    }
                }
            }
        }
        return respuesta;
    }

    public IntervencionLDV interviene(Mapa mapa, Phexagono origen, int nivel_origen, Phexagono destino, int nivel_destino, Phexagono hexagono) {
        IntervencionLDV respuesta = IntervencionLDV.Nada;

        //Si es un terreno adyacente al orígen y mide más que éste entonces bloquea



        return respuesta;
    }
    /*
     * El nivel indica la altura del punto
     */

    public static boolean sumergido(Mapa mapa, Phexagono h, int nivel) {
        //Al parecer si un hexágono de agua tiene nivel mayor que cero, ese agua tiene profundidad cero
        boolean respuesta = false;
        //Si es un hexágono de agua entonces
        Hexagono hex = mapa.casilla(h);
        int profundidad = hex.getProfundidad();
        if (profundidad > 0) {
            //entonces si el nivel es menor o igual que la profundidad -> ese punto está sumergido
            if (nivel <= profundidad) {
                respuesta = true;
            }
        }
        //Si no es de agua no puede estar sumergido
        return respuesta;
    }
}
