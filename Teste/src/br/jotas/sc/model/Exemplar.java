package br.jotas.sc.model;

public class Exemplar {

	private int idExemplar;
	private Filme filme;
	private StatusExemplarEnum status;

	public Exemplar(int idExemplar, Filme filme, StatusExemplarEnum status) {
		super();
		this.idExemplar = idExemplar;
		this.filme = filme;
		this.status = status;
	}

	public Exemplar() {
		super();
	}

	public int getIdExemplar() {
		return idExemplar;
	}	

	public void setIdExemplar(int idExemplar) {
		this.idExemplar = idExemplar;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public StatusExemplarEnum getStatus() {
		return status;
	}

	public void setStatus(StatusExemplarEnum status) {
		this.status = status;
	}

}
