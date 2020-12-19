# Käyttöohje

Lataa sovellusen [release](https://github.com/lautanal/ot-harjoitustyo/releases/tag/loppupalautus).  Pelaamiseen tarvitset jar-tiedoston sekä puzzle-kansio, jossa on pelattavat ruudukot.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar kakuro.jar
```

## Käyttöliittymä

Sovellusta käytetään JavaFX:llä luodun käyttöliittymän avulla :


<img src="png/KakuroUi.png" width="750">


Kakuro-ruudukossa on tyhjiä ruutuja, jotka täytetään käyttäen numeroita 1-9.  Samassa kentässä vaaka- tai pystyrivillä ei saa olla kahta samaa numeroa. Vaaka- ja pystyrivien lukujen tulee toteuttaa summa, joka on merkitty ruudukkoon.

Sovellusikkunan keskellä on varsinainen Kakuro-ruudukko.   Kakuro-ruudukon yläpuolella on numeroiden valintarivi ja alhaalla on info-alue.

Käyttäjä valitsee jonkin Kakuro-ruudukon ruuduista hiirellä näpäyttämällä.   Sen jälkeen hän syöttää kyseiseen ruutuun sopivan luvun.  Luvun voi valita yläreunan numerorivistä tai painamalla näppäimistöltä.  

Jos ruutuun valittu luku ei toteuta ruudun ehtoja (väärä summa tai sama luku kahdesti), käyttäjä saa ilmoituksen virheestä.  Virheelliset numerot muuttuvat punaisiksi.  Virheellisen numeron saa poistettua painamalla X-nappia tai valitsemalla 0 näppäimistöltä.


<img src="png/KakuroUiError.png" width="750">


Kun käyttäjä saa ruudukon täytettyä oikein, hän saa sovellukselta onnittelut.


<img src="png/KakuroUiCompleted.png" width="750">


Sovelluksessa on tällä hetkellä 30 pientä ruudukkoa, jotka on jaettu kolmelle eri vaikeustasolle.  Pelaaja aloittaa tasolta 1, jossa valittavana on kymmenen eri peliruudukkoa.  Kun pelaaja on ratkaissut oikein kaikki tason kymmenen ruudukkoa, pääsee hän seuraavalle tasolle.

Painamalla 'Uusi peli' -nappia voi aloittaa seuraavan pelin.  Ruudukon numero, taso, nopein suoritus ja kulunut aika näkyvät pelialueen alallaidassa. 

Kun ruudukko on oikein ratkaistu, käyttäjä näkee kulutetun ajan ja saa onnittelut.  Peli tallettaa kunkin ruudukon parhaan tuloksen tiedostoon.
