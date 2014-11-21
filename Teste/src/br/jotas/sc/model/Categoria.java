package br.jotas.sc.model;

public class Categoria {

	private int id;
	private Double valor;
	private int diasLocacao;
	private String descricao;

	public Categoria(int id, Double valor, int diasLocacao, String descricao) {
		super();
		this.id = id;
		this.valor = valor;
		this.diasLocacao = diasLocacao;
		this.descricao = descricao;
	}

	public Categoria() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getDiasLocacao() {
		return diasLocacao;
	}

	public void setDiasLocacao(int diasLocacao) {
		this.diasLocacao = diasLocacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString(){
		return this.descricao;
	}
}
