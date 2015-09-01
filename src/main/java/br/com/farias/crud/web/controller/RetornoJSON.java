package br.com.farias.crud.web.controller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.farias.crud.model.BaseBean;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RetornoJSON {
	@XmlElement
	private boolean success;
	
	@XmlElement
	private String message;
	
	//Indica para o JAXB para utilizar o tipo da instância em execução ao invés da classe pai
	@XmlElementRef
	private BaseBean data;
	
	public static RetornoJSON retornarSucesso(BaseBean data) {
		RetornoJSON ret = new RetornoJSON();
		ret.success = true;
		ret.data = data;
		
		return ret;
	}
	
	public static RetornoJSON retornarErro(String message) {
		RetornoJSON ret = new RetornoJSON();
		ret.success = false;
		ret.message = message;
		
		return ret;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseBean getData() {
		return data;
	}

	public void setData(BaseBean data) {
		this.data = data;
	}
}
