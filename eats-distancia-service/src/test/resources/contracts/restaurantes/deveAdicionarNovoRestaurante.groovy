import org.springframework.cloud.contract.spec.Contract

Contract.make {

	description "deve adicionar novo restaurante"
	
	request {
		
		method POST()
		
		url("/restaurantes")
		
		body([
			id: 2,
			cep: '71500-000',
			tipoDeCozinhaId: 1
		])
		
		headers {
			contentType('application/json')
		}
		
	}

	response {
	
		status 201

		body([
			id: 2,
			cep: '71500-000',
			tipoDeCozinhaId: 1
		])
	
		headers {
			contentType('application/json')
		}
	
	}
	
	

}