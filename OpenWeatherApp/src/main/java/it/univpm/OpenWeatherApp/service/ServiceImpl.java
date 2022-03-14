package it.univpm.OpenWeatherApp.service;

//import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
//import java.io.PrintWriter;
import java.io.StringReader;
//import java.io.StringWriter;
//import java.io.StringWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.InputStream;
//import java.io.OutputStream;

//import org.json.*;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONValue;

import java.util.Date;
//import java.util.Scanner;
//import java.util.Vector;
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

import java.text.SimpleDateFormat;
import java.text.DateFormat;

// import it.univpm.OpenWeatherApp.models.*;

@Service
public class ServiceImpl implements Service1{
	
	/** API KEY necessaria per ottenere dati da OpenWeather */
	private String API_KEY = "5420bdf83a983c382b72481b3e90aafe";
	
	//--------------------------//
	/** Questo metodo funziona **/
	//-------------------------//
	
	/** Metodo che consente di ottenere la data e l'ora al momento della chiamata.
	 * 
	 * @return Stringa contenente data e ora.
	 */
	public String getData() {
		
        DateFormat formato = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
        Date data = new Date();
        return formato.format(data);
    }
	
	public String getForecastMeteo(String nomeCitta) {
		
		/** Richiesta */
		String url = "https://api.openweathermap.org/data/2.5/forecast?q=" +
				nomeCitta + "&appid=" + API_KEY + "&units=metric&lang=it";
		RestTemplate restT = new RestTemplate();
		String previsione = restT.getForObject(url, String.class);
		return previsione;
		}
	
	public String getMeteo(String nomeCitta) {
		/** Richiesta */
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" +
				nomeCitta + "&appid=" + API_KEY + "&units=metric&lang=it";
		RestTemplate restT = new RestTemplate();
		String previsione = restT.getForObject(url, String.class);
		previsione += "\n\nData: " + getData() + "\n\n";
		return previsione;
	}
	
