package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.jotas.sc.controller.FilmeController;
import br.jotas.sc.model.Filme;
import br.jotas.sc.util.DataUtil.Mes;

public class GerarRelatorioFilmeUI extends JInternalFrame {
	
	public GerarRelatorioFilmeUI() {
		setClosable(true);
		setTitle("Relat\u00F3rio Loca\u00E7\u00F5es");
		setBounds(100, 100, 225, 190);

		JLabel jlPeriodo = new JLabel("Loca\u00E7\u00F5es Filme por per\u00EDodo:");

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
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jlMes)
									.addGap(48)
									.addComponent(jcbMes, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(jbCancelar)
										.addComponent(jlAno))
									.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(jcbAno, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jbGerar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addGap(20))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlPeriodo)
							.addContainerGap(76, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jlPeriodo)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlMes)
						.addComponent(jcbMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jcbAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlAno))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbGerar)
						.addComponent(jbCancelar))
					.addGap(55))
		);
		getContentPane().setLayout(groupLayout);

	}
}
