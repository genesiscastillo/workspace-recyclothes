package cl.ccastillo.recyclothes.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.ccastillo.recyclothes.model.Usuario;
import cl.ccastillo.recyclothes.service.impl.UsuarioServiceImpl;

@RestController
public class UsuarioRestController {

	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping("/api/usuario")
	public ResponseEntity<Usuario> getUsuario(@RequestParam("email") String email) {
		Optional<Usuario> opt = usuarioServiceImpl.getUsuario(email);
		return opt.isPresent() ? ResponseEntity.ok( opt.get() ) : ResponseEntity.notFound().build();
	}
	
	@PostMapping("/api/usuario")
	public Usuario registrarUsuario(@RequestBody Usuario usuario) {
		Optional<Usuario> opt = usuarioServiceImpl.getUsuario(usuario.getEmail());
		return  opt.isPresent() ? opt.get() : usuarioServiceImpl.save(usuario) ;
	}
	
}
