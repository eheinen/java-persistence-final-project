package com.adventure_time.dao;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.spi.SyncProviderException;
import javax.sql.rowset.spi.SyncResolver;

import com.adventure_time.entity.Cliente;
import com.adventure_time.entity.Quarto;

public class QuartoDao extends Dao{

	private CachedRowSet crs;

	public void add(Quarto quarto) throws Exception{
		try {
			openConn();
			String sql = "INSERT INTO QUARTOS (CODIGO_ROOM, PRECO) VALUES (?,?)";
			stmt =  conn.prepareStatement(sql);
			stmt.setString(1, quarto.getCodigoRoom());
			stmt.setString(2, quarto.getPreco());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			closeConn();
		}
	}

	public void delete(Quarto quarto) throws Exception{
		try {
			openConn();
			String sql = "DELETE FROM QUARTOS WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, quarto.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			closeConn();
		}
	}

	public Set<Quarto> listQuartos() throws Exception{
		try{
			openConn();
			String sql = "SELECT * FROM QUARTOS";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			crs = RowSetProvider.newFactory().createCachedRowSet();
			//Insere dados no objeto CachedRowSet
			crs.populate(rs);
			Set<Quarto> quartos = new HashSet<>();
			while(crs.next()){
				Quarto quarto = new Quarto();
				quarto.setClienteId(crs.getInt("CLIENTE_ID"));
				quarto.setCodigoRoom(crs.getString("CODIGO_ROOM"));
				quarto.setPreco(crs.getString("PRECO"));
				quarto.setId(crs.getInt("ID"));
				quartos.add(quarto);
			}
			crs.beforeFirst();
			return quartos;
		}catch(Exception e){
			throw e;
		}finally{
			closeConn();
		}
	}
	/*
	Este método é responsável por fazer reserva de quarto para um hotel. 
	Recebe como argumento o quarto escolhido e o cliente que está realizando a reserva. 
	Para garantir integridade nos dados o valor a ser pago e o número de quartos que 
	este cliente reservou são atualizados no mesmo bloco(na mesma transação) que a operação 
	que insere o cliente ID no quarto escolhido, tornando-o indisponível para outros clientes.
	Caso haja algum erro todos as operações desta transaçãoo são descartadas.
	*/

	public String reservarQuarto(Quarto quarto, Cliente cliente) throws Exception{
		String msg = "O quarto " + quarto.getCodigoRoom() + " j� foi reservado, escolha outro!";
		try {
			openConn();
			conn.setAutoCommit(false);
			while(crs.next()){
				if(crs.getInt("ID") == quarto.getId()){
					if(crs.getInt("CLIENTE_ID") == 0){
						String sql = "UPDATE CLIENTES SET VALOR_TOTAL=?, NUM_RESERVAS=? WHERE ID=?";
						stmt = conn.prepareStatement(sql);
						Float totalPagar = Float.parseFloat(cliente.getValorTotal()) + Float.parseFloat(quarto.getPreco());
						stmt.setString(1, new DecimalFormat("##.##").format(totalPagar));
						stmt.setInt(2, cliente.getNumReservas() + 1);
						stmt.setInt(3, cliente.getId());
						stmt.executeUpdate();
						crs.updateInt("CLIENTE_ID", cliente.getId());
						crs.updateRow();
						/*o método acceptChanges() é usado para propagar as alterações 
						 * feitas no objeto CachedRowSet para o banco de dados e também é 
						 * responsável por salvar as alterações como se fosse chamado commit().
						*/
						crs.acceptChanges(conn);
						msg = "O cliente " + cliente.getUsername() + " reservou o quarto " +
								quarto.getCodigoRoom() + " com sucesso!";
						break;
					}
				}
			}
			return msg;
		}catch(SyncProviderException e){
			return msg;
		} catch (Exception e) {
			//em caso de algum erro todas as altera��es s�o revertidas
			conn.rollback();
			throw e;
		}finally{
			closeConn();
		}
	}
}
