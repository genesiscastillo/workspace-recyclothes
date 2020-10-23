package cl.ccastillo.recyclothes.services;

import java.util.List;

import cl.ccastillo.recyclothes.model.Categoria;
import cl.ccastillo.recyclothes.model.Estado;
import cl.ccastillo.recyclothes.model.Foto;
import cl.ccastillo.recyclothes.model.Genero;
import cl.ccastillo.recyclothes.model.Producto;
import cl.ccastillo.recyclothes.model.Talla;

public interface ProductoService {

	List<Producto> findByEstado(Genero genero , Categoria categoria , Talla talla , Estado estado);
	Producto save(Producto producto);
	Foto save(Foto foto);

}
