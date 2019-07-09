package br.com.caelum.eats.distancia.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="restaurantes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteMongo {

	@Id
	private Long id;

	private String cep;

	private Long tipoDeCozinhaId;

}
