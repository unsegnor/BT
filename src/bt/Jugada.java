/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;

/**
 *
 * @author Víctor
 */
public class Jugada implements Runnable {

    String tiempo;
    String jugador;
    String fase;

    public Jugada(String tiempo, String jugador, String fase) {
        this.tiempo = tiempo;
        this.jugador = jugador;
        this.fase = fase;

    }

    @Override
    public void run() {

        //El tiempo está en segundos
        double tiempo_actual = System.currentTimeMillis();
        double tiempo_inicio = Double.parseDouble(tiempo) * 1000 + 500;
        double tiempo_maximo = tiempo_inicio + 10000;
        double tiempo_consumido = tiempo_actual - tiempo_inicio;


        System.out.println("Pensando jugada (" + tiempo + ", " + jugador + ", " + fase + ")... tiempo consumido: " + tiempo_consumido + "ms");

        //Convertir datos
        int i_jugador = Integer.parseInt(jugador);
        Fase c_fase = Fase.Desconocido;

        if ("Movimiento".equals(fase)) {
            c_fase = Fase.Movimiento;
        } else if ("Reaccion".equals(fase)) {
            c_fase = Fase.Reaccion;
        } else if ("AtaqueArmas".equals(fase)) {
            c_fase = Fase.AtaqueArmas;
        } else if ("AtaqueFisico".equals(fase)) {
            c_fase = Fase.AtaqueFisico;
        } else if ("FinalTurno".equals(fase)) {
            c_fase = Fase.FinalTurno;
        } else {
            System.err.println("La fase " + fase + " no existe.");
        }

        //Obtener mejor estrategia hasta el momento
        //Obtener mejor siguiente movimiento hasta el momento
        Accion accion = SunTzu.siguienteAccion(i_jugador, c_fase);
        System.out.println("Accion escogida:" + accion.toString());
        //Escribirla
        Escritor.escribeAccion(i_jugador, accion);
        tiempo_consumido = System.currentTimeMillis() - tiempo_inicio;
        System.out.println("Hecho en " + tiempo_consumido + "ms");
    }
}
