package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinAlkusaldoOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaminenKasvattaaSalsoaOikein() {
        kortti.lataaRahaa(2510);
        assertEquals("saldo: 25.20", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.lataaRahaa(2510);
        kortti.otaRahaa(1320);
        assertEquals("saldo: 12.0", kortti.toString());
    }

    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.lataaRahaa(2510);
        kortti.otaRahaa(3000);
        assertEquals("saldo: 25.20", kortti.toString());
    }

    @Test
    public void palauttaaTrueJosRahatRiittaa() {
        kortti.lataaRahaa(2510);
        assertEquals(true, kortti.otaRahaa(2500));
    }

    @Test
    public void palauttaaFalseJosRahatEiRiita() {
        kortti.lataaRahaa(2510);
        assertEquals(false, kortti.otaRahaa(2530));
    }
}
