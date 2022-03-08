package it.univpm.OpenWeatherApp.service;

import it.univpm.OpenWeatherApp.exceptions.CittaNotFoundException;
import it.univpm.OpenWeatherApp.exceptions.EmptyStringException;
import it.univpm.OpenWeatherApp.exceptions.WrongPeriodException;
import it.univpm.OpenWeatherApp.exceptions.WrongValueException;
import it.univpm.OpenWeatherApp.models.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

/** Questa è l'interfaccia che contiene i metodi utilizzati dal controller per effettuare 
 *  le richieste
 *  
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 */
public interface Service {
	
	public abstract JSONObject getMeteoCitta(String citta);
	public abstract JSONArray getPressioneCitta(String nome);
	public abstract String SalvaDatiCitta(String nomeCitta);

	public abstract ArrayList<JSONObject> readHistoryError
		(ArrayList<String> citta, int error, String value, int pressione)
		throws EmptyStringException, CittaNotFoundException, WrongPeriodException, 
		WrongValueException, IOException;
	
	public abstract ArrayList<JSONArray> readVisibilityHistory(ArrayList<String> citta,
			String pressione)
			throws EmptyStringException, CittaNotFoundException , WrongPeriodException, IOException;
}
