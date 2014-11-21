package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.jotas.sc.controller.CategoriaController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Filme;

public class FilmeDAO {

	private static FilmeDAO instance;
	private Connection con;

	public FilmeDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static FilmeDAO getInstance() {
		if (instance == null) {
			instance = new FilmeDAO();
		}
		return instance;
	}

	public ArrayList<Filme> listarFilmes() {
		String query = "Select * from filme";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Filme> listaFilmes = new ArrayList<Filme>();
			while (res.next()) {
				Filme filme = new Filme();
				filme.setId(res.getInt("id_filme"));
				filme.setCategoria(new CategoriaController().obterCategoria(res.getInt("id_categoria")));
				filme.setTitulo(res.getString("de_titulo"));
				filme.setGenero(res.getString("de_genero"));
				filme.setAno(res.getInt("nu_ano"));
				listaFilmes.add(filme);
			}
			return listaFilmes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar filmes ] : " + e.getMessage());
			return null;
		
		}
	}
	
	public Filme obterFilme(int id){
		String query = "SELECT * FROM filme WHERE id_filme = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Filme> listaFilmes = new ArrayList<Filme>();
			while (res.next()) {
				Filme filme = new Filme();
				filme.setId(res.getInt("id_filme"));
				filme.setCategoria(new CategoriaController().obterCategoria(res.getInt("id_categoria")));
				filme.setTitulo(res.getString("de_titulo"));
				filme.setGenero(res.getString("de_genero"));
				filme.setAno(res.getInt("nu_ano"));
				listaFilmes.add(filme);
			}
			return listaFilmes.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar filmes ] : " + e.getMessage());
			return null;
		
		}
	}

	public int salvarFilme(Filme filme) {
		String query = "INSERT INTO filme (de_titulo, id_categoria, nu_ano, de_genero) VALUES (?,?,?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, filme.getTitulo());
			stm.setInt(2, filme.getCategoria().getId());
			stm.setInt(3, filme.getAno());
			stm.setString(4, filme.getGenero());
			stm.execute();
			con.commit();
			ResultSet res = stm.getGeneratedKeys();
			while(res.next()){
				filme.setId(res.getInt(1));
			}
			return filme.getId();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar salvar Filme ] : " + e.getMessage());
			return 0;		
		}

	}

	public void editarFilme(Filme filme) {
		String query = "UPDATE filme SET de_titulo = ?, id_categoria = ?, nu_ano = ?, de_genero = ? WHERE id_filme = ?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			stm.setString(1, filme.getTitulo());
			stm.setInt(2, filme.getCategoria().getId());
			stm.setInt(3, filme.getAno());
			stm.setString(4, filme.getGenero());
			stm.setInt(5, filme.getId());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			System.out.println("[ Erro ao salvar filme editado ] : " + e.getMessage());
		
		}

	}

	public void excluirFilme(int id) {
		String query = "DELETE FROM filme WHERE id_filme = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar exlcuir Filme ] : " + e.getMessage());
		
		}
	}
}
