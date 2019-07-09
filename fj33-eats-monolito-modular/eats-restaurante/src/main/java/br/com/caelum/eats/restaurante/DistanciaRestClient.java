package br.com.caelum.eats.restaurante;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class DistanciaRestClient {
	
	private RestTemplate restTemplate;
	private String url;
	
	public DistanciaRestClient(RestTemplate restTemplate, @Value("${configuracao.distancia.service.url}") String url) {
		this.restTemplate = restTemplate;
		this.url = url;
	}

	public void novoRestauranteAprovado(Restaurante restaurante) {
		RestauranteParaServicoDeDistancia resDistancia = new RestauranteParaServicoDeDistancia(restaurante);
		ResponseEntity<RestauranteParaServicoDeDistancia> responseEntity = restTemplate.postForEntity(url + "/restaurantes", resDistancia, RestauranteParaServicoDeDistancia.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!HttpStatus.CREATED.equals(statusCode)) {
			throw new RuntimeException("Status diferente do esperado: " + statusCode);
		}
	}
	
	public void restauranteAtualizado(Restaurante restaurante) {
		RestauranteParaServicoDeDistancia resDistancia = new RestauranteParaServicoDeDistancia(restaurante);
		restTemplate.put(url + "/restaurantes/"+resDistancia.getId(), resDistancia, RestauranteParaServicoDeDistancia.class);
	}
}
