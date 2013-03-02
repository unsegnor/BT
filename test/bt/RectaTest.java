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
public class RectaTest {
    
    public RectaTest() {
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
     * Test of toString method, of class Recta.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Recta instance = new Recta(new Punto(1,5,0), new Punto(4,5,0));
        String expResult = "";
        String result = instance.toString();
        
        System.out.println(result);
    }

    /**
     * Test of y method, of class Recta.
     */
    @Test
    public void testY() {
        System.out.println("y");
        double x = 0.0;
        Recta instance = new Recta(new Punto(Math.random(),Math.random(),0), new Punto(Math.random(),Math.random(),0));
        double expResult = instance.cy;
        double result = instance.y(x);
        if(!Double.isNaN(result)){
        assertEquals(expResult, result, 0.0);
        }
    }

    /**
     * Test of igual method, of class Recta.
     */
    @Test
    public void testIgual() {
        System.out.println("igual");
        Recta otra = new Recta(new Punto(1,1,0), new Punto(2,2,0));
        Recta instance = new Recta(new Punto(1,1,0), new Punto(200,200,0));;
        boolean expResult = true;
        boolean result = instance.igual(otra);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of paralela method, of class Recta.
     */
    @Test
    public void testParalela() {
        System.out.println("paralela");
        Recta otra = new Recta(new Punto(1,6,0), new Punto(2,12,0));
        Recta instance = new Recta(new Punto(1,12,0), new Punto(2,18,0));
        boolean expResult = true;
        boolean result = instance.paralela(otra);
        assertEquals(expResult, result);
    }

    /**
     * Test of corte method, of class Recta.
     */
    @Test
    public void testCorte() {
        System.out.println("corte");
        Recta otra = new Recta(new Punto(345,536,0), new Punto(1342,53635,0));
        Recta instance = new Recta(new Punto(25442,2452,0), new Punto(1342,53635,0));
        Punto result = instance.corte(otra);
        assertEquals(1342, result.x, 0.0);
        assertEquals(53635, result.y, 0.0);
        
        //Corte de dos aleatorias
        otra = new Recta(new Punto(-Math.random(),Math.random(),0), new Punto(Math.random(),-Math.random(),0));
        instance = new Recta(new Punto(-Math.random(),-Math.random(),0), new Punto(Math.random(),-Math.random(),0));
        result = instance.corte(otra);
        System.out.println(result);
        
        //Corte de perpendiculares horizontal y vertical
        System.out.println("Corte de perpendiculares horizontal y vertical");
        double x1 = Math.random();
        double y1 = Math.random();
        otra = new Recta(new Punto(x1,Math.random(),0), new Punto(x1,-Math.random(),0));
        instance = new Recta(new Punto(-Math.random(),y1,0), new Punto(Math.random(),y1,0));
        result = instance.corte(otra);
        System.out.println(result);
        //assertEquals(x1, result.x, 0.0);
        //assertEquals(y1, result.y, 0.0);
        
        //Corte de horizontal y otra
        System.out.println("Corte de horizontal y otra");
        x1 = Math.random();
        y1 = Math.random();
        otra = new Recta(new Punto(-Math.random(),Math.random(),0), new Punto(Math.random(),-Math.random(),0));
        instance = new Recta(new Punto(-Math.random(),y1,0), new Punto(Math.random(),y1,0));
        result = instance.corte(otra);
        System.out.println(result);
        //assertEquals(x1,result.x, 0.0);
        assertEquals(y1, result.y, 0.0);
        
        //Corte de vertical y otra
        System.out.println("Corte de vertical y otra");
        x1 = Math.random();
        y1 = Math.random();
        otra = new Recta(new Punto(x1,Math.random(),0), new Punto(x1,-Math.random(),0));
        instance = new Recta(new Punto(-Math.random(),-Math.random(),0), new Punto(Math.random(),-Math.random(),0));
        result = instance.corte(otra);
        System.out.println(result);
        //assertEquals(x1, result.x, 0.0);
        //assertEquals(y1, result.y, 0.0);
    }

    /**
     * Test of x method, of class Recta.
     */
    @Test
    public void testX() {
        System.out.println("x");
        double y = 0.0;
        Recta instance = new Recta(new Punto(Math.random(),Math.random(),0), new Punto(Math.random(),Math.random(),0));
        double expResult = instance.cx;
        double result = instance.x(y);
        assertEquals(expResult, result, 0.0);
        double laY = Math.random();
        instance = new Recta(new Punto(Math.random(),laY,0), new Punto(Math.random(),laY,0));
        result = instance.x(y);
        System.out.println(result);
    }
}
