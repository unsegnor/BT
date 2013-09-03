/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import static bt.Reglas.TiposDeReaccion.Derecha;
import static bt.Reglas.TiposDeReaccion.Igual;
import static bt.Reglas.TiposDeReaccion.Izquierda;
import static bt.Reglas.tiposDeMovimiento.Andar;
import static bt.Reglas.tiposDeMovimiento.Correr;
import static bt.Reglas.tiposDeMovimiento.Inmovil;
import static bt.Reglas.tiposDeMovimiento.Saltar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * //Se encarga de recibir objetos que representan respuestas y escribir los
 * archivos.
 *
 * @author Víctor
 */
public class Escritor {

    //Recibe acción
    public static void escribeAccion(int jugador, Accion accion) {

        if (accion != null) {
            Reglas.Fase fase = accion.getFase();

            switch (fase) {
                case Movimiento:
                    escribeMovimiento(jugador, (Movimiento) accion);
                    break;
                case Reaccion:
                    escribeReaccion(jugador, (Reaccion) accion);
                    break;
                case AtaqueArmas:
                    escribeAtaqueArmas(jugador, (AtaqueArmas)accion);
                    break;
                case AtaqueFisico:
                    escribirAtaqueFisico(jugador, (AtaqueFisico) accion);
                    break;
                case FinalTurno:
                    escribirFinalDeTurno(jugador, (FinalTurno) accion);
            }
        }

    }

    public static void escribeMovimiento(int jugador, Movimiento movimiento) {

        //Discernir tipos de movimiento

        //Traducir a DataMov
        DataMov dm = new DataMov();
        dm.setTipoDeMovimiento(movimiento.getTipo());
        int npasos = 0;
        if (movimiento.getRuta() != null) {
            npasos = movimiento.getRuta().getPasos().size();
            dm.setPasos(movimiento.getRuta().getPasos().toArray(new Paso[npasos]));
        }
        dm.setHexagono_destino(movimiento.getDestino());
        dm.setLado_destino(movimiento.getLado_destino());
        dm.setUsaMASC(movimiento.isUsaMASC());

        dm.setNumero_de_pasos(npasos);

        //Escribir el movimiento
        escribeMovimiento(jugador, dm);
    }

