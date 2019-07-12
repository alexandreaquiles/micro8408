package br.com.caelum.apigateway;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

import br.com.caelum.apigateway.AmqpApiGatewayConfig.AtualizaPedidoSink;

@EnableBinding(AtualizaPedidoSink.class)
@Configuration
public class AmqpApiGatewayConfig {

	public static interface AtualizaPedidoSink {
		
		String PEDIDO_COM_STATUS_ATUALIZADO  = "pedidoComStatusAtualizado";
		
		@Input
		SubscribableChannel pedidoComStatusAtualizado();
		
	}
	
}
