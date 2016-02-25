# Oppgave 3

I oppgave 3 skal vi lage upstart-script for ubuntu for Java-applikasjonen vår.

Upstart er Ubuntu sitt nå updaterte system for å starte, stoppe, overvåke, og restarte tjenester i sysetmet. Nyere Ubuntu-versjoner bruker systemd istedenfor. Det er en del forskjeller mellom disse systemene, men fra et større perspektiv så gjør de det samme. Starter prosseser automatisk ved oppstart. De kan også restarte en tjeneste hvis den skulle feile.

For å gjøre denne oppgaven litt enklere har vi inkludert med en eksempelscript:

```
description "Test node.js server"
author      "Your Name"

start on filesystem or runlevel [2345]
stop on shutdown

script

    export HOME="/srv"
    echo $$ > /var/run/nodetest.pid
    exec /usr/bin/nodejs /srv/nodetest.js

end script

pre-start script
    echo "[`date`] Node Test Starting" >> /var/log/nodetest.log
end script

pre-stop script
    rm /var/run/nodetest.pid
    echo "[`date`] Node Test Stopping" >> /var/log/nodetest.log
end script
```

Du kan ta utgangspunkt i det skriptet og tilpasse det Java-applikasjonen.
upstart-script ligger i `/etc/init` på ubuntu
