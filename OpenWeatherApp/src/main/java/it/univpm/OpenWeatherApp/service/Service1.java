package it.univpm.OpenWeatherApp.service;

//import it.univpm.OpenWeatherApp.exceptions.CittaNotFoundException;
//import it.univpm.OpenWeatherApp.exceptions.EmptyStringException;
//import it.univpm.OpenWeatherApp.exceptions.WrongPeriodException;
//import it.univpm.OpenWeatherApp.exceptions.WrongValueException;
//import it.univpm.OpenWeatherApp.models.*;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;

//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.ArrayList;

/** Questa è l'interfaccia che contiene i metodi utilizzati dal controller per effettuare 
 *  le richieste
 *  
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 */

public interface Service1 {
	
	public abstract String getData();
	public abstract String getForecastMeteo(String nomeCitta);
	public abstract String getMeteo(String nomeCitta);
	public abstract String getDato(String dato, String statistica, char finalChar);
	public abstract String salvaDati(String dati, String path);
//	public ArrayList<JSONArray> getPressure(String nomeCitta);
//	public abstract JSONObject getPrevJson(String nomeCitta);
//	public abstract JSONObject getPrevisioniJSON(String nomeCitta);
//	public abstract Citta getPrevisioni(JSONObject object);
	//public abstract JSONArray getPrevisionePressione(String nome);
	//public abstract JSONObject ConvertToJson(Citta citta);
//	public abstract String salvaPrevisioni(String nomeCitta) throws IOException;
	//public abstract JSONArray LetturaStorico(String path) throws IOException;
}