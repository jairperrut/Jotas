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

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.util.DataUtil.Mes;

public class RelatorioClienteUI extends JInternalFrame {
	
	public RelatorioClienteUI() {
		setClosable(true);
		setTitle("Relat\u00F3rio Clientes");
		setBounds(100, 100, 227, 190);

		JLabel jlMes = new JLabel("M\u00EAs");

		JLabel jlAno = new JLabel("Ano");

		JComboBox jcbMes = new JComboBox();
		jcbMes.setModel(new DefaultComboBoxModel(Mes.values()));

		JComboBox jcbAno = new JComboBox();
		jcbAno.setModel(new DefaultComboBoxModel(new String[] { "2014", "2013", "2012", "2011", "2010" }));

		JLabel jlPeriodo = new JLabel("Loca\u00E7\u00F5es Cliente por per\u00EDodo:");

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
						.addComponent(jlPeriodo)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlMes)
							.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
							.addComponent(jcbMes, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(jlAno)
							.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
							.addComponent(jcbAno, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(jbCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
							.addComponent(jbGerar)))
					.addContainerGap())
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
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jcbAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlAno))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jbGerar)
						.addComponent(jbCancelar))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}

}
