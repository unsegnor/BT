/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaAndar;

import Aestrella.I_Coste;
import Aestrella.I_Grafo;
import Aestrella.I_Nodo;
import bt.DataMech;
import bt.EstadoDeJuego;
import bt.Hexagono;
import bt.Mapa;
import bt.Paso;
import bt.Phexagono;
import bt.Posicion;
import bt.Reglas;
import static bt.Reglas.ObjetoTipo.BosqueDenso;
import static bt.Reglas.ObjetoTipo.BosqueLigero;
import static bt.Reglas.ObjetoTipo.Escombros;
import static bt.Reglas.TerrenoTipo.Agua;
import static bt.Reglas.TerrenoTipo.Pantanoso;
import bt.Reglas.tiposDePaso;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Víctor
 */
class GrafoAndar implements I_Grafo {

    EstadoDeJuego estado;
    Mapa mapa;
    //Correspondencia entre posiciones del mapa y nodos que los representan
    /*
     * Hemos sobreescrito la función HashCode y Equals de Posición para poder utilizarla fácilmente como índice en Hash
     */
    HashMap<Posicion, NodoAndar> nodos;

    GrafoAndar(EstadoDeJuego estado) {
        this.estado = estado;
        this.mapa = estado.mapa;
        nodos = new HashMap<Posicion, NodoAndar>();
    }

