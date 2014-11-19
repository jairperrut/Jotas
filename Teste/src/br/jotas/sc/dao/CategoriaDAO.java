package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Categoria;

public class CategoriaDAO {

	private static CategoriaDAO instance;
	private Connection con;

	public CategoriaDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static CategoriaDAO getInstance() {
		if (instance == null) {
			instance = new CategoriaDAO();
		}
		return instance;
	}

	public ArrayList<Categoria> listarCategorias() {
		String query = "Select * from categoria";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
			while (res.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(res.getInt("id_categoria"));
				categoria.setDescricao(res.getString("de_descricao"));
				categoria.setValor(res.getDouble("vl_valor"));
				categoria.setDiasLocacao(res.getInt("nu_prazo"));
				listaCategorias.add(categoria);
			}
			return listaCategorias;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar categorias ]"
					+ e.getMessage());
			return null;
		}
	}
	
	public Categoria obterCategoria(int id) {
		String query = "SELECT * FROM categoria WHERE id_categoria = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
			while (res.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(res.getInt("id_categoria"));
				categoria.setDescricao(res.getString("de_descricao"));
				categoria.setValor(res.getDouble("vl_valor"));
				categoria.setDiasLocacao(res.getInt("nu_prazo"));
				listaCategorias.add(categoria);
			}
			return listaCategorias.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter Categoria ]"
					+ e.getMessage());
			return null;
		}
	}

	public void salvarCategoria(Categoria categoria) {
		String query = "INSERT INTO categoria (de_descricao, vl_valor, nu_prazo) VALUES (?,?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, categoria.getDescricao());
			stm.setDouble(2, categoria.getValor());
			stm.setInt(3, categoria.getDiasLocacao());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao tentar salvar Categoria ]"
					+ e.getMessage());
		}

	}

	public void editarCategoria(Categoria categoria) {
		String query = "UPDATE categoria SET de_descricao = ?, vl_valor = ?, nu_prazo = ? WHERE id_categoria = ?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			stm.setString(1, categoria.getDescricao());
			stm.setDouble(2, categoria.getValor());
			stm.setInt(3, categoria.getDiasLocacao());
			stm.setInt(4, categoria.getId());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao salvar categoria editado ] "
					+ e.getMessage());
		}

	}

	public void excluirCategoria(int id) {
		String query = "DELETE FROM categoria WHERE id_categoria = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao tentar exlcuir Categoria ] "
					+ e.getMessage());
		}
	}

}
