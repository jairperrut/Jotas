package br.jotas.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sair extends JInternalFrame {

	
	public Sair() {
		setTitle("Sair");
		setBounds(100, 100, 200, 125);
		
		JButton jbSim = new JButton("Sim");
		
		JButton jbNao = new JButton("N\u00E3o");
		jbNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel jlSair = new JLabel("SAIR");
		
		JLabel lblDesejaSairDo = new JLabel("Deseja sair do sistema?");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(79)
					.addComponent(jlSair)
					.addContainerGap(81, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDesejaSairDo)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jbSim)
							.addGap(18)
							.addComponent(jbNao)))
					.addGap(32))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jlSair)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDesejaSairDo)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbNao)
						.addComponent(jbSim))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
