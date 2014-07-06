/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 *
 * @author Víctor
 */
class PosEval implements I_Eval {

    //Clase con los datos necesarios para evaluar una posición respecto a otra
    
    //Nos interesa saber
    
    int distancia_al_enemigo;
    boolean enemigo_visible=false;
    boolean ofrece_cobertura=false;
    boolean visible_por_enemigo=false;
    boolean enemigo_cubierto=false;
    int giros_para_ecarar_enemigo=0; //Este entero indica cómo de encarado al enemigo estoy concretamente cuántas caras debo girar para encararme a él
    int giros_enemigo_para_encararme=0; //Este entero indica los giros necesarios del enemigo para encararme
    int niveles_por_ecima_del_enemigo=0;
    
    int calor_generado = 0;
    
    int[] flancos_seguros = new int[6];
    Mech enemigo;
    Mech actual;
    Posicion posicion;
    
    
    
    @Override
    public int compareTo(I_Eval valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("Calor generado: ").append(this.calor_generado).append("\n");
        sb.append("Distancia al enemigo: ").append(this.distancia_al_enemigo).append("\n");
        sb.append("Enemigo cubierto: ").append(this.enemigo_cubierto).append("\n");
        sb.append("Enemigo visible: ").append(this.enemigo_visible).append("\n");
        sb.append("Giros del enemigo para encarame: ").append(this.giros_enemigo_para_encararme).append("\n");
        sb.append("Giros para encarar al enemigo: ").append(this.giros_para_ecarar_enemigo).append("\n");
        sb.append("Niveles por encima del enemigo: ").append(this.niveles_por_ecima_del_enemigo).append("\n");
        sb.append("Ofrece cobertura: ").append(this.ofrece_cobertura).append("\n");
        sb.append("Visible por enemigo: ").append(this.visible_por_enemigo).append("\n");
        
        
        
        return sb.toString();
        
    }
    
}
