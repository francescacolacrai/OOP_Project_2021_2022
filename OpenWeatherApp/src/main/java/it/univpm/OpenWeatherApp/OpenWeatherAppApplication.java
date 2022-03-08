package it.univpm.OpenWeatherApp;

import it.univpm.OpenWeatherApp.service.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


@SpringBootApplication
public class OpenWeatherAppApplication {
	private static String API_KEY ="ce74fd08278903109816b3acfe7eb4fb";

	public static void main(String[] args) {
		SpringApplication.run(OpenWeatherAppApplication.class, args);

		try {

			URL url = new URL("api.openweathermap.org/data/2.5/weather?q=Ancone&appid=" + API_KEY);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			int responseCode = connection.getResponseCode();

			if(responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);

			}else {

				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
				scanner.close();
				System.out.println(informationString);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
