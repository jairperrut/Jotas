package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.jotas.sc.controller.FilmeController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Exemplar;

public class ExemplarDAO {

	private Connection connection;

	public ExemplarDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	public ArrayList<Exemplar> listarExemplares() {
		String query = "Select * from exemplar";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exemplar"));
				exemplar.setFilme(new FilmeController().obterFilme(res.getInt("id_filme")));
				exemplar.setQuantidade(res.getInt("nu_quantidade"));
				exemplar.setStatus(res.getString("tp_status"));
				listaExemplares.add(exemplar);
			}
			return listaExemplares;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplars ]" + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}

	public Exemplar obterExemplar(int id){
		String query = "SELECT * FROM exemplar WHERE id_exemplar = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exeomlar"));
				exemplar.setFilme((new FilmeController().obterFilme(res.getInt("id_filme"))));
				exemplar.setQuantidade(res.getInt("nu_quantidade"));
				exemplar.setStatus(res.getString("tp_status"));
				listaExemplares.add(exemplar);
			}
			return listaExemplares.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplars ]" + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}
	public void salvarExemplar(Exemplar exemplar) {
		String query = "INSERT INTO exemplar (id_filme, nu_quantidade, tp_status) VALUES (?,?,?)";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, exemplar.getFilme().getId());
			stm.setInt(2, exemplar.getQuantidade());
			stm.setString(3, exemplar.getStatus());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar salvar Exemplar ]" + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}

	}

	public void editarExemplar(Exemplar exemplar) {
		String query = "UPDATE exemplar SET id_filme = ?, nu_quantidade = ?, tp_status = ? WHERE id_exemplar = ?";
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(query);
			stm.setInt(1, exemplar.getFilme().getId());
			stm.setInt(2, exemplar.getQuantidade());
			stm.setString(3, exemplar.getStatus());
			stm.setInt(4, exemplar.getIdExemplar());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao salvar exemplar editado ] " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}

	}

	public void excluirExemplar(int id) {
		String query = "DELETE FROM exemplar WHERE id_exemplar = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar exlcuir Exemplar ] " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ]" + e.getMessage());
			}
		}
	}

	
}
