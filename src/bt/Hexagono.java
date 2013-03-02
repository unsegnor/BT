/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.ObjetoTipo;
import bt.Reglas.TerrenoTipo;

/**
 *
 * @author Víctor
 */
public class Hexagono extends DataHexagono {
    
    private TerrenoTipo tipoterreno = TerrenoTipo.Abierto;
    private ObjetoTipo tipoobjeto = ObjetoTipo.Nada;
    private int profundidad = 0;
    
    
    public Hexagono(DataHexagono dh){
        init(dh);
    }
    
    
    private void init(DataHexagono dh){
        //Copiar los datos
        this.copiaDura(dh);
        
        //Traducir el tipo de terreno a ago legible mientras programamos
        switch(dh.getTipo_de_terreno()){
            case 0:
                setTipoterreno(TerrenoTipo.Abierto);
            break;
            case 1:
                setTipoterreno(TerrenoTipo.Pavimentado);
            break;
            case 2:
                setTipoterreno(TerrenoTipo.Agua);
            break;
            case 3:
                setTipoterreno(TerrenoTipo.Pantanoso);
            break;
        }
        
        //Traducir el tipo de objeto
        switch(dh.getObjeto()){
            case 0:
                setTipoobjeto(ObjetoTipo.Escombros);
            break;
            case 1:
                setTipoobjeto(ObjetoTipo.BosqueLigero);
            break;
            case 2:
                setTipoobjeto(ObjetoTipo.BosqueDenso);
            break;
            case 3:
                setTipoobjeto(ObjetoTipo.EdificioLigero);
            break;
            case 4:
                setTipoobjeto(ObjetoTipo.EdificioMedio);
            break;
            case 5:
                setTipoobjeto(ObjetoTipo.EdificioPesado);
            break;
            case 6:
                setTipoobjeto(ObjetoTipo.EdificioReforzado);
            break;
            case 7:
                setTipoobjeto(ObjetoTipo.Bunker);
            break;
            case 255:
                setTipoobjeto(ObjetoTipo.Nada);
            break;
        }
        //Calcular profundidad
        //Si es un hexágono de agua y su nivel es menor que cero
        if(this.getTipoterreno() == TerrenoTipo.Agua && this.getNivel() < 0){
            //entonces la profundidad es el valor absoluto de su nivel
            this.setProfundidad(Math.abs(this.getNivel()));
        }else{
            //De otro modo la profundidad es cero
            this.setProfundidad(0);
        }
    }

    /**
     * @return the tipoterreno
     */
    public TerrenoTipo getTipoterreno() {
        return tipoterreno;
    }

    /**
     * @param tipoterreno the tipoterreno to set
     */
    public void setTipoterreno(TerrenoTipo tipoterreno) {
        this.tipoterreno = tipoterreno;
    }

    /**
     * @return the tipoobjeto
     */
    public ObjetoTipo getTipoobjeto() {
        return tipoobjeto;
    }

    /**
     * @param tipoobjeto the tipoobjeto to set
     */
    public void setTipoobjeto(ObjetoTipo tipoobjeto) {
        this.tipoobjeto = tipoobjeto;
    }

    /**
     * @return the profundidad
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     * @param profundidad the profundidad to set
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }
}
