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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre", length = 200)
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private Genero genero;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "talla")
	private Talla talla; 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "categoria")
	private Categoria categoria;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaCreacion")
	private Date fechaCreacion;
	
	@Column( name="precio", nullable = false)
	private Integer precio;
	
	@OneToMany
	@JoinTable(name="producto_foto",
	joinColumns=@JoinColumn(name="prod_id"),
	inverseJoinColumns=@JoinColumn(name="foto_id"))
	private Collection<Foto> fotos = new ArrayList<Foto>();
	
	public Producto(Genero genero, Talla talla, Categoria categoria, Foto foto) {
		super();
		this.genero = genero;
		this.talla = talla;
		this.categoria = categoria;
		this.fotos.add(foto);
	}
	
	public Producto() {
		super();
	}

	@PrePersist
	private void prePersist() {
		this.fechaCreacion = new Date();
		this.precio = 0;
		this.estado = Estado.PENDIENTE;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Collection<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(Collection<Foto> fotos) {
		this.fotos = fotos;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", genero=" + genero + ", talla=" + talla + ", categoria="
				+ categoria + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", precio=" + precio
				+ ", fotos=" + fotos + "]";
	}
}
