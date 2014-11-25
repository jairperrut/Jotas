package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.util.VerFilmesTableModel;

public class VerFilmesUI extends JInternalFrame {
	private JTextField jtfCliente;
	private JTable jtVerFilmes;

	public VerFilmesUI(Cliente cliente) {
		setClosable(true);
		setTitle("Filmes Locados");
		setBounds(100, 100, 400, 300);

		JLabel jlCliente = new JLabel("Cliente");

		jtfCliente = new JTextField();
		jtfCliente.setText(cliente.getNome());
		jtfCliente.setEditable(false);
		jtfCliente.setColumns(10);

		JScrollPane jspVerFilmes = new JScrollPane();

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
										.createParallelGroup(Alignment.LEADING)
										.addComponent(jspVerFilmes, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
										.addGroup(
												groupLayout.createSequentialGroup().addComponent(jlCliente).addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(jtfCliente, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
										.addComponent(jbCancelar, Alignment.TRAILING)).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlCliente)
										.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18)
						.addComponent(jspVerFilmes, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(jbCancelar).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		if (jtVerFilmes == null) {
			jtVerFilmes = new JTable();
			jtVerFilmes.setModel(new VerFilmesTableModel(new LocacaoController().listarFilmesLocadosPorCliente(cliente.getId())));
			jtVerFilmes.getColumnModel().getColumn(0).setPreferredWidth(50);
			jtVerFilmes.getColumnModel().getColumn(1).setPreferredWidth(200);
			jtVerFilmes.getColumnModel().getColumn(2).setPreferredWidth(100);
		}
		jspVerFilmes.setViewportView(jtVerFilmes);
		getContentPane().setLayout(groupLayout);

	}
}
