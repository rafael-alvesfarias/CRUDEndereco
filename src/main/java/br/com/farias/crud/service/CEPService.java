package br.com.farias.crud.service;

import br.com.farias.crud.model.Endereco;
import br.com.farias.crud.service.exceptions.EnderecoNaoEncontradoException;
import br.com.farias.crud.service.exceptions.ServicoNaoDisponivelException;

public interface CEPService {
	/**
	 * Busca os dados de endere�o pelo CEP
	 * @param cep
	 * @return
	 * @throws EnderecoNaoEncontradoException - caso n�o encontre nenhum endere�o
	 * @throws ServicoNaoDisponivelException - caso o servi�o n�o esteja dispon�vel
	 */
	public Endereco buscarCEP(String cep) throws EnderecoNaoEncontradoException, ServicoNaoDisponivelException;
}
