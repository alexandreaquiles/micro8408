package br.com.caelum.eats.distancia;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurante {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank @Size(max=18)
	private String cnpj;
	
	@NotBlank @Size(max=255)
	private String nome;
	
	@Size(max=1000)
	private String descricao;

	@NotBlank @Size(max=9)
	private String cep;

	@NotBlank @Size(max=300)
	private String endereco;

	@Positive
	private BigDecimal taxaDeEntregaEmReais;
	
	@Positive @Min(10) @Max(180)
	private Integer tempoDeEntregaMinimoEmMinutos;
	
	@Positive @Min(10) @Max(180)
	private Integer tempoDeEntregaMaximoEmMinutos;
	
	private Boolean aprovado;

	@Column(nullable=false)
	private Long tipoDeCozinhaId;
	
}
