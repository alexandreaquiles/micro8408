package br.com.caelum.eats.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.eats.model.ItemDoPedido;
import br.com.caelum.eats.model.Pedido;
import br.com.caelum.eats.model.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoDto {

	private Long id;
	private LocalDateTime dataHora;
	private Pedido.Status status;
	private Restaurante restaurante;
	private EntregaDto entrega;
	private List<ItemDoPedidoDto> itens = new ArrayList<>();
	
	public PedidoDto(Pedido pedido) {
		this(pedido.getId(), pedido.getDataHora(), pedido.getStatus(), pedido.getRestaurante(), new EntregaDto(pedido.getEntrega()), trataItens(pedido.getItens()));
	}

	private static List<ItemDoPedidoDto> trataItens(List<ItemDoPedido> itens) {
		return itens.stream().map(ItemDoPedidoDto::new).collect(Collectors.toList());
	}

	public BigDecimal getTotal() {
		BigDecimal total = restaurante.getTaxaDeEntregaEmReais() != null ? restaurante.getTaxaDeEntregaEmReais() : BigDecimal.ZERO;
		for (ItemDoPedidoDto item : itens) {
			BigDecimal preco = item.getItemDoCardapio().getPrecoPromocional() != null ? item.getItemDoCardapio().getPrecoPromocional() : item.getItemDoCardapio().getPreco() ;
			total = total.add(preco.multiply(new BigDecimal(item.getQuantidade())));
		}
		return total;
	}
}
