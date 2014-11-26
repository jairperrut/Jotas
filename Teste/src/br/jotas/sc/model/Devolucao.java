package br.jotas.sc.model;

import java.util.Date;

public class Devolucao {
	private int id;
	private Locacao locacao;
	private Date dataRealDevolucao;

	public Devolucao() {
		super();
	}

	public Devolucao(int id, Locacao locacao, Date dataRealDevolucao, Cliente cliente) {
		super();
		this.id = id;
		this.locacao = locacao;
		this.dataRealDevolucao = dataRealDevolucao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public Date getDataRealDevolucao() {
		return dataRealDevolucao;
	}

	public void setDataRealDevolucao(Date dataRealDevolucao) {
		this.dataRealDevolucao = dataRealDevolucao;
	}
}
