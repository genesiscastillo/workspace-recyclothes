package cl.ccastillo.recyclothes.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ccastillo.recyclothes.dao.FotoDAO;
import cl.ccastillo.recyclothes.dao.ProductoDAO;
import cl.ccastillo.recyclothes.model.Categoria;
import cl.ccastillo.recyclothes.model.Estado;
import cl.ccastillo.recyclothes.model.Foto;
import cl.ccastillo.recyclothes.model.Genero;
import cl.ccastillo.recyclothes.model.Producto;
import cl.ccastillo.recyclothes.model.Talla;

@Service
public class ProductoServiceImpl {

	@Autowired
	private ProductoDAO productoDao;
	
	@Autowired
	private FotoDAO fotoDao;

	public List<Producto> solicitarCatalogo(Genero genero , Categoria categoria , Talla talla , Estado estado) {
		return productoDao.solicitarCatalogo(genero , categoria , talla , estado);
	}

	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}
	
	public Foto save(Foto foto) {
		return fotoDao.save(foto);
	}
	
	public void actualizarProducto(Producto producto) {
			productoDao.save(producto);
	}
	
	public Optional<Producto> obtenerProducto(Long id) {
		return productoDao.findById(id);
	}
	
	public void eliminarProducto(Long id) {
		Optional<Producto> opt = productoDao.findById(id);
		if( opt.isPresent()) {
			Collection<Foto> fotos = opt.get().getFotos();
			fotos.stream().forEach(foto -> fotoDao.delete(foto));
			productoDao.deleteById(id);	
		}
	}
}
