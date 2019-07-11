package br.com.caelum.notafiscal.services;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import br.com.caelum.notafiscal.AmqpPagamentoConfig;
import br.com.caelum.notafiscal.pedido.PedidoDto;
import br.com.caelum.notafiscal.pedido.PedidoRestClient;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProcessadorDePagamentos {

	private GeradorDeNotaFiscal notaFiscal;
	private PedidoRestClient pedidos;

	
	
	@StreamListener(AmqpPagamentoConfig.PagamentoSink.PAGAMENTOS_CONFIRMADOS)
	public void processaPagamento(PagamentoConfirmado pagamento) {
		PedidoDto pedido = pedidos.detalhaPorId(pagamento.getPedidoId());
		String nota = notaFiscal.geraNotaPara(pedido);
		System.out.println(nota); // TODO: enviar XML para SEFAZ
	}
}
