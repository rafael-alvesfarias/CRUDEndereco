package br.com.farias.crud.web.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.farias.crud.model.Endereco;
import br.com.farias.crud.service.CEPService;
import br.com.farias.crud.service.CEPServiceImpl;
import br.com.farias.crud.service.exceptions.EnderecoNaoEncontradoException;
import br.com.farias.crud.service.exceptions.ServicoNaoDisponivelException;

@Controller
public class CEPController {
	
	//TODO Injetar via Spring
	private CEPService service = new CEPServiceImpl();
	
	@RequestMapping(value = "/CEP/buscar", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String buscarCEP(String cep) {
		RetornoJSON retornoJSON = null;
		Endereco endereco = null;
		JSONObject retorno = null;
		try {
			if(cep != null && cep.matches("[0-9]{8}")) {
				endereco = service.buscarCEP(cep);
				retornoJSON = RetornoJSON.retornarSucesso(endereco);
			} else {
				retornoJSON = RetornoJSON.retornarErro("CEP informado inválido: " + cep);
			}
		} catch (EnderecoNaoEncontradoException | ServicoNaoDisponivelException e) {
			retornoJSON = RetornoJSON.retornarErro(e.getMessage());
		} finally {
			retorno = new JSONObject(retornoJSON);
		}
		
		return retorno.toString();
	}
}
