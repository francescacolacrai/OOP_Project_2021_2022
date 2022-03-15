package it.univpm.OpenWeatherApp.exceptions;

/** Questa classe contiene l'eccezione lanciata quando il programma non trova il file richiesto.
 * 
 * @author Francesca Colacrai
 * @author Lionel
 * */

public class FileNotFoundException extends Exception {

	private String messaggio = "File not found!";
	
	/** Costruttore della classe */
	public FileNotFoundException (String messaggio) {
		this.messaggio = messaggio;
		System.out.print(messaggio);
	}
	
	public String getMessaggio() {
		return messaggio;
	}
}
