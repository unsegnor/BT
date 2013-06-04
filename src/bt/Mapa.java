/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa un mapa del battlemech.
 *
 * @author Víctor
 */
class Mapa {

    static int caraIzquierda(int lado) {
        int r;
        if (lado == 1) {
            r = 6;
        } else {
            r = lado - 1;
        }
        return r;
    }

    static int caraDerecha(int lado) {
        int r;
        if (lado == 6) {
            r = 1;
        } else {
            r = lado + 1;
        }
        return r;
    }

    /**
     * Devuelve el lado desde el que se encara p_fin desde p_inicio
     *
     * @param p_inicio
     * @param p_fin
     * @return
     */
    static int encarar(Phexagono p_inicio, Phexagono p_fin) {
        int lado = 0;
        int columna_inicio = p_inicio.getColumna();
        int columna_fin = p_fin.getColumna();
        int fila_inicio = p_inicio.getFila();
        int fila_fin = p_fin.getFila();

        //Si el fin está por encima del inicio
        if (fila_fin < fila_inicio) {
            if (columna_fin > columna_inicio) {
                //si la columna de fin es superior a la columna de inicio es que está a la derecha
                //así que para encaralo miraremos al lado 2
                lado = 2;
            } else if (columna_fin < columna_inicio) {
                lado = 6;
            } else {
                //Si están en la misma columna miramos hacia arriba
                lado = 1;
            }
        } else {
            if (columna_fin > columna_inicio) {
                lado = 3;
            } else if (columna_fin < columna_inicio) {
                lado = 5;
            } else {
                //Si están en la misma columna miramos hacia abajo
                lado = 4;
            }
        }

        return lado;
    }
    //Se construye a partir de los datos de un DataMapa.
    DataMapa datos;
    Hexagono[] hexagonos;
    //Datos de los hexágonos
    public final double apotema = 15; //Apotema del hexágono
    public final double lado = 2 * apotema / Math.sqrt(3); //Lado de cada hexágono

    public Mapa(DataMapa datos_del_mapa) {
        init(datos_del_mapa);
    }

    private void init(DataMapa dm) {
        datos = dm;

        //Convertir los DataHexágonos en Hexágonos y almacenar por separado.
        DataHexagono[] dh = dm.getHexagonos();
        int l = dh.length;
        //Inicializar el vector de hexágonos
        hexagonos = new Hexagono[l];
        //Convertir y almacenar
        for (int i = 0; i < l; i++) {
            hexagonos[i] = new Hexagono(dh[i]);
        }
    }

    public Hexagono casilla(int columna, int fila) {
        Hexagono respuesta = null;
        if (columna > 0 && columna <= datos.getAncho() && fila > 0 && fila <= datos.getAlto()) {
            int posicion_en_vector = datos.getAlto() * (columna - 1) + (fila - 1);
            respuesta = hexagonos[posicion_en_vector];
        } else {
            //Consultando casilla fuera del mapa
        }
        return respuesta;
    }

    public Hexagono casilla(int columnaFila) {
        int columna = (int) (columnaFila / 100);
        int fila = columnaFila % (columna * 100);
        return casilla(columna, fila);
    }

    public Hexagono casilla(Phexagono posicion) {
        return casilla(posicion.getColumna(), posicion.getFila());
    }

    //Devuelve las casillas adyacentes a una dada, en el orden de caras
    public DataHexagono[] adyacentes(int columna, int fila) {

        DataHexagono[] respuesta = new DataHexagono[6];

        for (int cara = 1; cara <= 6; cara++) {
            respuesta[cara - 1] = casilla(this.cara(columna, fila, cara));
        }

        return respuesta;
    }

    public Phexagono cara(Phexagono p, int cara) {
        return cara(p.getColumna(), p.getFila(), cara);
    }

    //Devuelve el hexágono que está en esa cara
    public Phexagono cara(int columna, int fila, int cara) {
        Phexagono respuesta = null;
        if (cara == 1) {
            //Devolvemos el que está encima, es decir una fila antes
            respuesta = new Phexagono(columna, fila - 1);
        } else if (cara == 2) {
            //Depende
            if (columna % 2 == 0) {
                //Si la columna es par entonces sumamos uno a la columna, la fila ees la misma
                respuesta = new Phexagono(columna + 1, fila);
            } else {
                //Si es impar sumamos a la columna y restamos a la fila
                respuesta = new Phexagono(columna + 1, fila - 1);
            }
        } else if (cara == 3) {
            //Depende
            if (columna % 2 == 0) {
                //Si la columna es par entonces sumamos uno a la columna y a la fila
                respuesta = new Phexagono(columna + 1, fila + 1);
            } else {
                //Si es impar sumamos sólo a la columna
                respuesta = new Phexagono(columna + 1, fila);
            }
        } else if (cara == 4) {
            //Devolvemos el que está debajo, es decir, una fila después
            respuesta = new Phexagono(columna, fila + 1);
        } else if (cara == 5) {
            //Depende
            if (columna % 2 == 0) {
                //Si la columna es par entonces restamos columna y sumamos fila
                respuesta = new Phexagono(columna - 1, fila + 1);
            } else {
                //Si es impar sólo restamos columna
                respuesta = new Phexagono(columna - 1, fila);
            }
        } else if (cara == 6) {
            //Depende
            if (columna % 2 == 0) {
                //Si la columna es par entonces restamos columna
                respuesta = new Phexagono(columna - 1, fila);
            } else {
                //Si es impar restamos columna y fila
                respuesta = new Phexagono(columna - 1, fila - 1);
            }
        }
        return respuesta;
    }

