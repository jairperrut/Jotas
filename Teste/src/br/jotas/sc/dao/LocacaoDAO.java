package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Locacao;

public class LocacaoDAO {

	private static LocacaoDAO instance;
	private Connection con;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public LocacaoDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static LocacaoDAO getInstance() {
		if (instance == null) {
			instance = new LocacaoDAO();
		}
		return instance;
	}

	public ArrayList<Locacao> listarLocacoes() {
		String query = "Select * from locacao";
		try {
			PreparedStatement stm = con.prepareStatement(query);
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
			System.out.println("[ Erro ao tentar listar loca��es ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conex�o ] : " + e.getMessage());
			}
		}
	}

	public ArrayList<Locacao> listarLocacoesPorCliente(int id) {
		String query = "Select * from locacao WHERE id_cliente = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
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
			System.out.println("[ Erro ao tentar listar Loca��es ] : " + e.getMessage());
			return null;
		}
	}

	public Locacao obterLocacao(int id) {
		String query = "SELECT * FROM locacao WHERE id_locacao = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
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
			return listaLocacoes.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar Loca��es ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conex�o ] : " + e.getMessage());
			}
		}
	}

	public void salvarLocacao(Locacao locacao) {
		String query = "INSERT INTO locacao (id_cliente, id_exemplar, dt_locacao, dt_prazo, vl_locacao, fl_pago) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, locacao.getCliente().getId());
			stm.setInt(2, locacao.getExemplar().getIdExemplar());
			stm.setString(3, sdf.format(locacao.getDataLocacao()));
			stm.setString(4, sdf.format(locacao.getPrazo()));
			stm.setDouble(5, locacao.getValor());
			stm.setBoolean(6, locacao.isPago());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao tentar salvar Loca��o ] : " + e.getMessage());
		}
	}

	public void pagarLocacao(int id) {
		String query = "UPDATE locacao SET fl_pago = ? WHERE id_locacao = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, 0);
			stm.setInt(2, id);
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void excluirLocacao(int id) {
		String query = "DELETE FROM locacao WHERE id_locacao = ?";
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
			System.out.println("[ Erro ao tentar exlcuir Locacao ] : " + e.getMessage());
		}
	}
}