	public String getDato(String dato, String statistica, char finalChar) {
		
    	BufferedReader r = new BufferedReader (new StringReader(dato));
		String ricercaStatistica = "";
		
		try {
			char next;
			while((next = (char)r.read()) != -1) {
				if (next == '\"') {
					while((next = (char)r.read()) != -1) {
						ricercaStatistica += next;
						
						if (next == '\"') {
							if(ricercaStatistica.compareTo("\"" + statistica + "\"") != 0) 
								ricercaStatistica = "\"";
							break;
						}
					}
					if(ricercaStatistica.compareTo("\"" + statistica + "\"") == 0) {
							while((next = (char)r.read()) != finalChar) 
								ricercaStatistica += next;
							
							if(next == finalChar) break;
						break;
					}	
				}
			}
			r.close();
		}
		catch(IOException e) {
			System.out.println("I/O Error!");
		}
		catch(Exception e) {
			System.out.println("Error!");
		}
		return ricercaStatistica;
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
	
	
	//------------------------//
	
	/*
	public JSONArray getPressure(String nomeCitta) {
		JSONObject dataForecast = getPrevJson(nomeCitta);
		JSONArray listArray = (JSONArray)dataForecast.get("list");
		//JSONObject holder = new JSONObject();
		JSONArray dati = new JSONArray();
		
		int pressione = 0;
		String data = "";
		
		
		for (int i = 0; i<listArray.size(); i++) {
			
			JSONObject holder = (JSONObject)listArray.get(i);
			JSONObject main = (JSONObject)holder.get("main");
			pressione = (int)main.get("pressure");
			
			data = (String) holder.get("dt_txt");
			JSONObject datiEstratti = new JSONObject();
			datiEstratti.put("Data", data);
			datiEstratti.put("Pressione", pressione);
			dati.add(datiEstratti);
		}
		return dati;
	}
	*/
	
	
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
	
	public Citta getPrevisioni(JSONObject object) {
		Citta citta = new Citta();
		Vector<Meteo> previsioneDati = new Vector<Meteo>();
		
		JSONObject datiCitta = (JSONObject)object.get("city");
		JSONArray listaDati = (JSONArray)object.get("list");
		
		citta.setNome((String)datiCitta.get("name"));
		citta.setPaese((String)datiCitta.get("country"));
		citta.setId((long)datiCitta.get("id"));
		//citta.setLat((double)datiCitta.get("lat"));
		//citta.setLong((double)datiCitta.get("long"));
		
		for(int j=0; j<listaDati.size(); j++) {
			JSONObject listaInfo = (JSONObject)listaDati.get(j);
			Meteo previsioneMeteo = new Meteo();
			
			JSONObject meteoMain = (JSONObject)listaInfo.get("main");
			
			previsioneMeteo.setData((String)listaInfo.get("dt_txt"));
			previsioneMeteo.setPressione((int)meteoMain.get("pressure"));
			
			previsioneDati.add(previsioneMeteo);
		}
		
		citta.setRaccoltaDatiMeteo(previsioneDati);
		
		return citta;
	}
	
	public JSONObject ConvertToJson(Citta citta) {
		
		JSONObject finale = new JSONObject();
		//HashMap<String, Object> finale = new HashMap<String, Object>();
		
		finale.put("citta", citta.getNome());
		finale.put("paese", citta.getPaese());
		finale.put("ID", citta.getId());
		
		//ArrayList<Object> elencoPrevisioni = new ArrayList<Object>();
		JSONArray elencoPrevisioni = new JSONArray();
		
		for(Meteo previsioneMeteo : citta.getRaccoltaDatiMeteo()) {
		//	HashMap<String, Object> previsione = new HashMap<String, Object>();
			JSONObject previsione = new JSONObject();
			
			previsione.put("data", previsioneMeteo.getData());
			previsione.put("main", previsioneMeteo.getMain());
			previsione.put("pressione", previsioneMeteo.getPressione());
			
			elencoPrevisioni.add(previsione);
		}
		
		finale.put("previsioni meteo", elencoPrevisioni);
		
		return finale;
	}
	
	public String salvaPrevisioni(String nomeCitta) throws IOException{
		Citta citta = getPrevisioni(getPrevisioniJSON(nomeCitta));
		
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
	
	
	public JSONArray LetturaStorico(String path) throws IOException {
		
		String lettura;
			
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
			array.add(lettura);
	
			return array;	
    }
	
	/**
	public String Stampa() {
		return "Ciao!";
	}
	*/
	
	/**
 public String getPrevisioniMeteo(String nomeCitta) {
    	
    	String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + nomeCitta + "&appid="+API_KEY;
        String datiMeteo = "";

        try {

            URLConnection connessione = (URLConnection) new URL(url).openConnection();
            Scanner input = new Scanner(new BufferedReader(new InputStreamReader(connessione.getInputStream())));
            datiMeteo += "\nData: " + getDateTime();
            datiMeteo += "\n";
            datiMeteo += input.nextLine();
            input.close();

        } catch (IOException e) {
        	System.out.println("Error!");
        } catch (Exception e) {
        	System.out.println("General error!");
        }
       
       return datiMeteo;
    }
    
	public String cercaStatistica(String forecast, String stat, int finalChar) {
    	
    	String cercaStat = "";
    	
    	BufferedReader r = new BufferedReader (new StringReader (forecast));
		BufferedWriter w = new BufferedWriter (new StringWriter ());

    	try {
    		int next;
    		while((next = r.read()) != -1 ) {

//valori int di alcuni segni di punteggiatura:  34 = "    125 = }    44 = ,   58 = :
    			
    			if (next == 34) {
    				while((next = r.read()) != -1 ) {
    					cercaStat += (char)next;

    					 if (next == 34) {
    						 if ((cercaStat.compareTo("\"" + stat + "\"") != 0)) {
    							 cercaStat = "\""; 
    						 }break; 
    					 }
    				} 

    				if ((cercaStat.compareTo("\"" + stat + "\"") == 0)) {
						while((next = r.read()) != ',' ) 
							cercaStat += (char)next;
						break;	
					}
    			}
    		}
    		if (cercaStat.compareTo("\"") == 0) 
    			cercaStat="\""+ stat + "\": " + "-1";
    		
    		r.close();
	    	w.close();
    	}
    	catch (IOException e) { 
			System.out.println ("Error!");
    	}
    	catch(Exception e){
    		System.out.println ("General error!");
    	}
    	return cercaStat;
    }
	*/
	
}