package br.com.farias.crud.data.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import mockit.Tested;

import org.junit.Test;

import br.com.farias.crud.model.Endereco;

public class EnderecoDAOImplTest {
	
	@Tested
	public EnderecoDAO enderecoDAO = new EnderecoDAOImpl();
	
	@Test(expected = IllegalArgumentException.class)
	public void testeSalvarEnderecoNulo() {
		enderecoDAO.salvar(null);
	}
	
	@Test
	public void testeIncluirEndereco() {
		EnderecoDAOImpl.reset();
		Endereco e = gerarMockEndereco();
		
		enderecoDAO.salvar(e);
		
		e.setId(0);
		List<Endereco> lista = enderecoDAO.listar();
		assertEquals(1, lista.size());
		assertEquals(e, lista.get(0));
	}
	
	@Test
	public void testeAtualizarEndereco() {
		EnderecoDAOImpl.reset();
		Endereco e = gerarMockEndereco();
		enderecoDAO.salvar(e);
		e.setId(0);
		e.setBairro("123");
		
		enderecoDAO.salvar(e);
		
		List<Endereco> lista = enderecoDAO.listar();
		assertEquals(1, lista.size());
		assertEquals(e, lista.get(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testeExcluirEnderecoNulo() {
		enderecoDAO.excluir(null);
	}
	
	@Test
	public void testeExcluirEndereco() {
		EnderecoDAOImpl.reset();
		Endereco e = gerarMockEndereco();
		enderecoDAO.salvar(e);
		e.setId(0);
		
		enderecoDAO.excluir(e);
		
		List<Endereco> lista = enderecoDAO.listar();
		assertEquals(0, lista.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testeBuscarEnderecoComIdNulo() {
		enderecoDAO.buscar(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testeBuscarEnderecoComIdNegativo() {
		enderecoDAO.buscar(-1);
	}
	
	@Test
	public void testeBuscarEndereco() {
		EnderecoDAOImpl.reset();
		Endereco e = gerarMockEndereco();
		e.setId(0);
		enderecoDAO.salvar(e);
		
		Endereco enderecoBusca = enderecoDAO.buscar(0);
		
		assertEquals(e, enderecoBusca);
	}
	
	@Test
	public void testeListar() {
		EnderecoDAOImpl.reset();
		enderecoDAO.salvar(gerarMockEndereco());
		enderecoDAO.salvar(gerarMockEndereco());
		enderecoDAO.salvar(gerarMockEndereco());
		
		List<Endereco> lista = enderecoDAO.listar();
		
		assertEquals(3, lista.size());
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
