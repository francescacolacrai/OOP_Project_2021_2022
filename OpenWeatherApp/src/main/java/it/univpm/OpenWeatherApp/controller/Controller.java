package it.univpm.OpenWeatherApp.controller;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.OpenWeatherApp.service.Service1;

@RestController
public class Controller {

	@Autowired 
	private Service1 service;
	
	@GetMapping(value = "/getPrevisioni")
	public ResponseEntity<Object> getPrevisioni(){
		return new ResponseEntity<>(service.ConvertToJson(service.getPrevisioni(service.getPrevisioniJSON("Pisa"))), HttpStatus.OK);
	}
	
	
	//@GetMapping(value="/pressure")
	
	//@PostMapping("/pressione_min_e_max") 
	
	//@PostMapping("/hours")
	
	//@PostMapping(value = "/stats")
	
	
}
