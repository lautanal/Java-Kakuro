package kakuro.logic;

import java.util.ArrayList;

public class Row {
    private int nSquares;
    private int correctSum;
    private int[] numbers;
    private int count;
    private int sum;
    private ArrayList<Square> squareList;

    public Row() {
        this.nSquares = 0;
        this.correctSum = 0;
        this.numbers = new int[10];
        this.count = 0;
        this.sum = 0;
        this.squareList = new ArrayList<>();
    }

    public Row(int nSquares, int correctSum) {
        this.nSquares = nSquares;
        this.correctSum = correctSum;
        this.numbers = new int[10];
        this.count = 0;
        this.sum = 0;
        this.squareList = new ArrayList<>();
    }

// Ruudun lisäys riviin
    public void addSquare(Square square) {
        this.nSquares += 1;
        this.correctSum += square.getCorrect();
        squareList.add(square);
        square.setRow(this);
    }

// Uuden numeron talletus rivisummaan (uusi numero)  
    public int addNumber(int number) {
        this.numbers[number] += 1;
        this.count += 1;
        this.sum += number;
        int res = 0;
        if (!this.checkSame()) {
            res = 1;
        }
        if (!this.checkSum()) {
            res += 4;
        }
        return res;
    }

// Ruudun numeron vaihtaminen    
    public int changeNumber(int oldNumber, int newNumber) {
        this.numbers[oldNumber] -= 1;
        this.numbers[newNumber] += 1;
        this.sum = this.sum - oldNumber + newNumber;
        int res = 0;
        if (!this.checkSame()) {
            res = 1;
        }
        if (!this.checkSum()) {
            res += 4;
        }
        return res;
    }

// Ruudun numeron nollaus    
    public int zeroNumber(int oldNumber) {
        this.numbers[oldNumber] -= 1;
        this.count -= 1;
        this.sum = this.sum - oldNumber;
        int res = 0;
        if (!this.checkSame()) {
            res = 1;
        }
        if (!this.checkSum()) {
            res += 4;
        }
        return res;
    }
    
// Oikea rivisumma
    public int getCorrectSum() {
        return this.correctSum;
    }

// Rivisumman tarkistus
    public boolean checkSum() {
        if (this.count == this.nSquares && this.sum != this.correctSum) {
            return false;
        }
        if (this.count < this.nSquares && this.sum >= this.correctSum) {
            return false;
        }
        return true;
    }

// Saman numeron tarkistus    
    public boolean checkSame() {
        for (int i=1; i <= 9; i++) {
            if (this.numbers[i] > 1) {
                return false;
            }
        }
        return true;
    }
    
}
