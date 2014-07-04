/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaMov;

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
    HashMap<Posicion, NodoMov> nodos;

    GrafoAndar(EstadoDeJuego estado) {
        this.estado = estado;
        this.mapa = estado.mapa;
        nodos = new HashMap<Posicion, NodoMov>();
    }

    /**
     * Devuelve el nodo correspondiente a la posición si existe. Sino crea uno
     * nuevo y lo devuelve.
     *
     * @param p
     * @return
     */
    public NodoMov getNodo(Posicion p) {
        NodoMov respuesta = nodos.get(p);
        if (respuesta == null) {
            respuesta = new NodoMov(p);
            nodos.put(p, respuesta);
        }
        return respuesta;
    }

    @Override
    public I_Coste calcularCosteHeuristico(I_Nodo vecino, I_Nodo destino) {
        //El coste heurístico es la distancia entre los dos hexágonos independientemente de su encaramiento
        CosteMov coste = new CosteMov();

        //Casteamos los nodos
        NodoMov a = (NodoMov) vecino;
        NodoMov b = (NodoMov) destino;

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
        Posicion p = ((NodoMov) nodo).getPosicion();

        //Coste real del nodo que nos envían
        CosteMov coste = (CosteMov) ((NodoMov) nodo).getCosteReal();

        //Si andamos podemos girar izquierda, girar derecha, avanzar o retroceder
        NodoMov giroIzquierda = new NodoMov(p.giroIzquierda());
        giroIzquierda.setPaso(new Paso(tiposDePaso.Izquierda, 1));
        giroIzquierda.setCosteReal(coste.sumar(new CosteMov(Reglas.costeGiro)));
        respuesta.add(giroIzquierda);

        NodoMov giroDerecha = new NodoMov(p.giroDerecha());
        giroDerecha.setPaso(new Paso(tiposDePaso.Derecha, 1));
        giroDerecha.setCosteReal(coste.sumar(new CosteMov(Reglas.costeGiro)));
        respuesta.add(giroDerecha);

        //Alante y atrás sólo las añadimos si las casillas existen
        Posicion delante = p.delante();
        if (mapa.valido(delante.getHexagono())) {
            //Obtenemos el nodo
            NodoMov avanzar = new NodoMov(delante);
            //Calculamos el coste
            CosteMov costeAccion = calcularCosteAvanzar(p.getHexagono(), delante.getHexagono(), estado);

            //Lo tenemos en cuenta si el coste no es imposible (ahorramos cálculo y nodos inútiles)
            if (!costeAccion.imposible) {
                //Lo sumamos
                avanzar.setCosteReal(coste.sumar(costeAccion));
                //Anotamos el tipo de paso que llevamos a cabo
                avanzar.setPaso(new Paso(tiposDePaso.Adelante, 1));
                //Lo anotamos
                respuesta.add(avanzar);
            }
        }
        Posicion detras = p.atras();
        if (mapa.valido(detras.getHexagono())) {
            //Obtenemos el nodo
            NodoMov retroceder = new NodoMov(detras);
            //Calculamos el coste
            CosteMov costeAccion = calcularCosteRetroceder(p.getHexagono(), detras.getHexagono(), estado);

            //Si el coste es imposible no la añadimos
            if (!costeAccion.imposible) {

                //Lo sumamos
                retroceder.setCosteReal(coste.sumar(costeAccion));
                //Anotamos el tipo de paso que llevamos a cabo
                retroceder.setPaso(new Paso(tiposDePaso.Atras, 1));
                //Lo anotamos            
                respuesta.add(retroceder);
            }
        }

        return respuesta;
    }

    private CosteMov calcularCosteAvanzar(Phexagono a, Phexagono b, EstadoDeJuego estado) {
        CosteMov respuesta = new CosteMov();

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

                //Si el destino está ardiendo sumamos dos puntos de calor generado por entrar
                if (destino.isFuego()) {
                    respuesta.calor_generado +=2;
                }

                //Anotamos si hay chequeo
                if (chequeos > 0) {
                    respuesta.chequeos_de_pilotaje = 1;
                }
            } else {
                respuesta.imposible = true;
            }
        } else {
            respuesta.imposible = true;
        }

        return respuesta;
    }

    private CosteMov calcularCosteRetroceder(Phexagono a, Phexagono b, EstadoDeJuego estado) {
        CosteMov respuesta = new CosteMov();
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
            } else {
                respuesta.imposible = true;
            }
        } else {
            respuesta.imposible = true;
        }



        return respuesta;
    }

    @Override
    public I_Coste calcularCosteReal(I_Nodo actual, I_Nodo vecino) {
        CosteMov respuesta = null;

        //Comprobar la acción que ha realizado el nodo y actuar en consecuencia
        NodoMov a = (NodoMov) actual;
        NodoMov b = (NodoMov) vecino;

        switch (a.getPaso().getTipoDePaso()) {
            case Adelante:
                respuesta = calcularCosteAvanzar(a.getPosicion().getHexagono(), b.getPosicion().getHexagono(), estado);
                break;

            case Atras:
                respuesta = calcularCosteRetroceder(a.getPosicion().getHexagono(), b.getPosicion().getHexagono(), estado);
                break;

            case Derecha:
                respuesta = new CosteMov(Reglas.costeGiro);
                break;

            case Izquierda:
                respuesta = new CosteMov(Reglas.costeGiro);
                break;
        }

        return respuesta;
    }
}
