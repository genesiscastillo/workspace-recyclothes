package cl.ccastillo.recyclothes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ccastillo.recyclothes.dao.ReservaDAO;
import cl.ccastillo.recyclothes.dao.UsuarioDAO;
import cl.ccastillo.recyclothes.model.Reserva;

@Service
public class ReservaServiceImpl {
	
	@Autowired
	ReservaDAO reservaDAO;
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	public Reserva save(Reserva reserva) {
		return reservaDAO.save(reserva);
	}

	public void eliminar(Long id) {
		reservaDAO.deleteById(id);
	}
	
//	public List<Reserva> obtenerReservasCliente(Long idCliente)	{
//		return reservaDAO.obtenerReservasCliente(idCliente);
//	}
	
	public List<Reserva> obtenerReservaByUsuario(String email){
		return reservaDAO.obtenerReservasUsuario(email);
	}
}