    public ResultadoLDV calcular(Mapa mapa, Phexagono origen, int suma_origen, Phexagono destino, int suma_destino) {

        //Una está dentro del agua y la otra no, no hay LDV

        //Si son adyacentes entonces sí hay LDV salvo que se cumpla lo anterior.

        //Si no se cumplen las dos anteriores y las dos están sumergidas entonces no hay LDV



        //Representar los hexágonos en el espacio
        //Obtener posición del orígen y el destino
        Punto p_origen = puntoEspacial(origen);
        p_origen.z += suma_origen * 6;
        Punto p_destino = puntoEspacial(destino);
        p_destino.z += suma_destino * 6;

        //Calcular la recta que une los dos puntos


        //Ir calculando el punto de la cara por la que entra y el punto de la cara por la que sale


        return null;
    }

    public Punto puntoEspacial(Phexagono posicion) {
        return puntoEspacial(posicion.getColumna(), posicion.getFila());
    }

    public double distancia(Phexagono a, Phexagono b){
        return distancia(puntoEspacial(a), puntoEspacial(b));
    }
    
    public double distancia(Punto a, Punto b){
        double diffX = Math.abs(a.x - b.x);
        double diffY = Math.abs(a.y - b.y);
        return Math.sqrt((diffX * diffX) + (diffY * diffY));
    }
    
    public Punto puntoEspacial(int columna, int fila) {
        //Cada hexágono mide 30 metros de lado a lado
        //Vamos a devolver la posición del centro del hexágono en el espacio

        //El primer hexágono estará en 15,alto-15,nivel
        //El 0102 estará en 15(misma columna), alto-(15+30)(casilla de abajo), nivel
        //El 0201 estará en 15+30(siguiente columna), alto-30(si es columna par), nivel
        //El columnafila estará en 15+(30*columna),alto-((30 si par, 15 si impar)+30*fila), nivel

        double x = lado * (1 + (1.5 * (columna - 1)));
        double y = (datos.getAlto() * 2 * apotema) - ((((columna % 2) == 0) ? 2 * apotema : apotema) + (2 * apotema * (fila - 1)));
        double z = casilla(columna, fila).getNivel();

        Punto p = new Punto(x, y, z);

        return p;
    }
    //Devuelve el segmento que delimita la cara del hexágono

    public Segmento caraEspacio(int columna, int fila, int cara) {

        Segmento r = null;

        //El lado de cada hexágono mide:
        //Si a = sqrt(lado^2 - (lado/2)^2) (fórmula del círculo inscrito en el hexágono
        //lado = sqrt(4/3)*a
        //lado = 2a/sqrt(3)
        //siendo a la apotema el hexágono: 15 metros
        double lado = 30 / Math.sqrt(3);
        //Calcular punto en el espacio del centro del hexágono
        Punto centro = this.puntoEspacial(columna, fila);
        //Marcamos los puntos
        if (cara == 1) {
            Punto p0 = new Punto(centro.x - lado / 2, centro.y + 15, centro.z);
            Punto p1 = new Punto(centro.x + lado / 2, centro.y + 15, centro.z);
            r = new Segmento(p0, p1);
        } else if (cara == 2) {
            Punto p1 = new Punto(centro.x + lado / 2, centro.y + 15, centro.z);
            Punto p2 = new Punto(centro.x + lado / 1, centro.y + 00, centro.z);
            r = new Segmento(p1, p2);
        } else if (cara == 3) {
            Punto p2 = new Punto(centro.x + lado / 1, centro.y + 00, centro.z);
            Punto p3 = new Punto(centro.x + lado / 2, centro.y - 15, centro.z);
            r = new Segmento(p2, p3);
        } else if (cara == 4) {
            Punto p3 = new Punto(centro.x + lado / 2, centro.y - 15, centro.z);
            Punto p4 = new Punto(centro.x - lado / 2, centro.y - 15, centro.z);
            r = new Segmento(p3, p4);
        } else if (cara == 5) {
            Punto p4 = new Punto(centro.x - lado / 2, centro.y - 15, centro.z);
            Punto p5 = new Punto(centro.x - lado / 1, centro.y + 00, centro.z);
            r = new Segmento(p4, p5);
        } else if (cara == 6) {
            Punto p5 = new Punto(centro.x - lado / 1, centro.y + 00, centro.z);
            Punto p0 = new Punto(centro.x - lado / 2, centro.y + 15, centro.z);
            r = new Segmento(p5, p0);
        }

        //Devolver el segmento
        return r;
    }

    public Punto[] vertices(int columna, int fila) {
        Punto centro = this.puntoEspacial(columna, fila);
        Punto[] p = new Punto[6];
        //Marcamos los puntos
        p[0] = new Punto(centro.x - lado / 2, centro.y + 15, centro.z);
        p[1] = new Punto(centro.x + lado / 2, centro.y + 15, centro.z);
        p[2] = new Punto(centro.x + lado / 1, centro.y + 00, centro.z);
        p[3] = new Punto(centro.x + lado / 2, centro.y - 15, centro.z);
        p[4] = new Punto(centro.x - lado / 2, centro.y - 15, centro.z);
        p[5] = new Punto(centro.x - lado / 1, centro.y + 00, centro.z);

        return p;
    }

