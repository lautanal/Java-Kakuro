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
    * Ruudun sarakkeen asettaminen
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

    /**
    * Ruudun numeron pyyhkiminen pois
    *
    */
    public int zeroNumber() {
        if (this.number == 0) {
            return 0;
        } else {
            int oldNumber = this.number;
            this.number = 0;
            int res = this.row.zeroNumber(oldNumber);
            return res + this.column.zeroNumber(oldNumber);
        }
    }

    /**
    * Ruudussa oleva numero
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
