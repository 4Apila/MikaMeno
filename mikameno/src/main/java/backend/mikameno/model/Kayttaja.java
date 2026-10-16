package backend.mikameno.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Kayttaja {

    @Id
    @NotBlank
    private String kayttajatunnus;

    // vähintään yksi numero. Vähintään yksi pieni kirjain. Vähintään yksi suuri
    // kirjain. Vähintään yksi erikoismerkki.
    // Ei välilyöntiä, vähintään 8 merkkiä pitkä
    @NotBlank(message = "Syötä salasana")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_:;<>€!/()?])(?=\\S+$).{8,}$", message = "Salasanan tulee olla vähintään 8 merkkiä pitkä ja sen tulee sisältää sekä isoja että pieniä kirjaimia sekä vähintään yksi numero ja erikoismerkki.")
    private String salasana;

}
