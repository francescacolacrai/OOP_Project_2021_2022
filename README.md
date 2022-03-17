# Progetto di Programmazione ad Oggetti dell'A.A. 2021/2022

Lo scopo di questo progetto è quello di fornire all'utente un web service Meteo, grazie al quale è possibile ottenere informazioni relative alla pressione di
una città. Con la seguente applicazione si riuscirà anche ad effettuare statistiche ed a filtrarle tramite opportuni parametri.

**INDICE**

* [Introduzione](#introduzione)
* [Installazione](#installazione)
* [Configurazione](#configurazione)
* [Rotte disponibili](#rotte)
* [Test](#test)
* [Documentazione](#documentazione)
* [Struttura del progetto](#struttura)
* [Software utilizzati](#software)
* [Autori](#autori)

<a name="introduzione"></a>
## Introduzione 

<a name="installazione"></a>
## Installazione

<a name="configurazione"></a>
## Configurazione 

<a name="rotte"></a>
## Rotte disponibili
Le diverse richieste da effetuare tramite postman si fanno al seguente indirizzo :

https://localhost:9090
<a name="table"></a>

Le rotte disponibile sono le seguenti:


|N°   | Tipo | Sintassi/Rotta                       | Descrizione                                                                                                                                                                                                  |
|-----|------|--------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1   | GET  | /infoMeteo?citta=Ancona              | Qesta rotta restituisce un JsonArray contenente un JSONObject con le informazione data e ora sulla presione rispetto alla citta per la quale è stato effettuato la ricerca |
| 2   | GET  | /previsionePressione?citta=Ancona    | Questa rotta consente di avere le statistiche sulla pressione per i prossimi 5 giorni stato fatto la ricerca , prende in parametro citta che sarebbe il nome della citta per il quale si vuele fare la ricerca                                                |
| 3   | GET  | /pressione?citta=Ancone    | Questa rotta consente di avere le statistiche sulla pressione data e ora al quale è stato fatto la ricerca , prende in parametro citta che sarebbe il nome della citta per il quale si vuele fare la ricerca                                                                                                                                                                                                              |
| 4   | GET  | /salvaPrevisioniPressione?citta=Roma | Questa rotta consente di salvare le informazione sulla previsione di una citta scelta                                                                                                                                                                                                             |
| 5   | GET  | /saveHourly?citta=Roma               |   Questa rotta consente di salvare le informazione sulla previsione di una citta scelta                                                                                                                                                                                                           |   
| 6   | GET  | /lettura?citta= Pisa                 |                                                                                                                                                                                                              |                                   
| 7   | POST | /pressione_min_e_max                 |                                                                                                                                                                                                              |
| 8   | POST | /stats                               |                                                                                                                                                                                                              |
| 9   | POST |                                      |                                                                                                                                                                                                              |

Per effettuare le diversi richieste tramite Postman, l'utente deve avviare il programma come applicazione 
SpringBoot, eseguire il software Postman o installare l'extenzione postman sur Grome, assicurarsi che 
la rote sia tipo GET o POST rispetto al tipo di richesta che si vuole effettuare.

<a name="DESCRIZIONE DELLE ROTTE"></a>
## DESCRIZIONE DELLE ROTTE

<a name="rotta"></a>
### 1. /infoMeteo?citta=

Qesta rotta restituisce un JsonArray contenente un JSONObject con le informazione data e ora sulla presione
rispetto alla citta per la quale è stato effettuato la ricerca 
(si ottiene il risultato inserendo qualsiasi citta)


![Previsione](/home/lionel/Scrivania/img/getMeteo1.png)

![Previsione](/home/lionel/Scrivania/img/getCitta2.png)

### 2. /previsionePressione?citta=

Questa rotta consente di avere le statistiche sulla pressione per i prossimi 5 giorni stato fatto la ricerca,
prende in parametro citta che sarebbe il nome della citta per il quale si vuele fare la ricerca

![Previsione](/home/lionel/Scrivania/img/getPrevisione.png)


### 3. /previsionePressione?citta=

Questa rotta consente di avere le statistiche sulla pressione data e ora al quale è stato fatto la ricerca , prende in parametro citta 
che sarebbe il nome della citta per il quale si vuele fare la ricerca
![Previsione](/home/lionel/Scrivania/img/1.png)

### 4. /salvaPrevisioniPressione?citta=

Questa rotta consente di salvare le informazione sulla pressione della citta scelta
durante l'esecuzione, il programma crea un file di texto nelle cartella "forecast" con il nome "AnconaForecastPressure.txt" che viene aggiornato ogni orea 
ch eviena eseguito il programma

![Previsione](/home/lionel/Scrivania/img/2.png)

### 5./saveHourly?citta=

Questa rotta consente di salvare le informazione meteo ogni ore sulla pressione della citta scelta
durante l'esecuzione, il programma crea un file di texto nelle cartella "meteo" con 
il nome "Pressure.txt" che viene aggiornato ogni orea
ch eviena eseguito il programma

![Previsione](/home/lionel/Scrivania/img/3.png)

<a name="test"></a>
## Test

<a name="documentazione"></a>
## Documentazione

<a name="struttura"></a>
## Struttura del progetto

<a name="software"></a>
## Software utilizzati
1. [Eclipse](https://www.eclipse.org/downloads/), ambiente principale di sviluppo
2. [Maven](https://maven.apache.org/), plug-in per la gestione delle librerie e dei progetti
3. [SpringBoot](https://spring.io/projects/spring-boot), framework per la realizzazione di applicazioni in Java
4. [Postman](https://www.postman.com/), piattaforma API che consente di effettuare le varie richieste

<a name="autori"></a>
## Autori
Il seguente progetto è stato realizzato da:
1. Francesca Colacrai
2. Djouaka Kelefack Lionel
