package br.com.caelum.eats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaAvaliacoesDto {

	private Long restauranteId;
	private Double media;

}
