package br.com.ea.arena.support.factory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ea.arena.model.editorial.GaleriasMultimidia;
import br.com.ea.arena.model.editorial.Imagem;
import br.com.ea.arena.model.editorial.Link;
import br.com.ea.arena.model.editorial.Materia;
import br.com.ea.arena.model.editorial.ResultadoBuscaMateria;
import br.com.ea.arena.support.json.JsonUtil;
import br.com.ea.arena.support.log.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Component
public class ModelFactory {

	@Log
	private Logger logger;

	@Autowired
	private JsonUtil jsonUtil;


	public Materia materia(JsonObject jsonObject) {
		if (!jsonObject.has("tipo_recurso")) {
			logger.error("O JSON de materia nao possui a chave 'materia'");
			throw new IllegalStateException("O JSON de materia nao possui a chave 'materia'");
		}

		if (!jsonObject.has("link")) {
			logger.error("O JSON de materia nao possui a chave 'link'");
			throw new IllegalStateException("O JSON de materia nao possui a chave 'link'");
		}

		JsonElement materiaElement = jsonObject.getAsJsonObject();
		JsonElement linkElement = jsonObject.get("link");

		final Gson jsonHandler = jsonUtil.getJsonHandler();

		Materia materia = jsonHandler.fromJson(materiaElement, Materia.class);
		materia.setLinks(jsonHandler.fromJson(linkElement, Link[].class));

		return materia;
	}
	
	public Imagem imagem(JsonObject jsonObject) {
		if (!jsonObject.has("tipo_recurso")) {
			logger.error("O JSON de midia nao possui a chave 'tipo_recurso'");
			throw new IllegalStateException("O JSON de midia nao possui a chave 'imagem'");
		}

		if (!jsonObject.has("link")) {
			logger.error("O JSON de midia nao possui a chave 'link'");
			throw new IllegalStateException("O JSON de midia nao possui a chave 'link'");
		}

		JsonElement imagemElement = jsonObject.getAsJsonObject();
		JsonElement linkElement = jsonObject.get("link");

		final Gson jsonHandler = jsonUtil.getJsonHandler();

		Imagem imagem = jsonHandler.fromJson(imagemElement, Imagem.class);
		imagem.setLink(jsonHandler.fromJson(linkElement, Link[].class));

		return imagem;
	}

	public ResultadoBuscaMateria listaConteudos(JsonObject jsonObject) {

		if (!jsonObject.has("link")) {
			logger.error("O JSON de materia nao possui a chave 'link'");
			throw new IllegalStateException("O JSON de materia nao possui a chave 'link'");
		}

		JsonElement linkElement = jsonObject.get("link");


		Gson jsonHandler = jsonUtil.getJsonHandler();
		ResultadoBuscaMateria resultadoBuscaConteudo = jsonHandler.fromJson(jsonObject, ResultadoBuscaMateria.class);
		resultadoBuscaConteudo.setLinks(jsonHandler.fromJson(linkElement, Link[].class));

		return resultadoBuscaConteudo;
	}

	public GaleriasMultimidia galeriasMultimidia(JsonObject jsonObject) {
		if (!jsonObject.has("tipo_recurso")) {
			logger.error("O JSON de midia nao possui a chave 'tipo_recurso'");
			throw new IllegalStateException("O JSON de midia nao possui a chave 'imagem'");
		}

		if (!jsonObject.has("link")) {
			logger.error("O JSON de midia nao possui a chave 'link'");
			throw new IllegalStateException("O JSON de midia nao possui a chave 'link'");
		}

		JsonElement imagemElement = jsonObject.getAsJsonObject();
		JsonElement linkElement = jsonObject.get("link");

		final Gson jsonHandler = jsonUtil.getJsonHandler();

		GaleriasMultimidia galeriasMultimidia = jsonHandler.fromJson(imagemElement, GaleriasMultimidia.class);
		galeriasMultimidia.setLink(jsonHandler.fromJson(linkElement, Link[].class));
		return galeriasMultimidia;
  }


}
