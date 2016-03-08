# Hjemmemappe
Alle brukere har sin egen hjemmemappe under /home/<username>
Denne mappen inneholder personlige filer + personlige config-filer

I OSX heter denne /Users/<username>

# .bashrc / .bash_profile
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
