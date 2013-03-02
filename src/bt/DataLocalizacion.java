/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Víctor
 */
class DataLocalizacion {
    /*
     * Para cada localización (0=BI,1=TI,2=PI,3=PD,4=TD,5=BD,6=TC,7=CAB) Entero
     * Número de slots ocupados
     */ private DataSlotOcupado[] slotsOcupados;/*
     *
     */

    public void leer(BufferedReader br) throws IOException{
        //Leer el tamaño del vector
        int tam = Integer.parseInt(br.readLine().trim());
        
        //Inicializar el vector
        slotsOcupados = new DataSlotOcupado[tam];
        
        //Leer tantos slots como se indican
        for(int i=0; i<tam; i++){
            DataSlotOcupado nuevo = new DataSlotOcupado();
            nuevo.leer(br);
            slotsOcupados[i] = nuevo;
        }
    }

    /**
     * @return the slotsOcupados
     */
    public DataSlotOcupado[] getSlotsOcupados() {
        return slotsOcupados;
    }

    /**
     * @param slotsOcupados the slotsOcupados to set
     */
    public void setSlotsOcupados(DataSlotOcupado[] slotsOcupados) {
        this.slotsOcupados = slotsOcupados;
    }
}
