package com.adventure_time.entity;

import java.io.Serializable;

public class Quarto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String codigoRoom;
	private String preco;
	private int clienteId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigoRoom() {
		return codigoRoom;
	}
	public void setCodigoRoom(String codigoRoom) {
		this.codigoRoom = codigoRoom;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
}
