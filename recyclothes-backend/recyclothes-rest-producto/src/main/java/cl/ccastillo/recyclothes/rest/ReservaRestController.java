package cl.ccastillo.recyclothes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.ccastillo.recyclothes.model.Reserva;
import cl.ccastillo.recyclothes.service.impl.ProductoServiceImpl;
import cl.ccastillo.recyclothes.service.impl.ReservaServiceImpl;

@RestController
public class ReservaRestController {
	
	@Autowired
	ReservaServiceImpl reservaServiceImpl; 

	@Autowired
	ProductoServiceImpl productoServiceImpl;
	
	@GetMapping("/api/reserva")
	public List<Reserva> obtenerReservasUsuario(@RequestParam("email") String email) {
		return reservaServiceImpl.obtenerReservaByUsuario(email);
	}
	
	@PostMapping("/api/reserva")
	public Reserva generarReserva(@RequestBody Reserva reserva){
		return reservaServiceImpl.save(reserva);
	}
	
}
