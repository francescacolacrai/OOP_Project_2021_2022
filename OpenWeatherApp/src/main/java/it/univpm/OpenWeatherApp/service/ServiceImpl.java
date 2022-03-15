package it.univpm.OpenWeatherApp.service;

//import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.PrintWriter;
import java.io.StringReader;
//import java.io.StringWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.InputStream;
//import java.io.OutputStream;

//import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONValue;

import java.util.Date;
//import java.util.Scanner;
import java.util.Vector;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Objects;
//import org.json.simple.parser.JSONParser;

//import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.HttpURLConnection;

import it.univpm.OpenWeatherApp.exceptions.FileNotFoundException;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.univpm.OpenWeatherApp.models.*;

@Service
public class ServiceImpl implements Service1{
	
	/** API KEY necessaria per ottenere dati da OpenWeather */
	private String API_KEY = "5420bdf83a983c382b72481b3e90aafe";
	
	/** Metodo che consente di ottenere la data e l'ora al momento della chiamata.
	 * 
	 * @return Stringa contenente data e ora.
	 */
	public String getData() {
		
        DateFormat formato = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
        Date data = new Date();
        return formato.format(data);
    }
	
	public JSONObject getForecastMeteo(String nomeCitta) {
		
		/** Richiesta */
		String url = "https://api.openweathermap.org/data/2.5/forecast?q=" +
				nomeCitta + "&appid=" + API_KEY + "&units=metric&lang=it";
		
		RestTemplate restT = new RestTemplate();
		JSONObject previsioneMeteo = new JSONObject(restT.getForObject(url, String.class));
		return previsioneMeteo;
		}
	
	public JSONObject getMeteo(String nomeCitta) {
		
		/** Richiesta */
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" +
				nomeCitta + "&appid=" + API_KEY + "&units=metric&lang=it";
		
		RestTemplate restT = new RestTemplate();
		JSONObject meteo = new JSONObject(restT.getForObject(url, String.class));
		meteo.put("\n\nData: ", getData());
		return meteo;
	}
	
	public String getPressure(String nomeCitta) {
		JSONObject dataForecast = getMeteo(nomeCitta);
		JSONObject main = (JSONObject)dataForecast.get("main");
		
		int pressione = (int)main.get("pressure");
		String data = getData();
		JSONObject datiEstratti = new JSONObject();
		datiEstratti.put("Data", data);
		datiEstratti.put("Pressione", pressione);
			
		return datiEstratti.toString();
	}
	
	public String getForecastPressure(String nomeCitta) {
		JSONObject dataForecast = getForecastMeteo(nomeCitta);
		JSONArray listArray = (JSONArray)dataForecast.get("list");
		JSONArray dati = new JSONArray();
		
		int pressione = 0;
		String data = "";
		
		for (int i = 0; i<listArray.length(); i++) {
			
			JSONObject holder = (JSONObject)listArray.get(i);
			JSONObject main = (JSONObject)holder.get("main");
			pressione = (int)main.get("pressure");
			
			data = (String) holder.get("dt_txt");
			JSONObject datiEstratti = new JSONObject();
			datiEstratti.put("Data", data);
			datiEstratti.put("Pressione", pressione);
			dati.put(datiEstratti);
		}
		return dati.toString();
	}
	
	public String salvaDati(String dati, String path) {
		
		File file = new File(path);
		
		try{
            if(file.exists() == false) {
                file.createNewFile();
            }
		}
		catch (Exception e) {
			System.out.println ("Error!");
		}
		
		try {
			int next;
			BufferedReader r = new BufferedReader (new StringReader (dati));
			BufferedWriter w = new BufferedWriter (new FileWriter (file, true));
			
			do {
				next = r.read();
				if (next != -1) {
					w.write ((char)next);
				}
			} while (next != -1);
				r.close();
				w.close();
		}
		catch (IOException e) { 
			System.out.println ("Error!");
		}
		return path;
	}
	
	public String salvaOgniTreOre(String nomeCitta, String path) {
		File file = new File(path);
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
		    
			@Override
		    public void run() {
		    	
		    	String actualPressure = getPressure(nomeCitta);

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
		}, 0, 1, TimeUnit.HOURS);
		
