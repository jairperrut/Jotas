package br.jotas.sc.model;

import java.util.ArrayList;
import java.util.Date;

public class Locacao {

	private int id;
	private Cliente cliente;
	private ArrayList<Exemplar> exemplar = new ArrayList<Exemplar>();
	private Date dataLocacao;
	private Date prazo;
	private Double valor;
	private boolean pago;
		
	
	public Locacao(int id, Cliente cliente, ArrayList<Exemplar> exemplar,
			Date dataLocacao, Date prazo, Double valor, boolean pago) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.exemplar = exemplar;
		this.dataLocacao = dataLocacao;
		this.prazo = prazo;
		this.valor = valor;
		this.pago = pago;
	}

	public Locacao() {
		super();
	}

	public int getId() {
		return id;
	}

	

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Exemplar> getExemplar() {
		return exemplar;
	}

	public void setExemplar(ArrayList<Exemplar> exemplar) {
		this.exemplar = exemplar;
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

}
