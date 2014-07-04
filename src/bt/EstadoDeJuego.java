/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Un estado es toda la información que define el juego en un instante concreto.
 * Quizá no sea necesaria la clase EstadoDeJuego sino simplemente Juego y crear
 * varios para representar cada estado. Lo necesitamos para poder simular con
 * una acción a qué estados podemos llegar y con qué probabilidad. Después
 * necesitaremos una función de evaluación del estado. Con heurísticas. Tenemos
 * que poder sacar los vecinos de cada estado. Y evaluar cada estado.
 *
 * @author Víctor
 */
public class EstadoDeJuego {

    //Mapa del momento
    public Mapa mapa;
    //Fase
    public Fase fase;
    //Iniciativa??? necesaria para saber los siguientes jugadores
    public int orden[];
    //Jugador actual
    public int jugador;
    //Jugadores siguientes
    public int siguientes[];
    //Estado de Jugadores
    public EstadoDeJugador jugadores[];
    //Datos de los mechs
    public DataMechs datos_mechs;
    //Datos de iniciativa
    public int[] iniciativa;
    //Definiciones de meches por jugador
    public DataDefMech[] datos_def_mechs;
    //Información completa de los mechs
    public HashMap<Integer, Mech> mechs = new HashMap<Integer, Mech>();
    //Array para consultar el orden de un jugador
    int[] orden_jugadores;

    public DataMech getMechActual() {
        DataMech respuesta = null;

        for (DataMech mech : datos_mechs.getMechs()) {
            if (mech.getnJugador() == jugador) {
                respuesta = mech;
            }
        }


        return respuesta;
    }

    void calcularHistorias() {

        //Recorremos todos los mechs
        int njugadores = this.datos_mechs.getnMechs();
        for (int nj = 0; nj < njugadores; nj++) {

            //Generamos el Mech
            Mech mech = new Mech();

            mech.id = nj;
            mech.datadefmech = this.datos_def_mechs[nj];
            mech.datamech = this.datos_mechs.getMechs()[nj];
            //Calculamos la potencia de fuego
            mech.potencia_de_fuego = calcularPotenciaDeFuego(mech.datadefmech);
            //Calculamos las posiciones alcanzables
            //la suya
            mech.posiciones_alcanzables = new ArrayList<PosicionAccion>();
            //Posición actual
            Posicion p_actual = new Posicion(mech.datamech.getColumna(), mech.datamech.getFila(), mech.datamech.getEncaramientoMech());
            PosicionAccion actual = new PosicionAccion(p_actual, Reglas.tiposDeMovimiento.Inmovil);
            mech.posiciones_alcanzables.add(actual);
            //Si aún no ha movido añadimos las futuras
            int nj_actual = this.jugador;
            int nj_mech = mech.id;
            if (this.orden_jugadores[nj_mech] >= this.orden_jugadores[nj_actual]) {
                mech.posiciones_alcanzables = SunTzu.obtenerPosicionesAlcanzables(mech.datamech, this);
            }
            //Calcular vida
            mech.vida = calcularVida(mech);

            mechs.put(nj, mech);

        }
    }

