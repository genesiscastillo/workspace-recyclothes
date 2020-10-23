package cl.ccastillo.recyclothes.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ccastillo.recyclothes.dao.ClienteDAO;
import cl.ccastillo.recyclothes.model.Cliente;

@Service
public class ClienteServiceImpl {

	@Autowired
	ClienteDAO clienteDAO;
	
	public Optional<Cliente> obtenerCliente(String email, String password) {
		return Optional.ofNullable(clienteDAO.obtenerCliente(email, password));
	}
	
	public Cliente save(Cliente cliente) {
		return clienteDAO.save(cliente);
	}
	
	public void delete(Long id) {
		clienteDAO.deleteById(id);
	}
	
	public Iterable<Cliente> obtenerClientes(){
		return clienteDAO.findAll();
	}
}
