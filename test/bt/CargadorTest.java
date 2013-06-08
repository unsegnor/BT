/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Víctor
 */
public class CargadorTest {
    
    public CargadorTest() {
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
     * Test of cargarMapa method, of class Cargador.
     */
    //@Test
    public void testCargarMapa() {
        System.out.println("cargarMapa");
        int nJugador = 0;
        DataMapa expResult = null;
        DataMapa result = Cargador.cargarMapa(nJugador);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leerMechs method, of class Cargador.
     */
    @Test
    public void testLeerMechs() {
        System.out.println("leerMechs");
        int nJugador = 0;
        File mechsfile = new File("mechsJ0.tst");
        DataMechs result = Cargador.leerMechs(nJugador, mechsfile);
        
        assertEquals(2, result.getnMechs());
        assertEquals(0, result.getMechs()[0].getnJugador());
        assertEquals(true, result.getMechs()[0].isOperativo());
        assertEquals(false, result.getMechs()[0].isDesconectado());
        assertEquals(false, result.getMechs()[0].isAtascado());
        assertEquals(false, result.getMechs()[0].isEnelsuelo());
        assertEquals(3, result.getMechs()[0].getFila());
        assertEquals(11, result.getMechs()[0].getColumna());
        assertEquals(3, result.getMechs()[0].getEncaramientoMech());
        assertEquals(3, result.getMechs()[0].getEncaramientoTorso());
        assertEquals(0, result.getMechs()[0].getTemperatura());
        assertEquals(false, result.getMechs()[0].isArdiendo());
        assertEquals(false, result.getMechs()[0].isConGarrote());
        assertEquals(0, result.getMechs()[0].getTipoGarrote());
        assertEquals(14, result.getMechs()[0].getBlindaje_brazo_izq());
        assertEquals(22, result.getMechs()[0].getBlindaje_pierna_izq());
        assertEquals(17, result.getMechs()[0].getBlindaje_torso_izq());
        assertEquals(17, result.getMechs()[0].getBlindaje_torso_der());
        assertEquals(22, result.getMechs()[0].getBlindaje_pierna_der());
        assertEquals(14, result.getMechs()[0].getBlindaje_brazo_der());
        assertEquals(21, result.getMechs()[0].getBlindaje_torso_central());
        assertEquals(9, result.getMechs()[0].getBlindaje_cabeza());
        assertEquals(5, result.getMechs()[0].getBlindaje_torso_izq_atras());
        assertEquals(5, result.getMechs()[0].getBlindaje_torso_der_atras());
        assertEquals(7, result.getMechs()[0].getBlindaje_torso_central_atras());
        assertEquals(21, result.getMechs()[0].getPuntos_estructura_pierna_der());
        assertEquals(21, result.getMechs()[0].getPuntos_estructura_pierna_izq());
        assertEquals(17, result.getMechs()[0].getPuntos_estructura_brazo_der());
        assertEquals(17, result.getMechs()[0].getPuntos_estructura_brazo_izq());
        assertEquals(21, result.getMechs()[0].getPuntos_estructura_torso_der());
        assertEquals(21, result.getMechs()[0].getPuntos_estructura_torso_izq());
        assertEquals(31, result.getMechs()[0].getPuntos_estructura_torso_central());
        assertEquals(3, result.getMechs()[0].getPuntos_estructura_cabeza());
        assertEquals(4, result.getMechs()[0].getInformacion_adicional().getPuntos_andar());
        assertEquals(6, result.getMechs()[0].getInformacion_adicional().getPuntos_correr());
        assertEquals(0, result.getMechs()[0].getInformacion_adicional().getPuntos_saltar());
        assertEquals(0, result.getMechs()[0].getInformacion_adicional().getHeridas_warrior());
        assertEquals(true, result.getMechs()[0].getInformacion_adicional().isWarrior_consciente());
        
    }

    /**
     * Test of cargarMech method, of class Cargador.
     */
    //@Test
    public void testCargarMech() {
        System.out.println("cargarMech");
        int nJugador = 0;
        DataMechs expResult = null;
        DataMechs result = Cargador.cargarMech(nJugador);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarConfig method, of class Cargador.
     */
    //@Test
    public void testCargarConfig() {
        System.out.println("cargarConfig");
        int nJugador = 0;
        DataConfig expResult = null;
        DataConfig result = Cargador.cargarConfig(nJugador);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of escribirAccion method, of class Cargador.
     */
    //@Test
    public void testEscribirAccion() {
        System.out.println("escribirAccion");
        int nJugador = 0;
        Accion accion = null;
        Cargador.escribirAccion(nJugador, accion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leerMapa method, of class Cargador.
     */
    @Test
    public void testLeerMapa() {
        System.out.println("leerMapa");
        int nJugador = 0;
        File archivo = new File("mapaJ0.tst");
        DataMapa result = Cargador.leerMapa(nJugador, archivo);
        //Comprobamos cosas
        assertEquals(10, result.getAlto());
        assertEquals(0, result.getHexagonos()[0].getNivel());
        assertEquals(-4, result.getHexagonos()[47].getNivel());
        assertEquals(7, result.getHexagonos()[70].getNivel());
        assertEquals(0, result.getHexagonos()[10].getTipo_de_terreno());
        assertEquals(1, result.getHexagonos()[21].getTipo_de_terreno());
        assertEquals(2, result.getHexagonos()[32].getTipo_de_terreno());
        assertEquals(3, result.getHexagonos()[43].getTipo_de_terreno());
        assertEquals(0, result.getHexagonos()[12].getObjeto());
        assertEquals(1, result.getHexagonos()[22].getObjeto());
        assertEquals(2, result.getHexagonos()[32].getObjeto());
        assertEquals(3, result.getHexagonos()[42].getObjeto());
        assertEquals(4, result.getHexagonos()[52].getObjeto());
        assertEquals(5, result.getHexagonos()[62].getObjeto());
        assertEquals(6, result.getHexagonos()[72].getObjeto());
        assertEquals(7, result.getHexagonos()[82].getObjeto());
        assertEquals(255, result.getHexagonos()[59].getObjeto());
        assertEquals(120, result.getHexagonos()[83].getFCE());
        assertEquals(120, result.getHexagonos()[73].getFCE());
        assertEquals(90, result.getHexagonos()[63].getFCE());
        assertEquals(40, result.getHexagonos()[53].getFCE());
        assertEquals(15, result.getHexagonos()[43].getFCE());
        assertEquals(false, result.getHexagonos()[61].getCaras_con_rio()[0]); //Comprobamos el río que va de 0702 a 0704
        assertEquals(true, result.getHexagonos()[61].getCaras_con_rio()[3]);
        assertEquals(true, result.getHexagonos()[62].getCaras_con_rio()[0]);
        assertEquals(true, result.getHexagonos()[62].getCaras_con_rio()[3]);
        assertEquals(true, result.getHexagonos()[63].getCaras_con_rio()[0]);
        assertEquals(false, result.getHexagonos()[63].getCaras_con_rio()[3]);
        assertEquals(false, result.getHexagonos()[11].getCaras_con_carretera()[0]); //Comprobamos la carretera que va de 0202 a 0204
        assertEquals(true, result.getHexagonos()[11].getCaras_con_carretera()[3]);
        assertEquals(true, result.getHexagonos()[12].getCaras_con_carretera()[0]);
        assertEquals(true, result.getHexagonos()[12].getCaras_con_carretera()[3]);
        assertEquals(true, result.getHexagonos()[13].getCaras_con_carretera()[0]);
        assertEquals(false, result.getHexagonos()[13].getCaras_con_carretera()[3]);
               
    }

    /**
     * Test of leerConfig method, of class Cargador.
     */
    @Test
    public void testLeerConfig() {
        System.out.println("leerConfig");
        int nJugador = 0;
        File archivo = new File("configJ0.tst");
        DataConfig result = Cargador.leerConfig(nJugador, archivo);
        assertEquals(true, result.isIncendiosPermitidos());
        assertEquals(false, result.isViento());
        assertEquals(-1, result.getDireccion_viento());
        assertEquals(false, result.isAtaquesFisicosPermitidos());
        assertEquals(true, result.isFaseDeCalor());
        assertEquals(false, result.isDevastarBosquesPermitido());
        assertEquals(true, result.isDerrumbarEdificiosPermitido());
        assertEquals(false, result.isChequeosDePilotaje());
        assertEquals(true, result.isChequeoDamageEnTurno());
        assertEquals(true, result.isChequeoDesconexion());
        assertEquals(false, result.isImpactosCriticosPermitidos());
        assertEquals(true, result.isExplosionDeMunicionPermitida());
        assertEquals(true, result.isApagarRadiadoresPermitido());
        assertEquals(true, result.isTiempoDeRespuestaLimitado());
        assertEquals(120, result.getTiempoLimiteDeRespuesta());
    }

    /**
     * Test of cargarIniciativa method, of class Cargador.
     */
    //@Test
    public void testCargarIniciativa() {
        System.out.println("cargarIniciativa");
        int nJugador = 0;
        DataIniciativa expResult = null;
        DataIniciativa result = Cargador.cargarIniciativa(nJugador);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leerIniciativa method, of class Cargador.
     */
    @Test
    public void testLeerIniciativa() {
        System.out.println("leerIniciativa");
        int nJugador = 0;
        File archivo = new File("iniciativaJ0.tst");
        DataIniciativa result = Cargador.leerIniciativa(nJugador, archivo);
        assertEquals(8, result.orden[0]);
        assertEquals(7, result.orden[1]);
        assertEquals(6, result.orden[2]);
        assertEquals(5, result.orden[3]);
        assertEquals(4, result.orden[4]);
        assertEquals(3, result.orden[5]);
        assertEquals(2, result.orden[6]);
        assertEquals(1, result.orden[7]);
    }

    /**
     * Test of cargarDefMech method, of class Cargador.
     */
    //@Test
    public void testCargarDefMech() {
        System.out.println("cargarDefMech");
        int nJugador = 0;
        int mech = 0;
        DataDefMech expResult = null;
        DataDefMech result = Cargador.cargarDefMech(nJugador, mech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leerDefMech method, of class Cargador.
     */
    @Test
    public void testLeerDefMech() {
        System.out.println("leerDefMech");
        int nJugador = 0;
        int mech = 0;
        File archivo = new File("defmech.tst");
        DataDefMech result = Cargador.leerDefMech(nJugador, mech, archivo);
        assertEquals(9,result.getBlindajeCabeza());
        assertEquals(20,result.getBlindajeTorsoDerecho());
        assertEquals(20,result.getBlindajeTorsoIzquierdo());
        assertEquals(29,result.getBlindajeTorsoCentral());
        assertEquals(18, result.getBlindajeBrazoDerecho());
        assertEquals(18, result.getBlindajeBrazoIzquierdo());
        assertEquals(22, result.getBlindajePiernaDerecha());
        assertEquals(22, result.getBlindajePiernaIzquierda());
        assertEquals(5,result.getBlindajeAtrasTorsoIzquierdo());
        assertEquals(5,result.getBlindajeAtrasTorsoDerecho());
        assertEquals(8,result.getBlindajeAtrasTorsoCentral());
        assertEquals(3,result.getPuntosInternosCabeza());
        assertEquals(15,result.getPuntosInternosTorsoDerecho());
        assertEquals(15,result.getPuntosInternosTorsoIzquierdo());
        assertEquals(22,result.getPuntosInternosTorsoCentral());
        assertEquals(11,result.getPuntosInternosBrazoDerecho());
        assertEquals(11,result.getPuntosInternosBrazoIzquierdo());
        assertEquals(15,result.getPuntosInternosPiernaDerecha());
        assertEquals(15,result.getPuntosInternosPiernaIzquierda());
        assertEquals(70,result.getToneladas());
        assertEquals(12,result.getnRadiadores());
        assertEquals(4,result.getPuntosMovAndando());
        assertEquals(6,result.getPuntosMovCorriendo());
        assertEquals(0,result.getPuntosMovSaltando());
        //assertEquals(0,result.getMaxCalorGenerado()); no sé lo que esto és
        assertEquals("ARC-2K",result.getModelo());
        assertEquals("Archer",result.getNombre());
        assertEquals("Hombro",result.getLocalizacion()[0].getSlotsOcupados()[0].getNombre());
        assertEquals(result.getActuadores()[result.getLocalizacion()[0].getSlotsOcupados()[0].getIndiceActuador()].getNombre(),result.getLocalizacion()[0].getSlotsOcupados()[0].getNombre());
        
    }
}
