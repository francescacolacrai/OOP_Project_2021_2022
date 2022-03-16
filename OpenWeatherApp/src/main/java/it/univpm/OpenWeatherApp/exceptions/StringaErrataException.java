package it.univpm.OpenWeatherApp.exceptions;

/**
 * Classe che contiene il metodo in grado di individuare una stringa inserita
 * non valida per il parametro "valore" relativo ai filtri.
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 *
 */
public class StringaErrataException extends Exception {
	
	private String messaggio;
	/** Questo è il costruttore.
	 *  @param messaggio messaggio d'errore.
	 */
	public StringaErrataException(String messaggio) {
		
		this.messaggio = messaggio;
	}
	
	/** Metodo getter
	 * @return Messaggio d'errore
	 */
	public String getMessaggio() {
		return messaggio;
	}
}