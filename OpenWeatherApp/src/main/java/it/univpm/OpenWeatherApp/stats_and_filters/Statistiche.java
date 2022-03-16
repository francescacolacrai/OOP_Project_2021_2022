package it.univpm.OpenWeatherApp.stats_and_filters;

//import java.util.Vector;

import org.json.JSONObject;

import it.univpm.OpenWeatherApp.models.*;
import it.univpm.OpenWeatherApp.service.ServiceImpl;

/** Questa è una classe che implementa i vari metodi necessari per calcolare
 *  le  statistiche richieste.
 *  
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 *
 */
public class Statistiche {
	
	ServiceImpl service = new ServiceImpl();
	
	/**
	 * Questo metodo serve per calcolare le medie giornaliere o su 5 giorni,
	 * in base al valore di flag.
	 * @param nome è il nome della città su cui si vogliono fare statistiche.
	 * @param flag è il parametro che consente di scegliere la media giornaliera
	 * 		  o su 5 giorni 
	 * @return JSONObject contenente il nome della città e le relative statistiche
	 */
	public JSONObject statistichePressione(String nome, boolean flag) {
		
		Citta citta = new Citta(nome);
		citta = service.getPrevisionePressione(nome);
		
		double pressioneMedia = 0;
		int sommaPressioni = 0;
		int cont = 0;
		String data = "";
		
		//seleziona il giorno nel formato della data ottenuto
		data += (citta.getRaccoltaDatiMeteo().get(0).getData()).charAt(0);
		data += (citta.getRaccoltaDatiMeteo().get(0).getData()).charAt(1);
		
		String realData = data;
		double scartiQuadMedi = 0, varianza = 0;
		
		int pressioneMax = 0, pressioneMin = citta.getRaccoltaDatiMeteo().get(cont).getPressione();
		if(flag) {
			while(data.equals(realData)) {
				sommaPressioni += (citta.getRaccoltaDatiMeteo().get(cont)).getPressione();
				if(citta.getRaccoltaDatiMeteo().get(cont).getPressione() > pressioneMax)
					pressioneMax = citta.getRaccoltaDatiMeteo().get(cont).getPressione();
				if (citta.getRaccoltaDatiMeteo().get(cont).getPressione() <pressioneMin)
					pressioneMin = citta.getRaccoltaDatiMeteo().get(cont).getPressione();
				cont++;
				realData = "";
				realData += (citta.getRaccoltaDatiMeteo().get(cont).getData()).charAt(0);
				realData += (citta.getRaccoltaDatiMeteo().get(cont).getData()).charAt(1);
			}
		
			pressioneMedia = sommaPressioni / cont;
			
			cont = 0;
			realData = data;
			
			while(data.equals(realData)) {
				scartiQuadMedi += Math.pow((citta.getRaccoltaDatiMeteo().get(cont).getPressione()) - pressioneMedia, 2);
				cont++;
				realData = "";
				realData += (citta.getRaccoltaDatiMeteo().get(cont).getData()).charAt(0);
				realData += (citta.getRaccoltaDatiMeteo().get(cont).getData()).charAt(1);
			}
		}
		else {
			while(cont < citta.getRaccoltaDatiMeteo().size()) {
				sommaPressioni += (citta.getRaccoltaDatiMeteo().get(cont)).getPressione();
				if(citta.getRaccoltaDatiMeteo().get(cont).getPressione() > pressioneMax)
					pressioneMax = citta.getRaccoltaDatiMeteo().get(cont).getPressione();
				if (citta.getRaccoltaDatiMeteo().get(cont).getPressione() <pressioneMin)
					pressioneMin = citta.getRaccoltaDatiMeteo().get(cont).getPressione();
				cont++;
			}
		
			pressioneMedia = sommaPressioni / cont;
			
			cont = 0;
			
			while(cont < citta.getRaccoltaDatiMeteo().size()) {
				scartiQuadMedi += Math.pow((citta.getRaccoltaDatiMeteo().get(cont).getPressione()) - pressioneMedia, 2);
				cont++;
			}
			
		}
		varianza = scartiQuadMedi / cont;	//calcolo varianza
		
		JSONObject obj = new JSONObject();
		JSONObject datiPressione = new JSONObject();
		
		datiPressione.put("Pressione minima", pressioneMin);
		datiPressione.put("Pressione massima", pressioneMax);
		datiPressione.put("Pressione media", pressioneMedia);
		datiPressione.put("Varianza della pressione", varianza);
		
		obj.put("Nome città", nome);
		obj.put("Dati sulla visibilità", datiPressione);
		
		return obj;
	}
}
