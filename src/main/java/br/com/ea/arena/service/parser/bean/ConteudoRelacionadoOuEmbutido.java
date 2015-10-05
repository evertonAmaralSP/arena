package br.com.ea.arena.service.parser.bean;

import com.google.gson.JsonObject;

public class ConteudoRelacionadoOuEmbutido {

	private JsonObject conteudoRelacionado;

	public ConteudoRelacionadoOuEmbutido(JsonObject conteudoRelacionado) {
		this.conteudoRelacionado = conteudoRelacionado;
	}

	public ConteudoRelacionadoOuEmbutido() {
		this(null);
	}

	public String getCredito() {
		if(conteudoRelacionado == null) return "";
		return conteudoRelacionado.get("credito").getAsString();
	}

}
