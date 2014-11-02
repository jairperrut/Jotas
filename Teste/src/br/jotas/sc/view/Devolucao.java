package br.jotas.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Devolucao extends JInternalFrame {
	private JTextField jtfFilme;
	private JTable jtListaDevolucao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Devolucao frame = new Devolucao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Devolucao() {
		setClosable(true);
		setTitle("Devolu\u00E7\u00E3o");
		setBounds(100, 100, 550, 400);
		
		JLabel jlFilme = new JLabel("Filme");
		
		jtfFilme = new JTextField();
		jtfFilme.setColumns(10);
		
		JButton jbInserir = new JButton("Inserir");
		jbInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JSeparator separator = new JSeparator();
		
		JLabel jlDevolucoes = new JLabel("Devolu\u00E7\u00F5es");
		
		JScrollPane jspDevolucoes = new JScrollPane();
		
		JButton jbExcluir = new JButton("Excluir");
		
		JSeparator separator_1 = new JSeparator();
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton jbOk = new JButton("Ok");
		jbOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel jlTotal = new JLabel("Total R$ 0,00");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlFilme)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(jbInserir))
						.addComponent(jlDevolucoes)
						.addComponent(jspDevolucoes, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbExcluir)
					.addContainerGap(461, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbCancelar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbOk)
					.addPreferredGap(ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
					.addComponent(jlTotal)
					.addContainerGap())
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlFilme)
						.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbInserir))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jlDevolucoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jspDevolucoes, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbExcluir)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancelar)
						.addComponent(jbOk)
						.addComponent(jlTotal))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		jtListaDevolucao = new JTable();
		jtListaDevolucao.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Cliente", "Filme", "Prazo", "Valor"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtListaDevolucao.getColumnModel().getColumn(0).setResizable(false);
		jtListaDevolucao.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtListaDevolucao.getColumnModel().getColumn(1).setResizable(false);
		jtListaDevolucao.getColumnModel().getColumn(1).setPreferredWidth(200);
		jtListaDevolucao.getColumnModel().getColumn(2).setResizable(false);
		jtListaDevolucao.getColumnModel().getColumn(2).setPreferredWidth(50);
		jtListaDevolucao.getColumnModel().getColumn(3).setResizable(false);
		jspDevolucoes.setViewportView(jtListaDevolucao);
		getContentPane().setLayout(groupLayout);

	}

}
