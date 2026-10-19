package backend.mikameno.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import backend.mikameno.model.Kayttaja;
import backend.mikameno.model.Tapahtuma;
import backend.mikameno.repository.KayttajaRepository;
import backend.mikameno.repository.TapahtumaRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;

@Controller
public class TapahtumaController {
    private TapahtumaRepository tr;
    private KayttajaRepository kr;

    public TapahtumaController(TapahtumaRepository tr, KayttajaRepository kr) {
        this.tr = tr;
        this.kr = kr;
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
    public String postaaTapahtuma(@ModelAttribute @Valid Tapahtuma tapahtuma, BindingResult bindingResult,
            Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "lisaaTapahtuma";
        }

        Kayttaja jarjestaja = kr.findByKayttajatunnus(authentication.getName());
        tapahtuma.setJarjestaja(jarjestaja);
        tr.save(tapahtuma);
        return "redirect:/";
    }

    @GetMapping("/tapahtuma/{id}")
    public String listaaTapahtuma(@PathVariable("id") Long id, Model model, Authentication authentication) {
        Tapahtuma tapahtuma = tr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean onOsallistuja = false;
        boolean kirjautunut = authentication != null
                && !authentication.getName().equals("anonymousUser");
        if (kirjautunut) {
            onOsallistuja = tapahtuma.sisaltaaOsallistujan(
                    authentication.getName());
        }

        model.addAttribute("tapahtuma", tapahtuma);
        model.addAttribute("onOsallistuja", onOsallistuja);
        model.addAttribute("kirjautunut", kirjautunut);
        return "listaaTapahtuma";
    }

    @GetMapping("/tapahtuma/{id}/edit")
    public String naytaMuokattavaTapahtuma(@PathVariable("id") Long id, Model model, Authentication authentication) {
        Tapahtuma tapahtuma = tr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!voiMuokata(tapahtuma, authentication)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        model.addAttribute("tapahtuma", tapahtuma);
        return "muokkaaTapahtumaa";
    }

    @PostMapping("/tapahtuma/{id}/edit")
    public String muokkaaTapahtumaa(@PathVariable("id") Long id,
            @ModelAttribute("tapahtuma") @Valid Tapahtuma tapahtuma, BindingResult bindingResult, Model model,
            Authentication authentication) {

        if (bindingResult.hasErrors()) {
            tapahtuma.setId(id);
            return "muokkaaTapahtumaa";
        }
        Tapahtuma muokattava = tr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!voiMuokata(muokattava, authentication)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
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
    public String poistaTapahtuma(@PathVariable("id") Long id, Model model, Authentication authentication) {

        Tapahtuma tapahtuma = tr.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));

        if (!voiMuokata(tapahtuma, authentication)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        tr.deleteById(id);
        return "redirect:/";
    }

    private boolean voiMuokata(Tapahtuma tapahtuma, Authentication authentication) {
        if (authentication == null) {
            return false;
        }

        boolean onAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        boolean onJarjestaja = tapahtuma.getJarjestaja() != null
                && authentication.getName()
                        .equals(tapahtuma.getJarjestaja().getKayttajatunnus());

        return onAdmin || onJarjestaja;
    }

    @PostMapping("/tapahtuma/{id}/osallistu")
    public String osallistuTapahtumaan(@PathVariable("id") Long id, Authentication authentication) {
        if (authentication == null
                || authentication.getName().equals("anonymousUser")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Tapahtuma tapahtuma = tr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Kayttaja kayttaja = kr.findByKayttajatunnus(authentication.getName());
        if (kayttaja == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if (!tapahtuma.sisaltaaOsallistujan(kayttaja.getKayttajatunnus())) {
            tapahtuma.getOsallistujat().add(kayttaja);
            tr.save(tapahtuma);
        }

        return "redirect:/tapahtuma/" + id;
    }

}
