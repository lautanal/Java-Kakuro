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
    private int[][] rowSums;
    private int[][] colSums;

    public Puzzle(int mapNr) {
        this.map = new Map(mapNr); 
        this.nRows = this.map.getnRows();
        this.nCols = this.map.getnCols();
        this.squares = new Square[this.nRows][this.nCols];
        this.rowSums = new int[nRows][nCols];
        this.colSums = new int[nRows][nCols];
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
//                    this.map.setChar(i, j, '_');
                }
            }
        }
    }
    
    public void initRows() {
// Rivien alustus ja rivisummien laskenta
        for (int i = 0; i < this.nRows; i++) {
            Row r1 = null;
            int rowStart = 0;
            for (int j = 0; j < this.nCols; j++) {
                if (this.squares[i][j] != null) {
                    if (r1 == null) {
                        rowStart = j - 1;
                        r1 = new Row(i,j);
                    }
                    r1.addSquare(squares[i][j]);
                } else if (r1 != null) {
                    rowSums[i][rowStart] = r1.getCorrectSum();
                    r1 = null;
                }
            }
        }
    }

    public void initColumns() {
// Sarakkeiden alustus ja sarakesummien laskenta
        for (int j = 0; j < this.nCols; j++) {
            Column c1 = null;
            int colStart = 0;
            for (int i = 0; i < this.nRows; i++) {
                if (this.squares[i][j] != null) {
                    if (c1 == null) {
                        colStart = i - 1;
                        c1 = new Column(j,i);
                    }
                    c1.addSquare(squares[i][j]);
                } else if (c1 != null) {
                    colSums[colStart][j] = c1.getCorrectSum();
                    c1 = null;
                }
            }
        }
    }

    public int getnRows() {
        return this.nRows;
    }

    public int getnCols() {
        return this.nCols;
    }

    public int getSquareRowSum(int i, int j) {
        return this.rowSums[i][j];
    }

    public int getSquareColSum(int i, int j) {
        return this.colSums[i][j];
    }

    public Square getSquare(int i, int j) {
        return this.squares[i][j];
    }
    
    public int setSquare(int i, int j, int number) {
// Numeron laittaminen ruutuun
//        this.map.setChar(i, j, (char) (number + 48));
        return squares[i][j].setNumber(number);
    }

    
    public int zeroSquare(int i, int j) {
// Numeron pyyhkiminen pois
//        this.map.setChar(i, j, (char) (number + 48));
        return squares[i][j].zeroNumber();
    }

    public boolean checkSquare(int i, int j) {
// Tarkistetaan, voiko ruutuun laittaa numeron
        if (this.squares[i][j] == null) {
//       if (i < 1 || i >= this.nRows || j < 1 || j >= this.nCols || this.map.getChar(i, j) == '#') {
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
    
    public int getRowStart(int i, int j) {
        return squares[i][j].getRow().getStart();
    }
    
    public int getRowSquares(int i, int j) {
        return squares[i][j].getRow().getSquares();
    }
    
    public int getColumnStart(int i, int j) {
        return squares[i][j].getColumn().getStart();
    }
    
    public int getColumnSquares(int i, int j) {
        return squares[i][j].getColumn().getSquares();
    }
}