package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Cliente;

public class ClienteDAO {

	private static ClienteDAO instance;
	private Connection con;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public ClienteDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static ClienteDAO getInstance() {
		if (instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}

	public ArrayList<Cliente> listarClientes() {
		String query = "Select * from cliente";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
			while (res.next()) {
				Cliente cliente = new Cliente();
				cliente.setNome(res.getString("de_nome"));
				cliente.setCpf(res.getString("nu_cpf"));
				cliente.setEndereco(res.getString("de_endereco"));
				cliente.setId(res.getInt("id_cliente"));
				cliente.setDataNascimento(res.getDate("dt_nasc"));
				cliente.setTelefone(res.getString("nu_telefone"));
				listaClientes.add(cliente);
			}
			return listaClientes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar clientes ] : " + e.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Cliente obterCliente(int id) {
		String query = "Select * from cliente where id_cliente = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
			while (res.next()) {
				Cliente cliente = new Cliente();
				cliente.setNome(res.getString("de_nome"));
				cliente.setCpf(res.getString("nu_cpf"));
				cliente.setEndereco(res.getString("de_endereco"));
				cliente.setId(res.getInt("id_cliente"));
				cliente.setDataNascimento(res.getDate("dt_nasc"));
				cliente.setTelefone(res.getString("nu_telefone"));
				listaClientes.add(cliente);
			}
			return listaClientes.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter Cliente ] : " + e.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void salvarCliente(Cliente cliente) {
		String query = "INSERT INTO cliente (de_nome, nu_cpf, de_endereco, nu_telefone, dt_nasc) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getCpf());
			stm.setString(3, cliente.getEndereco());
			stm.setString(4, cliente.getTelefone());
			stm.setString(5, sdf.format(cliente.getDataNascimento()));
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar salvar Cliente ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void editarCliente(Cliente cliente) {
		String query = "UPDATE cliente SET de_nome = ?, nu_cpf = ?, de_endereco = ?, nu_telefone = ?, dt_nasc = ? WHERE id_cliente = ?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getCpf());
			stm.setString(3, cliente.getEndereco());
			stm.setString(4, cliente.getTelefone());
			stm.setString(5, sdf.format(cliente.getDataNascimento()));
			stm.setInt(6, cliente.getId());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao salvar cliente editado ] : " + e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void excluirCliente(int id) {
		String query = "DELETE FROM cliente WHERE id_cliente = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar exlcuir Cliente ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
