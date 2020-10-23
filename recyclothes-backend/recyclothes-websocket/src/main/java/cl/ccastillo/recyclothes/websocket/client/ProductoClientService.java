package cl.ccastillo.recyclothes.websocket.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import cl.ccastillo.recyclothes.model.Categoria;
import cl.ccastillo.recyclothes.model.Estado;
import cl.ccastillo.recyclothes.model.Genero;
import cl.ccastillo.recyclothes.model.Producto;
import cl.ccastillo.recyclothes.model.Reserva;
import cl.ccastillo.recyclothes.model.Talla;
import cl.ccastillo.recyclothes.model.Usuario;
import cl.ccastillo.recyclothes.websocket.property.UrlRestProductoProperties;

@Service
public class ProductoClientService {

	private final String urlRestProductoPropertiesSolicitarCatalogo;
	private final String urlRestProductoPropertiesProducto;
	private final String urlRestProductoPropertiesUsuario;
	private final String urlRestProductoPropertiesReserva;

	@Autowired
	public ProductoClientService(UrlRestProductoProperties urlRestProductoProperties) {
		this.urlRestProductoPropertiesSolicitarCatalogo = urlRestProductoProperties.getSolicitarCatalogo();
		this.urlRestProductoPropertiesProducto = urlRestProductoProperties.getProducto();
		this.urlRestProductoPropertiesReserva = urlRestProductoProperties.getReserva();
		this.urlRestProductoPropertiesUsuario = urlRestProductoProperties.getUsuario();
	}

	public Producto actualizarProducto(Long id, Producto producto) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlRestProductoPropertiesProducto)
				.path(id.toString());
		new RestTemplate().put(builder.toUriString(), producto);
		return producto;
	}

	public Producto obtenerProducto(Long id) {
		UriComponentsBuilder builder = 
				UriComponentsBuilder
				.fromHttpUrl(urlRestProductoPropertiesProducto)
				.path(id.toString());
		
		ResponseEntity<Producto> response = new RestTemplate().getForEntity(builder.toUriString(), Producto.class);
		return response.getStatusCode().equals( HttpStatus.OK ) ? response.getBody() : null;
	}
	
	public List<Producto> solicitarCatalogo(Genero genero, Categoria categoria, Talla talla, Estado estado , String simpSessionId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlRestProductoPropertiesSolicitarCatalogo)
				.queryParam("genero", genero).queryParam("categoria", categoria).queryParam("talla", talla)
				.queryParam("estado", estado)
				.queryParam("simpSessionId", simpSessionId);

		@SuppressWarnings("unchecked")
		List<Producto> resultados = new RestTemplate().getForObject(builder.toUriString(), List.class);
		return resultados;
	}
	
	public Reserva generarReserva(Reserva reserva) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlRestProductoPropertiesReserva);
		return new RestTemplate().postForObject(builder.toUriString() , reserva , Reserva.class );
	}
	
	public Usuario registrarUsuario(Usuario usuario) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlRestProductoPropertiesUsuario);
		return new RestTemplate().postForObject(builder.toUriString() , usuario , Usuario.class );
	}
	
	public List<Reserva> obtenerReservas(String email){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlRestProductoPropertiesReserva)
										.queryParam("email", email);
		@SuppressWarnings("unchecked")
		List<Reserva> reservas = new RestTemplate().getForObject(builder.toUriString(), List.class);
		return reservas;
	}
	
}
