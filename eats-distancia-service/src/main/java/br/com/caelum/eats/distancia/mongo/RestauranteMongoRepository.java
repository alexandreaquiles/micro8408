package br.com.caelum.eats.distancia.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestauranteMongoRepository extends MongoRepository<RestauranteMongo, Long> {

	Page<RestauranteMongo> findAll(Pageable limit);

	Page<RestauranteMongo> findAllByTipoDeCozinhaId(Long tipoDeCozinhaId, Pageable limit);
	
}
