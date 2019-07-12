package br.com.caelum.eats.pagamento.base;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.caelum.eats.pagamento.NotificadorPagamentoConfirmado;
import br.com.caelum.eats.pagamento.Pagamento;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public class PagamentosConfirmadosBase {

	@Autowired
	private NotificadorPagamentoConfirmado notificadorPagamentoConfirmado;
	
	
	public void novoPagamentoConfirmado() {
		Pagamento pagamento = new Pagamento();
		pagamento.setId(2L);
		pagamento.setPedidoId(3L);
		notificadorPagamentoConfirmado.notificaConfirmacao(pagamento );
	}
	
}
