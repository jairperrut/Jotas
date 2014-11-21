package br.jotas.sc.model;

import java.util.Date;

public class Cliente {

	private int id;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private Date dataNascimento;
	private int filmesLocados;

	public Cliente(int id, String nome, String cpf, String endereco,
			String telefone, Date dataNascimento, int filmesLocados) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.filmesLocados = filmesLocados;
	}

	public int getFilmesLocados() {
		return filmesLocados;
	}

	public void setFilmesLocados(int filmesLocados) {
		this.filmesLocados = filmesLocados;
	}

	public Cliente() {

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String toString(){
		return this.nome;
	}

}