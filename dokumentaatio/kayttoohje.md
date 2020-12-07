# Käyttöohje

Lataa tiedosto [kakuro.jar](https://github.com/lautanal/ot-harjoitustyo/releases/tag/Viikko5) sekä puzzle-kansio, jossa on pelikentät.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar kakuro.jar
```

## Käyttöliittymä

Sovellusta käytetään JavaFX:llä luodun käyttöliittymän avulla :

<img src="KakuroUi.png" width="750">

Kakuro-ruudukossa on tyhjiä ruutuja, joihin täytetään numeroita vaaka- ja pystysuoraan käyttäen numeroita 1-9.  Samassa kentässä vaaka- tai pystyrivillä ei saa olla kahta samaa numeroa. Vaaka- ja pystyrivien lukujen tulee toteuttaa summa, joka on merkitty ruudukkoon.

Sovellusikkunan alaosassa on ruudukko ja yläosassa on numeroiden valinta ja info-tekstin rivi.

Käyttäjä valitsee jonkin ruuduista hiirellä näpäyttämällä ja valitsee sen jälkeen kyseiseen ruutuun sopivan luvun.  Luvun voi valita yläreunan numerorivistä tai näppäimistöltä.  Jos ruuruun valittu luku ei toteuta ruudun ehtoja (väärä summa tai sama luku kahdesti), saa käyttäjä ilmoituksen virheestä.  Virheelliset numerot muuttuvat punaisiksi.

<img src="KakuroUiError.png" width="750">

Kun käyttäjä saa ruudukon täytettyä oikein, hän saa sovellukselta onnittelut.

<img src="KakuroUiCompleted.png" width="750">

Sovelluksessa on tällä hetkellä 10 pientä ruudukkoa, joista valitaan satunnaisesti joku pelattavaksi.  Painamalla 'Uusi peli' -nappia voi aloittaa uuden pelin.
