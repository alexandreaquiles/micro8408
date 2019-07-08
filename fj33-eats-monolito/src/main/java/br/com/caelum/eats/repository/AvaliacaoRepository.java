package br.com.caelum.eats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.caelum.eats.model.Avaliacao;
import br.com.caelum.eats.model.Restaurante;

public interface AvaliacaoRepository  extends JpaRepository<Avaliacao, Long> {

	@Query("select avg(a.nota) from Avaliacao a where a.pedido.restaurante.id =:restauranteId")
	public Double mediaDoRestaurantePeloId(Long restauranteId);

	@Query("select a from Avaliacao a where a.pedido.restaurante = :restaurante")
	public List<Avaliacao> findAllByRestaurante(Restaurante restaurante);

}
