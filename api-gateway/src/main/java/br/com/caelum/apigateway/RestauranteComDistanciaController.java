package br.com.caelum.apigateway;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RestauranteComDistanciaController {

	private RestauranteRestClient restauranteRestClient;
	private DistanciaRestClient distanciaRestClient;
	
	@CrossOrigin
	@GetMapping("/restaurantes-com-distancia/{cep}/restaurante/{restauranteId}")
	public RestauranteComDistanciaDto porCepEIdComDistancia(@PathVariable("cep") String cep, @PathVariable("restauranteId") Long restauranteId) {
		RestauranteDto restauranteDto = restauranteRestClient.porId(restauranteId);
		RestauranteComDistanciaDto restauranteComDistanciaDto = distanciaRestClient.porCepEId(cep, restauranteId);
		restauranteComDistanciaDto.setRestaurante(restauranteDto);
		return restauranteComDistanciaDto;
	}
	
}
