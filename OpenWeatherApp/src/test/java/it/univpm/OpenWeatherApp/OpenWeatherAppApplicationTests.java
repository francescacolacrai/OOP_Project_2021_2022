package it.univpm.OpenWeatherApp;

import it.univpm.OpenWeatherApp.models.Citta;
import it.univpm.OpenWeatherApp.service.ServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootTest
class OpenWeatherAppApplicationTests {
	@Autowired
	static ServiceImpl service = new ServiceImpl();
	@Test
	void contextLoads() {
	}
	public void tesCreate(){
		System.out.println("ok");
	}
	@GetMapping(value="/infoMeteo")
	public ResponseEntity<Object> getInfoMeteo(@RequestParam(name="citta")String nomeCitta){
		Citta citta = service.getPrevisionePressione(nomeCitta);
		JSONObject obj = service.ConvertToJson(citta);
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);

	}

}
