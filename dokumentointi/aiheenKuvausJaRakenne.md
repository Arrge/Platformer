# Aihem��rittely

**Aihe:** Toteutetaan 2D tasohyppelypeli. Peliss� ohjaat pelihahmoa hyppim��n erilaisten tasojen p��lle ja v�istelet piikkej�, py�rivi� tuliseini�, liikkuvia monstereita ja yrit�t p��st� kent�n loppuun.

**k�ytt�j�t:** 
- pelaaja

**pelaajan toiminnot:**
- liiku
- hypp��

**ominaisuuksia:**
- tiled mapeditorin tuki
- itsetehdyt grafiikat
- liikkuvia vihollisia
- py�rivi� tuliseini�
- piikkej�
- "vihollispomo"

**rakenne:**
Entity-luokka on taso, jolla ei ole mit��n j�nnitt�vi� mekaniikkoja ja 
Spike, Player, Boss ja Patrolling Enemy laajentavat t�t� luokkaa. Entity-luokka ja Entity-luokkaa laajentavat luokat implementoivat Collidable rajapinnan.

Firespinner-luokka sis�lt�� 3 kpl tulipallo spritell� h�ystetty� Spike� ja py�ritt�� niit�.

Logiikka-luokka kontrolloi n�it� muita luokkia ja Game-luokka (graafinen k�ytt�liittym�) piirt�� kaiken logiikka-luokan laskemiin paikkoihin ja hakee Spritehandlerilta Spritet

Spritehandler-luokka lukee tiled map editorin .tmx tiedoston ja jakaa objektit omiin kategorioihinsa