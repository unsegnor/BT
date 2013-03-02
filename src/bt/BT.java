/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Víctor
 */
public class BT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Receptor receptor = new Receptor();
        
        
        System.out.println("Iniciando inteligencia artificial...");
        System.out.print("Iniciando receptor...");
        new Thread(receptor).start();
        System.out.println("OK");
        //new Thread(new MainThread()).start();
    }
}
