# Oppgave 2

I oppgave 2 skal vi installere postgresql og konfigurere applikasjonen vår til å koble til en database.

Det enkleste er og installere postgresql via pakkesystemet i Ubuntu. Pakken heter `postgresql-9.3`.

## Konfigurere postgres

Når postgres er installert må man opprette en database og sjekke innstillinger for tilkoblinger til denne. Som nevnt tidligere så blir det opprettet en egen bruker som kjører databasen. Brukeren i dette tilfellet har navnet `postgres`. For å opprette en database og kjøre SQL bruker man denne brukeren. For å skifte til `postgres`-brukeren i shellet

`sudo su - postgres`

Kommandoen `psql` lar deg koble til det lokale postgres databasesystemet(cluster i postgresterminologi).

Når du er inne i postgres-shellet kan du kjøre f.eks `\l`-Kommandoen som lister ut databaser som er installert. Postgres på Ubuntu kommer med 3-databaser. En med navn `postgres`, `template0` og `template1`.

Før vi oppretter en egen database bør vi lage en ny bruker som har tilgang til denne. I kurset velger vi å opprette en bruker med navnet `trhdevops`.

For å opprette en bruker brukes `createuser` kommandoen til postgres, denne kjører i vanlig `bash`-shell. La `trhdevops`-brukeren være superbruker av databasen (kan gjøre _alt_).

Når brukeren er opprettet må vi sette ett passord. Logg inn i psql-shellet `psql`.
Får å sette passord `\password $USERNAME`.

Når bruker er opprettet kan vi lage selve databasen. Da bruker vi `createdb`-kommandoen til postgres, denne kjører i vanlig `bash`-shell. For å gjøre dette enkelt lager vi en database med samme navn som brukeren vår, `trhdevops`.

Hvis vi nå prøver å logge inn på databasen med `psql trhdevops trhdevops` -W og skriver inn passordet får vi feilmeldingen `psql: FATAL:  Peer authentication failed for user "trhdevops"`. Dette er fordi default i Ubuntu så vil ikke postgres bruke vanlig passord autentisering, men kun såkalt peer authentication.

## Konfigurere tilkoblinger
Som alle andre applikasjoner så ligger konfigurasjonen til postgres-installasjonen i `etc`-mappen.

For å konfigurere tilkoblinger så åpner du opp `/etc/postgresql/9.3/main/pg_hba.conf`. Nederst i denne filen finner man hvilke porter postgres lytter på og hvilken autentisering som er lovlig for de ulike portene.

For å gjøre ting enkelt. Kommenter ut `#local all all peer` linjen og legg til `local trhdevops trhdevops md5` istedenfor. Dette gjør at tilkoblinger fra unix-socket (shellet) bruker md5, altså passord for autentisering. Legg merke til at IPv4 og IPv6-trafikk så lytter postgres default på localhost med md5 for autentisering. Det er dette vi skal bruke i vår Java-applikasjon for å koble til.

For hver endring du gjør i konfigurasjonensfilen må man restarte Postgresql tjenesten `sudo service postgresql restart`

Når dette er på plass kan du teste tilkoblinger med `psql trhdevops trhdevops -w`, skriv inn passordet og du skal være logget inn i postgresql databasen `trhdevops`.
