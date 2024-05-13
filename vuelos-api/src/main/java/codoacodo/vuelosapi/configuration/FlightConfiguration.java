package codoacodo.vuelosapi.configuration;

import codoacodo.vuelosapi.model.Dolar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FlightConfiguration {

    @Value("${dolarapi.url}")
    private String URL_DOLLAR;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public Dolar fetchDolar(){
        RestTemplate restTemplate = restTemplate();
        return restTemplate.getForObject(URL_DOLLAR + "/tarjeta", Dolar.class);
    }

    public Dolar[] fetchAllDolars(){
        RestTemplate restTemplate = restTemplate();
        return restTemplate.getForEntity(URL_DOLLAR, Dolar[].class).getBody();
    }

}
