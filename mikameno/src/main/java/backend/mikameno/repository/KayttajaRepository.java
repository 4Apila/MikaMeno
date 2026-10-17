package backend.mikameno.repository;

import backend.mikameno.model.Kayttaja;
import org.springframework.data.repository.CrudRepository;

public interface KayttajaRepository extends CrudRepository<Kayttaja, String>{
    
}
