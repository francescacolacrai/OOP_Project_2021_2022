package it.univpm.OpenWeatherApp.exceptions;

/** Questa classe contiene l'eccezione lanciata quando viene inserito un periodo sbagliato per i filtri
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * */
public class PeriodoErratoException extends Exception{
	
	private String messaggio = "Periodo sbagliato!";
	
	/** Costruttore della classe */
	public PeriodoErratoException(String messaggio) {
		this.messaggio = messaggio;
		System.out.print(messaggio);
	}
	
	public String getMessaggio() {
		return messaggio;
	}
}
