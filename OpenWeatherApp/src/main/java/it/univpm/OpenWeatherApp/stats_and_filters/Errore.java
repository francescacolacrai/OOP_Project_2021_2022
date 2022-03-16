package it.univpm.OpenWeatherApp.stats_and_filters;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe che contiene i metodi per calcolare l'errore di predizione delle città 
 * in base al periodo indicato
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 *
 */
public class Errore {

	/** Costruttore della classe */
	public Errore() {}
	
	/**
	 * Metodo invocato dal metodo calcolaErrore. Trova il giorno, presente 
	 * nel file contenente i dati raccolti, da cui partire o terminare 
	 * il calcolo dell'errore.
	 * 
	 * @param pressioneCitta è il JSONArray che contiene le informazioni 
	 * 		  sulla pressione e relative date.
	 * @param periodo rappresenta i giorni di predizione, che indicano il 
	 * 		  giorno da ricercare.
	 * @return il JSONObject che contiene la data ricercata e la posizione 
	 * 		   in cui si trova nel JSONArray.
	 */
	public JSONObject selezionaGiorno(JSONArray pressioneCitta, int periodo) {
		
		String data = "";
		JSONArray primoGiorno = pressioneCitta.getJSONArray(0);
	
		JSONObject object = primoGiorno.getJSONObject(0);
		data = object.getString("data");
		
		String primaData = "";
		 primaData += data.charAt(0);
		 primaData += data.charAt(1);
		
		int numero = 0;
		int i = 0;
		
		while(numero < periodo) {
			
			boolean controllo = true;
			while(i < primoGiorno.length() && controllo) {
			
				String date = primoGiorno.getJSONObject(i).getString("data");
				
				String giorno = "";
				giorno += date.charAt(0);
				giorno += date.charAt(1);
				
				if(!giorno.equals(primaData))
					controllo = false;
			
				i++;
			}
			
			JSONObject obj = primoGiorno.getJSONObject(i-1);
			data = obj.getString("data");
			
			primaData = "";
			primaData += data.charAt(0);
			primaData += data.charAt(1);
			
			numero++;
		
		}
		
		JSONObject informazioni = new JSONObject();
		informazioni.put("date", primoGiorno.getJSONObject(i-1).getString("data"));
		informazioni.put("position", i-1);
		
		System.out.print(informazioni);
		
		return informazioni;
	}
	
	/**
	 * Calcola l'errore sulle predizioni di ogni città indicata nell'ArrayList. 
 	 * Richiama il metodo selezionaGiorno, che calcola il giorno e l'ora da cui 
 	 * partire per il calcolo.
	 * 
	 * @param citta è l'ArrayList di stringhe con i nomi delle città di cui si 
	 * 		  vuole conoscere la soglia di errore.
	 * @param infoPressione è l'ArrayList con le informazioni relative alla 
	 * 		  pressione di ogni città indicata in "citta".
	 * @param periodo indica il numero di giorni su cui si vuole calcolare la soglia 
	 * 		  di errore.
	 * @return l'ArrayList di JSONObject contenente le informazioni sull'errore di 
	 * 		   ogni città e come ultimo elemento il JSONObject con le città che rispettano 
	 * 		   le condizioni indicate da errore
	 */
	public ArrayList<JSONObject> calcolaErrore(ArrayList<String> citta, ArrayList<JSONArray> infoPressione, int periodo) {
		
		ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
		
		Iterator<JSONArray> ItPressure = infoPressione.iterator();
		Iterator<String> ItCitta = citta.iterator();
		
		while(ItPressure.hasNext() && ItCitta.hasNext()) {
			
			int erroreTot = 0;
			int giuste = 0;
			int cont = 0;
			
			//Inserisce le informazioni della città del posto i-esimo
			JSONArray pressioneCitta = new JSONArray();
			pressioneCitta = ItPressure.next();
			
			//vado a prendere le informazioni sulla visibilità solo del primo giorno di previsione
			//JSONArray app = new JSONArray();
			JSONArray info = pressioneCitta.getJSONArray(0); //ho tutto il primo giorno di visibilità
			
			
			//JSONObject dati = new JSONObject();
			JSONObject dati = this.selezionaGiorno(pressioneCitta, periodo);
		    String dataInizio = dati.getString("date");
		    int posIniz = dati.getInt("position");
		    int posFin = this.selezionaGiorno(pressioneCitta, periodo+1).getInt("position");
		    
		    while(posIniz < posFin) {
		    	
		    	for(int k=0; k<pressioneCitta.getJSONArray(periodo).length(); k++) {
		    		
		    		//JSONObject visibility = new JSONObject();
		    		JSONObject pressione = pressioneCitta.getJSONArray(periodo).getJSONObject(k);
		    		
		    		if(dataInizio.equals(pressione.getString("data"))) {
		    			int errore = (info.getJSONObject(posIniz).getInt("pressure")-pressione.getInt("pressure"));
		    			if(errore == 0)
		    				giuste++;
		    			erroreTot += errore;
		    			cont++;
		    		}
		    	}
		    	posIniz++;
		    	dataInizio = info.getJSONObject(posIniz).getString("data");
		  }
		    
		  try {
		    erroreTot /= cont;
		  } catch (ArithmeticException e) {
			  e.printStackTrace();
		  }
		  
		  JSONObject infoErrore = new JSONObject();
		  infoErrore.put("errore ", erroreTot);
		  infoErrore.put("previsioni indovinate su " + cont, giuste);
		  infoErrore.put("Città", ItCitta.next());
            arr.add(infoErrore);
		}
		
		return arr;
	}
}
