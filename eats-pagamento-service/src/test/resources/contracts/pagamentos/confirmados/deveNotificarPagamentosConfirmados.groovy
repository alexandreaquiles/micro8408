import org.springframework.cloud.contract.spec.Contract

Contract.make {

	description "deve notificar pagamentos confirmados"
	
	label 'pagamento_confirmado'
	
	input {
	
		triggeredBy('novoPagamentoConfirmado()')
	
	}

	outputMessage {
		
		sentTo 'pagamentosConfirmados'
		
		body([
			pagamentoId: 2,
			pedidoId: 3
		
		])
	
		headers {
		
			messagingContentType(applicationJson())
			
		}
	
	}

}