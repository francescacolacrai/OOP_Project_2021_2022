package it.univpm.OpenWeatherApp.exceptions;

/** Questa classe contiene l'eccezione lanciata quando il programma non trova la città richiesto.
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * */
public class CittaNonTrovataException extends Exception {
	
	private String messaggio = "City not found!";
	
	/** Costruttore della classe */
	public CittaNonTrovataException(String messaggio) {
		this.messaggio = messaggio;
		System.out.print(messaggio);
	}
	
	public String getMessaggio() {
		return messaggio;
	}
}
