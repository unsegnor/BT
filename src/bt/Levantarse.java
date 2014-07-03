/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 *
 * @author Víctor
 */
public class Levantarse extends Movimiento {

    public Levantarse(EstadoDeJuego estado, Condiciones condiciones) {

        //Nos levantamos mirando hacia el enemigo más cercano

        //Determinar el enemigo
        DataMech enemigo_cercano = SunTzu.getEnemigoMasCercano(estado);

        //Mech actual
        DataMech actual = estado.getMechActual();

        //Posición del enemigo
        Posicion p_enemigo = new Posicion(enemigo_cercano.getColumna(), enemigo_cercano.getFila(), enemigo_cercano.getEncaramientoMech());

        //Posicion actual
        Posicion p_actual = new Posicion(actual.getColumna(), actual.getFila(), actual.getEncaramientoMech());

        //Determinar el lado al que debemos mirar para encararlo
        int cara = Mapa.encarar(p_actual.getHexagono(), p_enemigo.getHexagono());

        //Tenemos que levantarnos hacia el lado en que está el enemigo
        this.setTipo(Reglas.tiposDeMovimiento.Andar);
        this.setDestino(p_actual.getHexagono());
        this.setLado_destino(cara);
        
        //Montamos la ruta con un único paso para levantarnos
        Paso paso = new Paso();
        paso.setTipoDePaso(Reglas.tiposDePaso.Levantarse);
        paso.setVeces_o_lado(cara);
        Ruta ruta = new Ruta();
        ruta.getPasos().add(paso);
        this.setRuta(ruta);
        
    }
}
