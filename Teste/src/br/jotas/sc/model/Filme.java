package br.jotas.sc.model;

public class Filme {

	private int id;
	private String titulo;
	private int ano;
	private String genero;
	private String exemplar;
	private String tipo;
	private Categoria categoria;

	
	
	public Filme(int id, String titulo, int ano, String genero,
			String exemplar, String tipo, Categoria categoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.ano = ano;
		this.genero = genero;
		this.exemplar = exemplar;
		this.tipo = tipo;
		this.categoria = categoria;
	}

	public Filme() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getExemplar() {
		return exemplar;
	}

	public void setExemplar(String exemplar) {
		this.exemplar = exemplar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
