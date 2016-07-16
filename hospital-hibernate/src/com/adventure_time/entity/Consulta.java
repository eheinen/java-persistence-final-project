package com.adventure_time.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache(region = "cache1", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Date data;
	private Medico medico;
	private Paciente paciente;
	
	public Consulta(){}
	
	public Consulta(Date data) {
		super();
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
