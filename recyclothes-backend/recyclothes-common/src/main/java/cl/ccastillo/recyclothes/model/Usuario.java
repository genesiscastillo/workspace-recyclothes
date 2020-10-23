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

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column
	private String givenName;
	
	@Column
	private String familyName;
	
	@Column
	private String nickname;
	
	@Column
	private String name;
	
	@Column
	private String picture;
	
	@Column
	private String email;
	
	@Column
	@Getter(value = AccessLevel.NONE)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	

	@PrePersist
	public void prePersist() {
		this.fechaCreacion = new Date();
	}
	
}
