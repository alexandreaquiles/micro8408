package br.com.caelum.eats.dto;

import br.com.caelum.eats.model.ItemDoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDoPedidoDto {

	private Long id;
	private Integer quantidade;
	private String observacao;
	private ItemDoCardapioDto itemDoCardapio;

	public ItemDoPedidoDto(ItemDoPedido item) {
		this(item.getId(), item.getQuantidade(), item.getObservacao(), new ItemDoCardapioDto(item.getItemDoCardapio()));
	}

}
