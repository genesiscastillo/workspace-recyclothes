package cl.ccastillo.recyclothes.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.ccastillo.recyclothes.model.Cliente;
import cl.ccastillo.recyclothes.service.impl.ClienteServiceImpl;

@RestController
public class ClienteRestController {
	
	@Autowired
	ClienteServiceImpl clienteServiceImpl;
	
	@GetMapping("/api/clientes")
	public Iterable<Cliente> obtenerClientes(){
		return clienteServiceImpl.obtenerClientes();
	}
	
	@GetMapping("/api/cliente")
	public ResponseEntity<Cliente> obtenerCliente(@RequestParam String email, @RequestParam String password) {
		Optional<Cliente> opt = clienteServiceImpl.obtenerCliente(email, password);
		return opt.isPresent() ? ResponseEntity.ok( opt.get() ) : ResponseEntity.notFound().build();
	}
	
	@PostMapping("/api/cliente")
	public Cliente registrarCliente(@RequestBody Cliente cliente) {
		return clienteServiceImpl.save(cliente);
	}
	
	@PutMapping("/api/cliente/{id}")
	public Cliente actualizarCliente(@PathVariable Long id ,  @RequestBody Cliente cliente) {
		return clienteServiceImpl.save( cliente );
	}
	
	@DeleteMapping("/api/cliente/{id}")
	public void eliminarCliente(@PathVariable Long id) {
		clienteServiceImpl.delete(id);
	}
	
}
