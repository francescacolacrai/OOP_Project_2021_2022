package it.univpm.OpenWeatherApp;

import it.univpm.OpenWeatherApp.service.Service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"it.univpm.OpenWeatherApp.service.Service1"})
public class OpenWeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenWeatherAppApplication.class, args);
	}
}
