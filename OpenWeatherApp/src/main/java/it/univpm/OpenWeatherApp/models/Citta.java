package it.univpm.OpenWeatherApp.models;

import java.util.Vector;

/** Classe che descrive le proprietà della città e le previsioni meteo raccolte.
 * 
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 */
public class Citta {
	
	/** Nome della città */
	private String nomecitta;
	
	/** Paese a cui appartiene la città */
	private String paesecitta;
	
	/** Identificativo della città ottenuto dalle API di OpenWeather */
	private long idcitta;
	
	/** Latitudine della città */
	private double latitudine;
	
	/** Longitudine della città */
	private double longitudine;

	/** Pressione della città */
	private double pressione;

	public double getPressione() {
		return pressione;
	}

	public void setPressione(double pressione) {
		this.pressione = pressione;
	}

	/** Vettore che contiene i dati meteo della città */
	private Vector<Meteo> raccoltaDatiMeteo;
	
	/** Costruttore della classe
	 * @param nome Nome della città
	 * @param paese Paese della città
	 */
	public Citta(String nome, String paese) {
		nomecitta = nome;
		paesecitta = paese;
	}
	
	/** Costruttore della classe
	 * @param id Identificativo della città
	 */
	public Citta(long id) {
		idcitta = id;
	}

	/**
	 * Costruttore della classe che inizializza tutti gli attributi
	 *
	 * @param nome  Nome della città
	 * @param paese Paese di appartenenza della città
	 * @param id    Identificativo della città
	 * @param lat   Latitudine della città
	 * @param lon   Longitudine della città
	 */
	public Citta(String nome, String paese, long id, double lat, double lon, double pressure) {
		{
			nomecitta = nome;
			paesecitta = paese;
			idcitta = id;
			latitudine = lat;
			longitudine = lon;
			pressione = pressure;
			this.raccoltaDatiMeteo = new Vector<Meteo>();
		}
	}

	public Citta(String nome) {
	}

	/** Metodo getter
	 * @return Nome della città 
	 */
	public String getNome() {
		return nomecitta;
	}
	
	/**Metodo setter
	 * @param nomecitta
	 */
	public void setNome(String nomecitta) {
		this.nomecitta = nomecitta;
	}
	
	/** Metodo getter
	 * @return Paese a cui appartiene la città 
	 */
	public String getPaese() {
		return paesecitta;
	}
	
	/**Metodo setter
	 * @param paesecitta
	 */
	public void setPaese(String paesecitta) {
		this.paesecitta = paesecitta;
	}
	
	/** Metodo getter
	 * @return Id della città 
	 */
	public long getId() {
		return idcitta;
	}
	
	/**Metodo setter
	 * @param idcitta
	 */
	public void setId(long idcitta) {
		this.idcitta = idcitta;
	}
	
	/** Metodo getter
	 * @return Latitudine della città 
	 */
	public double getLat() {
		return latitudine;
	}
	
	/**Metodo setter
	 * @param latitudine
	 */
	public void setLat(double latitudine) {
		this.latitudine = latitudine;
	}
	
	/** Metodo getter
	 * @return Longitudine della città 
	 */
	public double getLong() {
		return longitudine;
	}
	
	/**Metodo setter
	 * @param longitudine 
	 */
	public void setLong(double longitudine) {
		this.longitudine = longitudine;
	}
	
	/** Metodo getter
	 * @return Vettore dei dati della città 
	 */
	public Vector<Meteo> getRaccoltaDatiMeteo() {
		return raccoltaDatiMeteo;
	}
	public void setRaccoltaDatiMeteo(Vector<Meteo> raccoltaDatiMeteo) {
		this.raccoltaDatiMeteo = raccoltaDatiMeteo;
	}
	
	/** Metodo che converte i dati delle previsioni fatte in stringa
	 * @return Stringa contenente le previsioni fatte
	 */
	public String showVector(){
		String risultato="";
		for(int i=0; i<raccoltaDatiMeteo.size(); i++) {
			risultato += raccoltaDatiMeteo.get(i).toString();
		}
		return risultato;	
	}
	
	/** Override del metodo toString() 
	 *  @return Lista completa di tutti i dati della città
	 */
	@Override
	public String toString() {
		System.out.println("I dati relativi alla città cercata sono: \n");
		String risultati = "nome=" + nomecitta + ", Paese=" + paesecitta + 
				",id=" + idcitta + ", latitudine=" + latitudine + 
				", longitudine=" + longitudine + "dati meteo raccolti=" +
				showVector() + ".";
		return risultati;
	}
}
