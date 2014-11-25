package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Exemplar;
import br.jotas.sc.model.StatusExemplarEnum;

public class ExemplarTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_FILME = 0;
	private static final int COL_CODIGO = 1;
	private static final int COL_STATUS = 2;

	private List<Exemplar> valores;

	public ExemplarTableModel(List<Exemplar> valores) {
		this.valores = new ArrayList<Exemplar>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 3;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_FILME) return "Título do Filme";
		if (column == COL_CODIGO) return "Código";
		if (column == COL_STATUS) return "Status";
		
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Exemplar exemplar = valores.get(row);
		if (column == COL_NOME_FILME) return exemplar.getFilme().getTitulo();
		if (column == COL_CODIGO) return exemplar.getIdExemplar();
		if (column == COL_STATUS) return exemplar.getStatus();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Exemplar exemplar = valores.get(rowIndex);
		if (columnIndex == COL_NOME_FILME) exemplar.getFilme().setTitulo(aValue.toString());
		if (columnIndex == COL_CODIGO) exemplar.setIdExemplar(Integer.parseInt(aValue.toString() ));
		if (columnIndex == COL_STATUS) exemplar.setStatus(StatusExemplarEnum.valueOf(aValue.toString() ));		
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
