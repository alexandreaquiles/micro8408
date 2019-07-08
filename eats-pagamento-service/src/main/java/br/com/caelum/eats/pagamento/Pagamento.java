package br.com.caelum.eats.pagamento;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
class Pagamento {

	public static enum Status {
		CRIADO,
		CONFIRMADO,
		CANCELADO;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull @Positive
	private BigDecimal valor;

	@NotBlank  @Size(max=100)
	private String nome;

	@NotBlank  @Size(max=19)
	private String numero;

	@NotBlank  @Size(max=7)
	private String expiracao;
	
	@NotBlank @Size(min=3, max=3)
	private String codigo;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(nullable=false)
	private Long pedidoId;

	@Column(nullable=false)
	private Long formaDePagamentoId;

}








