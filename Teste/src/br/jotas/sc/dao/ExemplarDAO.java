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
				exemplar.setStatus(StatusExemplarEnum.getValue(res.getInt("tp_status")));
				listaExemplares.add(exemplar);
			}
			return listaExemplares;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : " + e.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
				exemplar.setStatus(StatusExemplarEnum.getValue(res.getInt("tp_status")));
				listaExemplares.add(exemplar);
			}
			if(listaExemplares.size()==0){
				throw new IndexOutOfBoundsException();
			}
			return listaExemplares.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public ArrayList<Exemplar> obterExemplarPorTitulo(String s) {
		String query = "SELECT * FROM exemplar e, filme f WHERE e.id_filme = f.id_filme AND f.de_titulo like ? ";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, "%"+s+"%");
			ResultSet res = stm.executeQuery();
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exemplar"));
				exemplar.setFilme((new FilmeController().obterFilme(res.getInt("id_filme"))));
				exemplar.setStatus(StatusExemplarEnum.getValue(res.getInt("tp_status")));
				listaExemplares.add(exemplar);
			}
			if(listaExemplares.size()==0){
				throw new IndexOutOfBoundsException();
			}
			return listaExemplares;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public ArrayList<Exemplar> obterQuantidadeDeExemplares(int idFilme) {
		String query = "SELECT * FROM exemplar e, filme f WHERE e.id_filme = f.id_filme AND f.id_filme = ? ";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, idFilme);
			ResultSet res = stm.executeQuery();
			ArrayList<Exemplar> listaExemplares = new ArrayList<Exemplar>();
			while (res.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setIdExemplar(res.getInt("id_exemplar"));
				exemplar.setFilme((new FilmeController().obterFilme(res.getInt("id_filme"))));
				exemplar.setStatus(StatusExemplarEnum.getValue(res.getInt("tp_status")));
				listaExemplares.add(exemplar);
			}
			if(listaExemplares.size()==0){
				throw new IndexOutOfBoundsException();
			}
			return listaExemplares;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar exemplares ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
				System.out.println("[ Erro ao tentar salvar Exemplar ] : " + e.getMessage());
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

	public void editarExemplar(Exemplar exemplar) {
		String query = "UPDATE exemplar SET id_filme = ?, tp_status = ? WHERE id_exemplar = ?";
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
				System.out.println("[ Erro ao salvar exemplar editado ] : " + e.getMessage());
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
				System.out.println("[ Erro ao tentar excluir Exemplar ] : " + e.getMessage());
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