    public Punto vertice(int columna, int fila, int numero) {
        Punto centro = this.puntoEspacial(columna, fila);
        Punto p = null;
        //Marcamos los puntos
        if (numero == 0) {
            p = new Punto(centro.x - lado / 2, centro.y + 15, centro.z);
        } else if (numero == 1) {
            p = new Punto(centro.x + lado / 2, centro.y + 15, centro.z);
        } else if (numero == 2) {
            p = new Punto(centro.x + lado / 1, centro.y + 00, centro.z);
        } else if (numero == 3) {
            p = new Punto(centro.x + lado / 2, centro.y - 15, centro.z);
        } else if (numero == 4) {
            p = new Punto(centro.x - lado / 2, centro.y - 15, centro.z);
        } else if (numero == 5) {
            p = new Punto(centro.x - lado / 1, centro.y + 00, centro.z);
        }

        return p;
    }

    public Segmento[] caraEspacio(int columna, int fila) {

        Segmento[] r = new Segmento[6];

        //El lado de cada hexágono mide:
        //Si a = sqrt(lado^2 - (lado/2)^2) (fórmula del círculo inscrito en el hexágono
        //lado = sqrt(4/3)*a
        //lado = 2a/sqrt(3)
        //siendo a la apotema el hexágono: 15 metros
        //Calcular punto en el espacio del centro del hexágono
        Punto centro = this.puntoEspacial(columna, fila);
        //Marcamos los puntos
        Punto p0 = new Punto(centro.x - lado / 2, centro.y + 15, centro.z);
        Punto p1 = new Punto(centro.x + lado / 2, centro.y + 15, centro.z);
        r[0] = new Segmento(p0, p1);
        Punto p2 = new Punto(centro.x + lado / 1, centro.y + 00, centro.z);
        r[1] = new Segmento(p1, p2);
        Punto p3 = new Punto(centro.x + lado / 2, centro.y - 15, centro.z);
        r[2] = new Segmento(p2, p3);
        Punto p4 = new Punto(centro.x - lado / 2, centro.y - 15, centro.z);
        r[3] = new Segmento(p3, p4);
        Punto p5 = new Punto(centro.x - lado / 1, centro.y + 00, centro.z);
        r[4] = new Segmento(p4, p5);
        r[5] = new Segmento(p5, p0);


        //Devolver el segmento
        return r;
    }

    public Segmento[] caraEspacio(Phexagono p) {
        return caraEspacio(p.getColumna(), p.getFila());
    }
    //Devuelve la cara del hexágono siguiente con la que se encuentra

    public int caracontraria(int cara) {
        int respuesta = 0;
        if (cara == 1) {
            respuesta = 4;
        } else if (cara == 2) {
            respuesta = 5;
        } else if (cara == 3) {
            respuesta = 6;
        } else if (cara == 4) {
            respuesta = 1;
        } else if (cara == 5) {
            respuesta = 2;
        } else if (cara == 6) {
            respuesta = 3;
        }

        return respuesta;
    }
    //Indica cuál de los dos hexágonos obstaculiza más la LDV
    //0 es que obstaculizan por igual, 1 que a obstaculiza más y 2 que b obstaculiza más

    public int masObstaculizaLDV(Phexagono a, Phexagono b, Phexagono origen, Phexagono destino) {

        //El que más obstaculiza la LDV será siempre el más alto de los dos

        //En caso de que sean iguales el que tenga el mayor modificador


        //TODO si ralla mucho esta función lo que haremos será devolver todos los caminos posibles
        //y que se escoja después de calcular la línea de visión con todos.
        return 0;
    }

    public boolean bloqueaLDV(Phexagono hexagono, Phexagono origen, Phexagono destino) {
        boolean respuesta = false;

        //El hexágono bloquea la LDV si

        //Está al lado del orígen y mide más que éste
        //Está al lado del destino y mide más que éste

        return respuesta;
    }
    Random rand = new Random();

    //Ejecuta la función anterior y en caso de empate devuelve uno aleatoriamente
    public int masObstaculizaLDVresuelve(Phexagono a, Phexagono b, Phexagono origen, Phexagono destino) {
        int r = masObstaculizaLDV(a, b, origen, destino);
        if (r == 0) {
            r = 1 + rand.nextInt(2);
        }
        return r;
    }

    //Indica si un hexágono es válido o está fuera del mapa
    public boolean valido(int columna, int fila) {
        return columna > 0 && columna <= datos.getAncho() && fila > 0 && fila <= datos.getAlto();
    }

    public boolean valido(Phexagono h) {
        return valido(h.getColumna(), h.getFila());
    }

