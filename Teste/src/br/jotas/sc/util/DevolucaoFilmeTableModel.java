package br.jotas.sc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Devolucao;
import br.jotas.sc.model.Locacao;

public class DevolucaoFilmeTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_CLIENTE = 0;
	private static final int COL_NOME_FILME = 1;
	private static final int COL_PRAZO = 2;
	private static final int COL_VALOR = 3;
	private static final int COL_MULTA = 4;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private List<Locacao> valores;

	public DevolucaoFilmeTableModel(ArrayList<Locacao> valores) {
		this.valores = new ArrayList<Locacao>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 5;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_CLIENTE) return "Cliente";
		if (column == COL_NOME_FILME) return "Titulo do Filme";
		if (column == COL_PRAZO) return "Prazo";
		if (column == COL_VALOR) return "Valor";
		if (column == COL_MULTA) return "Multa";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Locacao locacao = valores.get(row);
		if (column == COL_NOME_CLIENTE) return locacao.getCliente().getNome();
		if (column == COL_NOME_FILME) return locacao.getExemplar().getFilme().getTitulo();
		if (column == COL_PRAZO) return sdf.format(locacao.getPrazo());
		if (column == COL_VALOR) return locacao.isPago() ? "PAGO" : locacao.getValor();		
		if (column == COL_MULTA) return	locacao.getPrazo().before(DataUtil.criarNoPrimeiroSegundo(new Date())) ? "SIM" : "NÃO";
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		try {
		Locacao locacao = valores.get(rowIndex);		
		if (columnIndex == COL_NOME_CLIENTE) locacao.getCliente().setNome(aValue.toString());
		if (columnIndex == COL_NOME_FILME) locacao.getExemplar().getFilme().setTitulo(aValue.toString());
		if (columnIndex == COL_PRAZO)	locacao.setPrazo(sdf.parse(aValue.toString()));
		if (columnIndex == COL_VALOR) locacao.setValor(Double.parseDouble(aValue.toString() ));		
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
