package backend.mikameno.repository;

import org.springframework.data.repository.CrudRepository;

import backend.mikameno.model.Tapahtuma;

public interface TapahtumaRepository extends CrudRepository<Tapahtuma, Long>{
    

}
