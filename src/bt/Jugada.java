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
public class Jugada implements Runnable{

    String tiempo;
    String jugador;
    String fase;
    
    public Jugada(String tiempo, String jugador, String fase){
        this.tiempo = tiempo;
        this.jugador = jugador;
        this.fase = fase;
        
    }
    
    @Override
    public void run() {
        
        //El tiempo está en segundos
        double tiempo_actual = System.currentTimeMillis();
        double tiempo_inicio = Double.parseDouble(tiempo)*1000 + 500;
        double tiempo_maximo = tiempo_inicio + 10000;       
        double tiempo_consumido = tiempo_actual - tiempo_inicio;
        
        
        System.out.println("Pensando jugada (" + tiempo  + ", " + jugador + ", " + fase +")... tiempo consumido: " + tiempo_consumido + "ms");
        
        //Convertir datos
        int i_jugador = Integer.parseInt(jugador);
        Fase c_fase = Fase.Desconocido;
        
        switch(fase){
            case "Movimiento":
                c_fase = Fase.Movimiento;
            break;
            case "Reaccion":
                c_fase = Fase.Reaccion;
            break;
            case "AtaqueArmas":
                c_fase = Fase.AtaqueArmas;
            break;
            case "AtaqueFisico":
                c_fase = Fase.AtaqueFisico;
            break;
            case "FinalTurno":
                c_fase = Fase.FinalTurno;
            break;
            default:
                System.err.println("La fase " + fase + " no existe.");
            break;
        }
        
        //Obtener mejor estrategia hasta el momento
        //Obtener mejor siguiente movimiento hasta el momento
        Accion accion = SunTzu.siguienteAccion(i_jugador, c_fase);
        //Escribirla
        Escritor.escribeAccion(accion);
    }
    
}
