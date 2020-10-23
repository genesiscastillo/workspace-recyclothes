package cl.ccastillo.recyclothes.model;

public class Catalogo {

	private Genero genero;
	private Categoria categoria;
	private Talla talla;
	private Estado estado;
	
	public Catalogo(Genero genero, Categoria categoria, Talla talla, Estado estado) {
		super();
		this.genero = genero;
		this.categoria = categoria;
		this.talla = talla;
		this.estado = estado;
	}
	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Talla getTalla() {
		return talla;
	}
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Catalogo [genero=" + genero + ", categoria=" + categoria + ", talla=" + talla + ", estado=" + estado
				+ "]";
	}
	
}
