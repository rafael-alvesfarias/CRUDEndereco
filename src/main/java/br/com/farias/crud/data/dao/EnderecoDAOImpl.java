package br.com.farias.crud.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.farias.crud.model.Endereco;

/**
 * Classe responsável por simular uma interação com um banco de dados
 * implementa a funcionalidade de DAO para a entidade Endereço.
 * @author Rafael
 *
 */
@Repository
public class EnderecoDAOImpl implements EnderecoDAO {
	
	private static Map<Integer, Endereco> enderecos = new HashMap<Integer, Endereco>(1000);
	private static Integer id = 0;

	@Override
	public synchronized void salvar(Endereco e) {
		if (e == null || (e.getId() != null && e.getId() < 0)) {
			throw new IllegalArgumentException("O parâmetro endereço é inválido: " + e);
		}
		Endereco endereco = e.clone();
		if (enderecos.containsKey(endereco.getId())) {
			//Atualiza o registro
			enderecos.put(endereco.getId(), endereco);
		} else {
			//Insere um novo registro
			endereco.setId(id);
			enderecos.put(id, endereco);
			id++;
		}
	}

	@Override
	public Endereco buscar(Integer id) {
		if(id == null || id < 0) {
			throw new IllegalArgumentException("O parâmetro id é inválido: " + id);
		}
		return enderecos.get(id).clone();
	}

	@Override
	public synchronized void excluir(Endereco e) {
		if (e == null) {
			throw new IllegalArgumentException("O parâmetro endereço é inválido: " + e);
		}
		enderecos.remove(e.getId());
	}

	@Override
	public List<Endereco> listar() {
		//Cria uma Implementação de ArrayList que clona os objetos antes de serem retornados
		List<Endereco> listaClonadora = new ArrayList<Endereco>(enderecos.values()) {
			@Override
			public Endereco get(int index) {
				return super.get(index).clone();
			}
		};
		
		return listaClonadora;
	}
	
	//Método utilizado para resetar o repositório de Endereços, usar somente nos testes unitários.
	public static void reset() {
		enderecos = new HashMap<Integer, Endereco>(1000);
		id = 0;
	}

}
