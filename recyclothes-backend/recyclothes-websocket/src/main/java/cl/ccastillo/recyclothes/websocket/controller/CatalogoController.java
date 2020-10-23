package cl.ccastillo.recyclothes.websocket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import cl.ccastillo.recyclothes.model.Catalogo;
import cl.ccastillo.recyclothes.model.Estado;
import cl.ccastillo.recyclothes.model.Pedido;
import cl.ccastillo.recyclothes.model.Producto;
import cl.ccastillo.recyclothes.model.Reserva;
import cl.ccastillo.recyclothes.model.Usuario;
import cl.ccastillo.recyclothes.websocket.client.ProductoClientService;
import cl.ccastillo.recyclothes.websocket.config.SocketSessionRegistry;

@Controller
public class CatalogoController {

	@Autowired
	SocketSessionRegistry socketSessionRegistry;

	@Autowired
	ProductoClientService productoClientService;
	
	@MessageMapping("/solicitarCatalogo")
	@SendToUser("/topic/catalogo")
	public List<Producto> solicitarCatalogo(@Header("simpSessionId") String simpSessionId, @Payload Catalogo catalogo)
			throws Exception {
		List<Producto> productos = new ArrayList<Producto>();
		productos = productoClientService.solicitarCatalogo(catalogo.getGenero(), catalogo.getCategoria(),
				catalogo.getTalla(), catalogo.getEstado(), simpSessionId);
		return productos;
	}

	@MessageMapping("/reservarProducto")
	@SendTo("/topic/producto")
	public Producto reservarProducto(@Header("simpSessionId") String simpSessionId, @Payload Producto producto)
			throws Exception {
		producto = productoClientService.obtenerProducto(producto.getId());
		if (producto.getEstado().equals(Estado.DISPONIBLE)) {
			producto.setEstado(Estado.RESERVADO);
			productoClientService.actualizarProducto(producto.getId(), producto);
			socketSessionRegistry.addProducto(simpSessionId, producto);
			System.out.println("reservarProducto " + simpSessionId + " : id " + producto.getId());
		}
		return producto;
	}

	@MessageMapping("/solicitarPedido")
	@SendToUser("/topic/pedido")
	public List<Producto> solicitarPedido(@Header("simpSessionId") String simpSessionId) {
		List<Producto> lista = new ArrayList<Producto>();
		lista = socketSessionRegistry.getProductos(simpSessionId);
		System.out.println("solicitarPedido " + simpSessionId + " : hay " + lista.size() + " productos");
		return socketSessionRegistry.getProductos(simpSessionId);
	}

	@MessageMapping("/generarReserva")
	@SendToUser("/topic/ticketReserva")
	public Reserva generarReserva(@Header("simpSessionId") String simpSessionId, @Payload Pedido pedido) {
		System.out.println("generarReserva "+pedido);
		List<Producto> productos = socketSessionRegistry.getProductos(simpSessionId);
		
		Integer precioTotal = productos.stream()
				  .map( producto -> producto.getPrecio())
				  .reduce(0, Integer::sum);

		Usuario usuario = productoClientService.registrarUsuario(pedido.getUsuario());
		
		Reserva reserva = new Reserva();
		reserva.setCelular(pedido.getCelular());
		reserva.setConsulta(pedido.getConsulta());
		reserva.setDireccion(pedido.getDireccion());
		reserva.setHorario(pedido.getHorario());
		reserva.setTipoEntrega(pedido.getTipoEntrega());
		reserva.setTipoPago(pedido.getTipoPago());
		reserva.setProductos(productos);
		reserva.setUsuario( usuario );
		reserva.setPrecioTotal(precioTotal);
		reserva.setUuid(UUID.randomUUID().toString());
		reserva = productoClientService.generarReserva(reserva);
		socketSessionRegistry.eliminarProductos(simpSessionId);
		return reserva;
	}
	
	@MessageMapping("/obtenerMisReservas")
	@SendToUser("/topic/misReservas")
	public List<Reserva> obtenerReserva(@Payload Usuario usuario){
		List<Reserva> listReservas = new ArrayList<Reserva>();
		listReservas =	productoClientService.obtenerReservas(usuario.getEmail());
		return listReservas;
	}
	
}
