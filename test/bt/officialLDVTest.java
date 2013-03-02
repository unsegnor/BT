/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Víctor
 */
public class officialLDVTest {

    Random r;
    
    public officialLDVTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        r = new Random(System.currentTimeMillis());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of ejecutar method, of class officialLDV.
     */
    @Test
    public void testEjecutar() {
        System.out.println("ejecutar");
        String ruta_mapa = "C:\\Users\\Víctor\\Documents\\NetBeansProjects\\BT\\testLDV.tst";
        
        int c1 = 0;
        int f1 = 0;
        int c2 = 0;
        int f2 = 0;
        
        for (int j = 0; f1 < 13 ; j++) {
            c1 = 3;
            f1 = 1+j;
            c2 = 4;
            f2 = 1+j;
            
            
            
            Phexagono origen = new Phexagono(c1, f1);
            int nivel_origen = 0;
            Phexagono destino = new Phexagono(c2, f2);
            int nivel_destino = 0;
            ResultadoLDV expResult = null;
            ResultadoLDV result = officialLDV.ejecutar(ruta_mapa, origen, nivel_origen, destino, nivel_destino);
            System.out.println("("+c1+","+f1+","+nivel_origen+") -> ("+c2+","+f2+","+nivel_destino+")");
            System.out.println("camino: ");
            for (int i = 0; i < result.hexagonos.length; i++) {
                System.out.println(result.hexagonos[i]);
            }
            System.out.println("línea de visión: " + result.LDV);
            System.out.println("cobertura parcial: " + result.CPdirecta);
        }
    }
    }
