package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Cliente;

public class ClienteDAO {

	private Connection connection;

	public ClienteDAO() throws SQLException {
		this.connection = new ConnectionFactory().getConnection();
	}

	public ArrayList<Cliente> listarClientes() throws SQLException {
		String query = "Select * from cliente";		
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();			
			while(res.next()){
				Cliente cliente = new Cliente();
				cliente.setNome(res.getString("de_nome"));
				cliente.setCpf(res.getString("de_cpf"));
				cliente.setEndereco(res.getString("de_endereco"));
				cliente.setId(res.getInt("id_cliente"));
				cliente.setDataNascimento(res.getDate("dt_nasc"));
				cliente.setTelefone(res.getString("de_telefone"));
				listaClientes.add(cliente);
				
			}
			return listaClientes; 			
		} finally {
			connection.close();
		}
	}

	public void salvarCliente(Cliente cliente) throws SQLException {
		String query = "INSERT INTO cliente (de_nome, de_cpf, de_endereco, de_telefone, dt_nasc) VALUES (?,?,?,?,?)";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getCpf());
			stm.setString(3, cliente.getEndereco());
			stm.setString(4, cliente.getTelefone());
			stm.setString(5, sdf.format(cliente.getDataNascimento()));
			stm.execute();
		} finally {
			connection.close();
		}

	}

	public void excluirCliente(int id) throws SQLException {
		String query = "DELETE FROM cliente WHERE id_cliente = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
		} finally {
			connection.close();
		}
	}

}
