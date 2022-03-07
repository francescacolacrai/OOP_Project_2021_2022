package it.univpm.OpenWeatherApp.models;

/** Classe che racchiude i seguenti dati meteo della città:
 *  1. pressione ottenuta dalla previsione 
 *  2. pressione effettiva
 *  
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 */
public class Meteo {
	
	/** Dati meteo generali della città */
	private String main;
	
	/** Temperatura massima */
	private double t_max;
	
	/** Temperatura minima */
	private double t_min;
	
	/** Data del rilevamento */
	private String data;
	
	/** Costruttore della classe */
	public Meteo() {
		this.main = null;
		this.t_max = 0;
		this.t_min = 0;
		this.data = null;
	}
	
	/** Costruttore della classe 
	 * @param main Dati meteo generali
	 * @param data Data del rilevamento dati
	 */
	public Meteo(String main, String data) {
		super();
		this.main = main;
		this.data = data;
	}
	
	/** Costruttore della classe
	 * @param t_max Temperatura massima
	 * @param t_min Temperatura minima
	 */
	public Meteo(double t_max, double t_min) {
		super();
		this.t_max = t_max;
		this.t_min = t_min;
	}
	
	/** Costruttore della classe
	 * @param main Dati meteo generali
	 * @param t_min Temperatura minima
	 * @param t_max Temperatura massima
	 * @param data Data del rilevamento dati
	 */
	public Meteo(String main, double t_min, double t_max, String data) {
		this.main = main;
		this.t_min = t_min;
		this.t_max = t_max;
		this.data = data;
	}
	
	/** Override del metodo toString
	 *  @return valore della pressione prevista ed effettiva
	 */
	@Override
	public String toString() {
		String dati = "Main=" + main + ", temperatura minima=" + t_min +
				", temperatura massima=" + t_max + "data misurazione=" + data + ".";
		return dati;
	}
}
