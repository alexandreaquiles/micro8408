package br.com.caelum.eats.restaurante;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.eats.admin.TipoDeCozinha;

@AutoConfigureStubRunner(stubsMode = StubsMode.LOCAL, ids = "br.com.caelum:eats-distancia-service:+:9992")
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistanciaRestClientWiremockTest {

	private DistanciaRestClient distanciaRestClient;

	@Before
	public void before() {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9992";
		distanciaRestClient = new DistanciaRestClient(restTemplate, url);
	}

	@Test
	public void deveAdicionarUmNovoRestauranteAprovado() {
		try {

			TipoDeCozinha tipoDeCozinha = new TipoDeCozinha();
			tipoDeCozinha.setId(1L);

			Restaurante restaurante = new Restaurante();
			restaurante.setId(2L);
			restaurante.setCep("71500-000");
			restaurante.setTipoDeCozinha(tipoDeCozinha);
			distanciaRestClient.novoRestauranteAprovado(restaurante);
		} catch (Exception ex) {
			Assert.fail("Não deveria ter lançado a exceção: "+ ex);
		}

	}
}
