# Ghost Net Fishing – IPWA02-01

Dies ist ein prototypisches Java-Webprojekt zur Meldung und Verwaltung von Geisternetzen im Rahmen der Fallstudie IPWA02-01.

## Funktionen

* Geisternetz anonym oder mit Kontaktdaten melden
* Offene Geisternetze anzeigen
* Bergung eines Geisternetzes übernehmen
* Geisternetz als geborgen melden
* Geisternetz als verschollen melden
* Gesamtübersicht aller gespeicherten Geisternetze

## Technologien

* Java 17
* Maven
* JSF / Jakarta Faces
* CDI
* JPA / Hibernate
* H2-Datenbank
* Jetty Maven Plugin

## Start der Anwendung

Die Anwendung kann lokal über Maven gestartet werden:

```bash
mvn jetty:run
```

Anschließend ist der Prototyp im Browser erreichbar unter:

```text
http://localhost:8080/ghost-net-fishing/index.xhtml
```
