package cl.ccastillo.recyclothes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class Pedido {

	@Getter
	@Setter
    private Usuario usuario;
	
	@Getter
	@Setter
    private String tipoEntrega;
	
	@Getter
	@Setter
    private String horario;

	@Getter
	@Setter
	private String celular;
    
	@Getter
	@Setter
	private String direccion;
    
	@Getter
	@Setter
	private String consulta;
    
	@Getter
	@Setter
	private String tipoPago;

}
