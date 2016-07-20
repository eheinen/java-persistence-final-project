package com.adventure_time.app;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.adventure_time.dao.QuartoDao;
import com.adventure_time.entity.Quarto;
import com.adventure_time.hotel.HotelManager;

public class App {

	public static void main(String[] args) {
		//Cadastra diversos clientes para se efetuar os tests
		cadastrarClientes();

		//Cadastra diversos quartos para se efetuar os tests
		cadastrarQuartos();

		/*
		São criadas três threads onde cada uma faz o papel de um cliente diferente,
		tentando reservar um quarto.
		*/
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(4);
			service.submit(() -> {
				HotelManager htm = new HotelManager();
				Set<Quarto> quartos = htm.listQuartos();
				//Loop para achar o quarto 18
				quartos.forEach((quarto) -> {
					if(quarto.getCodigoRoom().equals("Quarto 18")){
						/*
						Faz a reserva do para o cliente 7.
						Mostra menssagem se a reserva foi efetivada ou não
						*/
						System.out.println(htm.reservarQuarto(htm.acharCliente("Cliente 7"), quarto));
					}
				});
			});

			service.submit(() -> {
				HotelManager htm = new HotelManager();
				Set<Quarto> quartos = htm.listQuartos();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				quartos.forEach((quarto) -> {
					if(quarto.getCodigoRoom().equals("Quarto 5")){
						System.out.println(htm.reservarQuarto(htm.acharCliente("Cliente 9"), quarto));
					}
				});
			});

			service.submit(() -> {
				HotelManager htm = new HotelManager();
				Set<Quarto> quartos = htm.listQuartos();
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				quartos.forEach((quarto) -> {
					if(quarto.getCodigoRoom().equals("Quarto 18")){
						System.out.println(htm.reservarQuarto(htm.acharCliente("Cliente 5"), quarto));
					}
				});
			});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(service != null){
				service.shutdown();
			}
		}
	}

	private static void cadastrarClientes(){
		HotelManager htm = new HotelManager();
		for(int i=0; i < 20; i++){
			System.out.println(htm.cadastrarCliente("Cliente " + i));
		}
	}

	private static void cadastrarQuartos(){
		QuartoDao quartoDao = new QuartoDao();
		for(int i=0; i < 20; i++){
			Quarto quarto = new Quarto();
			quarto.setCodigoRoom("Quarto " + i);
			quarto.setPreco("75");
			try {
				quartoDao.add(quarto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
