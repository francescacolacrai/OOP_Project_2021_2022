package it.univpm.OpenWeatherApp.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.univpm.OpenWeatherApp.exceptions.*;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.univpm.OpenWeatherApp.models.*;
//import it.univpm.OpenWeatherApp.stats_and_filters.Statistiche;
import it.univpm.OpenWeatherApp.stats_and_filters.Errore;

/**
 * Classe che implementa l'interfaccia Service1 e contiene i metodi usati dal controller
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * 
 * @see Service1
 *
 */
@Service
public class ServiceImpl implements Service1{
	
	/** API KEY necessaria per ottenere dati da OpenWeather */
	private String API_KEY = "8e44562b0941908fea35b9a5a6ed46eb";
	
	/** Metodo che consente di ottenere la data e l'ora al momento della chiamata.
	 * 
	 * @return Stringa contenente data e ora.
	 */
	public String getData() {
		
        DateFormat formato = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
        Date data = new Date();
        return formato.format(data);
    }
	
	/** Questo metodo serve per ottenere le informazioni sulla città da OpenWeather
	 *  @param nomeCitta nome della città
	 *  @return un oggetto di tipo Citta contenente le informazioni sulla città
	 */
	public Citta getInfoCitta(String nomeCitta) {
		
		JSONObject object = getForecastMeteo(nomeCitta);
		
		Citta citta = new Citta(nomeCitta);
	
		try {
			JSONObject objCitta = object.getJSONObject("city");
			String country = (String) objCitta.get("country");
			int id = (int) objCitta.get("id");
			//double latitude = (double) objCitta.get("lat");
			//double longitude = (double) objCitta.get("lon");
			citta.setPaese(country);
			citta.setId(id);
			//citta.setLat(latitudine);
			//citta.setLong(longitudine);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return citta;
	}
	
	/** Questo metodo serve per ottenere le previsioni meteo da OpenWeather
	 *  @param nomeCitta nome della città richiesta
	 *  @return un JSONObject con le previsioni meteo ottenute 
	 * 
	 */
	public JSONObject getForecastMeteo(String nomeCitta) {
		
		/** Richiesta */
		String url = "https://api.openweathermap.org/data/2.5/forecast?q=" +
				nomeCitta + "&appid=" + API_KEY + "&units=metric&lang=it";
		
		RestTemplate restT = new RestTemplate();
		JSONObject previsioneMeteo = new JSONObject(restT.getForObject(url, String.class));
		return previsioneMeteo;
		}
	
	/** Questo metodo serve per ottenere i dati meteo da OpenWeather
	 *  @param nomeCitta nome della città richiesta
	 *  @return un JSONObject con i dati meteo effettivi 
	 * 
	 */
	public JSONObject getMeteo(String nomeCitta) {
		
		/** Richiesta */
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" +
				nomeCitta + "&appid=" + API_KEY + "&units=metric&lang=it";
		
		RestTemplate restT = new RestTemplate();
		JSONObject meteo = new JSONObject(restT.getForObject(url, String.class));
		meteo.put("\n\nData: ", getData());
		return meteo;
	}
	
	/** Questo metodo serve per ottenere le previsioni sulla pressione. 
	 *  Invoca il metodo getForecastMeteo(String nomeCitta)
	 *  @param nomeCitta nome della città
	 *  @return un oggetto di tipo Citta contenente le informazioni estratte sulla città
	 */
	public Citta getPrevisionePressione(String nomeCitta) {
		
		JSONObject obj = getForecastMeteo(nomeCitta);
		Citta citta = new Citta(nomeCitta);
		citta = getInfoCitta(nomeCitta);
		
		JSONArray arrayMeteo = obj.getJSONArray("list");
		JSONObject counter;
		
		Vector<Meteo> vector = new Vector<Meteo>(arrayMeteo.length());
		
		try {
			for (int i = 0; i<arrayMeteo.length(); i++) {
				
				Meteo meteo = new Meteo();
				counter = arrayMeteo.getJSONObject(i);
				meteo.setData(counter.getString("dt_txt"));
				JSONObject main = counter.getJSONObject("main");
				meteo.setPressione(main.getInt("pressure"));
				vector.add(meteo); 
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		citta.setRaccoltaDatiMeteo(vector);
		
		return citta;
	}
	
	/** Questo metodo serve per ottenere i dati effettivi sulla pressione. 
	 *  Invoca il metodo getMeteo(String nomeCitta)
	 *  @param nomeCitta nome della città
	 *  @return una stringa contenente le informazioni estratte sulla città
	 */
	public String getPressione(String nomeCitta) {
		JSONObject dataForecast = getMeteo(nomeCitta);
		JSONObject main = (JSONObject)dataForecast.get("main");
		
		int pressione = (int)main.get("pressure");
		String data = getData();
		JSONObject datiEstratti = new JSONObject();
		datiEstratti.put("Data", data);
		datiEstratti.put("Pressione", pressione);
			
		return datiEstratti.toString();
	}
	
	/**
	 * Questo metodo salva su file le previsioni meteo per i prossimi cinque giorni
	 * della città indicata dal parametro. 
	 * @param nomeCitta nome della città
	 * @param citta oggetto di tipo Citta
	 * @return una stringa contenente il path del file salvato.
	 * @throws IOException per gestire errori dovuti all'input dal file
	 */
	public String salvaPrevisioni(String nomeCitta, Citta citta) throws IOException {       
        
		JSONObject object = this.ConvertToJson(citta);
		
		String path = System.getProperty("user.dir") + "/forecast/" + nomeCitta + "ForecastPressure.txt";
        
		try{
			PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			
			file.println(object.toString());
			file.close();
		}
		
		catch (Exception e) {
			System.err.println("Errore!");
		}
        
		return path;
	}
	
	/** Questo metodo serve per salvare ogni 3 ore i dati effettivi della pressione.
	 *  Invoca il metodo getPressione(String nomeCitta)
	 *  
	 *  @param nomeCitta nome della città
	 *  @param path percorso in cui salvare il dati
	 *  @return stringa contenente il path in cui sono memorizzati i dati
	 */
	public String salvaOgniTreOre(String nomeCitta, String path) {
		File file = new File(path);
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
		    
			@Override
		    public void run() {
		    	
		    	String actualPressure = getPressione(nomeCitta);

		    			try{
		    			    if(!file.exists()) {
		    			        file.createNewFile();
		    			    }

		    			    FileWriter fileWriter = new FileWriter(file, true);
		    				BufferedWriter buff = new BufferedWriter(fileWriter);
		    			    buff.write(actualPressure.toString());
		    			    buff.write("\n");
		    			    
		    			    buff.close();
		    			    
		    			} catch(IOException e) {
		    			    System.out.println(e);
		    			}
		    }
		}, 0, 1, TimeUnit.SECONDS);
		
		return path;
	}	
	
	/** Questo metodo legge i dati memorizzati sul file 
	 *  @param path il percorso del file da cui leggere dati
	 *  @return l'oggetto json contenente i dati memorizzati
	 *  @throws IOException per gestire errori dovuti all'input dal file
	 *  @throws FileNonTrovatoException per gestire errori dovuti all'assenza del file indicato
	 */
	public JSONArray letturaDaFile(String path) throws IOException, FileNonTrovatoException {
		
		String lettura = "";
		File file = new File(path);
    	if (!file.exists()) throw new FileNonTrovatoException("File not found!");
    	
		BufferedReader buff = new BufferedReader(new FileReader(path));
		
			try {
			    StringBuilder stringa = new StringBuilder();
			    String line = buff.readLine();

			    while (line != null) {
			        stringa.append(line);
			        stringa.append(System.lineSeparator());
			        line = buff.readLine();
			    }
			    lettura = stringa.toString();
			} finally {
			    buff.close();
			}
				JSONArray array = new JSONArray(lettura);
			return array;	
    }

	/** Questo metodo legge i dati memorizzati sul file 
	 *  @param path il percorso del file da cui leggere dati
	 *  @return stringa contenente i dati memorizzati
	 *  @throws IOException per gestire errori dovuti all'input dal file
	 */
	public String ottieniDaFile(String path) throws IOException, FileNonTrovatoException{
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		String everything;
		
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    everything = sb.toString();
			    
			} finally {
			    br.close();
			}
			
			return everything;
		}
	
