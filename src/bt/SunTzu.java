/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import Aestrella.I_Nodo;
import Aestrella.NodosEnRango;
import AestrellaMov.CosteMov;
import AestrellaMov.NodoMov;
import bt.Reglas.Fase;
import bt.Reglas.TerrenoTipo;
import bt.Reglas.tiposDeMovimiento;
import static bt.Reglas.tiposDeMovimiento.Andar;
import static bt.Reglas.tiposDeMovimiento.Correr;
import static bt.Reglas.tiposDeMovimiento.Inmovil;
import static bt.Reglas.tiposDeMovimiento.Saltar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

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

        estado.calcularHistorias();


        //Responder en función de la fase
        Accion resultado = null;

        System.out.println("Leido estado. Jugador " + jugador + " fase " + fase.name());

        //Determinamos las condiciones que se deben tener en cuenta
        Condiciones condiciones = new Condiciones();

        //Determinamos la forma de comparar las posiciones según si tenemos o no Armas de Fuego


        //Cargamos heurísticas

        //Comprobar si tenemos o no potencia de fuego

        ComparaPosicionesConArmas conArmas = new ComparaPosicionesConArmas();
        ComparaPosicionesSinArmas sinArmas = new ComparaPosicionesSinArmas();

        //Imprimimos el resumen del turno
        for (Map.Entry<Integer, Mech> entrada : estado.mechs.entrySet()) {
            System.out.println(entrada.getValue().toString());
        }




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



        if (estado.getMechActual().isEnelsuelo()) {
            Mech mech = estado.mechs.get(estado.jugador);

            System.out.println("Puntos andar: " + mech.datadefmech.getPuntosMovAndando());
            System.out.println("Puntos correr: " + mech.datadefmech.getPuntosMovCorriendo());

            if (mech.datadefmech.getPuntosMovAndando() >= 2) {
                respuesta = new Levantarse(estado, condiciones);
            } else {
                respuesta = new NoMoverse();
            }
        } else {
            respuesta = movimientoCortoLargo(estado, condiciones);
        }

        //Si no tenemos armas de fuego
        /*
         if (!condiciones.armas) {
         respuesta = movimientoSinArmas(estado, condiciones);
         } else {
         respuesta = movimientoConArmas(estado, condiciones);
         }
         */



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
        
        //Escribimos también la iniciativa para consultar el orden del jugador
        int[] orden_jugador = new int[njugadores];
        for(int i=0; i<ini.orden.length; i++){
            int nj = ini.orden[i];
            orden_jugador[nj] = i; 
        }

        estado.orden_jugadores = orden_jugador;

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

        Accion respuesta = null;


        if (estado.getMechActual().isEnelsuelo()) {

            //Si estamos en el suelo nada
            respuesta = new ReaccionarIgual();

        } else {

            //Obtener el mech actual
            DataMech mech_actual = estado.getMechActual();

            //Obtener la posición actual
            Posicion posicion_mech = new Posicion(mech_actual.getColumna(), mech_actual.getFila(), mech_actual.getEncaramientoMech());

            DataMech enemigo_cercano = SunTzu.getEnemigoMasCercanoConLDV(estado).primero;


            //Aquí tenemos el enemigo más cercano que nos puede disparar o null
            if (enemigo_cercano != null) {
                Posicion p_enemigo = new Posicion(enemigo_cercano.getColumna(), enemigo_cercano.getFila(), enemigo_cercano.getEncaramientoMech());
                //Si tenemos enemigo más cercano calculamos cuál de los encaramientos posibles nos acerca más a encararlo

                //Calculamos cuál sería el encaramiento completo
                int encaramiento_completo = estado.mapa.encararA(posicion_mech.getHexagono(), p_enemigo.getHexagono());


                //Obtenemos los encaramientos posibles
                int[] encaramientos = new int[3];
                encaramientos[0] = posicion_mech.getLado();
                encaramientos[1] = posicion_mech.giroIzquierda().getLado();
                encaramientos[2] = posicion_mech.giroDerecha().getLado();

                //Nos quedamos con el mejor encaramiento
                int mejor_encaramiento = 0;
                int menor_diferencia_hasta_encaramiento_completo = 6;

                for (int i = 0; i < encaramientos.length; i++) {
                    //Para cada encaramiento medimos la diferencia hasta el encaramiento completo

                    int dif = Posicion.distanciaEntreCaras(encaramientos[i], encaramiento_completo);

                    //Si la diferencia es menor que la menor entonces lo tomamos como el encaramiento correcto
                    if (dif < menor_diferencia_hasta_encaramiento_completo) {
                        mejor_encaramiento = i;
                        menor_diferencia_hasta_encaramiento_completo = dif;
                    }

                }

                //Según qué encaramiento sea el mejor lo aplicamos
                if (mejor_encaramiento == 1) {
                    respuesta = new ReaccionarIzquierda();
                } else if (mejor_encaramiento == 2) {
                    respuesta = new ReaccionarDerecha();
                } else {
                    respuesta = new ReaccionarIgual();
                }


            } else {
                respuesta = new ReaccionarIgual();
            }

        }

        //De momento nos quedamos igual
        return respuesta;


    }

    private static Accion responderAAtaqueArmas(EstadoDeJuego estado, Condiciones condiciones) {
        Accion respuesta = null;

        //Detectar al enemigo más cercano con LDV
        DataMech actual = estado.getMechActual();
        DataMech enemigo;

        Par<DataMech, ResultadoLDV> r = SunTzu.getEnemigoMasCercanoConLDV(estado);
        enemigo = r.primero;
        ResultadoLDV rLDV = r.segundo;

        //Si tenemos enemigo seguimos
        //Si tenemos un enemigo cercano con LDV entonces le disparamos con todo
        if (enemigo != null) {
            respuesta = new DispararConTodoAMech(enemigo, rLDV, estado);
        } else {
            respuesta = new NoDisparar();
        }

        return respuesta;
    }

    public static Reglas.tiposAnguloDisparo obtenerAnguloDeDisparo(EstadoDeJuego estado, Posicion origen, Posicion destino) {
        Reglas.tiposAnguloDisparo respuesta = Reglas.tiposAnguloDisparo.Desconocido;
        //Obtenemos la cara que encara directamente al enemigo
        int cara_enemigo = estado.mapa.encararA(origen.getHexagono(), destino.getHexagono());
        int cara_origen = origen.getLado();

        //Calculamos la diferencia de nuestro encaramiento al suyo
        int dif = Posicion.distanciaEntreCaras(origen.getLado(), cara_enemigo);

        //Según la diferencia estaremos en un ángulo u otro

        //Si la diferencia es menor que 2 el enemigo está en el ángulo  frontal
        if (dif < 2) {
            respuesta = Reglas.tiposAnguloDisparo.Frontal;
        } else if (dif == 3) {
            //Si está en el lado opuesto 
            respuesta = Reglas.tiposAnguloDisparo.Posterior;
        } else {
            //Sino la diferencia es 2 pero tenemos que averiguar por qué lado

            //Debemos averiguar si la diferencia es hacia abajo o hacia arriba

            //Obtenemos la cara que está dos posiciones a la derecha
            int cara_derecha = origen.giroDerecha().giroDerecha().getLado();

            //Si es igual que la del enemigo está a la derecha, sino a la izquierda, no queda otra
            if (cara_derecha == cara_enemigo) {
                respuesta = Reglas.tiposAnguloDisparo.Derecho;
            } else {
                respuesta = Reglas.tiposAnguloDisparo.Izquierda;
            }
        }


        return respuesta;
    }

    public static Reglas.Localizacion traducirLocalizacion(int localizacion) {
        //(0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)
        switch (localizacion) {
            case 0:
                return Reglas.Localizacion.BI;
            case 1:
                return Reglas.Localizacion.TI;
            case 2:
                return Reglas.Localizacion.PI;
            case 3:
                return Reglas.Localizacion.PD;
            case 4:
                return Reglas.Localizacion.TD;
            case 5:
                return Reglas.Localizacion.BD;
            case 6:
                return Reglas.Localizacion.TC;
            case 7:
                return Reglas.Localizacion.CAB;

            default:
                return Reglas.Localizacion.BIBD;
        }
    }

    private static Accion responderAAtaqueFisico(EstadoDeJuego estado, Condiciones condiciones) {
        Accion respuesta = new NoAtacar();

        //Si no tengo potencia de fuego pruebo a dar puñetazos
        if (!estado.mechs.get(estado.jugador).potencia_de_fuego.existe) {

            respuesta = new PunietazosYPatada(estado, condiciones);
        }
        return respuesta;
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

        //Calcular el calor generado para llegar aquí?

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

        if (true) {//hamovido) {
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

    public static ArrayList<PosicionAccion> obtenerPosicionesAlcanzables(DataMech mech_actual, EstadoDeJuego estado) {
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
        NodosEnRango alcanzables_andando = AestrellaMov.AstrellaMov.obtenerEnRangoAndando(estado, posicion_mech, pAndar);

        //Generamos las relaciones Posición-Acción
        ArrayList<I_Nodo> nodos_andando = alcanzables_andando.lista;

        int nnodosAndar = nodos_andando.size();

        for (int i = 0; i < nnodosAndar; i++) {
            NodoMov nodo = (NodoMov) nodos_andando.get(i);
            CosteMov coste = (CosteMov) nodo.getCosteTotal();
            coste.calor_generado = 1;
            Posicion p = nodo.getPosicion();
            //Añadirla a la lista
            respuesta.add(new PosicionAccion(p, tiposDeMovimiento.Andar));
        }


        //TODO rellenar aquellas alcanzables corriendo
        //Obtener casillas a las que podemos llegar andando
        NodosEnRango alcanzables_corriendo = AestrellaMov.AstrellaMov.obtenerEnRangoCorriendo(estado, posicion_mech, pCorrer);

        //Generamos las relaciones Posición-Acción
        ArrayList<I_Nodo> nodos_corriendo = alcanzables_corriendo.lista;

        int nnodosCorrer = nodos_corriendo.size();

        for (int i = 0; i < nnodosCorrer; i++) {
            NodoMov nodo = (NodoMov) nodos_corriendo.get(i);
            CosteMov coste = (CosteMov) nodo.getCosteTotal();
            coste.calor_generado = 2;
            Posicion p = nodo.getPosicion();
            //Añadirla a la lista
            respuesta.add(new PosicionAccion(p, tiposDeMovimiento.Correr));
        }

        //TODO rellenar aquellas alcanzables saltando

        //Si la posición actual es de agua de profundidad mayor que cero pasamos de saltar
        Hexagono hactual = estado.mapa.casilla(posicion_mech.getHexagono());
        if (hactual.getTipoterreno() == TerrenoTipo.Agua && hactual.getProfundidad() > 0) {
        } else {

            //Obtener casillas a las que podemos llegar andando
            NodosEnRango alcanzables_saltando = obtenerEnRangoSaltando(estado, posicion_mech, pSaltar);

            //Generamos las relaciones Posición-Acción
            ArrayList<I_Nodo> nodos_saltando = alcanzables_saltando.lista;

            int nnodosSaltar = nodos_saltando.size();

            for (int i = 0; i < nnodosSaltar; i++) {
                NodoMov nodo = (NodoMov) nodos_saltando.get(i);
                CosteMov coste = (CosteMov) nodo.getCosteTotal();
                Posicion p = nodo.getPosicion();
                //TODO no sirve de nada calcular aquí el calor, se pierde la información, calcularlo después para el PosEval
                //Al saltar se genera tanto calor como distancia recorrida con un mínimo de 3
                int distancia = estado.mapa.distanciaCasillas(posicion_mech.getHexagono(), p.getHexagono());
                if (distancia <= 3) {
                    coste.calor_generado = 3;
                } else {
                    coste.calor_generado = distancia;
                }
                //Añadirla a la lista
                respuesta.add(new PosicionAccion(p, tiposDeMovimiento.Saltar));
            }
        }

        return respuesta;
    }

    private static Movimiento movimientoSinArmas(EstadoDeJuego estado, Condiciones condiciones) {
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



        DataMech mech_enemigo = enemigos_operativos.get(0);

        Posicion p_enemigo = new Posicion(mech_enemigo.getColumna(), mech_enemigo.getFila(), mech_enemigo.getEncaramientoMech());

        int epandar = estado.datos_def_mechs[mech_enemigo.getnJugador()].getPuntosMovAndando();
        int epcorrer = estado.datos_def_mechs[mech_enemigo.getnJugador()].getPuntosMovCorriendo();
        int epsaltar = estado.datos_def_mechs[mech_enemigo.getnJugador()].getPuntosMovSaltando();

        int max_radio = Math.max(epandar, Math.max(epcorrer, epsaltar));

        //Buscar la mejor casilla del mapa entre las cercanas al enemigo
        //Seleccionar las casillas cercanas al enemigo
        ArrayList<Phexagono> cercanas = estado.mapa.cercanas(p_enemigo.getHexagono(), max_radio);


        ArrayList<PosicionAccion> posiciones_alcanzables;

        //Obtenemos las posiciones adyacentes al enemigo
        posiciones_alcanzables = obtenerPosicionesAlcanzables(mech_enemigo, estado);

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
//Añadir el calor generado para alcanzar la posición
                switch (pa.tipoMovimiento) {
                    case Inmovil:
                        datos_de_posicion.calor_generado = 0;
                        break;
                    case Andar:
                        datos_de_posicion.calor_generado = 1;
                        break;
                    case Correr:
                        datos_de_posicion.calor_generado = 2;
                        break;
                    case Saltar:
                        //El calor generado al saltar depende de la distancia recorrida
                        int distancia = estado.mapa.distanciaCasillas(p_actual.getHexagono(), pa.posicion.getHexagono());
                        if (distancia <= 3) {
                            datos_de_posicion.calor_generado = 3;
                        } else {
                            datos_de_posicion.calor_generado = distancia;
                        }
                        break;

                }

                if (mejor == null) {
                    mejor = datos_de_posicion;
                } else {
                    //Comprobamos si es mejor que la mejor actual
                    if (condiciones.comparador_de_posiciones.compare(datos_de_posicion, mejor) < 0) {

                        //Si estamos en modo sin armas tenemos que comprobar que efectivamente existe una ruta hasta la casilla 
                        //ya que el conjunto inicial no se consigue de los sitios a los que podemos acceder sino a los que puede acceder el enemigo.
                        Aestrella.Resultado rutaAcasilla = AestrellaMov.AstrellaMov.calcularRutaAndando(p_actual, pa.posicion, estado);

                        if (!condiciones.armas) {



                            //Sólo si existe la ruta la tenemos en cuenta
                            if (rutaAcasilla != null) {

                                //Que exista significa que el coste no es imposible
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

        System.out.println("Objetivo: " + mejorPA.posicion);



        switch (mejorPA.tipoMovimiento) {
            case Andar:
                //Calculamos la ruta hasta el objetivo 
                //TODO evitar este cálculo utilizando los conjuntos de antes para saber hasta dónde podemos llegar
                Aestrella.Resultado r = AestrellaMov.AstrellaMov.calcularRutaAndando(p_actual, mejorPA.posicion, estado);


                //Imprimimos la ruta por pantalla
                for (int i = 0; i < r.getRuta().size(); i++) {
                    NodoMov nodo = (NodoMov) r.getRuta().get(i);

                    System.out.println(nodo.getPosicion());

                }

                ArrayList<I_Nodo> nodos_ruta = r.getRuta();

                int lnr = nodos_ruta.size();

                boolean posible = true;
                Posicion nuevodestino = null;

                //Componer la ruta ( comenzamos en el 1 porque la primera casilla es el orígen y no forma parte de la ruta )
                for (int i = 1; i < lnr && posible; i++) {
                    NodoMov nodo = (NodoMov) nodos_ruta.get(i);
                    if (((CosteMov) nodo.getCosteReal()).getPuntos_de_movimiento() > pandar) {
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
                //Calculamos la ruta hasta el objetivo 
                //TODO evitar este cálculo utilizando los conjuntos de antes para saber hasta dónde podemos llegar
                Aestrella.Resultado rutaCorriendo = AestrellaMov.AstrellaMov.calcularRutaCorriendo(p_actual, mejorPA.posicion, estado);


                //Imprimimos la ruta por pantalla
                for (int i = 0; i < rutaCorriendo.getRuta().size(); i++) {
                    NodoMov nodo = (NodoMov) rutaCorriendo.getRuta().get(i);

                    System.out.println(nodo.getPosicion());

                }

                ArrayList<I_Nodo> nodos_ruta_corriendo = rutaCorriendo.getRuta();

                int lnrc = nodos_ruta_corriendo.size();

                boolean posible_corriendo = true;
                Posicion nuevodestino_corriendo = null;

                //Componer la ruta ( comenzamos en el 1 porque la primera casilla es el orígen y no forma parte de la ruta )
                for (int i = 1; i < lnrc && posible_corriendo; i++) {
                    NodoMov nodo = (NodoMov) nodos_ruta_corriendo.get(i);
                    if (((CosteMov) nodo.getCosteReal()).getPuntos_de_movimiento() > pandar) {
                        posible = false;
                    } else {
                        respuesta.getRuta().getPasos().add(nodo.getPaso());
                        nuevodestino = nodo.getPosicion();
                    }
                }

                //Si posible es falso significa que hemos acortado la ruta y tenemos que modificar el destino
                if (!posible_corriendo) {
                    respuesta.setDestino(nuevodestino_corriendo.getHexagono());
                    respuesta.setLado_destino(nuevodestino_corriendo.getLado());
                }
                break;

            case Saltar:
                break;
            case Inmovil:
                respuesta = new NoMoverse();
                break;
        }


        return respuesta;



    }

    private static Movimiento movimientoConArmas(EstadoDeJuego estado, Condiciones condiciones) {
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

        posiciones_alcanzables = obtenerPosicionesAlcanzables(mech_actual, estado);

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
                //Añadir el calor generado para alcanzar la posición
                switch (pa.tipoMovimiento) {
                    case Inmovil:
                        datos_de_posicion.calor_generado = 0;
                        break;
                    case Andar:
                        datos_de_posicion.calor_generado = 1;
                        break;
                    case Correr:
                        datos_de_posicion.calor_generado = 2;
                        break;
                    case Saltar:
                        //El calor generado al saltar depende de la distancia recorrida
                        int distancia = estado.mapa.distanciaCasillas(p_actual.getHexagono(), pa.posicion.getHexagono());
                        if (distancia <= 3) {
                            datos_de_posicion.calor_generado = 3;
                        } else {
                            datos_de_posicion.calor_generado = distancia;
                        }
                        break;

                }

                if (mejor == null) {
                    mejor = datos_de_posicion;
                } else {
                    //Comprobamos si es mejor que la mejor actual
                    if (condiciones.comparador_de_posiciones.compare(datos_de_posicion, mejor) < 0) {

                        //Si es mejor la sustituimos
                        mejor = datos_de_posicion;
                        mejorPA = pa;

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

        System.out.println("Objetivo: " + mejorPA.posicion);



        switch (mejorPA.tipoMovimiento) {
            case Andar:
                //Inicializamos la ruta
                respuesta.setRuta(new Ruta());

                //Calculamos la ruta hasta el objetivo 
                //TODO evitar este cálculo utilizando los conjuntos de antes para saber hasta dónde podemos llegar
                Aestrella.Resultado r = AestrellaMov.AstrellaMov.calcularRutaAndando(p_actual, mejorPA.posicion, estado);


                //Imprimimos la ruta por pantalla
                for (int i = 0; i < r.getRuta().size(); i++) {
                    NodoMov nodo = (NodoMov) r.getRuta().get(i);

                    System.out.println(nodo.getPosicion());

                }

                ArrayList<I_Nodo> nodos_ruta = r.getRuta();

                int lnr = nodos_ruta.size();

                //Componer la ruta ( comenzamos en el 1 porque la primera casilla es el orígen y no forma parte de la ruta )
                for (int i = 1; i < lnr; i++) {
                    NodoMov nodo = (NodoMov) nodos_ruta.get(i);
                    respuesta.getRuta().getPasos().add(nodo.getPaso());

                }
                break;

            case Correr:
                //Inicializamos la ruta
                respuesta.setRuta(new Ruta());

                //Calculamos la ruta hasta el objetivo 
                //TODO evitar este cálculo utilizando los conjuntos de antes para saber hasta dónde podemos llegar
                Aestrella.Resultado rutaCorriendo = AestrellaMov.AstrellaMov.calcularRutaCorriendo(p_actual, mejorPA.posicion, estado);


                //Imprimimos la ruta por pantalla
                for (int i = 0; i < rutaCorriendo.getRuta().size(); i++) {
                    NodoMov nodo = (NodoMov) rutaCorriendo.getRuta().get(i);

                    System.out.println(nodo.getPosicion());

                }

                ArrayList<I_Nodo> nodos_ruta_corriendo = rutaCorriendo.getRuta();

                int lnrc = nodos_ruta_corriendo.size();

                //Componer la ruta ( comenzamos en el 1 porque la primera casilla es el orígen y no forma parte de la ruta )
                for (int i = 1; i < lnrc; i++) {
                    NodoMov nodo = (NodoMov) nodos_ruta_corriendo.get(i);
                    respuesta.getRuta().getPasos().add(nodo.getPaso());

                }
                break;

            case Saltar:
                //Saltamos a la posición determinada
                //Generamos el salto
                Salto salto = new Salto();
                salto.setTipo(Reglas.tiposDeMovimiento.Saltar);
                salto.setDestino(mejorPA.posicion.getHexagono());
                salto.setUsaMASC(false);
                salto.setRuta(null);
                salto.setLado_destino(mejorPA.posicion.getLado());

                respuesta = salto;

                break;
            case Inmovil:
                respuesta = new NoMoverse();
                break;
        }


        return respuesta;
    }

    private static NodosEnRango obtenerEnRangoSaltando(EstadoDeJuego estado, Posicion posicion_mech, int pSaltar) {
        NodosEnRango respuesta = new NodosEnRango();

        respuesta.origen = new NodoMov(posicion_mech);

        respuesta.lista = new ArrayList<I_Nodo>();
        //Obtener las casillas susceptibles de ser alcanzadas saltando
        ArrayList<Phexagono> posiciones_cercanas = estado.mapa.cercanas(posicion_mech.getHexagono(), pSaltar);

        //Para cada una de ellas determinar si se puede o no llegar saltando
        for (Phexagono p : posiciones_cercanas) {

            Hexagono hdestino = estado.mapa.casilla(p);

            //Si el destino está ardiendo pasamos
            if (!hdestino.isFuego()) {

                //Si se puede alcanzar la añadimos a la lista el hexágono con todos sus encaramientos
                boolean rutaSaltando = PathFinder.buscaRutaSaltando(estado.mapa, posicion_mech.getHexagono(), p, pSaltar);

                if (rutaSaltando) {
                    Posicion pos1 = new Posicion(p, 1);
                    Posicion pos2 = new Posicion(p, 2);
                    Posicion pos3 = new Posicion(p, 3);
                    Posicion pos4 = new Posicion(p, 4);
                    Posicion pos5 = new Posicion(p, 5);
                    Posicion pos6 = new Posicion(p, 6);

                    NodoMov n1 = new NodoMov(pos1);
                    NodoMov n2 = new NodoMov(pos2);
                    NodoMov n3 = new NodoMov(pos3);
                    NodoMov n4 = new NodoMov(pos4);
                    NodoMov n5 = new NodoMov(pos5);
                    NodoMov n6 = new NodoMov(pos6);

                    respuesta.lista.add(n1);
                    respuesta.lista.add(n2);
                    respuesta.lista.add(n3);
                    respuesta.lista.add(n4);
                    respuesta.lista.add(n5);
                    respuesta.lista.add(n6);
                }
            }
        }


        return respuesta;
    }

    static Par<DataMech, ResultadoLDV> getEnemigoMasCercanoConLDV(EstadoDeJuego estado) {

        Par<DataMech, ResultadoLDV> respuesta = new Par<DataMech, ResultadoLDV>();

        //Obtener el mech actual
        DataMech mech_actual = estado.getMechActual();

        //Obtener la posición actual
        Posicion posicion_mech = new Posicion(mech_actual.getColumna(), mech_actual.getFila(), mech_actual.getEncaramientoMech());

        //Obtener a todos los enemigos y sus posiciones

        DataMech[] mechs = estado.datos_mechs.getMechs();

        Posicion p_enemigo = null;
        int menor_distancia = 0;

        for (DataMech mech : mechs) {
            if (mech.getnJugador() == estado.jugador) {
                mech_actual = mech;
            } else {
                if (mech.isOperativo()) {
                    //Obtener su posición
                    Posicion p = new Posicion(mech.getColumna(), mech.getFila(), mech.getEncaramientoMech());

                    //Comprobar si tenemos LDv con él
                    ResultadoLDV rLDV = LDV.calcularLDV(estado.mapa, posicion_mech.getHexagono(), 1, p.getHexagono(), 1);

                    //Si tenemos LDV entonces
                    if (rLDV.LDV) {
                        //Calculamos la distancia hasta el mech
                        int distancia = estado.mapa.distanciaCasillas(posicion_mech.getHexagono(), p.getHexagono());

                        //Si no tenemos posición cercana la definimos
                        if (p_enemigo == null) {
                            p_enemigo = p;
                            respuesta.primero = mech;
                            //Guardamos el resultado de la LDV para devolverlo
                            respuesta.segundo = rLDV;
                            menor_distancia = distancia;
                        } else {
                            //Sino sólo lo sustiuimos si está más cerca que los demás
                            if (distancia < menor_distancia) {
                                p_enemigo = p;
                                respuesta.primero = mech;
                                //Guardamos el resultado de la LDV para devolverlo
                                respuesta.segundo = rLDV;
                                menor_distancia = distancia;
                            }
                        }
                    }
                }
            }
        }

        return respuesta;
    }

    static boolean[] getLocalizacionesValidasParaDisparar(EstadoDeJuego estado, Reglas.tiposAnguloDisparo anguloDisparoParteSuperior, Reglas.tiposAnguloDisparo anguloDisparoPiernas, boolean disparan_piernas) {
        boolean[] respuesta = new boolean[8];

        //Los ponemos todos a false
        Arrays.fill(respuesta, false);


        //(0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB,8=TIa,9=TDa,10=TCa)

        switch (anguloDisparoParteSuperior) {
            case Frontal:
                //Son válidas las zonas del torso y la cabeza
                respuesta[6] = true;
                respuesta[1] = true;
                respuesta[4] = true;
                respuesta[7] = true;

                //También los brazos y las piernas
                respuesta[0] = true;
                respuesta[2] = true;
                respuesta[3] = true;
                respuesta[5] = true;
                break;

            case Izquierda:
                //Sólo pueden disparar las armas del brazo izquierdo
                respuesta[0] = true;
                break;
            case Derecho:
                //Sólo puede disparar las armas del brazo derecho
                respuesta[5] = true;
                break;

            case Posterior:
                //Son válidas las armas montadas en la parte trasera del torso
                respuesta[1] = true;
                respuesta[4] = true;
                respuesta[6] = true;

                //Las piernas y la cabeza también son válidas hacia atrás
                respuesta[2] = true;
                respuesta[3] = true;
                respuesta[7] = true;
        }

        //Si las piernas no pueden disparar las desactivamos
        if (!disparan_piernas) {
            respuesta[2] = false;
            respuesta[3] = false;
        }

        return respuesta;
    }

    private static Movimiento movimientoCortoLargo(EstadoDeJuego estado, Condiciones condiciones) {
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

        DataMech enemigo_cercano = SunTzu.getEnemigoMasCercanoConLDV(estado).primero;

        if (enemigo_cercano == null) {
            enemigo_cercano = SunTzu.getEnemigoMasCercano(estado);
        }
        Posicion p_enemigo = new Posicion(enemigo_cercano.getColumna(), enemigo_cercano.getFila(), enemigo_cercano.getEncaramientoMech());

        ArrayList<PosicionAccion> posiciones_alcanzables;

        posiciones_alcanzables = obtenerPosicionesAlcanzables(mech_actual, estado);

        //Todas las calculadas aquí son de corto recorrido
        for (PosicionAccion pos : posiciones_alcanzables) {
            pos.setRecorrido(Reglas.TipoRecorrido.Corto);
        }

        //Añadimos ahora las de largo recorrido

        //Obtenemos las casillas cercanas al enemigo con un radio de 6, por ejemplo
        ArrayList<Phexagono> cercanas_al_enemigo = estado.mapa.cercanas(p_enemigo.getHexagono(), 6);

        //Para cada una de ellas añadimos una posicionAcción por cada posición que tiene y por cada forma de llegar hasta ella
        for (Phexagono hex : cercanas_al_enemigo) {

            //Para cada cara
            for (int cara = 1; cara <= 6; cara++) {
                Posicion p = new Posicion(hex, cara);
                PosicionAccion pa = new PosicionAccion(p, Andar);
                pa.setRecorrido(Reglas.TipoRecorrido.Largo);
                PosicionAccion pc = new PosicionAccion(p, Correr);
                pc.setRecorrido(Reglas.TipoRecorrido.Largo);

                //Y lo añadimos a la lista
                posiciones_alcanzables.add(pa);
                posiciones_alcanzables.add(pc);
            }
        }

        Posicion p_actual = new Posicion(mech_actual.getColumna(), mech_actual.getFila(), mech_actual.getEncaramientoMech());

        /*
         System.out.println("Posiciones a evaluar:");
        
         //Tenemos todas las posiciones que vamos a evaluar
         for(PosicionAccion posac : posiciones_alcanzables){
         System.out.println(posac.posicion.toString() + " " + posac.tipoMovimiento.name());
         }*/


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
                //Añadir el calor generado para alcanzar la posición
                switch (pa.tipoMovimiento) {
                    case Inmovil:
                        datos_de_posicion.calor_generado = 0;
                        break;
                    case Andar:
                        datos_de_posicion.calor_generado = 1;
                        break;
                    case Correr:
                        datos_de_posicion.calor_generado = 2;
                        break;
                    case Saltar:
                        //El calor generado al saltar depende de la distancia recorrida
                        int distancia = estado.mapa.distanciaCasillas(p_actual.getHexagono(), pa.posicion.getHexagono());
                        if (distancia <= 3) {
                            datos_de_posicion.calor_generado = 3;
                        } else {
                            datos_de_posicion.calor_generado = distancia;
                        }
                        break;

                }

                if (mejor == null) {
                    boolean existeRuta = true;
                    //Si la posición es de largo recorrido tenemos que comprobar que sea alcanzable
                    //TODO ¿y si es de corto recorrido no?
                    if (pa.getRecorrido() == Reglas.TipoRecorrido.Largo) {
                        Aestrella.Resultado result;
                        switch (pa.tipoMovimiento) {
                            case Andar:
                                result = AestrellaMov.AstrellaMov.calcularRutaAndando(p_actual, pa.posicion, estado);
                                if (result == null || ((CosteMov) result.getCoste()).imposible) {
                                    existeRuta = false;
                                }
                                break;
                            case Correr:
                                result = AestrellaMov.AstrellaMov.calcularRutaCorriendo(p_actual, pa.posicion, estado);
                                if (result == null || ((CosteMov) result.getCoste()).imposible) {
                                    existeRuta = false;
                                }
                                break;
                        }
                    }
                    if (existeRuta) {
                        mejor = datos_de_posicion;
                    }
                } else {
                    //Comprobamos si es mejor que la mejor actual
                    if (condiciones.comparador_de_posiciones.compare(datos_de_posicion, mejor) < 0) {
                        boolean existeRuta = true;
                        //Si la posición es de largo recorrido tenemos que comprobar que sea alcanzable
                        //y sino también??
                        if (pa.getRecorrido() == Reglas.TipoRecorrido.Largo) {
                            Aestrella.Resultado result;
                            switch (pa.tipoMovimiento) {
                                case Andar:
                                    result = AestrellaMov.AstrellaMov.calcularRutaAndando(p_actual, pa.posicion, estado);
                                    if (result == null || ((CosteMov) result.getCoste()).imposible) {
                                        existeRuta = false;
                                    }
                                    break;
                                case Correr:
                                    result = AestrellaMov.AstrellaMov.calcularRutaCorriendo(p_actual, pa.posicion, estado);
                                    if (result == null || ((CosteMov) result.getCoste()).imposible) {
                                        existeRuta = false;
                                    }
                                    break;
                            }
                        }
                        //Si existe la ruta entonces la sustituimos
                        if (existeRuta) {
                            //Si es mejor la sustituimos
                            mejor = datos_de_posicion;
                            mejorPA = pa;

                            //Nueva mejor casilla destino
                            System.out.println("Nueva mejor posición -------- " + mejorPA.posicion.toString() + " " + mejorPA.tipoMovimiento.name());
                            System.out.println(mejor);

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

        System.out.println("Objetivo: " + mejorPA.posicion);

        //Calculamos los puntos máximos de movimiento y en caso necesario cortamos las rutas
        int pandar = mech_actual.getInformacion_adicional().getPuntos_andar();
        int pcorrer = mech_actual.getInformacion_adicional().getPuntos_correr();
        int psaltar = mech_actual.getInformacion_adicional().getPuntos_saltar();

        boolean posible = true;

        switch (mejorPA.tipoMovimiento) {
            case Andar:
                //Inicializamos la ruta
                respuesta.setRuta(new Ruta());

                //Calculamos la ruta hasta el objetivo 
                //TODO evitar este cálculo utilizando los conjuntos de antes para saber hasta dónde podemos llegar
                Aestrella.Resultado r = AestrellaMov.AstrellaMov.calcularRutaAndando(p_actual, mejorPA.posicion, estado);


                //Imprimimos la ruta por pantalla
                for (int i = 1; i < r.getRuta().size(); i++) {
                    NodoMov nodo = (NodoMov) r.getRuta().get(i);

                    System.out.println(nodo.getPosicion());

                }

                ArrayList<I_Nodo> nodos_ruta = r.getRuta();

                int lnr = nodos_ruta.size();

                //Componer la ruta ( comenzamos en el 1 porque la primera casilla es el orígen y no forma parte de la ruta )
                for (int i = 1; i < lnr && posible; i++) {
                    NodoMov nodo = (NodoMov) nodos_ruta.get(i);
                    if (i > 1 && mejorPA.getRecorrido() == Reglas.TipoRecorrido.Largo && ((CosteMov) nodo.getCosteReal()).getPuntos_de_movimiento() > pandar) {
                        posible = false;
                    } else {
                        respuesta.getRuta().getPasos().add(nodo.getPaso());
                        respuesta.setDestino(nodo.getPosicion().getHexagono());
                        respuesta.setLado_destino(nodo.getPosicion().getLado());
                    }

                }
                break;

            case Correr:
                //Inicializamos la ruta
                respuesta.setRuta(new Ruta());

                //Calculamos la ruta hasta el objetivo 
                //TODO evitar este cálculo utilizando los conjuntos de antes para saber hasta dónde podemos llegar
                Aestrella.Resultado rutaCorriendo = AestrellaMov.AstrellaMov.calcularRutaCorriendo(p_actual, mejorPA.posicion, estado);


                //Imprimimos la ruta por pantalla
                for (int i = 0; i < rutaCorriendo.getRuta().size(); i++) {
                    NodoMov nodo = (NodoMov) rutaCorriendo.getRuta().get(i);

                    System.out.println(nodo.getPosicion());

                }

                ArrayList<I_Nodo> nodos_ruta_corriendo = rutaCorriendo.getRuta();

                int lnrc = nodos_ruta_corriendo.size();

                //Componer la ruta ( comenzamos en el 1 porque la primera casilla es el orígen y no forma parte de la ruta )
                for (int i = 1; i < lnrc && posible; i++) {
                    NodoMov nodo = (NodoMov) nodos_ruta_corriendo.get(i);
                    if (mejorPA.getRecorrido() == Reglas.TipoRecorrido.Largo && ((CosteMov) nodo.getCosteReal()).getPuntos_de_movimiento() > pcorrer) {
                        posible = false;
                    } else {
                        respuesta.getRuta().getPasos().add(nodo.getPaso());
                        respuesta.setDestino(nodo.getPosicion().getHexagono());
                        respuesta.setLado_destino(nodo.getPosicion().getLado());
                    }

                }

                break;

            case Saltar:
                //Saltamos a la posición determinada
                //Generamos el salto
                Salto salto = new Salto();
                salto.setTipo(Reglas.tiposDeMovimiento.Saltar);
                salto.setDestino(mejorPA.posicion.getHexagono());
                salto.setUsaMASC(false);
                salto.setRuta(null);
                salto.setLado_destino(mejorPA.posicion.getLado());

                respuesta = salto;

                break;
            case Inmovil:
                respuesta = new NoMoverse();
                break;
        }


        return respuesta;
    }

    public static DataMech getEnemigoMasCercano(EstadoDeJuego estado) {
        DataMech respuesta = null;

        //Obtener el mech actual
        DataMech mech_actual = estado.getMechActual();

        //Obtener la posición actual
        Posicion posicion_mech = new Posicion(mech_actual.getColumna(), mech_actual.getFila(), mech_actual.getEncaramientoMech());

        //Obtener a todos los enemigos y sus posiciones

        DataMech[] mechs = estado.datos_mechs.getMechs();

        Posicion p_enemigo = null;
        int menor_distancia = 0;

        for (DataMech mech : mechs) {
            if (mech.getnJugador() == estado.jugador) {
                mech_actual = mech;
            } else {
                if (mech.isOperativo()) {
                    //Obtener su posición
                    Posicion p = new Posicion(mech.getColumna(), mech.getFila(), mech.getEncaramientoMech());

                    //Calculamos la distancia hasta el mech
                    int distancia = estado.mapa.distanciaCasillas(posicion_mech.getHexagono(), p.getHexagono());

                    //Si no tenemos posición cercana la definimos
                    if (p_enemigo == null) {
                        p_enemigo = p;
                        respuesta = mech;
                        menor_distancia = distancia;
                    } else {
                        //Sino sólo lo sustiuimos si está más cerca que los demás
                        if (distancia < menor_distancia) {
                            p_enemigo = p;
                            respuesta = mech;
                            menor_distancia = distancia;
                        }
                    }

                }
            }
        }

        return respuesta;
    }

    static boolean[] getLocalizacionesValidasParaGolpear(EstadoDeJuego estado, Reglas.tiposAnguloDisparo anguloGolpeoSuperior, Reglas.tiposAnguloDisparo anguloGolpeoPiernas, boolean golpean_piernas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
