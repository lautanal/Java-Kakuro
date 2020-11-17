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

    public int getCorrect() {
// Oikea tulos
        return this.correct;
    }

    public Row getRow() {
// Ruudun rivialkion hakeminen
        return this.row;
    }

    public void setRow(Row row) {
// Ruudun rivialkion merkitseminen
        this.row = row;
    }

    public Column getColumn() {
// Ruudun sarakealkion hakeminen
        return this.column;
    }

    public void setColumn(Column column) {
// Ruudun sarakealkion merkitseminen
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

    public int getNumber() {
        return number;
    }

    
    public boolean checkCorrect() {
// Tarkistetaan, onko ruudun numero oikein
        if (this.number == this.correct) {
            return true;
        }
        return false;
    }
}