    //Escribe el movimiento que se le pasa en un archivo
    public static void escribeMovimiento(int Jugador, DataMov dm) {

        //Abrir archivo
        File archivo = new File("accionJ" + Jugador + ".sbt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            switch (dm.getTipoDeMovimiento()) {

                case Inmovil:
                    bw.write("Inmovil\n");
                    break;
                case Saltar:
                    bw.write("Saltar\n");

                    //Escribir hexagono de destino
                    bw.write(dm.getHexagono_destino().toString() + "\n");

                    //Escribir el lado
                    bw.write(dm.getLado_destino() + "\n");

                    break;
                case Andar:
                    bw.write("Andar\n");

                    //Escribir hexagono de destino
                    bw.write(dm.getHexagono_destino().toString() + "\n");

                    //Escribir el lado
                    bw.write(dm.getLado_destino() + "\n");

                    //Escribir si va a usar MASC
                    bw.write(dm.isUsaMASC() ? "True\n" : "False\n");

                    //Número de pasos
                    int npasos = dm.getNumero_de_pasos();
                    bw.write(npasos + "\n");

                    //Imprimir los pasos
                    for (int i = 0; i < npasos; i++) {
                        bw.write(dm.getPasos()[i].toString() + "\n");
                    }
                    break;
                case Correr:
                    bw.write("Correr\n");

                    //Escribir hexagono de destino
                    bw.write(dm.getHexagono_destino().toString() + "\n");

                    //Escribir el lado
                    bw.write(dm.getLado_destino() + "\n");

                    //Escribir si va a usar MASC
                    bw.write(dm.isUsaMASC() ? "True" : "False");

                    //Número de pasos
                    int npasos2 = dm.getNumero_de_pasos();
                    bw.write(npasos2 + "\n");

                    //Imprimir los pasos
                    for (int i = 0; i < npasos2; i++) {
                        bw.write(dm.getPasos()[i].toString() + "\n");
                    }
                    break;
            }

            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private static void escribeReaccion(int jugador, Reaccion reaccion) {
        //Abrir archivo
        File archivo = new File("accionJ" + jugador + ".sbt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            switch (reaccion.getTipo()) {

                case Igual:
                    bw.write("Igual\n");
                    break;
                case Derecha:
                    bw.write("Derecha\n");
                    break;
                case Izquierda:
                    bw.write("Izquierda\n");
                    break;
            }

            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void escribeAtaqueArmas(int jugador, AtaqueArmas aa) {
        //Abrir archivo
        File archivo = new File("accionJ" + jugador + ".sbt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            if(aa.cogerGarrote){
                //Si coge el garrote no puede hacer nada más ese turno
                bw.write("True\n");
            }else{
                bw.write("False\n");
                
                //Imprimimos el hexágono objetivo si lo hay
                if(aa.objetivo_primario != null){
                bw.write(aa.objetivo_primario.toString() + "\n");
                }else{
                    //Si no hay objetivo ponemos 0000
                    bw.write("0000\n");
                }
                
                //Imprimir número de armas que vamos a disparar
                int narmas = aa.disparos.size();
                bw.write(narmas + "\n");
                
                //Para cada arma imprimir sus datos
                for(DisparoArma disparo : aa.disparos){
                    //Localización del arma
                    bw.write(disparo.localizacion_arma.name() + "\n");
                    
                    //Slot del arma
                    bw.write(disparo.slot_arma + "\n");
                    
                    //Doble cadencia?
                    String s_cadencia = disparo.dobleCadencia?"True":"False";
                    bw.write(s_cadencia + "\n");
                    
                    //Localización de la munición
                    //Si el arma es de energía entonces debe ser -1
                    if(disparo.localizacion_municion!=null){
                        bw.write(disparo.localizacion_municion.name()+"\n");
                        //Slot de munición
                        bw.write(disparo.slot_municion + "\n");
                    }else{
                        bw.write(-1);
                        bw.write("\n");
                        bw.write(-1);
                        bw.write("\n");
                    }
                    
                    //Hexágono objetivo
                    if(disparo.objetivo != null){
                        bw.write(disparo.objetivo.toString() + "\n");
                    }else{
                        //Si no hay objetivo
                        bw.write("0000\n");
                    }
                    
                    //Disparamos al mech o al hexágono
                    //Igual hexágono debe ir escrito con acento... habrá que comprobarlo
                    bw.write(disparo.tipoObjetivo.name() + "\n");
                }
                
                
            }

            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void escribirAtaqueFisico(int jugador, AtaqueFisico accion) {
        //Abrir archivo
        File archivo = new File("accionJ" + jugador + ".sbt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            //Escribir el número de ataques
            bw.write(accion.ataques.size() + "\n");
            
            //Para cada disparo escribir cosas
            for(AtaqueArmaFisica ataque : accion.ataques){
                //bw.write(ataque.toString()); TODO podríamos hacerlos todos así... ya veremos
                
                //Localización
                bw.write(ataque.localizacion.name() + "\n");
                
                //Slot
                bw.write(ataque.slot + "\n");
                
                //Hexágono objetivo
                bw.write(ataque.objetivo.toString() + "\n");
                
                //Tipo de objetivo
                bw.write(ataque.tipoObjetivo.name() + "\n");
                
            }
            

            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void escribirFinalDeTurno(int jugador, FinalTurno accion) {
        //Abrir archivo
        File archivo = new File("accionJ" + jugador + ".sbt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            bw.write(accion.n_radiadores_a_apagar + "\n");
            
            bw.write(accion.n_radiadores_a_encender + "\n");
            
            bw.write(accion.soltarGarrote?"True\n":"False\n");
            
            bw.write(accion.municiones_a_expulsar.size() + "\n");

            for(ExpulsarMunicion expulsion : accion.municiones_a_expulsar){
                
                bw.write(expulsion.localizacion.name() + "\n");
                
                bw.write(expulsion.slot + "\n");
                
            }
            
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
