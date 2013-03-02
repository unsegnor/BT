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
public class AAvanzar extends Agente {

    @Override
    boolean preCondiciones(State estado) {
        return estado.getFaseActual() == Reglas.Fase.Movimiento
                //Y no esté determinada ya
                && (estado.getModalidadMovimiento() == Reglas.tiposDeMovimiento.Andar
                || estado.getModalidadMovimiento() == Reglas.tiposDeMovimiento.Correr);
    }

    @Override
    protected Collection<? extends AccionResultado> getAcciones(State estado) {
        ArrayList<AccionResultado> respuesta = new ArrayList<>();

        //Datos necesarios
        Mapa mapa = estado.getMapa();
        Phexagono hex = estado.getHexagonoActual();
        int lado = estado.getLadoActual();
        tiposDeMovimiento tm = estado.getModalidadMovimiento();
        Phexagono destino = mapa.cara(hex, lado);



        //Comprobar si es posible
        //Si tenemos los puntos de movimiento suficientes
        int PM;
        if (tm == tiposDeMovimiento.Andar) {
            PM = estado.getPMAndar();
        } else if (tm == tiposDeMovimiento.Correr) {
            PM = estado.getPMCorrer();
        } else {
            PM = 0;
        }

        if (PM > 0) {

            int costeAvance = mapa.calcularCosteCambio(hex, destino);
            

            //Acción
            Movimiento accion = new Movimiento();
            accion.setDestino(destino);
            accion.setLado_destino(lado);
            accion.setTipo(tm);

            //Crear paso
            Paso paso = new Paso();
            paso.setTipoDePaso(Reglas.tiposDePaso.Adelante);
            paso.setVeces_o_lado(1);

            //Añadir paso a la ruta
            Ruta ruta = new Ruta();
            ruta.getPasos().addAll(estado.getRutaActual().getPasos());
            ruta.getPasos().add(paso);

            //Definir la nueva ruta
            accion.setRuta(ruta);

            //Consecuencias
            State nuevoEstado = estado.shallowCopy();

            if (tm == tiposDeMovimiento.Andar) {
                nuevoEstado.setPMAndar(estado.getPMAndar() - costeAvance);
            } else if (tm == tiposDeMovimiento.Correr) {
                nuevoEstado.setPMCorrer(estado.getPMCorrer() - costeAvance);
            }




            //Montar Resultado
            Resultado rI = new Resultado();
            rI.setEstado(nuevoEstado);
            rI.setProbabilidad(1);
            //Montar AccionResultado
            AccionResultado arI = new AccionResultado();
            arI.setAccion(accion);
            arI.getResultados().add(rI);
            //Añadir a la lista
            respuesta.add(arI);
        }
        return respuesta;
    }
}
