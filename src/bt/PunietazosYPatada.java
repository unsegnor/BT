/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Localizacion;
import static bt.Reglas.tiposAnguloDisparo.Derecho;
import static bt.Reglas.tiposAnguloDisparo.Frontal;
import static bt.Reglas.tiposAnguloDisparo.Izquierda;
import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
class PunietazosYPatada extends AtaqueFisico {

    public PunietazosYPatada(EstadoDeJuego estado, Condiciones condiciones) {

        DataMech actual = estado.getMechActual();

        //Obtener el enemigo más cercano
        DataMech enemigo = SunTzu.getEnemigoMasCercano(estado);

        //Obtenemos la posición de su torso
        Posicion p_torso_enemigo = new Posicion(enemigo.getColumna(), enemigo.getFila(), enemigo.getEncaramientoTorso());


        System.out.println("Detectado enemigo cercano en " + p_torso_enemigo.getHexagono());


        //Obtenemos la posición actual del torso
        Posicion p_torso_actual = new Posicion(actual.getColumna(), actual.getFila(), actual.getEncaramientoTorso());

        //Obtenemos la posición de las piernas
        Posicion p_piernas_actual = new Posicion(actual.getColumna(), actual.getFila(), actual.getEncaramientoMech());


        //Comprobar la distancia hasta el enemigo
        if (estado.mapa.distanciaCasillas(p_torso_enemigo.getHexagono(), p_torso_actual.getHexagono()) == 1) {

            //Comprobar que están al mismo nivel
            Hexagono h_enemigo = estado.mapa.casilla(p_torso_enemigo.getHexagono());
            Hexagono h_actual = estado.mapa.casilla(p_torso_actual.getHexagono());

            //Calculo altura del enemigo


            if (Math.abs(h_enemigo.getNivel() - h_actual.getNivel()) < 2) {

                Phexagono objetivo_primario;
                //Definimos ese como el objetivo
                objetivo_primario = p_torso_enemigo.getHexagono();

                //Determinar ángulo de ataque al objetivo
                Reglas.tiposAnguloDisparo anguloGolpeoSuperior = SunTzu.obtenerAnguloDeDisparo(estado, p_torso_actual, p_torso_enemigo);
                Reglas.tiposAnguloDisparo anguloGolpeoPiernas = SunTzu.obtenerAnguloDeDisparo(estado, p_piernas_actual, p_torso_enemigo);

                boolean golpean_piernas = false;

                this.ataques = new ArrayList<AtaqueArmaFisica>();

                DataDefMech datos = estado.datos_def_mechs[estado.jugador];


                //Determinamos las localizaciones válidas en función del ángulo
                //Sólo podemos pegar con puños si estamos al mismo nivel (y no está en el suelo) o por debajo
                if (h_actual.getNivel() < h_enemigo.getNivel()
                        || (h_actual.getNivel() == h_enemigo.getNivel()
                        && !enemigo.isEnelsuelo())) {
                    switch (anguloGolpeoSuperior) {
                        case Derecho:
                            //Si el brazo derecho está operativo pegamos con él
                            if (datos.isConBrazoDerecho() && datos.isConAntebrazoDerecho() && datos.isConHombroDerecho()) {

                                AtaqueArmaFisica a = new AtaqueArmaFisica();
                                a.localizacion = Localizacion.BD;
                                a.objetivo = objetivo_primario;
                                a.slot = 1000;
                                a.tipoObjetivo = Reglas.TiposObjetivo.Mech;
                                this.ataques.add(a);
                            }


                            break;
                        case Izquierda:
                            //Si el brazo izquierdo está operativo pegamos con él

                            if (datos.isConBrazoIzquierdo() && datos.isConAntebrazoIzquierdo() && datos.isConHombroIzquierdo()) {

                                AtaqueArmaFisica a = new AtaqueArmaFisica();
                                a.localizacion = Localizacion.BI;
                                a.objetivo = objetivo_primario;
                                a.slot = 1000;
                                a.tipoObjetivo = Reglas.TiposObjetivo.Mech;
                                this.ataques.add(a);
                            }

                            break;

                        case Frontal:
                            //Si es el ángulo frontal pegamos con todos los brazos operativos

                            if (datos.isConBrazoDerecho() && datos.isConAntebrazoDerecho() && datos.isConHombroDerecho()) {

                                AtaqueArmaFisica a = new AtaqueArmaFisica();
                                a.localizacion = Localizacion.BD;
                                a.objetivo = objetivo_primario;
                                a.slot = 1000;
                                a.tipoObjetivo = Reglas.TiposObjetivo.Mech;
                                this.ataques.add(a);
                            }

                            if (datos.isConBrazoIzquierdo() && datos.isConAntebrazoIzquierdo() && datos.isConHombroIzquierdo()) {

                                AtaqueArmaFisica a = new AtaqueArmaFisica();
                                a.localizacion = Localizacion.BI;
                                a.objetivo = objetivo_primario;
                                a.slot = 1000;
                                a.tipoObjetivo = Reglas.TiposObjetivo.Mech;
                                this.ataques.add(a);
                            }

                            break;
                    }
                }



                //Podemos pegar patadas sólo si estamos al mismo nivel o uno por encima y está de pie
                if (h_actual.getNivel() == h_enemigo.getNivel()
                        || (h_actual.getNivel() > h_enemigo.getNivel()
                        && !enemigo.isEnelsuelo())) {

                    //Si el enemigo está en el ángulo frontal continuamos
                    if (anguloGolpeoPiernas == Reglas.tiposAnguloDisparo.Frontal) {

                        //Si tenemos las dos caderas bien
                        Mech mech = estado.mechs.get(estado.jugador);

                        boolean podemos = true;

                        //Recorrer actuadores y comprobar las dos caderas
                        DataActuador[] actuadores = mech.datadefmech.getActuadores();
                        for (DataActuador actuador : actuadores) {
                            //Si tenemos una cadera
                            if (actuador.getNombre().contains("Cadera")) {
                                //Debe estar operativa, sino false
                                if (!actuador.isOperativo()) {
                                    podemos = false;
                                }
                            }
                        }
                        
                        //Si todas las caderas están operativas podemos dar la patada
                        if (podemos) {

                            //Si es el ángulo frontal pegamos con la pierna que esté operativa
                            if (true) {

                                AtaqueArmaFisica a = new AtaqueArmaFisica();
                                a.localizacion = Localizacion.PD;
                                a.objetivo = objetivo_primario;
                                a.slot = 2000;
                                a.tipoObjetivo = Reglas.TiposObjetivo.Mech;
                                this.ataques.add(a);
                            }else {

                                AtaqueArmaFisica a = new AtaqueArmaFisica();
                                a.localizacion = Localizacion.PI;
                                a.objetivo = objetivo_primario;
                                a.slot = 2000;
                                a.tipoObjetivo = Reglas.TiposObjetivo.Mech;
                                this.ataques.add(a);
                            }
                        }
                    }
                }
            } else {
                System.out.println("Distinto nivel");
            }
        } else {
            System.out.println("Demasiado lejos para pegarle");
        }
    }
}
