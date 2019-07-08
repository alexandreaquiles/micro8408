package br.com.caelum.eats.restaurante;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class CategoriaDoCardapioController {

	private CategoriaDoCardapioRepository repo;

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}")
	public CategoriaDoCardapioDto categoriaPorId(@PathVariable("idCategoria") Long idCategoria) {
		CategoriaDoCardapio categoria = repo.findById(idCategoria).orElseThrow(() -> new ResourceNotFoundException());
		return new CategoriaDoCardapioDto(categoria);
	}

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria")
	public CategoriaDoCardapio cardapioDoRestaurante(@PathVariable("idCardapio") Long idCardapio,
			@RequestBody CategoriaDoCardapio categoria) {
		return repo.save(categoria);
	}

}