    /**
     * Devuelve el nodo correspondiente a la posición si existe. Sino crea uno
     * nuevo y lo devuelve.
     *
     * @param p
     * @return
     */
    public NodoAndar getNodo(Posicion p) {
        NodoAndar respuesta = nodos.get(p);
        if (respuesta == null) {
            respuesta = new NodoAndar(p);
            nodos.put(p, respuesta);
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

        int distancia = mapa.distanciaCasillas(a.getPosicion().getHexagono(), b.getPosicion().getHexagono());

        //Sumamos a la distancia lo que queda para encararse igual que el destino
        int dif = Posicion.distanciaEntreCaras(a.getPosicion().getLado(), b.getPosicion().getLado());

        distancia += dif;

        coste.setPuntos_de_movimiento(distancia);

        return coste;
    }

    @Override
    public ArrayList<I_Nodo> getVecinos(I_Nodo nodo) {
        ArrayList<I_Nodo> respuesta = new ArrayList<I_Nodo>();

        //Extraemos la posición del nodo
        Posicion p = ((NodoAndar) nodo).getPosicion();

        //Coste real del nodo que nos envían
        CosteAndar coste = (CosteAndar) ((NodoAndar) nodo).getCosteReal();

        //Si andamos podemos girar izquierda, girar derecha, avanzar o retroceder
        NodoAndar giroIzquierda = new NodoAndar(p.giroIzquierda());
        giroIzquierda.setPaso(new Paso(tiposDePaso.Izquierda, 1));
        giroIzquierda.setCosteReal(coste.sumar(new CosteAndar(Reglas.costeGiro)));
        respuesta.add(giroIzquierda);

        NodoAndar giroDerecha = new NodoAndar(p.giroDerecha());
        giroDerecha.setPaso(new Paso(tiposDePaso.Derecha, 1));
        giroDerecha.setCosteReal(coste.sumar(new CosteAndar(Reglas.costeGiro)));
        respuesta.add(giroDerecha);

        //Alante y atrás sólo las añadimos si las casillas existen
        Posicion delante = p.delante();
        if (mapa.valido(delante.getHexagono())) {
            //Obtenemos el nodo
            NodoAndar avanzar = new NodoAndar(delante);
            //Calculamos el coste
            CosteAndar costeAccion = calcularCosteAvanzar(p.getHexagono(), delante.getHexagono(), estado);
            //Lo sumamos
            avanzar.setCosteReal(coste.sumar(costeAccion));
            //Anotamos el tipo de paso que llevamos a cabo
            avanzar.setPaso(new Paso(tiposDePaso.Adelante, 1));
            //Lo anotamos
            respuesta.add(avanzar);
        }
        Posicion detras = p.atras();
        if (mapa.valido(detras.getHexagono())) {
            //Obtenemos el nodo
            NodoAndar retroceder = new NodoAndar(detras);
            //Calculamos el coste
            CosteAndar costeAccion = calcularCosteRetroceder(p.getHexagono(), detras.getHexagono(), estado);
            //Lo sumamos
            retroceder.setCosteReal(coste.sumar(costeAccion));
            //Anotamos el tipo de paso que llevamos a cabo
            retroceder.setPaso(new Paso(tiposDePaso.Atras, 1));
            //Lo anotamos            
            respuesta.add(retroceder);
        }

        return respuesta;
    }

    private CosteAndar calcularCosteAvanzar(Phexagono a, Phexagono b, EstadoDeJuego estado) {
        CosteAndar respuesta = new CosteAndar();

        //Obtenemos las casillas 
        //Coste total
        int costeTotal = 1;
        int chequeos = 0;


        //Obtener las casillas
        Hexagono origen = mapa.casilla(a);
        Hexagono destino = mapa.casilla(b);

        //Si la casilla de destino está ocupada por otro mech entonces es imposible
        for (DataMech mech : estado.datos_mechs.getMechs()) {
            if (mech != estado.getMechActual() && mech.getColumna() == b.getColumna() && mech.getFila() == b.getFila()) {
                respuesta.imposible = true;
            }
        }

        if (!respuesta.imposible) {

            //Calcular el cambio de elevación
            int cambioDeElevacion = Math.abs(origen.getNivel() - destino.getNivel());

            if (cambioDeElevacion < 3) {

                //Anotamos el cambio de elevación
                costeTotal += cambioDeElevacion;

                //Calcular coste por entrada en el terreno destino
                int costeEntrada = 0;

                Reglas.TerrenoTipo terreno = destino.getTipoterreno();

                switch (terreno) {
                    case Agua:
                        //En función de la profundidad
                        switch (destino.getProfundidad()) {
                            case 0:
                                costeEntrada += 1;
                                break;
                            case 1:
                                costeEntrada += 2;
                                chequeos++;
                                break;
                            case 2:
                                costeEntrada += 4;
                                chequeos++;
                                break;
                        }
                        break;
                    case Pantanoso:
                        costeEntrada = 2;
                        break;
                }

                //Calcular coste por objeto en el terreno
                Reglas.ObjetoTipo objeto = destino.getTipoobjeto();

                switch (objeto) {
                    case Escombros:
                        costeEntrada += 2;
                        chequeos++;
                        break;

                    case BosqueLigero:
                        costeEntrada += 2;
                        break;
                    case BosqueDenso:
                        costeEntrada += 3;
                        break;
                }

                //Lo sumamos todo
                costeTotal += costeEntrada;

                //Lo anotamos en la respuesta
                respuesta.setPuntos_de_movimiento(costeTotal);

                //Anotamos si hay chequeo
                if (chequeos > 0) {
                    respuesta.chequeos_de_pilotaje = 1;
                }
            }else{
                respuesta.imposible = true;
            }
        } else {
            respuesta.imposible = true;
        }

        return respuesta;
    }

    private CosteAndar calcularCosteRetroceder(Phexagono a, Phexagono b, EstadoDeJuego estado) {
        CosteAndar respuesta = new CosteAndar();
        respuesta.imposible = false;
        //Hacia atrás no se puede cambiar de elevación
        //Obtenemos las casillas 
        //Coste total
        int costeTotal = 1;
        int chequeos = 0;


        //Obtener las casillas
        Hexagono origen = mapa.casilla(a);
        Hexagono destino = mapa.casilla(b);

        //Si la casilla de destino está ocupada por otro mech entonces es imposible
        for (DataMech mech : estado.datos_mechs.getMechs()) {
            if (mech != estado.getMechActual() && mech.getColumna() == b.getColumna() && mech.getFila() == b.getFila()) {
                respuesta.imposible = true;
            }
        }

        if (!respuesta.imposible) {
            //Calcular el cambio de elevación
            int cambioDeElevacion = Math.abs(origen.getNivel() - destino.getNivel());

            //Si no hay cambio de elevación entonces podemos movernos, sino es imposible
            if (cambioDeElevacion == 0) {

                //Anotamos el cambio de elevación
                costeTotal += cambioDeElevacion;

                //Calcular coste por entrada en el terreno destino
                int costeEntrada = 0;

                Reglas.TerrenoTipo terreno = destino.getTipoterreno();

                switch (terreno) {
                    case Agua:
                        //En función de la profundidad
                        switch (destino.getProfundidad()) {
                            case 0:
                                costeEntrada += 1;
                                break;
                            case 1:
                                costeEntrada += 2;
                                chequeos++;
                                break;
                            case 2:
                                costeEntrada += 4;
                                chequeos++;
                                break;
                        }
                        break;
                    case Pantanoso:
                        costeEntrada = 2;
                        break;
                }

                //Calcular coste por objeto en el terreno
                Reglas.ObjetoTipo objeto = destino.getTipoobjeto();

                switch (objeto) {
                    case Escombros:
                        costeEntrada += 2;
                        chequeos++;
                        break;

                    case BosqueLigero:
                        costeEntrada += 2;
                        break;
                    case BosqueDenso:
                        costeEntrada += 3;
                        break;
                }

                //Lo sumamos todo
                costeTotal += costeEntrada;

                //Lo anotamos en la respuesta
                respuesta.setPuntos_de_movimiento(costeTotal);

                //Anotamos si hay chequeo
                if (chequeos > 0) {
                    respuesta.chequeos_de_pilotaje = 1;
                }
            }else{
                respuesta.imposible = true;
            }
        } else {
            respuesta.imposible = true;
        }



        return respuesta;
    }

    @Override
    public I_Coste calcularCosteReal(I_Nodo actual, I_Nodo vecino) {
        CosteAndar respuesta = null;

        //Comprobar la acción que ha realizado el nodo y actuar en consecuencia
        NodoAndar a = (NodoAndar) actual;
        NodoAndar b = (NodoAndar) vecino;

        switch (a.getPaso().getTipoDePaso()) {
            case Adelante:
                respuesta = calcularCosteAvanzar(a.getPosicion().getHexagono(), b.getPosicion().getHexagono(), estado);
                break;

            case Atras:
                respuesta = calcularCosteRetroceder(a.getPosicion().getHexagono(), b.getPosicion().getHexagono(), estado);
                break;

            case Derecha:
                respuesta = new CosteAndar(Reglas.costeGiro);
                break;

            case Izquierda:
                respuesta = new CosteAndar(Reglas.costeGiro);
                break;
        }

        return respuesta;
    }
}
