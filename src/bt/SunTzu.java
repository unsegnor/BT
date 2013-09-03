/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import Aestrella.I_Nodo;
import Aestrella.NodosEnRango;
import AestrellaAndar.CosteAndar;
import AestrellaAndar.NodoAndar;
import bt.Reglas.Fase;
import bt.Reglas.tiposDeMovimiento;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que centraliza toda la elaboración de la estrategia.
 *
 * @author Víctor
 */
public class SunTzu {

    private static int p_encaramiento = 10;
    private static int p_nivel = 0;
    private static int p_cobertura = 1000;
    private static int p_cobertura_enemigo = 1000;
    private static double p_distancia = 0.001;
    private static double p_alcance = 10000;

    public static Accion siguienteAccion(int jugador, Fase fase) {


        EstadoDeJuego estado = leer_estado_actual(jugador, fase);


        //Responder en función de la fase
        Accion resultado = null;

        System.out.println("Leido estado. Jugador " + jugador + " fase " + fase.name());

        //Determinamos las condiciones que se deben tener en cuenta
        Condiciones condiciones = new Condiciones();





        //Determinamos la forma de comparar las posiciones según si tenemos o no Armas de Fuego

        //Comprobar si tenemos o no potencia de fuego

        ComparaPosicionesConArmas conArmas = new ComparaPosicionesConArmas();
        ComparaPosicionesSinArmas sinArmas = new ComparaPosicionesSinArmas();


        if (estado.datos_def_mechs[jugador].getnArmas() == 0) {
            condiciones.comparador_de_posiciones = sinArmas;
            condiciones.armas = false;
        } else {
            condiciones.comparador_de_posiciones = conArmas;
            condiciones.armas = true;
        }


        switch (fase) {
            case Movimiento:
                resultado = responderAMovimiento(estado, condiciones);
                break;
            case Reaccion:
                resultado = responderAReaccion(estado, condiciones);
                break;
            case AtaqueArmas:
                resultado = responderAAtaqueArmas(estado, condiciones);
                break;
            case AtaqueFisico:
                resultado = responderAAtaqueFisico(estado, condiciones);
                break;
            case FinalTurno:
                resultado = responderAFinalTurno(estado, condiciones);
                break;
        }



        //Devolver el mejor siguiente movimiento


        return resultado;
    }

