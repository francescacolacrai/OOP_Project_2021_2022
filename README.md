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
<a name="table"></a>


|N°   | Tipo | Sintassi/Rotta             | Descrizione                                                                                                                                                                                                  |
|-----|------|----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1   | GET  |   /infoMeteo?citta=Ancona       | Questa rotta consente di avere le statistiche sulla pressione data e ora al quale è stato fatto la ricerca , prende in parametro citta che sarebbe il nome della citta per il quale si vuele fare la ricerca |
| 2   | GET  |    /previsionePressione?citta=Ancona | Questa rotta consente di avere le statistiche sulla pressione per i prossimi 5 giorni stato fatto la ricerca , prende in parametro citta che sarebbe il nome della citta per il quale si vuele fare la ricerca                                                |
| 3   | GET  |  /previsionePressione=Ancone    | Questa rotta consente di avere le statistiche sulla pressione data e ora al quale è stato fatto la ricerca , prende in parametro citta che sarebbe il nome della citta per il quale si vuele fare la ricerca                                                                                                                                                                                                              |
| 4   | GET  |    /salvaPrevisioniPressione?citta=Roma    | Questa rotta consente di salvare le informazione sulla previsione di una citta scelta                                                                                                                                                                                                             |
| 5   | GET  |     /saveHourly?citta=Roma   |   Questa rotta consente di salvare le informazione sulla previsione di una citta scelta                                                                                                                                                                                                           |   
| 6   | GET  |    /lettura?citta= Pisa   |                                                                                                                                                                                                              |                                   
| 7   | POST |     /pressione_min_e_max?citta= Pisa     |                                                                                                                                                                                                              |
| 8   | POST |    /stats?citta=Pisa          |                                                                                                                                                                                                              |
| 9   | POST |                    |                                                                                                                                                                                                              |

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
