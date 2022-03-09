package it.univpm.OpenWeatherApp.service;

import it.univpm.OpenWeatherApp.models.Citta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


@org.springframework.stereotype.Service

public class ServiceApi implements Service{
    private String API_KEY = "ce74fd08278903109816b3acfe7eb4fb";

//metodo per prendere le previoni meteo di una citta

 public Citta getMeteoCitta(String citta) {

     JSONObject object;
     String url = "api.openweathermap.org/data/2.5/weather?q=" + citta + "&appid="+API_KEY;
     RestTemplate restTemplate = new RestTemplate();
     object = new JSONObject(restTemplate.getForObject(url, String.class));
     return object;

    }


    /**
     * Questo metodo richiama getMeteoCitta(String name) serve per salvare su file le previsioni meteo per
     * i prossimi cinque giorni della città passata come parametro.
     * @return una stringa contenente il path del file salvato.
     */

    public String save(String nomeCitta) throws IOException {

       Citta citta = getMeteoCitta(nomeCitta);

        JSONObject object = new JSONObject();
        DatiSalvati datiSalvati = new  DatiSalvati();

       object = datiSalvati.datiSalvati(citta);
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String today = date.format(new Date());
        String nomeFile =nomeCitta+"_"+today;
        String path = System.getProperty("user.dir")+nomeFile+".txt";

        try{

            PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            file_output.println(object.toString());
            file_output.close();

        }

        catch (Exception e) {
            System.err.println("Error: " + e);
        }

        return path;

    }


    @Override
    public JSONArray getPressioneCitta(String nome) {
        return null;
    }

    @Override
    public String SalvaDatiCitta(String nomeCitta) {
        return null;
    }


    public Citta getCittafromApi(String nome){
        JSONObject object = getNomeCitta(nome);
        Citta citta = new Citta(nome);
        citta = getCittafromApi(nome);

        return citta;


    }

    @Override
    public JSONObject getNomeCitta() {
        return null;
    }

    @Override
    public JSONObject getNomeCitta(String nomeCitta) {
        return null;
    }
}

