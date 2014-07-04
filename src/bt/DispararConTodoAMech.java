/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
class DispararConTodoAMech extends AtaqueArmas {

    public DispararConTodoAMech(DataMech enemigo, ResultadoLDV rLDV, EstadoDeJuego estado) {
        DataMech actual = estado.getMechActual();

        int calor_actual = actual.getTemperatura();
        int calor_disipado = estado.datos_def_mechs[estado.jugador].getnRadiadores();
        int calor_maximo = 15 - 5;
        int calor_permitido = calor_maximo + calor_disipado - calor_actual;



        //Obtenemos la posición de su torso
        Posicion p_torso_enemigo = new Posicion(enemigo.getColumna(), enemigo.getFila(), enemigo.getEncaramientoTorso());

        System.out.println("Detectado enemigo con ldv en " + p_torso_enemigo.getHexagono());

        //Obtenemos la posición actual del torso
        Posicion p_torso_actual = new Posicion(actual.getColumna(), actual.getFila(), actual.getEncaramientoTorso());

        //Obtenemos la posición de las piernas
        Posicion p_piernas_actual = new Posicion(actual.getColumna(), actual.getFila(), actual.getEncaramientoMech());

        //Definimos ese como el objetivo
        this.objetivo_primario = p_torso_enemigo.getHexagono();

        //Determinar ángulo de disparo al objetivo
        Reglas.tiposAnguloDisparo anguloDisparoParteSuperior = SunTzu.obtenerAnguloDeDisparo(estado, p_torso_actual, p_torso_enemigo);
        Reglas.tiposAnguloDisparo anguloDisparoPiernas = SunTzu.obtenerAnguloDeDisparo(estado, p_piernas_actual, p_torso_enemigo);

        //Si tenemos cobertura parcial las piernas no pueden disparar
        boolean disparan_piernas = !rLDV.CPinversa;


        //Determinamos las localizaciones válidas en función del ángulo y de la cobertura parcial
        boolean[] localizacion_valida = SunTzu.getLocalizacionesValidasParaDisparar(estado, anguloDisparoParteSuperior, anguloDisparoPiernas, disparan_piernas);


        //Comprobar las armas que tenemos
        int nj = estado.jugador;
        DataComponente[] componentes = estado.datos_def_mechs[nj].getComponentesEquipados();

        ArrayList<Arma> armas = new ArrayList<Arma>();
        ArrayList<Municion> municiones = new ArrayList<Municion>();


        //Obtenemos la distancia entre los mechs
        int distancia = estado.mapa.distanciaCasillas(p_torso_enemigo.getHexagono(), p_torso_actual.getHexagono());

        //Para cada localización válida comprobamos qué slots son armas
        for (int loc = 0; loc < localizacion_valida.length; loc++) {

            //Si es una localización válida comprobamos los slots
            if (localizacion_valida[loc]) {

                DataLocalizacion localizacion = estado.datos_def_mechs[nj].getLocalizacion()[loc];

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
                                    //Comprobamos si el objetivo se encuentra en rango
                                    if (distancia >= componente.getDistanciaMinima() && distancia <= componente.getDistanciaLarga()) {
                                        //Si el ángulo es posterior comprobamos que esté en la parte de atrás
                                        if (anguloDisparoParteSuperior == Reglas.tiposAnguloDisparo.Posterior) {
                                            if (componente.isMontadaEnParteTrasera()) {
                                                //Determinamos que es un arma válida
                                                valida = true;
                                            }
                                        } else {
                                            if (!componente.isMontadaEnParteTrasera()) {
                                                valida = true;
                                            }
                                        }
                                    }
                                }
                            }
                            //Si es válida la añadimos como arma
                            if (valida) {
                                System.out.println("Arma válida " + componente.getNombre());
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

        //Para cada arma componemos el disparo y lo agregamos a los disparos que vamos a realizar
        this.disparos = new ArrayList<DisparoArma>();

        for (Arma arma : armas) {
            DisparoArma disparo = new DisparoArma();
            disparo.objetivo = objetivo_primario;
            disparo.dobleCadencia = false;
            disparo.localizacion_arma = SunTzu.traducirLocalizacion(arma.componente.getLocalizacion());
            disparo.slot_arma = arma.nslot;
            disparo.tipoObjetivo = Reglas.TiposObjetivo.Mech;

            boolean valida = false;
            //Comprobamos el tipo de arma
            //Si es de balística buscamos su munición
            boolean encontrada = false;
            Municion municion = null;
            if (arma.componente.getTipoDeArma().contains("Bal") || arma.componente.getTipoDeArma().contains("Misi")) {
                //Buscamos la primera munición que se pueda usar con este arma
                int codArma = arma.componente.getCodigo();
                
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
                disparo.localizacion_municion = SunTzu.traducirLocalizacion(municion.componente.getLocalizacion());
                disparo.slot_municion = municion.nslot;
                valida = true;
            } else if (arma.componente.getTipoDeArma().contains("Energ")) {
                valida = true;
            } else {
                valida = false;
            }


            //Si es válida
            if (valida) {
                //Si no va a añadir demasiado calor la agregamos

                if (arma.componente.getCalor() < calor_permitido) {
                    disparos.add(disparo);
                    calor_permitido -= arma.componente.getCalor();
                }
            }


        }

        //Aquí deberíamos tener los disparos en la lista de disparos
        //Debería funcionar
    }
}
