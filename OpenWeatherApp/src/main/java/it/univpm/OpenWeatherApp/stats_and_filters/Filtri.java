package it.univpm.OpenWeatherApp.stats_and_filters;

import java.util.ArrayList;
import org.json.JSONArray;

import it.univpm.OpenWeatherApp.exceptions.StringaErrataException;

/**
 * Classe che contiene i metodi per effettuare il filtraggio
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 *
 */
public class Filtri {
	private ArrayList<String> citta = new ArrayList<String>();
	private String valore;
	boolean flag;
	
   /**
    *  Questo è il costruttore della classe.
    * @param citta è un ArrayList di Stringhe contenenti i nomi delle città che si vogliono filtrare.
    * @param valore valore max o min di param.
    * @param flag valore utile per stabilire il filtraggio giornaliero o su 5 giorni 
    */
	public Filtri(ArrayList<String> citta, String valore, boolean flag) {
		this.citta = citta;
		this.valore = valore;
		this.flag = flag;
	}

	/**
	 * Questo metodo filtra il periodo e il parametro. Richiama altri metodi per filtrare il value.
	 * @return JSONArray contenente le città filtrate e le statistiche ottenute.
	 * @throws StringaErrataException se è stato inserita una stringa errata per valore.
	 */
	
	public JSONArray filtra() throws StringaErrataException {
		
		JSONArray array = new JSONArray ();
		
		FiltraPressione filtro = new FiltraPressione();
		array = filtro.filtraPressione(citta, valore, flag);
		
		return array;
	}
	
}
