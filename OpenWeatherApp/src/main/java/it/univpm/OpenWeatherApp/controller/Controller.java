package it.univpm.OpenWeatherApp.controller;


import java.io.IOException;
import java.util.ArrayList;

import it.univpm.OpenWeatherApp.stats_and_filters.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.univpm.OpenWeatherApp.exceptions.*;
import it.univpm.OpenWeatherApp.service.*;
import it.univpm.OpenWeatherApp.models.*;

/**
 * Classe che gestisce le chiamate dell'utente
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * 
 * @see ServiceImpl
 *
 */

@RestController
public class Controller {

	@Autowired 
	static ServiceImpl service = new ServiceImpl();
	Statistiche stats = new Statistiche();

	 /**
	  * Questa rotta consente di avere le statistiche sulla pressione con l'ora esatta in cui
	  * viene fatta la ricerca. Accetta come parametro una stringa che rappresenta il nome della 
	  * città di cui si fa richiesta
	  * @param nome della città scelta
	  * @return JSONObject con i dati relativi alle previsioni meteo
	 */
	
	@GetMapping(value="/infoMeteo")
		public ResponseEntity<Object> getInfoMeteo(@RequestParam(name="citta")String nomeCitta){
			Citta citta = service.getPrevisionePressione(nomeCitta);
			JSONObject obj = service.ConvertToJson(citta);
			return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
	}

	/**
	 * Questa rotta consente di avere le statistiche sulla pressione per i prossimi 5 giorni.
	 * Prende come parametro il nome della citta di cui si vogliono ottenere i dati
	 * @param nome della città scelta
	 * @return oggetto Citta con i dati relativi alla previsione della pressione 
	 */
	@GetMapping(value="/previsionePressione")
    public ResponseEntity<Object> getPressureF(@RequestParam(name="citta") String nomeCitta) {    
		return new ResponseEntity<> (service.getPrevisionePressione(nomeCitta).toString(), HttpStatus.OK);
    }

	/** 
	 * Questa rotta consente di avere le statistiche sulla pressione con l'ora esatta in cui
	 * viene fatta la ricerca. Accetta come parametro una stringa che rappresenta il nome della 
	 * città di cui si fa richiesta
	 * @param nome della città scelta
	 * @return Stringa contenente i dati della pressione per la città scelta
	 */
	@GetMapping(value="/pressione")
    public ResponseEntity<Object> getPressure(@RequestParam(name="citta") String nomeCitta) {    
		return new ResponseEntity<> (service.getPressione(nomeCitta).toString(), HttpStatus.OK);
    }
	
	/** 
	 * Questa rotta consente di salvare le informazioni sulle previsioni di pressione di una città
	 * @param nome della città 
	 * @return path in cui sono memorizzati i dati
     */
	@GetMapping(value="/salvaPrevisioniPressione")
	public ResponseEntity<Object> salva(@RequestParam(name="citta") String nomeCitta) throws IOException{
		Citta citta = service.getPrevisionePressione(nomeCitta);
		return new ResponseEntity<>((service.salvaPrevisioni(nomeCitta, citta)), HttpStatus.OK);
	}
	
	/** 
	 * Questa rotta consente di salvare le informazioni sullapressione di una città ogni ora
	 * @param nome della città
	 * @return path in cui si memorizzano i dati
     */
	@GetMapping(value="/saveHourly")
		public ResponseEntity<Object> salvaOgniTreOre(@RequestParam(name="citta") String nomeCitta) {
			String path = System.getProperty("user.dir") + "/meteo/" + nomeCitta + "Pressure.txt";
			return new ResponseEntity<>((service.salvaOgniTreOre(nomeCitta, path)), HttpStatus.OK);
	}
	
