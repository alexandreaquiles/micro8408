package br.com.caelum.eats.distancia.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.caelum.eats.distancia.RestaurantesController;
import br.com.caelum.eats.distancia.mongo.RestauranteMongo;
import br.com.caelum.eats.distancia.mongo.RestauranteMongoRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantesBase {
	
	@Autowired
	private RestaurantesController restauranteController;

	@MockBean	
	private RestauranteMongoRepository restauranteMongoRepository;
	
	@Before
	public void before () {
		
		RestAssuredMockMvc.standaloneSetup(restauranteController);
		
		
		Mockito.when(restauranteMongoRepository.insert(Mockito.any(RestauranteMongo.class)))
			.thenAnswer((InvocationOnMock invocation) -> {
				return invocation.getArgument(0);
			});
		
	}
	
}
