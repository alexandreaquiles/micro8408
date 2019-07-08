package br.com.caelum.eats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.model.TipoDeCozinha;

public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> {

	List<TipoDeCozinha> findAllByOrderByNomeAsc();

}
