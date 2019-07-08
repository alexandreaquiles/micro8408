package br.com.caelum.eats.dto;

import java.math.BigDecimal;

import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.TipoDeCozinha;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestauranteDto {

	private Long id;

	private String cnpj;

	private String nome;

	private String descricao;

	private String cep;

	private String endereco;

	private BigDecimal taxaDeEntregaEmReais;

	private Integer tempoDeEntregaMinimoEmMinutos;

	private Integer tempoDeEntregaMaximoEmMinutos;

	private Boolean aprovado;

	private TipoDeCozinha tipoDeCozinha;

	public RestauranteDto(Restaurante restaurante) {
		this(restaurante.getId(), restaurante.getCnpj(), restaurante.getNome(), restaurante.getDescricao(), restaurante.getCep(), restaurante.getEndereco(),
				restaurante.getTaxaDeEntregaEmReais(), restaurante.getTempoDeEntregaMinimoEmMinutos(),
				restaurante.getTempoDeEntregaMaximoEmMinutos(), restaurante.getAprovado(),
				restaurante.getTipoDeCozinha());
	}

}
