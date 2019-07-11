package br.com.caelum.apigateway;

import org.springframework.stereotype.Component;

@Component
public class RestauranteRestClientFallback implements RestauranteRestClient {

	@Override
	public RestauranteDto porId(Long id) {
		RestauranteDto dto = new RestauranteDto();
		dto.setId(id);
		return dto;
	}

}
