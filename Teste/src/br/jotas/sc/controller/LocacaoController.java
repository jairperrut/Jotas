package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import br.jotas.sc.dao.LocacaoDAO;
import br.jotas.sc.exception.CampoObrigatorioException;
import br.jotas.sc.model.Locacao;
import br.jotas.sc.util.DataUtil;

public class LocacaoController {

	public void salvarLocacao(Locacao locacao) throws Exception {
		validaDados(locacao);
		LocacaoDAO dao = new LocacaoDAO();
		dao.salvarLocacao(locacao);
	}

	public void pagarLocacao(int id) {
		LocacaoDAO dao = new LocacaoDAO();
		dao.pagarLocacao(id);
	}

	public Locacao obterLocacao(int id) {
		LocacaoDAO dao = new LocacaoDAO();
		return dao.obterLocacao(id);
	}
	
	public int numeroDeLocacaoPorFilme(int idFilme) {
		LocacaoDAO dao = new LocacaoDAO();
		return dao.numeroDeLocacaoPorFilme(idFilme);
	}
	
	public Locacao obterLocacaoPorExemplar(int idExemplar) {
		LocacaoDAO dao = new LocacaoDAO();
		return dao.obterLocacaoPorExemplar(idExemplar);
	}

	public ArrayList<Locacao> listarLocacoes() {
		LocacaoDAO dao = new LocacaoDAO();
		return dao.listarLocacoes();
	}

	public ArrayList<Locacao> listarFilmesLocadosPorCliente(int id) {
		LocacaoDAO dao = new LocacaoDAO();
		return dao.listarLocacoesPorCliente(id);
	}
	
	public ArrayList<Locacao> listarTodasAsLocacoesPorCliente(int id) {
		LocacaoDAO dao = new LocacaoDAO();
		return dao.listarTodasLocacoesPorCliente(id);
	}	

	public ArrayList<Locacao> listarLocacoesEmAtraso(Date periodo) {
		LocacaoDAO dao = new LocacaoDAO();
		Date dataInicial = DataUtil.criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(periodo);
		Date dataFinal = DataUtil.criarNoUltimoSegundo(periodo);
		if(!DataUtil.mesmoMesAtual(periodo)){
			dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(periodo);			
		}
		return dao.listarLocacoesEmAtraso(dataInicial, dataFinal);
	}

	public void excluirLocacao(int id) throws SQLException {
		LocacaoDAO dao = new LocacaoDAO();
		dao.excluirLocacao(id);
	}

	public void validaDados(Locacao locacao) throws CampoObrigatorioException, Exception {
		if (locacao.getCliente() == null || locacao.getCliente().getId() == 0) {
			throw new CampoObrigatorioException("Cliente");
		}
		if (locacao.getExemplar() == null) {
			throw new CampoObrigatorioException("Exemplar");
		}
		if (locacao.getDataLocacao() == null) {
			throw new NullPointerException("Campo Exemplar obrigatório!");
		} else if (locacao.getDataLocacao().before(DataUtil.criarNoPrimeiroSegundo(new Date()))) {
			throw new Exception("Data de locação não pode ser anterior a data atual!");
		}
	}

}
