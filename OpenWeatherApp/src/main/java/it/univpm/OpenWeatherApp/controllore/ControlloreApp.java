package it.univpm.OpenWeatherApp.controllore;

import it.univpm.OpenWeatherApp.models.Citta;
import it.univpm.OpenWeatherApp.service.DatiSalvati;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;


/**
 * Rotta  GET che mostra le previsioni ristrette (temperatura massima, minima, percepita e pressure)
 * per i 5 giorni successivi alla richiesta della città inserita dall'utente.
 *
 * @return un JSONObject contenente le previsioni meteo ristrette della città richiesta e
 *         le informazioni generali su di essa.
 */

@RestController
public class ControlloreApp {

    @Autowired
    Service service;

    @GetMapping(value = "/meteo")
    public ResponseEntity<Object> getMeteoCitta(@RequestParam String nomeCitta) {
        Citta citta = service.getCittafromApi(nomeCitta);

        JSONObject object = new JSONObject();
        DatiSalvati datiSalvati= new DatiSalvati();
        object = datiSalvati.datiSalvati();
        return new ResponseEntity<>(object.toString(), HttpStatus.OK);
    }
}
