/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;
import bt.Reglas.tiposAnguloDisparo;

/**
 *
 * @author Víctor
 */
class DispararConTodoAlMasCercano extends AtaqueArmas {

    public DispararConTodoAlMasCercano(EstadoDeJuego estado) {

        //Detectar al enemigo más cercano con LDV
        DataMech actual = estado.getMechActual();
        DataMech enemigo;

        Par<DataMech, ResultadoLDV> r = SunTzu.getEnemigoMasCercanoConLDV(estado);
        enemigo = r.primero;
        ResultadoLDV rLDV = r.segundo;

        //Si tenemos enemigo seguimos
        if (enemigo != null) {

            //Obtenemos la posición de su torso
            Posicion p_torso_enemigo = new Posicion(enemigo.getColumna(), enemigo.getFila(), enemigo.getEncaramientoTorso());

            //Obtenemos la posición actual del torso
            Posicion p_torso_actual = new Posicion(actual.getColumna(), actual.getFila(), actual.getEncaramientoTorso());

            //Obtenemos la posición de las piernas
            Posicion p_piernas_actual = new Posicion(actual.getColumna(), actual.getFila(), actual.getEncaramientoMech());

            //Definimos ese como el objetivo
            this.objetivo_primario = p_torso_enemigo.getHexagono();

            //Determinar ángulo de disparo al objetivo
            tiposAnguloDisparo anguloDisparoParteSuperior = obtenerAnguloDeDisparo(estado, p_torso_actual, p_torso_enemigo);
            tiposAnguloDisparo anguloDisparoPiernas = obtenerAnguloDeDisparo(estado, p_piernas_actual, p_torso_enemigo);

            //Si tenemos cobertura parcial las piernas no pueden disparar
            boolean disparan_piernas = !rLDV.CPinversa;
            
            

            //Comprobar si estamos encarados hacia el objetivo
            //para determinar qué armas disparamos
        /*
             * Por ejemplo, si estamos sumergidos en agua no podemos disparar aquellas armas sumergidas
             * si estamos de espaldas sólo podemos disparar las de atrás
             * si estamos cubiertos no podemos disparar las de los pies
             * 
             */


            //Realizamos tantos disparos como armas tengamos
            int nj = estado.jugador;
            int narmas = estado.datos_def_mechs[nj].getnArmas();

            //Recorremos las armas de que disponemos


        }


    }

    @Override
    Reglas.Fase getFase() {
        return Fase.AtaqueArmas;
    }

    private Reglas.tiposAnguloDisparo obtenerAnguloDeDisparo(EstadoDeJuego estado, Posicion origen, Posicion destino) {
        tiposAnguloDisparo respuesta = tiposAnguloDisparo.Desconocido;
        //Obtenemos la cara que encara directamente al enemigo
        int cara_enemigo = estado.mapa.encararA(origen.getHexagono(), destino.getHexagono());
        int cara_origen = origen.getLado();

        //Calculamos la diferencia de nuestro encaramiento al suyo
        int dif = Posicion.distanciaEntreCaras(origen.getLado(), cara_enemigo);

        //Según la diferencia estaremos en un ángulo u otro

        //Si la diferencia es menor que 2 el enemigo está en el ángulo  frontal
        if (dif < 2) {
            respuesta = tiposAnguloDisparo.Frontal;
        } else if (dif == 3) {
            //Si está en el lado opuesto 
            respuesta = tiposAnguloDisparo.Posterior;
        } else {
            //Sino la diferencia es 2 pero tenemos que averiguar por qué lado

            //Debemos averiguar si la diferencia es hacia abajo o hacia arriba

            //Obtenemos la cara que está dos posiciones a la derecha
            int cara_derecha = origen.giroDerecha().giroDerecha().getLado();

            //Si es igual que la del enemigo está a la derecha, sino a la izquierda, no queda otra
            if (cara_derecha == cara_enemigo) {
                respuesta = tiposAnguloDisparo.Derecho;
            } else {
                respuesta = tiposAnguloDisparo.Izquierdao;
            }
        }


        return respuesta;
    }
}
