package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.controller.DevolucaoController;
import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Locacao;

public class RelatorioClienteTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_CLIENTE = 0;
	private static final int COL_NUMERO_FILMES = 1;
	private static final int COL_LUCRO = 2;
	private Date dataPeriodo;

	private List<Cliente> valores;

	public RelatorioClienteTableModel(List<Cliente> valores, Date dataPeriodo) {
		this.valores = new ArrayList<Cliente>(valores);
		this.dataPeriodo = dataPeriodo;
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 3;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_CLIENTE) return "Nome Cliente";
		if (column == COL_NUMERO_FILMES) return "Filmes Locados";
		if (column == COL_LUCRO) return "Rentabilidade";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Cliente cliente = valores.get(row);
		DevolucaoController dev = new DevolucaoController();
		ArrayList<Locacao> filmesLocados = new LocacaoController().listarTodasAsLocacoesPorCliente(cliente.getId());
		if (column == COL_NOME_CLIENTE) return cliente.getNome();
		if (column == COL_NUMERO_FILMES) return filmesLocados.size();
		if (column == COL_LUCRO) return dev.valorPorCliente(cliente.getId(), dataPeriodo);
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = valores.get(rowIndex);
		ArrayList<Locacao> filmesLocados = new LocacaoController().listarTodasAsLocacoesPorCliente(cliente.getId());
		if (columnIndex == COL_NOME_CLIENTE) cliente.setNome(aValue.toString());
		if (columnIndex == COL_NUMERO_FILMES) filmesLocados.size();
		if (columnIndex == COL_LUCRO) ;
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