    private static Accion responderAMovimiento(EstadoDeJuego estado, Condiciones condiciones) {
        Movimiento respuesta = null;


        //TODO Si somos los últimos o primeros en mover debe influir en nuestra decisión



        //Comprobar posiciones de los mechs enemigos operativos
        //y la nuestra

        ArrayList<DataMech> enemigos_operativos = new ArrayList<DataMech>();
        DataMech mech_actual = null;

        DataMech[] mechs = estado.datos_mechs.getMechs();

        for (DataMech mech : mechs) {
            if (mech.getnJugador() == estado.jugador) {
                mech_actual = mech;
            } else {
                if (mech.isOperativo()) {
                    enemigos_operativos.add(mech);
                }
            }
        }



        ArrayList<PosicionAccion> posiciones_alcanzables;

        //Si no tenemos armas
        if (!condiciones.armas) {

            DataMech enemigo = enemigos_operativos.get(0);

            Posicion p_enemigo = new Posicion(enemigo.getColumna(), enemigo.getFila(), enemigo.getEncaramientoMech());

            int epandar = estado.datos_def_mechs[enemigo.getnJugador()].getPuntosMovAndando();
            int epcorrer = estado.datos_def_mechs[enemigo.getnJugador()].getPuntosMovCorriendo();
            int epsaltar = estado.datos_def_mechs[enemigo.getnJugador()].getPuntosMovSaltando();

            int max_radio = Math.max(epandar, Math.max(epcorrer, epsaltar));

            //Buscar la mejor casilla del mapa entre las cercanas al enemigo
            //Seleccionar las casillas cercanas al enemigo
            ArrayList<Phexagono> cercanas = estado.mapa.cercanas(p_enemigo.getHexagono(), max_radio);


            //Obtenemos las posiciones alcanzables por el enemigo
            posiciones_alcanzables = obtenerPosicionesAlcanzables(enemigo, estado);

        } else {
            posiciones_alcanzables = obtenerPosicionesAlcanzables(mech_actual, estado);
        }

        Posicion p_actual = new Posicion(mech_actual.getColumna(), mech_actual.getFila(), mech_actual.getEncaramientoMech());


        //Evaluar todas las posiciones y quedarnos con la mejor, incluida la actual
        PosEval mejor = evaluarPosicion(p_actual, estado, condiciones);
        PosicionAccion mejorPA = new PosicionAccion(p_actual, tiposDeMovimiento.Inmovil);
        for (PosicionAccion pa : posiciones_alcanzables) {
            boolean valida = true;
            //Descartamos las posiciones en las que hay un enemigo
            for (DataMech enemigo : enemigos_operativos) {
                if (pa.posicion.getHexagono().getColumna() == enemigo.getColumna()
                        && pa.posicion.getHexagono().getFila() == enemigo.getFila()) {
                    valida = false;
                }


            }
            if (valida) {
                //Evaluar la posición
                PosEval datos_de_posicion = evaluarPosicion(pa.posicion, estado, condiciones);


                if (mejor == null) {
                    mejor = datos_de_posicion;
                } else {
                    //Comprobamos si es mejor que la mejor actual
                    if (condiciones.comparador_de_posiciones.compare(datos_de_posicion, mejor) < 0) {

                        //Si estamos en modo sin armas tenemos que comprobar que efectivamente existe una ruta hasta la casilla 
                        //ya que el conjunto inicial no se consigue de los sitios a los que podemos acceder sino a los que puede acceder el enemigo.
                        Aestrella.Resultado rutaAcasilla = AestrellaAndar.AstrellaAndar.calcularRutaAndando(p_actual, pa.posicion, estado);

                        if (!condiciones.armas) {
                            //Sólo si existe la ruta la tenemos en cuenta
                            if (rutaAcasilla != null) {
                                mejor = datos_de_posicion;
                                mejorPA = pa;
                            }
                        } else {
                            //Si es mejor la sustituimos
                            mejor = datos_de_posicion;
                            mejorPA = pa;
                        }
                    }
                }
            }
        }


        //Ya tenemos la mejor casilla a la que podemos llegar



        //Comprobamos qué tipo de acción es la que vamos a utilizar y obtenemos la ruta necesaria
        respuesta = new Movimiento();
        //Determinamos el tipo de movimiento
        respuesta.setTipo(mejorPA.tipoMovimiento);

        //Determinamos el objetivo
        respuesta.setDestino(mejorPA.posicion.getHexagono());

        //Determinamos el lado
        respuesta.setLado_destino(mejorPA.posicion.getLado());

        //Inicializamos la ruta
        respuesta.setRuta(new Ruta());

        //Calculamos los puntos máximos de movimiento y en caso necesario cortamos las rutas
        int pandar = mech_actual.getInformacion_adicional().getPuntos_andar();
        int pcorrer = mech_actual.getInformacion_adicional().getPuntos_correr();
        int psaltar = mech_actual.getInformacion_adicional().getPuntos_saltar();



        switch (mejorPA.tipoMovimiento) {
            case Andar:
                //Calculamos la ruta hasta el objetivo 
                //TODO evitar este cálculo utilizando los conjuntos de antes para saber hasta dónde podemos llegar
                Aestrella.Resultado r = AestrellaAndar.AstrellaAndar.calcularRutaAndando(p_actual, mejorPA.posicion, estado);

                ArrayList<I_Nodo> nodos_ruta = r.getRuta();

                int lnr = nodos_ruta.size();

                boolean posible = true;
                Posicion nuevodestino = null;

                //Componer la ruta ( comenzamos en el 1 porque la primera casilla es el orígen y no forma parte de la ruta )
                for (int i = 1; i < lnr && posible; i++) {
                    NodoAndar nodo = (NodoAndar) nodos_ruta.get(i);
                    if (((CosteAndar) nodo.getCosteReal()).getPuntos_de_movimiento() > pandar) {
                        posible = false;
                    } else {
                        respuesta.getRuta().getPasos().add(nodo.getPaso());
                        nuevodestino = nodo.getPosicion();
                    }
                }

                //Si posible es falso significa que hemos acortado la ruta y tenemos que modificar el destino
                if (!posible) {
                    respuesta.setDestino(nuevodestino.getHexagono());
                    respuesta.setLado_destino(nuevodestino.getLado());
                }
                break;

            case Correr:
                break;

            case Saltar:
                break;
            case Inmovil:
                respuesta = new NoMoverse();
                break;
        }


        return respuesta;
    }

