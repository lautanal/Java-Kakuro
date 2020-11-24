package kakuro.logic;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author lasselautanala
 */
public class Map {

    private char[][] map;
    private int nRows;
    private int nCols;

    public Map(int mapNr) {
        String fname = "puzzle/puzzle" + mapNr + ".txt";
        this.map = puzzleRead(fname,10,9);
        this.nRows = this.map.length;
        this.nCols = this.map[0].length;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnCols() {
        return nCols;
    }

    public char getChar(int i, int j) {
        return map[i][j];
    }

    public void setChar(int i, int j, char c) {
        map[i][j] = c;
    }


    public char[][] puzzleRead(String fname, int n, int m) {
        char[][] t = new char[n][m];
        try {
            Scanner input = new Scanner(new File(fname));
            for (int i = 0; i < n; i++) {
                String s = input.next();
                for (int j = 0; j < m; j++) {
                    t[i][j] = s.charAt(j);
                }
            }
        } catch (Exception e) {
        }
        return t;
    }
}