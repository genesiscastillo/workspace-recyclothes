package cl.ccastillo.recyclothes.websocket.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "recyclothes-rest-producto")
public class UrlRestProductoProperties {
	
	@Getter
	@Setter
	private String solicitarCatalogo;
	
	@Getter
	@Setter
	private String producto;
	
	@Getter
	@Setter
	private String reserva;
	
	@Getter
	@Setter
	private String usuario;
	
}
