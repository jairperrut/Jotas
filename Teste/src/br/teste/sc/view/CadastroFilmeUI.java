package br.teste.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class CadastroFilmeUI extends JInternalFrame {
	private JTextField jtfTitulo;
	private JTextField jtfGenero;
	private JTextField jtfAno;
	private JTextField jtfCodigoReserva;
	private JTable jtListaExemplares;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFilmeUI frame = new CadastroFilmeUI();
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
	public CadastroFilmeUI() {
		setTitle("Filme");
		setBounds(100, 100, 500, 400);
		
		JLabel jlTitulo = new JLabel("T\u00EDtulo");
		
		jtfTitulo = new JTextField();
		jtfTitulo.setColumns(10);
		
		JLabel jlGenero = new JLabel("G\u00EAnero");
		
		jtfGenero = new JTextField();
		jtfGenero.setColumns(10);
		
		JLabel jlAno = new JLabel("Ano");
		
		jtfAno = new JTextField();
		jtfAno.setColumns(10);
		
		JLabel jlTipo = new JLabel("Tipo");
		
		JComboBox jcbTipo = new JComboBox();
		jcbTipo.setModel(new DefaultComboBoxModel(new String[] {"Acervo", "Lan\u00E7amento"}));
		jcbTipo.setSelectedIndex(0);
		jcbTipo.setToolTipText("");
		
		JLabel jlCodigoReserva = new JLabel("C\u00F3digo Reserva");
		
		jtfCodigoReserva = new JTextField();
		jtfCodigoReserva.setEditable(false);
		jtfCodigoReserva.setColumns(10);
		
		JLabel jlQuantidade = new JLabel("Quantidade");
		
		JSpinner spinnerQuantidade = new JSpinner();
		spinnerQuantidade.setModel(new SpinnerNumberModel(0, null, 50, 1));
		
		JButton btnGerarExemplares = new JButton("Gerar Exemplares");
		
		JScrollPane jspListaExemplares = new JScrollPane();
		
		JButton jbCancelar = new JButton("Cancelar");
		
		JButton jbSalvar = new JButton("Salvar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jspListaExemplares, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(jlAno)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlTitulo)
							.addGap(18)
							.addComponent(jtfTitulo, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jlGenero)
								.addComponent(jlTipo))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jtfGenero, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jcbTipo, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jtfAno, Alignment.LEADING))
									.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
									.addComponent(jlCodigoReserva)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(jtfCodigoReserva, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlQuantidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerQuantidade, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGerarExemplares))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(jbSalvar)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlGenero)
						.addComponent(jtfGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlAno)
						.addComponent(jtfAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jcbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlTipo)
						.addComponent(jtfCodigoReserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlCodigoReserva))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlQuantidade)
						.addComponent(spinnerQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGerarExemplares))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jspListaExemplares, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancelar)
						.addComponent(jbSalvar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		jtListaExemplares = new JTable();
		jtListaExemplares.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"T\u00EDtulo do Filme", "C\u00F3digo", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtListaExemplares.getColumnModel().getColumn(0).setResizable(false);
		jtListaExemplares.getColumnModel().getColumn(0).setPreferredWidth(225);
		jtListaExemplares.getColumnModel().getColumn(1).setResizable(false);
		jtListaExemplares.getColumnModel().getColumn(1).setPreferredWidth(50);
		jtListaExemplares.getColumnModel().getColumn(2).setResizable(false);
		jtListaExemplares.getColumnModel().getColumn(2).setPreferredWidth(100);
		jspListaExemplares.setViewportView(jtListaExemplares);
		getContentPane().setLayout(groupLayout);

	}
}
