/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.*;
import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
public class AtaqueArmas extends Accion {

    boolean cogerGarrote;
    Phexagono objetivo_primario;
    ArrayList<DisparoArma> disparos = new ArrayList<DisparoArma>();
    
    
    
    
    
    
    @Override
    Fase getFase() {
        return Fase.AtaqueArmas;
    }
    
}
