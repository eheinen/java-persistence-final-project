/**
 * 
 */
package technicalArticle.entity;

import java.util.List;

import javax.persistence.*;

/**
 * jpa / Clientes.java
 * FIAP / RM30222 - Vagner Panarello
 */

@Entity
@Table(name="clientes")
public class Cliente {
	
	
	public Cliente(String username, String valorTotal, int numeroReservas) {
		super();
		this.username = username;
		this.valorTotal = valorTotal;
		this.numeroReservas = numeroReservas;
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="valor_total")
	private String valorTotal;
	
	
	@Column(name="num_reserva")
	private int numeroReservas;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente")
	private List<Quarto> quartos;


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
	public int getNumeroReservas() {
		return numeroReservas;
	}
	public void setNumeroReservas(int numeroReservas) {
		this.numeroReservas = numeroReservas;
	}
	public List<Quarto> getQuartos() {
		return quartos;
	}
	public void setQuartos(List<Quarto> quartos) {
		this.quartos = quartos;
	}
}
