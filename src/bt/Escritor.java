/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * //Se encarga de recibir objetos que representan respuestas y escribir los archivos.
 * @author Víctor
 */
public class Escritor {
    
    //Recibe acción
    public static void escribeAccion(Accion accion){}
    
    public static void escribeAccion(Movimiento movimiento){
        //Traducir a DataMov
        DataMov dm = new DataMov();
        dm.setTipoDeMovimiento(movimiento.getTipo());
        int npasos = movimiento.getRuta().getPasos().size();
        dm.setNumero_de_pasos(npasos);
        dm.setPasos(movimiento.getRuta().getPasos().toArray(new Paso[npasos]));
        dm.setHexagono_destino(movimiento.getDestino());
        dm.setLado_destino(movimiento.getLado_destino());
        dm.setUsaMASC(movimiento.isUsaMASC());
    }
    
    //Escribe el movimiento que se le pasa en un archivo
    public static void escribeMovimiento(int Jugador, DataMov dm){
            
        //Abrir archivo
        File archivo = new File("accionJ"+Jugador+".sbt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            
            switch(dm.getTipoDeMovimiento()){
            
                case Inmovil:
                        bw.write("Inmovil\n");
                break;
                case Saltar:
                    bw.write("Saltar\n");
                    
                    //Escribir hexagono de destino
                    bw.write(dm.getHexagono_destino().toString() + "\n");
                    
                    //Escribir el lado
                    bw.write(dm.getLado_destino()+"\n");
                    
                break;
                case Andar:
                    bw.write("Andar\n");
                    
                    //Escribir hexagono de destino
                    bw.write(dm.getHexagono_destino().toString() + "\n");
                    
                    //Escribir el lado
                    bw.write(dm.getLado_destino()+"\n");
                    
                    //Escribir si va a usar MASC
                    bw.write(dm.isUsaMASC()?"True":"False");
                    
                    //Número de pasos
                    int npasos = dm.getNumero_de_pasos();
                    bw.write(npasos+"\n");
                    
                    //Imprimir los pasos
                    for(int i=0; i< npasos; i++){
                        bw.write(dm.getPasos()[i].toString()+"\n");
                    }
                break;
                case Correr:
                    bw.write("Correr\n");
                    
                    //Escribir hexagono de destino
                    bw.write(dm.getHexagono_destino().toString() + "\n");
                    
                    //Escribir el lado
                    bw.write(dm.getLado_destino()+"\n");
                    
                    //Escribir si va a usar MASC
                    bw.write(dm.isUsaMASC()?"True":"False");
                    
                    //Número de pasos
                    int npasos2 = dm.getNumero_de_pasos();
                    bw.write(npasos2+"\n");
                    
                    //Imprimir los pasos
                    for(int i=0; i< npasos2; i++){
                        bw.write(dm.getPasos()[i].toString()+"\n");
                    }
                break;
            }
       /*     
            //Escribir el tipo de movimiento
            bw.write(dm.getTipoDeMovimiento().toString() + "\n");
            //Escribir el lado de destino
            * */
            
        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
