package it.univpm.OpenWeatherApp.service;

import java.io.IOException;

import it.univpm.OpenWeatherApp.exceptions.FileNotFoundException;
//import it.univpm.OpenWeatherApp.exceptions.CittaNotFoundException;
//import it.univpm.OpenWeatherApp.exceptions.EmptyStringException;
//import it.univpm.OpenWeatherApp.exceptions.WrongPeriodException;
//import it.univpm.OpenWeatherApp.exceptions.WrongValueException;
import it.univpm.OpenWeatherApp.models.*;
import org.json.JSONArray;
import org.json.JSONObject;
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
	public abstract JSONObject getForecastMeteo(String nomeCitta);
	public abstract JSONObject getMeteo(String nomeCitta);
	public abstract String getPressure(String nomeCitta);
	public abstract String getForecastPressure(String nomeCitta);
	public abstract String salvaDati(String dati, String path);
	public abstract String salvaOgniTreOre(String nomeCitta, String path);
	public abstract JSONArray letturaDaFile(String path) throws IOException, FileNotFoundException;
	public abstract JSONObject ConvertToJson(Citta citta);
	public abstract Citta setPrevisioni(JSONObject object);
	//public abstract String getDato(String dato, String statistica, char finalChar);
	//public abstract int getStatisticaFromString(String dato, String statistica);
	//public abstract String salvaPrevisioni(String nomeCitta) throws IOException;
	//public abstract String ottieniDaFile(String path, String nomeCitta) throws IOException;
	//public abstract JSONObject getPrevJson(String nomeCitta);
	//public abstract JSONObject getPrevisioniJSON(String nomeCitta);
	//public abstract JSONArray getPrevisionePressione(String nome);
}