package com.adventure_time.entity;

import java.io.Serializable;

public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String valorTotal;
	private int numReservas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getNumReservas() {
		return numReservas;
	}
	public void setNumReservas(int numReservas) {
		this.numReservas = numReservas;
	}
}
