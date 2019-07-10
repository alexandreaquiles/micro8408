package br.com.caelum.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanciaRestClient {

	private RestTemplate restTemplate;
	private String distanciaServiceUrl;

	public DistanciaRestClient(RestTemplate restTemplate, 
				@Value("${zuul.routes.distancia.url}") String distanciaServiceUrl) {
		this.restTemplate = restTemplate;
		this.distanciaServiceUrl = distanciaServiceUrl;
	}

	public RestauranteComDistanciaDto porCepEId(String cep, Long restauranteId) {
		String url = distanciaServiceUrl + "/restaurantes/" + cep + "/restaurante/" + restauranteId;
		return restTemplate.getForObject(url, RestauranteComDistanciaDto.class);
	}

}
