/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
public class FinalTurno extends Accion{

    int n_radiadores_a_apagar;
    int n_radiadores_a_encender;
    boolean soltarGarrote;
    ArrayList<ExpulsarMunicion> municiones_a_expulsar = new ArrayList<ExpulsarMunicion>();
    
    
    
    
    
    
    @Override
    Reglas.Fase getFase() {
        return Reglas.Fase.FinalTurno;
    }
    
}
