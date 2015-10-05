package br.com.ea.arena.service.parser;

import br.com.ea.arena.support.json.JsonUtil;

import com.google.gson.JsonObject;

public interface Parser {
	public String parse(String corpo, JsonObject entity);
	public void setJsonUtil(JsonUtil jsonUtil);
}
