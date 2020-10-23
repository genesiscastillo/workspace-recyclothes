package cl.ccastillo.recyclothes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.ccastillo.recyclothes.model.Reserva;

@Repository
public interface ReservaDAO  extends CrudRepository<Reserva, Long>{

//	@Query("Select r from Reserva r where r.cliente.id = ?1")
//	List<Reserva> obtenerReservasCliente(Long idCliente);
	
	@Query("Select r from Reserva r where r.usuario.email = ?1")
	List<Reserva> obtenerReservasUsuario(String email);

}
