package br.com.fiap.gametracker.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/games")
public class GamesController {

    @Autowired
    GamesService service;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("game", service.findAll());
        return "games/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if (service.delete(id)){
            redirect.addFlashAttribute("success", "Jogo apagado com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Jogo não encontrado");
        }
        return "redirect:/games";
    }
    
    @GetMapping("new")
    public String form(Games games){
        return "games/form";
    }


    @PostMapping
    public String create (@Valid Games games, BindingResult binding, RedirectAttributes redirect){
        if (binding.hasErrors()) return "/games/form";

        service.save(games);
        redirect.addFlashAttribute("Sucess", "Game cadastrado com sucesso");
        return "redirect: /games";
    }
}
