package br.com.farias.crud.service.exceptions;

public class ServicoNaoDisponivelException extends Exception {
	
	private static final long serialVersionUID = 3082902156133167516L;

	public ServicoNaoDisponivelException(int status){
		super("O servi�o requisitado n�o est� disp�vel no momento, status HTTP: " + status);
	}

}
