package backend.mikameno.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "kayttaja")
public class Kayttaja {

    @Id
    @NotBlank
    @Column(nullable = false, unique = true)
    private String kayttajatunnus;

    @NotBlank(message = "Syötä salasana")
    // vähintään yksi numero. Vähintään yksi pieni kirjain. Vähintään yksi suuri
    // kirjain. Vähintään yksi erikoismerkki.
    // Ei välilyöntiä, vähintään 8 merkkiä pitkä
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_:;<>€!/()?])(?=\\S+$).{8,}$", message = "Salasanan tulee olla vähintään 8 merkkiä pitkä ja sen tulee sisältää sekä isoja että pieniä kirjaimia sekä vähintään yksi numero ja erikoismerkki.")
    private String salasanaHash;

    private String rooli;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jarjestaja")
    private List<Tapahtuma> jarjestetytTapahtumat;


    public Kayttaja() {
    }

    public Kayttaja(String tunnus, String salasanaHash) {
		this.kayttajatunnus = tunnus;
		this.salasanaHash = salasanaHash;
		this.rooli = "user";
	}
    
    public Kayttaja(String tunnus, String salasanaHash, String rooli) {
		this.kayttajatunnus = tunnus;
		this.salasanaHash = salasanaHash;
		this.rooli = rooli;
	}

    public String getKayttajatunnus() {
        return kayttajatunnus;
    }

    public void setKayttajatunnus(String kayttajatunnus) {
        this.kayttajatunnus = kayttajatunnus;
    }

    public String getSalasanaHash() {
        return salasanaHash;
    }

    public void setSalasanaHash(String salasanaHash) {
        this.salasanaHash = salasanaHash;
    }

    public String getRooli() {
        return rooli;
    }

    public void setRooli(String rooli) {
        this.rooli = rooli;
    }

    public List<Tapahtuma> getJarjestetytTapahtumat() {
        return jarjestetytTapahtumat;
    }

    public void setJarjestetytTapahtumat(List<Tapahtuma> jarjestetytTapahtumat) {
        this.jarjestetytTapahtumat = jarjestetytTapahtumat;
    }

    public void lisaaJarjestettyTapahtuma(Tapahtuma tapahtuma) {
        jarjestetytTapahtumat.add(tapahtuma);
    }
}
