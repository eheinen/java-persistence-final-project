package com.adventure_time.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache(region = "cache1", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String telefone;
	private Set<Consulta> consultas = new HashSet<>();
	
	public Paciente(){}
	
	public Paciente(String nome, String telefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public Paciente(String nome, String telefone, Set<Consulta> consultas) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.consultas = consultas;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}
}