	/** Questo metodo serve per convertire un oggetto Citta in Json
	 *  @param citta oggetto di tipo Citta che si vuole convertire
	 *  @return oggetto json con i dati della citta passata
	 */
	public JSONObject ConvertToJson(Citta citta) {
		
		JSONObject finale = new JSONObject();
		
		finale.put("citta", citta.getNome());
		finale.put("paese", citta.getPaese());
		finale.put("ID", citta.getId());
		
		JSONArray elencoPrevisioni = new JSONArray();
		
		for(int i=0; i<(citta.getRaccoltaDatiMeteo()).size(); i++) {
			JSONObject previsioneMeteo = new JSONObject();
			previsioneMeteo.put("data", (citta.getRaccoltaDatiMeteo()).get(i).getData());
			previsioneMeteo.put("pressure", (citta.getRaccoltaDatiMeteo()).get(i).getPressione());
		
			elencoPrevisioni.put(previsioneMeteo);
		}
		
		finale.put("previsioni meteo", elencoPrevisioni);
		
		return finale;
	}
	
	/**
	 * Metodo che legge i file su cui sono salvate le informazioni relative alla pressione per 5 giorni. 
	 * Dopo aver salvato in un ArrayList di JSONArray le informazioni di ogni città, vengono calcolate
	 * le statistiche sulla pressione.
	 * 
	 * @param citta rappresenta i nomi delle città su cui si vogliono fare statistiche. 
	 * 		  I valori possibili sono "Ancona", "Pisa", "Torino" e "Roma"
	 * @throws CittaNonTrovataException se la città inserita non è tra quelle indicate
	 * @throws FileNonTrovatoException se il percorso non esiste
	 * @throws IOException in caso di errore durante la lettura del file
	 */
	public ArrayList<JSONArray> letturaDatiPressione(ArrayList<String> citta) 
			throws CittaNonTrovataException, FileNonTrovatoException, IOException {
		
		Iterator<String> iter = citta.iterator();
		Iterator<String> iter2 = citta.iterator();
		ArrayList<JSONArray> datiPressione = new ArrayList<JSONArray>();
		ArrayList<JSONArray> info = new ArrayList<JSONArray>();
		
		for(int i=0; i<citta.size(); i++) {
			if(!(citta.get(i).equals("Ancona") || citta.get(i).equals("Roma") || citta.get(i).equals("Pisa") 
					|| citta.get(i).equals("Torino")))
				throw new CittaNonTrovataException("Nel file non sono presenti i dati relativi a" +  citta.get(i));
		}
		
		while(iter.hasNext()) {
			String path = System.getProperty("user.dir") + "/meteo/" + iter.next() + "Pressure.txt";
			
			try {
				JSONArray array = letturaDaFile(path);
				datiPressione.add(array);
			}
			catch(FileNonTrovatoException e) {
				System.out.println(e.getMessaggio());
			}	
		}
		
		int count = 0;
		while(iter2.hasNext()) {
			
			JSONArray stats = new JSONArray();
			stats.put(iter2.next());
			String data = "";
			
			int i = 0;
			
			while(i < datiPressione.get(count).length()) {
				JSONArray pressioneGiornaliera = (datiPressione.get(count)).getJSONArray(i);
				
				int media = 0, somma = 0, j = 0, massima = 0, minima = 1000;
				double varianza = 0;
				
				while(j < pressioneGiornaliera.length()) {
					JSONObject obj = pressioneGiornaliera.getJSONObject(j);
					somma += obj.getInt("pressione");
					data = obj.getString("data").substring(0,10);
					if(obj.getInt("pressione") > massima)
						massima = obj.getInt("pressione");
					if(obj.getInt("pressione") < minima)
						minima = obj.getInt("pressione");
					
					j++;
				}
				
				media = somma / j;
				
				JSONObject dati = new JSONObject();
				dati.put("data", data);
				dati.put("massima", massima);
				dati.put("minima", minima);
				dati.put("media", media);
				
				int k = 0;
				int scartiQuadMedi = 0;
				while(k < pressioneGiornaliera.length()) {
					JSONObject obj2 = pressioneGiornaliera.getJSONObject(k);
					obj2.getInt("pressione");
			        scartiQuadMedi += Math.pow((obj2.getInt("pressione") - media), 2);
			       	k++;
			     }
				
				varianza = scartiQuadMedi / j;
				dati.put("varianza", varianza);
				stats.put(dati);
				
				i++;
			}
	
			info.add(stats);
			count++;
		}
		
		return info;
	
	}
	
