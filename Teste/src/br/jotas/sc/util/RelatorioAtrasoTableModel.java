package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Locacao;

public class RelatorioAtrasoTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_CLIENTE = 0;
	private static final int COL_TITULO_FILME = 1;
	private static final int COL_DIAS_DE_ATRASO = 2;

	private List<Cliente> valores;

	public RelatorioAtrasoTableModel(List<Cliente> valores) {
		this.valores = new ArrayList<Cliente>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 3;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_CLIENTE) return "Nome Cliente";
		if (column == COL_TITULO_FILME) return "Titulo em Atraso";
		if (column == COL_DIAS_DE_ATRASO) return "Dias em Atraso";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Cliente cliente = valores.get(row);
		ArrayList<Locacao> filmesLocados = new LocacaoController().listarFilmesLocadosPorCliente(cliente.getId());
		//if (column == COL_NOME_CLIENTE) return cliente.getNome();
		//if (column == COL_TITULO_FILME) return cliente.get;
		//if (column == COL_TELEFONE) return cliente.getTelefone();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = valores.get(rowIndex);
		ArrayList<Locacao> filmesLocados = new LocacaoController().listarFilmesLocadosPorCliente(cliente.getId());
		//if (columnIndex == COL_NOME) cliente.setNome(aValue.toString());
		//if (columnIndex == COL_NUMERO_FILMES) filmesLocados.size();
		//if (columnIndex == COL_TELEFONE) cliente.setTelefone(aValue.toString());
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
