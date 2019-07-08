package br.com.caelum.eats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.model.HorarioDeFuncionamento;
import br.com.caelum.eats.model.Restaurante;

public interface HorarioDeFuncionamentoRepository extends JpaRepository<HorarioDeFuncionamento, Long> {

	List<HorarioDeFuncionamento> findAllByRestaurante(Restaurante restaurante);

}
