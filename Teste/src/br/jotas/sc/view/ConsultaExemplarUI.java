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

public class ConsultaExemplarUI extends JInternalFrame {
	private JTextField jtfTitulo;
	private JTable jtListaExemplar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaExemplarUI frame = new ConsultaExemplarUI();
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
	public ConsultaExemplarUI() {
		setTitle("Consulta Exemplar");
		setBounds(100, 100, 450, 375);
		
		JLabel jlTitulo = new JLabel("T\u00EDtulo");
		
		jtfTitulo = new JTextField();
		jtfTitulo.setColumns(10);
		
		JButton jbProcurar = new JButton("Procurar");
		
		JScrollPane jspConsultaExemplar = new JScrollPane();
		
		JButton jbCancelar = new JButton("Cancelar");
		
		JButton jbEditar = new JButton("Editar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jspConsultaExemplar, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlTitulo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfTitulo, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
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
						.addComponent(jlTitulo)
						.addComponent(jtfTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbProcurar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jspConsultaExemplar, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancelar)
						.addComponent(jbEditar))
					.addContainerGap())
		);
		
		jtListaExemplar = new JTable();
		jtListaExemplar.setModel(new DefaultTableModel(
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
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"T\u00EDtulo do Filme", "C\u00F3digo", "Status", "Cliente"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtListaExemplar.getColumnModel().getColumn(0).setResizable(false);
		jtListaExemplar.getColumnModel().getColumn(0).setPreferredWidth(150);
		jtListaExemplar.getColumnModel().getColumn(1).setResizable(false);
		jtListaExemplar.getColumnModel().getColumn(1).setPreferredWidth(50);
		jtListaExemplar.getColumnModel().getColumn(2).setResizable(false);
		jtListaExemplar.getColumnModel().getColumn(2).setPreferredWidth(50);
		jtListaExemplar.getColumnModel().getColumn(3).setResizable(false);
		jtListaExemplar.getColumnModel().getColumn(3).setPreferredWidth(100);
		jspConsultaExemplar.setViewportView(jtListaExemplar);
		getContentPane().setLayout(groupLayout);

	}
}
