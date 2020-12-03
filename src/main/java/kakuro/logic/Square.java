package kakuro.logic;

public class Square {
    private Row row;
    private Column column;
    private int number;
    private int correct;

    public Square(int correct) {
        this.number = 0;
        this.correct = correct;
    }

    public Row getRow() {
// Ruudun rivin hakeminen
        return this.row;
    }

    public void setRow(Row row) {
// Ruudun rivin merkitseminen
        this.row = row;
    }

    public Column getColumn() {
// Ruudun sarakkeen hakeminen
        return this.column;
    }

    public void setColumn(Column column) {
// Ruudun sarakkeen merkitseminen
        this.column = column;
    }

    public int setNumber(int number) {
// Ruudun numeron asettaminen
        if (this.number == 0) {
            this.number = number;
            int res = this.row.addNumber(number);
            return res + this.column.addNumber(number);
        } else {
            int oldNumber = this.number;
            this.number = number;
            int res = this.row.changeNumber(oldNumber, number);
            return res + this.column.changeNumber(oldNumber, number);
        }
    }

    public int zeroNumber() {
// Ruudun numeron nollaus
        if (this.number == 0) {
            return 0;
        } else {
            int oldNumber = this.number;
            this.number = 0;
            int res = this.row.zeroNumber(oldNumber);
            return res + this.column.zeroNumber(oldNumber);
        }
    }

    public int getNumber() {
        return number;
    }

    public int getCorrect() {
// Oikea tulos
        return this.correct;
    }

    public boolean checkCorrect() {
// Tarkistetaan, onko ruudun numero oikein
        if (this.number == this.correct) {
            return true;
        }
        return false;
    }
}
