package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Devolucao;

public class DevolucaoDAO {

	private static DevolucaoDAO instance;
	private Connection con;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public DevolucaoDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static DevolucaoDAO getInstance() {
		if (instance == null) {
			instance = new DevolucaoDAO();
		}
		return instance;
	}

	public ArrayList<Devolucao> listarDevolucoes() {
		String query = "Select * from devolucao";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Devolucao> listaDevolucoes = new ArrayList<Devolucao>();
			while (res.next()) {
				Devolucao devolucao = new Devolucao();
				devolucao.setId(res.getInt("id_devolucao"));
				devolucao.setLocacao(new LocacaoController().obterLocacao(res.getInt("id_locacao")));
				devolucao.setDataRealDevolucao(res.getDate("dt_devolucao"));
				devolucao.setValorTotal(res.getDouble("vl_valor"));
				devolucao.setMulta(res.getDouble("vl_multa"));
				listaDevolucoes.add(devolucao);
			}
			return listaDevolucoes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar Devoluções ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Devolucao obterDevolucao(int id) {
		String query = "SELECT * FROM devolucao WHERE id_devolucao = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Devolucao> listaDevolucoes = new ArrayList<Devolucao>();
			while (res.next()) {
				Devolucao devolucao = new Devolucao();
				devolucao.setId(res.getInt("id_devolucao"));
				devolucao.setLocacao(new LocacaoController().obterLocacao(res.getInt("id_locacao")));
				devolucao.setDataRealDevolucao(res.getDate("dt_devolucao"));
				devolucao.setValorTotal(res.getDouble("vl_valor"));
				devolucao.setMulta(res.getDouble("vl_multa"));
				listaDevolucoes.add(devolucao);
			}
			return listaDevolucoes.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar Devoluções ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public double obterValorPorCliente(int id, Date dataInicio, Date dataFinal) {
		String query = "SELECT SUM(d.vl_valor) FROM cliente c, locacao l, devolucao d WHERE c.id_cliente = l.id_cliente AND l.id_locacao = d.id_locacao AND l.dt_locacao between ? and ? AND c.id_cliente = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, sdf.format(dataInicio));
			stm.setString(2, sdf.format(dataFinal));
			stm.setInt(3, id);
			ResultSet res = stm.executeQuery();
			double valor = 0;
			while (res.next()) {
				valor = res.getDouble(1);
			}
			return valor;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter valor por Cliente ] : " + e.getMessage());
			return -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public double obterValorPorFilme(int id, Date dataInicio, Date dataFinal) {
		String query = "SELECT SUM(d.vl_valor) FROM exemplar e, locacao l, devolucao d WHERE d.id_locacao = l.id_locacao AND l.id_exemplar = e.id_exemplar AND l.dt_locacao between ? and ? AND e.id_filme = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, sdf.format(dataInicio));
			stm.setString(2, sdf.format(dataFinal));
			stm.setInt(3, id);
			ResultSet res = stm.executeQuery();
			double valor = 0;
			while (res.next()) {
				valor = res.getDouble(1);
			}
			return valor;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter valor por Cliente ] : " + e.getMessage());
			return -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void salvarDevolucao(Devolucao devolucao) {
		String query = "INSERT INTO devolucao (id_locacao, dt_devolucao, vl_valor, vl_multa) VALUES (?,?,?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, devolucao.getLocacao().getId());
			stm.setString(2, sdf.format(devolucao.getDataRealDevolucao()));
			stm.setDouble(3, devolucao.getValorTotal());
			stm.setDouble(4, devolucao.getMulta());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar salvar Devolução ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void editarDevolucao(Devolucao devolucao) {
		String query = "UPDATE devolucao SET id_locacao = ?, dt_devolucao = ?, vl_valor = ? , vl_multa = ?WHERE id_devolucao = ?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			stm.setInt(1, devolucao.getLocacao().getId());
			stm.setString(2, sdf.format(devolucao.getDataRealDevolucao()));
			stm.setDouble(3, devolucao.getValorTotal());
			stm.setDouble(4, devolucao.getMulta());
			stm.setInt(5, devolucao.getId());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao editar Devolução ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void excluirDevolucao(int id) {
		String query = "DELETE FROM devolucao WHERE id_devolucao = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar exlcuir Devolução ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
