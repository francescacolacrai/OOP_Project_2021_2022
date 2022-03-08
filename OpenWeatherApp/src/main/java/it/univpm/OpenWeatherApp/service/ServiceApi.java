package it.univpm.OpenWeatherApp.service;

import it.univpm.OpenWeatherApp.models.Citta;
import it.univpm.OpenWeatherApp.models.Meteo;
import org.json.simple.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public abstract class ServiceApi implements Service{
    private String API_KEY = "ce74fd08278903109816b3acfe7eb4fb";
    Vector<String> dati = new Vector<String>();


 public JSONObject getMeteoCitta(String citta) {

        String url = "api.openweathermap.org/data/2.5/forecast?q=" + citta + "&appid="+API_KEY;

        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(restTemplate.getForObject(url, Map.class)));

        return jsonObject;

    }
    public Citta getCittafromApi(String nome){
        JSONObject object = getNomeCitta(nome);
        Citta citta = new Citta(nome);
        citta = getCittafromApi(nome);

        return citta;


    }
}

