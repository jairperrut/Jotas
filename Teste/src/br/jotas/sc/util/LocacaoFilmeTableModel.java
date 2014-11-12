	package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Filme;

public class LocacaoFilmeTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_FILME = 0;
	private static final int COL_PRAZO = 1;
	
	private List<Filme> valores;

	public LocacaoFilmeTableModel(List<Filme> valores) {
		this.valores = new ArrayList<Filme>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 2;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_FILME) return "Título do Filme";
		if (column == COL_PRAZO) return "Prazo";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Filme filme = valores.get(row);
		if (column == COL_NOME_FILME) return filme.getTitulo();
		if (column == COL_PRAZO) return filme.getCategoria().getDiasLocacao();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Filme filme = valores.get(rowIndex);
		if (columnIndex == COL_NOME_FILME) filme.setTitulo(aValue.toString());
		if (columnIndex == COL_PRAZO) filme.getCategoria().setDiasLocacao(Integer.parseInt((aValue.toString() )));
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return true;
	}
	
	public Filme get(int row) {
		return valores.get(row);
	}
}
