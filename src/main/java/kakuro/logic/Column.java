package kakuro.logic;

import java.util.ArrayList;

    /**
    * Pystysarakkeen logiikasta vastaava luokka
    *
    */
public class Column {
    private int xColumn;
    private int yStart;
    private int nSquares;
    private int correctSum;
    private int[] numbers;
    private int count;
    private int sum;

    /**
    * Konstruktori
    *
    */
    public Column() {
        this.nSquares = 0;
        this.correctSum = 0;
        this.numbers = new int[10];
        this.count = 0;
        this.sum = 0;
    }
    
    /**
    * Sarakkeen alun paikan talletus
    *
    */
    public void setColumnStart(int xColumn, int yStart) {
        this.xColumn = xColumn;
        this.yStart = yStart;
    }

    /**
    * Ruudun lisäys sarakkeeseen
    *
    */
    public void addSquare(Square square) {
        this.nSquares += 1;
        this.correctSum += square.getCorrect();
//        squareList.add(square);
//        square.setColumn(this);
    }


    /**
    * Numeron lisäys sarakkeeseen
    *
    */
    public int changeNumber(int oldNumber, int newNumber) {
        if (newNumber != oldNumber) {
            if (oldNumber == 0) {
                this.numbers[newNumber] += 1;
                this.count += 1;
                this.sum += newNumber;
            } else if (newNumber == 0) {
                this.numbers[oldNumber] -= 1;
                this.count -= 1;
                this.sum -= oldNumber;
            } else {
                this.numbers[oldNumber] -= 1;
                this.numbers[newNumber] += 1;
                this.sum = this.sum - oldNumber + newNumber;
            }
        } 
        int res = 0;
        if (!this.checkSame()) {
            res = 2;
        }
        if (!this.checkSum()) {
            res += 8;
        }
        return res;
    }
    
    /**
    * Sarakkeen alun y-koordinaatti
    *
    */
    public int getStart() {
        return this.yStart;
    }
    
    /**
    * Sarakkeen ruutujen lukumäärä
    *
    */
    public int getSquares() {
        return this.nSquares;
    }

    /**
    * Oikea sarakesumma
    *
    */
    public int getCorrectSum() {
        return correctSum;
    }

    /**
    * Sarakesumman tarkistus
    *
    */
    public boolean checkSum() {
        if (this.count == this.nSquares && this.sum != this.correctSum) {
            return false;
        }
        if (this.count < this.nSquares && this.sum >= this.correctSum) {
            return false;
        }
        return true;
    }

    /**
    * Tarkistetaan, ettei sarakkeessa ole samaa numeroa kahdesti
    *
    */
    public boolean checkSame() {
        for (int i = 1; i <= 9; i++) {
            if (this.numbers[i] > 1) {
                return false;
            }
        }
        return true;
    }
    
}
