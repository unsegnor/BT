/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aestrella;

import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Víctor
 */
public class AestrellaTest {

    public AestrellaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcular method, of class Aestrella.
     */
    @Test
    public void testCalcular() {
        System.out.println("calcular");
        GrafoTest grafo = new GrafoTest(10, 10);
        System.out.println(this.imprimirMapa(grafo));
        I_Nodo origen = grafo.getNodo(0,0);
        I_Nodo destino = grafo.getNodo(9, 9);
        Aestrella instance = new Aestrella();
        Resultado expResult = null;
        Resultado result = instance.calcular(grafo, origen, destino);
        System.out.println(((CosteTest) result.getCoste()).valor);
        System.out.println(imprimirRuta(result.getRuta()));
    }

    private String imprimirMapa(GrafoTest grafo) {
        NodoTest[][] mapa = grafo.mapa;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < grafo.alto; i++) {
            for (int j = 0; j < grafo.ancho; j++) {
                sb.append(mapa[i][j].valor);
            }
            sb.append("\n");
        }

        return sb.toString();

    }

    private String imprimirRuta(ArrayList<I_Nodo> ruta) {
        StringBuilder sb = new StringBuilder();
        for(I_Nodo nodo: ruta){
            sb.append(nodo.toString()).append("->");
        }
        
        
        return sb.toString();
    }

    public static class GrafoTest implements I_Grafo {

        Random r = new Random();
        int alto = 0;
        int ancho = 0;
        //Matriz de costes de entrada
        public NodoTest[][] mapa;

        public GrafoTest(int alto, int ancho) {

            this.alto = alto;
            this.ancho = ancho;
            
            
            mapa = new NodoTest[alto][ancho];

            //Inicializamos la matriz
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    NodoTest nuevo = new NodoTest(r.nextInt(2) + 1);
                    nuevo.pi = i; 
                    nuevo.pj = j;
                    mapa[i][j] = nuevo;
                    
                }
            }
        }

        @Override
        public I_Coste calcularCosteHeuristico(I_Nodo a, I_Nodo b) {
            //La distancia entre los puntos que describen
            NodoTest na = (NodoTest) a;
            NodoTest nb = (NodoTest) b;

            //Calculamos la distancia
            int distanciai = Math.abs(na.pi - nb.pi);
            int distanciaj = Math.abs(na.pj - nb.pj);

            int distancia = distanciai + distanciaj;

            CosteTest respuesta = new CosteTest(distancia);

            return respuesta;
        }

        @Override
        public ArrayList<I_Nodo> getVecinos(I_Nodo nodo) {
            //Devolvemos los nodos a la izquierda, derecha, arriba y abajo
            NodoTest n = (NodoTest) nodo;

            ArrayList<I_Nodo> respuesta = new ArrayList<I_Nodo>();

            int pi = n.pi;
            int pj = n.pj;

            //Calcular nodo de encima
            if (pi > 0) {
                NodoTest naux = mapa[pi - 1][pj];
                naux.setCosteReal(nodo.getCosteReal().sumar(new CosteTest(naux.valor)));
                respuesta.add(naux);
            }
            //Calcular nodo de abajo
            if (pi + 1 < alto) {
                NodoTest naux = mapa[pi + 1][pj];
                naux.setCosteReal(nodo.getCosteReal().sumar(new CosteTest(naux.valor)));
                respuesta.add(naux);
            }
            //Calcular nodo izquierdo
            if (pj > 0) {
                NodoTest naux = mapa[pi][pj - 1];
                naux.setCosteReal(nodo.getCosteReal().sumar(new CosteTest(naux.valor)));
                respuesta.add(naux);
            }
            //Calcular nodo derecho
            if (pj + 1 < ancho) {
                NodoTest naux = mapa[pi][pj + 1];
                naux.setCosteReal(nodo.getCosteReal().sumar(new CosteTest(naux.valor)));
                respuesta.add(naux);
            }

            return respuesta;
        }

        private I_Nodo getNodo(int i, int j) {
            return mapa[i][j];
        }

        @Override
        public I_Coste calcularCosteReal(I_Nodo actual, I_Nodo vecino) {
            NodoTest v = (NodoTest) vecino;
            
            
            CosteTest coste = new CosteTest(v.valor);
            
            return coste;
        }
    }

    private static class NodoTest extends Nodo {

        public int pi = 0;
        public int pj = 0;
        int valor = 0;

        public NodoTest(int valor) {
            this.valor = valor;
            init();
        }

        private void init() {
            this.real = new CosteTest(0);
            this.total = new CosteTest(0);
            this.heuristico = new CosteTest(0);
        }
        
        @Override
        public String toString(){
            return "["+pi+","+pj+","+valor+"]";
        }
    }

    private static class CosteTest implements I_Coste {

        int valor = 0;

        public CosteTest(int valor) {
            this.valor = valor;
        }

        @Override
        public I_Coste sumar(I_Coste coste) {

            CosteTest otro = (CosteTest) coste;

            //Sumamos el valor
            int nuevoValor = valor + otro.valor;

            CosteTest respuesta = new CosteTest(nuevoValor);

            return respuesta;
        }

        @Override
        public int compareTo(I_Coste coste) {
            CosteTest otro = (CosteTest) coste;

            return valor - otro.valor;
        }
        
        @Override
        public String toString(){
            return Integer.toString(valor);
        }
    }
}