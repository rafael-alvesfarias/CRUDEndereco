package br.com.farias.crud.service;

import org.json.JSONObject;

import br.com.farias.crud.model.Endereco;
import br.com.farias.crud.service.exceptions.EnderecoNaoEncontradoException;
import br.com.farias.crud.service.exceptions.ServicoNaoDisponivelException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Implementa o serviço de busca de CEP
 * Utiliza a API do Jersey para realizar a comunicação com o WebService Rest
 * @author Rafael
 *
 */
public class CEPServiceImpl implements CEPService {
	
	private static final String RESOURCE_URI = "http://localhost:8080/CEPWebServices/rest/cep/";

	@Override
	public Endereco buscarCEP(String cep) throws EnderecoNaoEncontradoException, ServicoNaoDisponivelException {
		Client client = Client.create();
		WebResource webResource = client.resource(RESOURCE_URI + cep);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if(response.getStatus() == 200) {
			String output = response.getEntity(String.class);
			JSONObject retorno = new JSONObject(output);
			if (retorno.getBoolean("success")) {
				JSONObject enderecoJSON = retorno.getJSONObject("endereco");
				Endereco e = new Endereco();
				e.setCep(enderecoJSON.getString("cep"));
				e.setEstado(enderecoJSON.getString("estado"));
				e.setCidade(enderecoJSON.getString("cidade"));
				e.setBairro(enderecoJSON.getString("bairro"));
				e.setRua(enderecoJSON.getString("rua"));

				return e;
			} else {
				throw new EnderecoNaoEncontradoException(cep);
			}
		} else {
			throw new ServicoNaoDisponivelException(response.getStatus());
		}
	}

}
