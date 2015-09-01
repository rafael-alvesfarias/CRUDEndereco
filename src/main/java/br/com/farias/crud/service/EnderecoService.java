package br.com.farias.crud.service;

import java.util.List;

import br.com.farias.crud.model.Endereco;

public interface EnderecoService {
	
	public void salvar(Endereco e);
	
	public Endereco buscar(Integer id);
	
	public void excluir(Endereco e);
	
	public List<Endereco> listar();

}
