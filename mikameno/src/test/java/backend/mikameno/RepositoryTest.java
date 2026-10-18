package backend.mikameno;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

import backend.mikameno.model.Kayttaja;
import backend.mikameno.model.Tapahtuma;
import backend.mikameno.repository.KayttajaRepository;
import backend.mikameno.repository.TapahtumaRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    private TapahtumaRepository tr;

    @Autowired
    private KayttajaRepository kr;

    @Test
    public void findByNimiToimii() {
        Tapahtuma uusiTapahtuma = new Tapahtuma("Testi", LocalDateTime.parse("2042-01-30T00:00:00"),
                LocalDateTime.parse("2042-01-31T23:59:00"), "VsCode", "Tapahtuma Repository-testien testaamiseen");
        tr.save(uusiTapahtuma);
        List<Tapahtuma> tapahtumat = tr.findByNimi("Testi");

        assertThat(tapahtumat).hasSize(1);
        assertThat(tapahtumat.get(0).getNimi())
                .isEqualTo("Testi");
    }

    @Test
    public void luoTapahtumaToimii() {
        Tapahtuma uusiTapahtuma = new Tapahtuma("Testi", LocalDateTime.parse("2042-01-30T00:00:00"),
                LocalDateTime.parse("2042-01-31T23:59:00"), "VsCode", "Tapahtuma Repository-testien testaamiseen");
        tr.save(uusiTapahtuma);
        assertThat(uusiTapahtuma.getId()).isNotNull();
    }

    @Test
    public void poistaTapahtumaToimii() {
        Tapahtuma uusiTapahtuma = new Tapahtuma("Testi", LocalDateTime.parse("2042-01-30T00:00:00"),
                LocalDateTime.parse("2042-01-31T23:59:00"), "VsCode", "Tapahtuma Repository-testien testaamiseen");
        tr.save(uusiTapahtuma);
        Long id = uusiTapahtuma.getId();
        tr.delete(uusiTapahtuma);
        assertThat(tr.findById(id)).isEmpty();
    }

    @Test
    public void findByKayttajatunnusToimii() {
        Kayttaja uusiKayttaja = new Kayttaja("testaaja", "testaaja", "USER");
        kr.save(uusiKayttaja);
        Kayttaja loydetty = kr.findByKayttajatunnus("testaaja");

        assertThat(loydetty).isNotNull();
        assertThat(loydetty.getKayttajatunnus())
                .isEqualTo("testaaja");
    }

    @Test
    public void luoKayttaja() {
        Kayttaja uusiKayttaja = new Kayttaja("testaaja", "testaaja", "USER");
        kr.save(uusiKayttaja);
        Kayttaja loydetty = kr.findByKayttajatunnus("testaaja");
        assertThat(loydetty).isNotNull();
        assertThat(loydetty.getRooli()).isEqualTo("USER");
    }

    @Test
    public void poistaKayttajaToimii() {
        Kayttaja uusiKayttaja = new Kayttaja("poistuva", "poistuva", "USER");
        kr.save(uusiKayttaja);
        kr.delete(uusiKayttaja);
        Kayttaja loydetty = kr.findByKayttajatunnus("poistuva");
        assertThat(loydetty).isNull();
    }

/*     @Test
    public void findTapahtumatByJarjestajaToimii() {
        Kayttaja uusiKayttaja = new Kayttaja("testaaja", "testaaja", "USER");
        kr.save(uusiKayttaja);

        Tapahtuma uusiTapahtuma = new Tapahtuma("Testi", LocalDateTime.parse("2042-01-30T00:00:00"),
                LocalDateTime.parse("2042-01-31T23:59:00"), "VsCode", "Tapahtuma Repository-testien testaamiseen");
        uusiTapahtuma.setJarjestaja(uusiKayttaja);
        tr.save(uusiTapahtuma);

        List<Tapahtuma> tapahtumat = tr.findByJarjestaja(uusiKayttaja); TÄSSÄ KÄY VIRHE

        assertThat(tapahtumat).hasSize(1);
        assertThat(tapahtumat.get(0).getJarjestaja()
                .getKayttajatunnus()).isEqualTo("testaaja");

        Tapahtuma uusiTapahtuma2 = new Tapahtuma("Testi", LocalDateTime.parse("2042-01-30T00:00:00"),
                LocalDateTime.parse("2042-01-31T23:59:00"), "VsCode", "Tapahtuma Repository-testien testaamiseen");
        uusiTapahtuma2.setJarjestaja(uusiKayttaja);
        tr.save(uusiTapahtuma);

    } */

}
