package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.CategoriaFilmeEnum;
import br.jotas.sc.model.Locacao;
import br.jotas.sc.model.StatusExemplarEnum;

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
				locacao.setValor(res.getDouble("vl_locacao"));
				locacao.setPago(res.getBoolean("fl_pago"));
				listaLocacoes.add(locacao);
			}
			return listaLocacoes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar locações ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int numeroDeLocacaoPorFilme(int id) {
		String query = "SELECT COUNT(*) FROM locacao l, exemplar e WHERE l.id_exemplar = e.id_exemplar AND e.id_filme = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			int quantidade = 0;
			while (res.next()) {
				quantidade = res.getInt(1);
			}
			return quantidade;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar locações ] : " + e.getMessage());
			return -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public ArrayList<Locacao> listarTodasLocacoesPorCliente(int id) {
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
				locacao.setValor(res.getDouble("vl_locacao"));
				locacao.setPago(res.getBoolean("fl_pago"));
				listaLocacoes.add(locacao);
			}
			return listaLocacoes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar locações ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Locacao> listarLocacoesPorCliente(int id) {
		String query = "Select * from locacao WHERE id_cliente = ? and id_locacao not in " + " (select id_locacao from devolucao)";
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
				locacao.setValor(res.getDouble("vl_locacao"));
				locacao.setPago(res.getBoolean("fl_pago"));
				listaLocacoes.add(locacao);
			}
			return listaLocacoes;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar Locações ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Locacao> listarLocacoesEmAtraso(Date dataInicial, Date dataFinal) {
		String query = " Select * from locacao where dt_prazo between ? and ? " + "	and id_locacao not in (Select id_locacao from devolucao) ";
		try {
			ArrayList<Locacao> locacoes = new ArrayList<Locacao>();
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, sdf.format(dataInicial));
			stm.setString(2, sdf.format(dataFinal));
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				Locacao locacao = new Locacao();
				locacao.setId(res.getInt("id_locacao"));
				locacao.setCliente(new ClienteController().obterCliente((res.getInt("id_cliente"))));
				locacao.setDataLocacao(res.getDate("dt_locacao"));
				locacao.setExemplar(new ExemplarController().obterExemplar(res.getInt("id_exemplar")));
				locacao.setPago(res.getBoolean("fl_pago"));
				locacao.setPrazo(res.getDate("dt_prazo"));
				locacao.setValor(res.getDouble("vl_locacao"));
				locacoes.add(locacao);
			}
			return locacoes;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
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
				locacao.setValor(res.getDouble("vl_locacao"));
				locacao.setPago(res.getBoolean("fl_pago"));
				listaLocacoes.add(locacao);
			}
			return listaLocacoes.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter locação ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Locacao obterLocacaoPorExemplar(int idExemplar) {
		String query = " select * from locacao loc " + " join exemplar exe on exe.id_exemplar = loc.id_exemplar "
				+ " join cliente cli on cli.id_cliente = loc.id_cliente" + " where exe.id_exemplar = ?" + " and loc.id_locacao not in "
				+ " (select dev.id_locacao from devolucao dev)";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, idExemplar);
			ResultSet res = stm.executeQuery();
			ArrayList<Locacao> listaLocacoes = new ArrayList<Locacao>();
			while (res.next()) {
				Locacao locacao = new Locacao();
				locacao.setId(res.getInt("id_locacao"));
				locacao.setCliente(new ClienteController().obterCliente(res.getInt("id_cliente")));
				locacao.setExemplar(new ExemplarController().obterExemplar(res.getInt("id_exemplar")));
				locacao.setDataLocacao(res.getDate("dt_locacao"));
				locacao.setPrazo(res.getDate("dt_prazo"));
				locacao.setValor(res.getDouble("vl_locacao"));
				locacao.setPago(res.getBoolean("fl_pago"));
				listaLocacoes.add(locacao);
			}
			if (listaLocacoes.size() > 0) {
				return listaLocacoes.get(0);
			} else {
				return new Locacao();
			}

		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter Locação ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
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
			System.out.println("[ Erro ao tentar salvar Locação ] : " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
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
				System.out.println("[ Erro ao tentar exlcuir Locacao ] : " + e.getMessage());
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
