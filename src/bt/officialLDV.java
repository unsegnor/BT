/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * //Interfaz para utilizar el LDV.exe que nos dan
 * @author Víctor
 */
public class officialLDV {
    
    public static ResultadoLDV ejecutar(String ruta_mapa, Phexagono origen, int nivel_origen, Phexagono destino, int nivel_destino){
        ResultadoLDV r = new ResultadoLDV();
        try {
            //Formateador
            NumberFormat formatter = new DecimalFormat("00");
            String columna_origen = formatter.format(origen.getColumna());
            String fila_origen = formatter.format(origen.getFila());
            String columna_destino = formatter.format(destino.getColumna());
            String fila_destino = formatter.format(destino.getFila());
            
            //Ejecutar el programa
            //String command = "cmd /c \"C:\\Users\\Víctor\\Documents\\NetBeansProjects\\BT\\LDVyC.exe\" " + ruta_mapa + " " +columna_origen+fila_origen+" "+nivel_origen+" "+columna_destino+fila_destino+" "+nivel_destino;
            String command = "\"C:\\Users\\Victor\\Documents\\NetBeansProjects\\BT\\LDVyC.exe\" " + ruta_mapa + " " +columna_origen+fila_origen+" "+nivel_origen+" "+columna_destino+fila_destino+" "+nivel_destino;
            //System.out.println("ejecutando: "+command);
            Process p = Runtime.getRuntime().exec(command);
            
            //Damos tiempo a que se escriba el fichero
            Thread.currentThread().sleep(1000);
            
            //Leer el resultado
            File f_respuesta = new File("LDV.sbt");
            FileReader fr = new FileReader(f_respuesta);
            BufferedReader br = new BufferedReader(fr);
            
            String s_camino = br.readLine();
            String s_LDV = br.readLine(); //Leemos si hay línea de visión
            String s_CP = br.readLine(); //Leemos si hay cobertura parcial
            
            br.close();
            fr.close();
            
            //Transformar el resultado
            boolean ldv = Boolean.parseBoolean(s_LDV);
            boolean cp = Boolean.parseBoolean(s_CP);
            //Si hay camino
            if (!s_camino.trim().equals("")){
                //Dividir el camino
                String[] casillas = s_camino.trim().split(" ");
            
                //Para cada casilla componer la ruta
                Phexagono[] ruta = new Phexagono[casillas.length];
                
                for(int i=0; i<casillas.length; i++){
                    //Convertir a Phexagono
                    int columna = Integer.parseInt(casillas[i].substring(0, 2));
                    int fila = Integer.parseInt(casillas[i].substring(2));
                    ruta[i] = new Phexagono(columna, fila);
                }
          
          //Rellenar el resultado
                r.hexagonos = ruta;
            }else{
                r.hexagonos = new Phexagono[0];
            }
                r.LDV = ldv;
                r.CPinversa = cp;
        } catch (InterruptedException ex) {
            Logger.getLogger(officialLDV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(officialLDV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }
    
}
