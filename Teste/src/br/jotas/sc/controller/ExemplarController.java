package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.jotas.sc.dao.ExemplarDAO;
import br.jotas.sc.model.Exemplar;

public class ExemplarController {

	public void salvarExemplar(Exemplar exemplar) throws NullPointerException, Exception {
		ExemplarDAO dao = new ExemplarDAO();
		if (exemplar.getId() != 0) {
			dao.editarExemplar(exemplar);
		} else {
			dao.salvarExemplar(exemplar);
		}
	}

	public Exemplar obterExemplar(int id) {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.obterExemplar(id);
	}

	public ArrayList<Exemplar> listarExemplar() {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.listarExemplares();
	}

	public void excluirExemplar(int id) throws SQLException {
		ExemplarDAO dao = new ExemplarDAO();
		dao.excluirExemplar(id);
	}

}
