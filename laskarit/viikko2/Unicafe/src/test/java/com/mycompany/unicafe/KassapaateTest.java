package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(550);
    }

    @Test
    public void luotuPaateOlemassa() {
        assertTrue(paate!=null);      
    }
    
    @Test
    public void paatteenAlkusaldoOikein() {
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void lounaitaMyytyNolla() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty()+paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syöMaukkaastiToimii() {
        assertEquals(100, paate.syoMaukkaasti(500));
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        assertEquals(300, paate.syoMaukkaasti(300));
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syöEdullisestiToimii() {
        assertEquals(260, paate.syoEdullisesti(500));
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(200, paate.syoEdullisesti(200));
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syöEdullisestiMaksukortilla() {
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(2, paate.edullisiaLounaitaMyyty());
        assertEquals(false, paate.syoEdullisesti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(2, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syöMaukkaastiMaksukortilla() {
        assertEquals(true, paate.syoMaukkaasti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        assertEquals(false, paate.syoMaukkaasti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kortinLatausToimii() {
        int saldo = kortti.saldo();
        paate.lataaRahaaKortille(kortti,500);
        assertEquals(saldo+500, kortti.saldo());
        assertEquals(100500, paate.kassassaRahaa());
        paate.lataaRahaaKortille(kortti,-500);
        assertEquals(100500, paate.kassassaRahaa());
    }

}
