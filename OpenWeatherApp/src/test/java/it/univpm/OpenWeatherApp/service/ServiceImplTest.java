package it.univpm.OpenWeatherApp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.OpenWeatherApp.exceptions.CittaNonTrovataException;
import it.univpm.OpenWeatherApp.models.Citta;

/** Questa classe serve per testare alcuni metodi presenti in ServiceImpl.
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 * 
 * @see ServiceImpl
 */
public class ServiceImplTest {
	
	private ServiceImpl service;
	Citta citta;
	
	/**
	 * Inizializzazione dei componenti necessari per effettuare correttamente i test
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new ServiceImpl();	
		citta = new Citta();
	}
	
	/** 
	 * Elimina tutto ciò che viene inizializzato da setUp()
	 * @throws Exception
	 */
	@AfterEach 
	void tearDown() throws Exception {
	}
	
	/** Questo test verifica che sia corretto il path in cui vengono salvate le previsioni ottenute */
	@Test 
	@DisplayName("Salvataggio del file effettuato correttamente!")
	void salvaTest() throws IOException {
		
		String nome = "Ancona";
		Citta citta = service.getPrevisionePressione(nome);
		String path = System.getProperty("user.dir") + "/forecasts/" + nome +
				"ForecastPressure.txt";

		assertEquals(path, service.salvaPrevisioni(nome, citta));
	}
	
	/** Questo test verifica se l'eccezione CittaNonTrovata viene generata correttamente */
    @Test
    @DisplayName("Generazione corretta dell'eccezione CittaNonTrovataException!")
    void letturaTest() {
		
    	String nomeCitta = "Pisa";
    	String path = System.getProperty("user.dir") + "/forecasts/" + nomeCitta + "ForecastPressure.txt";
    	
        CittaNonTrovataException error = assertThrows(CittaNonTrovataException.class, () -> 
        	{service.letturaDaFile(path);});
    
        assertEquals("Città non presente nel file!", error.getMessaggio());
        
    }

}
