/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Visibilidad;
import java.util.ArrayList;

/**
 * El resultado de calcular la LDV son dos booleanos que indican si hay visión y si hay cobertura parcial y un conjunto de casillas por las que pasa la línea.
 * @author Víctor
 */
public class ResultadoLDV {
    //Visibilidad
    Visibilidad visibilidad;
    
    //LDV
    boolean LDV;
    
    //Cobertura Parcial directa
    boolean CPdirecta;
    
    //Cobertura Parcial inversa, del destino al orígen
    boolean CPinversa;
    
    //Intermedios
    ArrayList<ArrayList<Phexagono> > intermedios;
    
    //Conjunto de posiciones de hexágonos desde el orígen hasta el fin
    Phexagono hexagonos[];
    
}
