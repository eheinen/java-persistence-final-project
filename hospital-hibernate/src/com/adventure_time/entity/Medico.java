package com.adventure_time.entity;

import java.util.HashSet;
import java.util.Set;

public class Medico {

	private int id;
	private String nome;
	private String telefone;
	private Set<Consulta> consultas = new HashSet<>();
	
	public Medico(){}
	
	public Medico(String nmMedico, String telefoneMedico) {
		super();
		this.nome = nmMedico;
		this.telefone = telefoneMedico;
	}
	
	public Medico(String nmMedico, String telefoneMedico, Set<Consulta> consultas) {
		super();
		this.nome = nmMedico;
		this.telefone = telefoneMedico;
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

	@Override
	public String toString() {
		return "Medico [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", consultas=" + consultas + "]";
	}
}
