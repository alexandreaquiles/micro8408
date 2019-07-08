package br.com.caelum.eats.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDoPedido {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull @Positive
	private Integer quantidade;

	private String observacao;
	
	@ManyToOne(optional=false)
	private Pedido pedido;

	@ManyToOne(optional=false)
	private ItemDoCardapio itemDoCardapio;

}
