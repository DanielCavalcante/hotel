package br.unipe.dsw.controller;

import br.unipe.dsw.entity.Categoria;
import br.unipe.dsw.entity.Quarto;
import br.unipe.dsw.repository.CategoriaRepository;
import br.unipe.dsw.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quarto")
public class QuartoController {

    @Autowired
    QuartoRepository quartoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("categorias", selectCategoria());
        Quarto quarto = new Quarto();
        quarto.setCategoria(new Categoria());
        modelMap.addAttribute("quarto", quarto);
        return "quarto/novo";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") String id, ModelMap modelMap) {
        Quarto quarto = quartoRepository.findOne(Long.parseLong(id));
        modelMap.addAttribute("categorias", selectCategoria());
        modelMap.addAttribute("quarto", quarto);
        return "quarto/novo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("quarto") Quarto quarto, RedirectAttributes redirect) {
        Quarto quartoSalvar = new Quarto();
        if (quarto.getId() != null) {
            quartoSalvar = quartoRepository.findOne(quarto.getId());
            quartoSalvar.setNumero(quarto.getNumero());
            quartoSalvar.setAndar(quarto.getAndar());
            quartoSalvar.setCategoria(quarto.getCategoria());
            quartoRepository.save(quartoSalvar);
            redirect.addFlashAttribute("success", "Quarto atualizado com sucesso!");
        } else {
            quartoSalvar = quarto;
            quartoRepository.save(quartoSalvar);
            redirect.addFlashAttribute("success", "Quarto cadastrado com sucesso!");
        }
        return "redirect:/quarto/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") String id, RedirectAttributes redirect) {
        Quarto quarto = quartoRepository.findOne(Long.parseLong(id));
        quartoRepository.delete(quarto);
        redirect.addFlashAttribute("removed", "Quarto removido com sucesso!");
        return "redirect:/quarto/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap,
                       @RequestParam(value = "numero", required = false) String numero,
                       @RequestParam(value = "categoriaId", required = false) String categoiraId) {

        List<Quarto> quartoList = new ArrayList<Quarto>();
        if (numero != null & categoiraId != null) {
            Categoria categoria = categoriaRepository.findOne(Long.parseLong(categoiraId));
            quartoList = (List<Quarto>)quartoRepository.findByNumeroAndCategoria(numero, categoria);
        } else {
            quartoList = (List<Quarto>)quartoRepository.findAll();
        }
        List<Categoria> categoriaList = (List<Categoria>)categoriaRepository.findAll();
        modelMap.addAttribute("quartos", quartoList);
        modelMap.addAttribute("categorias", categoriaList);
        return "quarto/list";
    }

    public Map<Long, String> selectCategoria() {

        List<Categoria> categorias = (List<Categoria>)categoriaRepository.findAll();
        Map<Long, String> mapa = new HashMap<Long, String>();

        for (Categoria categoria:categorias) {
            mapa.put(categoria.getId(), categoria.getDescricao());
        }
        return mapa;
    }
}
