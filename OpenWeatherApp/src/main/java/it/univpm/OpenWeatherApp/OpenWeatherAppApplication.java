package it.univpm.OpenWeatherApp;

import it.univpm.OpenWeatherApp.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Applicazione principale Spring
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 *
 */
@SpringBootApplication(scanBasePackages={"it.univpm.OpenWeatherApp.service.Service1", "it.univpm.OpenWeatherApp.models","it.univpm.OpenWeatherApp.controller"})
public class OpenWeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenWeatherAppApplication.class, args);
		
		/**
		 * Dopo l'avvio del programma salva i dati ogni ora relativi alla pressione corrente 
		 * delle città passate come parametro.
		 */
		ServiceImpl service = new ServiceImpl();
		String pathA = System.getProperty("user.dir") + "/meteo/" + "Ancona" + "Pressure.txt";
		String pathP = System.getProperty("user.dir") + "/meteo/" + "Pisa" + "Pressure.txt";
		String pathT = System.getProperty("user.dir") + "/meteo/" + "Torino" + "Pressure.txt";
		String pathR = System.getProperty("user.dir") + "/meteo/" + "Roma" + "Pressure.txt";
		service.salvaOgniTreOre("Ancona", pathA);
		service.salvaOgniTreOre("Pisa", pathP);
		service.salvaOgniTreOre("Torino", pathT);
		service.salvaOgniTreOre("Roma", pathR);
	}
}
