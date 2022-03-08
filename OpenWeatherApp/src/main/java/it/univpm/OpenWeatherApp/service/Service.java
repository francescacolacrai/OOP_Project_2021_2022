package it.univpm.OpenWeatherApp.service;

import it.univpm.OpenWeatherApp.models.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public interface Service {
	
	public abstract JSONObject getMeteoCitta(String citta);
	public abstract JSONArray getPressioneCitta(String nome);
	public abstract String SalvaDatiCitta(String nomeCitta);
	public abstract Citta getCittafromApi(String citta);


	public abstract ArrayList<JSONObject> readHistoryError(ArrayList<String> citta, int error, String value, int pressione)
			throws EmptyStringException, CittaNotFoundException, WrongPeriodException, WrongValueException, IOException;
	public abstract ArrayList<JSONArray> readVisibilityHistory(ArrayList<String> citta,String pressione)
			throws EmptyStringException, CittaNotFoundException , WrongPeriodException, IOException;

	JSONObject getNomeCitta();

	JSONObject getNomeCitta(String nomeCitta);
	//...continuare
	

}
