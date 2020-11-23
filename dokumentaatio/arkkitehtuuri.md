
# Arkkitehtuurikuvaus

## Rakenne

Ohjelman koodin pakkausrakenne on seuraava:

<img src="package.png" width="750">

Pakkaus _todoapp.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _todoapp.domain_ sovelluslogiikan ja _todoapp.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä on rakennettu ohjelmallisesti luokassa _kakuro.ui.KakuroUi_ .  Käyttöliittymä on toistaiseksi tekstikäyttöliittymä, mutta toteutetaan jatkossa JavaFX-kirjaston avulla graafisena käyttöliittymänä.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sovelluslogiikan toteuttavan _Puzzle_-luokan olion _puzzle_ metodeja.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat _Puzzle_, _Square_, _Row_, _Column_ ja _Map_.

<img src="class.png" width="750">
