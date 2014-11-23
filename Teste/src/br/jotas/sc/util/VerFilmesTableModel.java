package br.jotas.sc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Exemplar;
import br.jotas.sc.model.Locacao;

public class VerFilmesTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_CODIGO = 0;
	private static final int COL_NOME_FILME = 1;
	private static final int COL_PRAZO = 2;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private List<Locacao> valores;

	public VerFilmesTableModel(List<Locacao> valores) {
		this.valores = new ArrayList<Locacao>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 3;
	}

	public String getColumnName(int column) {		
		if (column == COL_CODIGO) return "Código";
		if (column == COL_NOME_FILME) return "Título do Filme";
		if (column == COL_PRAZO) return "Prazo de Devolução";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Locacao locacao = valores.get(row);
		if (column == COL_CODIGO) return locacao.getExemplar().getIdExemplar();
		if (column == COL_NOME_FILME) return locacao.getExemplar().getFilme().getTitulo();
		if (column == COL_PRAZO) return sdf.format(locacao.getPrazo());
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Locacao locacao = valores.get(rowIndex);
		if (columnIndex == COL_CODIGO) locacao.getExemplar().setIdExemplar(Integer.parseInt(aValue.toString() ));
		if (columnIndex == COL_NOME_FILME) locacao.getExemplar().getFilme().setTitulo(aValue.toString());
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return false;
	}
	
	public Locacao get(int row) {
		return valores.get(row);
	}
}
