package br.jotas.sc.view;

import java.awt.EventQueue;
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

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.util.ClienteTableModel;
import javax.swing.JSeparator;

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
		setClosable(true);
		setTitle("Consulta Cliente");
		setBounds(100, 100, 445, 370);

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
		jbEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClienteUI cadCliEdit = new CadastroClienteUI();
				cadCliEdit.setFocusable(true);
				cadCliEdit.moveToFront();
				cadCliEdit.requestFocus();
				PrincipalUI.obterInstancia().getContentPane().add(cadCliEdit, 0);
				cadCliEdit.setVisible(true);
			}
		});
		
		JSeparator separator = new JSeparator();
		
		JButton jbVerFilmes = new JButton("Ver Filmes");
		jbVerFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VerFilmes verfilmes = new VerFilmes();
				verfilmes.setFocusable(true);
				verfilmes.moveToFront();
				verfilmes.requestFocus();
				PrincipalUI.obterInstancia().getContentPane().add(verfilmes, 0);
				verfilmes.setVisible(true);
				
				//Cliente cliente = new ClienteTableModel().get(jtListaCliente.getSelectedRow());
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(jbEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jbCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfNome, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)))
					.addContainerGap())
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbVerFilmes))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(359, Short.MAX_VALUE)
					.addComponent(jbProcurar)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jspConsultaCliente, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlNome)
						.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbProcurar)
					.addGap(11)
					.addComponent(jspConsultaCliente, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbVerFilmes)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbEditar)
						.addComponent(jbCancelar))
					.addContainerGap())
		);

		if (jtListaCliente == null) {
			jtListaCliente = new JTable();
			jtListaCliente.setModel(new ClienteTableModel(new ClienteController().listarClientes()));
			jtListaCliente.getColumnModel().getColumn(0).setPreferredWidth(100);
			jtListaCliente.getColumnModel().getColumn(1).setPreferredWidth(200);
		}
		jspConsultaCliente.setViewportView(jtListaCliente);
		getContentPane().setLayout(groupLayout);

	}
}
