package cl.ccastillo.recyclothes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	@Getter(value = AccessLevel.NONE)
	@ToString.Exclude
	private String password;
	
	@Column(name = "fechaCreacion")
	@Temporal(TemporalType.DATE)
	@Setter(value = AccessLevel.NONE)
	private Date fechaCreacion;
	
	@PrePersist
	public void preFechaCreacion() {
		this.fechaCreacion = new Date();
	}

	public static void main(String[] args) {
		new Cliente();
	}
	
}
