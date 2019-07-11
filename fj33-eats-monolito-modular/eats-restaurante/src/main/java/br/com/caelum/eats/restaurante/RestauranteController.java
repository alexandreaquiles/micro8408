package br.com.caelum.eats.restaurante;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class RestauranteController {

	private RestauranteRepository restauranteRepo;
	private CardapioRepository cardapioRepo;
	private DistanciaRestClient distanciaRestClient;

	@GetMapping("/restaurantes/{id}")
	public RestauranteDto detalha(@PathVariable("id") Long id) {
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteDto(restaurante);
	}
	
	@GetMapping("/restaurantes")
	public List<RestauranteDto> detalhePorIds(@RequestParam List<Long> ids) {
		return restauranteRepo.findAllById(ids).stream().map(RestauranteDto::new).collect(Collectors.toList());
	}

	@GetMapping("/parceiros/restaurantes/{id}")
	public RestauranteDto detalhaParceiro(@PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteDto(restaurante);
	}

	@PostMapping("/parceiros/restaurantes")
	public Restaurante adiciona(@RequestBody Restaurante restaurante) {
		restaurante.setAprovado(false);
		Restaurante restauranteSalvo = restauranteRepo.save(restaurante);
		Cardapio cardapio = new Cardapio();
		cardapio.setRestaurante(restauranteSalvo);
		cardapioRepo.save(cardapio);
		return restauranteSalvo;
	}

	@Transactional
	@PutMapping("/parceiros/restaurantes/{id}")
	public Restaurante atualiza(@RequestBody Restaurante restaurante) {
		Restaurante doBD = restauranteRepo.getOne(restaurante.getId());
		restaurante.setUser(doBD.getUser());
		restaurante.setAprovado(doBD.getAprovado());
		
		String cepDoBD = doBD.getCep();
		Long idTipoDeCozinhaDoBD = doBD.getTipoDeCozinha().getId();
		
		Restaurante salvo = restauranteRepo.save(restaurante);

		if (doBD.getAprovado() &&
				
				(
						!cepDoBD.equals(restaurante.getCep())
						
						||
						
						!idTipoDeCozinhaDoBD.equals(restaurante.getTipoDeCozinha().getId())
						
						)
				) 
		
		{
			distanciaRestClient.restauranteAtualizado(restaurante);
			
		}
		
		
		return salvo;
	}

	@GetMapping("/admin/restaurantes/em-aprovacao")
	public List<RestauranteDto> emAprovacao() {
		return restauranteRepo.findAllByAprovado(false).stream().map(RestauranteDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	@PatchMapping("/admin/restaurantes/{id}")
	public void aprova(@PathVariable("id") Long id) {
		restauranteRepo.aprovaPorId(id);
		Restaurante restaurante = restauranteRepo.getOne(id);
		distanciaRestClient.novoRestauranteAprovado(restaurante);
	}
}












