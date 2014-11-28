package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.controller.DevolucaoController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.dao.LocacaoDAO;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Exemplar;
import br.jotas.sc.model.Filme;
import br.jotas.sc.model.Locacao;

public class RelatorioFilmeTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_FILME = 0;
	private static final int COL_NUMERO_EXEMPLARES = 1;
	private static final int COL_QUANTIDADE_LOCACAO = 2;
	private static final int COL_LUCRO = 3;
	private Date periodo;
	
	private List<Exemplar> valores;

	public RelatorioFilmeTableModel(List<Exemplar> valores, Date periodo) {
		this.valores = new ArrayList<Exemplar>(valores);
		this.periodo = periodo;
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 4;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_FILME) return "Título Filme";
		if (column == COL_NUMERO_EXEMPLARES) return "Nº de Exemplares";
		if (column == COL_QUANTIDADE_LOCACAO) return "Nº de locações";
		if (column == COL_LUCRO) return "Rentabilidade";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Filme filme = new Filme();
		if (column == COL_NOME_FILME) return filme.getTitulo();
		if (column == COL_NUMERO_EXEMPLARES) return new ExemplarController().obterExemplarPorTitulo(filme.getTitulo()).size();
		if (column == COL_QUANTIDADE_LOCACAO) return new LocacaoController().numeroDeLocacaoPorFilme(filme.getId());
		if (column == COL_LUCRO) return new DevolucaoController().obterValorPorFilme(filme.getId(), periodo);
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Filme filme = new Filme();
		
		//if (columnIndex == COL_NOME_FILME) exemplar.getFilme().setTitulo(aValue.toString());
		//if (columnIndex == COL_NUMERO_EXEMPLARES) filmesLocados.size();
		//if (columnIndex == COL_QUANTIDADE_LOCACAO) cliente.setTelefone(aValue.toString());
		//if (columnIndex == COL_LUCRO) cliente.setTelefone(aValue.toString());
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return false;
	}
	
	public Exemplar get(int row) {
		return valores.get(row);
	}
}
