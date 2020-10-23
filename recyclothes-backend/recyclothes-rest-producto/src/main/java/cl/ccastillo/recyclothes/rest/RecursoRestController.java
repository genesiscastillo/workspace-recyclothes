package cl.ccastillo.recyclothes.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ccastillo.recyclothes.model.Categoria;
import cl.ccastillo.recyclothes.model.Genero;
import cl.ccastillo.recyclothes.model.Talla;

@RestController
public class RecursoRestController {
	
	@GetMapping("/api/recurso")
	public Recurso obtenerRecursos() {
		return new Recurso();
	}

	
	public class Recurso{
		public Genero[] genero = Genero.values();
		public Talla[] talla = Talla.values();
		public Categoria[] categoria = Categoria.values();		
	}
}
