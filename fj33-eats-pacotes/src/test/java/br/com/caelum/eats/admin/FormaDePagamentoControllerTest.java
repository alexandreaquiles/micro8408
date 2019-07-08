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

import br.com.caelum.eats.admin.FormaDePagamento;
import br.com.caelum.eats.admin.FormaDePagamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FormaDePagamentoControllerTest {

	private static final String FORMAS_DE_PAGAMENTO = "/formas-de-pagamento";
	private static final String ADMIN_FORMAS_DE_PAGAMENTO = "/admin/formas-de-pagamento";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FormaDePagamentoRepository repo;

	@Autowired
	private ObjectMapper json;

	private FormaDePagamento visaCredito;
	private FormaDePagamento ticketRestaurante;
	private List<FormaDePagamento> formasDePagamento;

	@Before
	public void antes() {
		visaCredito = new FormaDePagamento(1L, FormaDePagamento.Tipo.CARTAO_CREDITO, "Visa Crédito");
		ticketRestaurante = new FormaDePagamento(2L, FormaDePagamento.Tipo.VALE_REFEICAO, "Ticket Restaurante");

		formasDePagamento = Arrays.asList(visaCredito, ticketRestaurante);
	}

	@Test
	public void todas() throws Exception {
		Mockito.when(repo.findAllByOrderByNomeAsc()).thenReturn(formasDePagamento);

		this.mockMvc.perform(get(FORMAS_DE_PAGAMENTO)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(2)).andExpect(jsonPath("$.[0].id").value(1L))
				.andExpect(jsonPath("$.[0].tipo").value(FormaDePagamento.Tipo.CARTAO_CREDITO.name()))
				.andExpect(jsonPath("$.[0].nome").value("Visa Crédito")).andExpect(jsonPath("$.[1].id").value(2L))
				.andExpect(jsonPath("$.[1].tipo").value(FormaDePagamento.Tipo.VALE_REFEICAO.name()))
				.andExpect(jsonPath("$.[1].nome").value("Ticket Restaurante"));

		Mockito.verify(repo).findAllByOrderByNomeAsc();

	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void tipos() throws Exception {
		this.mockMvc.perform(get(ADMIN_FORMAS_DE_PAGAMENTO+"/tipos")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(3))
				.andExpect(jsonPath("$.[0]").value("CARTAO_CREDITO"))
				.andExpect(jsonPath("$.[1]").value("CARTAO_DEBITO"))
				.andExpect(jsonPath("$.[2]").value("VALE_REFEICAO"));

		Mockito.verifyZeroInteractions(repo);
	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void adiciona() throws Exception {
		FormaDePagamento visaCreditoSemId = new FormaDePagamento(null, FormaDePagamento.Tipo.CARTAO_CREDITO,
				"Visa Crédito");

		Mockito.when(repo.save(visaCreditoSemId)).thenReturn(visaCredito);

		this.mockMvc.perform(post(ADMIN_FORMAS_DE_PAGAMENTO).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json.writeValueAsString(visaCreditoSemId))).andDo(print()).andExpect(status().isOk());

		Mockito.verify(repo).save(visaCreditoSemId);
	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void atualiza() throws Exception {
		Mockito.when(repo.save(visaCredito)).thenReturn(visaCredito);

		this.mockMvc.perform(put(ADMIN_FORMAS_DE_PAGAMENTO+"/1").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json.writeValueAsString(visaCredito))).andDo(print()).andExpect(status().isOk());

		Mockito.verify(repo).save(visaCredito);
	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void remove() throws Exception {
		Mockito.doNothing().when(repo).deleteById(1L);

		this.mockMvc.perform(delete(ADMIN_FORMAS_DE_PAGAMENTO+"/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());

		Mockito.verify(repo).deleteById(1L);
		;
	}

}
