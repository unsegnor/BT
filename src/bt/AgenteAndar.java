/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;
import bt.Reglas.tiposDePaso;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Víctor
 */
public class AgenteAndar extends Agente {

    @Override
    boolean preCondiciones(State estado) {
        /*
         * Puede andar si
         * estamos en la fase de movimiento
         * tiene puntos de movimiento
         * no está en el suelo
         * la modalidad de movimiento es ninguna o andar
         */
        return estado.getFaseActual() == Fase.Movimiento 
                && estado.getPMAndar() > 0
                && !estado.isMechCuerpoATierra()
                && (estado.getModalidadMovimiento() == Reglas.tiposDeMovimiento.Indefinido 
                    || estado.getModalidadMovimiento() == Reglas.tiposDeMovimiento.Andar);
    }

    @Override
    protected Collection<? extends AccionResultado> getAcciones(State estado) {
        //Este agente va a comprobar todos aquellos lugares donde puede llegar andando y cuáles serían los
        //posibles resultados y su probabilidad
        ArrayList<AccionResultado> respuesta = new ArrayList<>();
        
        //Necesitamos conocer su posición y el mapa
        Mapa mapa = estado.getMapa();
        Phexagono hex = estado.getHexagonoActual();
        int lado = estado.getLadoActual();
        int PM = estado.getPMAndar();
        //Cargamos así mismo la ruta que estamos montando
        Ruta ruta = estado.getRutaActual();
        
        /*
         * Podremos mover adelante o atrás dependiendo del terreno que hay enfrente y detrás
         */
        
        //Calcular acción de mover al lado izquierdo
        Movimiento girarIzquierda = new Movimiento();
        //Definir destino
        girarIzquierda.setDestino(hex);
        //Definir lado
        girarIzquierda.setLado_destino(Mapa.caraIzquierda(lado));
        //Definir tipo de movimiento
        girarIzquierda.setTipo(Reglas.tiposDeMovimiento.Andar);
        //crear y definir la nueva ruta
        Ruta rutaIzquierda = new Ruta();
        //Copiar los pasos de la ruta anterior
        ArrayList<Paso> pasos = new ArrayList<>(ruta.getPasos());
        //Crear y definir el nuevo paso
        Paso p = new Paso();
        p.setTipoDePaso(tiposDePaso.Izquierda);
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
        
        
        
        return respuesta;
    }
    
}
