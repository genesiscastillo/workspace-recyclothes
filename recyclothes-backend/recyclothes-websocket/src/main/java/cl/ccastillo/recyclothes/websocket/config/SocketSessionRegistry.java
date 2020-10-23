package cl.ccastillo.recyclothes.websocket.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.util.Assert;

import cl.ccastillo.recyclothes.model.Producto;

public class SocketSessionRegistry {

	private final ConcurrentMap<String, List<Producto>> sessionPedidos = new ConcurrentHashMap<String, List<Producto>>();

	private final Object lock = new Object();

	public void registerSessionId(String simpSessionId) {
		Object var3 = this.lock;
		synchronized (this.lock) {
			this.sessionPedidos.put(simpSessionId, new ArrayList<Producto>());
		}
	}

	public List<Producto> getProductos(String simpSessionId) {
		Object var3 = this.lock;
		synchronized (this.lock) {
			List<Producto> set = this.sessionPedidos.get(simpSessionId);
			return set != null ? set : new ArrayList<Producto>();
		}
	}

//	public ConcurrentMap<String, List<Producto>> getProductosPedido() {
//		return this.sessionPedidos;
//	}

	public void addProducto(String simpSessionId, Producto producto) {
		Assert.notNull(simpSessionId, "User must not be null");
		Assert.notNull(producto, "Producto must not be null");
		Object var3 = this.lock;
		synchronized (this.lock) {
			
			if(!this.sessionPedidos.containsKey(simpSessionId)) {
				this.sessionPedidos.put(simpSessionId, new ArrayList<Producto>());
			}
			this.sessionPedidos.get(simpSessionId).add(producto);
		}
	}
	public void eliminarProductos(String simpSessionId) {
		unregisterSessionId(simpSessionId);
	}
	public void unregisterSessionId(String simpSessionId) {
		Object var3 = this.lock;
		synchronized (this.lock) {
			if (this.sessionPedidos.containsKey(simpSessionId)) {
				this.sessionPedidos.remove(simpSessionId);
			}
		}
	}
}
