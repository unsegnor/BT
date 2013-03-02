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
public class PNTest {
    
    public PNTest() {
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
        System.out.println("Probando red de perceptrones");
        System.out.println("");
        System.out.println("Crear una red de perceptrones");
        int n_entradas = 2;
        int[] capas_intermedias = new int[5];
        capas_intermedias[0] = 2;
        capas_intermedias[1] = 3;
        capas_intermedias[2] = 4;
        capas_intermedias[3] = 3;
        capas_intermedias[4] = 2;
        int n_salidas = 1;
        PN red = new PN(n_entradas, capas_intermedias, n_salidas);
        System.out.println("La red:");
        System.out.println(red);
        int[] entradas = new int[n_entradas];
        int[] salidas = new int[n_salidas];
        red.aprender(entradas,salidas);
        int[] resultado = red.calcular(entradas); 
    }
}
