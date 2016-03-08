# Filsystem

I Unix er alt lagt i et kataloghierarki. Fordelen med dette er at alt av files
av samme type ligger i samme katalog. Slik at all konfigurasjon finnes i `/etc` f.eks.

Nedenfor er en liste over de viktigste lokasjonene man må forholde seg til.

|Katalog|Beskrivelse|
|-------|-----------|
|/bin   |Applikasjoner som er tilgjenglig i `single-user-mode`. f.eks `ls` og `cat`|
|/etc   |Konfigurasjonssfiler. Typisk ligger det en rekke kataloger for hver applikasjon, f.eks `apache`|
|/home |Brukerenes hjemmemapper ligger inni her. eks `/home/palt`|
|/opt   |Pakker som er optional. Typisk legger man pakker man installerer manuelt her.|
|/proc  | Virtuelt filsystem i Linux med kjøretidsinformasjon for kernelen. `cat /proc/cpuinfo`|
|/root  |Hjemmemappe for `root`-brukeren|
|/tmp   |Midlertidige filer, trenger ikke overleve en reboot.|
|/usr   |Sekundær hierarki. Inneholder applikasjoner og biblioteker for de fleste brukerne. eks `/usr/bin`|
|/var   | Variable filer som er ment å vokse under normal kjøring. F.eks `/var/logs` som inneholder logger.

For en full oversikt se https://www.wikiwand.com/en/Filesystem_Hierarchy_Standard

# Pakker
I linux er alt delt opp i pakker. En pakke er ganske løst definiert og format varierer fra distro til distro. Generelt kan man si at at en pakke inneholder et bibliotek, applikasjon eller kildekode. Tanken i Linux er at man setter sammen en pakke ved at den lister opp sine avhengigheter til andre pakker. Et eksempel er at Apache-tomcat er avhengig av Java-pakken.

## Pakkeformater
Generelt er det to store formater, `deb`-Debian Package og `rpm`-Red Hat Package Management. For vår del er forskjellene ikke så viktige, men man bruker ulike verktøy for å installere pakker. I dette kurset tar vi utgangspunkt i Deb som brukes på Ubuntu og Debian.

## Deb
På `deb`-baserte pakkesystem bruker man `apt` - advanced package tool og varianter `aptitude` for å installere og fjerne pakker. For dette kurset bruker vi Ubuntu som baserer seg på `deb`-pakker.

Eksempel på installasjon av Vim `aptitude install vim`

# Brukere
I Unix har du normale brukere og `root`-brukeren. Root-brukeren er såkalt superbruker (administrator) og har tilgang til å gjøre alt. Siden det er skummelt å kjøre prosesser som denne brukeren fordi den har rettigheter til alt er det vanlig å la prosesser kjøres som vanlige brukere. Det er vanlig å opprette brukere til brukerne (pal, emil, stian, klakken), men også til proseser (nginx, apache, postgres).

## Hjemmemappe
Alle brukere har sin egen hjemmemappe under /home/<username>
Denne mappen inneholder personlige filer + personlige config-filer

I OSX heter denne /Users/<username>

Når man starter en tjeneste f.eks apache som lytter på trafikk på port 80 så starter man den via `root` for å få tilgang til port 80, men rett etterpå lar man `apache`-brukeren kjøre prosessen. Dette gjør at dersom en angriper får til å angripe apache-webserveren vil angriperen kun få tilgang som `apache`-brukeren og ikke root. Man begrenser dermed skadeomfang.

Normalt så vil installasjonen av apache-pakken, sette opp `apache`-brukeren og lage nødvendige oppstartsskript slik at dette er ikke noe man trenger å gjøre selv.

## .bashrc / .bash_profile
Filer som .bashrc (linux) og .bash_profile (mac) som ligger i hjemmemappa.
Disse inneholder personlige configs for terminalene (som kjører bash).
Her kan du legge inn alias (f.eks hvis man skriver `gp` så skal den utføre `git push origin master`

Serveren kommer til å mase om:
```
perl: warning: Setting locale failed.
perl: warning: Please check that your locale settings:
	LANGUAGE = (unset),
	LC_ALL = (unset),
	LC_CTYPE = "UTF-8",
	LANG = "en_US.UTF-8"
    are supported and installed on your system.
perl: warning: Falling back to the standard locale ("C").
locale: Cannot set LC_CTYPE to default locale: No such file or directory
locale: Cannot set LC_ALL to default locale: No such file or directory
```

Da kan man legge til
```
export LC_CTYPE=en_US.UTF-8
export LC_ALL=en_US.UTF-8
```
nederst i .bashrc via vim eller nano.
Etterpå må man skrive `source .bashrc` for å oppdatere bash terminalen, slik at den bruker nyeste .bashrc


# Oppgave 1:
1. Installer vim og nginx.
2. Installer java8 (Vil kreve ekstra Repo via PPA)

Sjekk at du får fram eksempelsiden til nginx (http://ip-adresse) skal vi deg eksempelsiden til nginx.
