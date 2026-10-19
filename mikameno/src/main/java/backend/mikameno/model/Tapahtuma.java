package backend.mikameno.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tapahtuma")
public class Tapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50, message = "Syötä kelvollinen nimi tapahtumalle. Nimen maksimipituus on 50 merkkiä")
    String nimi;

    @NotNull(message = "Syötä tapahtuman alkupäivä")
    @FutureOrPresent(message = "Alkupäivä ei voi olla menneisyydessä")
    private LocalDateTime alkupv;

    @NotNull(message = "Syötä tapahtuman loppumispäivä ja kellonaika")
    @FutureOrPresent(message = "Loppumispäivä tai -aika ei voi olla menneisyydessä")
    private LocalDateTime loppupv;

    @NotEmpty(message = "Syötä tapahtumapaikka")
    private String paikka;

    @NotNull(message = "Syötä tapahtuman kuvaus")
    @Size(min = 1, max = 1000, message = "Tapahtuman kuvaus voi olla enintään 1000 merkkiä pitkä")
    private String kuvaus;

    @Min(value = 1, message = "Syötä positiivinen luku. Mikäli maksimiosallistujamäärää ei ole, jätä kenttä tyhjäksi")
    private Integer maxOsallistujat;

    @ManyToMany
    @JsonIgnore
    private List<Kayttaja> osallistujat;

    @ManyToOne
    @JoinColumn(name = "kayttajatunnus")
    @JsonIgnore
    private Kayttaja jarjestaja;

    public Tapahtuma() {

    }

    public Tapahtuma(String nimi, LocalDateTime alkupv, LocalDateTime loppupv,
            String paikka, String kuvaus) {
        this.nimi = nimi;
        this.alkupv = alkupv;
        this.loppupv = loppupv;
        this.paikka = paikka;
        this.kuvaus = kuvaus;
    }

    public Tapahtuma(String nimi, LocalDateTime alkupv, LocalDateTime loppupv,
            String paikka, String kuvaus, Integer maxOsallistujat) {
        this.nimi = nimi;
        this.alkupv = alkupv;
        this.loppupv = loppupv;
        this.paikka = paikka;
        this.kuvaus = kuvaus;
        this.maxOsallistujat = maxOsallistujat;
    }

    // vain h2 käyttöön: järjestäjä
    public Tapahtuma(String nimi, LocalDateTime alkupv, LocalDateTime loppupv,
            String paikka, String kuvaus, Integer maxOsallistujat, Kayttaja jarjestaja) {
        this.nimi = nimi;
        this.alkupv = alkupv;
        this.loppupv = loppupv;
        this.paikka = paikka;
        this.kuvaus = kuvaus;
        this.maxOsallistujat = maxOsallistujat;
        this.jarjestaja = jarjestaja;
    }

    // vain h2 käyttöön: ei max-osallistujia, järjestäjä
    public Tapahtuma(String nimi, LocalDateTime alkupv, LocalDateTime loppupv,
            String paikka, String kuvaus, Kayttaja jarjestaja) {
        this.nimi = nimi;
        this.alkupv = alkupv;
        this.loppupv = loppupv;
        this.paikka = paikka;
        this.kuvaus = kuvaus;
        this.jarjestaja = jarjestaja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
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

    public List<Kayttaja> getOsallistujat() {
        return osallistujat;
    }

    public void setOsallistujat(List<Kayttaja> osallistujat) {
        this.osallistujat = osallistujat;
    }

    public void addOsallistuja(Kayttaja osallistuja) {
        osallistujat.add(osallistuja);
    }

    public void setMaxOsallistujat(Integer max) {
        this.maxOsallistujat = max;
    }

    public Integer getMaxOsallistujat() {
        return this.maxOsallistujat;
    }

    public Kayttaja getJarjestaja() {
        return jarjestaja;
    }

    public void setJarjestaja(Kayttaja jarjestaja) {
        this.jarjestaja = jarjestaja;
    }

    public boolean sisaltaaOsallistujan(String kayttajatunnus) {
        return osallistujat != null && osallistujat.stream()
                .anyMatch(k -> k.getKayttajatunnus().equals(kayttajatunnus));
    }
}
