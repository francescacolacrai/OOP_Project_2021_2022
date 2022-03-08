package it.univpm.OpenWeatherApp.service;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.web.client.RestTemplate;
import it.univpm.OpenWeatherApp.models.*;

import java.util.Map;
import java.util.Objects;

/** Questa classe è l'implementazione dell'interfaccia Service
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * 
 * @see Service
 */
public abstract class ServiceApi implements Service{
	
	/** api key indispensabile per ottenere dati da OpenWeather */
    private String API_KEY = "ce74fd08278903109816b3acfe7eb4fb";

    /** Metodo che ottiene da OpenWeather i dati meteo di una città
     *  @param citta  Città di cui si vogliono ottenere i dati meteo
     *  @return jsonObject Un oggetto JSONObject che contiene le informazioni richieste
     */
    public JSONObject getMeteoCitta(String citta) {

    	/** Richiesta */
        String url = "api.openweathermap.org/data/2.5/forecast?q=" + citta + "&appid="+API_KEY +
        		"&units=metric&lang=it";

        /** Oggetto della classe centrale all'interno del framework SpringBoot per poter 
         *  eseguire richieste HTTP sincrone sul lato client
         */
        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(restTemplate.getForObject
        		(url, Map.class)));

        return jsonObject;
    }
    
    /** Metodo che consente di ottenere i dati relativi alla pressione della città richiesta
     * @param nome Nome della città di cui si vogliono avere i dati sulla pressione
     * @return l'array JSON contenente i dati richiesti
     */
    public JSONArray getPressioneCitta(String nome) {
    	int pressione;
    	String data;
    	
    	JSONObject oggettoJSON = getMeteoCitta(nome);
    	JSONArray contenitore = new JSONArray();
    	JSONArray arrayMeteo = oggettoJSON.getJSONArray("list");
    	
    	for(int i=0; i<arrayMeteo.size(); i++) {
    		pressione = (int)(arrayMeteo.getJSONObject(i)).get("pressure");
    		data = (String)(arrayMeteo.getJSONObject(i)).get("dt_txt");
    		
    		JSONObject finale = new JSONObject();
    		finale.put("Pressione", pressione);
    		finale.put("Data", data);
    		contenitore.put(finale);
    	}
    	return contenitore;
    }
    
   /*
    public String SalvaDatiCitta(StringNomeCitta) {
    	return ;
    }
    */
}