    //Calcula y devuelve las posiciones de los terrenos intermedios entre dos terrenos
    public ArrayList<ArrayList<Phexagono>> intermedios(Phexagono origen, Phexagono destino) {
        //Nota: en los casos en que la línea pasa exactamente entre dos hexágonos se resuelve por cuál pasa
        //en función de cuál obstaculice más la LDV y se añade a la lista directamente pasando a ser el siguiente
        //el hexágono en el que deja de darse esa situación de ambigüedad. Ya que ninguno de los anteriores puede ser
        //el hexágono de destino.
        ArrayList<ArrayList<Phexagono>> lista = new ArrayList<>();

        ArrayList<Phexagono> nuevoarray; //Array que utilizamos para añadir a la lista

        //Trazar línea entre los hexágonos
        Punto p_origen = puntoEspacial(origen);
        Punto p_destino = puntoEspacial(destino);
        Segmento recta = new Segmento(p_origen, p_destino);

        //Valores iniciales
        Phexagono actual = origen;
        int caraentrada = -1;
        boolean[][] cpp0 = new boolean[6][6]; //Caras posibles con pendiente positiva
        //Con pendiente >0 y <1 es imposible que
        //  entre por 1 y salga por 2 o 3 o 4               -> 1:  , , , ,5,6
        //  entre por 2 y salga por 1 o 3                   -> 2:  , , ,4,5,6               
        //  entre por 3 y salga por 1 o 2 o 6               -> 3:  , , ,4,5, 
        //  entre por 4 y salga por 1 o 5 o 6               -> 4:  ,2,3, , , 
        //  entre por 5 y salga por 4 o 6                   -> 5: 1,2,3, , , 
        //  entre por 6 y salga por 3 o 4 o 5               -> 6: 1,2, , , , 
        cpp0[0][0] = false;
        cpp0[0][1] = false;
        cpp0[0][2] = false;
        cpp0[0][3] = false;
        cpp0[0][4] = true;
        cpp0[0][5] = true;
        cpp0[1][0] = false;
        cpp0[1][1] = false;
        cpp0[1][2] = false;
        cpp0[1][3] = true;
        cpp0[1][4] = true;
        cpp0[1][5] = true;
        cpp0[2][0] = false;
        cpp0[2][1] = false;
        cpp0[2][2] = false;
        cpp0[2][3] = true;
        cpp0[2][4] = true;
        cpp0[2][5] = false;
        cpp0[3][0] = false;
        cpp0[3][1] = true;
        cpp0[3][2] = true;
        cpp0[3][3] = false;
        cpp0[3][4] = false;
        cpp0[3][5] = false;
        cpp0[4][0] = true;
        cpp0[4][1] = true;
        cpp0[4][2] = true;
        cpp0[4][3] = false;
        cpp0[4][4] = false;
        cpp0[4][5] = false;
        cpp0[5][0] = true;
        cpp0[5][1] = true;
        cpp0[5][2] = false;
        cpp0[5][3] = false;
        cpp0[5][4] = false;
        cpp0[5][5] = false;
        boolean[][] cpp1 = new boolean[6][6]; //Caras posibles con pendiente positiva
        //Con pendiente >1 es imposible que
        //  entre por 1 y salga por 2 o 3 o 6               -> 1:  , , ,4,5, 
        //  entre por 2 y salga por 1 o 5 o 6               -> 2:  , ,3,4, , 
        //  entre por 3 y salga por 1 o 4 o 5 o 6           -> 3:  ,2, , , , 
        //  entre por 4 y salga por 3 o 5 o 6               -> 4: 1,2, , , , 
        //  entre por 5 y salga por 2 o 3 o 4               -> 5: 1, , , , ,6
        //  entre por 6 y salga por 1 o 2 o 3 o 4           -> 6:  , , , ,5, 
        cpp1[0][0] = false;
        cpp1[0][1] = false;
        cpp1[0][2] = false;
        cpp1[0][3] = true;
        cpp1[0][4] = true;
        cpp1[0][5] = false;
        cpp1[1][0] = false;
        cpp1[1][1] = false;
        cpp1[1][2] = true;
        cpp1[1][3] = true;
        cpp1[1][4] = false;
        cpp1[1][5] = false;
        cpp1[2][0] = false;
        cpp1[2][1] = true;
        cpp1[2][2] = false;
        cpp1[2][3] = false;
        cpp1[2][4] = false;
        cpp1[2][5] = false;
        cpp1[3][0] = true;
        cpp1[3][1] = true;
        cpp1[3][2] = false;
        cpp1[3][3] = false;
        cpp1[3][4] = false;
        cpp1[3][5] = false;
        cpp1[4][0] = true;
        cpp1[4][1] = false;
        cpp1[4][2] = false;
        cpp1[4][3] = false;
        cpp1[4][4] = false;
        cpp1[4][5] = true;
        cpp1[5][0] = false;
        cpp1[5][1] = false;
        cpp1[5][2] = false;
        cpp1[5][3] = false;
        cpp1[5][4] = true;
        cpp1[5][5] = false;
        boolean[][] cpn0 = new boolean[6][6]; //Caras posibles con pendiente positiva
        //Con pendiente <0 y >-1 es imposible que
        //  entre por 1 y salga por 4 o 5 o 6               -> 1:  ,2,3, , , 
        //  entre por 2 y salga por 3 o 4 o 5               -> 2: 1, , , , ,6
        //  entre por 3 y salga por 2 o 4                   -> 3: 1, , , ,5,6
        //  entre por 4 y salga por 1 o 2 o 3               -> 4:  , , , ,5,6
        //  entre por 5 y salga por 1 o 2 o 6               -> 5:  , ,3,4, , 
        //  entre por 6 y salga por 1 o 5                   -> 6:  ,2,3,4, , 
        cpn0[0][0] = false;
        cpn0[0][1] = true;
        cpn0[0][2] = true;
        cpn0[0][3] = false;
        cpn0[0][4] = false;
        cpn0[0][5] = false;
        cpn0[1][0] = true;
        cpn0[1][1] = false;
        cpn0[1][2] = false;
        cpn0[1][3] = false;
        cpn0[1][4] = false;
        cpn0[1][5] = true;
        cpn0[2][0] = true;
        cpn0[2][1] = false;
        cpn0[2][2] = false;
        cpn0[2][3] = false;
        cpn0[2][4] = true;
        cpn0[2][5] = true;
        cpn0[3][0] = false;
        cpn0[3][1] = false;
        cpn0[3][2] = false;
        cpn0[3][3] = false;
        cpn0[3][4] = true;
        cpn0[3][5] = true;
        cpn0[4][0] = false;
        cpn0[4][1] = false;
        cpn0[4][2] = true;
        cpn0[4][3] = true;
        cpn0[4][4] = false;
        cpn0[4][5] = false;
        cpn0[5][0] = false;
        cpn0[5][1] = true;
        cpn0[5][2] = true;
        cpn0[5][3] = true;
        cpn0[5][4] = false;
        cpn0[5][5] = false;
        boolean[][] cpn1 = new boolean[6][6]; //Caras posibles con pendiente positiva
        //Con pendiente <-1 es imposible que
        //  entre por 1 y salga por 2 o 5 o 6               -> 1:  , ,3,4, , 
        //  entre por 2 y salga por 1 o 4 o 5 o 6           -> 2:  , ,3, , , 
        //  entre por 3 y salga por 4 o 5 o 6               -> 3: 1,2, , , , 
        //  entre por 4 y salga por 2 o 3 o 5               -> 4: 1, , , , ,6
        //  entre por 5 y salga por 1 o 2 o 3 o 4           -> 5:  , , , , ,6
        //  entre por 6 y salga por 1 o 2 o 3               -> 6:  , , ,4,5, 
        cpn1[0][0] = false;
        cpn1[0][1] = false;
        cpn1[0][2] = true;
        cpn1[0][3] = true;
        cpn1[0][4] = false;
        cpn1[0][5] = false;
        cpn1[1][0] = false;
        cpn1[1][1] = false;
        cpn1[1][2] = true;
        cpn1[1][3] = false;
        cpn1[1][4] = false;
        cpn1[1][5] = false;
        cpn1[2][0] = true;
        cpn1[2][1] = true;
        cpn1[2][2] = false;
        cpn1[2][3] = false;
        cpn1[2][4] = false;
        cpn1[2][5] = false;
        cpn1[3][0] = true;
        cpn1[3][1] = false;
        cpn1[3][2] = false;
        cpn1[3][3] = false;
        cpn1[3][4] = false;
        cpn1[3][5] = true;
        cpn1[4][0] = false;
        cpn1[4][1] = false;
        cpn1[4][2] = false;
        cpn1[4][3] = false;
        cpn1[4][4] = false;
        cpn1[4][5] = true;
        cpn1[5][0] = false;
        cpn1[5][1] = false;
        cpn1[5][2] = false;
        cpn1[5][3] = true;
        cpn1[5][4] = true;
        cpn1[5][5] = false;
        //Determinar la pendiente de la recta
        double pendiente = recta.m;
        double pu = 1.7320508; //pendiente umbral
        //Mientras no sea el último
        while (actual.getColumna() != destino.getColumna() || actual.getFila() != destino.getFila()) {
            //Añadir el actual a la lista
            nuevoarray = new ArrayList<>();
            nuevoarray.add(actual);
            lista.add(nuevoarray);

            //Determinar el siguiente hexágono
            //Determinar el punto de salida de la recta
            int carasalida = -1;
            int carasiguiente = -1;
            Phexagono siguiente = null;
            //Obtener segmentos de las caras
            Segmento[] segmento_caras = this.caraEspacio(actual);
            Punto salida = null;
            int contador_cara = 1;

            while (salida == null && contador_cara <= 6) {

                if (caraentrada != -1) {
                    if (Op.mayorque(pendiente, pu)) {
                        if (cpp1[caraentrada - 1][contador_cara - 1]) {
                            salida = recta.corte(segmento_caras[contador_cara - 1]);
                        }
                    } else if (Op.mayorque(pendiente, 0) && Op.menorque(pendiente, pu)) {
                        if (cpp0[caraentrada - 1][contador_cara - 1]) {
                            salida = recta.corte(segmento_caras[contador_cara - 1]);
                        }
                    } else if (Op.menorque(pendiente, 0) && Op.mayorque(pendiente, -pu)) {
                        if (cpn0[caraentrada - 1][contador_cara - 1]) {
                            salida = recta.corte(segmento_caras[contador_cara - 1]);
                        }
                    } else if (Op.menorque(pendiente, -pu)) {
                        if (cpn1[caraentrada - 1][contador_cara - 1]) {
                            salida = recta.corte(segmento_caras[contador_cara - 1]);
                        }
                    } else if (Op.iguales(pendiente, 0)) {
                        if (caraentrada == 23) {
                            salida = recta.corte(segmento_caras[4]);
                        } else if (caraentrada == 56) {
                            salida = recta.corte(segmento_caras[2]);
                        }
                    } else if (Op.iguales(pendiente, pu)) {
                        if (caraentrada == 12) {
                            salida = recta.corte(segmento_caras[3]);
                        } else if (caraentrada == 45) {
                            salida = recta.corte(segmento_caras[1]);
                        }
                    } else if (Op.iguales(pendiente, -pu)) {
                        if (caraentrada == 61) {
                            salida = recta.corte(segmento_caras[2]);
                        } else if (caraentrada == 34) {
                            salida = recta.corte(segmento_caras[5]);
                        }
                    }
                } else {
                    //Si no es la misma cara por la que ha entrado
                    if (caraentrada != contador_cara) {
                        salida = recta.corte(segmento_caras[contador_cara - 1]);
                    }
                }
                contador_cara++;
            }
            //Aquí o hemos pasado por todas las caras sin resultado o salida es el punto de corte de salida y contador_cara la cara
            if (salida == null) {
                //Si hemos recorrido todos sin resultado es que la línea pasa por encima de una cara
                //en este caso, en función de la pendiente y la cara de entrada sabremos por qué cara está pasando
                //Creo que nunca puede darse este caso porque si concide exactamente con un segmento entonces
                //tocará en un punto con los dos segmentos adyacentes así que tendremos un punto de salida
                //que puede ser igual al de entrada o el lado opuesto
            } else {
                //salida contiene el punto de corte, ahora hay que ver si corresponde con un vértice
                //Obtener vértices para comparar
                Punto[] vertices = vertices(actual.getColumna(), actual.getFila());
                //TODO mejora: Dependiendo de la cara comprobamos los vértices contiguos
                //if(contador_cara == 1){
                //Comprobamos si el punto coincide con el vértice 0 o 1
                //comprobar vértices contador_cara-1 y contador_cara (menos el 6 que es entro 5 y 0)
                //}
                int v = 0;
                int verticecoincidente = -1;
                for (v = 0; v < 6; v++) {
                    //TODO ajustar factor de error para que coincida con 
                    if (Op.iguales(salida.x, vertices[v].x, 0.1) && Op.iguales(salida.y, vertices[v].y)) {
                        //Coincide
                        verticecoincidente = v;
                    }
                }
                //Aquí verticecoincidente tiene el vértice coincidente o -1 si no coincide con ninguno
                if (verticecoincidente == -1) {
                    //No coincide con ningún vértice así que cara_contador es la cara por la que sale
                    //Así determinamos el siguiente hexágono
                    carasalida = contador_cara - 1;
                    siguiente = cara(actual, carasalida);
                    //La cara por la que entra al siguiente hexágono
                    carasiguiente = caracontraria(carasalida);
                } else {
                    //Si coincide con un vértice pueden ser varias cosas dependiendo de la pendiente de la recta
                    //TODO cuando pasa por enmedio añadir los dos?? no porque se sumarían bosques q no hay
                    //Miremos por casos
                    if (verticecoincidente == 0) {
                        //Si la pendiente es menor que -1 entonces pasamos al de arriba (cara 1)
                        if (Op.menorque(pendiente, -pu)) {
                            carasalida = 1;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else if (Op.mayorque(pendiente, -pu)) {
                            //Si es mayor que -1 pasamos al de la cara 6
                            carasalida = 6;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else {
                            //Si es igual a -1 entonces elegimos uno de los dos (cara 1 o cara 6)
                            //lo añadimos y pasamos al siguiente que los toca a los dos
                            Phexagono cara1 = cara(actual, 1);
                            Phexagono cara6 = cara(actual, 6);
                            /*int elegido = masObstaculizaLDVresuelve(cara1, cara6, origen, destino);
                             if(elegido==1){
                             //cara1 es el elegido
                             lista.add(cara1);
                             }else{
                             //cara6 es el elegido
                             lista.add(cara6);
                             }*/
                            //Añadimos las dos opciones y retrasamos la elección
                            nuevoarray = new ArrayList<>();
                            if (valido(cara1)) {
                                nuevoarray.add(cara1);
                            }
                            if (valido(cara6)) {
                                nuevoarray.add(cara6);
                            }
                            lista.add(nuevoarray);
                            //No hay cara siguiente
                            carasiguiente = 34;
                            //Determinar el siguiente como
                            //el adyacente a uno de los dos
                            //el de la cara 6 del 1, o el de la cara 1 del 6
                            siguiente = cara(cara1, 6);
                        }

                    } else if (verticecoincidente == 3) {
                        //Si la pendiente es menor que -1 entonces pasamos al de arriba (cara 1)
                        if (Op.mayorque(pendiente, -pu)) {
                            carasalida = 3;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else if (Op.menorque(pendiente, -pu)) {
                            //Si es mayor que -1 pasamos al de la cara 6
                            carasalida = 4;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else {
                            //Si es igual a -pu entonces elegimos uno de los dos (cara 1 o cara 6)
                            //lo añadimos y pasamos al siguiente que los toca a los dos
                            Phexagono cara3 = cara(actual, 3);
                            Phexagono cara4 = cara(actual, 4);
                            /*int elegido = masObstaculizaLDVresuelve(cara3, cara4, origen, destino);
                             if(elegido==1){
                             //cara1 es el elegido
                             lista.add(cara3);
                             }else{
                             //cara6 es el elegido
                             lista.add(cara4);
                             }*/
                            //Añadimos las dos opciones y retrasamos la elección
                            nuevoarray = new ArrayList<>();
                            if (valido(cara3)) {
                                nuevoarray.add(cara3);
                            }
                            if (valido(cara4)) {
                                nuevoarray.add(cara4);
                            }
                            lista.add(nuevoarray);
                            //No hay cara siguiente
                            carasiguiente = 61;
                            //Determinar el siguiente como
                            //el adyacente a uno de los dos
                            //el de la cara 6 del 1, o el de la cara 1 del 6
                            siguiente = cara(cara3, 4);
                        }

                    } else if (verticecoincidente == 1) {
                        //Si la pendiente es menor que -1 entonces pasamos al de arriba (cara 1)
                        if (Op.mayorque(pendiente, pu)) {
                            carasalida = 1;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else if (Op.menorque(pendiente, pu)) {
                            //Si es mayor que -1 pasamos al de la cara 6
                            carasalida = 2;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else {
                            //Si es igual a -1 entonces elegimos uno de los dos (cara 1 o cara 6)
                            //lo añadimos y pasamos al siguiente que los toca a los dos
                            Phexagono cara1 = cara(actual, 1);
                            Phexagono cara2 = cara(actual, 2);
                            /*int elegido = masObstaculizaLDVresuelve(cara1, cara2, origen, destino);
                             if(elegido==1){
                             //cara1 es el elegido
                             lista.add(cara1);
                             }else{
                             //cara6 es el elegido
                             lista.add(cara2);
                             }*/
                            //Añadimos las dos opciones y retrasamos la elección
                            nuevoarray = new ArrayList<>();
                            if (valido(cara1)) {
                                nuevoarray.add(cara1);
                            }
                            if (valido(cara2)) {
                                nuevoarray.add(cara2);
                            }
                            lista.add(nuevoarray);
                            //No hay cara siguiente
                            carasiguiente = 45;
                            //Determinar el siguiente como
                            //el adyacente a uno de los dos
                            //el de la cara 6 del 1, o el de la cara 1 del 6
                            siguiente = cara(cara1, 2);
                        }

                    } else if (verticecoincidente == 4) {
                        //Si la pendiente es menor que -1 entonces pasamos al de arriba (cara 1)
                        if (Op.mayorque(pendiente, pu)) {
                            carasalida = 4;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else if (Op.menorque(pendiente, pu)) {
                            //Si es mayor que -1 pasamos al de la cara 6
                            carasalida = 5;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else {
                            //Si es igual a -1 entonces elegimos uno de los dos (cara 1 o cara 6)
                            //lo añadimos y pasamos al siguiente que los toca a los dos
                            Phexagono cara4 = cara(actual, 4);
                            Phexagono cara5 = cara(actual, 5);
                            /*int elegido = masObstaculizaLDVresuelve(cara4, cara5, origen, destino);
                             if(elegido==1){
                             //cara1 es el elegido
                             lista.add(cara4);
                             }else{
                             //cara6 es el elegido
                             lista.add(cara5);
                             }*/
                            //Añadimos las dos opciones y retrasamos la elección
                            nuevoarray = new ArrayList<>();
                            if (valido(cara4)) {
                                nuevoarray.add(cara4);
                            }
                            if (valido(cara5)) {
                                nuevoarray.add(cara5);
                            }
                            lista.add(nuevoarray);
                            //No hay cara siguiente
                            carasiguiente = 12;
                            //Determinar el siguiente como
                            //el adyacente a uno de los dos
                            //el de la cara 6 del 1, o el de la cara 1 del 6
                            siguiente = cara(cara4, 5);
                        }

                    } else if (verticecoincidente == 2) {
                        //Si la pendiente es menor que -1 entonces pasamos al de arriba (cara 1)
                        if (Op.mayorque(pendiente, 0)) {
                            carasalida = 2;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else if (Op.menorque(pendiente, 0)) {
                            //Si es mayor que -1 pasamos al de la cara 6
                            carasalida = 3;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else {
                            //Si es igual a -1 entonces elegimos uno de los dos (cara 1 o cara 6)
                            //lo añadimos y pasamos al siguiente que los toca a los dos
                            Phexagono cara2 = cara(actual, 2);
                            Phexagono cara3 = cara(actual, 3);
                            /*int elegido = masObstaculizaLDVresuelve(cara2, cara3, origen, destino);
                             if(elegido==1){
                             //cara1 es el elegido
                             lista.add(cara2);
                             }else{
                             //cara6 es el elegido
                             lista.add(cara3);
                             }*/
                            //Añadimos las dos opciones y retrasamos la elección
                            nuevoarray = new ArrayList<>();
                            if (valido(cara2)) {
                                nuevoarray.add(cara2);
                            }
                            if (valido(cara3)) {
                                nuevoarray.add(cara3);
                            }
                            lista.add(nuevoarray);
                            //No hay cara siguiente
                            carasiguiente = 56;
                            //Determinar el siguiente como
                            //el adyacente a uno de los dos
                            //el de la cara 6 del 1, o el de la cara 1 del 6
                            siguiente = cara(cara2, 3);
                        }

                    } else if (verticecoincidente == 5) {
                        //Si la pendiente es menor que -1 entonces pasamos al de arriba (cara 1)
                        if (Op.mayorque(pendiente, 0)) {
                            carasalida = 5;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else if (Op.menorque(pendiente, 0)) {
                            //Si es mayor que -1 pasamos al de la cara 6
                            carasalida = 6;
                            carasiguiente = caracontraria(carasalida);
                            siguiente = cara(actual, carasalida);
                        } else {
                            //Si es igual a -1 entonces elegimos uno de los dos (cara 1 o cara 6)
                            //lo añadimos y pasamos al siguiente que los toca a los dos
                            Phexagono cara5 = cara(actual, 5);
                            Phexagono cara6 = cara(actual, 6);
                            /*int elegido = masObstaculizaLDVresuelve(cara5, cara6, origen, destino);
                             if(elegido==1){
                             //cara1 es el elegido
                             lista.add(cara5);
                             }else{
                             //cara6 es el elegido
                             lista.add(cara6);
                             }*/
                            //Añadimos las dos opciones y retrasamos la elección
                            nuevoarray = new ArrayList<>();
                            if (valido(cara5)) {
                                nuevoarray.add(cara5);
                            }
                            if (valido(cara6)) {
                                nuevoarray.add(cara6);
                            }
                            lista.add(nuevoarray);
                            //No hay cara siguiente
                            carasiguiente = 23;
                            //Determinar el siguiente como
                            //el adyacente a uno de los dos
                            //el de la cara 6 del 1, o el de la cara 1 del 6
                            siguiente = cara(cara5, 6);
                        }

                    }

                }
            }
            actual = siguiente;
            caraentrada = carasiguiente;
        }
        nuevoarray = new ArrayList<>();
        nuevoarray.add(destino);
        lista.add(nuevoarray);
        //lista.add(destino);
        /*
         lista.add(actual);
         //
         //Comenzamos a trazar el camino
         //Añadir origen
         lista.add(origen);
         //Detectar cara por la que sale
         //Obtener segmentos de las caras
         Segmento[] caras = this.caraEspacio(origen);
         //Calcular la cara con la que corta
         //En este caso sólo es una
         int i=0;
         boolean encontrado=false;
         Punto c = recta.corte(caras[i]);
         while(c!=null && i<6){
         i++;
         c = recta.corte(caras[i]);
         }
         //Aquñi tenemos el punto de la cara (que nos da igual) y la cara por la que sale la recta
         int carasalida = i;
         //Calculamos el siguiente hexágono
         //Obtenemos la posición del hexágono adyacente correspondiente a esa cara
         Phexagono siguiente = this.cara(origen, i);
         //Si es el destino, hemos terminado
         //Sino continuamos
         //Phexagono actual = origen;
         //Mientras el actual sea distinto del destino
         //int caraentrada = -1;
         while(actual.getColumna() != destino.getColumna() && actual.getFila() != destino.getFila()){
         //Comprobar el número de caras por las que sale, 
         //El punto por el que sale
         Punto cc = null;
         while(cc==null && i<6)
         //si son dos es que pasa justo por el punto que las une
         //ya depende de su pendiente si pasará a uno o a otro hexágono siguiente o justo por enmedio
         //Si no es ninguna es que pasa por el lado de la que ha entrado, o que sólo ha tocado un punto(no puede ser)
         //Comprobar la cara con la que corta distinta de la cara por la que entró (aunque puede ser la misma si entra o sale justo por un vértice)
         //TODO arreglar esta parte, ir determinando la cara por la que sale la recta distinta de la que entra
         //tener en cuenta el caso en el que la línea pasa justo entre dos hexágonos
         //TODO hacer testeses
         }
         */
        //Devolver la lista directamente
        //TODO si queremos podemos transformarlo en array pero para lo que se va a usar creo que vale más la pena dejarlo así.
        //return lista.toArray(new Phexagono[lista.size()]);
        return lista;
    }

    public int cortaCara(Phexagono h, Recta r) {
        return 0;
    }

    int calcularCosteCambio(Phexagono hex, Phexagono destino) {
        //Coste total
        int costeTotal = 1;


        //Obtener las casillas
        Hexagono chex = casilla(hex);
        Hexagono cdestino = casilla(destino);

        //Calcular el cambio de elevación
        int cambioDeElevacion = Math.abs(chex.getNivel() - cdestino.getNivel());

        if (cambioDeElevacion < 3) {

            //Calcular coste por entrada en el terreno destino
            int costeEntrada = 0;

            Reglas.TerrenoTipo terreno = cdestino.getTipoterreno();

            switch (terreno) {
                case Agua:
                    //En función de la profundidad
                    switch (cdestino.getProfundidad()) {
                        case 0:
                            costeEntrada += 1;
                            //chequeo de pilotaje
                            break;
                        case 1:
                            costeEntrada += 2;
                            break;
                        case 2:
                            costeEntrada += 4;
                            break;
                    }
                    break;
                case Pantanoso:

                    break;


            }
        } else {
            costeTotal = 255;
        }

        return costeTotal;
    }

    /**
     * Devuelve las casillas que se encuentran dentro de un radio radio de la
     * casilla posicion
     *
     * @param posicion
     * @param radio
     * @return
     */
    ArrayList<Phexagono> cercanas(Phexagono posicion, int radio) {
        ArrayList<Phexagono> respuesta = new ArrayList<Phexagono>();

        //TODO arreglar, de momento voy a simplficar el método para obtener rápido un conjunto, pero no está bien

        //Generar todas las posiciones

        for (int i = -radio; i <= radio; i++) {
            for (int j = -radio; j <= radio; j++) {

                int c = posicion.getColumna() - i;
                int f = posicion.getFila() - j;

                //Si es una casilla válida
                if (this.valido(c, f)) {
                    respuesta.add(new Phexagono(c, f));
                }
            }
        }

        return respuesta;
    }
}
