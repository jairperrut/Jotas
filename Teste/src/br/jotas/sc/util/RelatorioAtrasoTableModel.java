package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Locacao;

public class RelatorioAtrasoTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_CLIENTE = 0;
	private static final int COL_TITULO_FILME = 1;
	private static final int COL_DIAS_DE_ATRASO = 2;	
	private ArrayList<Locacao> filmesLocados;

	public RelatorioAtrasoTableModel(Date periodo) {
		filmesLocados = new LocacaoController().listarLocacoesEmAtraso(periodo);
	}

	public int getRowCount() {		
		return filmesLocados.size();
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
		if (column == COL_NOME_CLIENTE) return filmesLocados.get(row).getCliente().getNome();
		if (column == COL_TITULO_FILME) return filmesLocados.get(row).getExemplar().getFilme().getTitulo();
		if (column == COL_DIAS_DE_ATRASO) return DataUtil.diferencaEmdias(new Date(), filmesLocados.get(row).getPrazo());
		return ""; 
	}

	/*public void setValueAt(Object aValue, int rowIndex, int columnIndex) {		
		if (columnIndex == COL_NOME_CLIENTE) ;
		if (columnIndex == COL_TITULO_FILME) ;
		if (columnIndex == COL_DIAS_DE_ATRASO) ;
	}*/

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return false;
	}
	
	public Locacao get(int row) {
		return filmesLocados.get(row);
	}
}
