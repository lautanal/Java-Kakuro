# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on japanilainen Kakuro-numeroristikko.  Ruudukossa on tyhjiä ruutuja, joihin täytetään numeroita vaaka- ja pystysuoraan käyttäen numeroita 1-9, siten, että samalla vaaka- tai pystyrivillä ei ole kahta samaa numeroa.  Vaakarivin vasemmalla puolella ja pystyrivin yläpuolella on merkitty summa, joka rivin lukujen tulee toteuttaa.

## Käyttäjät

Sovelluksella on alkuvaiheessa vain yksi käyttäjä eli pelaaja.

## Käyttöliittymä

Sovellus koostuu yhdestä näkymästä eli pelilaudasta.

Käyttö on suoraviivaista.  Käyttäjä valitsee ruudun ja antaa haluamansa numeron, joka ruutuun laitetaan. 

Jos käyttäjän antama numero ei toteuta ruudun ehtoja, numero muuttuu punaiseksi.

Kun ruudukko on täynnä, käyttäjä näkee kulutetun ajan ja saa onnittelut.  Parhaat tulokset talletetaan tiedostoon.

<img src="png/KakuroUi.png" width="750">

## Perusversion tarjoama toiminnallisuus

Sovelluksessa on tällä hetkellä 30 pientä ruudukkoa, jotka on jaettu kolmelle eri vaikeustasolle.

Pelaaja aloittaa tasolta 1, jossa valittavana on kymmenen eri peliruudukkoa.  Kun pelaaja on ratkaissut oikein kaikki tason kymmenen ruudukkoa, pääsee hän seuraavalle tasolle.

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- Käyttäjien rekisteröinti
- Suorituksen tulokset voidaan tallettaa käyttäjäkohtaisesti
- Käyttäjä voi merkitä ruutuun muistiin mahdolliset numerovaihtoehdot
- Kenttien automaattinen generointi