		return path;
	}	
	
	public JSONArray letturaDaFile(String path) throws IOException, FileNotFoundException {
		
		String lettura;
		File file = new File(path);
    	if (!file.exists()) throw new FileNotFoundException("File not found!");
    	
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
				
			JSONArray array = new JSONArray();
			array.put(lettura);
	
			return array;	
    }
	
	public JSONObject ConvertToJson(Citta citta) {
		
		JSONObject finale = new JSONObject();
		
		finale.put("citta", citta.getNome());
		finale.put("paese", citta.getPaese());
		finale.put("ID", citta.getId());
		
		JSONArray elencoPrevisioni = new JSONArray();
		
		for(Meteo previsioneMeteo : citta.getRaccoltaDatiMeteo()) {
			JSONObject previsione = new JSONObject();
			
			previsione.put("data", previsioneMeteo.getData());
			//previsione.put("main", previsioneMeteo.getMain());
			previsione.put("pressione", previsioneMeteo.getPressione());
			
			elencoPrevisioni.put(previsione);
		}
		
		finale.put("previsioni meteo", elencoPrevisioni);
		
		return finale;
	}
	
	public Citta setPrevisioni(JSONObject object) {
		Citta citta = new Citta();
		Vector<Meteo> previsioneDati = new Vector<Meteo>();
		
		JSONObject datiCitta = (JSONObject)object.get("city");
		JSONArray listaDati = (JSONArray)object.get("main");
		
		citta.setNome((String)datiCitta.get("name"));
		citta.setPaese((String)datiCitta.get("country"));
		citta.setId((long)datiCitta.get("id"));
		//citta.setLat((double)datiCitta.get("lat"));
		//citta.setLong((double)datiCitta.get("long"));
		
		try {
			for(int j=0; j<listaDati.length(); j++) {
				JSONObject listaInfo = listaDati.getJSONObject(j);
				Meteo previsioneMeteo = new Meteo();
			
				previsioneMeteo.setData(getData());
				previsioneMeteo.setPressione((int)listaInfo.get("pressure"));
			
				previsioneDati.add(previsioneMeteo);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		citta.setRaccoltaDatiMeteo(previsioneDati);
		
		return citta;
	}
	
	/** Metodo alternativo per leggere dal file i dati scritti 
	
	public String ottieniDaFile(String path, String citta) throws IOException{
		
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
	*/

	/**
	public String leggiFile(String path, String nomeCitta) {
		File file = new File(path);
		
		//if(file.exists() == false) throw new FileNotFoundException("File not found!");
		
		String valoriPressione = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer buffer=new StringBuffer();
			String linea;

	        while((linea=reader.readLine())!=null) {
	            buffer.append(linea);
	            linea = linea.substring(7, linea.length());

	            linea = reader.readLine();

	            valoriPressione = String.valueOf(getForecastPressure(nomeCitta));
	        }
	        reader.close();
	    }
	    catch (IOException e) {
	    	System.out.print("ERRORE DI INPUT/OUTPUT");
	    }
		
	    return valoriPressione;
	}
	*/
	
	
	//------------------------//

	/**
	public JSONObject getPrevisioniJSON(String nomeCitta) {
		
		/** Richiesta 
		String url = "https://api.openweathermap.org/data/2.5/forecast?q=" +
				nomeCitta + "&appid=" + API_KEY + "&units=metric&lang=it";
		JSONObject previsione = null;
		
		try {
			URLConnection connessione = new URL(url).openConnection();
			InputStream input = connessione.getInputStream();
			String StringaDatiJson = "";
			String line = "";
			
			try {
				BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
				//Scanner in = new Scanner(buffer);
				
				//while((line = in.nextLine()) != null)
				while((line = buffer.readLine()) != null)
				StringaDatiJson += line; //salva il JSON nella stringa
				//in.close();
			}
			finally {
				input.close();
				
			}
			previsione = (JSONObject)JSONValue.parseWithException(StringaDatiJson); //parsing dei dati
		}
		catch(IOException e) {
			System.out.println("Errore!");
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return previsione;
	}
	*/
	
	
	/**
	public String salvaPrevisioni(String nomeCitta) throws IOException{
		Citta citta = setPrevisioni(getPrevisioniJSON(nomeCitta));
		
		JSONObject oggetto = new JSONObject();
		oggetto = this.ConvertToJson(citta);
		
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String oggi = data.format(new Date());
		
		String nomeFile = nomeCitta + "_" + oggi;
		String path = System.getProperty("user.home") + "/Previsioni/" + nomeFile + "Forecast.txt";

		try {
			PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			outputFile.write(oggetto.toString());
			
			outputFile.close();
			System.out.println("File salvato in " + path);
		}
		catch (Exception e) {
			System.err.println("Errore: " + e);
		}
	
		return path;
	}
	*/
	
}