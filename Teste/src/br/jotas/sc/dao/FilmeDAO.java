package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.jotas.sc.controller.CategoriaController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Filme;

public class FilmeDAO {

	private Connection connection;

	public FilmeDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public ArrayList<Filme> listarFilmes() {
		String query = "Select * from filme";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Filme> listaFilmes = new ArrayList<Filme>();
			while (res.next()) {
				Filme filme = new Filme();
				filme.setId(res.getInt("id_filme"));
				filme.setCategoria(new CategoriaController().obterCategoria(res.getInt("id_categoria")));
				filme.setTitulo(res.getString("de_titulo"));
				filme.setGenero(res.getString("de_genero"));
				filme.setTipo(res.getString("tp_tipo"));
				filme.setAno(res.getInt("nu_ano"));
				listaFilmes.add(filme);
			}
			return listaFilmes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar filmes ]" + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}
	
	public Filme obterFilme(int id){
		String query = "SELECT * FROM filme WHERE id_filme = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Filme> listaFilmes = new ArrayList<Filme>();
			while (res.next()) {
				Filme filme = new Filme();
				filme.setId(res.getInt("id_filme"));
				filme.setCategoria(new CategoriaController().obterCategoria(res.getInt("id_categoria")));
				filme.setTitulo(res.getString("de_titulo"));
				filme.setGenero(res.getString("de_genero"));
				filme.setTipo(res.getString("tp_tipo"));
				filme.setAno(res.getInt("nu_ano"));
				listaFilmes.add(filme);
			}
			return listaFilmes.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar filmes ]" + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}

	public void salvarFilme(Filme filme) {
		String query = "INSERT INTO filme (de_titulo, id_categoria, nu_ano, de_genero, de_tipo) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setString(1, filme.getTitulo());
			stm.setInt(2, filme.getCategoria().getId());
			stm.setInt(3, filme.getAno());
			stm.setString(4, filme.getGenero());
			stm.setString(5, filme.getTipo());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar salvar Filme ]" + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}

	}

	public void editarFilme(Filme filme) {
		String query = "UPDATE filme SET de_titulo = ?, id_categoria = ?, nu_ano = ?, de_genero = ?, de_tipo = ? WHERE id_filme = ?";
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(query);
			stm.setString(1, filme.getTitulo());
			stm.setInt(2, filme.getCategoria().getId());
			stm.setInt(3, filme.getAno());
			stm.setString(4, filme.getGenero());
			stm.setString(5, filme.getTipo());
			stm.setInt(6, filme.getId());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao salvar filme editado ] " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}

	}

	public void excluirFilme(int id) {
		String query = "DELETE FROM filme WHERE id_filme = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar exlcuir Filme ] " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}
}
