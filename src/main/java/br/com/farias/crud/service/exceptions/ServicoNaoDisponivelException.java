package br.com.farias.crud.service.exceptions;

public class ServicoNaoDisponivelException extends Exception {
	
	private static final long serialVersionUID = 3082902156133167516L;

	public ServicoNaoDisponivelException(int status){
		super("O serviço requisitado não está dispível no momento, status HTTP: " + status);
	}

}
