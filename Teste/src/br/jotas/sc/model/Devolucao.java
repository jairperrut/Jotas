package br.jotas.sc.model;

import java.util.Date;

public class Devolucao {
	private int id;
	private Locacao locacao;
	private Date dataRealDevolucao;
	private Double valorTotal;
	private Double valorMulta;
	private int numeroFilmes;

	public Devolucao() {
		super();
	}

	public Devolucao(int id, Locacao locacao, Date dataRealDevolucao,
			Double valorTotal, Double valorMulta, int numeroFilmes) {
		super();
		this.id = id;
		this.locacao = locacao;
		this.dataRealDevolucao = dataRealDevolucao;
		this.valorTotal = valorTotal;
		this.valorMulta = valorMulta;
		this.numeroFilmes = numeroFilmes;
	}

	
	public int getNumeroFilmes() {
		return numeroFilmes;
	}

	public void setNumeroFilmes(int numeroFilmes) {
		this.numeroFilmes = numeroFilmes;
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}

}
