/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.Comparator;

/**
 *
 * @author Víctor
 */
class ComparaPosicionesSinArmas implements Comparator<PosEval> {

    @Override
    public int compare(PosEval o1, PosEval o2) {
        int respuesta = 0;
        //No hay armas de fuego así que no tendremos en cuenta la LDV ni la cobertura...etc
        
        //Mejor cuanto más cerca
        respuesta =  o1.distancia_al_enemigo - o2.distancia_al_enemigo;
        
        //Mejor estar un nivel por encima
        
        //Al mismo nivel
        if (respuesta == 0){
            respuesta = Math.abs(o1.niveles_por_ecima_del_enemigo) - Math.abs(o2.niveles_por_ecima_del_enemigo);
        }
        
        //Si están igual de cerca, mejor encarar al enemigo
        if(respuesta == 0){
            respuesta = o1.giros_para_ecarar_enemigo - o2.giros_para_ecarar_enemigo;
        }
        
        return respuesta;
    }
    
}
