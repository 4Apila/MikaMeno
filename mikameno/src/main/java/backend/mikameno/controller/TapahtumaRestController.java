package backend.mikameno.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.mikameno.model.Tapahtuma;
import backend.mikameno.repository.TapahtumaRepository;

@RestController 
@RequestMapping("/api")
public class TapahtumaRestController {
    private TapahtumaRepository tr;

    public TapahtumaRestController(TapahtumaRepository tr) {
        this.tr = tr;
    }

    @GetMapping("/tapahtumat")
    public Iterable<Tapahtuma> haeTapahtumat() {
        return tr.findAll();
    }

    @GetMapping("/tapahtuma/{id}")
    public Optional<Tapahtuma> haeYksiTapahtuma(@PathVariable("id") Long id) {
        return tr.findById(id);
    }

    @PostMapping("/tapahtumat")
    public Tapahtuma lisaaTapahtuma(@RequestBody Tapahtuma uusi) {
        return tr.save(uusi);
    }

    @PutMapping("/tapahtuma/{id}")
    public Tapahtuma muutaTapahtumaa(@PathVariable Long id, @RequestBody Tapahtuma muutettava) {
        muutettava.setId(id);
        return tr.save(muutettava);
    }

    @DeleteMapping("/tapahtuma/{id}")
    public Iterable<Tapahtuma> poistaTapahtuma(@PathVariable Long id) {
        tr.deleteById(id);
        return tr.findAll();
    }


    
}
