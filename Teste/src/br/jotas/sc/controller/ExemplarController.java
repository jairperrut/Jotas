package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.jotas.sc.dao.ExemplarDAO;
import br.jotas.sc.model.Exemplar;

public class ExemplarController {

	public void salvarExemplar(Exemplar exemplar) throws NullPointerException,
			Exception {
		ExemplarDAO dao = new ExemplarDAO();
		if (exemplar.getIdExemplar() != 0) {
			dao.editarExemplar(exemplar);
		} else {
			dao.salvarExemplar(exemplar);
		}
	}

	public Exemplar obterExemplar(int id) throws IndexOutOfBoundsException {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.obterExemplar(id);
	}

	public ArrayList<Exemplar> obterExemplarPorTitulo(String s)
			throws IndexOutOfBoundsException {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.obterExemplarPorTitulo(s);
	}

	public ArrayList<Exemplar> listarExemplares() {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.listarExemplares();
	}

	public ArrayList<Exemplar> listarExemplaresPorFilme(int id) {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.listarExemplaresPorFilme(id);
	}

	public ArrayList<Exemplar> obterQuantidadeDeExemplares(int idFilme) {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.obterQuantidadeDeExemplares(idFilme);
	}

	public void excluirExemplar(int id) throws SQLException {
		ExemplarDAO dao = new ExemplarDAO();
		dao.excluirExemplar(id);
	}

}
