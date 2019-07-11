package br.com.caelum.eats.pagamento;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.pagamento.AmqpPagamentoConfig.PagamentoSource;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificadorPagamentoConfirmado {

	private PagamentoSource pagamentoSource;
	
	public void notificaConfirmacao(Pagamento pagamento) {
		PagamentoConfirmado confirmado = new PagamentoConfirmado(pagamento.getId(), pagamento.getPedidoId());
		Message<PagamentoConfirmado> message = MessageBuilder.withPayload(confirmado).build();
		pagamentoSource.pagamentosConfirmados().send(message);
	}
	
}
