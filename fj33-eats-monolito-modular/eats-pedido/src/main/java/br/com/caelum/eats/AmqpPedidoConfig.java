package br.com.caelum.eats;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import br.com.caelum.eats.AmqpPedidoConfig.AtualizacaoPedidoSource;

@EnableBinding(AtualizacaoPedidoSource.class)
@Configuration
public class AmqpPedidoConfig {

	public static interface AtualizacaoPedidoSource {
		String PEDIDO_COM_STATUS_ATUALIZADO = "pedidoComStatusAtualizado";
		
		@Output
		MessageChannel pedidoComStatusAtualizado();
	}
	
}
