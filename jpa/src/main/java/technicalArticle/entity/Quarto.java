/**
 * 
 */
package technicalArticle.entity;

import javax.persistence.*;

/**
 * jpa / Quartos.java
 * FIAP / RM30222 - Vagner Panarello
 */

@Entity
@Table(name="quartos")
public class Quarto {
	
	public Quarto(String codigoRoom, String preco) {
		super();
		this.codigoRoom = codigoRoom;
		this.preco = preco;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="codigo_room")
	private String codigoRoom;
	
	@Column(name="preco")
	private String preco;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
