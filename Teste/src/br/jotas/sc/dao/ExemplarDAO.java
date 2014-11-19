package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.jotas.sc.controller.FilmeController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Exemplar;
import br.jotas.sc.model.StatusExemplarEnum;

public class ExemplarDAO {

	private static ExemplarDAO instance;
	private Connection con;

	public ExemplarDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static ExemplarDAO getInstance() {
		if (instance == null) {
			instance = new ExemplarDAO();
		}
		return instance;
	}

	public ArrayList<Exemplar> listarExemplares() {
		String query = "Select * from exemplar";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exemplar"));
				exemplar.setFilme(new FilmeController().obterFilme(res.getInt("id_filme")));

				// TODO: Só pra testar, tem que arrumar esse Status				
				exemplar.setStatus(StatusExemplarEnum.DISPONIVEL);
				listaExemplares.add(exemplar);
			}
			return listaExemplares;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : " + e.getMessage());
			return null;
		}
	}

	public Exemplar obterExemplar(int id) {
		String query = "SELECT * FROM exemplar WHERE id_exemplar = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();			
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exemplar"));
				exemplar.setFilme((new FilmeController().obterFilme(res.getInt("id_filme"))));
				exemplar.setStatus(StatusExemplarEnum.valueOf(res.getString("tp_status")));
				listaExemplares.add(exemplar);
			}
			return listaExemplares.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : " + e.getMessage());
			return null;
		}
	}

	public ArrayList<Exemplar> listarExemplaresPorFilme(int id) {
		String query = "SELECT * FROM exemplar WHERE id_filme = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exemplar"));
				exemplar.setFilme(new FilmeController().obterFilme(res.getInt("id_filme")));
				exemplar.setStatus(StatusExemplarEnum.valueOf(res.getString("tp_status")));
				listaExemplares.add(exemplar);
			}
			return listaExemplares;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : " + e.getMessage());
			return null;
		}
	}

	public void salvarExemplar(Exemplar exemplar) {
		String query = "INSERT INTO exemplar (id_filme, tp_status) VALUES (?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, exemplar.getFilme().getId());
			stm.setInt(2, exemplar.getStatus().ordinal());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao tentar salvar Exemplar ] : " + e.getMessage());
		}
	}

	public void editarExemplar(Exemplar exemplar) {
		String query = "UPDATE exemplar SET id_filme = ?, tp_status = ?, id_reserva WHERE id_exemplar = ?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			stm.setInt(1, exemplar.getFilme().getId());
			stm.setInt(2, exemplar.getStatus().ordinal());
			stm.setInt(3, exemplar.getIdExemplar());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao salvar exemplar editado ] : " + e.getMessage());
		}
	}

	public void excluirExemplar(int id) {
		String query = "DELETE FROM exemplar WHERE id_exemplar = ?";
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
			System.out.println("[ Erro ao tentar excluir Exemplar ] : " + e.getMessage());
		}
	}

}
