/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;
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
        switch (fase) {
            case Movimiento:
                resultado = responderAMovimiento(estado);
                break;
            case Reaccion:
                resultado = responderAReaccion(estado);
                break;
            case AtaqueArmas:
                resultado = responderAAtaqueArmas(estado);
                break;
            case AtaqueFisico:
                resultado = responderAAtaqueFisico(estado);
                break;
            case FinalTurno:
                resultado = responderAFinalTurno(estado);
                break;
        }



        //Devolver el mejor siguiente movimiento


        return resultado;
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
    private static Accion responderAMovimiento(EstadoDeJuego estado) {
        Accion resultado = null;


        //Si somos los últimos o primeros en mover debe influir en nuestra decisión



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
                double bondad = evaluar(p, estado, enemigos_operativos);
                evaluaciones.add(new Evaluacion(p, bondad));
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
            ArrayList<Phexagono> cercanas = estado.mapa.cercanas(p_enemigo, alcance_enemigo);

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
            for (Phexagono pe : cercanas) {
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
            if(alalcance){
                min_eval += -p_alcance;
                //System.out.println("La casilla " + p + " está al alcance del enemigo.");
            }

            //Nos ponemos en el peor de los casos, nos quedamos con la casilla con peor evaluación para nosotros
            resultado = min_eval;
            System.out.println("Mínimo valor de " + p + " es " + resultado);
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

    private static Accion responderAReaccion(EstadoDeJuego estado) {
        
        //TODO Encarar hacia donde está el enemigo más cercano
        
        //De momento nos quedamos igual
        return new ReaccionarIgual();
        
        
    }

    private static Accion responderAAtaqueArmas(EstadoDeJuego estado) {
        return new NoDisparar();
    }

    private static Accion responderAAtaqueFisico(EstadoDeJuego estado) {
        return new NoAtacar();
    }

    private static Accion responderAFinalTurno(EstadoDeJuego estado) {
        return new NoHacerNada();
    }
}
