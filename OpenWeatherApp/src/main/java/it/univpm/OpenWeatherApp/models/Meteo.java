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
	//private double t_max;
	
	/** Temperatura minima */
	//private double t_min;
	
	/** Pressione */
	private int pressione;
	
	/** Data del rilevamento */
	private String data;
	
	/** Costruttore della classe */
	public Meteo() {
		this.main = null;
		//this.t_max = 0;
		//this.t_min = 0;
		this.pressione = 0;
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
	 * @param pressione Pressione 
	 */
	public Meteo(int pressione) {
		super();
		this.pressione = pressione;
	}
	
	/** Costruttore della classe
	 * @param main Dati meteo generali
	 * @param t_min Temperatura minima
	 * @param t_max Temperatura massima
	 * @param p Pressione
	 * @param data Data del rilevamento dati
	 */
	public Meteo(String main, int p, String data) {
		this.main = main;
		pressione = p;
		this.data = data;
	}
	
	/** Metodo getter
	 * @return Dati meteo generali della città
	 */
	public String getMain() {
		return main;
	}

	/** Metodo setter
	 * @param main Dati generali relativi al meteo della città
	 */
	public void setMain(String main) {
		this.main = main;
	}

	/** Metodo getter
	 * @return Temperatura massima 
	 *
	public double getT_max() {
		return t_max;
	}
	*/
	
	/** Metodo setter
	 * @param t_max Temperatura massima
	 *
	public void setT_max(double t_max) {
		this.t_max = t_max;
	}
	*/
	
	/** Metodo getter
	 * @return Temperatura minima
	 *
	public double getT_min() {
		return t_min;
	}
	*/
	
	/** Metodo setter
	 * @param t_min Temperatura minima
	 *
	public void setT_min(double t_min) {
		this.t_min = t_min;
	}
	*/
	
	/** Metodo getter
	 * @return Pressione
	 */
	public double getPressione() {
		return pressione;
	}

	/** Metodo setter
	 * @param p Pressione 
	 */
	public void setPressione(int p) {
		pressione = p;
	}
	

	/** Metodo getter
	 * @return Data previsione dati meteo 
	 */
	public String getData() {
		return data;
	}

	/** Metodo setter
	 * @param data Data previsione dati meteo
	 */
	public void setData(String data) {
		this.data = data;
	}

	/** Override del metodo toString
	 *  @return valore della pressione prevista ed effettiva
	 */
	@Override
	public String toString() {
		String dati = "Main=" + main + ", pressione=" + pressione + "data misurazione=" + data + ".";
		return dati;
	}

	/** Override del metodo equals
	 *  @questo metodo confronte i datti Meteo con il tipo boolean (true o false)per vedere se sono uguali o no
	 */

	/**
	 * @Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		Meteo other = (Meteo) object;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		if (Double.doubleToLongBits(t_max) != Double.doubleToLongBits(other.t_max))
			return false;
		if (Double.doubleToLongBits(t_min) != Double.doubleToLongBits(other.t_min))
			return false;
		if (data != other.data)
			return false;
		return true;
	}
	*/
}
