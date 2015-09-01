package br.com.farias.crud.data.dao;

import java.util.List;

import br.com.farias.crud.model.Endereco;

/**
 * Interface que representa as opera��es dispon�veis da entidade persist�vel Endere�o
 * @author rafael.farias
 *
 */
public interface EnderecoDAO {

	
	/**
	 * Salva ou altera um endere�o
	 * @param e - endere�o a ser persistido
	 */
	public void salvar(Endereco e);
	
	/**
	 * Busca um endere�o
	 * @param id que corresponde ao endere�o a ser buscado
	 * @return Endereco obtido atrav�s do id
	 */
	public Endereco buscar(Integer id);
	
	/**
	 * Exclui um endere�o
	 * @param e endere�o a ser excluido
	 */
	public void excluir(Endereco e);
	
	/**
	 * Lista todos os endere�os
	 * @return A lista de todos os endere�os
	 */
	public List<Endereco> listar();
}
