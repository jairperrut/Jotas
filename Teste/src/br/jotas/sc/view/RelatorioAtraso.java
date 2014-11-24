package br.jotas.sc.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import br.jotas.sc.util.DataUtil.Mes;

public class RelatorioAtraso extends JInternalFrame {


	public RelatorioAtraso() {
		setClosable(true);
		setTitle("Relat\u00F3rio Atraso");
		setBounds(100, 100, 220, 200);

		JLabel jlPeriodo = new JLabel("Filmes Atrasados por per\u00EDodo:");

		JLabel jlMes = new JLabel("M\u00EAs");

		JLabel jlAno = new JLabel("Ano");

		JComboBox jcbMes = new JComboBox();
		jcbMes.setModel(new DefaultComboBoxModel(Mes.values()));

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
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(
														groupLayout
																.createSequentialGroup()
																.addComponent(jlAno)
																.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
																.addComponent(jcbAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														groupLayout
																.createSequentialGroup()
																.addComponent(jlMes)
																.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
																.addComponent(jcbMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														Alignment.TRAILING,
														groupLayout.createSequentialGroup().addComponent(jbCancelar)
																.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE).addComponent(jbGerar))
												.addComponent(jlPeriodo)).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jlPeriodo)
						.addGap(18)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlMes)
										.addComponent(jcbMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlAno)
										.addComponent(jcbAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jbGerar).addComponent(jbCancelar))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}
}
