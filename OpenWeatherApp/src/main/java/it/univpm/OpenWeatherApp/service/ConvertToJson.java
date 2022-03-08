package it.univpm.OpenWeatherApp.service;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import it.univpm.OpenWeatherApp.models.*;

public class ConvertToJson {
	
	Citta citta = new Citta();
	
	 /** Metodo che restituisce il JSONObject associato all'oggetto Citta passato come argomento
     * @param citta Citta di cui si vuole ottenere il JSONObject
     * @return il JSONObject corrispondente alla città richiesta
     */
    public JSONObject ConvertToJson(Citta citta) {
    	
    	JSONObject oggettoJSON = new JSONObject();
    	oggettoJSON.put("nome", citta.getNome());
    	oggettoJSON.put("paese", citta.getPaese());
    	oggettoJSON.put("id", citta.getId());
    	oggettoJSON.put("latitudine", citta.getLat());
    	oggettoJSON.put("longitudine", citta.getLong());
    	
    	JSONArray vettore = new JSONArray();
    	
    	for(int i=0; i<(citta.showVector()).length(); i++) {
    		JSONObject meteo = new JSONObject();
    		meteo.put("data", (citta.getRaccoltaDatiMeteo()).get(i).getData());
    		meteo.put("main", (citta.getRaccoltaDatiMeteo()).get(i).getMain());
			meteo.put("temp_max", (citta.getRaccoltaDatiMeteo()).get(i).getT_max());
			meteo.put("temp_min", (citta.getRaccoltaDatiMeteo()).get(i).getT_min());
			vettore.add(meteo);
		}

		oggettoJSON.put("Meteo", vettore);
		
		return oggettoJSON;
    }

}
