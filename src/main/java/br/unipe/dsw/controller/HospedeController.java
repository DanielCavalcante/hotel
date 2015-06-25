package br.unipe.dsw.controller;

import br.unipe.dsw.entity.Endereco;
import br.unipe.dsw.entity.Hospede;
import br.unipe.dsw.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hospede")
public class HospedeController {

    @Autowired
    HospedeRepository hospedeRepository;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        Hospede hospede = new Hospede();
        hospede.setEndereco(new Endereco());
        modelMap.addAttribute("hospede", hospede);
        return "hospede/novo";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "nome", required = false) String nome,
                       @RequestParam(value = "cpf", required = false) String cpf,
                       ModelMap modelMap){
        List<Hospede> hospedes = new ArrayList<Hospede>();
        if(nome != null && cpf != null){
            hospedes = (List<Hospede>)hospedeRepository.findByNomeAndCpf(nome, cpf);
        }else{
            hospedes = (List<Hospede>)hospedeRepository.findAll();
        }
        modelMap.addAttribute("hospedes", hospedes);
        return "hospede/list";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("hospede") Hospede hospede, RedirectAttributes redirect) {
        Hospede hospedeSalvar = new Hospede();
        if(hospede.getId() != null) {
            hospedeSalvar = hospedeRepository.findOne(hospede.getId());
            hospedeSalvar.setNome(hospede.getNome());
            hospedeSalvar.setCpf(hospede.getCpf());
            hospedeSalvar.setEmail(hospede.getEmail());
            hospedeSalvar.setTelefone(hospede.getTelefone());
            hospedeSalvar.setEndereco(hospede.getEndereco());
            hospedeRepository.save(hospedeSalvar);
            redirect.addFlashAttribute("success", "Hospede atualizado com sucesso");
        } else {
            hospedeSalvar = hospede;
            hospedeRepository.save(hospedeSalvar);
            redirect.addFlashAttribute("success", "Hospede cadastrado com sucesso");
        }
        return "redirect:/hospede/list";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") String id, ModelMap modelMap){
        Hospede hospede = hospedeRepository.findOne(Long.parseLong(id));
        modelMap.addAttribute("hospede", hospede);
        return "hospede/novo";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") String id, RedirectAttributes redirect){

        hospedeRepository.delete(hospedeRepository.findOne(Long.parseLong(id)));
        redirect.addFlashAttribute("removed", "Hospede removido com sucesso!");
        return "redirect:/hospede/list";

    }

}
