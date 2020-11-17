package kakuro.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lasselautanala
 */
public class Puzzle {

    private char[][] map;
    private int nRows;
    private int nCols;
    private Square[][] squares;
    private ArrayList<Row>[] rowList;
    private ArrayList<Column>[] colList;

    public Puzzle(int mapNr) {
        switch (mapNr) {
          case 1:
            this.map = puzzle1();
            break;
          case 2:
            this.map = puzzle2();
            break;
          case 3:
            this.map = puzzle3();
            break;
          case 4:
            this.map = puzzle4();
            break;
          case 5:
            this.map = puzzle5();
            break;
          case 6:
            this.map = puzzle6();
            break;
          case 7:
            this.map = puzzle7();
            break;
          case 8:
            this.map = puzzle8();
            break;
          case 9:
            this.map = puzzle9();
            break;
          case 10:
            this.map = puzzle10();
            break;
        }
        this.nRows = this.map.length;
        this.nCols = this.map[0].length;
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
// Ruutujen olioiden initialisointi
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                if (this.map[i][j] != '#') {
                    this.squares[i][j] = new Square((int)map[i][j]-48);
                    this.map[i][j] = '_';
                }
            }
        }
    }
    
    public void initRows() {
// Rivialkioiden luonti ja rivien summien laskenta
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
// Sarakealkioiden luonti ja summien laskenta
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
        this.map[i][j] = (char)(number + 48);
        return squares[i][j].setNumber(number);
    }

    public boolean checkSquare(int i, int j) {
// Tarkistetaan, voiko ruutuun laittaa numeron
        if (i < 1 || i >= this.nRows || j < 1 || j >= this.nCols || this.map[i][j] == '#') return false;
        return true;
    }
    
    public boolean checkCompleted() {
// Tarkistetaan, onko ruudukko valmis
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                if (this.squares[i][j] != null) {
                    if (!this.squares[i][j].checkCorrect()) return false;
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
                s = s + map[i][j] + "  ";
            }
            for (int j=0; j < this.rowList[i].size(); j++) {
                s = s + this.rowList[i].get(j).getCorrectSum() + " ";
                if (this.rowList[i].get(j).getCorrectSum() < 10) s += " ";
            }
            s += "\n";
        }
        String s0 = "     ";
        String s1 = "     ";
        String s2 = "     ";
        for (int i = 1; i < this.nCols-1; i++) {
            if (this.colList[i].size() == 0) {
                s0 += "   ";
                s1 += "   ";
                s2 += "   ";
            } else if (this.colList[i].size() == 1) {
                s0 = s0 + this.colList[i].get(0).getCorrectSum() + " ";
                if (this.colList[i].get(0).getCorrectSum() < 10) s0 += " ";
                s1 += "   ";
                s2 += "   ";
            } else if (this.colList[i].size() == 2) {
                s0 = s0 + this.colList[i].get(0).getCorrectSum() + " ";
                if (this.colList[i].get(0).getCorrectSum() < 10) s0 += " ";
                s1 = s1 + this.colList[i].get(1).getCorrectSum() + " ";
                if (this.colList[i].get(1).getCorrectSum() < 10) s1 += " ";
                s2 += "   ";
            } else {
                s0 = s0 + this.colList[i].get(0).getCorrectSum() + " ";
                if (this.colList[i].get(0).getCorrectSum() < 10) s0 += " ";
                s1 = s1 + this.colList[i].get(1).getCorrectSum() + " ";
                if (this.colList[i].get(1).getCorrectSum() < 10) s1 += " ";
                s2 = s2 + this.colList[i].get(2).getCorrectSum() + " ";
                if (this.colList[i].get(2).getCorrectSum() < 10) s2 += " ";
            }
        }
        s = s + s0 + "\n" + s1 + "\n" + s2 + "\n";
        
        return s;

    }

