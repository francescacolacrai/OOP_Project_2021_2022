package it.univpm.OpenWeather.service;

import it.univpm.OpenWeatherApp.models.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public interface Service {
	
	public abstract JSONObject getMeteoCitta(String citta);
	public abstract JSONArray getPressioneCitta(String nome);
	public abstract String SalvaDatiCitta(String nomeCitta);
	//...continuare
	

}
