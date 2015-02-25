**TESTIKATTAVUUS:**

-peliä on myös testattu minun ja ystävieni toimesta

**Logic luokka:**
-Logiikkaluokan checkInput() metodia ei voi testata automaattisesti, koska metodin toimiminen perustuu käyttöliittymästä saatuun Input keylistener classiin.

-Samasta syystä update() metodia ei voi kutsua, koska se kutsuu checkInput() metodin.

-CONDITIONALS_BOUNDARY_MUTATORit selviytyvät collision detection metodeissa, koska koordinaatit ovat floatteja, joten raja-arvoja on vaikea testata 

-updateFirespinners(int delta) & updatePatrollingEnemies(int delta) metodit vain kutsuvat arraylistien jokaisen objektin update() metodia, joten en tehnyt niille junit testejä

**player & Firespinner luokka:**
- mutaatiokattavuus on näissä luokissa hieman alhaisempi math mutatorien takia (yleensä vain hidastaa/nopeuttaa asioita esim kiihtyvyyttä) sekä player luokassa conditional mutatorit eivät yleensä edes muuta koodin kulkua.

esimerkki:
if (immunityTimer > immunityTime)
muuttuu
if (immunityTimer >= immunityTime)

**Spike luokka:**
-luokassa on vain 2 konstruktoria, joten en lisännyt sitä pit-test raporttiin.


**BUGIT:**
- ainakin netbeanssista käynnistäessä ruudun liikuttaminen johtaa yleensä pelaajan tippumiseen pelialueen ulkopuolelle