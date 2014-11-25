package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.jotas.sc.dao.ExemplarDAO;
import br.jotas.sc.model.Exemplar;

public class ExemplarController {

	public void salvarExemplar(Exemplar exemplar) throws NullPointerException, Exception {
		ExemplarDAO dao = new ExemplarDAO();
		if (exemplar.getIdExemplar() != 0) {
			dao.editarExemplar(exemplar);
		} else {
			dao.salvarExemplar(exemplar);
		}
	}
	
	public Exemplar obterExemplar(int id) {
		try{	
		ExemplarDAO dao = new ExemplarDAO();
		return dao.obterExemplar(id);
		}catch (IndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(null, "Exemplar inválido");
			return null;
		}
	}	
	
	public ArrayList<Exemplar> obterExemplarPorTitulo(String s) {
		try{	
		ExemplarDAO dao = new ExemplarDAO();
		return dao.obterExemplarPorTitulo(s);
		}catch (IndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(null, "Título do exemplar inválido");
			return null;
		}
	}	
	
	public ArrayList<Exemplar> listarExemplares() {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.listarExemplares();
	}
	
	public ArrayList<Exemplar> listarExemplaresPorFilme(int id) {
		ExemplarDAO dao = new ExemplarDAO();
		return dao.listarExemplaresPorFilme(id);
	}

	public void excluirExemplar(int id) throws SQLException {
		ExemplarDAO dao = new ExemplarDAO();
		dao.excluirExemplar(id);
	}

}
