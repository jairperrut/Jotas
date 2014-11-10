package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Locacao;

public class LocacaoDAO {

	private Connection connection;

	public LocacaoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public ArrayList<Locacao> listarLocacoes() {
		String query = "Select * from locacao";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Locacao> listaLocacoes = new ArrayList<Locacao>();
			while (res.next()) {
				Locacao locacao = new Locacao();
				locacao.setId(res.getInt("id_locacao"));
				locacao.setCliente(new ClienteController().obterCliente(res.getInt("id_cliente")));
				locacao.setExemplar(new ExemplarController().obterExemplar(res.getInt("id_exemplar")));
				locacao.setDataLocacao(res.getDate("dt_locacao"));
				locacao.setPrazo(res.getDate("dt_prazo"));
				locacao.setValor(res.getDouble("vl_valor"));
				locacao.setPago(res.getBoolean("fl_pago"));
				listaLocacoes.add(locacao);
			}
			return listaLocacoes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar locações ] : " + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}
	}
	
	public Locacao obterLocacao(int id){
		String query = "SELECT * FROM locacao WHERE id_locacao = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Locacao> listaLocacoes= new ArrayList<Locacao>();
			while (res.next()) {
				Locacao locacao = new Locacao();
				locacao.setId(res.getInt("id_locacao"));
				locacao.setCliente(new ClienteController().obterCliente(res.getInt("id_cliente")));
				locacao.setExemplar(new ExemplarController().obterExemplar(res.getInt("id_exemplar")));
				locacao.setDataLocacao(res.getDate("dt_locacao"));
				locacao.setPrazo(res.getDate("dt_prazo"));
				locacao.setValor(res.getDouble("vl_valor"));
				locacao.setPago(res.getBoolean("fl_pago"));
				listaLocacoes.add(locacao);
			}
			return listaLocacoes.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar Locações ] : " + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}
	}

	public void salvarLocacao(Locacao locacao) {
		String query = "INSERT INTO locacao (id_cliente, id_exemplar, dt_locacao, dt_prazo, vl_locacao, fl_pago) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, locacao.getCliente().getId());
			stm.setInt(2, locacao.getExemplar().getIdExemplar());
			stm.setDate(3,(java.sql.Date) locacao.getDataLocacao());
			stm.setDate(4,(java.sql.Date) locacao.getPrazo());
			stm.setDouble(5, locacao.getValor());
			stm.setBoolean(6, locacao.isPago());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar salvar Locação ] : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}

	}

	public void editarLocacao(Locacao locacao) {
		String query = "UPDATE locacao SET id_cliente = ?, id_exemplar = ?, dt_locacao = ?, dt_prazo = ?, vl_locacao = ?, fl_pago = ? WHERE id_locacao = ?";
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(query);
			stm.setInt(1, locacao.getCliente().getId());
			stm.setInt(2, locacao.getExemplar().getIdExemplar());
			stm.setDate(3,(java.sql.Date) locacao.getDataLocacao());
			stm.setDate(4,(java.sql.Date) locacao.getPrazo());
			stm.setDouble(5, locacao.getValor());
			stm.setBoolean(6, locacao.isPago());
			stm.setInt(7, locacao.getId());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao editar Locação ] : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}

	}

	public void excluirLocacao(int id) {
		String query = "DELETE FROM locacao WHERE id_locacao = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar exlcuir Locacao ] : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}
	}
}
