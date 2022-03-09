package it.univpm.OpenWeatherApp.service;

//import it.univpm.OpenWeatherApp.exceptions.CittaNotFoundException;
//import it.univpm.OpenWeatherApp.exceptions.EmptyStringException;
//import it.univpm.OpenWeatherApp.exceptions.WrongPeriodException;
//import it.univpm.OpenWeatherApp.exceptions.WrongValueException;
import it.univpm.OpenWeatherApp.models.*;
import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;

//import java.io.IOException;
//import java.util.ArrayList;

/** Questa è l'interfaccia che contiene i metodi utilizzati dal controller per effettuare 
 *  le richieste
 *  
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 */

public interface Service1 {
	
	public abstract JSONObject getPrevisioniJSON(String nomeCitta);
	public abstract Citta getPrevisioni(JSONObject object);
	//public abstract JSONArray getPrevisionePressione(String nome);
	public abstract JSONObject ConvertToJson(Citta citta);
	//public abstract String SalvaPrevisioniCitta(String nomeCitta);
}
