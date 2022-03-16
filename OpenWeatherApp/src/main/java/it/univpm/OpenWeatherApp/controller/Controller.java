package it.univpm.OpenWeatherApp.controller;

//import org.json.JSONObject;

import java.io.IOException;
import java.lang.invoke.WrongMethodTypeException;

//import org.json.JSONArray;
import it.univpm.OpenWeatherApp.stats_and_filters.Statistiche;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;

import it.univpm.OpenWeatherApp.exceptions.*;
import it.univpm.OpenWeatherApp.service.*;
import it.univpm.OpenWeatherApp.models.*;

//import org.json.simple.parser.ParseException;

/**
 * Classe che gestisce le chiamate dell'utente
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * 
 * @see ServiceImpl
 *
 */

@RestController
public class Controller {

	@Autowired 
	static ServiceImpl service = new ServiceImpl();
	Statistiche stats = new Statistiche();

	/**
	@GetMapping("/hello")
	public @ResponseBody String Saluta(){
		return "Hello!";
	}
	*/
	/**
	@GetMapping(value="/forecastMeteo")
	public String getPrevisioneMeteo(@RequestParam(name = "citta") String nomeCitta) {
		String path = System.getProperty("user.dir") + "/forecasts/" + nomeCitta + "Forecast.txt";
		String forecastMeteo =  service.getForecastMeteo(nomeCitta);
		System.out.println(service.salvaDati(forecastMeteo, path));
		return forecastMeteo;
	}
	
	@GetMapping(value="/forecastPressure")
	public static String previsionePressione(@RequestParam(name="citta") String nomeCitta) {
		String path = System.getProperty("user.dir") + "/forecasts/" + nomeCitta + "ForecastPressure.txt";
		String previsioniMeteo = service.getForecastMeteo(nomeCitta);
		System.out.println(previsioniMeteo);
		String dati = "";
		String data = service.getDato(previsioniMeteo, "dt_txt", '}') + "\n";
		String valoriPressione = service.getDato(previsioniMeteo, "pressure", ',');
		dati = data + valoriPressione;
		System.out.println(service.salvaDati(dati, path));
		return dati;
	}
	
	@GetMapping(value="/meteo")
	public static String getDatiMeteo(@RequestParam(name="citta") String nomeCitta){
		String path = System.getProperty("user.dir") + "/meteo/" + nomeCitta + ".txt";
		String meteo = service.getMeteo(nomeCitta);
		System.out.println(service.salvaDati(meteo, path));
		return meteo;
	}

	@GetMapping (value = "/pressure")
	public static String pressione(@RequestParam(name="citta") String cityName) throws IOException {
	String path = System.getProperty("user.dir") + "/meteo/" + cityName + "Pressure.txt";
	String meteo = service.getMeteo(cityName);
	
	String data = "\nData: " + service.getData();
	String pressione = data + "\n" + service.getDato(meteo, "pressure", ',') + "\n";
	System.out.println(service.salvaDati(pressione, path));
	return pressione;
	}
	
	@GetMapping(value = "/stampa")
	public static String Stampa(@RequestParam(name="citta") String cityName) throws IOException {
		String path = System.getProperty("user.dir") + "/meteo/" + cityName + "Pressure.txt";
		String dati = service.ottieniDaFile(path, cityName);
		return dati;
	}
	*/
	
	//--------------//

	/**
	@GetMapping(value="/getForecast")
		public static JSONObject getForecast(@RequestParam(name="nome") String nomeCitta) {
		String path = System.getProperty("user.dir") + "/forecasts/" + nomeCitta + "Forecast.txt";
		String meteo = service.getMeteo(nomeCitta);
	}
	*/
	
	/**
	@GetMapping(value = "/getMeteo")
		public static JSONObject getPrev(@RequestParam (name="citta")String nomeCitta) throws IOException{
			JSONObject meteo = service.getPrevisioniJSON(nomeCitta);
			//Citta citta = service.getPrevisioni(meteo);
			//JSONObject finale = service.ConvertToJson(citta);
			
			//String path = service.salvaPrevisioni(nomeCitta);
			//JSONArray array = service.LetturaStorico(path);
			
			return meteo;
	}
	*/

