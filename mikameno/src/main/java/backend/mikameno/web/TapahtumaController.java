package backend.mikameno.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import backend.mikameno.model.Tapahtuma;
import backend.mikameno.repository.TapahtumaRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class TapahtumaController {
    private TapahtumaRepository tr;

    // Välittää repositoryn
    public TapahtumaController(TapahtumaRepository tr) {
        this.tr = tr;
    }

    @GetMapping("/")
    public String naytaEtusivu(Model model) {
        model.addAttribute("tapahtumat", tr.findAll());
        return "index";
    }

    @GetMapping("/lisaaTapahtuma")
    public String lisaaTapahtuma(Model model) {
        model.addAttribute("tapahtuma", new Tapahtuma());
        return "lisaaTapahtuma";
    }

    @PostMapping("/lisaaTapahtuma")
    public String postaaTapahtuma(@ModelAttribute @Valid Tapahtuma tapahtuma, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "lisaaTapahtuma";
        }
        tr.save(tapahtuma);
        return "redirect:/";
    }

    @GetMapping("/tapahtuma/{id}")
    public String listaaTapahtuma(@PathVariable("id") Long id, Model model) {
        Tapahtuma tapahtuma = tr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("tapahtuma", tapahtuma);
        return "listaaTapahtuma";
    }

    @GetMapping("/tapahtuma/{id}/edit")
    public String naytaMuokattavaTapahtuma(@PathVariable("id") Long id, Model model) {
        Tapahtuma tapahtuma = tr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("tapahtuma", tapahtuma);
        return "muokkaaTapahtumaa";
    }

    @PostMapping("/tapahtuma/{id}/edit")
    public String muokkaaTapahtumaa(@PathVariable("id") Long id,
            @ModelAttribute("tapahtuma") @Valid Tapahtuma tapahtuma, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            tapahtuma.setId(id);
            return "muokkaaTapahtumaa";
        }
        Tapahtuma muokattava = tr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        muokattava.setNimi(tapahtuma.getNimi());
        muokattava.setAlkupv(tapahtuma.getAlkupv());
        muokattava.setLoppupv(tapahtuma.getLoppupv());
        muokattava.setKuvaus(tapahtuma.getKuvaus());
        muokattava.setPaikka(tapahtuma.getPaikka());
        muokattava.setMaxOsallistujat(tapahtuma.getMaxOsallistujat());
        tr.save(muokattava);

        return "redirect:/tapahtuma/" + id;
    }

    @GetMapping("/tapahtuma/{id}/delete")
    public String poistaTapahtuma(@PathVariable("id") Long id, Model model) {
        tr.deleteById(id);
        return "redirect:/";
    }

}
