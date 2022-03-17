package it.univpm.OpenWeatherApp.service;

import java.io.IOException;
import java.util.ArrayList;

import it.univpm.OpenWeatherApp.exceptions.CittaNonTrovataException;
import it.univpm.OpenWeatherApp.exceptions.FileNonTrovatoException;
import it.univpm.OpenWeatherApp.exceptions.PeriodoErratoException;
import it.univpm.OpenWeatherApp.exceptions.ValoreErratoException;
import it.univpm.OpenWeatherApp.models.*;

import org.json.JSONObject;
import org.json.JSONArray;

/** Questa è l'interfaccia che contiene i metodi utilizzati dal controller per effettuare 
 *  le richieste
 *  
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 */

public interface Service1 {
	
	public abstract String getData();
	public abstract Citta getInfoCitta(String nomeCitta);
	public abstract JSONObject getForecastMeteo(String nomeCitta);
	public abstract JSONObject getMeteo(String nomeCitta);
	public abstract String getPressione(String nomeCitta);
	public abstract Citta getPrevisionePressione(String nomeCitta);
	public abstract String salvaPrevisioni(String nomeCitta, Citta citta) throws IOException;
	public abstract String salvaOgniTreOre(String nomeCitta, String path);
	public abstract JSONArray letturaDaFile(String path) throws IOException, FileNonTrovatoException;
	public abstract JSONObject ConvertToJson(Citta citta);
	public abstract String ottieniDaFile(String path) throws IOException;
	public ArrayList<JSONObject> letturaErrore(ArrayList<String> citta, int errore, String valore, int periodo) 
			throws CittaNonTrovataException, PeriodoErratoException, ValoreErratoException, FileNonTrovatoException, IOException;
	public ArrayList<JSONArray> letturaDatiPressione(ArrayList<String> citta) 
			throws CittaNonTrovataException, FileNonTrovatoException, IOException;
}