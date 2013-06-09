/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import bt.Reglas.Fase;
import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
public class AtaqueFisico extends Accion{

    ArrayList<AtaqueArmaFisica> ataques = new ArrayList<AtaqueArmaFisica>();
    
    
    
    @Override
    Reglas.Fase getFase() {
        return Fase.AtaqueFisico;
    }
    
}