	/**
	 * Questo metodo serve per raccogliere le informazioni dall file di ogni città inserita 
	 * e calcola statistiche e le filtra
	 * 
	 * @param citta contiene i nomi di tutte le città su cui si vogliono fare statistiche e applicare i filtri.
	 * @param errore è la soglia con cui si vuole filtrare
	 * @param valore esprime il filtro che si vuole applicare. I valori consentiti sono "$lt", "$gt" e "=".
	 * @param periodo rappresenta i giorni di cui si vuole fare la previsione (da 1 a 5).
	 * @return restituisce l'ArrayList di JSONObject filtrati secondo i filtri indicati.
	 * @throws CittaNonTrovataException se l'utente inserisce città di cui non sono presenti i file
	 * @throws PeriodoErratoException se l'utente inserisce valori minori di 1 o maggiori di 5.
	 * @throws ValoreErratoException se l'utente inserisce o una stringa non ammessa per il valore.
	 * @throws IOException se si verificano problemi nella lettura del file.
	 */
	public ArrayList<JSONObject> letturaErrore(ArrayList<String> citta, int errore, String valore, int periodo) 
			throws CittaNonTrovataException, PeriodoErratoException, ValoreErratoException, FileNonTrovatoException, IOException {
		
			for(int i=0; i<citta.size(); i++) {
				if(!(citta.get(i).equals("Ancona") || citta.get(i).equals("Roma") || citta.get(i).equals("Pisa") || 
						citta.get(i).equals("Torino"))) 
					throw new CittaNonTrovataException("Città non presente nel file!");
			}
		
			if(periodo < 1 || periodo > 5)
				throw new PeriodoErratoException(periodo + " non è valido!");
			
			if(!(valore.equals("$gt") || valore.equals("$lt") || valore.equals("=")))
				throw new ValoreErratoException(valore + " non è accettabile!");
		
			Iterator<String> iter = citta.iterator();
			
			ArrayList<JSONArray> pressione = new ArrayList<JSONArray>();
			ArrayList<JSONObject> errori = new ArrayList<JSONObject>();
			
			while(iter.hasNext()) {
		
				String path = System.getProperty("user.dir") + "/forecasts/" + iter.next() + "ForecastPressure.txt";
				JSONArray array = letturaDaFile(path);
				JSONArray datiPressione = new JSONArray();
				
				for(int i=0; i<array.length(); i++) {
					
					JSONArray pressione2 = new JSONArray();
					JSONObject dati = array.getJSONObject(i);
					JSONArray array2 = dati.getJSONArray("previsioni meteo");
					
					
					for(int j=0; j<array2.length();j++) {
						
						JSONObject pressure = new JSONObject();
						JSONObject oggetto = array2.getJSONObject(j);
						
						pressure.put("data", oggetto.get("data"));
						pressure.put("pressure", oggetto.get("pressure"));
						pressione2.put(pressure);
					}
					
					datiPressione.put(pressione2);

				}
				
				pressione.add(datiPressione);
				
			}
			
			Errore errore2 = new Errore();
			errori = errore2.calcolaErrore(citta, pressione, errore, valore, periodo);
			
			return errori;
			
	}
}