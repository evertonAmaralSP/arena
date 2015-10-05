package br.com.ea.arena.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.ea.arena.model.editorial.Materia;
import br.com.ea.arena.model.editorial.ResultadoBuscaMateria;
import br.com.ea.arena.service.edtorial.Editorial;
import br.com.ea.arena.service.staticengine.StaticEngineMateria;
import br.com.ea.arena.support.factory.FileFactory;


/**
 * Handles requests for the product home page.
 */
@Controller
@SessionAttributes({"listaMarcas","useMarca"})
public class HomeController {

	@Autowired
	private StaticEngineMateria staticEngine;

	@Autowired
	private Editorial editorial;
	
	@Autowired
	private FileFactory fileFactory;

	@RequestMapping("/")
	public String home(ModelMap model) {
		return "home";
	}
	
	
	@RequestMapping("/selectMarca/{id}")
	public String list(ModelMap model,@PathVariable int id) {
		return "redirect:/";
	}



	@RequestMapping("/editorial")
	public ModelAndView testeEditorial() throws Exception {

		Materia materia = editorial.getMateriaIdHash("547e50316b6c1236ad0001d0");
		ModelAndView model = new ModelAndView("editorial");
		model.addObject("materia", materia);
		return model;
	}


	@RequestMapping(value = "/editorial/{id}", method = RequestMethod.GET)
	public ModelAndView testeEditorialId(HttpServletRequest request,@PathVariable String id) throws Exception {

		Materia materia = editorial.getMateriaIdHash(id);
		ModelAndView model = new ModelAndView("editorial");
		model.addObject("materia", materia);
		return model;
	}

	@RequestMapping(value = "/gerarLista", method = RequestMethod.GET)
	public ModelAndView excuteList() throws Exception {		

		ModelAndView model = new ModelAndView("home");
		return model;
	}

	@RequestMapping(value = "/ultimasNoticias/{marca}", method = RequestMethod.GET)
	public ModelAndView listaUltimasNoticias(HttpServletRequest request,@PathVariable String marca) throws Exception {

		ResultadoBuscaMateria resultadoBuscaConteudo = editorial.getListaUltimasNoticias(marca);
		ModelAndView model = new ModelAndView("listaMaterias");
		model.addObject("list", resultadoBuscaConteudo.getResultado());
		model.addObject("marca", marca);
		return model;
	}


	private Map<String, Object> getConteudo(Object obj) {
	  Map<String, Object> conteudo = new HashMap<String, Object>();
	  conteudo.put("materia", obj);
	  return conteudo;
  }

}
