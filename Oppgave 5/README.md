# Oppgave 5

I denne oppgaven skal man koble applikasjonen sammen med databasen.

Dette er faktisk veldig enkelt når databasen og applikasjonen er på samme server.

Gå inn i `app/helloworld/helloworld.yml` og endre passordet til passordet du opprettet for databasen og database tilkoblingen til `url: jdbc:postgresql://localhost/trhdevops`. Dette lar dropwizard koble til databasen.

Ved oppstart vil Dropwizard automatisk forsøke å koble til databasen og vil eventuelt gi en feilmelding hvis den ikke får koblet til ved oppstart.

Når alt er oppe å kjøre kan du benytte 3 endepunkter for å skrive data til databasen. Alle ligger under `http://serverip/database`.

* create - GET: Oppretter en tabell `something` om den ikke finnes.
* insert - POST: Tar imot følgende JSON i BODY `{"status" : "something"}`.
* list - GET: Skriver ut en liste med alt som har blitt skrevet til den ene tabellen.


## gatling
Du kan nå prøve gatling mot din nye applikasjon for å sjekke hvordan ting virker under last. Instruksjoner finnes i `gatling`-mappen sin readme.
