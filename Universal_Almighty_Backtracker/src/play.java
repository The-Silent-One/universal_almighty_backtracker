/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author safou
 */
import java.io.*;

public class play {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        Element [][] map = null;
        int n = Integer.parseInt(in.readLine()); //number of lines
        
        for (int i = 0; i < n; i++) {
            String sl = in.readLine();
            for (int j = 0; j < sl.length(); j++) {
                switch (sl.charAt(j)){
                    case 'S':
                        map[i][j] = new Sheep(i,j);
                        break;
                    case 'D':
                        map[i][j] = new Door(i,j);
                        break;
                    case 'K':
                        map[i][j] = new Key(i,j);
                        break;
                    case 'W':
                        map[i][j] = new Wolf(i,j);
                        break;
                    case 'G':
                        map[i][j] = new Grass(i,j);
                        break;
                    default:
                        map[i][j] = new Field(i,j);
                        
                }
                
                
            }
        }
        
        out.close();
    }
   
}