    /**
     * La estrategia para la fase de movimiento será: Buscar la espalda del
     * enemigo Encararlo Buscar cobertura Estar más alto que el enemigo para
     * ignorar su cobertura
     *
     *
     * @param estado
     * @return
     */
    private static Accion responderAMovimiento2(EstadoDeJuego estado) {
        Accion resultado = null;


        //TODO Si somos los últimos o primeros en mover debe influir en nuestra decisión



        //Comprobar posiciones de los mechs enemigos operativos
        //y la nuestra

        ArrayList<DataMech> enemigos_operativos = new ArrayList<DataMech>();
        DataMech mech_actual = null;

        DataMech[] mechs = estado.datos_mechs.getMechs();

        for (DataMech mech : mechs) {
            if (mech.getnJugador() == estado.jugador) {
                mech_actual = mech;
            } else {
                if (mech.isOperativo()) {
                    enemigos_operativos.add(mech);
                }
            }
        }

        //Seleccionar un subconjunto de casillas alrededor del mech (según su capacidad de movimiento) o evaluarlas todas
        Phexagono posicion_mech = new Phexagono(mech_actual.getColumna(), mech_actual.getFila());
        int pAndar = mech_actual.getInformacion_adicional().getPuntos_andar();
        int pCorrer = mech_actual.getInformacion_adicional().getPuntos_correr();
        int pSaltar = mech_actual.getInformacion_adicional().getPuntos_saltar();
        //Los puntos de movimiento máximos de los 3
        int max_pm = Math.max(pAndar, Math.max(pCorrer, pSaltar));

        ArrayList<Phexagono> posiciones_cercanas = estado.mapa.cercanas(posicion_mech, max_pm);
        System.out.println("Posiciones cercanas a " + posicion_mech + " con rango " + max_pm);

        //Ordenar las posiciones en función de la ventaja que nos confieren
        ArrayList<Evaluacion<Phexagono>> evaluaciones = new ArrayList<Evaluacion<Phexagono>>();

        //Evaluar todas las casillas
        for (Phexagono p : posiciones_cercanas) {
            //Si es distinta de la casilla de un enemigo
            boolean valida = true;
            for (DataMech d : enemigos_operativos) {
                if (p.getColumna() == d.getColumna() && p.getFila() == d.getFila()) {
                    valida = false;
                }
            }
            if (valida) {
                //Evaluar y añadir como posible
                System.out.println(p);
                double bondad = evaluar(p, estado, enemigos_operativos);
                //TODO lo quito por modificación en evaluaciones:    evaluaciones.add(new Evaluacion(p, bondad));
            }
        }

        //Ordenar las casillas
        Collections.sort(evaluaciones, new OrdenadorDeEvaluaciones());

        //Buscar una ruta desde la posición actual a las mejores hasta hallar la primera
        //TODO de momento saltamos, luego hay que implementar el correr y andar

        //Para cada posición ordenadas de mejor a peor buscar una ruta andando, corriendo o saltando
        //en el momento en que se encuentre ese será el movimiento a realizar

        boolean adjudicado = false;
        for (int i = 0; i < evaluaciones.size() && !adjudicado; i++) {

            Evaluacion e = evaluaciones.get(i);
            Phexagono pe = (Phexagono) e.cosa;
            System.out.println(pe + " -> " + e.valor);


            //Comprobar si se puede llegar andando
            Ruta rutaAndando = PathFinder.buscaRutaAndando(estado.mapa, posicion_mech, pe, pAndar);
            if (rutaAndando != null) {
                //Se puede andar -> andamos
                adjudicado = true;
            } else {
                //Sino comprobar si se puede llegar corriendo
                Ruta rutaCorriendo = PathFinder.buscaRutaCorriendo(estado.mapa, posicion_mech, pe, pCorrer);
                if (rutaCorriendo != null) {
                    //Se puede llegar corriendo -> correr
                    adjudicado = true;
                } else {
                    //Sino comprobar si se puede llegar saltando
                    boolean rutaSaltando = PathFinder.buscaRutaSaltando(estado.mapa, posicion_mech, pe, pSaltar);
                    if (rutaSaltando) {

                        //Se puede llegar saltando -> saltar
                        adjudicado = true;
                        //Generamos el salto
                        Salto salto = new Salto();
                        salto.setTipo(Reglas.tiposDeMovimiento.Saltar);
                        salto.setDestino(pe);
                        salto.setUsaMASC(false);
                        salto.setRuta(null);

                        //Seleccionar el lado que encare al enemigo
                        //TODO si hay varios enemigos decidir a cuál nos encaramos
                        //TODO también hay que encarar sólo si hay línea de visión, sino no es necesario
                        DataMech enemigo = enemigos_operativos.get(0);
                        Phexagono posicion_enemigo = new Phexagono(enemigo.getColumna(), enemigo.getFila());
                        int lado_encara_enemigo = Mapa.encarar(pe, posicion_enemigo);
                        salto.setLado_destino(lado_encara_enemigo);


                        //El resultado es el salto
                        resultado = salto;



                    }
                }
            }


        }





        //Movernos a aquella a la que se pueda llegar


        return resultado;
    }

