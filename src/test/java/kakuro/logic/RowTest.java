/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    Square sq1, sq2, sq3, sq4;
    Row r1;
    Column c1, c2;
    
    @Before
    public void setUp() {
        sq1 = new Square(8);
        sq2 = new Square(9);
        sq3 = new Square(6);
        sq4 = new Square(5);
        r1 = new Row();
        r1.addSquare(sq1);
        r1.addSquare(sq2);
        c1 = new Column();
        c1.addSquare(sq1);
        c1.addSquare(sq3);
        c2 = new Column();
        c2.addSquare(sq2);
        c2.addSquare(sq4);
    }

    @Test
    public void luotuRuutuOlemassa() {
        assertTrue(sq1!=null);      
        assertTrue(sq2!=null);      
        assertTrue(sq3!=null);      
   }
    
    @Test
    public void ruudunOikeaTulos() {
        assertEquals(8, sq1.getCorrect());
        assertEquals(9, sq2.getCorrect());
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
    public void rivinSummanTarkistus() {
        sq1.setNumber(8);
        sq2.setNumber(7);
        assertEquals(false, r1.checkSum());
        sq2.setNumber(9);
        assertEquals(true, r1.checkSum());
    }
    
}
