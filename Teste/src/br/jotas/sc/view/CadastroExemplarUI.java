package br.jotas.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroExemplarUI extends JInternalFrame {
	private JTextField jftTitulo;
	private JTextField jtfGenero;
	private JTextField jtfAno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroExemplarUI frame = new CadastroExemplarUI();
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
	public CadastroExemplarUI() {
		setTitle("Exemplar");
		setBounds(100, 100, 450, 215);
		
		JLabel jlTitulo = new JLabel("T\u00EDtulo");
		
		jftTitulo = new JTextField();
		jftTitulo.setColumns(10);
		
		JLabel jlGenero = new JLabel("G\u00EAnero");
		
		jtfGenero = new JTextField();
		jtfGenero.setColumns(10);
		
		JLabel jlAno = new JLabel("Ano");
		
		jtfAno = new JTextField();
		jtfAno.setColumns(10);
		
		JLabel jlDisponibilidade = new JLabel("Disponibilidade");
		
		JComboBox jcbDisponibilidade = new JComboBox();
		jcbDisponibilidade.setModel(new DefaultComboBoxModel(new String[] {"Estoque", "Indispon\u00EDvel"}));
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton jbSalvar = new JButton("Salvar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jlTitulo)
								.addComponent(jlGenero)
								.addComponent(jlAno))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jtfAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jftTitulo, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
								.addComponent(jtfGenero, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(jbSalvar)
							.addGap(18)
							.addComponent(jbCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlDisponibilidade)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jcbDisponibilidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlTitulo)
						.addComponent(jftTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlGenero))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlAno))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlDisponibilidade)
						.addComponent(jcbDisponibilidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancelar)
						.addComponent(jbSalvar))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
