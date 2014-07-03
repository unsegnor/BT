/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.util.ArrayList;

/**
 *
 * @author Víctor
 */
class Mech {

    int id;
    DataMech datamech;
    DataDefMech datadefmech;
    PotenciaDeFuego potencia_de_fuego;
    ArrayList<PosicionAccion> posiciones_alcanzables;
    VidaMech vida;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("------------------------------------------\n");
        sb.append("Jugador: ").append(id).append("\n");
        sb.append("Vida: ").append(vida.resumen).append("\n");
        sb.append("PuedeDisparar: ").append(potencia_de_fuego.existe).append("\n");
        sb.append("AlcanceMaximo: ").append(potencia_de_fuego.alcanceMaximo).append("\n");
        sb.append("DanioMaximo: ").append(potencia_de_fuego.danioMaximo).append("\n");
        sb.append("AlcanceDevastador: ").append(potencia_de_fuego.minimoAlcanceCorto).append("\n");

        /*
        sb.append("---Posiciones Alcanzables---\n");
        for (PosicionAccion pa : posiciones_alcanzables) {
            sb.append(pa.posicion.toString()).append(",");
        }*/
        sb.append("\n------------------------------------------\n");

        return sb.toString();
    }
}
