package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.model.Exemplar;
import br.jotas.sc.model.StatusExemplarEnum;
import br.jotas.sc.util.ConsultaExemplarTableModel;

public class CadastroExemplarUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfTitulo;
	private JTextField jtfGenero;
	private JTextField jtfCodigo;

	public CadastroExemplarUI(final Exemplar exe) {
		setClosable(true);
		setTitle("Exemplar");
		setBounds(100, 100, 450, 215);

		JLabel jlTitulo = new JLabel("T\u00EDtulo");

		jtfTitulo = new JTextField();
		jtfTitulo.setEditable(false);
		jtfTitulo.setColumns(10);

		JLabel jlGenero = new JLabel("G\u00EAnero");

		jtfGenero = new JTextField();
		jtfGenero.setEditable(false);
		jtfGenero.setColumns(10);

		JLabel jlCodigo = new JLabel("C\u00F3digo");

		jtfCodigo = new JTextField();
		jtfCodigo.setEditable(false);
		jtfCodigo.setColumns(10);
		JLabel jlDisponibilidade = new JLabel("Disponibilidade");

		final JComboBox<StatusExemplarEnum> jcbDisponibilidade = new JComboBox<StatusExemplarEnum>();		
		jcbDisponibilidade.addItem(StatusExemplarEnum.DISPONIVEL);
		jcbDisponibilidade.addItem(StatusExemplarEnum.INDISPONIVEL);
		

		if (exe != null) {
			jtfTitulo.setText(exe.getFilme().getTitulo());
			jtfGenero.setText(exe.getFilme().getGenero());
			jtfCodigo.setText(Integer.toString(exe.getIdExemplar()));
			if (exe.getStatus().equals(StatusExemplarEnum.LOCADO)) {
				jcbDisponibilidade.addItem(StatusExemplarEnum.LOCADO);
				jcbDisponibilidade.setEnabled(false);
			}
			jcbDisponibilidade.setSelectedItem(exe.getStatus());
		}

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exe.setStatus((StatusExemplarEnum) jcbDisponibilidade.getSelectedItem());
				try {
					new ExemplarController().salvarExemplar(exe);
					ConsultaExemplarUI.obterInstancia().jtListaExemplar.setModel(new ConsultaExemplarTableModel(new ExemplarController().listarExemplares()));
					JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
					dispose();
				} catch (NullPointerException e) {
					System.out.println("[ ERRO ao tentar atualizar exemplar ] : " + e.getMessage());
				} catch (Exception e) {
					System.out.println("[ ERRO ao tentar atualizar exemplar ] : " + e.getMessage());
				}
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
										.addGroup(
												Alignment.TRAILING,
												groupLayout
														.createSequentialGroup()
														.addGroup(
																groupLayout.createParallelGroup(Alignment.LEADING).addComponent(jlTitulo)
																		.addComponent(jlGenero).addComponent(jlCodigo))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(
																groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(jtfTitulo, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
																		.addComponent(jtfGenero, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
																		.addComponent(jtfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGroup(Alignment.TRAILING,
												groupLayout.createSequentialGroup().addComponent(jbSalvar).addGap(18).addComponent(jbCancelar))
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addComponent(jlDisponibilidade)
														.addGap(18)
														.addComponent(jcbDisponibilidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlTitulo)
										.addComponent(jtfTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(8)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlGenero)
										.addComponent(jtfGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jtfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(jlCodigo))
						.addGap(18)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlDisponibilidade)
										.addComponent(jcbDisponibilidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jbCancelar).addComponent(jbSalvar)).addContainerGap()));
		getContentPane().setLayout(groupLayout);

	}
}
