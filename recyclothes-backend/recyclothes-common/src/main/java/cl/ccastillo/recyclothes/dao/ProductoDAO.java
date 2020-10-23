package cl.ccastillo.recyclothes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.ccastillo.recyclothes.model.Categoria;
import cl.ccastillo.recyclothes.model.Estado;
import cl.ccastillo.recyclothes.model.Genero;
import cl.ccastillo.recyclothes.model.Producto;
import cl.ccastillo.recyclothes.model.Talla;

@Repository
public interface ProductoDAO extends CrudRepository<Producto, Long>{
	
	@Query("SELECT p FROM Producto p WHERE p.genero= ?1 and p.categoria = ?2 and p.talla = ?3 and p.estado = ?4")
	List<Producto> solicitarCatalogo(Genero genero, Categoria categoria, Talla talla , Estado estado);
}
