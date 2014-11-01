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
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

public class Locacao extends JInternalFrame {
	private JTextField jtfCliente;
	private JTextField jtfFilme;
	private JTable jtListaLocacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Locacao frame = new Locacao();
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
	public Locacao() {
		setTitle("Loca\u00E7\u00E3o");
		setBounds(100, 100, 550, 440);
		
		JLabel jlCliente = new JLabel("Cliente");
		
		jtfCliente = new JTextField();
		jtfCliente.setColumns(10);
		
		JLabel jlFilme = new JLabel("Filme");
		
		jtfFilme = new JTextField();
		jtfFilme.setColumns(10);
		
		JButton jbBuscar = new JButton("Buscar");
		
		JButton jbInserir = new JButton("Inserir");
		
		JDesktopPane desktopPane = new JDesktopPane();
		
		JScrollPane jspLocacao = new JScrollPane();
		
		JButton jbExcluir = new JButton("Excluir");
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JButton jbCancelar = new JButton("Cancelar");
		
		JButton jbOk = new JButton("Ok");
		
		JCheckBox jcbPago = new JCheckBox("Pago");
		
		JLabel jlTotal = new JLabel("Total R$ 0,00");
		
		JLabel jlLocacoes = new JLabel("Loca\u00E7\u00F5es");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jbExcluir))
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jlLocacoes))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(jlFilme)
									.addGap(21)
									.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(jbInserir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(jlCliente)
									.addGap(12)
									.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(jbBuscar)))))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbCancelar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbOk)
					.addGap(18)
					.addComponent(jcbPago)
					.addPreferredGap(ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
					.addComponent(jlTotal)
					.addGap(82))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 591, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jspLocacao, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(jlCliente))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(jbBuscar))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(jlFilme))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(jbInserir))
					.addGap(26)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jlLocacoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jspLocacao, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbExcluir)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(jbOk)
									.addComponent(jcbPago)
									.addComponent(jlTotal))
								.addComponent(jbCancelar)))))
		);
		
		jtListaLocacao = new JTable();
		jtListaLocacao.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"Filme", "Prazo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtListaLocacao.getColumnModel().getColumn(0).setResizable(false);
		jtListaLocacao.getColumnModel().getColumn(0).setPreferredWidth(275);
		jtListaLocacao.getColumnModel().getColumn(1).setResizable(false);
		jspLocacao.setViewportView(jtListaLocacao);
		getContentPane().setLayout(groupLayout);

	}
}
