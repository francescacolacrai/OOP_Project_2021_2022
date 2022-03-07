package it.univpm.OpenWeatherApp.models;

/** Classe che racchiude i seguenti dati meteo della città:
 *  1. pressione ottenuta dalla previsione 
 *  2. pressione effettiva
 *  
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 */
public class Meteo {
	
	/** Pressione prevista per la città */
	private double pressionePrevista;
	
	/** Pressione effettiva per la città s*/
	private double pressioneEffettiva;
	
	/** Costruttore della classe */
	public Meteo() {
		this.pressionePrevista = 0;
		this.pressioneEffettiva = 0;
	}
	
	/** Costruttore della classe che inizializza tutti gli attributi
	 * @param p1 Pressione prevista
	 * @param p2 Pressione effettiva
	 */
	public Meteo(double p1, double p2) {
		pressionePrevista = p1;
		pressioneEffettiva = p2;
	}

	/** Metodo getter
	 * @return Pressione prevista
	 */
	public double getPressionePrevista() {
		return pressionePrevista;
	}

	/** Metodo setter
	 * @param pressioneP Pressione prevista
	 */
	public void setPressionePrevista(double pressioneP) {
		pressionePrevista = pressioneP;
	}

	/** Metodo getter
	 * @return Pressione effettiva
	 */
	public double getPressioneEffettiva() {
		return pressioneEffettiva;
	}

	/** Metodo setter
	 * @param pressioneP Pressione effettiva
	 */
	public void setPressioneEffettiva(double pressioneE) {
		pressioneEffettiva = pressioneE;
	}
	
	/** Override del metodo toString
	 *  @return valore della pressione prevista ed effettiva
	 */
	@Override
	public String toString() {
		String dati = "Pressione prevista=" + pressionePrevista +
				", pressione effettiva=" + pressioneEffettiva + ".";
		return dati;
	}
}
