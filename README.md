# Progetto di Programmazione ad Oggetti dell'A.A. 2021/2022

Lo scopo di questo progetto è quello di fornire all'utente un web service Meteo, grazie al quale è possibile ottenere informazioni relative alla pressione di
una città. Con la seguente applicazione si riuscirà anche ad effettuare statistiche ed a filtrarle tramite opportuni parametri.

**INDICE**

* [Introduzione](#introduzione)
* [Installazione](#installazione)
* [Rotte disponibili](#rotte)
* [Test](#test)
* [Documentazione](#documentazione)
* [Struttura del progetto](#struttura)
* [Software utilizzati](#software)
* [Autori](#autori)

<a name="introduzione"></a>
## Introduzione 

Il programma OpenWeatherApp si focalizza sulle previsioni della pressione di una determinata città e ne calcola le statistiche.
Una volta avviata, l'applicazione inizierà a raccogliere i dati sulla pressione di 4 città, ovvero Ancona, Pisa, Torino e Pisa, salvandole su un file differente ogni ora. Inoltre essa consentirà anche di salvare le previsioni ottenute su 5 giorni per ogni città.

<a name="installazione"></a>
## Installazione

La seguente applicazione OpenWeatherApp può essere scaricata dal Terminale tramite il seguente comando:

git clone https://github.com/francescacolacrai/Progetto-di-Programmazione-ad-Oggetti-2021-2022.git

<a name="rotte"></a>
## Rotte disponibili
Le diverse richieste da effettuare tramite Postman si fanno al seguente indirizzo :

https://localhost:9090
<a name="table"></a>

Le rotte disponibili sono le seguenti:


|N°   | Tipo | Sintassi/Rotta                       | Descrizione                                                                                                                                                                                                  |
|-----|------|--------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1   | GET  | /infoMeteo?citta=Ancona              | Questa rotta restituisce un JsonArray contenente un JSONObject con le informazioni data e ora sulla pressione rispetto alla citta per la quale è stato effettuata la ricerca |
| 2   | GET  | /previsionePressione?citta=Ancona    | Questa rotta consente di avere le statistiche sulla pressione per i prossimi 5 giorni da quando è stata fatta la ricerca, prende come parametro il nome della città per la quale si vuole fare la ricerca                                                |
| 3   | GET  | /pressione?citta=Ancona    | Questa rotta consente di avere le statistiche sulla pressione data e ora in cui è stata fatto la ricerca, prende come parametro il nome della città per la quale si vuole fare la ricerca                                                                                                                                                                                                              |
| 4   | GET  | /salvaPrevisioniPressione?citta=Roma | Questa rotta consente di salvare le informazioni sulla previsione di una città scelta                                                                                                                                                                                                             |
| 5   | GET  | /saveHourly?citta=Roma               |   Questa rotta consente di salvare le informazioni sulla pressione di una città scelta                                                                                                                                                                                                           |   
| 6   | GET  | /lettura?citta= Pisa                 |   Questa rotta consente di leggere le informazioni sulle previsioni di pressione di una citta memorizzate su file                                                                                                                                                                                                           |                                   
| 7   | POST | /pressione_min_e_max                 |    Questa rotta di tipo Post filtra le statistiche, a seconda del parametro "valore" inserito, su un singolo giorno (flag = true) oppure sui 5 giorni (flag = false), in base al valore di flag inserito                                                                                                                                                                                                          |
| 8   | POST | /statistiche                         |    Questa rotta di tipo Post consente di ottenere i valori delle statistiche calcolate, ovvero media e varianza della pressione e pressione minima e massima, con rispettivi valori medi.                                                                                                                                                                                                          |
                                                                                                                                             

Per effettuare le diversi richieste tramite Postman, l'utente deve avviare il programma come applicazione 
SpringBoot, eseguire il software Postman o installare l'estensione Postman sur Grome, assicurarsi che 
la rotta sia tipo GET o POST rispetto al tipo di richesta che si vuole effettuare.

<a name="DESCRIZIONE DELLE ROTTE"></a>
## DESCRIZIONE DELLE ROTTE

<a name="rotta"></a>
### 1. /infoMeteo?citta=

Qesta rotta restituisce un JsonArray contenente un JSONObject con le informazioni relative a data e ora sulla pressione
rispetto alla città per la quale è stata effettuata la ricerca 
(si ottiene il risultato inserendo qualsiasi citta)


![getMeteo1](https://user-images.githubusercontent.com/90463687/158895989-8f0cd884-bd92-4614-b8da-392ee36a019c.png)

![getCitta2](https://user-images.githubusercontent.com/90463687/158896040-3c74be38-ea59-4048-bf07-399446340444.png)


### 2. /previsionePressione?citta=

Questa rotta consente di avere le statistiche sulla previsione della pressione dei i prossimi 5 giorni sulla città per cui è stata fatta la ricerca.
Prende come parametro una stringa che rappresenta il nome della citta di cui si vuole fare la ricerca
![getPrevisione](https://user-images.githubusercontent.com/90463687/158896089-89b7f1d6-736d-4b08-90b3-df71926926f6.png)


### 3. /previsionePressione?citta=

Questa rotta consente di avere le statistiche sulla pressione data e ora al quale è stato fatto la ricerca , prende in parametro citta 
che sarebbe il nome della citta per il quale si vuele fare la ricerca

![1](https://user-images.githubusercontent.com/90463687/158896113-0d48f693-9e2c-45a9-a18e-78d6763b37a6.png)


### 4. /salvaPrevisioniPressione?citta=

Questa rotta consente di salvare le informazioni sulla pressione della citta scelta.
Durante l'esecuzione, il programma crea un file di testo nelle cartella "forecasts" con il nome, ad esempio, "AnconaForecastPressure.txt" che viene aggiornato ogni ora 

![2](https://user-images.githubusercontent.com/90463687/158896136-086ebbb9-2662-46b9-96b1-1165bbe231c6.png)


### 5./saveHourly?citta=

Questa rotta consente di salvare le informazione meteo ogni ora sulla pressione della città scelta
durante l'esecuzione, il programma crea un file di testo nelle cartella "meteo" con 
il nome "Pressure.txt", preceduto dal nome della città scelta, che viene aggiornato ogni ora

![3](https://user-images.githubusercontent.com/90463687/158896170-f597c645-e263-4053-a844-936128004a4e.png)


<a name="test"></a>
## Test
Il test dell'applicazione si è basato su un metodo di tipo GET per eseguire la richiesta delle informazioni 
sui dati meteo.
La richiesta dei dati è andata a buon fine.

<a name="documentazione"></a>
## Documentazione
Il codice è documentato in Javadoc

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
