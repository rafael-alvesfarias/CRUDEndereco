package br.com.farias.crud.data.dao;

import java.util.List;

import br.com.farias.crud.model.Endereco;

/**
 * Interface que representa as operações disponíveis da entidade persistível Endereço
 * @author rafael.farias
 *
 */
public interface EnderecoDAO {

	
	/**
	 * Salva ou altera um endereço
	 * @param e - endereço a ser persistido
	 */
	public void salvar(Endereco e);
	
	/**
	 * Busca um endereço
	 * @param id que corresponde ao endereço a ser buscado
	 * @return Endereco obtido através do id
	 */
	public Endereco buscar(Integer id);
	
	/**
	 * Exclui um endereço
	 * @param e endereço a ser excluido
	 */
	public void excluir(Endereco e);
	
	/**
	 * Lista todos os endereços
	 * @return A lista de todos os endereços
	 */
	public List<Endereco> listar();
}
