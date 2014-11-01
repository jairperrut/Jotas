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

public class Locacao extends JInternalFrame {
	private JTextField jtfCliente;
	private JTextField jtfFilme;

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
		setBounds(100, 100, 550, 450);
		
		JLabel jlCliente = new JLabel("Cliente");
		
		jtfCliente = new JTextField();
		jtfCliente.setColumns(10);
		
		JLabel jlFilme = new JLabel("Filme");
		
		jtfFilme = new JTextField();
		jtfFilme.setColumns(10);
		
		JButton jbBuscar = new JButton("Buscar");
		
		JButton btnInserir = new JButton("Inserir");
		
		JDesktopPane desktopPane = new JDesktopPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jlCliente)
								.addComponent(jlFilme))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jtfFilme)
								.addComponent(jtfCliente, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jbBuscar)
								.addComponent(btnInserir)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlCliente)
						.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbBuscar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlFilme)
						.addComponent(btnInserir))
					.addGap(296)
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
