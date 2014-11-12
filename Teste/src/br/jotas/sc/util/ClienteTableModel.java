package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Cliente;

public class ClienteTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_NUMERO_FILMES = 1;

	private List<Cliente> valores;

	public ClienteTableModel(List<Cliente> valores) {
		this.valores = new ArrayList<Cliente>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 2;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME) return "Nome";
		if (column == COL_NUMERO_FILMES) return "Filmes Locados";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Cliente cliente = valores.get(row);
		if (column == COL_NOME) return cliente.getNome();
		if (column == COL_NUMERO_FILMES) return cliente.getFilmesLocados();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = valores.get(rowIndex);
		if (columnIndex == COL_NOME) cliente.setNome(aValue.toString());
		if (columnIndex == COL_NUMERO_FILMES) cliente.setFilmesLocados(Integer.parseInt(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return true;
	}
	
	public Cliente get(int row) {
		return valores.get(row);
	}
}
