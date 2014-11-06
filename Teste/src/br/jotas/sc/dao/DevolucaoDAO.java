package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Devolucao;

public class DevolucaoDAO {

	private Connection connection;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public DevolucaoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public ArrayList<Devolucao> listarDevolucoes() {
		String query = "Select * from devolucao";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<Devolucao> listaDevolucoes = new ArrayList<Devolucao>();
			while (res.next()) {
				Devolucao devolucao = new Devolucao();
				devolucao.setId(res.getInt("id_devolucao"));
				devolucao.setLocacao(new LocacaoController().obterLocacao(res.getInt("id_locacao")));
				devolucao.setDataRealDevolucao(res.getDate("dt_devolucao"));
				devolucao.setValorMulta(res.getDouble("vl_multa"));
				devolucao.setValorTotal(res.getDouble("vl_valor"));
				listaDevolucoes.add(devolucao);
			}
			return listaDevolucoes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar Devoluções ] : " + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}
	}
	
	public Devolucao obterDevolucao(int id){
		String query = "SELECT * FROM devolucao WHERE id_devolucao = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Devolucao> listaDevolucoes= new ArrayList<Devolucao>();
			while (res.next()) {
				Devolucao devolucao = new Devolucao();
				devolucao.setId(res.getInt("id_devolucao"));
				devolucao.setLocacao(new LocacaoController().obterLocacao(res.getInt("id_locacao")));
				devolucao.setValorMulta(res.getDouble("vl_multa"));
				devolucao.setValorTotal(res.getDouble("vl_valor"));
				listaDevolucoes.add(devolucao);
			}
			return listaDevolucoes.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar Devoluções ] : " + e.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}
	}

	public void salvarDevolucao(Devolucao devolucao) {
		String query = "INSERT INTO devolucao (id_locacao, dt_devolucao, vl_multa, vl_valor) VALUES (?,?,?,?)";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, devolucao.getLocacao().getId());
			stm.setString(2, sdf.format(devolucao.getDataRealDevolucao()));
			stm.setDouble(3, devolucao.getValorMulta());
			stm.setDouble(4, devolucao.getValorTotal());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar salvar Devolução ] : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}

	}

	public void editarDevolucao(Devolucao devolucao) {
		String query = "UPDATE devolucao SET id_cliente = ?, id_exemplar = ?, dt_devolucao = ?, dt_prazo = ?, vl_devolucao = ?, fl_pago = ? WHERE id_devolucao = ?";
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(query);
			stm.setInt(1, devolucao.getLocacao().getId());
			stm.setString(2, sdf.format(devolucao.getDataRealDevolucao()));
			stm.setDouble(3, devolucao.getValorMulta());
			stm.setDouble(4, devolucao.getValorTotal());
			stm.setInt(5, devolucao.getId());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao editar Devolução ] : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}

	}

	public void excluirDevolucao(int id) {
		String query = "DELETE FROM devolucao WHERE id_devolucao = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar exlcuir Devolução ] : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar fechar conexão ] : " + e.getMessage());
			}
		}
	}
}
