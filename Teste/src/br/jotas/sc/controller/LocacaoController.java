package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import br.jotas.sc.dao.LocacaoDAO;
import br.jotas.sc.model.Locacao;

public class LocacaoController {

	public void salvarLocacao(Locacao locacao) {
		try {
			validaDados(locacao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		LocacaoDAO dao = new LocacaoDAO();
		if(locacao.getId()!=0){
			dao.editarLocacao(locacao);
		}else{
			dao.salvarLocacao(locacao);			
		}
	}

	public Locacao obterLocacao(int id){
		LocacaoDAO dao = new LocacaoDAO();		
		return dao.obterLocacao(id);		
	}
	
	public ArrayList<Locacao> listarLocacoes() {
		LocacaoDAO dao = new LocacaoDAO();		
		return dao.listarLocacoes();
	}	

	public void excluirLocacao(int id) throws SQLException {
		LocacaoDAO dao = new LocacaoDAO();
		dao.excluirLocacao(id);
	}

	public void validaDados(Locacao locacao) throws Exception {
		if (locacao.getCliente() == null) {
			throw new NullPointerException("Campo Cliente é obrigatório!");
		}
		if (locacao.getExemplar() == null) {
			throw new NullPointerException("Campo Exemplar obrigatório!");
		}
		if (locacao.getDataLocacao() == null ) {
			throw new NullPointerException("Campo Exemplar obrigatório!");
		}else if(locacao.getDataLocacao().before(new Date())){
			throw new Exception("Data de locação não pode ser anterior a data atual!");
		}
	}
	
}
