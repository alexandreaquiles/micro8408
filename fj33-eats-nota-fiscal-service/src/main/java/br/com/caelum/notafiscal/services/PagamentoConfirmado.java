package br.com.caelum.notafiscal.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoConfirmado {

	private Long pagamentoId;
	private Long pedidoId;

}
