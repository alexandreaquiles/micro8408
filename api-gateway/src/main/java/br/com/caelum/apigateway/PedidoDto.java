package br.com.caelum.apigateway;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoDto {

	private Long id;
	private LocalDateTime dataHora;
	private String status;
	private RestauranteDto restaurante;
	private EntregaDto entrega;
	private List<ItemDoPedidoDto> itens = new ArrayList<>();
	
	public BigDecimal getTotal() {
		BigDecimal total = restaurante.getTaxaDeEntregaEmReais() != null ? restaurante.getTaxaDeEntregaEmReais() : BigDecimal.ZERO;
		for (ItemDoPedidoDto item : itens) {
			BigDecimal preco = item.getItemDoCardapio().getPrecoPromocional() != null ? item.getItemDoCardapio().getPrecoPromocional() : item.getItemDoCardapio().getPreco() ;
			total = total.add(preco.multiply(new BigDecimal(item.getQuantidade())));
		}
		return total;
	}
}
