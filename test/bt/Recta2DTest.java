/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Víctor
 */
public class Recta2DTest {
    
    public Recta2DTest() {
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

    /**
     * Test of Y method, of class Recta2D.
     */
    @Test
    public void testY() {
        System.out.println("Y");
        float x = 0.58F;
        Recta2D instance = new Recta2D(new Punto(1,3,1), new Punto(2,5,7));
        double expResult = instance.Y2(x);
        double result = instance.Y(x);
        assertEquals(expResult, result, 0.0);
        System.out.println("Y1: " + result + " Y2: " + expResult);
    }

    /**
     * Test of Y2 method, of class Recta2D.
     */
    
    public void testY2() {
        System.out.println("Y2");
        double x = 0.0;
        Recta2D instance = new Recta2D();
        double expResult = 0.0;
        double result = instance.Y2(x);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of X method, of class Recta2D.
     */
    @Test
    public void testX() {
        System.out.println("X");
        double y = 2.16;
        Recta2D instance = new Recta2D(new Punto(1,3,1), new Punto(2,5,7));
        double expResult = instance.X2(y);
        double result = instance.X(y);
        assertEquals(expResult, result, 0.0001);
        System.out.println("X1: " + result + " X2: " + expResult);
    }

    /**
     * Test of X2 method, of class Recta2D.
     */
    
    public void testX2() {
        System.out.println("X2");
        double y = 0.0;
        Recta2D instance = new Recta2D();
        double expResult = 0.0F;
        double result = instance.X2(y);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
