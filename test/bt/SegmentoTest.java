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
public class SegmentoTest {
    
    public SegmentoTest() {
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
     * Test of corte method, of class Segmento.
     */
    @Test
    public void testCorte_Recta() {
        System.out.println("corte segmento-recta");
        System.out.print("Recta y segmento que cortan...");
        Recta r = new Recta(new Punto(4,4,0), new Punto(8,8,0)); //Bisectriz
        Segmento instance = new Segmento(new Punto(1,3,0), new Punto(3,1,0)); //Cruzan en 2,2
        Punto expResult = null;
        Punto result = instance.corte(r);
        assertEquals(2, result.x,0.0);
        assertEquals(2, result.y,0.0);
        System.out.println("OK");
        //Segmento que no corta
        System.out.print("segmento que no corta...");
        instance = new Segmento(new Punto(4,0,0), new Punto(3,1,0));
        result = instance.corte(r);
        assertEquals(null,result);
        System.out.println("OK");
        //Segmento que corta sólo en un punto
        System.out.print("segmento que coincide en un punto...");
        instance = new Segmento(new Punto(4,0,0), new Punto(3,3,0));
        result = instance.corte(r);
        assertEquals(3, result.x,0.0);
        assertEquals(3, result.y,0.0);
        System.out.println("OK");
        //Segmento que es parte de la recta
        System.out.print("Segmento que es parte de la recta...");
        instance = new Segmento(new Punto(4,4,0), new Punto(3,3,0));
        result = instance.corte(r);
        assertEquals(null,result);
        System.out.println("OK");
    }

    /**
     * Test of corte method, of class Segmento.
     */
    @Test
    public void testCorte_Segmento() {
        System.out.println("corte segmento-segmento");
        //Segmentos que no cortan
        System.out.print("Segmentos que no cortan...");
        Segmento s = new Segmento(new Punto(4,4,0), new Punto(8,8,0)); //Bisectriz
        Segmento instance = new Segmento(new Punto(1,3,0), new Punto(3,1,0)); //Cruzan en 2,2
        Punto expResult = null;
        Punto result = instance.corte(s);
        assertEquals(null, result);
        System.out.println("OK");
        //Que Cortan
        System.out.print("Segmentos que cortan...");
        s = new Segmento(new Punto(1,1,0), new Punto(8,8,0)); //Bisectriz
        instance = new Segmento(new Punto(1,3,0), new Punto(3,1,0)); //Cruzan en 2,2
        result = instance.corte(s);
        assertEquals(2, result.x,0.0);
        assertEquals(2, result.y,0.0);
        System.out.println("OK");
        //Segmento que no corta
        System.out.print("segmento que no corta...");
        instance = new Segmento(new Punto(4,0,0), new Punto(3,1,0));
        result = instance.corte(s);
        assertEquals(null,result);
        System.out.println("OK");
        //Segmento que corta sólo en un punto
        System.out.print("segmento que coincide en un punto...");
        instance = new Segmento(new Punto(4,0,0), new Punto(3,3,0));
        result = instance.corte(s);
        assertEquals(3, result.x,0.0);
        assertEquals(3, result.y,0.0);
        System.out.println("OK");
        //Segmento que es parte de la recta
        System.out.print("Segmento que es parte de otro segmento...");
        instance = new Segmento(new Punto(4,4,0), new Punto(3,3,0));
        result = instance.corte(s);
        assertEquals(null,result);
        System.out.println("OK");
        //Segmentos horizontal y vertical que cortan
        System.out.print("Segmentos que cortan en cero vertical y horizontal...");
        s = new Segmento(new Punto(0,3,0), new Punto(0,-8,0));
        instance = new Segmento(new Punto(134,0,0), new Punto(-143,0,0));
        result = instance.corte(s);
        assertEquals(0, result.x,0.0);
        assertEquals(0, result.y,0.0);
        System.out.println("OK");
    }
}
