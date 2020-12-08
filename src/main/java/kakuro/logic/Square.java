package kakuro.logic;

    /**
    * Ruudun logiikasta vastaava luokka
    *
    */
public class Square {
    private Row row;
    private Column column;
    private int number;
    private int correct;

    public Square(int correct) {
        this.number = 0;
        this.correct = correct;
    }

    /**
    * Ruudun rivin hakeminen
    *
    */
    public Row getRow() {
        return this.row;
    }

    /**
    * Ruudun rivin merkitseminen
    *
    */
    public void setRow(Row row) {
        this.row = row;
    }

    /**
    * Ruudun sarakkeen hakeminen
    *
    */
    public Column getColumn() {
        return this.column;
    }

    /**
    * Ruudun sarakkeen merkitseminen
    *
    */
    public void setColumn(Column column) {
        this.column = column;
    }

    /**
    * Ruudun numeron muuttaminen
    *
    */
    public int setNumber(int number) {
        int oldNumber = this.number;
        this.number = number;
        int res = this.row.changeNumber(oldNumber, number);
        return res + this.column.changeNumber(oldNumber, number);
    }

    /**
    * Ruudussa olevan numeron haku
    *
    */
    public int getNumber() {
        return number;
    }

    /**
    * Ruudun oikea tulos
    *
    */
    public int getCorrect() {
        return this.correct;
    }

    /**
    * Tarkistetaan, onko ruudun numero oikein
    *
    */
    public boolean checkCorrect() {
        if (this.number == this.correct) {
            return true;
        }
        return false;
    }
}
