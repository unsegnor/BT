/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Víctor
 */
public class Cargador {

    public static DataMapa cargarMapa(int nJugador) {

        //Cargar archivo
        File mapafile = new File("mapaJ" + nJugador + ".sbt");

        return leerMapa(nJugador, mapafile);
    }

    public static DataMapa leerMapa(int nJugador, File archivo) {
        FileReader fr = null;
        BufferedReader br = null;
        DataMapa dm = null;
        try {

            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            dm = new DataMapa();

            //Leer número mágico
            String numeroMagico = br.readLine().trim();
            if(numeroMagico.equals("mapaSBT")){
                //Comenzamos a leer cosas
                int alto = Integer.parseInt(br.readLine().trim());
                int ancho = Integer.parseInt(br.readLine().trim());
               dm.setAlto(alto);
               dm.setAncho(ancho);
               int nhexagonos = alto*ancho;
               
               //Leer el conjunto de hexagonos
               DataHexagono[] vh = new DataHexagono[nhexagonos];
               
               for(int h=0; h<nhexagonos; h++){
                   vh[h] = new DataHexagono();
                   vh[h].setNivel(Integer.parseInt(br.readLine().trim())); //Nivel
                   vh[h].setTipo_de_terreno(Integer.parseInt(br.readLine().trim())); //Tipo de terreno
                   vh[h].setObjeto(Integer.parseInt(br.readLine().trim())); //Objeto en el terreno
                   vh[h].setFCE(Integer.parseInt(br.readLine().trim())); //FCE
                   vh[h].setEdificio_derrumbado(Boolean.parseBoolean(br.readLine().trim())); //Está derrumbado el edificio?
                   vh[h].setFuego(Boolean.parseBoolean(br.readLine().trim())); //Hay fuego en el hexágono?
                   vh[h].setHumo(Boolean.parseBoolean(br.readLine().trim())); //Hay humo en el hexágono?
                   vh[h].setNumero_de_garrotes(Integer.parseInt(br.readLine().trim())); //Número de garrotes en el hexágono
                   //Caras con ríos
                   vh[h].setCaras_con_rio(cargar_vector_bool(6,br)); //Cargar el río de las caras
                   //Caras con carretera
                   vh[h].setCaras_con_carretera(cargar_vector_bool(6, br)); //Cargar la carretera de las caras
               }
               
               //Añadir los datos al datamapa
               dm.setHexagonos(vh);
               
            }else{
                System.err.println("Esperado \"mapaSBT\" encontrado " + numeroMagico + " en el archivo " + archivo.getName());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);

        } finally{
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dm;
    }

    public static DataMechs leerMechs(int nJugador, File mechsfile) {
        FileReader fr = null;
        BufferedReader br = null;
        DataMechs dm = null;
        try {
            dm = new DataMechs();
            //Leer archivo
            fr = new FileReader(mechsfile);
            br = new BufferedReader(fr);

            String numeroMagico = (br.readLine()).trim();
            if (numeroMagico.equals("mechsSBT")) {
                //Comenzamos a extraer datos
                int nMechs = Integer.parseInt((br.readLine()).trim()); //Número de mechs
                dm.setnMechs(nMechs);
                //Vector de los mechs
                DataMech[] vdm = new DataMech[nMechs];
                //Llenar vector
                for (int i = 0; i < nMechs; i++) {
                    vdm[i] = new DataMech();
                    vdm[i].setnJugador(Integer.parseInt((br.readLine()).trim()));            //Número del jugador que lo controla
                    vdm[i].setOperativo(Boolean.parseBoolean((br.readLine()).trim()));       //Está operativo?
                    vdm[i].setDesconectado(Boolean.parseBoolean((br.readLine()).trim()));    //Está desconectado?
                    vdm[i].setAtascado(Boolean.parseBoolean((br.readLine()).trim()));        //Está atascado en terreno pantanoso?
                    vdm[i].setEnelsuelo(Boolean.parseBoolean((br.readLine()).trim()));       //Está en el suelo?
                    String hex = (br.readLine()).trim();                                     //Leemos la posición
                    vdm[i].setColumna(Integer.parseInt(hex.substring(0, 2)));           //almacenamos la fila
                    vdm[i].setFila(Integer.parseInt(hex.substring(2, 4)));       //almacenamos la columna
                    vdm[i].setEncaramientoMech(Integer.parseInt((br.readLine()).trim()));    //Encaramiento mech
                    vdm[i].setEncaramientoTorso(Integer.parseInt((br.readLine()).trim()));   //Encaramiento torso
                    vdm[i].setTemperatura(Integer.parseInt((br.readLine()).trim()));         //Temperatura
                    vdm[i].setArdiendo(Boolean.parseBoolean((br.readLine()).trim()));        //Está ardiendo?
                    vdm[i].setConGarrote(Boolean.parseBoolean((br.readLine()).trim()));      //Con garrote?
                    vdm[i].setTipoGarrote(Integer.parseInt((br.readLine()).trim()));         //Tipo de garrote
                    //Puntos de blindaje
                    vdm[i].setBlindaje_brazo_izq(Integer.parseInt((br.readLine()).trim()));  //BBI
                    vdm[i].setBlindaje_torso_izq(Integer.parseInt((br.readLine()).trim()));  //BTI
                    vdm[i].setBlindaje_pierna_izq(Integer.parseInt((br.readLine()).trim())); //BPI
                    vdm[i].setBlindaje_pierna_der(Integer.parseInt((br.readLine()).trim())); //BPD
                    vdm[i].setBlindaje_torso_der(Integer.parseInt((br.readLine()).trim()));  //BTD
                    vdm[i].setBlindaje_brazo_der(Integer.parseInt((br.readLine()).trim()));  //BBD
                    vdm[i].setBlindaje_torso_central(Integer.parseInt((br.readLine()).trim()));
                    vdm[i].setBlindaje_cabeza(Integer.parseInt((br.readLine()).trim()));
                    vdm[i].setBlindaje_torso_izq_atras(Integer.parseInt((br.readLine()).trim()));
                    vdm[i].setBlindaje_torso_der_atras(Integer.parseInt((br.readLine()).trim()));
                    vdm[i].setBlindaje_torso_central_atras(Integer.parseInt((br.readLine()).trim()));
                    //Puntos de estructura interna
                    vdm[i].setPuntos_estructura_brazo_izq(Integer.parseInt((br.readLine()).trim())); //EBI
                    vdm[i].setPuntos_estructura_torso_izq(Integer.parseInt((br.readLine()).trim())); //ETI
                    vdm[i].setPuntos_estructura_pierna_izq(Integer.parseInt((br.readLine()).trim()));//EPI
                    vdm[i].setPuntos_estructura_pierna_der(Integer.parseInt((br.readLine()).trim()));//EPD
                    vdm[i].setPuntos_estructura_torso_der(Integer.parseInt((br.readLine()).trim())); //ETD
                    vdm[i].setPuntos_estructura_brazo_der(Integer.parseInt((br.readLine()).trim())); //EBD
                    vdm[i].setPuntos_estructura_torso_central(Integer.parseInt((br.readLine()).trim())); //ETC
                    vdm[i].setPuntos_estructura_cabeza(Integer.parseInt((br.readLine()).trim())); //EC
                    //Información sólo del Mech que indica el archivo
                    if (i == nJugador) {
                        //Sí tenemos más información y la leemos
                        DataMechAddInfo inf = new DataMechAddInfo();
                        inf.setPuntos_andar(Integer.parseInt((br.readLine()).trim())); //Puntos de movimiento para andar
                        inf.setPuntos_correr(Integer.parseInt((br.readLine()).trim())); //Puntos de movimiento para correr
                        inf.setPuntos_saltar(Integer.parseInt((br.readLine()).trim())); //Puntos de movimiento para saltar
                        inf.setRadiadores_encendidos(Integer.parseInt((br.readLine()).trim())); //Radiadores operativos encendidos
                        inf.setRadiadores_apagados(Integer.parseInt((br.readLine()).trim())); //Radiadores operativos apagados
                        inf.setHeridas_warrior(Integer.parseInt((br.readLine()).trim())); //Heridas del MechWarrior
                        inf.setWarrior_consciente(Boolean.parseBoolean((br.readLine()).trim())); //MechWarrior consciente
                        //Slots impactados
                        inf.setSlots_impactados_brazo_izq(cargar_vector_bool(12, br)); //Cargar los del brazo izquierdo
                        inf.setSlots_impactados_torso_izq(cargar_vector_bool(12, br)); //Cargar los del torso izquierdo
                        inf.setSlots_impactados_pierna_izq(cargar_vector_bool(6, br)); //Cargar los de la pierna izquierda
                        inf.setSlots_impactados_pierna_der(cargar_vector_bool(6, br)); //Cargar los de la pierna derecha
                        inf.setSlots_impactados_torso_der(cargar_vector_bool(12, br)); //Cargar los del torso derecho
                        inf.setSlots_impactados_brazo_der(cargar_vector_bool(12, br)); //Cargar los del brazo derecho
                        inf.setSlots_impactados_torso_central(cargar_vector_bool(12, br)); //Cargar los del torso central
                        inf.setSlots_impactados_cabeza(cargar_vector_bool(6, br)); //Cargar los de la cabeza
                        //Localizaciones desde las que se ha disparado un arma
                        inf.setDisparo_de_brazo_izq(Boolean.parseBoolean((br.readLine()).trim()));       //Disparo de BI
                        inf.setDisparo_de_torso_izq(Boolean.parseBoolean((br.readLine()).trim()));       //TI
                        inf.setDisparo_de_pierna_izq(Boolean.parseBoolean((br.readLine()).trim()));      //PI
                        inf.setDisparo_de_pierna_der(Boolean.parseBoolean((br.readLine()).trim()));      //PD
                        inf.setDisparo_de_torso_der(Boolean.parseBoolean((br.readLine()).trim()));       //TD
                        inf.setDisparo_de_brazo_der(Boolean.parseBoolean((br.readLine()).trim()));       //BD
                        inf.setDisparo_de_torso_central(Boolean.parseBoolean((br.readLine()).trim()));   //TC
                        inf.setDisparo_de_cabeza(Boolean.parseBoolean((br.readLine()).trim()));          //C
                        //Municiones preparadas par ser expulsadas
                        int nMuniciones = Integer.parseInt((br.readLine()).trim());
                        inf.setMuniciones_preparadas_para_expulsar(nMuniciones);

                        //Crear vector de localizaciones
                        DataMunicionAExpulsar[] dmx = new DataMunicionAExpulsar[nMuniciones];

                        //Para cada munición
                        for (int m = 0; m < nMuniciones; m++) {
                            dmx[m].setLocalizacion((br.readLine()).trim());             //Almacenal la localización de la munición
                            dmx[m].setSlot(Integer.parseInt((br.readLine()).trim()));   //Almacenar el slot
                        }

                        //Añadir la info de municiones al inf
                        inf.setMuniciones_a_expulsar(dmx);

                        //Añadir la información adicional a la información del Mech
                        vdm[i].setInformacion_adicional(inf);

                    } else {
                        //No tenemos información adicional
                        vdm[i].setInformacion_adicional(null);
                    }

                    //Cargar Narcs colocados por el jugador actual
                    vdm[i].setNarc_colocado(cargar_vector_bool(nMechs, br));

                    //Cargar iNarcs colocados por el jugador actual
                    vdm[i].setiNarc_colocado(cargar_vector_bool(nMechs, br));
                }

                //Añadir información al DataMechs
                dm.setMechs(vdm);
            } else {
                System.err.println("Esperado \"mechsSBT\" encontrado " + numeroMagico + " en el archivo " + mechsfile.getName());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dm;
    }

    public static DataMechs cargarMech(int nJugador) {

        File mechsfile = new File("mechsJ" + nJugador + ".sbt");

        return leerMechs(nJugador, mechsfile);
    }

    public static DataConfig cargarConfig(int nJugador) {

        //Cargar archivo de configuración
        File configfile = new File("configJ" + nJugador + ".sbt");

        return leerConfig(nJugador,configfile);
    }
    public static DataConfig leerConfig(int nJugador, File archivo) {
        FileReader fr = null;
        BufferedReader br = null;
        DataConfig dc = null;
        try {

            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            dc = new DataConfig();

            //Leer número mágico
            String numeroMagico = br.readLine().trim();
            if(numeroMagico.equals("configSBT")){
                //Comenzamos a leer cosas
                dc.setIncendiosPermitidos(Boolean.parseBoolean(br.readLine().trim())); //Incendios permitidos
                dc.setViento(Boolean.parseBoolean(br.readLine().trim())); //Existe viento
                dc.setDireccion_viento(Integer.parseInt(br.readLine().trim())); //Direcció del viento
                dc.setAtaquesFisicosPermitidos(Boolean.parseBoolean(br.readLine().trim())); //Los ataques físicos están permitidos
                dc.setFaseDeCalor(Boolean.parseBoolean(br.readLine().trim())); //Hay fase de control de la temperatura
                dc.setDevastarBosquesPermitido(Boolean.parseBoolean(br.readLine().trim())); //Se permite devastar bosques
                dc.setDerrumbarEdificiosPermitido(Boolean.parseBoolean(br.readLine().trim())); //Se permte destruir edificios
                dc.setChequeosDePilotaje(Boolean.parseBoolean(br.readLine().trim())); //Hay chequeos de pilotaje
                dc.setChequeoDamageEnTurno(Boolean.parseBoolean(br.readLine().trim())); //Hay chequeos por daño mayor de 20 en el mismo turno
                dc.setChequeoDesconexion(Boolean.parseBoolean(br.readLine().trim())); //Hay chequeos de desconexión por el calor
                dc.setImpactosCriticosPermitidos(Boolean.parseBoolean(br.readLine().trim())); //Hay impactos críticos
                dc.setExplosionDeMunicionPermitida(Boolean.parseBoolean(br.readLine().trim())); //Hay explosiones de munición
                dc.setApagarRadiadoresPermitido(Boolean.parseBoolean(br.readLine().trim())); //Se pueden apagar los radiadores
                dc.setTiempoDeRespuestaLimitado(Boolean.parseBoolean(br.readLine().trim())); //El tiempo de respuesta está limitado
                dc.setTiempoLimiteDeRespuesta(Integer.parseInt(br.readLine().trim())); //Límite del tiempo de respuesta
               
            }else{
                System.err.println("Esperado \"configSBT\" encontrado " + numeroMagico + " en el archivo " + archivo.getName());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);

        } finally{
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dc;
    }
    public static DataIniciativa cargarIniciativa(int nJugador) {

        //Cargar archivo de configuración
        File iniciativafile = new File("iniciativaJ" + nJugador + ".sbt");

        return leerIniciativa(nJugador, iniciativafile); 
    }
    public static DataIniciativa leerIniciativa(int nJugador, File archivo) {
        FileReader fr = null;
        BufferedReader br = null;
        DataIniciativa di = null;
        try {

            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            di = new DataIniciativa();

            //Comenzamos a leer cosas
            //Leemos el número de jugadores
            int njugadores = Integer.parseInt(br.readLine().trim());
            int[] orden = new int[njugadores];
            //Leemos los jugadores en orden
            for(int i=0; i<njugadores; i++){
                orden[i] = Integer.parseInt(br.readLine().trim());
            }
            //Almacenamos en el datainiciativa
            di.orden = orden;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);

        } finally{
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return di;
    }
    public static DataDefMech cargarDefMech(int nJugador, int mech) {

        //Cargar archivo de configuración
        File iniciativafile = new File("defmechJ" + nJugador + "-" + mech + ".sbt");

        return leerDefMech(nJugador, mech, iniciativafile); 
    }
    public static DataDefMech leerDefMech(int nJugador, int mech, File archivo) {
        FileReader fr = null;
        BufferedReader br = null;
        DataDefMech ddm = null;
        try {

            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            ddm = new DataDefMech();

//Leer número mágico
            String numeroMagico = br.readLine().trim();
            if(numeroMagico.equals("defmechSBT")){
                ddm.leer(br);
            }else{
                System.err.println("Esperado \"defmechSBT\" encontrado " + numeroMagico + " en el archivo " + archivo.getName());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);

        } finally{
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ddm;
    }
    public static void escribirAccion(int nJugador, Accion accion) {

        //Crear archivo
        File accionfile = new File("accionJ" + nJugador + ".sbt");
            
    }

    private static boolean[] cargar_vector_bool(int longitud, BufferedReader br) throws IOException {
        boolean[] v = new boolean[longitud];

        for (int i = 0; i < longitud; i++) {
            v[i] = Boolean.parseBoolean(br.readLine().trim());
        }


        return v;
    }
}
