**TESTIKATTAVUUS:**

-peli� on my�s testattu minun ja yst�vieni toimesta

**Logic luokka:**
-Logiikkaluokan checkInput() metodia ei voi testata automaattisesti, koska metodin toimiminen perustuu k�ytt�liittym�st� saatuun Input keylistener classiin.

-Samasta syyst� update() metodia ei voi kutsua, koska se kutsuu checkInput() metodin.

-CONDITIONALS_BOUNDARY_MUTATORit selviytyv�t collision detection metodeissa, koska koordinaatit ovat floatteja, joten raja-arvoja on vaikea testata 

-updateFirespinners(int delta) & updatePatrollingEnemies(int delta) metodit vain kutsuvat arraylistien jokaisen objektin update() metodia, joten en tehnyt niille junit testej�

**player & Firespinner luokka:**
- mutaatiokattavuus on n�iss� luokissa hieman alhaisempi math mutatorien takia (yleens� vain hidastaa/nopeuttaa asioita esim kiihtyvyytt�) sek� player luokassa conditional mutatorit eiv�t yleens� edes muuta koodin kulkua.

esimerkki:
if (immunityTimer > immunityTime)
muuttuu
if (immunityTimer >= immunityTime)

**Spike luokka:**
-luokassa on vain 2 konstruktoria, joten en lis�nnyt sit� pit-test raporttiin.


**BUGIT:**
- ainakin netbeanssista k�ynnist�ess� ruudun liikuttaminen johtaa yleens� pelaajan tippumiseen pelialueen ulkopuolelle