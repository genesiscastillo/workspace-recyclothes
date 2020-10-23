package cl.ccastillo.recyclothes.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.ccastillo.recyclothes.model.Cliente;

@Repository
public interface ClienteDAO extends CrudRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c WHERE c.email = ?1 and c.password = ?2")
	Cliente obtenerCliente(String email, String password);

}
