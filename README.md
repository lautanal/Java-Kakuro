# Kakuro

Sovellus on japanilainen Kakuro-numeroristikko.  Ruudukossa on tyhjiä ruutuja, jotka täytetään käyttäen numeroita 1-9 siten, että samassa kentässä vaaka- tai pystyrivillä ei ole kahta samaa numeroa. Vaaka- ja pystyrivien lukujen tulee toteuttaa summa, joka on merkitty ruudukkoon.

<img src="dokumentaatio/png/KakuroUi.png" width="750">

## Dokumentaatio

[Käyttöohje](https://github.com/lautanal/ot-harjoitustyo//blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/lautanal/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/lautanal/ot-harjoitustyo//blob/master/dokumentaatio/arkkitehtuuri.md)

[Testaus](https://github.com/lautanal/ot-harjoitustyo//blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/lautanal/ot-harjoitustyo//blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Loppupalautus](https://github.com/lautanal/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn test jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Kakuro-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/lautanal/ot-harjoitustyo/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
