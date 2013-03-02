/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author Víctor
 */
public class LDVTest {

    Mapa map;
    Random r;
    String ruta_mapa = "C:\\Users\\Víctor\\Documents\\NetBeansProjects\\BT\\testLDV.tst";
    Mapa mapa_compatibilidad;
    String ruta_mapa_compatibilidad = "C:\\Users\\Víctor\\Documents\\NetBeansProjects\\BT\\testCompatibilidad.tst";

    public LDVTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        File archivo = new File("testLDV.tst");
        DataMapa datosmapa = Cargador.leerMapa(0, archivo);
        map = new Mapa(datosmapa);
        r = new Random(System.currentTimeMillis());


        DataMapa datosmapa_compatibilidad = Cargador.leerMapa(0, new File("testCompatibilidad.tst"));
        mapa_compatibilidad = new Mapa(datosmapa_compatibilidad);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcularLDV method, of class LDV.
     */
    //@Test
    public void testCalcularLDV_7args() {
        System.out.println("calcularLDV");
        Mapa mapa = null;
        int columna_origen = 0;
        int fila_origen = 0;
        int nivel_origen = 0;
        int columna_destino = 0;
        int fila_destino = 0;
        int nivel_destino = 0;
        ResultadoLDV expResult = null;
        ResultadoLDV result = LDV.calcularLDV(mapa, columna_origen, fila_origen, nivel_origen, columna_destino, fila_destino, nivel_destino);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularLDV method, of class LDV.
     */
    //@Test
    public void testCalcularLDV_5args() {
        System.out.println("calcularLDV");

        System.out.println("ejecutar");

        int c1 = 0;
        int f1 = 0;
        int c2 = 0;
        int f2 = 0;

        for (int j = 0; f1 < 13; j++) {
            c1 = 3;
            f1 = 1 + j;
            c2 = 4;
            f2 = 1 + j;


            Phexagono origen = new Phexagono(c1, f1);
            int nivel_origen = 0;
            Phexagono destino = new Phexagono(c2, f2);
            int nivel_destino = 0;
            Mapa mapa = map;

            ResultadoLDV expResult = null;
            ResultadoLDV result = LDV.calcularLDV(mapa, origen, nivel_origen, destino, nivel_destino);
            ResultadoLDV result2 = officialLDV.ejecutar(ruta_mapa, origen, nivel_origen, destino, nivel_destino);

            System.out.println("(" + c1 + "," + f1 + ") -> (" + c2 + "," + f2 + ")");
            System.out.println("LDV: " + result.LDV);
            System.out.println("Official LDV: " + result2.LDV);

        }
    }

    @Test
    public void testCalcularLDV_si_deberian() {
        System.out.println("calcularLDV: sí deberían");

        boolean resultados[] = this.calcularLDVs_por_filas(11, 15, 1, 31, false);

        for (int i = 0; i < resultados.length; i++) {
            assertTrue("Fila " + (i + 1), resultados[i]);
        }
    }

    @Test
    public void testCalcularLDV_no_deberian() {
        System.out.println("calcularLDV: no deberían");

        boolean resultados[] = this.calcularLDVs_por_filas(17, 21, 1, 16, false);

        for (int i = 0; i < resultados.length; i++) {
            assertFalse("Fila " + (i + 1), resultados[i]);
        }

    }

    @Test
    public void testCalcularLDV_deberian_impares() {
        System.out.println("calcularLDV: deberían de filas impares");

        boolean resultados[] = this.calcularLDVs_por_filas(23, 31, 1, 11, false);

        for (int i = 0; i < resultados.length; i++) {
            assertEquals("Fila " + (i + 1), i % 2 == 0, resultados[i]);
        }

    }

    @Test
    public void testCalcularCPs_deberian() {
        System.out.println("calcularCPs: deberían");

        //Las CPS van al revés, la directa indica la 
        //cobertura del que ataca
        boolean resultados[] = this.calcularCPs_por_filas(33, 39, 1, 11, false);

        for (int i = 0; i < resultados.length; i++) {
            assertTrue("Fila " + (i + 1), resultados[i]);
        }

        //Y las inversas falsas

        resultados = this.calcularCPs_por_filas(39, 33, 1, 11, false);

        for (int i = 0; i < resultados.length; i++) {
            assertFalse("Fila " + (i + 1), resultados[i]);
        }

    }

    @Test
    public void testCalcularCPs_no_deberian() {
        System.out.println("calcularCPs: no deberían");

        //Las CPS van al revés, la directa indica la 
        //cobertura del que ataca
        boolean resultados[] = this.calcularCPs_por_filas(41, 47, 1, 10, false);

        for (int i = 0; i < resultados.length; i++) {
            assertFalse("Fila " + (i + 1), resultados[i]);
        }

        //Y las inversas falsas también

        resultados = this.calcularCPs_por_filas(47, 41, 1, 10, false);

        for (int i = 0; i < resultados.length; i++) {
            assertFalse("Fila " + (i + 1), resultados[i]);
        }

    }

    //@Test
    public void testCalcularCP_oficial() {
        System.out.println("calcularCP oficial");

        Phexagono origen = new Phexagono(1, 1);
        int nivel_origen = 1;
        Phexagono destino = new Phexagono(7, 10);
        int nivel_destino = 1;
        ResultadoLDV resultado = officialLDV.ejecutar(ruta_mapa, origen, nivel_origen, destino, nivel_destino);
        System.out.println("Cálculo entre " + origen + " y " + destino + ": ");
        for (int i = 0; i < resultado.hexagonos.length; i++) {
            System.out.print(resultado.hexagonos[i]);
        }
        System.out.println();
        System.out.println("LDV: " + resultado.LDV);
        System.out.println("CP: " + resultado.CPdirecta);

    }

    public boolean[] calcularLDVs_por_filas(int columna_origen, int columna_destino, int fila_inicial, int fila_final, boolean depurar) {

        boolean[] respuesta = new boolean[(fila_final - fila_inicial) + 1];

        int c1 = columna_origen;
        int f1 = fila_inicial;
        int c2 = columna_destino;
        int f2 = fila_inicial;

        for (int j = fila_inicial; j <= fila_final; j++) {
            c1 = columna_origen;
            f1 = j;
            c2 = columna_destino;
            f2 = j;


            Phexagono origen = new Phexagono(c1, f1);
            int nivel_origen = 1;
            Phexagono destino = new Phexagono(c2, f2);
            int nivel_destino = 1;
            Mapa mapa = map;

            ResultadoLDV result = LDV.calcularLDV(mapa, origen, nivel_origen, destino, nivel_destino);

            respuesta[j - 1] = result.LDV;

            if (depurar) {
                ResultadoLDV result2 = officialLDV.ejecutar(ruta_mapa, origen, nivel_origen, destino, nivel_destino);
                System.out.println("(" + c1 + "," + f1 + ") -> (" + c2 + "," + f2 + ")" + " LDV: " + result.LDV + " Official LDV: " + result2.LDV);
            }

        }
        return respuesta;
    }

    public boolean[] calcularCPs_por_filas(int columna_origen, int columna_destino, int fila_inicial, int fila_final, boolean depurar) {

        boolean[] respuesta = new boolean[(fila_final - fila_inicial) + 1];

        int c1 = columna_origen;
        int f1 = fila_inicial;
        int c2 = columna_destino;
        int f2 = fila_inicial;

        for (int j = fila_inicial; j <= fila_final; j++) {
            c1 = columna_origen;
            f1 = j;
            c2 = columna_destino;
            f2 = j;


            Phexagono origen = new Phexagono(c1, f1);
            int nivel_origen = 1;
            Phexagono destino = new Phexagono(c2, f2);
            int nivel_destino = 1;
            Mapa mapa = map;

            ResultadoLDV result = LDV.calcularLDV(mapa, origen, nivel_origen, destino, nivel_destino);

            respuesta[j - 1] = result.CPdirecta;

            if (depurar) {
                ResultadoLDV result2 = officialLDV.ejecutar(ruta_mapa, origen, nivel_origen, destino, nivel_destino);
                System.out.println("(" + c1 + "," + f1 + ") -> (" + c2 + "," + f2 + ")" + " CPd: " + result.CPdirecta + " CPi: " + result.CPinversa + " Official CPi: " + result2.CPinversa);
            }

        }
        return respuesta;
    }
@Test
public void testCPpropia_simple(){
    
            Phexagono origen = new Phexagono(6, 13);
            int nivel_origen = 1;
            Phexagono destino = new Phexagono(14, 8);
            int nivel_destino = 0;
            Mapa mapa = mapa_compatibilidad;

            ResultadoLDV result = LDV.calcularLDV(mapa, origen, nivel_origen, destino, nivel_destino);

}
    
    
    /**
     * Test of sumergido method, of class LDV.
     */
    @Test
    public void testSumergido() {
        System.out.println("sumergido");
        Mapa mapa = map;
        //En el primer hexágono 1,1 no debe estar sumergido ni depie ni tirado
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(1, 1), 1)); //Tirado
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(1, 1), 2)); //De pie
        //El 1,2 tiene nivel 1 y por lo tanto profundidad 0 no debería estar sumergido
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(1, 2), 1)); //Tirado
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(1, 2), 2)); //De pie
        //El 1,5 tiene nivel 2 y por lo tanto profundidad 0 no debería estar sumergido
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(1, 5), 1)); //Tirado
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(1, 5), 2)); //De pie
        //El 1,8 tiene nivel 3 y por lo tanto profundidad 0 no debería estar sumergido
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(1, 8), 1)); //Tirado
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(1, 8), 2)); //De pie
        //El 2,2 tiene profundidad 1 debería estar sumergido tirado pero no de pie
        assertEquals(true, LDV.sumergido(mapa, new Phexagono(2, 2), 1)); //Tirado
        assertEquals(false, LDV.sumergido(mapa, new Phexagono(2, 2), 2)); //De pie
        //El 2,5 tiene profundidad 2 debería estar sumergido de todas formas
        assertEquals(true, LDV.sumergido(mapa, new Phexagono(2, 5), 1)); //Tirado
        assertEquals(true, LDV.sumergido(mapa, new Phexagono(2, 5), 2)); //De pie
        //El 2,8 tiene profundidad 3 debería estar sumergido de todas formas
        assertEquals(true, LDV.sumergido(mapa, new Phexagono(2, 8), 1)); //Tirado
        assertEquals(true, LDV.sumergido(mapa, new Phexagono(2, 8), 2)); //De pie
    }

    @Test
    public void test_de_compatibilidad() {
        System.out.println("Test de compatibilidad con LDVyC.exe");

        int diferencias = 0;
        int iteraciones = 100;
        for (int j = 0; j < iteraciones; j++) {
            int c1 = 1 + r.nextInt(mapa_compatibilidad.datos.getAncho());
            int c2 = 1 + r.nextInt(mapa_compatibilidad.datos.getAncho());
            int f1 = 1 + r.nextInt(mapa_compatibilidad.datos.getAlto());
            int f2 = 1 + r.nextInt(mapa_compatibilidad.datos.getAlto());
            int nivel_origen = r.nextInt(2);
            int nivel_destino = r.nextInt(2);
            //System.out.println(j+" entre ("+c1+","+f1+") y ("+c2+","+f2+")");
            Phexagono origen = new Phexagono(c1, f1);
            Phexagono destino = new Phexagono(c2, f2);
            Mapa instance = mapa_compatibilidad;
            //Calcular en función propia (tiene dos hexágonos más, inicio y fin)
            ResultadoLDV resultado = LDV.calcularLDV(mapa_compatibilidad, origen, nivel_origen, destino, nivel_destino);
            ArrayList<ArrayList<Phexagono>> result = resultado.intermedios;
            if (resultado.intermedios == null) {
                result = mapa_compatibilidad.intermedios(origen, destino);
            }
            //Calcular en función ajena
            ResultadoLDV resultadoOficial = officialLDV.ejecutar(ruta_mapa_compatibilidad, origen, nivel_origen, destino, nivel_destino);
            Phexagono[] expResul = resultadoOficial.hexagonos;
            //Construir ruta para comparar
            Phexagono[] igual = new Phexagono[expResul.length + 2];
            //Añadier origen
            igual[0] = origen;
            for (int k = 1; k < igual.length - 1; k++) {
                igual[k] = expResul[k - 1];
            }
            //Añadir destino
            igual[igual.length - 1] = destino;

            //Comparar ambos caminos
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
            //Si la LDV es diferente son diferentes
            if (resultado.LDV != resultadoOficial.LDV) {
                diferentes = true;
            }

            //Si la LDV directa del resultado e inversa del oficial son distintas también son diferentes
            if (resultado.CPdirecta != resultadoOficial.CPinversa) {
                diferentes = true;
            }

            //Si son diferentes los mostramos
            if (diferentes) {
                diferencias++;
                int maxLength = Math.max(igual.length, result.size());
                System.out.println("Diferencias en el caso " + origen + " SN: " + nivel_origen + " -> " + destino + " SN: " + nivel_destino + ":");
                System.out.println("Comparando caminos: ");
                for (int i = 0; i < maxLength; i++) {
                    System.out.print((i < igual.length ? igual[i] : "") + " -> ");
                    if (i < result.size()) {
                        for (int p = 0; p < result.get(i).size(); p++) {
                            System.out.print(result.get(i).get(p) + ", ");
                        }
                    }
                    System.out.println();
                }
                //Mostramos la LDV y Cobertura
                System.out.println("LDVoficial: " + resultadoOficial.LDV + " LDVpropia: " + resultado.LDV);
                System.out.println("CPioficial: " + resultadoOficial.CPinversa + " CPdpropia: " + resultado.CPdirecta);
            }

        }
        System.out.println("Total: " + diferencias + " diferencias de " + iteraciones + " iteraciones.");
    }
}