    private static EstadoDeJuego leer_estado_actual(int jugador, Fase fase) {
        EstadoDeJuego estado = new EstadoDeJuego();
        estado.jugador = jugador;
        estado.fase = fase;


        //Leer el mapa
        DataMapa dmapa = Cargador.cargarMapa(jugador);
        Mapa mapa = new Mapa(dmapa);
        //Escribir el mapa
        estado.mapa = mapa;

        //Leer los mechs
        DataMechs dm = Cargador.cargarMech(jugador);
        //Escribirlos
        estado.datos_mechs = dm;

        //Leer los jugadores e iniciativas
        DataIniciativa ini = Cargador.cargarIniciativa(jugador);

        //Escribir la iniciativa
        estado.iniciativa = ini.orden;

        int njugadores = ini.orden.length;

        //Leer las descripciones de los mechs
        DataDefMech[] dfm = new DataDefMech[njugadores];
        for (int i = 0; i < njugadores; i++) {
            dfm[i] = Cargador.cargarDefMech(jugador, i);
        }

        //Escribir defmechs en estado
        estado.datos_def_mechs = dfm;

        return estado;
    }

    /**
     * Evalua la bondad de una posición en el mapa
     *
     * @param p
     * @param estado
     * @param enemigos_operativos
     * @return
     */
    private static double evaluar(Phexagono p, EstadoDeJuego estado, ArrayList<DataMech> enemigos_operativos) {
        //Aquí es donde puntuamos lo bien o mal que podría estar la posición respecto a los enemigos
        double resultado = 0;
        //Para cada enemigo
        for (DataMech enemigo : enemigos_operativos) {
            resultado += evaluar(p, estado, enemigo);
        }

        return resultado;
    }

    //Se evalúa la posición con el peor encaramiento para nosotros, en el peor de los casos
    private static double evaluar_posicion(Phexagono p, EstadoDeJuego estado, Phexagono p_enemigo) {

        Hexagono h_enemigo = estado.mapa.casilla(p_enemigo);
        Hexagono h = estado.mapa.casilla(p);

        double resultado = 0;

        //Es mejor que esté por encima, cuanto más alto mejor
        int diferencia_nivel = h.getNivel() - h_enemigo.getNivel();
        resultado += diferencia_nivel * p_nivel;

        //Procuramos que haya cobertura
        //Calculamos la LDV y cobertura
        ResultadoLDV rLDV = LDV.calcularLDV(estado.mapa, p, 1, p_enemigo, 1);

        if (rLDV.LDV) {
            if (rLDV.CPdirecta) {
                resultado += -p_cobertura_enemigo;
            }
            if (rLDV.CPinversa) {
                resultado += +p_cobertura;
            }
        }

        //Es mejor estar cerca del enemigo que lejos
        resultado -= estado.mapa.distancia(p, p_enemigo) * p_distancia;

        return resultado;
    }

