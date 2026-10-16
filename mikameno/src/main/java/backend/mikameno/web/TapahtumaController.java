package backend.mikameno.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import backend.mikameno.model.Tapahtuma;
import backend.mikameno.repository.TapahtumaRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TapahtumaController {
    private TapahtumaRepository tr;
	
    // Välittää repositoryn
	public TapahtumaController(TapahtumaRepository tr) {
		this.tr = tr;
	}

    @GetMapping("/")
    public String naytaEtusivu(Model model) {
        model.addAttribute("tapahtumat",tr.findAll());
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
        tr.save(tapahtuma);
        return "redirect:/";
    }

    @GetMapping("/tapahtumat")
    public String listaaTapahtumat(ArrayList<Tapahtuma> tapahtumat) {
        return "listaaTapahtumat";
    }

}
