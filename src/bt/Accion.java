/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * Contiene la descripción de una acción. Una acción se traduce directamente en una escritura de fichero de respuesta.
 * @author Víctor
 */
abstract class Accion {
    
    abstract Reglas.Fase getFase();
    
}
