package cl.ccastillo.recyclothes.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.ccastillo.recyclothes.model.Usuario;

@Repository
public interface UsuarioDAO  extends CrudRepository<Usuario, Long>{
	
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	Usuario findUsuarioByEmail(String email);

}
