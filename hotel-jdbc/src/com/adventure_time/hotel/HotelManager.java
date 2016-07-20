package com.adventure_time.hotel;

import java.util.Set;

import com.adventure_time.dao.ClienteDao;
import com.adventure_time.dao.QuartoDao;
import com.adventure_time.entity.Cliente;
import com.adventure_time.entity.Quarto;

public class HotelManager {

	private final QuartoDao quartoDao = new QuartoDao();
	private final ClienteDao clienteDao = new ClienteDao();
	
	public String cadastrarCliente(String username){
		Cliente cliente = new Cliente();
		cliente.setUsername(username);
		cliente.setNumReservas(0);
		cliente.setValorTotal("0");
		String msg = "Cliente " + username;
		try {
			clienteDao.add(cliente);
			msg += " foi cadastrado com sucesso!";
		} catch (Exception e) {
			msg = "Escolha outro username!";
			e.printStackTrace();
		}
		return msg;
	}
	
	public String deletarCliente(String username){
		String msg = "Cliente " + username;
		try {
			Cliente cliente = clienteDao.findByUsername(username);
			if(cliente != null){
				clienteDao.delete(cliente);
				msg += " foi deletado com sucesso!";
			}else{
				msg += " não foi deletado. Este cliente não possui cadastro!";
			}
		} catch (Exception e) {			
			e.printStackTrace();
			msg = "Ocorreu um erro!";
		}
		return msg;
	}
	
	public Cliente acharCliente(String username){
		Cliente cliente = null;
		try {
			cliente = clienteDao.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public String reservarQuarto(Cliente cliente, Quarto quarto){
		String msg = null;
		try {
			msg = quartoDao.reservarQuarto(quarto, cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public Set<Quarto> listQuartos() {
		Set<Quarto> quartos = null;
		try {
			quartos = quartoDao.listQuartos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quartos;
	}
}
