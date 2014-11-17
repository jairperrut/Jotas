package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.jotas.sc.dao.CategoriaDAO;
import br.jotas.sc.model.Categoria;

public class CategoriaController {

	public void salvarCategoria(Categoria categoria) throws NullPointerException, Exception {
		validaDados(categoria);
		CategoriaDAO dao = new CategoriaDAO();
		if(categoria.getId()!=0){
			dao.editarCategoria(categoria);
		}else{
			dao.salvarCategoria(categoria);			
		}
	}
	
	public Categoria obterCategoria(int id){
		CategoriaDAO dao = new CategoriaDAO();
		return dao.obterCategoria(id);
	}

	public ArrayList<Categoria> listarCategoria() {
		CategoriaDAO dao = new CategoriaDAO();		
		return dao.listarCategorias();
	}	

	public void excluirCategoria(int id) throws SQLException {
		CategoriaDAO dao = new CategoriaDAO();
		dao.excluirCategoria(id);
	}

	public void validaDados(Categoria categoria) throws NullPointerException, Exception {
		if (categoria.getDiasLocacao() == 0) {
			throw new NullPointerException("Campo Prazo deve ser maior que 0!");
		}
	}
	
}
