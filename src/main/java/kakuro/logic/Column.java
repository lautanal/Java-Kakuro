package kakuro.logic;
import java.util.ArrayList;

public class Column {
    private int nSquares;
    private int correctSum;
    private int[] numbers;
    private int count;
    private int sum;
    private ArrayList<Square> squareList;

    public Column() {
        this.nSquares = 0;
        this.correctSum = 0;
        this.numbers = new int[10];
        this.count = 0;
        this.sum = 0;
        this.squareList = new ArrayList<>();
    }

    public Column(int nSquares, int correctSum) {
        this.nSquares = nSquares;
        this.correctSum = correctSum;
        this.numbers = new int[10];
        this.count = 0;
        this.sum = 0;
        this.squareList = new ArrayList<>();
    }

// Ruudun lisäys sarakkeeseen
    public void addSquare(Square square) {
        this.nSquares += 1;
        this.correctSum += square.getCorrect();
        squareList.add(square);
        square.setColumn(this);
    }

//  Uuden numeron lisäys sarakesummaan   
    public int addNumber(int number) {
        this.numbers[number] += 1;
        this.count += 1;
        this.sum += number;
        int res = 0;
        if (this.numbers[number] > 1) {
            res = 1;
        }
        if (!this.checkSum()) {
            res += 8;
        }
        return res;
    }

// Numeron muuttaminen    
    public int changeNumber(int oldNumber, int newNumber) {
        this.numbers[oldNumber] -= 1;
        this.numbers[newNumber] += 1;
        this.sum = this.sum - oldNumber + newNumber;
        int res = 0;
        if (this.numbers[newNumber] > 1) {
            res = 1;
        }
        if (!this.checkSum()) {
            res += 8;
        }
        return res;
    }

// Oikea sarakesumma    
    public int getCorrectSum() {
        return correctSum;
    }

// Sarakesumman tarkistus    
    public boolean checkSum() {
        if (this.count == this.nSquares && this.sum != this.correctSum) {
            return false;
        }
        if (this.count < this.nSquares && this.sum >= this.correctSum) {
            return false;
        }
        return true;
    }
    
}
