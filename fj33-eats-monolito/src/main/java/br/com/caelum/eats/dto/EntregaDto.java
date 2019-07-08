package br.com.caelum.eats.dto;

import br.com.caelum.eats.model.Cliente;
import br.com.caelum.eats.model.Entrega;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntregaDto {

	private Long id;
	private Cliente cliente;
	private String cep;
	private String endereco;
	private String complemento;

	public EntregaDto(Entrega entrega) {
		this(entrega.getId(), entrega.getCliente(), entrega.getCep(), entrega.getEndereco(), entrega.getComplemento());
	}

}
