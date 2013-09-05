/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import Aestrella.I_Coste;

/**
 * Aquí vamos a almacenar las reglas del juego. Y las funciones que las consultan.
 * @author Víctor
 */
public class Reglas {
    public static int costeGiro = 1;
    
    
    //Tipos de movimiento
    public enum tiposDeMovimiento {Indefinido, Inmovil, Andar, Correr, Saltar};
    //Tipos de reacción
    public enum TiposDeReaccion {Igual, Derecha, Izquierda};
    //Tipos de localización
    public enum Localizacion {BI, BD, PI, PD, TC, TI, TD, CAB, BIBD};
    //Tipos de Objetivo
    public enum TiposObjetivo {Mech, Hexagono, Ninguno};
    //Fases
    public enum Fase{Movimiento, Reaccion, AtaqueArmas, AtaqueFisico, FinalTurno, Desconocido};
    //Tipos de paso
    public enum tiposDePaso {Adelante, Atras, Izquierda, Derecha, Levantarse, CuerpoATierra};
    //Tipos de visibilidad
    public enum Visibilidad {Nula, Total, Parcial, Desconocida};
    //Tipos de terreno
    public enum TerrenoTipo{Abierto, Pavimentado, Agua, Pantanoso};
    //Tipos de objeto
    public enum ObjetoTipo{Escombros, BosqueLigero, BosqueDenso, EdificioLigero, EdificioMedio, EdificioPesado, EdificioReforzado, Bunker, Nada};
    //Tipos de ángulo de disparo
    public enum tiposAnguloDisparo{Frontal, Izquierdao, Derecho, Posterior, Desconocido};
    
    
    //Esta función devuelve las acciones posibles desde un estado.
    Accion acciones_posibles(EstadoDeJuego estado){
        
        //TODO 
        
        
        
        switch(estado.fase){
            case Movimiento:
                //De momento calcular casillas adyacentes y acciones que lleven a ellas
                //Phexagono posicionActual = estado.jugadores[estado.jugador]
                //DataHexagono[] adyacentes = estado.mapa.adyacentes(columna, fila)
            break;
            case Reaccion:
            break;
            case AtaqueArmas:
            break;
            case AtaqueFisico:
            break;
            case FinalTurno:
            break;
        }
        
        
               
        return null;
    }
    
    //Comprueba si es posible una tarea
    boolean posible(Tarea tarea, EstadoDeJuego estado){
        
        return false;
    }
    
}
