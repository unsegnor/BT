/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Neuro;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Víctor
 */
public class NeuronTest {
    
    public NeuronTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        
        System.out.println("Probando neurona");
        
        
        
        for(double rc = 0.00001; rc < 1; rc+=0.00001){
        
        Neuron n = new Neuron(0);
        
        n.RC = rc;
        
        //Ejecutar sinapsis y comprobar carga.
        n.synapsis(n,10);
        System.out.println("RC = " + rc + " " +n);
        System.out.println("---------------------------");
        
        double t = 10;
        while(n.carga != 0){
        n.actualizar(t);
        System.out.println(t+" "+n);
        t+= 0.001d;
        }
        
        System.out.println();
        
        }
        //for(int i=0; i<100; i++) System.out.println(new Neuron(System.currentTimeMillis()));
        
    }
}
