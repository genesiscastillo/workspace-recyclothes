package cl.ccastillo.recyclothes.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Table(name="reserva")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "tipoEntrega", nullable = false)
    private String tipoEntrega;
	
	@Column(name = "horario")
    private String horario;

	@Column(name = "celular")
	private String celular;
    
	@Column(name = "direccion")
	private String direccion;
    
	@Column(name = "consulta")
	private String consulta;
    
	@Column(name = "tipoPago" , nullable = false)
	private String tipoPago;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaCreacion")
	@Setter(value = AccessLevel.NONE)
	private Date fechaCreacion;
	
	@Column( name="precioTotal", nullable = false)
	private Integer precioTotal;
	
	@Enumerated(EnumType.STRING)
	@Setter(value = AccessLevel.NONE)
	private EstadoReserva estadoReserva;
	
	@OneToMany
	@JoinTable(name="reserva_producto",
	joinColumns=@JoinColumn(name="reserva_id"),
	inverseJoinColumns=@JoinColumn(name="producto_id"))
	private Collection<Producto> productos = new ArrayList<Producto>();

	@PrePersist
	public void setFechaCreacion() {
		this.fechaCreacion = new Date();
		this.estadoReserva = EstadoReserva.PENDIENTE_PAGO;
	}
	
}


