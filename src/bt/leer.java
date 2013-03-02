/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Víctor
 */
public class leer {
    public static int i(BufferedReader br) throws IOException{
        return Integer.parseInt(br.readLine().trim());
    }
    
    public static String s(BufferedReader br) throws IOException{
        return br.readLine().trim();
    }
    
    public static boolean b(BufferedReader br) throws IOException{
        return Boolean.parseBoolean(br.readLine().trim());
    }
}
