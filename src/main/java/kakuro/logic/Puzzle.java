package kakuro.logic;

import java.util.ArrayList;

    /**
    * Pelilaudan logiikasta vastaava luokka
    *
    */
public class Puzzle {

    private Map map;
    private int nRows;
    private int nCols;
    private Square[][] squares;
    private int[][] rowSums;
    private int[][] colSums;
    private int xStart;
    private int yStart;

    public Puzzle(int mapNr) {
        this.map = new Map(mapNr); 
        this.nRows = this.map.getnRows();
        this.nCols = this.map.getnCols();
        this.squares = new Square[this.nRows][this.nCols];
        this.rowSums = new int[nRows][nCols];
        this.colSums = new int[nRows][nCols];
        this.xStart = 0;
        this.yStart = 0;
        initSquares();
        initRows();
        initColumns();
    }
    
    /**
    * Ruutujen initialisointi
    *
    */
    public void initSquares() {
        boolean startXY = false;
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                if (this.map.getChar(i, j) != '#') {
                    if (!startXY) {
                        this.yStart = i;
                        this.xStart = j;
                        startXY = true;
                    }
                    this.squares[i][j] = new Square(this.map.getChar(i, j) - 48);
                }
            }
        }
    }
    
    /**
    * Rivien alustus ja rivisummien laskenta
    *
    */
    public void initRows() {
        for (int i = 0; i < this.nRows; i++) {
            Row r1 = null;
            int rowStart = 0;
            for (int j = 0; j < this.nCols; j++) {
                if (this.squares[i][j] != null) {
                    if (r1 == null) {
                        rowStart = j - 1;
                        r1 = new Row();
                        r1.setRowStart(i, j);
                    }
                    r1.addSquare(squares[i][j]);
                } else if (r1 != null) {
                    rowSums[i][rowStart] = r1.getCorrectSum();
                    r1 = null;
                }
            }
        }
    }

    /**
    * Sarakkeiden alustus ja sarakesummien laskenta
    *
    */
    public void initColumns() {
        for (int j = 0; j < this.nCols; j++) {
            Column c1 = null;
            int colStart = 0;
            for (int i = 0; i < this.nRows; i++) {
                if (this.squares[i][j] != null) {
                    if (c1 == null) {
                        colStart = i - 1;
                        c1 = new Column();
                        c1.setColumnStart(j, i);
                    }
                    c1.addSquare(squares[i][j]);
                } else if (c1 != null) {
                    colSums[colStart][j] = c1.getCorrectSum();
                    c1 = null;
                }
            }
        }
    }

    /**
    * Pelialueen rivien lukumäärä
    *
    */
    public int getnRows() {
        return this.nRows;
    }

    /**
    * Pelialueen sarakkeiden lukumäärä
    *
    */
    public int getnCols() {
        return this.nCols;
    }
    
    public int getXstart() {
        return this.xStart;
    }
    
    public int getYstart() {
        return this.yStart;
    }

    public int getSquareRowSum(int i, int j) {
        return this.rowSums[i][j];
    }

    public int getSquareColSum(int i, int j) {
        return this.colSums[i][j];
    }

    /**
    * Ruutu paikassa i,j
    *
    */
    public Square getSquare(int i, int j) {
        return this.squares[i][j];
    }
    

    /**
    * Numeron laittaminen ruutuun
    *
    */
    public int setSquare(int i, int j, int number) {
        return squares[i][j].setNumber(number);
    }

    /**
    * Tarkistetaan, voiko ruutuun laittaa numeron
    *
    */
    public boolean checkSquare(int i, int j) {
        if (this.squares[i][j] == null) {
            return false;
        }
        return true;
    }
    
    /**
    * Tarkistetaan, onko ruudukko valmis
    *
    */
    public boolean checkCompleted() {
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