/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalLearner;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Víctor
 */
public class ULTest {
    
    public ULTest() {
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
    public void testSomeMethod() throws InterruptedException {
        System.out.println("Universal Learner");
        System.out.println("");
        System.out.println("Creando el UL");
        UL ul = new UL();
        
        System.out.println("Introducir datos");
        
        Integer[] e = new Integer[3];
        
        e[0] = 0;
        e[1] = 0;
        e[2] = 0;
        
        ul.muestrear(e, 10);
        
        Thread.currentThread().sleep(5000);
        e[0] = 1;
        Thread.currentThread().sleep(5000);
        e[1] = 1;
        Thread.currentThread().sleep(5000);
        e[2] = 1;
        Thread.currentThread().sleep(5000);
        e[0] = 0;
        Thread.currentThread().sleep(5000);
        //Cerrar
        
        
    }
}
