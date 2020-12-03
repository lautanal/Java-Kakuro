# Käyttöohje

Lataa tiedosto [kakuro.jar](https://github.com/lautanal/ot-harjoitustyo/releases/tag/Viikko5)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar kakuro.jar
```
Pelikentät sijaitsevat puzzle-kansiossa, joka täytyy ladata kakuro.jar -tiedoston lisäksi.

## Käyttöliittymä

Sovellusta käytetään JavaFX:llä luodun käyttöliittymän avulla:

<img src="KakuroUi.png" width="750">

Kakuro-ruudukossa on tyhjiä ruutuja, joihin täytetään numeroita vaaka- ja pystysuoraan käyttäen numeroita 1-9.  Samassa kentässä vaaka- tai pystyrivillä ei ole kahta samaa numeroa. Vaaka- ja pystyrivien lukujen tulee toteuttaa summa, joka on merkitty ruudukkoon.

Sovellusikkunan alaosassa on ruudukko ja yläosassa on numeroiden valinta ja info-tekstin rivi.

Käyttäjä merkitsee jonkin ruudun hiirellä näpäyttämällä ja valitsee sen jälkeen kyseiseen ruutuun tulevan luvun yläreunan numerorivistä.  Jos ruuruun valittu luku ei toteuta ruudun ehtoja (väärä summa tai sama luku kahdesti), saa käyttäjä ilmoituksen virheestä.  Virheelliset numerot muuttuvat punaisiksi.

<img src="KakuroUiError.png" width="750">

Kun käyttäjä saa ruudukon täytettyä oikein, hän saa sovellukselta onnittelut.

<img src="KakuroUiCompleted.png" width="750">

Sovelluksessa on tällä hetkellä 10 pientä ruudukkoa, joista valitaan satunnaisesti joku pelattavaksi.
