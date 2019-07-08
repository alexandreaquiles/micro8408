package br.com.caelum.eats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.model.Cardapio;
import br.com.caelum.eats.model.Restaurante;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

	Cardapio findByRestaurante(Restaurante restaurante);
}
