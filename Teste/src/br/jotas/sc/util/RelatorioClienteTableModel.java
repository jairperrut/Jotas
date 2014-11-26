package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Locacao;

public class RelatorioClienteTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_NUMERO_FILMES = 1;
	private static final int COL_TELEFONE = 2;

	private List<Cliente> valores;

	public RelatorioClienteTableModel(List<Cliente> valores) {
		this.valores = new ArrayList<Cliente>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 3;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME) return "Nome";
		if (column == COL_NUMERO_FILMES) return "Filmes Locados";
		if (column == COL_TELEFONE) return "Telefone";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Cliente cliente = valores.get(row);
		ArrayList<Locacao> filmesLocados = new LocacaoController().listarFilmesLocadosPorCliente(cliente.getId());
		if (column == COL_NOME) return cliente.getNome();
		if (column == COL_NUMERO_FILMES) return filmesLocados.size();
		if (column == COL_TELEFONE) return cliente.getTelefone();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = valores.get(rowIndex);
		ArrayList<Locacao> filmesLocados = new LocacaoController().listarFilmesLocadosPorCliente(cliente.getId());
		if (columnIndex == COL_NOME) cliente.setNome(aValue.toString());
		if (columnIndex == COL_NUMERO_FILMES) filmesLocados.size();
		if (columnIndex == COL_TELEFONE) cliente.setTelefone(aValue.toString());
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return false;
	}
	
	public Cliente get(int row) {
		return valores.get(row);
	}
}
