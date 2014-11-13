package br.jotas.sc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Locacao;

public class DevolucaoFilmeTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_CLIENTE = 0;
	private static final int COL_NOME_FILME = 0;
	private static final int COL_PRAZO = 1;
	private static final int COL_VALOR = 0;
	
	private List<Locacao> valores;

	public DevolucaoFilmeTableModel(List<Locacao> valores) {
		this.valores = new ArrayList<Locacao>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 4;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_CLIENTE) return "Cliente";
		if (column == COL_NOME_FILME) return "Titulo do Filme";
		if (column == COL_PRAZO) return "Prazo";
		if (column == COL_VALOR) return "Valor";
		
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Locacao locacao = valores.get(row);
		if (column == COL_NOME_CLIENTE) return locacao.getCliente().getNome();
		if (column == COL_NOME_FILME) return null;
		if (column == COL_PRAZO) return locacao.getPrazo();
		if (column == COL_VALOR) return locacao.getValor();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Locacao locacao = valores.get(rowIndex);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (columnIndex == COL_NOME_CLIENTE) locacao.getCliente().setNome(aValue.toString());
		//if (columnIndex == COL_NOME_FILME) null;
		if (columnIndex == COL_PRAZO)
			try {
				locacao.setPrazo(sdf.parse(aValue.toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		if (columnIndex == COL_VALOR) locacao.setValor(Double.parseDouble(aValue.toString() ));		
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
