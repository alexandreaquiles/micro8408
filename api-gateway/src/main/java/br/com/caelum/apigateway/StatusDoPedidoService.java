package br.com.caelum.apigateway;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.caelum.apigateway.AmqpApiGatewayConfig.AtualizaPedidoSink;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class StatusDoPedidoService {

	private SimpMessagingTemplate websocket;
	
	@StreamListener(AtualizaPedidoSink.PEDIDO_COM_STATUS_ATUALIZADO)
	public void pedidoAtualizado(PedidoDto pedido) {
		log.info("Pedido atualizado: " + pedido);
		
		websocket.convertAndSend("/pedidos/"+pedido.getId()+"/status", pedido);
		
		if ("PAGO".equals(pedido.getStatus())) {
			websocket.convertAndSend("/parceiros/restaurantes/"+pedido.getRestaurante().getId()+"/pedidos/pendentes", pedido);

		}
	}
	
	
}
