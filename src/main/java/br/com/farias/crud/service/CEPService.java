package br.com.farias.crud.service;

import br.com.farias.crud.model.Endereco;
import br.com.farias.crud.service.exceptions.EnderecoNaoEncontradoException;
import br.com.farias.crud.service.exceptions.ServicoNaoDisponivelException;

public interface CEPService {
	/**
	 * Busca os dados de endereço pelo CEP
	 * @param cep
	 * @return
	 * @throws EnderecoNaoEncontradoException - caso não encontre nenhum endereço
	 * @throws ServicoNaoDisponivelException - caso o serviço não esteja disponível
	 */
	public Endereco buscarCEP(String cep) throws EnderecoNaoEncontradoException, ServicoNaoDisponivelException;
}
