package br.com.caelum.eats.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.eats.model.CategoriaDoCardapio;
import br.com.caelum.eats.model.ItemDoCardapio;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaDoCardapioDto {

	private Long id;
	private String nome;
	private List<ItemDoCardapioDto> itens = new ArrayList<>();

	public CategoriaDoCardapioDto(CategoriaDoCardapio categoria) {
		this(categoria.getId(), categoria.getNome(), trataItens(categoria.getItens()));
	}

	private static List<ItemDoCardapioDto> trataItens(List<ItemDoCardapio> itens) {
		return itens.stream().map(ItemDoCardapioDto::new).collect(Collectors.toList());
	}

}
