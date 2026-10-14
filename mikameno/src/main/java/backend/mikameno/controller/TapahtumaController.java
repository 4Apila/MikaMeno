package backend.mikameno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import backend.mikameno.model.Tapahtuma;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class TapahtumaController {

    @GetMapping("/lisaaTapahtuma")
    public String lisaaTapahtuma(Model model) {
        model.addAttribute("tapahtuma", new Tapahtuma());
        return "lisaaTapahtuma";
    }

    @PostMapping("/lisaaTapahtuma")
    public String postaaTapahtuma(@Valid Tapahtuma tapahtuma, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "lisaaTapahtuma";
        }
        model.addAttribute("tapahtuma", tapahtuma);
        return "index";
    }
}
