package br.com.caelum.apigateway;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ItemDoPedidoDto {

	private Long id;
	private Integer quantidade;
	private String observacao;
	private ItemDoCardapioDto itemDoCardapio;

}
