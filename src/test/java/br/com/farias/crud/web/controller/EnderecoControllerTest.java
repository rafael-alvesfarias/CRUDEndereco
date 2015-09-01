package br.com.farias.crud.web.controller;

import java.util.ArrayList;
import java.util.List;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;

import br.com.farias.crud.model.Endereco;
import br.com.farias.crud.service.EnderecoService;

@RunWith(JMockit.class)
public class EnderecoControllerTest {
	
	@Tested
	EnderecoController controller;
	
	@Injectable
	EnderecoService service;
	
	@Test
	public void testSalvarEnderecoNulo() {
		final Model model = new MockUp<Model>() {
			boolean doAssert = true;
			@Mock
			Model addAttribute(String attributeName, Object attributeValue) {
				if(doAssert) {
					assertEquals("message", attributeName);
					assertEquals("Erro de validação. Um ou mais campos do endereço não foram informados:null", attributeValue);
					doAssert = false;
				}
				return null;
			}
		}.getMockInstance();
		
		controller.salvar(model, null);
	}
	
	@Test
	public void testSavarEnderecoInvalido() {
		final Model model = new MockUp<Model>() {
			boolean doAssert = true;
			@Mock
			Model addAttribute(String attributeName, Object attributeValue) {
				if(doAssert) {
					assertEquals("message", attributeName);
					assertEquals("Erro de validação. Um ou mais campos do endereço não foram informados", ((String)attributeValue).split(":")[0]);
					doAssert = false;
				}
				return null;
			}
		}.getMockInstance();
		
		Endereco e  = new Endereco();
		controller.salvar(model, e);
	}
	
	@Test
	public void testSalvarEndereco(@Mocked Model model) {
		new Expectations() {{
			service.salvar(withInstanceOf(Endereco.class));
		}};
		Endereco e = gerarMockEndereco();
		
		controller.salvar(model, e);
	}
	
	@Test
	public void testExcluirEnderecoComIdNulo() {
		final Model model = new MockUp<Model>() {
			boolean doAssert = true;
			@Mock
			Model addAttribute(String attributeName, Object attributeValue) {
				if(doAssert) {
					assertEquals("message", attributeName);
					assertEquals("Erro de validação. O campo id é inválido: null", attributeValue);
					doAssert = false;
				}
				return null;
			}
		}.getMockInstance();
		
		controller.excluir(model, null);
	}
	
	@Test
	public void testExcluirEnderecoComIdNegativo() {
		final Model model = new MockUp<Model>() {
			boolean doAssert = true;
			@Mock
			Model addAttribute(String attributeName, Object attributeValue) {
				if(doAssert) {
					assertEquals("message", attributeName);
					assertEquals("Erro de validação. O campo id é inválido: -1", attributeValue);
					doAssert = false;
				}
				return null;
			}
		}.getMockInstance();
		
		controller.excluir(model, -1);
	}
	
	@Test
	public void testExcluirEndereco(@Mocked Model model) {
		new Expectations() {{
			service.excluir(withInstanceOf(Endereco.class));
		}};
		
		controller.excluir(model, 1);
	}
	
	@Test
	public void testListar() {
		List<Endereco> lista = new ArrayList<Endereco>();
		lista.add(gerarMockEndereco());
		new Expectations() {{
			service.listar(); result = lista;
		}};
		final Model model = new MockUp<Model>() {
			@Mock
			Model addAttribute(String attributeName, Object attributeValue) {
				assertEquals("enderecos", attributeName);
				assertEquals(lista, attributeValue);
				return null;
			}
		}.getMockInstance();
		
		controller.listar(model);
	}
	
	public static Endereco gerarMockEndereco() {
		Endereco e = new Endereco();
		e.setCep("12345678");
		e.setEstado("SP");
		e.setCidade("Osasco");
		e.setRua("Rua 1");
		e.setNumero(213);
		e.setComplemento("Bloco A Apto. 22");
		
		return e;
	}
	
	
	
}