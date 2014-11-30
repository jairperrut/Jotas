package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.jotas.sc.util.RelatorioAtrasoTableModel;

public class RelatorioAtrasoUI extends JInternalFrame {

	public JTable jtRelatorioAtraso;
	
	public RelatorioAtrasoUI(Date periodo) {
		setClosable(true);
		setTitle("Relat\u00F3rio de Atraso");
		setBounds(100, 100, 450, 300);
		
		JLabel jlRelatorioAtrasos = new JLabel("Relat\u00F3rio de exemplares em atraso no per\u00EDodo de:");
		
		JLabel jlMesAno = new JLabel("Mes/Ano");
		
		JScrollPane jspRelatorioAtrasos = new JScrollPane();
		
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jspRelatorioAtrasos, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlRelatorioAtrasos)
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
						.addComponent(jlRelatorioAtrasos)
						.addComponent(jlMesAno))
					.addGap(18)
					.addComponent(jspRelatorioAtrasos, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(jbSair)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		
		jtRelatorioAtraso= new JTable();
		jtRelatorioAtraso.setModel(new RelatorioAtrasoTableModel(periodo));
		jtRelatorioAtraso.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtRelatorioAtraso.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtRelatorioAtraso.getColumnModel().getColumn(2).setPreferredWidth(100);
		jspRelatorioAtrasos.setViewportView(jtRelatorioAtraso);
		getContentPane().setLayout(groupLayout);

	}

}
