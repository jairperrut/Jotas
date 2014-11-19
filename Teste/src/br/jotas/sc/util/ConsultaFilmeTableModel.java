package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Filme;

public class ConsultaFilmeTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_FILME = 0;
	private static final int COL_CATEGORIA = 1;
	
	private List<Filme> valores;

	public ConsultaFilmeTableModel(List<Filme> valores) {
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
		if (column == COL_CATEGORIA) return "Categoria";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Filme filme = valores.get(row);
		if (column == COL_NOME_FILME) return filme.getTitulo();
		if (column == COL_CATEGORIA) return filme.getCategoria().getDescricao();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Filme filme = valores.get(rowIndex);
		if (columnIndex == COL_NOME_FILME) filme.setTitulo(aValue.toString());
		if (columnIndex == COL_CATEGORIA) filme.getCategoria().setDescricao(aValue.toString() );
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
