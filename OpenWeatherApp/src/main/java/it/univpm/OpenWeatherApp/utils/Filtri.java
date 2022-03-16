package it.univpm.OpenWeatherApp.utils;

import java.util.ArrayList;
import org.json.JSONArray;

import it.univpm.OpenWeatherApp.exceptions.WrongValueException;

public class Filtri {
	private ArrayList<String> citta = new ArrayList<String>();
	private String value;
	boolean flag;
	
   /**
    *  Questo è il costruttore della classe.
    * @param cities è un ArrayList di Stringhe contenenti i nomi delle città che si vogliono filtrare.
    * @param value valore max o min di param.
    */
	public Filtri(ArrayList<String> citta, String value, boolean flag) {
		this.citta = citta;
		this.value = value;
		this.flag = flag;
	}

	/**
	 * Questo metodo filtra il periodo e il parametro. Richiama altri metodi per filtrare il value.
	 * @return JSONArray contenente le città filtrate e le statistiche ottenute.
	 * @throws WrongValueException se è stato inserita una stringa errata per value.
	 */
	
	public JSONArray filtra() throws WrongValueException {
		
		JSONArray array = new JSONArray ();
		
		FiltraPressione filtro = new FiltraPressione();
		array = filtro.filtraPressione(citta, value, flag);
		
		return array;
	}
	
}
