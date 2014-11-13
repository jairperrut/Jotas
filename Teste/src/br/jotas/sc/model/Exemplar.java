package br.jotas.sc.model;

public class Exemplar {

	private int idExemplar;
	private Filme filme;
	private int quantidade;
	private String status;
	private String codigoReserva;

	public Exemplar(int idExemplar, Filme filme, int quantidade, String status,
			String codigoReserva) {
		super();
		this.idExemplar = idExemplar;
		this.filme = filme;
		this.quantidade = quantidade;
		this.status = status;
		this.codigoReserva = codigoReserva;
	}

	public Exemplar() {
		super();
	}

	public int getIdExemplar() {
		return idExemplar;
	}

	public String getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
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

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
