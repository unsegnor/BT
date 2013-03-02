/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.tiposDeMovimiento;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Víctor
 */
public class AgenteGirar extends Agente {
    private int costeGiroDerecha = 1;
    private int costeGiroIzquierda = 1;

    @Override
    boolean preCondiciones(State estado) {
        /*
         * Puede girar si estamos en la fase de movimiento le quedan puntos de
         * movimiento
         */
        return estado.getFaseActual() == Reglas.Fase.Movimiento
                //Si puede andar y viene andando
                && (estado.getPMAndar() > 0 && estado.getModalidadMovimiento() == Reglas.tiposDeMovimiento.Andar
                //o si viene corriendo y le quedan puntos de correr
                || estado.getPMCorrer() > 0 && estado.getModalidadMovimiento() == Reglas.tiposDeMovimiento.Correr);
    }

    @Override
    protected Collection<? extends AccionResultado> getAcciones(State estado) {
        //Este agente va a comprobar los giros que puede realizar y sus consecuencias
        ArrayList<AccionResultado> respuesta = new ArrayList<>();

        //No necesitamos conocer nada del mapa
        tiposDeMovimiento tm = estado.getModalidadMovimiento();
        Phexagono hex = estado.getHexagonoActual();
        int lado = estado.getLadoActual();
        int PMandar = estado.getPMAndar();
        int PMCorrer = estado.getPMCorrer();
        //Cargamos así mismo la ruta que estamos montando
        Ruta ruta = estado.getRutaActual();

        /*
         * Podemos mover siempre a los lados siempre que tengamos un punto de
         * movimiento
         */

        //Calcular acción de mover al lado izquierdo
        Movimiento girarIzquierda = new Movimiento();
        //Definir destino
        girarIzquierda.setDestino(hex);
        //Definir lado
        int lado_final = Mapa.caraIzquierda(lado);
        girarIzquierda.setLado_destino(lado_final);
        //Definir tipo de movimiento
        //girarIzquierda.setTipo(Reglas.tiposDeMovimiento.Andar);
        //crear y definir la nueva ruta
        Ruta rutaIzquierda = new Ruta();
        //Copiar los pasos de la ruta anterior
        ArrayList<Paso> pasos = new ArrayList<>(ruta.getPasos());
        //Crear y definir el nuevo paso
        Paso p = new Paso();
        p.setTipoDePaso(Reglas.tiposDePaso.Izquierda);
        p.setVeces_o_lado(1);
        //Añadir el nuevo paso
        pasos.add(p);
        //Definir los pasos de la nueva ruta
        rutaIzquierda.setPasos(pasos);
        //Asignar la nueva ruta al movimiento
        girarIzquierda.setRuta(rutaIzquierda);

        //Calcular resultado
        //Crear estado nuevo
        State estadoIzquierda = estado.shallowCopy(); //De momento copia superficial
        //Realizar las modificaciones
        //El lado pasa a ser el lado de la izquierda
        estadoIzquierda.setLadoActual(lado_final);
        switch(tm){
            case Andar:
                estadoIzquierda.setPMAndar(PMandar-costeGiroIzquierda);
            break;
            case Correr:
                estadoIzquierda.setPMCorrer(PMCorrer-costeGiroIzquierda);
            break;
        }
        //Creamos el nuevo resultado
        Resultado resultadoI = new Resultado();
        resultadoI.setEstado(estadoIzquierda);
        resultadoI.setProbabilidad(1);
        ArrayList<Resultado> resultadosI = new ArrayList<>();
        resultadosI.add(resultadoI);
        //Creamos la AcciónResultado
        AccionResultado arI = new AccionResultado();
        arI.setAccion(girarIzquierda);
        arI.setResultados(resultadosI);
        //Añadimos la acción resultado a la lista de respuesta
        respuesta.add(arI);

        //Calcular acción de mover al lado derecho
        Movimiento girarD = new Movimiento();
        //Definir destino
        girarD.setDestino(hex);
        //Definir lado
        int lado_final_derecha = Mapa.caraDerecha(lado);
        girarD.setLado_destino(lado_final_derecha);
        //Definir tipo de movimiento
        //girarIzquierda.setTipo(Reglas.tiposDeMovimiento.Andar);
        //crear y definir la nueva ruta
        Ruta rutaD = new Ruta();
        //Copiar los pasos de la ruta anterior
        ArrayList<Paso> pasosD = new ArrayList<>(ruta.getPasos());
        //Crear y definir el nuevo paso
        Paso pD = new Paso();
        pD.setTipoDePaso(Reglas.tiposDePaso.Derecha);
        pD.setVeces_o_lado(1);
        //Añadir el nuevo paso
        pasosD.add(pD);
        //Definir los pasos de la nueva ruta
        rutaD.setPasos(pasosD);
        //Asignar la nueva ruta al movimiento
        girarD.setRuta(rutaD);

        //Calcular resultado
        //Crear estado nuevo
        State estadoD = estado.shallowCopy(); //De momento copia superficial
        //Realizar las modificaciones
        //El lado pasa a ser el lado de la izquierda
        estadoD.setLadoActual(lado_final_derecha);
        switch(tm){
            case Andar:
                estadoD.setPMAndar(PMandar-costeGiroDerecha);
            break;
            case Correr:
                estadoD.setPMCorrer(PMCorrer-costeGiroDerecha);
            break;
        }
        //Creamos el nuevo resultado
        Resultado resultadoD = new Resultado();
        resultadoD.setEstado(estadoD);
        resultadoD.setProbabilidad(1);
        ArrayList<Resultado> resultadosD = new ArrayList<>();
        resultadosD.add(resultadoD);
        //Creamos la AcciónResultado
        AccionResultado arD = new AccionResultado();
        arD.setAccion(girarD);
        arD.setResultados(resultadosD);
        //Añadimos la acción resultado a la lista de respuesta
        respuesta.add(arD);

        return respuesta;
    }
}
