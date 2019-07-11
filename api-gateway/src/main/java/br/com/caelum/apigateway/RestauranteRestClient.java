package br.com.caelum.apigateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="monolito", fallback=RestauranteRestClientFallback.class)
public interface RestauranteRestClient {

	@GetMapping("/restaurantes/{id}")
	RestauranteDto porId(@PathVariable("id") Long id);
}
