package br.com.caelum.eats.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.caelum.eats.admin.TipoDeCozinha;
import br.com.caelum.eats.admin.TipoDeCozinhaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TipoDeCozinhaControllerTest {

	private static final String TIPOS_DE_COZINHA = "/tipos-de-cozinha";
	private static final String ADMIN_TIPOS_DE_COZINHA = "/admin/tipos-de-cozinha";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TipoDeCozinhaRepository repo;
	
	@Autowired
	private ObjectMapper json;

	private TipoDeCozinha chinesa;
	private TipoDeCozinha mexicana;
	private List<TipoDeCozinha> tiposDeCozinha;

	@Before
	public void antes() {
		chinesa = new TipoDeCozinha(1L, "Chinesa");
		mexicana = new TipoDeCozinha(2L, "Mexicana");
		
		tiposDeCozinha = Arrays.asList(chinesa, mexicana);
	}
	
	@Test
	public void todas() throws Exception {
		Mockito.when(repo.findAllByOrderByNomeAsc()).thenReturn(tiposDeCozinha);
		
		this.mockMvc.perform(get(TIPOS_DE_COZINHA))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$.[0].id").value(1L))
			.andExpect(jsonPath("$.[0].nome").value("Chinesa"))
			.andExpect(jsonPath("$.[1].id").value(2L))
			.andExpect(jsonPath("$.[1].nome").value("Mexicana"));

		Mockito.verify(repo).findAllByOrderByNomeAsc();

	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void adiciona() throws Exception {
		TipoDeCozinha chinesaSemId = new TipoDeCozinha(null, "Chinesa");
		
		Mockito.when(repo.save(chinesaSemId)).thenReturn(chinesa);
		
		this.mockMvc.perform(
				post(ADMIN_TIPOS_DE_COZINHA)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json.writeValueAsString(chinesaSemId)))
			.andDo(print())
			.andExpect(status().isOk());

		Mockito.verify(repo).save(chinesaSemId);
	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void atualiza() throws Exception {
		Mockito.when(repo.save(chinesa)).thenReturn(chinesa);

		this.mockMvc.perform(
				put(ADMIN_TIPOS_DE_COZINHA + "/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json.writeValueAsString(chinesa)))
			.andDo(print())
			.andExpect(status().isOk());

		Mockito.verify(repo).save(chinesa);
	}
	
	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void remove() throws Exception {
		Mockito.doNothing().when(repo).deleteById(1L);

		this.mockMvc.perform(
				delete(ADMIN_TIPOS_DE_COZINHA + "/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print())
			.andExpect(status().isOk());

		Mockito.verify(repo).deleteById(1L);;
	}

}
