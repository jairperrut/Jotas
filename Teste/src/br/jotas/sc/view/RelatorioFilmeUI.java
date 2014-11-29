package br.jotas.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.controller.FilmeController;
import br.jotas.sc.util.RelatorioClienteTableModel;
import br.jotas.sc.util.RelatorioFilmeTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class RelatorioFilmeUI extends JInternalFrame {

	public JTable jtRelatorioFilme;
	
	public RelatorioFilmeUI(Date periodo) {
		setClosable(true);
		setTitle("Relat\u00F3rio de Loca\u00E7\u00E3o");
		setBounds(100, 100, 450, 300);
		
		JLabel jlLocacoesFilme = new JLabel("Loca\u00E7\u00F5es de filmes no per\u00EDodo de :");
		
		JLabel jlMesAno = new JLabel("M\u00EAs / Ano");
		
		JScrollPane jspRelatorioLocacoes = new JScrollPane();
		
		JButton jbSair = new JButton("Sair");
		jbSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jspRelatorioLocacoes, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlLocacoesFilme)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jlMesAno))
						.addComponent(jbSair))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlLocacoesFilme)
						.addComponent(jlMesAno))
					.addGap(18)
					.addComponent(jspRelatorioLocacoes, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbSair)
					.addContainerGap())
		);
		jtRelatorioFilme = new JTable();
		jtRelatorioFilme.setModel(new RelatorioFilmeTableModel(new FilmeController().listarFilme(), periodo));
		jtRelatorioFilme.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtRelatorioFilme.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtRelatorioFilme.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtRelatorioFilme.getColumnModel().getColumn(3).setPreferredWidth(100);
		jspRelatorioLocacoes.setViewportView(jtRelatorioFilme);
		getContentPane().setLayout(groupLayout);

	}
}
