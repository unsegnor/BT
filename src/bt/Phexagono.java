/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 * //Almacena una fila y una columna que representa un hexágono del mapa
 * @author Víctor
 */
public class Phexagono {
    
    //Fila
    private int fila;
    
    //Columna
    private int columna;

    public Phexagono(int columna, int fila){
        this.columna = columna;
        this.fila = fila;
    }
    
    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        
        
        sb.append(columna/10).append(columna%10).append(fila/10).append(fila%10);
        //sb.append("(").append(this.getColumna()).append(",").append(this.getFila()).append(")");
    
        return sb.toString();
    }
    
}
