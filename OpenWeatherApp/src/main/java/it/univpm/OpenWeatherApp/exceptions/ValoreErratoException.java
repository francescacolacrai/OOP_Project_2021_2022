package it.univpm.OpenWeatherApp.exceptions;

/** Questa classe contiene l'eccezione lanciata quando viene inserito un valore 
 *  errato per il filtraggio.
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * */
public class ValoreErratoException extends Exception{

	private String messaggio = "Valore errato!!";
	
	/** Costruttore della classe */
	public ValoreErratoException(String messaggio) {
		this.messaggio = messaggio;
		System.out.print(messaggio);
	}
	
	public String getMessaggio() {
		return messaggio;
	}
}
