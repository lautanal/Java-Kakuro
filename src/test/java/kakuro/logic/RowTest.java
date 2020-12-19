package kakuro.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lasselautanala
 */
public class RowTest {

    Square sq1, sq2, sq3, sq4, sq5;
    Row r1, r2;
    Column c1, c2;
    
    @Before
    public void setUp() {
        sq1 = new Square(8);
        sq2 = new Square(9);
        sq3 = new Square(6);
        sq4 = new Square(5);
        sq5 = new Square(7);
        r1 = new Row();
        r1.addSquare(sq1);
        sq1.setRow(r1);
        r1.addSquare(sq2);
        sq2.setRow(r1);
        c1 = new Column();
        c1.addSquare(sq1);
        sq1.setColumn(c1);
        c1.addSquare(sq3);
        sq3.setColumn(c1);
        c2 = new Column();
        c2.addSquare(sq2);
        sq2.setColumn(c2);
        c2.addSquare(sq4);
        sq4.setColumn(c2);
        r2 = new Row();
        r2.addSquare(sq3);
        sq3.setRow(r2);
        r2.addSquare(sq5);
        sq5.setRow(r2);
    }

    @Test
    public void luotuRuutuOlemassa() {
        assertTrue(sq1!=null);      
        assertTrue(sq2!=null);      
        assertTrue(sq3!=null);      
   }

    
    @Test
    public void ruudunOikeaTulos() {
        sq1.setNumber(8);
        sq2.setNumber(8);
        assertEquals(8, sq1.getCorrect());
        assertEquals(9, sq2.getCorrect());
        assertEquals(true, sq1.checkCorrect());
        assertEquals(false, sq2.checkCorrect());
    }
    
    @Test
    public void rivinOikeaSumma() {
        assertEquals(17, r1.getCorrectSum());
    }
    
    @Test
    public void sarakkeenOikeaSumma() {
        assertEquals(14, c1.getCorrectSum());
    }

    
    @Test
    public void ruudunNumeronTarkistus() {
        sq1.setNumber(8);
        sq2.setNumber(7);
        assertEquals(8, sq1.getNumber());
        assertEquals(7, sq2.getNumber());
    }
    
    @Test
    public void rivinSarakkeenSummanTarkistus() {
        sq1.setNumber(8);
        sq2.setNumber(7);
        assertEquals(false, r1.checkSum());
        sq2.setNumber(9);
        assertEquals(true, r1.checkSum());
        sq3.setNumber(3);
        assertEquals(false, c1.checkSum());
        sq3.setNumber(6);
        assertEquals(true, c1.checkSum());
    }
    
    @Test
    public void samaNumeroTarkistus() {
        sq1.setNumber(8);
        sq2.setNumber(8);
        assertEquals(false, r1.checkSame());
        sq2.setNumber(9);
        assertEquals(true, r1.checkSame());
        sq3.setNumber(8);
        assertEquals(false, c1.checkSame());
        sq3.setNumber(6);
        assertEquals(true, c1.checkSame());
    }
    
    
    @Test
    public void pelikentanTarkistus() {
        Puzzle pz1 = new Puzzle(1);
        assertEquals(false, pz1.checkSquare(2,1));
        assertEquals(true, pz1.checkSquare(3,1));
        assertEquals(0, pz1.setSquare(1,3,9));
        assertEquals(0, pz1.setSquare(1,4,5));
        assertEquals(0, pz1.setSquare(2,2,4));
        assertEquals(0, pz1.setSquare(2,3,3));
        assertEquals(0, pz1.setSquare(2,4,2));
        assertEquals(0, pz1.setSquare(3,1,9));
        assertEquals(0, pz1.setSquare(3,2,3));
        assertEquals(0, pz1.setSquare(3,5,3));
        assertEquals(0, pz1.setSquare(3,6,8));
        assertEquals(0, pz1.setSquare(4,1,7));
        assertEquals(0, pz1.setSquare(4,2,6));
        assertEquals(0, pz1.setSquare(4,4,3));
        assertEquals(0, pz1.setSquare(4,5,1));
        assertEquals(0, pz1.setSquare(4,6,7));
        assertEquals(0, pz1.setSquare(5,2,1));
        assertEquals(0, pz1.setSquare(5,3,3));
        assertEquals(0, pz1.setSquare(5,4,4));
        assertEquals(0, pz1.setSquare(5,6,2));
        assertEquals(0, pz1.setSquare(5,7,6));
        assertEquals(0, pz1.setSquare(6,2,7));
        assertEquals(0, pz1.setSquare(6,3,5));
        assertEquals(0, pz1.setSquare(6,6,3));
        assertEquals(0, pz1.setSquare(6,7,9));
        assertEquals(0, pz1.setSquare(7,4,5));
        assertEquals(0, pz1.setSquare(7,5,8));
        assertEquals(0, pz1.setSquare(7,6,1));
        assertEquals(0, pz1.setSquare(8,4,7));
        assertEquals(0, pz1.setSquare(8,5,9));
        assertEquals(true, pz1.checkCompleted());
    }

}
