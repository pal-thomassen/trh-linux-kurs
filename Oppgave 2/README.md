## Oppgave 3

1. Starte en enkel Java-applikasjon på port 8080
2. Proxy applikasjonen gjennom nginx
3. Konfigurere brannmuren til å ikke lytte på port 8080, kun 80 (gjennom Nginx)

Oppgavene blir gått gjennom i detalj nedenfor.

### Intro til applikasjonen

Vi tar utgangspunkt i en enkel hello-world Java applikasjon. For og bygge denne applikasjonen trenger du JDK 1.8 installert. Om du vil bygge lokalt eller på serveren er ett fett.

Steg for å bygge:
1. Klon repoet `git clone https://github.com/pal-thomassen/trh-linux-kurs`
2. `cd trh-linux-kurs/app/helloworld`
3. For å bygge `../gradlew build oneJar`
4. Kjør med `java -jar build/libs/helloworld.jar server helloworld.yml`
5. Applikasjonen skal nå lytte på localhost:8080 og localhost:8081(admin-panel)
6. Test localhost:8080/?name="palt". Skal gi tilbake:
```
{
  id: 1,
  content: "Hello, "palt"!"
}
```

Filene du trenger for å starte applikasjonen er `build/libs/helloworld.jar` og `helloworld.yml`.

Kopier disse filene til serveren din. Hint: Bruk scp(secure copy) som bruker ssh til å kopiere filer.
`scp /path/lokalt/fil brukernavn@server:/path/tjener/fil`

For og (enkelt) kjøre en applikasjon i bakgrunnen på Linux kan du bruke `screen`. Neste oppgave tar for seg mer robust oppsett.

### Nginx konfigurasjon

Nginx lytter nå på port 80 og leverer ut en statisk HTML-side. Vi skal konfigurere nginx til og proxy-trafikk som kommer inn på port 80 og til port 8080 (hvor Java-applikasjonen vår lytter).

For og konfigurere nginx se i filen `/etc/nginx/nginx.conf`. Bruk enten `vim` eller `nano` for og redigere filen. Denne refererer videre til `/etc/nginx/sites-enabled/default`. (Denne måten og konfigurere nginx er Ubuntu-spesifikk).

Etter konfigurasjonsendring må nginx restartes `sudo service nginx restart`. Hvis du får `fail` i terminalen kan du sjekke nginx sin error log. `tail -n 100 /var/log/nginx/error.log`. Man kan også bruke nginx for å sjekke etter syntax-feil i konfigurasjonen `nginx -c /etc/nginx/sites-enabled/default -t`.

Hvis Java-applikasjonen ikke kjører vil du få `Bad gateway - 502` fra Nginx.

Når alt er oppe skal du få samme side som når man startet applikasjonen lokalt. Se intro til applikasjonen

### Brannmur
Hvis du nå går til `serverip/` får du ut samme som overfor. Men nå har vi direkte tilgang til backenden vår via `serverip:8080`, Dette er selvfølgelig ingen krise, men det er vakkert og eksponere slike interne ting. Det er også vanlig og ha beskyttede ressurser bak reverse proxyer slik at ressursen selv ikke trenger og håndtere autorisasjon.

#### Litt brannmur teori

I Linux er det en innebygde brannmur i kernelen. Den har et grensesnitt med kommandoen `iptables` som kan brukes til og lage regler. Ulempen med dette er at det er veldig lavnivå og for de som ikke er råe på nettverk er den veldig kryptisk. Det finnes derfor en rekke abstraksjoner over `iptables` som alle til slutt genererer `iptables` regler. Ubuntu kommer med `ufw` uncomplicated firewall. For vår del vil den være tilstrekkelig for det vi ønsker og oppnå.

Merk at det er kommet et nyere verktøy som skal erstattet `iptables` kalt `nftables`. For vårt kurs trenger man ikke å forholde seg til noen av delene.

Følgende regler skal vi ha i brannmuren vår:

Tillat-innkommende:
22(TCP)
80(TCP)
Blokker alt annet

Tillatt all utgående trafikk.

Hint: `sudo ufw help` skriver ut en meget fin hjelpemeny.

Når brannmur reglene er på plass skal `serverip:8080` ikke svare. I nettleseren vil det være at den henger i ett minutt før den får TCP-timeout. Dette skyldes at brannmuren ikke svarer i det hele tatt (dropper TCP-pakken) framfor og sende et blokkert svar. I brannmursammenheng kalles dette ofte for stealth.

###### Bonus:

Om du vil teste brannmur blokkering vs ikke svar kan du bruke `sudo ufw reject <port>` for å blokkere porter. For å teste fra en klient kan du bruke netcat `nc`. `nc -vz <ipadresse> <port>`.

Eksempel på blokkert port:
```
ubuntu@ip-172-31-30-125:/etc/nginx$ nc -vz 54.229.210.40 8080
nc: connect to 54.229.210.40 port 8080 (tcp) failed: Connection refused
```

Eksempel på ikke blokkert:
```
ubuntu@ip-172-31-30-125:/etc/nginx$ nc -vz 54.229.210.40 80
Connection to 54.229.210.40 80 port [tcp/http] succeeded!
```

Eksempel på stealth:
```
ubuntu@ip-172-31-30-125:/etc/nginx$ nc -vz 54.229.210.40 8080
nc: connect to 54.229.210.40 port 8080 (tcp) failed: Connection timed out
```
