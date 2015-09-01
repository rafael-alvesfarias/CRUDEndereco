<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="salvarAction" value="/endereco/salvar"/>
<c:url var="alterarAction" value="/endereco/alterar"/>
<c:url var="excluirAction" value="/endereco/excluir"/>

<html>
<head>
	<meta charset="UTF-8">
	<title>Endereço</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/recursos/css/style.css'/>">
	<script src="<c:url value='/recursos/javascript/jquery/jquery-1.11.2.js'/>"></script>
	<script>
		$(document).ready(function() {
			$("#cep").on("blur", function(){
				buscarCEP();
			});
			if($("#message").val() != "") {
				alert($("#message").val());
			}
		});
		function novoEndereco() {
			$("#popup-novo-endereco .subtitulo").html("Novo Endereço");
			openDialog($("#popup-novo-endereco"),250,500);
		}
		
		function cancelar(){
			$("#popup-novo-endereco input[type=text]").val("");
			closeDialog($("#popup-novo-endereco"));
		}
		
		function openDialog(div, w, h) {
			$("body").append("<div id='mask'></div>");
			var left = (screen.width/2)-(w/2);
			var top = (screen.height/2)-(h/2);
			div.css("width",w);
			div.css("heigth",h);
			div.css("left",left);
			div.css("top",top);
			div.css("z-index",101);
			div.css("display","block");
		}
		
		function closeDialog(div) {
			div.css("display","none");
			$("#mask").remove();
		}
		
		function buscarCEP() {
	        //Fazendo a chamada ajax ao controller
			var data = $("#formEndereco").serialize();
	        
			$.ajax({
				data: data,
				type: "POST",
				dataType: "text",
				success: function(data){
					try{
						json = jQuery.parseJSON(data);
						if (json.success) {
							$("#cep").val(json.data.cep);
							$("#rua").val(json.data.rua);
							$("#bairro").val(json.data.bairro);
							$("#cidade").val(json.data.cidade);
							$("#estado").val(json.data.estado);
						} else {
							alert("Erro ao buscar o CEP: " + json.message);
						}

					} catch(e){
						console.log(e);
					}
				},
				url: "<c:url value='/CEP/buscar'/>"
			});
		}
		
		function salvar() {
			//Validação
			if(validar()) {
				$("#formEndereco").submit();
			}
		}
		
		function validar(){
			var retorno = true;
			$("#formEndereco input[type=text]").each(function(index, elemento) {
				if ($(elemento).attr("required") == "required") {
					if($(elemento).val() == "") {
						retorno = false;
						alert("Parâmetro " + $(elemento).attr("name") + " é obrigatório.");
					}
				}
			});
			
			return retorno;
		}
		
		function alterar(id) {
			$.ajax({
				type: "POST",
				dataType: "text",
				success: function(data){
					try{
						json = jQuery.parseJSON(data);
						if (json.success) {
							$("#id").val(json.data.id);
							$("#cep").val(json.data.cep);
							$("#rua").val(json.data.rua);
							$("#bairro").val(json.data.bairro);
							$("#numero").val(json.data.numero);
							$("#complemento").val(json.data.complemento);
							$("#cidade").val(json.data.cidade);
							$("#estado").val(json.data.estado);
							
							$("#popup-novo-endereco .subtitulo").html("Alterar Endereço");
							openDialog($("#popup-novo-endereco"), 250, 500);
						}
					} catch(e){
						console.log(e);
					}
				},
				url: '${alterarAction}/'+id
			});
		}
		
	</script>
</head>
<body>
	<input type="hidden" value="${message}" id="message">
	<div class="cabecalho">
		<h1>CRUD<span>Endereço</span></h1>
		<h2>Teste SpringMVC e Web Services Rest</h2>
	</div>
	<div class="box">
		<h2 class="titulo line-separator-bottom">Endereço</h2>
		<div class="painel">
			<h3 class="subtitulo">Endereços Cadastrados</h3>
				<table>
					<thead>
						<tr>
							<th>id</th>
							<th>cep</th>
							<th>cidade</th>
							<th>estado</th>
							<th>bairro</th>
							<th>rua</th>
							<th>número</th>
							<th>complemento</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="endereco" items="${enderecos}" varStatus="status">
							<tr>
								<td>${endereco.id}</td>
								<td>${endereco.cep}</td>
								<td>${endereco.cidade}</td>
								<td>${endereco.estado}</td>
								<td>${endereco.bairro}</td>
								<td>${endereco.rua}</td>
								<td>${endereco.numero}</td>
								<td>${endereco.complemento}</td>
								<td><a href="#" onclick="alterar(${endereco.id});">alterar</a></td>
								<td><a href="${excluirAction}/${endereco.id}">excluir</a></td>					
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="botoes">
					<input type="button" value="Novo Endereço" class="botao-direita" onclick="javascript:novoEndereco();"/>
				</div>
			</div>
	</div>
	<div id="popup-novo-endereco" class="popup">
		<h3 class="subtitulo">Endereço</h3>
		<form id="formEndereco" action="${salvarAction}">
			<table>
				<tr>
					<td><label for="id">Id</label></td>
					<td><input type="text" id="id" name="id" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label for="cep">CEP</label></td>
					<td><input type="text" id="cep" name="cep" required="required" maxlength="8"/></td>
				</tr>
				<tr>
					<td><label for="cidade">Cidade</label></td>
					<td><input type="text" id="cidade" name="cidade" required="required"/></td>
				</tr>
				<tr>
					<td><label for="estado">Estado</label></td>
					<td><input type="text" id="estado" name="estado" required="required"/></td>
				</tr>
				<tr>
					<td><label for="estado">Bairro</label></td>
					<td><input type="text" id="bairro" name="bairro"/></td>
				</tr>
				<tr>
					<td><label for="rua">Rua</label></td>
					<td><input type="text" id="rua" name="rua" required="required"/></td>
				</tr>
				<tr>
					<td><label for="numero">Número</label></td>
					<td><input type="text" id="numero" name="numero" required="required"/></td>
				</tr>
				<tr>
					<td><label for="complemento">Complemento</label></td>
					<td><input type="text" id="complemento" name="complemento"/></td>
				</tr>
			</table>
			<div class="botoes">
				<input type="button" value="Cancelar" class="botao-direita" onclick="cancelar();"/>
				<input type="button" value="Salvar" class="botao-direita" onclick="salvar();"/>
			</div>
		</form>
	</div>
	<div class="rodape">
	</div>
</body>
</html>