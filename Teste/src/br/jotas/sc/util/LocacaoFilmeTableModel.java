	package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Filme;
import br.jotas.sc.model.Locacao;

public class LocacaoFilmeTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_FILME = 0;
	private static final int COL_PRAZO = 1;
	
	private List<Locacao> valores;

	public LocacaoFilmeTableModel(List<Locacao> valores) {
		this.valores = new ArrayList<Locacao>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 2;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_FILME) return "T�tulo do Filme";
		if (column == COL_PRAZO) return "Prazo";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Locacao locacao = valores.get(row);
		if (column == COL_NOME_FILME) return locacao.getExemplar().getFilme().getTitulo();
		if (column == COL_PRAZO) return locacao.getExemplar().getFilme().getCategoria().getDiasLocacao();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Locacao locacao = valores.get(rowIndex);
		if (columnIndex == COL_NOME_FILME) locacao.getExemplar().getFilme().setTitulo(aValue.toString());
		if (columnIndex == COL_PRAZO) locacao.getExemplar().getFilme().getCategoria().setDiasLocacao(Integer.parseInt((aValue.toString() )));
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return true;
	}
	
	public Locacao get(int row) {
		return valores.get(row);
	}
}
