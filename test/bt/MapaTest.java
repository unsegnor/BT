/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Víctor
 */
public class MapaTest {

    Mapa map;
    Random r;
    String ruta_mapa = "C:\\Users\\Víctor\\Documents\\NetBeansProjects\\BT\\mapaJ0.tst";

    public MapaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        File archivo = new File("mapaJ0.tst");
        DataMapa datosmapa = Cargador.leerMapa(0, archivo);
        map = new Mapa(datosmapa);
        r = new Random(System.currentTimeMillis());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of casilla method, of class Mapa.
     */
    public void testCasilla_int_int() {
        System.out.println("casilla");
        int columna = 0;
        int fila = 0;
        Mapa instance = null;
        DataHexagono expResult = null;
        DataHexagono result = instance.casilla(columna, fila);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of casilla method, of class Mapa.
     */
    public void testCasilla_int() {
        System.out.println("casilla");
        int columnaFila = 0;
        Mapa instance = null;
        DataHexagono expResult = null;
        DataHexagono result = instance.casilla(columnaFila);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of casilla method, of class Mapa.
     */
    public void testCasilla_Phexagono() {
        System.out.println("casilla");
        Phexagono posicion = null;
        Mapa instance = null;
        DataHexagono expResult = null;
        DataHexagono result = instance.casilla(posicion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adyacentes method, of class Mapa.
     */
    @Test
    public void testAdyacentes() {
        System.out.println("adyacentes");
        //Cargar mapa
        int nJugador = 0;
        File archivo = new File("mapaJ0.tst");
        DataMapa datosmapa = Cargador.leerMapa(nJugador, archivo);

        int columna = 3;
        int fila = 3;
        Mapa instance = new Mapa(datosmapa);
        DataHexagono[] result = instance.adyacentes(columna, fila);
        assertEquals(instance.casilla(3, 2), result[0]);
        assertEquals(instance.casilla(4, 2), result[1]);
        assertEquals(instance.casilla(4, 3), result[2]);
        assertEquals(instance.casilla(3, 4), result[3]);
        assertEquals(instance.casilla(2, 3), result[4]);
        assertEquals(instance.casilla(2, 2), result[5]);

    }

    /**
     * Test of cara method, of class Mapa.
     */
    public void testCara() {
        System.out.println("cara");
        int columna = 11;
        int fila = 6;
        int cara = 3;
        Mapa instance = map;
        Phexagono result = instance.cara(columna, fila, cara);
        assertEquals(12, result.getColumna());
        assertEquals(6, result.getFila());
    }

    /**
     * Test of cara method, of class Mapa.
     */
    @Test
    public void testCara_Phexagono_int() {
        System.out.println("cara");
        Phexagono p = new Phexagono(12, 5);
        int cara = 3;
        Mapa instance = map;
        Phexagono expResult = null;
        Phexagono result = instance.cara(p, cara);
        assertEquals(13, result.getColumna());
        assertEquals(6, result.getFila());
    }

    /**
     * Test of cara method, of class Mapa.
     */
    public void testCara_3args() {
        System.out.println("cara");
        int columna = 0;
        int fila = 0;
        int cara = 0;
        Mapa instance = null;
        Phexagono expResult = null;
        Phexagono result = instance.cara(columna, fila, cara);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcular method, of class Mapa.
     */
    @Test
    public void testCalcular() {
        System.out.println("calcular");
        Mapa mapa = map;
        Phexagono origen = new Phexagono(7, 4);
        int suma_origen = 0;
        Phexagono destino = new Phexagono(8, 4);
        int suma_destino = 0;
        Mapa instance = map;
        ResultadoLDV expResult = null;
        ResultadoLDV result = instance.calcular(mapa, origen, suma_origen, destino, suma_destino);
        assertEquals(expResult, result);
    }

    /**
     * Test of puntoEspacial method, of class Mapa.
     */
    @Test
    public void testPuntoEspacial_Phexagono() {
        System.out.println("puntoEspacial");
        Phexagono posicion = new Phexagono(9, 6);
        Mapa instance = map;
        Punto expResult = null;
        Punto result = instance.puntoEspacial(posicion);
        assertEquals(instance.datos.getAlto() * 2 * map.apotema - 165, result.y, 0.0);
        assertEquals(13 * map.lado, result.x, 0.0);
    }

    /**
     * Test of puntoEspacial method, of class Mapa.
     */
    public void testPuntoEspacial_int_int() {
        System.out.println("puntoEspacial");
        int columna = 0;
        int fila = 0;
        Mapa instance = null;
        Punto expResult = null;
        Punto result = instance.puntoEspacial(columna, fila);
        assertEquals(expResult, result);
    }

    /**
     * Test of caraEspacio method, of class Mapa.
     */
    @Test
    public void testCaraEspacio_3args() {
        System.out.println("caraEspacio");
        int columna = 4;
        int fila = 3;
        int cara = 5;
        Mapa instance = map;
        Segmento expResult = null;
        Segmento result = instance.caraEspacio(columna, fila, cara);
        assertEquals(4.5 * map.lado, result.b.x, 0.0);
        assertEquals(map.datos.getAlto() * 2 * map.apotema - 2 * 3 * map.apotema, result.b.y, 0.0);
        assertEquals(5 * map.lado, result.a.x, 0.0);
        assertEquals(map.datos.getAlto() * 2 * map.apotema - 2 * 3 * map.apotema - 15, result.a.y, 0.0);
    }

    /**
     * Test of caraEspacio method, of class Mapa.
     */
    public void testCaraEspacio_int_int() {
        System.out.println("caraEspacio");
        int columna = 0;
        int fila = 0;
        Mapa instance = null;
        Segmento[] expResult = null;
        Segmento[] result = instance.caraEspacio(columna, fila);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of caraEspacio method, of class Mapa.
     */
    public void testCaraEspacio_Phexagono() {
        System.out.println("caraEspacio");
        Phexagono p = null;
        Mapa instance = null;
        Segmento[] expResult = null;
        Segmento[] result = instance.caraEspacio(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intermedios method, of class Mapa.
     */
    @Test
    public void testIntermedios() {
        System.out.println("intermedios");
        for (int j = 0; j < 0; j++) {
            int c1 = 1 + r.nextInt(map.datos.getAncho());
            int c2 = 1 + r.nextInt(map.datos.getAncho());
            int f1 = 1 + r.nextInt(map.datos.getAlto());
            int f2 = 1 + r.nextInt(map.datos.getAlto());
            System.out.println(j + " entre (" + c1 + "," + f1 + ") y (" + c2 + "," + f2 + ")");
            Phexagono origen = new Phexagono(c1, f1);
            Phexagono destino = new Phexagono(c2, f2);
            Mapa instance = map;
            Phexagono[] expResult = null;
            ArrayList<ArrayList<Phexagono> > result = instance.intermedios(origen, destino);
            //assertEquals(expResult, result);
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

    }

    @Test
    public void testIntermediosDeLaMuerte() {
        System.out.println("intermedios de la muerte");
        int diferencias = 0;
        int iteraciones = 1;
        for (int j = 0; j < iteraciones; j++) {
            int c1 = 1 + r.nextInt(map.datos.getAncho());
            int c2 = 1 + r.nextInt(map.datos.getAncho());
            int f1 = 1 + r.nextInt(map.datos.getAlto());
            int f2 = 1 + r.nextInt(map.datos.getAlto());
            //System.out.println(j+" entre ("+c1+","+f1+") y ("+c2+","+f2+")");
            Phexagono origen = new Phexagono(c1, f1);
            Phexagono destino = new Phexagono(c2, f2);
            Mapa instance = map;
            //Calcular en función propia (tiene dos hexágonos más, inicio y fin)
            ArrayList<ArrayList<Phexagono>> result = instance.intermedios(origen, destino);
            //Calcular en función ajena
            Phexagono[] expResul = (officialLDV.ejecutar(ruta_mapa, origen, 0, destino, 0)).hexagonos;
            //Construir ruta para comparar
            Phexagono[] igual = new Phexagono[expResul.length + 2];
            //Añadier origen
            igual[0] = origen;
            for (int k = 1; k < igual.length - 1; k++) {
                igual[k] = expResul[k - 1];
            }
            //Añadir destino
            igual[igual.length - 1] = destino;

            //Comparar ambos
            boolean diferentes = false;
            if (result.size() != result.size()) {
                diferentes = true;
            }
            for (int i = 0; i < result.size() && i < result.size() && !diferentes; i++) {
                boolean unocoincide = false;
                //Para cada alternativa de la posición
                for (int p = 0; p < result.get(i).size(); p++) {
                    unocoincide = false;
                    if (igual[i].getColumna() == result.get(i).get(p).getColumna() && igual[i].getFila() == result.get(i).get(p).getFila()) {
                        //Si coincide con una alternativa ya nos vale
                        unocoincide = true;
                    }
                }
                if (!unocoincide) {
                    //Si no coincide ninguno entonces son diferentes
                    diferentes = true;
                }
            }
            //Si son diferentes los mostramos
            if (!diferentes) {
                diferencias++;
                int maxLength = Math.max(igual.length, result.size());
                System.out.println(j + ": diferencias entre (" + c1 + "," + f1 + ") y (" + c2 + "," + f2 + ")");
                for (int i = 0; i < maxLength; i++) {
                    System.out.print((i < igual.length ? igual[i] : "") + " -> ");
                    if (i < result.size()) {
                        for (int p = 0; p < result.get(i).size(); p++) {
                             System.out.print(result.get(i).get(p) + ", ");
                        }
                    }
                    System.out.println();
                }
            }

        }

        System.out.println("diferentes " + diferencias + " de " + iteraciones);

    }

    /**
     * Test of cortaCara method, of class Mapa.
     */
    //@Test
    public void testCortaCara() {
        System.out.println("cortaCara");
        Phexagono h = null;
        Recta r = null;
        Mapa instance = null;
        int expResult = 0;
        int result = instance.cortaCara(h, r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vertices method, of class Mapa.
     */
    //@Test
    public void testVertices() {
        System.out.println("vertices");
        int columna = 0;
        int fila = 0;
        Mapa instance = null;
        Punto[] expResult = null;
        Punto[] result = instance.vertices(columna, fila);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vertice method, of class Mapa.
     */
    //@Test
    public void testVertice() {
        System.out.println("vertice");
        int columna = 0;
        int fila = 0;
        int numero = 0;
        Mapa instance = null;
        Punto expResult = null;
        Punto result = instance.vertice(columna, fila, numero);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of caracontraria method, of class Mapa.
     */
    //@Test
    public void testCaracontraria() {
        System.out.println("caracontraria");
        int cara = 0;
        Mapa instance = null;
        int expResult = 0;
        int result = instance.caracontraria(cara);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
