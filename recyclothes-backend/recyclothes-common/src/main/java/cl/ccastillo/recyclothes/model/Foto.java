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

@Entity
@Table(name = "foto")
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "urlimg", nullable = false, length = 200)
	private String urlimg;

	@Column(name = "dirimg", nullable = false, length = 200)
	private String dirimg;
	
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@PrePersist
	private void prePserist() {
		this.fechaCreacion = new Date();
	}

	public Foto(String dirimg , String urlimg) {
		super();
		this.urlimg = urlimg;
		this.dirimg = dirimg;
	}

	public Foto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrlimg() {
		return urlimg;
	}

	public void setUrlimg(String urlimg) {
		this.urlimg = urlimg;
	}

	public String getDirimg() {
		return dirimg;
	}

	public void setDirimg(String dirimg) {
		this.dirimg = dirimg;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Foto [id=" + id + ", urlimg=" + urlimg + ", dirimg=" + dirimg + ", fechaCreacion=" + fechaCreacion
				+ "]";
	}

}
