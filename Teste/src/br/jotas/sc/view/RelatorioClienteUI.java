package br.jotas.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.util.RelatorioClienteTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class RelatorioClienteUI extends JInternalFrame {

	public JTable jtRelatorioCliente;
	
	public RelatorioClienteUI(Date periodo) {
		setClosable(true);
		setTitle("Relat\u00F3rio de Cliente");
		setBounds(100, 100, 450, 300);
		
		JLabel jlLocacoesCliente = new JLabel("Loca\u00E7\u00F5es de clientes no per\u00EDodo de :");
		
		JLabel jlMesAno = new JLabel("M\u00EAs / Ano");
		
		JScrollPane jspRelatorioClientes = new JScrollPane();
		
		JButton jbSair = new JButton("Sair");
		jbSair.addActionListener(new ActionListener() {
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
						.addComponent(jspRelatorioClientes, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlLocacoesCliente)
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
						.addComponent(jlLocacoesCliente)
						.addComponent(jlMesAno))
					.addGap(18)
					.addComponent(jspRelatorioClientes, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(jbSair)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

		jtRelatorioCliente = new JTable();
		jtRelatorioCliente.setModel(new RelatorioClienteTableModel(new ClienteController().listarClientes(), periodo));
		jtRelatorioCliente.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtRelatorioCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtRelatorioCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
		jspRelatorioClientes.setViewportView(jtRelatorioCliente);
		getContentPane().setLayout(groupLayout);		
	}

}
