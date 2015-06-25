package br.unipe.dsw.controller;

import br.unipe.dsw.entity.Usuario;
import br.unipe.dsw.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login(ModelMap modelMap){
        Usuario usuario = new Usuario();
        modelMap.addAttribute("usuario", usuario);
        return "login/form";
    }

    @RequestMapping(value = "/autenticar", method = RequestMethod.POST)
    public String auth(@ModelAttribute Usuario usuario, HttpSession session, RedirectAttributes redirect){

        Usuario usuarioAutenticado = usuarioRepository.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
        if (usuarioAutenticado != null) {
            session.setAttribute("usuario", usuarioAutenticado);
        } else {
            redirect.addFlashAttribute("erro", "Login ou senha incorreto.");
            return "redirect:/login";
        }
        redirect.addFlashAttribute("user", "Bem-vindo ao sistema de reservas, " + usuario.getLogin());
        return "redirect:/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

}
