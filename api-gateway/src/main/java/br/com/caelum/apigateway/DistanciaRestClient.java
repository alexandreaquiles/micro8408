package br.com.caelum.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class DistanciaRestClient {

	private RestTemplate restTemplate;
	private String distanciaServiceUrl;

	public DistanciaRestClient(RestTemplate restTemplate, 
				@Value("${configuracao.distancia.service.url}") String distanciaServiceUrl) {
		this.restTemplate = restTemplate;
		this.distanciaServiceUrl = distanciaServiceUrl;
	}

	@HystrixCommand(fallbackMethod="restauranteSemDistancia")
	public RestauranteComDistanciaDto porCepEId(String cep, Long restauranteId) {
		String url = distanciaServiceUrl + "/restaurantes/" + cep + "/restaurante/" + restauranteId;
		return restTemplate.getForObject(url, RestauranteComDistanciaDto.class);
	}
	
	public RestauranteComDistanciaDto restauranteSemDistancia(String cep, Long restauranteId) {
		return new RestauranteComDistanciaDto(restauranteId, null, null);
	}

}
