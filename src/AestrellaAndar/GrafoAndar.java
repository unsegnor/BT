/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AestrellaAndar;

import Aestrella.I_Coste;
import Aestrella.I_Grafo;
import Aestrella.I_Nodo;
import bt.Hexagono;
import bt.Mapa;
import bt.Phexagono;
import bt.Posicion;
import bt.Reglas;
import static bt.Reglas.ObjetoTipo.BosqueDenso;
import static bt.Reglas.ObjetoTipo.BosqueLigero;
import static bt.Reglas.ObjetoTipo.Escombros;
import static bt.Reglas.TerrenoTipo.Agua;
import static bt.Reglas.TerrenoTipo.Pantanoso;
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

        //Coste real del nodo que nos envían
        CosteAndar coste = (CosteAndar) ((NodoAndar) nodo).getCosteReal();

        //Si andamos podemos girar izquierda, girar derecha, avanzar o retroceder
        NodoAndar giroIzquierda = this.getNodo(p.giroIzquierda());
        giroIzquierda.setCosteReal(coste.sumar(new CosteAndar(Reglas.costeGiro)));
        respuesta.add(giroIzquierda);

        NodoAndar giroDerecha = this.getNodo(p.giroDerecha());
        giroDerecha.setCosteReal(coste.sumar(new CosteAndar(Reglas.costeGiro)));
        respuesta.add(giroDerecha);

        //Alante y atrás sólo las añadimos si las casillas existen
        Posicion delante = p.delante();
        if (mapa.valido(delante.getHexagono())) {
            //Obtenemos el nodo
            NodoAndar avanzar = this.getNodo(delante);
            //Calculamos el coste
            CosteAndar costeAccion = calcularCosteAvanzar(p.getHexagono(), delante.getHexagono());
            //Lo sumamos
            avanzar.setCosteReal(coste.sumar(coste.sumar(costeAccion)));
            //Lo anotamos
            respuesta.add(avanzar);
        }
        Posicion detras = p.atras();
        if (mapa.valido(p.getHexagono())) {
            //Obtenemos el nodo
            NodoAndar retroceder = this.getNodo(delante);
            //Calculamos el coste
            CosteAndar costeAccion = calcularCosteRetroceder(p.getHexagono(), delante.getHexagono());
            //Lo sumamos
            retroceder.setCosteReal(coste.sumar(coste.sumar(costeAccion)));
            //Lo anotamos
            respuesta.add(retroceder);
        }

        return respuesta;
    }

    private CosteAndar calcularCosteAvanzar(Phexagono a, Phexagono b) {
        CosteAndar respuesta = new CosteAndar();
        
        //Obtenemos las casillas 
        //Coste total
        int costeTotal = 1;
        int chequeos = 0;


        //Obtener las casillas
        Hexagono origen = mapa.casilla(a);
        Hexagono destino = mapa.casilla(b);

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
            
            switch(objeto){
                case Escombros:
                    costeEntrada+= 2;
                    chequeos++;
                    break;
                    
                case BosqueLigero:
                    costeEntrada +=2;
                    break;
                case BosqueDenso:
                    costeEntrada +=3;
                    break;
            }
            
            //Lo sumamos todo
            costeTotal += costeEntrada;
            
            //Lo anotamos en la respuesta
            respuesta.setPuntos_de_movimiento(costeTotal);
            
            //Anotamos si hay chequeo
            if(chequeos > 0){
                respuesta.chequeos_de_pilotaje = 1;
            }
        } else {
            respuesta.imposible = true;
        }
        
        return respuesta;
    }

    private CosteAndar calcularCosteRetroceder(Phexagono a, Phexagono b) {
        CosteAndar respuesta = new CosteAndar();
        //Hacia atrás no se puede cambiar de elevación
        //Obtenemos las casillas 
        //Coste total
        int costeTotal = 1;
        int chequeos = 0;


        //Obtener las casillas
        Hexagono origen = mapa.casilla(a);
        Hexagono destino = mapa.casilla(b);

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
            
            switch(objeto){
                case Escombros:
                    costeEntrada+= 2;
                    chequeos++;
                    break;
                    
                case BosqueLigero:
                    costeEntrada +=2;
                    break;
                case BosqueDenso:
                    costeEntrada +=3;
                    break;
            }
            
            //Lo sumamos todo
            costeTotal += costeEntrada;
            
            //Lo anotamos en la respuesta
            respuesta.setPuntos_de_movimiento(costeTotal);
            
            //Anotamos si hay chequeo
            if(chequeos > 0){
                respuesta.chequeos_de_pilotaje = 1;
            }
        } else {
            respuesta.imposible = true;
        }
        
        
        
        return respuesta;
    }
}
