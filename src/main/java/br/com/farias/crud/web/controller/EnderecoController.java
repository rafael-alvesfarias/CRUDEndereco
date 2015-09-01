package br.com.farias.crud.web.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.farias.crud.model.Endereco;
import br.com.farias.crud.service.EnderecoService;

@Controller
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@RequestMapping("/endereco/listar")
	public String listar(Model model) {
		model.addAttribute("enderecos", enderecoService.listar());
		return "/crud/crudEndereco";
	}
	
	@RequestMapping("/endereco/salvar")
	public String salvar(Model model, @ModelAttribute Endereco e) {
		if(!validar(e)) {
			model.addAttribute("message", "Erro de validação. Um ou mais campos do endereço não foram informados:" + e);
		} else {
			enderecoService.salvar(e);
		}
		return listar(model);
	}
	
	@RequestMapping("/endereco/excluir/{id}")
	public String excluir(Model model, @PathVariable Integer id) {
		if(id == null || id < 0) {
			model.addAttribute("message", "Erro de validação. O campo id é inválido: " + id);
		}
		Endereco e = new Endereco();
		e.setId(id);
		enderecoService.excluir(e);
		return listar(model);
	}
	
	@RequestMapping(value = "/endereco/alterar/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String alterar(@PathVariable Integer id) {
		if(id == null || id < 0) {
			return new JSONObject(RetornoJSON.retornarErro("Erro de validação. O campo id é inválido: " + id)).toString();
		}
		Endereco e = enderecoService.buscar(id);
		RetornoJSON retorno = RetornoJSON.retornarSucesso(e);
		
		return new JSONObject(retorno).toString();
	}
	
	public static final boolean validar(Endereco e) {
		if (e == null)
			return false;
		if (e.getCep() == null || !e.getCep().matches("[0-9]{8}"))
			return false;
		if(e.getCidade() == null || e.getCidade().isEmpty())
			return false;
		if(e.getEstado() == null || e.getEstado().isEmpty())
			return false;
		if(e.getNumero() == null || e.getNumero() < 0)
			return false;
		if(e.getRua() == null || e.getRua().isEmpty())
			return false;
		return true;
	}

	public EnderecoService getEnderecoService() {
		return enderecoService;
	}

	public void setEnderecoService(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

}
