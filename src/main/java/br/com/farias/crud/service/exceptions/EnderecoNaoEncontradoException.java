package br.com.farias.crud.service.exceptions;

public class EnderecoNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = -1853431518512375862L;

	public EnderecoNaoEncontradoException(String cep){
		super("N�o foi encontrado nenhum endere�o com o CEP informado: " + cep);
	}
}
