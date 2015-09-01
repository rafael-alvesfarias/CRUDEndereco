package br.com.farias.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.farias.crud.data.dao.EnderecoDAO;
import br.com.farias.crud.model.Endereco;

@Component
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	private EnderecoDAO enderecoDAO;

	@Override
	public void salvar(Endereco e) {
		enderecoDAO.salvar(e);		
	}

	@Override
	public Endereco buscar(Integer id) {
		return enderecoDAO.buscar(id);
	}

	@Override
	public void excluir(Endereco e) {
		enderecoDAO.excluir(e);		
	}

	@Override
	public List<Endereco> listar() {
		return enderecoDAO.listar();
	}

	public EnderecoDAO getEnderecoDAO() {
		return enderecoDAO;
	}

	public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
		this.enderecoDAO = enderecoDAO;
	}

}
