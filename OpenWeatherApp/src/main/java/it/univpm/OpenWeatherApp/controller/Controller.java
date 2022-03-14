package it.univpm.OpenWeatherApp.controller;

import java.io.IOException;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.OpenWeatherApp.service.*;
//import it.univpm.OpenWeatherApp.models.*;

@RestController
public class Controller {

	@Autowired 
	static ServiceApi service = new ServiceApi();

	/**
	@GetMapping("/hello")
	public @ResponseBody String Saluta(){
		return "Hello!";
	}
	*/
	
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
		String valoriPressione = service.getDato(previsioniMeteo, "pressure", ',') + "\n";
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
	@GetMapping(value = "/getPisa")
		public ResponseEntity<Object> getPrevisione(){
		return new ResponseEntity<>(service.ConvertToJson(service.getPrevisioni(service.getPrevJson("Pisa"))), HttpStatus.OK);
	}
	*/
	/**
	@GetMapping(value = "/getPrev")
	public ResponseEntity<Object> getPrev(){
	return new ResponseEntity<>(service.getPrevisioniJSON("Pisa"), HttpStatus.OK);
}

	@GetMapping(value="/meteo")
    public ResponseEntity<Object> getMeteo(@RequestParam(name="citta") String cityName) {
		return new ResponseEntity<> (service.getPrevisioniJSON(cityName).toString(), HttpStatus.OK);
    }
	/**
	@GetMapping("/salva")
	public ResponseEntity<Object> salva(@RequestParam String nomeCitta) throws IOException{
		String path = service.salvaPrevisioni(nomeCitta);
		return new ResponseEntity<>(path, HttpStatus.OK);
	}
	*/
	//@PostMapping("/pressione_min_e_max")
	
	//@PostMapping("/hours")
	
	//@PostMapping(value = "/stats")
	
}
