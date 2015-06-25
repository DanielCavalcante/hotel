package br.unipe.dsw.controller;

import br.unipe.dsw.entity.Quarto;
import br.unipe.dsw.repository.CategoriaRepository;
import br.unipe.dsw.entity.Categoria;
import br.unipe.dsw.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	QuartoRepository quartoRepository;

	@RequestMapping(value = "index")
	public String index(ModelMap model) {
        List<Categoria> categoriaList = (List<Categoria>)categoriaRepository.findAll();
		List<Quarto> quartoList = (List<Quarto>) quartoRepository.findAll();
		model.addAttribute("categorias", "Temos " + categoriaList.size() + " tipos de quarto.");
		model.addAttribute("quartos", "Temos " + quartoList.size() + " quartos no total.");
		return "index/hello";
	}
}