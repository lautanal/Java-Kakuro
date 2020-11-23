package kakuro.logic;

import java.util.ArrayList;

/**
 *
 * @author lasselautanala
 */
public class Puzzle {

    private Map map;
    private int nRows;
    private int nCols;
    private Square[][] squares;
    private ArrayList<Row>[] rowList;
    private ArrayList<Column>[] colList;

    public Puzzle(int mapNr) {
        this.map = new Map(mapNr); 
        this.nRows = this.map.getnRows();
        this.nCols = this.map.getnCols();
        this.squares = new Square[this.nRows][this.nCols];
        this.rowList = new ArrayList[nRows];
        for (int i = 0; i < nRows; i++) { 
            this.rowList[i] = new ArrayList<>(); 
        } 
        this.colList = new ArrayList[nCols];
        for (int i = 0; i < nCols; i++) { 
            this.colList[i] = new ArrayList<>(); 
        } 
        initSquares();
        initRows();
        initColumns();
    }
    
    public void initSquares() {
// Ruutujen initialisointi
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                if (this.map.getChar(i, j) != '#') {
                    this.squares[i][j] = new Square(this.map.getChar(i, j) - 48);
                    this.map.setChar(i, j, '_');
                }
            }
        }
    }
    
    public void initRows() {
// Rivien alustus ja rivisummien laskenta
        for (int i = 0; i < this.nRows; i++) {
            Row r1 = null;
            for (int j = 0; j < this.nCols; j++) {
                if (this.squares[i][j] != null) {
                    if (r1 == null) {
                        r1 = new Row();
                        this.rowList[i].add(r1);
                    }
                    r1.addSquare(squares[i][j]);
                } else {
                    r1 = null;
                }
            }
        }
    }

    public void initColumns() {
// Sarakkeiden alustus ja sarakesummien laskenta
        for (int j = 0; j < this.nCols; j++) {
            Column c1 = null;
            for (int i = 0; i < this.nRows; i++) {
                if (this.squares[i][j] != null) {
                    if (c1 == null) {
                        c1 = new Column();
                        this.colList[j].add(c1);
                    }
                    c1.addSquare(squares[i][j]);
                } else {
                    c1 = null;
                }
            }
        }
    }

    public int setSquare(int i, int j, int number) {
// Numeron laittaminen ruutuun
        this.map.setChar(i, j, (char) (number + 48));
        return squares[i][j].setNumber(number);
    }

    public boolean checkSquare(int i, int j) {
// Tarkistetaan, voiko ruutuun laittaa numeron
        if (i < 1 || i >= this.nRows || j < 1 || j >= this.nCols || this.map.getChar(i, j) == '#') {
            return false;
        }
        return true;
    }
    
    public boolean checkCompleted() {
// Tarkistetaan, onko ruudukko valmis
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                if (this.squares[i][j] != null) {
                    if (!this.squares[i][j].checkCorrect()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
// Ruudukon tulostus tekstimuodossa
        String s = "  ";
        for (int j = 0; j < this.nCols; j++) {
            s += j + "  ";
        }
        s += "\n";
        for (int i = 0; i < this.nRows; i++) {
            s = s + i + " ";
            for (int j = 0; j < this.nCols; j++) {
                s = s + this.map.getChar(i, j) + "  ";
            }
            for (int j = 0; j < this.rowList[i].size(); j++) {
                s = s + this.rowList[i].get(j).getCorrectSum() + " ";
                if (this.rowList[i].get(j).getCorrectSum() < 10) {
                    s += " ";
                }
            }
            s += "\n";
        }
        String s0 = "    ";
        String s1 = "    ";
        String s2 = "    ";
        for (int i = 1; i < this.nCols - 1; i++) {
            String[] cSums = colSums(i);
            s0 = s0 + cSums[0];
            s1 = s1 + cSums[1];
            s2 = s2 + cSums[2];
        }
        s = s + s0 + "\n" + s1 + "\n" + s2 + "\n";
        
        return s;
    }
    
    public String[] colSums(int iCol) {
// Sarakkeen summat (tulostus tekstimuodossa)
        String[] cSums = new String[] {"   ", "   ", "   "};
        for (int j = 0; j < this.colList[iCol].size(); j++) {
            int sj = this.colList[iCol].get(j).getCorrectSum();
            if (sj < 10) {
                cSums[j] = " " + sj + " ";
            } else {
                cSums[j] = " " + sj;
            }
        }
        return cSums;
    }
}
