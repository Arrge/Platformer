# Aihemäärittely

**Aihe:** Toteutetaan 2D tasohyppelypeli. Pelissä ohjaat pelihahmoa hyppimään erilaisten tasojen päälle ja väistelet piikkejä, pyöriviä tuliseiniä, liikkuvia monstereita ja yrität päästä kentän loppuun.

**käyttäjät:** 
- pelaaja

**pelaajan toiminnot:**
- liiku
- hyppää

**ominaisuuksia:**
- tiled mapeditorin tuki
- itsetehdyt grafiikat
- liikkuvia vihollisia
- pyöriviä tuliseiniä
- piikkejä
- "vihollispomo"

**rakenne:**
Entity-luokka on taso, jolla ei ole mitään jännittäviä mekaniikkoja ja 
Spike, Player, Boss ja Patrolling Enemy laajentavat tätä luokkaa. Entity-luokka ja Entity-luokkaa laajentavat luokat implementoivat Collidable rajapinnan.

Firespinner-luokka sisältää 3 kpl tulipallo spritellä höystettyä Spikeä ja pyörittää niitä.

Logiikka-luokka kontrolloi näitä muita luokkia ja Game-luokka (graafinen käyttöliittymä) piirtää kaiken logiikka-luokan laskemiin paikkoihin ja hakee Spritehandlerilta Spritet

Spritehandler-luokka lukee tiled map editorin .tmx tiedoston ja jakaa objektit omiin kategorioihinsa