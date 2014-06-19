/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Localizacion;
import static bt.Reglas.tiposAnguloDisparo.Derecho;
import static bt.Reglas.tiposAnguloDisparo.Izquierdao;
import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
class Punietazos extends AtaqueFisico {

    public Punietazos(EstadoDeJuego estado, Condiciones condiciones) {

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
                //Sólo podemos pegar con puños si estamos al mismo nivel o por debajo
                if (h_actual.getNivel() <= h_enemigo.getNivel()) {
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
                        case Izquierdao:
                            //Si el brazo izquierdo está operativo pegamos con él

                            if (datos.isConBrazoIzquiero() && datos.isConAntebrazoIzquierdo() && datos.isConHombroIzquierdo()) {

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

                            if (datos.isConBrazoIzquiero() && datos.isConAntebrazoIzquierdo() && datos.isConHombroIzquierdo()) {

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

            } else {
                System.out.println("Distinto nivel");
            }
        } else {
            System.out.println("Demasiado lejos para pegarle");
        }
    }
}
