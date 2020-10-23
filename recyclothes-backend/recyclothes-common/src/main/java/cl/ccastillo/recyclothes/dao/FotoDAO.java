package cl.ccastillo.recyclothes.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.ccastillo.recyclothes.model.Foto;

@Repository
public interface FotoDAO extends CrudRepository<Foto,Long>{

}
