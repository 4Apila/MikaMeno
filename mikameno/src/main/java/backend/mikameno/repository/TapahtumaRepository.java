package backend.mikameno.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import backend.mikameno.model.Kayttaja;
import backend.mikameno.model.Tapahtuma;

public interface TapahtumaRepository extends CrudRepository<Tapahtuma, Long>{
    List<Tapahtuma> findByJarjestaja(Kayttaja jarjestaja);
    List<Tapahtuma> findByAlkupv(LocalDateTime alkupv);
    List<Tapahtuma> findByNimi(String nimi);

}
