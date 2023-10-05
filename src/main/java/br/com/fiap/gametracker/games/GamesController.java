package br.com.fiap.gametracker.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/games")
public class GamesController {

    @Autowired
    GamesService service;

    @GetMapping
    public String index(Model model){
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
    
}
