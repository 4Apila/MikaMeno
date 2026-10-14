package backend.mikameno.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.*;


public class Tapahtuma {

    private Long id;

    @NotNull(message = "Syötä tapahtuman alkupäivä")
    @FutureOrPresent(message = "Alkupäivä ei voi olla menneisyydessä")
    private LocalDateTime alkupv;

    @NotNull(message = "Syötä tapahtuman loppumispäivä ja kellonaika")
    @FutureOrPresent(message = "Loppumispäivä tai -aika ei voi olla menneisyydessä")
    private LocalDateTime loppupv;

    @NotEmpty(message = "Syötä tapahtumapaikka")
    private String paikka;

    @NotNull(message = "Syötä tapahtuman kuvaus")
    @Size(min=1, max=1000, message = "Tapahtuman kuvaus voi olla enintään 1000 merkkiä pitkä")
    private String kuvaus;

    private List<Kayttaja> kayttajat;

    private Kayttaja jarjestaja;
    
    public Tapahtuma() {

    }

    public Tapahtuma(Long id, LocalDateTime alkupv, LocalDateTime loppupv, 
            String paikka, String kuvaus, List<Kayttaja> kayttajat, Kayttaja jarjestaja) {
        this.id = id;
        this.alkupv = alkupv;
        this.loppupv = loppupv;
        this.paikka = paikka;
        this.kuvaus = kuvaus;
        this.kayttajat = kayttajat;
        this.jarjestaja = jarjestaja;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getAlkupv() {
        return alkupv;
    }
    public void setAlkupv(LocalDateTime alkupv) {
        this.alkupv = alkupv;
    }

    public LocalDateTime getLoppupv() {
        return loppupv;
    }
    public void setLoppupv(LocalDateTime loppupv) {
        this.loppupv = loppupv;
    }

    public String getPaikka() {
        return paikka;
    }
    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }
    public String getKuvaus() {
        return kuvaus;
    }
    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    public List<Kayttaja> getKayttajat() {
        return kayttajat;
    }
    public void setKayttajat(List<Kayttaja> kayttajat) {
        this.kayttajat = kayttajat;
    }
    public Kayttaja getJarjestaja() {
        return jarjestaja;
    }
    public void setJarjestaja(Kayttaja jarjestaja) {
        this.jarjestaja = jarjestaja;
    }
}
