package it.univpm.OpenWeatherApp.controllore;

import it.univpm.OpenWeatherApp.models.Citta;
import it.univpm.OpenWeatherApp.service.Service;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ControlloreApp {

    @Autowired
     Service service;

    @GetMapping(value = "/meteo")
    public ResponseEntity<Object> getMeteoCitta(@RequestParam String nomeCitta) {
        Citta citta = service.getCittafromApi(nomeCitta);

        JSONObject object = new JSONObject();
        object = new JSONObject((Map) citta);
        return new ResponseEntity<>(object.toString(), HttpStatus.OK);
    }
}
