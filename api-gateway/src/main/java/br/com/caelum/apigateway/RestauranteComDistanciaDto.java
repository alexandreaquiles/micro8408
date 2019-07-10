package br.com.caelum.apigateway;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteComDistanciaDto {

	private Long restauranteId;
	private BigDecimal distancia;
	
	@Delegate @JsonIgnore
	private RestauranteDto restaurante;
}