	/**
	@GetMapping(value = "/pressure")
		public ResponseEntity<Object> getPrevisione(@RequestParam(name="citta")String nomeCitta){
			return new ResponseEntity<>(service.getPressure(nomeCitta).toString(), HttpStatus.OK);
		}
	
	
	@GetMapping(value = "/forecastPressure")
		public ResponseEntity<Object> getPrev(@RequestParam(name="citta")String nomeCitta){
			return new ResponseEntity<>(service.getForecastPressure(nomeCitta), HttpStatus.OK);
		}

	@GetMapping(value="/meteo")
    	public ResponseEntity<Object> getMeteo(@RequestParam(name="citta") String cityName) {
			return new ResponseEntity<> (service.getMeteo(cityName).toString(), HttpStatus.OK);
    	}
	
	@GetMapping(value="/forecastMeteo")
		public ResponseEntity<Object> getForecastMeteo(@RequestParam(name="citta") String cityName) {
			return new ResponseEntity<> (service.getForecastMeteo(cityName).toString(), HttpStatus.OK);
    	}
	*/
	
	/**
	@GetMapping("/saveForecastP")
		public ResponseEntity<Object> salvaPrevisioni(@RequestParam(name="citta") String nomeCitta) {
			String path = System.getProperty("user.dir") + "/forecasts/" + nomeCitta + "ForecastPressure.txt";
			String forecastPressure = service.getPrevisionePressure(nomeCitta);
			return new ResponseEntity<>(service.salvaDati(forecastPressure, path), HttpStatus.OK);
		}
	*/
	/*
	@GetMapping(value="/savePressure")
		public ResponseEntity<Object> salvaPressione(@RequestParam(name="citta") String nomeCitta) {
		String path = System.getProperty("user.dir") + "/meteo/" + nomeCitta + "Pressure.txt";
		String pressure = service.getPressure(nomeCitta);
		return new ResponseEntity<>((service.salvaDati(pressure, path)), HttpStatus.OK);
	}
	*/
	
	@GetMapping(value="/infoMeteo")
		public ResponseEntity<Object> getInfoMeteo(@RequestParam(name="citta")String nomeCitta){
			Citta citta = service.getPrevisionePressione(nomeCitta);
			JSONObject obj = service.ConvertToJson(citta);
			return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
	}
	
	
	@GetMapping(value="/previsionePressione")
    public ResponseEntity<Object> getPressureF(@RequestParam(name="citta") String nomeCitta) {    
		return new ResponseEntity<> (service.getPrevisionePressione(nomeCitta).toString(), HttpStatus.OK);
    }

	@GetMapping(value="/pressione")
    public ResponseEntity<Object> getPressure(@RequestParam(name="citta") String nomeCitta) {    
		return new ResponseEntity<> (service.getPressione(nomeCitta).toString(), HttpStatus.OK);
    }
	
	@GetMapping(value="/salvaPrevisioniPressione")
	public ResponseEntity<Object> salva(@RequestParam(name="citta") String nomeCitta) throws IOException{
		Citta citta = service.getPrevisionePressione(nomeCitta);
		return new ResponseEntity<>((service.salvaPrevisioni(nomeCitta, citta)), HttpStatus.OK);
	}
	
	@GetMapping(value="/saveHourly")
		public ResponseEntity<Object> salvaOgniTreOre(@RequestParam(name="citta") String nomeCitta) {
			String path = System.getProperty("user.dir") + "/meteo/" + nomeCitta + "Pressure.txt";
			return new ResponseEntity<>((service.salvaOgniTreOre(nomeCitta, path)), HttpStatus.OK);
	}
	
	@GetMapping(value="/lettura")
		public ResponseEntity<Object> getCitta(@RequestParam(name="citta") String nomeCitta) throws IOException, FileNonTrovatoException{
			String path = System.getProperty("user.dir") + "/forecasts/" + nomeCitta + "ForecastPressure.txt";
			return new ResponseEntity<>(service.ottieniDaFile(path), HttpStatus.OK);
	}
	
	
	//@PostMapping("/pressione_min_e_max")

	
	//@PostMapping("/hours")
	
	@PostMapping(value = "/stats")
	public ResponseEntity<Object> stats(@RequestBody String body)throws IOException{
		JSONObject rq = new JSONObject(body);
		String valore = rq.getString("valore");
		String nomeCita = rq.getString("citta");
		try {
			if (valore.equals("max"))
				return new ResponseEntity<>(stats.statistichePressione(nomeCita,true).toString(), HttpStatus.OK);
			else if (valore.equals("min"))
				return new ResponseEntity<>(stats.statistichePressione(nomeCita,false).toString(), HttpStatus.OK);
			else throw new WrongMethodTypeException(valore + "tipo non ammesso");
		} catch (WrongMethodTypeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
