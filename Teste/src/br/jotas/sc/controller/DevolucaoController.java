package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import br.jotas.sc.dao.DevolucaoDAO;
import br.jotas.sc.model.Devolucao;
import br.jotas.sc.model.Locacao;

public class DevolucaoController {

	public void salvarDevolucao(Devolucao devolucao) {
		try {
			validaDados(devolucao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		DevolucaoDAO dao = new DevolucaoDAO();
		if (devolucao.getId() != 0) {
			dao.editarDevolucao(devolucao);
		} else {
			dao.salvarDevolucao(devolucao);
		}
	}

	public Devolucao obterDevolucao(int id) {
		DevolucaoDAO dao = new DevolucaoDAO();
		return dao.obterDevolucao(id);
	}

	public void excluirDevolucao(int id) {
		DevolucaoDAO dao = new DevolucaoDAO();
		dao.excluirDevolucao(id);
	}

	public void validaDados(Devolucao devolucao) throws Exception {
		if (devolucao.getDataRealDevolucao() == null)
			throw new NullPointerException("Campo Exemplar obrigatório!");
	}
}
