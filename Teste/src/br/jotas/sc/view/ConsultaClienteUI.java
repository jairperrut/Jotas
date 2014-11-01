package br.jotas.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaClienteUI extends JInternalFrame {
	private JTextField jtfNome;
	private JTable jtListaCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClienteUI frame = new ConsultaClienteUI();
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
	public ConsultaClienteUI() {
		setTitle("Consulta Cliente");
		setBounds(100, 100, 450, 370);
		
		JLabel jlNome = new JLabel("Nome");
		
		jtfNome = new JTextField();
		jtfNome.setColumns(10);
		
		JButton jbProcurar = new JButton("Procurar");
		
		JScrollPane jspConsultaCliente = new JScrollPane();
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton jbEditar = new JButton("Editar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jspConsultaCliente, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfNome, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
						.addComponent(jbProcurar, Alignment.TRAILING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(jbEditar)
							.addGap(18)
							.addComponent(jbCancelar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlNome)
						.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbProcurar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jspConsultaCliente, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancelar)
						.addComponent(jbEditar))
					.addContainerGap())
		);
		
		jtListaCliente = new JTable();
		jtListaCliente.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Cliente", "Filmes Locados"
			}
		));
		jtListaCliente.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtListaCliente.getColumnModel().getColumn(1).setPreferredWidth(200);
		jspConsultaCliente.setViewportView(jtListaCliente);
		getContentPane().setLayout(groupLayout);

	}

}
