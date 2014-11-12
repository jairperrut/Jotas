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
	private static final int COL_CPF = 1;
	private static final int COL_TELEFONE = 2;
	private static final int COL_ENDERECO = 3;
	private static final int COL_DATANASCIMENTO = 4;

	private List<Cliente> valores;

	public ClienteTableModel(List<Cliente> valores) {
		this.valores = new ArrayList<Cliente>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 5;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME) return "Nome";
		if (column == COL_CPF) return "CPF";
		if (column == COL_TELEFONE) return "Telefone";
		if (column == COL_ENDERECO) return "Endereço";
		if (column == COL_DATANASCIMENTO) return "Data de Nascimento";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Cliente cliente = valores.get(row);
		if (column == COL_NOME) return cliente.getNome();
		if (column == COL_CPF) return cliente.getCpf();
		if (column == COL_TELEFONE) return cliente.getTelefone();
		if (column == COL_ENDERECO) return cliente.getEndereco();
		if (column == COL_DATANASCIMENTO) return cliente.getDataNascimento();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = valores.get(rowIndex);
		if (columnIndex == COL_NOME) cliente.setNome(aValue.toString());
		if (columnIndex == COL_CPF) cliente.setCpf(aValue.toString());
		if (columnIndex == COL_TELEFONE) cliente.setTelefone(aValue.toString());
		if (columnIndex == COL_ENDERECO) cliente.setEndereco(aValue.toString());
		if (columnIndex == COL_DATANASCIMENTO) cliente.setDataNascimento((Date)aValue);		
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