// Ruudukko 1
    public char[][] puzzle1() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','#','9','5','#','#','#','#'},
            {'#','#','4','3','2','#','#','#','#'},
            {'#','9','3','#','#','3','8','#','#'},
            {'#','7','6','#','3','1','7','#','#'},
            {'#','#','1','3','4','#','2','6','#'},
            {'#','#','7','5','#','#','3','9','#'},
            {'#','#','#','#','5','8','1','#','#'},
            {'#','#','#','#','7','9','#','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 2
    public char[][] puzzle2() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','#','8','7','#','#','#','#'},
            {'#','7','9','6','3','4','1','#','#'},
            {'#','1','5','#','#','1','3','#','#'},
            {'#','#','#','#','1','3','#','#','#'},
            {'#','#','#','2','4','#','#','#','#'},
            {'#','#','1','9','#','#','9','2','#'},
            {'#','#','2','5','1','4','7','3','#'},
            {'#','#','#','#','3','9','#','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 3
    public char[][] puzzle3() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','9','8','#','#','#','#','#','#'},
            {'#','1','7','#','4','8','2','#','#'},
            {'#','#','6','8','9','3','7','#','#'},
            {'#','#','#','9','7','#','#','#','#'},
            {'#','#','#','#','6','3','#','#','#'},
            {'#','#','9','5','8','7','2','#','#'},
            {'#','#','8','2','5','#','5','8','#'},
            {'#','#','#','#','#','#','1','5','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 4
    public char[][] puzzle4() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','#','#','3','1','#','#','#'},
            {'#','#','#','#','9','6','#','#','#'},
            {'#','#','8','6','#','5','1','8','#'},
            {'#','#','9','2','8','#','6','9','#'},
            {'#','8','6','#','3','5','9','#','#'},
            {'#','3','7','4','#','3','7','#','#'},
            {'#','#','#','1','6','#','#','#','#'},
            {'#','#','#','3','9','#','#','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 5
    public char[][] puzzle5() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','#','9','8','#','1','5','#'},
            {'#','#','1','6','2','#','5','7','#'},
            {'#','3','4','#','#','3','2','#','#'},
            {'#','9','5','#','7','1','6','#','#'},
            {'#','#','7','6','5','#','8','3','#'},
            {'#','#','9','2','#','#','3','5','#'},
            {'#','9','6','#','5','9','4','#','#'},
            {'#','2','3','#','9','7','#','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 6
    public char[][] puzzle6() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','#','9','6','#','#','#','#'},
            {'#','3','4','1','7','5','2','#','#'},
            {'#','5','8','#','#','9','3','#','#'},
            {'#','#','#','#','9','7','#','#','#'},
            {'#','#','#','3','8','#','#','#','#'},
            {'#','#','4','8','#','#','6','8','#'},
            {'#','#','3','5','1','7','9','2','#'},
            {'#','#','#','#','3','9','#','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 7
    public char[][] puzzle7() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','#','#','6','9','#','#','#'},
            {'#','#','#','8','2','5','#','#','#'},
            {'#','#','4','9','#','6','7','8','#'},
            {'#','#','5','6','#','#','5','7','#'},
            {'#','4','1','#','#','2','4','#','#'},
            {'#','7','2','1','#','6','9','#','#'},
            {'#','#','#','9','2','7','#','#','#'},
            {'#','#','#','8','1','#','#','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 8
    public char[][] puzzle8() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','#','9','5','#','#','#','#'},
            {'#','#','5','7','1','#','#','#','#'},
            {'#','6','8','#','#','6','3','#','#'},
            {'#','8','4','#','7','2','8','#','#'},
            {'#','#','6','1','4','#','9','2','#'},
            {'#','#','9','7','#','#','7','1','#'},
            {'#','#','#','#','5','1','6','#','#'},
            {'#','#','#','#','9','3','#','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 9
    public char[][] puzzle9() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','9','1','#','#','#','#','#'},
            {'#','#','5','2','1','#','#','#','#'},
            {'#','7','8','#','3','7','9','#','#'},
            {'#','1','6','#','#','9','3','#','#'},
            {'#','#','1','5','#','#','6','1','#'},
            {'#','#','4','9','7','#','8','3','#'},
            {'#','#','#','#','9','4','7','#','#'},
            {'#','#','#','#','#','5','1','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

// Ruudukko 10
    public char[][] puzzle10() {
        char[][] p = new char[][] {
            {'#','#','#','#','#','#','#','#','#'},
            {'#','#','7','5','#','#','7','9','#'},
            {'#','#','9','8','1','6','2','4','#'},
            {'#','#','#','6','2','1','#','#','#'},
            {'#','#','5','1','#','2','4','#','#'},
            {'#','#','6','3','#','7','1','#','#'},
            {'#','#','#','7','2','4','#','#','#'},
            {'#','7','1','9','6','3','2','#','#'},
            {'#','9','2','#','#','8','3','#','#'},
            {'#','#','#','#','#','#','#','#','#'}
        };
        return p;
    }

    
    public char[][] puzzlex() {
        int n = 50;
        char[][] t = new char[n][n];
        try {
            Scanner input = new Scanner(new File("laby2.txt"));
            for (int i = 0; i < n; i++) {
                String s = input.next();
                for (int j = 0; j < n; j++) {
                    t[i][j] = s.charAt(j);
                }
            }
        } catch (Exception e) {}
        return t;
    }
    
    
}
