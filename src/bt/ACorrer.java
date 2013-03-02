/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Víctor
 */
public class ACorrer extends Agente {

    @Override
    boolean preCondiciones(State estado) {
        return estado.getFaseActual() == Reglas.Fase.Movimiento
                //Y no esté determinada ya
                && estado.getModalidadMovimiento() == Reglas.tiposDeMovimiento.Indefinido;
    }

    @Override
    protected Collection<? extends AccionResultado> getAcciones(State estado) {
        ArrayList<AccionResultado> respuesta = new ArrayList<>();

        //Correr
        Movimiento aI = new Movimiento();
        aI.setTipo(Reglas.tiposDeMovimiento.Correr);
        //Consecuencias
        State sI = estado.shallowCopy();
        sI.setModalidadMovimiento(Reglas.tiposDeMovimiento.Correr);
        //Montar Resultado
        Resultado rI = new Resultado();
        rI.setEstado(sI);
        rI.setProbabilidad(1);
        //Montar AccionResultado
        AccionResultado arI = new AccionResultado();
        arI.setAccion(aI);
        arI.getResultados().add(rI);
        //Añadir a la lista
        respuesta.add(arI);

        return respuesta;
    }
}
