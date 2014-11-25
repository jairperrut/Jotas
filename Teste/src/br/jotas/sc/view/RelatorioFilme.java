package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RelatorioFilme extends JInternalFrame {
	private JTextField jtfFilme;

	public RelatorioFilme() {
		setClosable(true);
		setTitle("Relat\u00F3rio Loca\u00E7\u00F5es");
		setBounds(100, 100, 300, 220);

		JLabel jlPeriodo = new JLabel("Loca\u00E7\u00F5es Filme por per\u00EDodo:");

		JLabel jlFilme = new JLabel("Filme");

		jtfFilme = new JTextField();
		jtfFilme.setColumns(10);

		JButton jbBuscar = new JButton("Buscar");

		JLabel jlMes = new JLabel("M\u00EAs");

		JLabel jlAno = new JLabel("Ano");

		JComboBox jcbMes = new JComboBox();
		jcbMes.setModel(new DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" }));

		JComboBox jcbAno = new JComboBox();
		jcbAno.setModel(new DefaultComboBoxModel(new String[] { "2014", "2013", "2012", "2011", "2010" }));

		JButton jbGerar = new JButton("Gerar");

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.TRAILING)
										.addGroup(
												groupLayout.createSequentialGroup().addComponent(jbCancelar)
														.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE).addComponent(jbGerar))
										.addComponent(jlPeriodo, Alignment.LEADING)
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addGroup(
																groupLayout
																		.createParallelGroup(Alignment.TRAILING)
																		.addGroup(
																				Alignment.LEADING,
																				groupLayout
																						.createSequentialGroup()
																						.addComponent(jlFilme)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, 153,
																								GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addGroup(
																								groupLayout.createParallelGroup(Alignment.LEADING)
																										.addComponent(jlMes).addComponent(jlAno))
																						.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
																						.addGroup(
																								groupLayout
																										.createParallelGroup(Alignment.LEADING, false)
																										.addComponent(jcbAno, 0, GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(jcbMes, 0, 74, Short.MAX_VALUE))))
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(jbBuscar)))
						.addContainerGap(160, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jlPeriodo)
						.addGap(18)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlFilme)
										.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(jbBuscar))
						.addGap(18)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlMes)
										.addComponent(jcbMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlAno)
										.addComponent(jcbAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jbGerar).addComponent(jbCancelar))
						.addContainerGap(27, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}

}
