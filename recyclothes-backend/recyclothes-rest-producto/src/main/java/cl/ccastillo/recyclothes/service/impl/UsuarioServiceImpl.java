package cl.ccastillo.recyclothes.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ccastillo.recyclothes.dao.UsuarioDAO;
import cl.ccastillo.recyclothes.model.Usuario;

@Service
public class UsuarioServiceImpl {
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	public Usuario save(Usuario usuario) {
		return usuarioDAO.save( usuario );
	}

	public Optional<Usuario> getUsuario(String email) {
		return Optional.ofNullable(usuarioDAO.findUsuarioByEmail(email));
	}
}
