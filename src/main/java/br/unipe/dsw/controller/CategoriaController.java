package br.unipe.dsw.controller;

import br.unipe.dsw.entity.Categoria;
import br.unipe.dsw.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "descricao", required = false) String descricao, ModelMap modelMap){
        List<Categoria> categoriaList = new ArrayList<Categoria>();
        if(descricao != null){
            categoriaList = (List<Categoria>)categoriaRepository.findByDescricao(descricao);
        }else{
            categoriaList = (List<Categoria>)categoriaRepository.findAll();
        }
        modelMap.addAttribute("categorias", categoriaList);
        return "categoria/list";
    }

    @RequestMapping(value = "/nova", method = RequestMethod.GET)
    public String create(@ModelAttribute Categoria categoria) {
        return "categoria/nova";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("categoria") @Valid Categoria categoria, RedirectAttributes redirectAttributes, BindingResult binding) {
        if (binding.hasErrors()) {
            return create(categoria);
        }

        if(categoria.getId() != null) {
            Categoria categoriaUpdate = categoriaRepository.findOne(categoria.getId());
            categoriaUpdate.setDescricao(categoria.getDescricao());
            categoriaUpdate.setCapacidade(categoria.getCapacidade());
            categoriaUpdate.setValor(categoria.getValor());
            categoriaRepository.save(categoriaUpdate);
            redirectAttributes.addFlashAttribute("success", "Categoria atualizada com sucesso!");
        } else {
            categoriaRepository.save(categoria);
            redirectAttributes.addFlashAttribute("success", "Categoria cadastrada com sucesso!");
        }
        return "redirect:/categoria/list";
    }

    @RequestMapping(value = "/{id}/edit")
    public String edit(@PathVariable(value = "id") String id, ModelMap modelMap){
        Categoria categoria = categoriaRepository.findOne(Long.parseLong(id));
        modelMap.addAttribute("categoria", categoria);
        return "categoria/nova";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") String id, RedirectAttributes redirect){
        categoriaRepository.delete(categoriaRepository.findOne(Long.parseLong(id)));
        redirect.addFlashAttribute("removed", "Categoria removida com sucesso!");
        return "redirect:/categoria/list";
    }

}
