package br.com.caelum.apigateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDeCozinhaDto {

	private Long id;
	private String nome;
	
}
