package cl.ccastillo.recyclothes.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import cl.ccastillo.recyclothes.model.Catalogo;
import cl.ccastillo.recyclothes.model.Categoria;
import cl.ccastillo.recyclothes.model.Estado;
import cl.ccastillo.recyclothes.model.Genero;
import cl.ccastillo.recyclothes.model.Producto;
import cl.ccastillo.recyclothes.model.Talla;
import cl.ccastillo.recyclothes.websocket.client.ProductoClientService;
import cl.ccastillo.recyclothes.websocket.property.UrlRestProductoProperties;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableConfigurationProperties( UrlRestProductoProperties.class )
@EnableCaching
public class RecyclothesWebsocketApplication implements CommandLineRunner {

	@Autowired
	ProductoClientService productoClientService;
	
	public static void main(String[] args) {
		SpringApplication.run(RecyclothesWebsocketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("WebSocket Server Listooooo");
		//getAllPendiente();
	}

//	private List<Producto> getAllPendiente()	{
//		List<Producto> productos = new ArrayList<Producto>();
//		for( Genero genero :Genero.values()) {
//			for(Categoria categoria : Categoria.values()) {
//				for(Talla talla : Talla.values()) {
//					Catalogo catalogo = new Catalogo(genero, categoria, talla, Estado.DISPONIBLE);
//					System.out.println(catalogo);
//					productos.addAll(productoClientService.solicitarCatalogo(genero, categoria, talla, Estado.DISPONIBLE));
//				}
//			}
//		}
//		return productos;
//	}

}
