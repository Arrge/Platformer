**TESTIKATTAVUUS:**

- peliä on myös testattu manuaalisesti minun ja ystävieni toimesta

**Logic luokka:**
- Logiikkaluokan checkInput() metodia ei voi testata automaattisesti, koska metodin toimiminen perustuu käyttöliittymästä saatuun Input keylistener classiin.

- Samasta syystä update() metodia ei voi kutsua, koska se kutsuu checkInput() metodin.

- CONDITIONALS_BOUNDARY_MUTATORit selviytyvät collision detection metodeissa, koska koordinaatit ovat floatteja, joten raja-arvoja on vaikea testata 

- updateFirespinners(int delta) & updatePatrollingEnemies(int delta) metodit vain kutsuvat arraylistien jokaisen objektin update() metodia, joten en tehnyt niille junit testejä

**player & Firespinner luokka:**
- mutaatiokattavuus on näissä luokissa hieman alhaisempi math mutatorien takia (yleensä vain hidastaa/nopeuttaa asioita esim kiihtyvyyttä) sekä player luokassa conditional mutatorit eivät yleensä edes muuta koodin kulkua.

esimerkki:
<code>if (immunityTimer > immunityTime)</code>
muuttuu
<code>if (immunityTimer >= immunityTime)</code>

**Spike luokka:**
- luokassa on vain 2 konstruktoria, joten en lisännyt sitä pit-test raporttiin.


**BUGIT:**
- ainakin netbeanssista käynnistäessä ruudun liikuttaminen johtaa yleensä pelaajan tippumiseen pelialueen ulkopuolelle
- en voi laittaa packageja alkamaan pienellä kirjaimella, koska github ei ota kirjainkokoa huomioon puskiessa ja tuhoaa koko projektipuun
- peli herjaa linuxilla javaws.jar tiedoston puuttumista jdk:sta, joten oli pakko lisätä se suoraan projektikansioon vaikka maveni siitä valittaakin
- executable jar ei vain toimi linuxilla vaikka mitä tekisi, joten linuxilla täytyy ladata .tar muotoinen release ja unpackata se ja käynnistää game.sh tiedostosta
- shaded ei osaa pakata slick2d kirjastoja oikein
