package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Categoria;

public class CategoriaDAO {
	private Connection connection;

	public CategoriaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public ArrayList<Categoria> listarCategorias() {
		String query = "Select * from categoria";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
			while (res.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(res.getInt("id_categoria"));
				categoria.setDescricao(res.getString("de_descricao"));
				categoria.setValor(res.getDouble("vl_valor"));
				categoria.setDiasLocacao(res.getInt("de_prazo"));
				listaCategorias.add(categoria);
			}
			return listaCategorias;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar categorias ]" + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}

	public Categoria obterCategoria(int id){
		String query = "SELECT * FROM categoria WHERE id_categoria = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
			while (res.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(res.getInt("id_categoria"));
				categoria.setDescricao(res.getString("de_descricao"));
				categoria.setValor(res.getDouble("vl_valor"));
				categoria.setDiasLocacao(res.getInt("de_prazo"));
				listaCategorias.add(categoria);
			}
			return listaCategorias.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter Categoria ]" + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}
	public void salvarCategoria(Categoria categoria) {
		String query = "INSERT INTO categoria (de_descricao, vl_valor, de_prazo) VALUES (?,?,?)";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setString(1, categoria.getDescricao());
			stm.setDouble(2, categoria.getValor());
			stm.setInt(3, categoria.getDiasLocacao());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar salvar Categoria ]" + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}

	}

	public void editarCategoria(Categoria categoria) {
		String query = "UPDATE categoria SET de_descricao = ?, vl_valor = ?, de_prazo = ? WHERE id_categoria = ?";
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(query);
			stm.setString(1, categoria.getDescricao());
			stm.setDouble(2, categoria.getValor());
			stm.setInt(3, categoria.getDiasLocacao());
			stm.setInt(4, categoria.getId());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao salvar categoria editado ] " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}

	}

	public void excluirCategoria(int id) {
		String query = "DELETE FROM categoria WHERE id_categoria = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar exlcuir Categoria ] " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}

}
