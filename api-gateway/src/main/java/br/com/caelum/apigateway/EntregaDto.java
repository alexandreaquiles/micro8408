package br.com.caelum.apigateway;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class EntregaDto {

	private Long id;
	private ClienteDto cliente;
	private String cep;
	private String endereco;
	private String complemento;


}
