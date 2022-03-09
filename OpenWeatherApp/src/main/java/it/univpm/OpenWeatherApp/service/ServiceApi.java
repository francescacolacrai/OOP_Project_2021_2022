package it.univpm.OpenWeatherApp.service;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
//import org.json.simple.parser.JSONParser;
import it.univpm.OpenWeatherApp.models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
import org.json.simple.parser.ParseException;
//import java.util.Map;
//import java.util.Objects;
import java.util.Vector;

/** Questa classe è l'implementazione dell'interfaccia Service
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * 
 * @see Service
 */

@Service
public class ServiceApi implements Service1{
	
	/** api key indispensabile per ottenere dati da OpenWeather */
    private String API_KEY = "aa40575c16b2fc97f5696d9f0c233ccb";
   //private String url = "api.openweathermap.org/data/2.5/forecast?q=";
    
    public JSONObject getPrevisioniJSON(String nomeCitta) {	
    	
    	/** Richiesta */
        String url = "api.openweathermap.org/data/2.5/forecast?q=" + 
        		nomeCitta + "&appid="+ API_KEY + "&units=metric&lang=it";
   
		JSONObject previsione = null;
	//	String StringaDatiJson = "";
	//	String line = "";
		
		try {
			URLConnection connessione = new URL(url).openConnection();
			InputStream input = connessione.getInputStream();
			
			String StringaDatiJson = "";
			String line = "";
			try {
				BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
				while((line = buffer.readLine()) != null) 
					StringaDatiJson += line; //salva il JSON nella stringa 
			} finally {
				input.close();
			}
			previsione = (JSONObject)JSONValue.parseWithException(StringaDatiJson); //parsing dei dati
		}
		catch(IOException | ParseException e) {
			System.out.println("Errore!");
			e.printStackTrace(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return previsione;
    }   
   
   public Citta getPrevisioni(JSONObject object) {
	   Citta citta = new Citta();
	   Vector<Meteo> previsioneDati = new Vector<Meteo>();
	   
	   JSONObject datiCitta = (JSONObject)object.get("city");
	   JSONArray listaDati = (JSONArray)object.get("list");
	   
	   citta.setNome((String)datiCitta.get("name"));
	   citta.setPaese((String)datiCitta.get("country"));
	   citta.setId((long)datiCitta.get("id"));
	   citta.setLat((double)datiCitta.get("lat"));
	   citta.setLong((double)datiCitta.get("long"));
	   
	   for(int j=0; j<listaDati.size(); j++) {
		   JSONObject listaInfo = (JSONObject)listaDati.get(j);
		   Meteo previsioneMeteo = new Meteo();
		   
		   JSONObject meteoMain = (JSONObject)(listaInfo.get("main"));
		   
		   previsioneMeteo.setData((String)listaInfo.get("dt_txt"));
		   previsioneMeteo.setPressione((int)meteoMain.get("pressure"));
		   
		   previsioneDati.add(previsioneMeteo);
	   }
	   
	   citta.setRaccoltaDatiMeteo(previsioneDati);
	   
	   return citta;
   }
    
   public JSONObject ConvertToJson(Citta citta) {
	   
	   JSONObject finale = new JSONObject();
	   
	   finale.put("città", citta.getNome());
	   finale.put("Paese", citta.getPaese());
	   finale.put("ID", citta.getId());
	   
	   JSONArray elencoPrevisioni = new JSONArray();
	   
	   for(Meteo previsioneMeteo : citta.getRaccoltaDatiMeteo()) {
		   JSONObject previsione = new JSONObject();
		   
		   previsione.put("data", previsioneMeteo.getData());
		   previsione.put("main", previsioneMeteo.getMain());
		   previsione.put("pressione", previsioneMeteo.getPressione());
		   
		   elencoPrevisioni.add(previsione);
	   }
	   
	   return finale;
   }
   
   
   
    /* ------------------------------------------------- */
    
    /** Metodo che ottiene da OpenWeather i dati meteo di una città
     *  @param citta  Città di cui si vogliono ottenere i dati meteo
     *  @return jsonObject Un oggetto JSONObject che contiene le informazioni richieste
     */
    //public JSONObject getPrevisioni(String citta) {

    	/** Richiesta */
    //    String url = "api.openweathermap.org/data/2.5/forecast?q=" + citta + "&appid="+API_KEY +
    //    		"&units=metric&lang=it";

        /** Oggetto della classe centrale all'interno del framework SpringBoot per poter 
         *  eseguire richieste HTTP sincrone sul lato client (API REST)
         */
    //    RestTemplate restTemplate = new RestTemplate();
        
    //    JSONObject oggettoJSON = new JSONObject(Objects.requireNonNull(restTemplate.getForObject
    //    		(url, Map.class)));

    //    return oggettoJSON;
    //}
        
    /** Metodo che consente di ottenere i dati relativi alla pressione della città richiesta
     * @param nome Nome della città di cui si vogliono avere i dati sulla pressione
     * @return l'array JSON contenente i dati richiesti
     */
   // public JSONArray getPrevisionePressione(String nomeCitta) {
    	
    	/** Richiesta */
    //    String url = "api.openweathermap.org/data/2.5/forecast?q=" + nomeCitta +
    //    		"&appid="+API_KEY + "&units=metric&lang=it";
    
    //	JSONParser parser = new JSONParser();
   	//	RestTemplate restTemplate2 = new RestTemplate();
   	// 	String dati = restTemplate2.getForObject(url, String.class);
   	
   	// 	try {
   	 		
   	// 		JSONObject oggetto = (JSONObject)parser.parse(dati);
   	 		
   	// 		JSONObject main = (JSONObject) oggetto.get("main");
   	// 		pressione = (int)main.get("pressure");
   	// 		data = (String)oggetto.get("dt_txt");
   	 		
   	// 	} catch(ParseException e) {
   	// 		System.out.println("Parsing error!");
   	// 	}
   	 	
   	// 	JSONObject finale = new JSONObject();
 	//	JSONArray contenitore = new JSONArray();
 	//	finale.put("Pressione", pressione);
 	//	finale.put("Data", data);
 	//	contenitore.add(finale);
   	 	
   	 //	return contenitore;
        
   // 	JSONObject oggettoJSON = getMeteoCitta(nomeCitta);
   // 	JSONArray contenitore = new JSONArray();
   // 	JSONArray arrayMeteo = oggettoJSON.getJSONArray("list");
  
  //  	for(int i=0; i<arrayMeteo.size(); i++) {
    		
   // 		int pressione = (arrayMeteo.getJSONObject(i)).getInt("pressure");
   // 		String data = (arrayMeteo.getJSONObject(i)).getString("dt_txt");
    		
   // 		JSONObject finale = new JSONObject();
   // 		finale.put("Pressione", pressione);
   // 		finale.put("Data", data);
   // 		contenitore.add(finale);
   // 	}
    
   // 	return contenitore;
    //}
	
   
  
  //  public String SalvaDatiCitta(String nomeCitta) {
    	
    	
  //  	return ;
  //  }
    
}

