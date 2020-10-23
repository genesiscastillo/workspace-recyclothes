package cl.ccastillo.recyclothes.websocket.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import cl.ccastillo.recyclothes.model.Estado;
import cl.ccastillo.recyclothes.model.Producto;
import cl.ccastillo.recyclothes.websocket.client.ProductoClientService;

public class STOMPDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent>{

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;

    @Autowired
    ProductoClientService productoClientService; 
    
	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String simpSessionId = sha.getSessionId();
		System.out.println("------- DISCONNECT [ "+simpSessionId+" ] -----------");        
        List<Producto> productos = webAgentSessionRegistry.getProductos(simpSessionId);
        productos.stream().forEach(producto -> {
        	producto.setEstado(Estado.DISPONIBLE);
        	System.out.println("cambiando estado para el producto "+ producto.getId());
        	productoClientService.actualizarProducto(producto.getId(), producto);
        });
        webAgentSessionRegistry.unregisterSessionId(simpSessionId);
	}
}
