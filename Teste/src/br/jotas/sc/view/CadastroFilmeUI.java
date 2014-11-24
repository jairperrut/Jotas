package br.jotas.sc.view;

import java.awt.EventQueue;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;

import br.jotas.sc.controller.CategoriaController;
import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.controller.FilmeController;
import br.jotas.sc.model.Categoria;
import br.jotas.sc.model.Exemplar;
import br.jotas.sc.model.Filme;
import br.jotas.sc.model.StatusExemplarEnum;
import br.jotas.sc.util.ClienteTableModel;
import br.jotas.sc.util.ConsultaFilmeTableModel;

public class CadastroFilmeUI extends JInternalFrame {
	private JTextField jtfTitulo;
	private JTextField jtfGenero;
	private JTextField jtfAno;
	private JTextField jtfCodigoReserva;
	private int idFilmeSalvo = 0;

	
	public CadastroFilmeUI(final Filme f) {
		setClosable(true);
		setTitle("Filme");
		setBounds(100, 100, 500, 236);

		final JLabel jlTitulo = new JLabel("T\u00EDtulo");

		jtfTitulo = new JTextField();
		jtfTitulo.setColumns(10);

		final JLabel jlGenero = new JLabel("G\u00EAnero");

		jtfGenero = new JTextField();
		jtfGenero.setColumns(10);

		JLabel jlAno = new JLabel("Ano");

		jtfAno = new JTextField();
		jtfAno.setColumns(10);

		JLabel jlTipo = new JLabel("Tipo");

		final JComboBox<Categoria> jcbTipo = new JComboBox<Categoria>();
		DefaultComboBoxModel<Categoria> dtmc = new DefaultComboBoxModel<>();
		for (Categoria categ : new CategoriaController().listarCategorias()) {
			dtmc.addElement(categ);
		}

		jcbTipo.setModel(dtmc);
		jcbTipo.setSelectedIndex(0);
		jcbTipo.setToolTipText("");

		JLabel jlCodigoReserva = new JLabel("C\u00F3digo Reserva");

		jtfCodigoReserva = new JTextField();
		jtfCodigoReserva.setEditable(false);
		jtfCodigoReserva.setColumns(10);

		final JLabel jlQuantidade = new JLabel("Quantidade");

		final JSpinner spinnerQuantidade = new JSpinner();
		spinnerQuantidade.setModel(new SpinnerNumberModel(1, 1, 50, 1));

		if (f != null) {
			jtfTitulo.setText(f.getTitulo());
			jtfGenero.setText(f.getGenero());
			jtfAno.setText(Integer.toString(f.getAno()));
			jtfCodigoReserva.setText(Integer.toString(f.getId()));
			f.setCategoria((Categoria) jcbTipo.getSelectedItem());
		}

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws NullPointerException {
				Filme filme = new Filme();
				if (f != null) {
					filme = f;
				}
				// Nao esta editando o filme por causa da categoria
				filme.setAno(Integer.parseInt(jtfAno.getText()));
				filme.setCategoria((Categoria) jcbTipo.getSelectedItem());
				filme.setGenero(jtfGenero.getText());
				filme.setTitulo(jtfTitulo.getText());
				try {
					int id = new FilmeController().salvarFilme(filme);
					filme.setId(id);
					JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
					ConsultaFilmeUI.obterInstancia().jtListaFilme.setModel(new ConsultaFilmeTableModel(new FilmeController().listarFilme()));
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (filme.getId() > 0) {
					for (int i = 0; i < Integer.parseInt(spinnerQuantidade.getValue().toString()); i++) {
						Exemplar exemplar = new Exemplar();
						exemplar.setFilme(filme);
						exemplar.setStatus(StatusExemplarEnum.DISPONIVEL);
						try {
							new ExemplarController().salvarExemplar(exemplar);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
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
												groupLayout.createSequentialGroup().addComponent(jlQuantidade).addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(spinnerQuantidade, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addGroup(
																groupLayout.createParallelGroup(Alignment.LEADING).addComponent(jlGenero).addComponent(jlTipo)
																		.addComponent(jlAno).addComponent(jlTitulo))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(
																groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(jtfTitulo, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
																		.addComponent(jtfAno, 251, 251, 251)
																		.addComponent(jtfGenero, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addComponent(jcbTipo, GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
																						.addComponent(jlCodigoReserva)
																						.addPreferredGap(ComponentPlacement.UNRELATED)
																						.addComponent(jtfCodigoReserva, GroupLayout.PREFERRED_SIZE, 78,
																								GroupLayout.PREFERRED_SIZE))))
										.addGroup(groupLayout.createSequentialGroup().addComponent(jbSalvar).addGap(10).addComponent(jbCancelar)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlTitulo)
										.addComponent(jtfTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlGenero)
										.addComponent(jtfGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlAno)
										.addComponent(jtfAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jcbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(jlTipo)
										.addComponent(jtfCodigoReserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(jlCodigoReserva))
						.addGap(18)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlQuantidade)
										.addComponent(spinnerQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(jbCancelar).addComponent(jbSalvar))
						.addContainerGap()));
		getContentPane().setLayout(groupLayout);

	}
}