    PotenciaDeFuego calcularPotenciaDeFuego(DataDefMech defmech) {

        PotenciaDeFuego respuesta = new PotenciaDeFuego();

        if (defmech.getnArmas() == 0) {
            //Si no hay armas no hay potencia de fuego
            respuesta.resumen = 0;
            respuesta.existe = false;
            System.out.println("No tiene armas");
        } else {
            //Buscamos posibles parejas (ARMA - MUNICIÓN)
            DataComponente[] componentes = defmech.getComponentesEquipados();

            ArrayList<Arma> armas = new ArrayList<Arma>();
            ArrayList<Municion> municiones = new ArrayList<Municion>();

            boolean[] localizacion_valida = new boolean[8];

            //En principio tomaré por válidas todas las localizaciones ya que todas pueden disparar hacia adelante
            Arrays.fill(localizacion_valida, true);


            //Para cada localización válida comprobamos qué slots son armas
            for (int loc = 0; loc < localizacion_valida.length; loc++) {

                //Si es una localización válida comprobamos los slots
                if (localizacion_valida[loc]) {

                    DataLocalizacion localizacion = defmech.getLocalizacion()[loc];

                    //Para cada slot de la localización comprobamos
                    DataSlotOcupado[] slots = localizacion.getSlotsOcupados();

                    for (int nslot = 0; nslot < slots.length; nslot++) {
                        DataSlotOcupado slot = slots[nslot];

                        boolean valida = false;

                        //Si tiene índice de componente
                        if (slot.getIndiceComponente() != -1) {

                            //Cargamos el componente que se encuentra en ese slot
                            DataComponente componente = componentes[slot.getIndiceComponente()];

                            //Comprobamos que sea un arma
                            if (componente.getClase().equals("ARMA")) {
                                //Comprobamos que esté operativo
                                if (componente.isOperativo()) {
                                    //Comprobamos que esté en una localización válida
                                    if (localizacion_valida[componente.getLocalizacion()]) {
                                        valida = true;
                                    }
                                }
                                //Si es válida la añadimos como arma
                                if (valida) {
                                    System.out.println("Arma válida " + componente.getNombre() + " " + componente.getTipoDeArma());
                                    Arma nueva = new Arma();
                                    nueva.componente = componente;
                                    nueva.nslot = nslot;
                                    armas.add(nueva);
                                }
                            } else if (componente.getClase().endsWith("MUNICION")) {
                                //Si es munición la añadimos a la lista de municiones
                                Municion nuevaMunicion = new Municion();
                                nuevaMunicion.componente = componente;
                                nuevaMunicion.nslot = nslot;

                                municiones.add(nuevaMunicion);
                            }
                        }
                    }
                }
            }

            //Aquí ya tenemos una lista con todas las armas válidas y las municiones disponibles

            //Para cada arma buscamos munición que la pueda disparar
            for (int narma = 0; narma < armas.size(); narma++) {

                Arma arma = armas.get(narma);

                boolean valida = false;
                //Comprobamos el tipo de arma
                //Si es de balística buscamos su munición
                boolean encontrada = false;
                if (arma.componente.getTipoDeArma().contains("Bal") || arma.componente.getTipoDeArma().contains("Misi")) {
                    //Buscamos la primera munición que se pueda usar con este arma
                    int codArma = arma.componente.getCodigo();


                    Municion municion = null;
                    for (int m = 0; m < municiones.size() && !encontrada; m++) {
                        Municion mu = municiones.get(m);

                        if (mu.componente.getMunicion_codArma() == codArma && mu.componente.getMunicion_cantidad() > 0) {
                            municion = mu;
                            encontrada = true;
                            //Disminuimos la munición para que no la quieran coger otras armas
                            mu.componente.setMunicion_cantidad(mu.componente.getMunicion_cantidad() - 1);
                        }
                    }
                }
                if (encontrada) {
                    valida = true;
                } else if (arma.componente.getTipoDeArma().contains("Energ")) {
                    valida = true;
                } else {
                    valida = false;
                }


                //Si es válida
                if (valida) {
                    respuesta.existe = true;
                    respuesta.alcanceMaximo = Math.max(respuesta.alcanceMaximo, arma.componente.getDistanciaLarga());
                    respuesta.danioMaximo += arma.componente.getDamage();
                    respuesta.minimoAlcanceCorto = Math.min(respuesta.minimoAlcanceCorto, arma.componente.getDistanciaCorta());
                }


            }
        }

        return respuesta;
    }

    private VidaMech calcularVida(Mech mech) {
        VidaMech respuesta = new VidaMech();
        //Sumamos los datos de sus blindajes y puntos
        respuesta.resumen += mech.datadefmech.getBlindajeAtrasTorsoCentral();
        respuesta.resumen += mech.datadefmech.getBlindajeAtrasTorsoDerecho();
        respuesta.resumen += mech.datadefmech.getBlindajeAtrasTorsoIzquierdo();
        respuesta.resumen += mech.datadefmech.getBlindajeBrazoDerecho();
        respuesta.resumen += mech.datadefmech.getBlindajeBrazoIzquierdo();
        respuesta.resumen += mech.datadefmech.getBlindajeCabeza();
        respuesta.resumen += mech.datadefmech.getBlindajePiernaDerecha();
        respuesta.resumen += mech.datadefmech.getBlindajePiernaIzquierda();
        respuesta.resumen += mech.datadefmech.getBlindajeTorsoCentral();
        respuesta.resumen += mech.datadefmech.getBlindajeTorsoDerecho();
        respuesta.resumen += mech.datadefmech.getBlindajeTorsoIzquierdo();
        respuesta.resumen += mech.datadefmech.getPuntosInternosBrazoDerecho();
        respuesta.resumen += mech.datadefmech.getPuntosInternosBrazoIzquierdo();
        respuesta.resumen += mech.datadefmech.getPuntosInternosCabeza();
        respuesta.resumen += mech.datadefmech.getPuntosInternosPiernaDerecha();
        respuesta.resumen += mech.datadefmech.getPuntosInternosPiernaIzquierda();
        respuesta.resumen += mech.datadefmech.getPuntosInternosTorsoCentral();
        respuesta.resumen += mech.datadefmech.getPuntosInternosTorsoDerecho();
        respuesta.resumen += mech.datadefmech.getPuntosInternosTorsoIzquierdo();
        return respuesta;
    }
}