    private static double evaluar_todo(Phexagono p, EstadoDeJuego estado, DataMech enemigo) {

        Phexagono p_enemigo = new Phexagono(enemigo.getColumna(), enemigo.getFila());
        Hexagono h_enemigo = estado.mapa.casilla(p_enemigo);
        Hexagono h = estado.mapa.casilla(p);

        //Aquí es donde puntuamos lo bien o mal que podría estar la posición respecto un ememigo concreto
        double resultado = 0;

        //Es mejor que esté detrás de él que delante
        //Depende hacia dónde esté mirando el enemigo
        int encaramiento_enemigo = enemigo.getEncaramientoMech();

        resultado += evaluar_encaramiento(p, estado, p_enemigo, encaramiento_enemigo);

        resultado += evaluar_posicion(p, estado, p_enemigo);

        return resultado;
    }

    private static double evaluar_encaramiento(Phexagono p, EstadoDeJuego estado, Phexagono p_enemigo, int encaramiento_enemigo) {
        double resultado = 0;
        //Si está mirando hacia arriba
        if (encaramiento_enemigo == 1) {
            //Y la posición está por delante de él entonces la penalizamos
            if (p.getFila() < p_enemigo.getFila()) {
                resultado += -p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += p_encaramiento;
            }
        } else if (encaramiento_enemigo == 2) {
            //Si está mirando hacia el noreste
            if (p.getFila() > p_enemigo.getFila()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
            if (p.getColumna() < p_enemigo.getColumna()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
        } else if (encaramiento_enemigo == 3) {
            //Si está mirando hacia el sureste
            if (p.getFila() < p_enemigo.getFila()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
            if (p.getColumna() < p_enemigo.getColumna()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
        } else if (encaramiento_enemigo == 4) {
            //Si está mirando hacia el sur
            if (p.getFila() < p_enemigo.getFila()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
        } else if (encaramiento_enemigo == 5) {
            //Si está mirando hacia el suroeste
            if (p.getFila() < p_enemigo.getFila()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
            if (p.getColumna() > p_enemigo.getColumna()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
        } else if (encaramiento_enemigo == 6) {
            //Si está mirando hacia el noroeste
            if (p.getFila() > p_enemigo.getFila()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
            if (p.getColumna() > p_enemigo.getColumna()) {
                resultado += p_encaramiento;
            } else {
                //Si está por detrás la incentivamos
                resultado += -p_encaramiento;
            }
        }
        return resultado;
    }

    private static double evaluar2(Phexagono p, EstadoDeJuego estado, DataMech enemigo) {
        double resultado = 0;

        //Si el enemigo ya ha movido evaluamos con su posición y encaramiento, sino sólo con su posición actual
        boolean hamovido = false;

        int nj = enemigo.getnJugador(); //Número de jugador del enemigo

        if (turno(estado, nj) < turno(estado, estado.jugador)) {
            hamovido = true;
        }

        if (hamovido) {

            resultado = evaluar_todo(p, estado, enemigo);

        } else {
            //Sino obtenemos el alcance del enemigo

            int pAndando = estado.datos_def_mechs[nj].getPuntosMovAndando();
            int pCorriendo = estado.datos_def_mechs[nj].getPuntosMovCorriendo();
            int pSaltando = estado.datos_def_mechs[nj].getPuntosMovSaltando();

            int alcance_enemigo = Math.max(pAndando, Math.max(pCorriendo, pSaltando));


            Phexagono p_enemigo = new Phexagono(enemigo.getColumna(), enemigo.getFila());
            Hexagono h_enemigo = estado.mapa.casilla(p_enemigo);
            Hexagono h = estado.mapa.casilla(p);


            //Obtenemos las casillas a su alcance
            ArrayList<Phexagono> cercanas = estado.mapa.cercanas(p_enemigo, alcance_enemigo);


            //Evaluarlas todas con el peor encaramiento posible para nosotros
            //Simplemente evaluamos las posiciones que puede tomar el enemigo como si fueran nuestras, con la misma función
            //como si nosotros fuéramos el enemigo
            //Evaluar todas las casillas

            //Utilizaremos como enemigo la posición que estamos evaluando... las posiciones tienen que ser hexágono con lado, sino no se puede

            //ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();

            //Tenemos que evaluar cada posición con cada encaramiento nuestro posible
            //Los encaramientos los calculamos luego

            double min_eval = Double.MAX_VALUE;

            for (Phexagono pe : cercanas) {
                //Si es distinta de la casilla actual
                boolean valida = true;
                if (pe.getColumna() == p.getColumna() && pe.getFila() == p.getFila()) {
                    valida = false;
                }
                if (valida) {
                    //Evaluar y añadir como posible
                    double bondad = evaluar_posicion(p, estado, pe);
                    //Almacenamos la peor
                    if (bondad < min_eval) {
                        min_eval = bondad;
                    }
                }
            }


            //Nos ponemos en el peor de los casos, nos quedamos con la casilla con peor evaluación para nosotros
            resultado = min_eval;
        }

        return resultado;
    }

    private static double evaluar(Phexagono p, EstadoDeJuego estado, DataMech enemigo) {
        double resultado = 0;

        //Si el enemigo ya ha movido evaluamos con su posición y encaramiento, sino sólo con su posición actual
        boolean hamovido = false;

        int nj = enemigo.getnJugador(); //Número de jugador del enemigo

        if (turno(estado, nj) < turno(estado, estado.jugador)) {
            hamovido = true;
        }

        if (hamovido) {

            resultado = evaluar_todo(p, estado, enemigo);

        } else {
            //Sino obtenemos el alcance del enemigo

            int pAndando = estado.datos_def_mechs[nj].getPuntosMovAndando();
            int pCorriendo = estado.datos_def_mechs[nj].getPuntosMovCorriendo();
            int pSaltando = estado.datos_def_mechs[nj].getPuntosMovSaltando();

            int alcance_enemigo = Math.max(pAndando, Math.max(pCorriendo, pSaltando));


            Phexagono p_enemigo = new Phexagono(enemigo.getColumna(), enemigo.getFila());
            Hexagono h_enemigo = estado.mapa.casilla(p_enemigo);
            Hexagono h = estado.mapa.casilla(p);


            //Obtenemos las casillas a su alcance
            ArrayList<Phexagono> cercanas_al_enemigo = estado.mapa.cercanas(p_enemigo, alcance_enemigo);

            //Tienen prioridad las casillas que están fuera de su alcance

            //Evaluarlas todas con el peor encaramiento posible para nosotros
            //Simplemente evaluamos las posiciones que puede tomar el enemigo como si fueran nuestras, con la misma función
            //como si nosotros fuéramos el enemigo
            //Evaluar todas las casillas

            //Utilizaremos como enemigo la posición que estamos evaluando... las posiciones tienen que ser hexágono con lado, sino no se puede

            //ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();

            //Tenemos que evaluar cada posición con cada encaramiento nuestro posible
            //Los encaramientos los calculamos luego

            double min_eval = Double.MAX_VALUE;
            boolean alalcance = false;
            for (Phexagono pe : cercanas_al_enemigo) {
                //Si es distinta de la casilla actual
                boolean valida = true;
                if (pe.getColumna() == p.getColumna() && pe.getFila() == p.getFila()) {
                    valida = false;
                    alalcance = true;
                }
                if (valida) {
                    //Evaluar y añadir como posible
                    double bondad = evaluar_posicion(p, estado, pe);
                    //System.out.println(p + " con el enemigo en " + pe + " vale " + bondad);
                    //Almacenamos la peor
                    if (bondad < min_eval) {
                        min_eval = bondad;
                    }
                }
            }

            //Si la casilla está al alcance del enemigo entonces tiene penalización
            //porque le puede tomar la espalda
            if (alalcance) {
                min_eval += -p_alcance;
                //System.out.println("La casilla " + p + " está al alcance del enemigo.");
            }

            //Nos ponemos en el peor de los casos, nos quedamos con la casilla con peor evaluación para nosotros
            resultado = min_eval;
            //System.out.println("Mínimo valor de " + p + " es " + resultado);
        }

        return resultado;
    }

    private static int turno(EstadoDeJuego estado, int nj) {
        int respuesta = -1;

        int njugadores = estado.iniciativa.length;

        for (int i = 0; i < njugadores; i++) {
            //Recorremos los turnos hasta que encontramos el del jugador
            if (estado.iniciativa[i] == nj) {
                respuesta = i;
            }
        }

        return respuesta;
    }

    private static Accion responderAReaccion(EstadoDeJuego estado, Condiciones condiciones) {

        //TODO Encarar hacia donde está el enemigo más cercano

        //Obtener el mech actual
        DataMech mech_actual = estado.getMechActual();

        //Obtener la posición actual
        Phexagono posicion_mech = new Phexagono(mech_actual.getColumna(), mech_actual.getFila());

        //Obtener encaramiento actual
        int encaramiento_actual = mech_actual.getEncaramientoTorso();

        //Determinamos los encaramientos posibles
        int[] encaramientos = new int[3];

        encaramientos[0] = encaramiento_actual;



        //De momento nos quedamos igual
        return new ReaccionarIgual();


    }

    private static Accion responderAAtaqueArmas(EstadoDeJuego estado, Condiciones condiciones) {
        return new DispararConTodoAlMasCercano(estado);
    }

    private static Accion responderAAtaqueFisico(EstadoDeJuego estado, Condiciones condiciones) {
        return new NoAtacar();
    }

    private static Accion responderAFinalTurno(EstadoDeJuego estado, Condiciones condiciones) {
        return new NoHacerNada();
    }

    //Una posición se evalúa respecto de un enemigo
    private static PosEval evaluarPosicion(Posicion posicion, EstadoDeJuego estado, Condiciones condiciones) {
        //Tenemos que rellenar el PosEval
        PosEval respuesta = new PosEval();

        //TODO de momento seleccionamos el enemigo como el mech más cercano operativo que no somos nosotros
        //O aquel al que más daño podemos hacer...


        Posicion p_enemigo;

        //Así que obtenemos todos los mechs operativos distintos de nosotros, sus posiciones y las distancias hasta ellos
        ArrayList<DataMech> enemigos_operativos = new ArrayList<DataMech>();
        DataMech mech_actual = null;

        DataMech[] mechs = estado.datos_mechs.getMechs();
        Mapa mapa = estado.mapa;

        for (DataMech mech : mechs) {
            if (mech.getnJugador() == estado.jugador) {
                mech_actual = mech;
            } else {
                if (mech.isOperativo()) {
                    enemigos_operativos.add(mech);
                    //Obtener la posición
                    Posicion p = new Posicion(mech.getColumna(), mech.getFila(), mech.getEncaramientoMech());
                }
            }
        }

        DataMech enemigo = enemigos_operativos.get(0);
        p_enemigo = new Posicion(enemigo.getColumna(), enemigo.getFila(), enemigo.getEncaramientoMech());

        DataMech actual = mech_actual;

        //Si el enemigo ya ha movido evaluamos con su posición y encaramiento
        boolean hamovido = false;

        int nj = enemigo.getnJugador(); //Número de jugador del enemigo

        if (turno(estado, nj) < turno(estado, estado.jugador)) {
            hamovido = true;
        }

        if (hamovido) {
            respuesta = evaluarPosicionRelativa(mapa, posicion, p_enemigo);
        } else {
            //Si aún no ha movido entonces tenemos que evaluar todas las posiciones a las que puede llegar el enemigo y quedarnos con la peor para nosotros
            ArrayList<PosicionAccion> posiciones_alcanzables = obtenerPosicionesAlcanzables(enemigo, estado);

            PosEval peor = null;

            //Ahora evaluamos nuestra posición en relación a todas las alcanzables por el enemigo y nos quedamos con la peor
            for (PosicionAccion pene : posiciones_alcanzables) {
                PosEval eval = evaluarPosicionRelativa(mapa, posicion, pene.posicion);

                if (peor == null) {
                    peor = eval;
                } else {
                    //Comparamos la posición con la peor
                    if (condiciones.comparador_de_posiciones.compare(eval, peor) > 0) {
                        //Si la posición que estamos evaluando para nsotros es peor respecto a otra del enemigo, contamos esa evaluación
                        peor = eval;
                    }
                }

            }

            //Devolvemos la peor que hayamos encontrado
            respuesta = peor;
        }

        return respuesta;
    }

    private static PosEval evaluarPosicionRelativa(Mapa mapa, Posicion posicion, Posicion p_enemigo) {
        PosEval respuesta = new PosEval();


        //Distancia
        respuesta.distancia_al_enemigo = mapa.distanciaCasillas(posicion.getHexagono(), p_enemigo.getHexagono());

        //Lado hacia el enemigo
        int lado_enemigo = mapa.encararA(posicion.getHexagono(), p_enemigo.getHexagono());

        //Diferencia entre el lado enemigo y el actual 
        respuesta.giros_para_ecarar_enemigo = Posicion.distanciaEntreCaras(posicion.getLado(), lado_enemigo);

        //Obtener lado hacia el que debe mirar el enemigo para vernos
        int lado_para_encararnos = mapa.encararA(p_enemigo.getHexagono(), posicion.getHexagono());

        //Calcular la diferencia del encaramiento del enemigo al que nos perjudicaría
        respuesta.giros_enemigo_para_encararme = Posicion.distanciaEntreCaras(p_enemigo.getLado(), lado_para_encararnos);


        //Calcular LDV y rellenar el resto
        ResultadoLDV rLDV = LDV.calcularLDV(mapa, posicion.getHexagono(), 1, p_enemigo.getHexagono(), 1);

        respuesta.enemigo_visible = rLDV.LDV;
        respuesta.visible_por_enemigo = rLDV.LDV;


        respuesta.enemigo_cubierto = rLDV.CPdirecta;
        respuesta.ofrece_cobertura = rLDV.CPinversa;

        //Calcular diferencia de nivel
        Hexagono h_actual = mapa.casilla(posicion.getHexagono());
        Hexagono h_enemigo = mapa.casilla(p_enemigo.getHexagono());

        respuesta.niveles_por_ecima_del_enemigo = h_actual.getNivel() - h_enemigo.getNivel();


        return respuesta;
    }

    private static ArrayList<PosicionAccion> obtenerPosicionesAlcanzables(DataMech mech_actual, EstadoDeJuego estado) {
        ArrayList<PosicionAccion> respuesta = new ArrayList<PosicionAccion>();

        //Seleccionar un subconjunto de casillas alrededor del mech (según su capacidad de movimiento) o evaluarlas todas
        //Phexagono posicion_mech = new Phexagono(mech_actual.getColumna(), mech_actual.getFila());
        Posicion posicion_mech = new Posicion(mech_actual.getColumna(), mech_actual.getFila(), mech_actual.getEncaramientoMech());
        int pAndar;
        int pCorrer;
        int pSaltar;
        if (mech_actual.getInformacion_adicional() == null) {
            int nj = mech_actual.getnJugador();
            pAndar = estado.datos_def_mechs[nj].getPuntosMovAndando();
            pCorrer = estado.datos_def_mechs[nj].getPuntosMovCorriendo();
            pSaltar = estado.datos_def_mechs[nj].getPuntosMovSaltando();
        } else {
            pAndar = mech_actual.getInformacion_adicional().getPuntos_andar();
            pCorrer = mech_actual.getInformacion_adicional().getPuntos_correr();
            pSaltar = mech_actual.getInformacion_adicional().getPuntos_saltar();
        }

        //Obtener casillas a las que podemos llegar andando
        NodosEnRango alcanzables_andando = AestrellaAndar.AstrellaAndar.obtenerEnRango(estado, posicion_mech, pAndar);

        //Generamos las relaciones Posición-Acción
        ArrayList<I_Nodo> nodos = alcanzables_andando.lista;

        int nnodosAndar = nodos.size();

        for (int i = 0; i < nnodosAndar; i++) {
            NodoAndar nodo = (NodoAndar) nodos.get(i);
            Posicion p = nodo.getPosicion();
            //Añadirla a la lista
            respuesta.add(new PosicionAccion(p, tiposDeMovimiento.Andar));
        }


        //TODO rellenar aquellas alcanzables corriendo


        //TODO rellenar aquellas alcanzables saltando


        return respuesta;
    }
}
