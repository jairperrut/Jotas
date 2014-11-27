package br.jotas.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class RelatorioAtrasoUI extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioAtrasoUI frame = new RelatorioAtrasoUI();
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
	public RelatorioAtrasoUI() {
		setClosable(true);
		setTitle("Relat\u00F3rio de Atraso");
		setBounds(100, 100, 450, 300);
		
		JLabel jlRelatorioAtrasos = new JLabel("Relat\u00F3rio de exemplares em atraso no per\u00EDodo de:");
		
		JLabel jlMesAno = new JLabel("M\u00EAs / Ano");
		
		JScrollPane jspRelatorioAtrasos = new JScrollPane();
		
		JButton jbSair = new JButton("Sair");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jspRelatorioAtrasos, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlRelatorioAtrasos)
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
						.addComponent(jlRelatorioAtrasos)
						.addComponent(jlMesAno))
					.addGap(18)
					.addComponent(jspRelatorioAtrasos, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(jbSair)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}

}
