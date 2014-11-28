package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import br.jotas.sc.dao.DevolucaoDAO;
import br.jotas.sc.model.Devolucao;
import br.jotas.sc.model.Locacao;
import br.jotas.sc.util.DataUtil;

public class DevolucaoController {

	public void salvarDevolucao(Devolucao devolucao) throws Exception {
		validaDados(devolucao);
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

	public double valorPorCliente(int id, Date dataPeriodo){
		DevolucaoDAO dao = new DevolucaoDAO();
		Date dataInicio = DataUtil.criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(dataPeriodo);
		Date dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(dataPeriodo);
		return dao.obterValorPorCliente(id, dataInicio, dataFinal);
	}
	
	public double obterValorPorFilme(int id, Date dataPeriodo){
		DevolucaoDAO dao = new DevolucaoDAO();
		Date dataInicio = DataUtil.criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(dataPeriodo);
		Date dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(dataPeriodo);
		return dao.obterValorPorFilme(id, dataInicio, dataFinal);
	}
	
	public void validaDados(Devolucao devolucao) throws Exception {
		if (devolucao.getDataRealDevolucao() == null)
			throw new NullPointerException("Campo Exemplar obrigatório!");
	}
}
