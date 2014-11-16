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
				exemplar.setFilme(new FilmeController().obterFilme(res
						.getInt("id_filme")));
				exemplar.setStatus(StatusExemplarEnum.valueOf(res.getString("tp_status")));
				//exemplar.setCodigoReserva(res.getString("id_reserva"));
				listaExemplares.add(exemplar);
			}
			return listaExemplares;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : "
					+ e.getMessage());
			return null;
		}
	}

	public Exemplar obterExemplar(String codigoReserva) {
		String query = "SELECT * FROM exemplar WHERE id_exemplar = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exemplar"));
				exemplar.setFilme((new FilmeController().obterFilme(res
						.getInt("id_filme"))));
				exemplar.setStatus(StatusExemplarEnum.valueOf(res.getString("tp_status")));
				//exemplar.setCodigoReserva(res.getString("id_reserva"));
				listaExemplares.add(exemplar);
			}
			return listaExemplares.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : "
					+ e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Exemplar> obterExemplares(int filmeId) {
		String query = "SELECT * FROM exemplar ex JOIN filme fi on fi.id_filme = ex.id_filme WHERE ex.id_filme = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exemplar"));
				exemplar.setFilme((new FilmeController().obterFilme(res
						.getInt("id_filme"))));
				exemplar.setStatus(StatusExemplarEnum.valueOf(res.getString("tp_status")));
				//exemplar.setCodigoReserva(res.getString("id_reserva"));
				listaExemplares.add(exemplar);
			}
			return listaExemplares;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : "
					+ e.getMessage());
			return null;
		}
	}

	public void salvarExemplar(Exemplar exemplar) {
		String query = "INSERT INTO exemplar (id_filme, tp_status, id_reserva) VALUES (?,?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, exemplar.getFilme().getId());
			stm.setInt(2, exemplar.getStatus().ordinal());
			stm.setString(3, exemplar.getCodigoReserva());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar salvar Exemplar ] : "
					+ e.getMessage());
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
			System.out.println("[ Erro ao salvar exemplar editado ] : "
					+ e.getMessage());
		}
	}

	public void excluirExemplar(int id) {
		String query = "DELETE FROM exemplar WHERE id_exemplar = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar excluir Exemplar ] : "
					+ e.getMessage());
		}
	}
}