	/** 
	 * Questa rotta consente di leggere le informazioni sulle previsioni di pressione di una citta
	 * memorizzate su file
     */
	@GetMapping(value="/lettura")
		public ResponseEntity<Object> getCitta(@RequestParam(name="citta") String nomeCitta) throws IOException, FileNonTrovatoException{
			String path = System.getProperty("user.dir") + "/forecasts/" + nomeCitta + "ForecastPressure.txt";
			return new ResponseEntity<>(service.ottieniDaFile(path), HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo POST che filtra le statistiche.
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * 
	 * {
     *     "città": [
     *        {
     *          "nome" : "Ancona"
     *        },
     *        {
     *          "nome" : "Torino"
     *        },
     *        {
     *          "nome" : "Pisa"
     *        },
     *        {
     *        	"nome" : "Roma"
     *        }
     *      ],
     *     "valore": "max",
     *     "flag": true
     *  }
	 * 
	 * Filtra, a seconda del valore inserito (min o max), la città con pressione più alta o più 
	 * bassa e se su 1 giorno o 5 giorni, a seconda del valore di flag inserito. 
	 * 
	 * @param body è un JSONObject come sopra indicato.
	 * @return la stringa che contiene tutti i dati relativi al filtraggio (pressione media) calcolate 
	 * 		   per le città inserite e, inoltre, viene restituita anche la pressione media minima o 
	 * 		   massima a seconda del valore di "valore".
	 * @throws StringaErrataException se viene inserita una stringa errata per valore o flag
	 */
	@PostMapping("/pressione_min_e_max")
	public ResponseEntity<Object> filtraPressione(@RequestBody String body) throws StringaErrataException {
		
		JSONObject oggetto = new JSONObject(body);
		JSONArray array = oggetto.getJSONArray("città");
		ArrayList<String> citta = new ArrayList<>(array.length());
		
		for(int k=0; k<array.length(); k++) {
            JSONObject hold = array.getJSONObject(k);
            citta.add(hold.getString("nome"));
        }

        String valore = oggetto.getString("valore");
        String flag = oggetto.getString("flag");
		String esito = "";
        try {
        	Filtri filtro = new Filtri(citta, valore, flag);
        	esito = filtro.filtra().toString();
        }catch(StringaErrataException e) {
    			System.out.println("Hai inserito un valore errato per valore o flag!");
    		}
			return new ResponseEntity<>(esito, HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo POST che mostra la media della pressione, la minima, la massima e la varianza della 
	 * pressione del giorno corrente o su 5 giorni, a seconda del valore di flag che l'utente inserisce. 
	 * 
	 * {
     *		"città" : "Pisa",
     *		"valore" : "oggi",
     *		"flag" : "true"
	 *	}
	 * 
	 * @param body è un JSONObject come sopra indicato.
	 * @return il JSONObject che contiene la statistica richiesta.
	 * @throws PeriodoErratoException in caso di errato inserimento della stringa per il periodo.
	 * @throws IOException in caso di errore durante la lettura del file.
	 */
	@PostMapping(value = "/statistiche")
	public ResponseEntity<Object> stats(@RequestBody String body) throws PeriodoErratoException, IOException{
		
		JSONObject req = new JSONObject(body);
		String valore = req.getString("valore");
		String nomeCitta = req.getString("città");
		String flag = req.getString("flag");
		try {
			if (valore.equals("oggi"))
				return new ResponseEntity<>(stats.statistichePressione(nomeCitta,flag).toString(), HttpStatus.OK);
			else if (valore.equals("5 giorni"))
				return new ResponseEntity<>(stats.statistichePressione(nomeCitta,flag).toString(), HttpStatus.OK);
			else throw new PeriodoErratoException(valore + " Periodo non ammesso!");
		} catch (PeriodoErratoException e) {
			return new ResponseEntity<>(e.getMessaggio(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Rotta di tipo POST che filtra per giorno le statistiche sulla pressione delle città 
	 * i cui dati sono memorizzati in file.
	 * 
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * 
	 * {
     *     "città": [
     *        {
     *          "nome": "Torino"
     *        },
     *        {
     *          "nome": "Pisa"
     *        },
     *        {
     *          "nome": "Ancona"
     *        }
     *      ]
     *  }
	 * 
 	 * Le città accettate sono solo Ancona, Torino, Pisa e Roma.
	 * 
	 * @param body è un JSONObject come sopra indicato.
	 * @return le statistiche di ciascuna città con periodicità giornaliera.
	 * @throws CittaNonTrovataException se la città immessa non è una tra quelle indicate sopra.
	 * @throws IOException se si verificano errori durante la lettura dal file.
	 */
	
	@PostMapping(value="/statisticheDaFile")
    public ResponseEntity<Object> statistiche(@RequestBody String body) throws CittaNonTrovataException, IOException {
		
		JSONObject object = new JSONObject(body);
        JSONArray array = object.getJSONArray("città");
        
        ArrayList<String> citta = new ArrayList<String>(array.length());
        
        for(int i=0; i<array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            citta.add(obj.getString("nome"));
        }
        
        try {
        	return new ResponseEntity<>(service.letturaDatiPressione(citta).toString(), HttpStatus.OK);
        }
        catch (CittaNonTrovataException | FileNonTrovatoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}  
	}
	
	/**
	 * Rotta di tipo POST che filtra le statistiche sulla visibilità in base ad una soglia di errore.
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * 
	 * {
     *     "citta": [
     *        {
     *          "nome": "Torino"
     *        },
     *        {
     *          "nome": "Roma"
     *        },
     *        {
     *          "nome": "Ancona"
     *        }
     *      ],
     *     "errore": 1,
     *     "valore": "$gt",
     *     "periodo": 2
     *  }
	 * 
	 * Le città possibili sono: Ancona, Roma, Torino e Pisa.
	 * 
	 * @param body è un JSONObject come sopra indicato.
	 * @return un JSONArray contenente i JSONObject con tutte le informazioni sulle previsioni indovinate e l'errore di
	 *         ogni città
	 * @throws CittaNonTrovataException se viene inserito un nome non presente
	 * @throws ValoreErratoException se si inserisce una stringa non valida per valore
	 * @throws PeriodoErratoException se si inserisce un numero non valido per periodo
	 * @throws IOException se si sono verificati errori di input da file.
	 */
	
	@PostMapping(value = "/errori")
	public ResponseEntity<Object> filtroErrore(@RequestBody String body) 
			throws CittaNonTrovataException, ValoreErratoException, PeriodoErratoException, IOException {
	
		JSONObject object = new JSONObject(body);
        JSONArray array = object.getJSONArray("città");
        ArrayList<String> citta = new ArrayList<String>();
        
        for(int i=0; i<array.length(); i++) {
        	JSONObject obj = array.getJSONObject(i);
            citta.add(obj.getString("nome"));
        }
        
        int errore = object.getInt("errore");
        String valore = object.getString("valore");
        int periodo = object.getInt("periodo");
        
        try {
        	return new ResponseEntity<>(service.letturaErrore(citta, errore, valore, periodo).toString(), HttpStatus.OK);
        }
        catch(CittaNonTrovataException | ValoreErratoException | PeriodoErratoException | FileNonTrovatoException e) {
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}
	
	
}
