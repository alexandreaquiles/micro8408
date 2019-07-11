package br.com.caelum.eats.restaurante;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@Retryable(maxAttempts=5, backoff=@Backoff(delay=2000, multiplier=2))
	public void restauranteAtualizado(Restaurante restaurante) {
		log.info("monólito tentando chamar distancia-service");
		
		
		RestauranteParaServicoDeDistancia resDistancia = new RestauranteParaServicoDeDistancia(restaurante);
		restTemplate.put(url + "/restaurantes/"+resDistancia.getId(), resDistancia, RestauranteParaServicoDeDistancia.class);
	}
}
