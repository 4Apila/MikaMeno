package backend.mikameno.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import backend.mikameno.model.Tapahtuma;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TapahtumaController {
    List<Tapahtuma> tapahtumat = new ArrayList<Tapahtuma>();

    @GetMapping("/")
    public String naytaEtusivu(Model model) {
        model.addAttribute("tapahtumat", tapahtumat);
        return "index";
    }

    @GetMapping("/lisaaTapahtuma")
    public String lisaaTapahtuma(Model model) {
        model.addAttribute("tapahtuma", new Tapahtuma());
        return "lisaaTapahtuma";
    }

    @PostMapping("/lisaaTapahtuma")
    public String postaaTapahtuma(@ModelAttribute @Valid Tapahtuma tapahtuma, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "lisaaTapahtuma";
        }
        model.addAttribute("tapahtuma", tapahtuma);
        tapahtumat.add(tapahtuma);
        return "redirect:/";
    }

    @GetMapping("/tapahtumat")
    public String listaaTapahtumat(ArrayList<Tapahtuma> tapahtumat) {
        return "listaaTapahtumat";
    }

}
