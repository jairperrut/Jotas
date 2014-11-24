package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Exemplar;
import br.jotas.sc.model.Locacao;
import br.jotas.sc.model.StatusExemplarEnum;
import br.jotas.sc.util.LocacaoFilmeTableModel;

public class LocacaoUI extends JInternalFrame {
	private JTextField jtfFilme;
	private ArrayList<Cliente> listaClientes = new ClienteController().listarClientes();
	private JTable jtListaLocacao;

	public LocacaoUI() {
		setClosable(true);
		setTitle("Loca\u00E7\u00E3o");
		setBounds(100, 100, 550, 450);

		final GroupLayout groupLayout = new GroupLayout(getContentPane());

		JLabel jlCliente = new JLabel("Cliente");

		JLabel jlFilme = new JLabel("Cod Exemplar");

		double total = 0.00;
		final ArrayList<Locacao> locacoes = new ArrayList<Locacao>();

		jtfFilme = new JTextField();
		jtfFilme.setColumns(10);

		final JComboBox<Cliente> jcbClientes = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}
		jcbClientes.setModel(modelCliente);

		JDesktopPane desktopPane = new JDesktopPane();

		final JScrollPane jspLocacao = new JScrollPane();

		JButton jbInserir = new JButton("Inserir");
		jbInserir.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
				Locacao locacao = new Locacao();
				if (jtfFilme.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo C�d. Exemplar obrigat�rio!");
				} else {
					Exemplar exemplar = new ExemplarController().obterExemplar(Integer.parseInt(jtfFilme.getText()));
					if (exemplar.getStatus().equals(StatusExemplarEnum.DISPONIVEL)) {
						locacao.setExemplar(exemplar);
						locacao.setDataLocacao(new Date());
						locacao.setPrazo(new Date());
						locacao.getPrazo().setDate(locacao.getDataLocacao().getDate() + exemplar.getFilme().getCategoria().getDiasLocacao());
						locacao.setValor(exemplar.getFilme().getCategoria().getValor());
						locacao.setCliente((Cliente) jcbClientes.getSelectedItem());
						locacoes.add(locacao);
						jtListaLocacao.setModel(new LocacaoFilmeTableModel(locacoes));
						jspLocacao.setViewportView(jtListaLocacao);
						getContentPane().setLayout(groupLayout);
					} else {
						JOptionPane.showMessageDialog(null, "Exemplar " + exemplar.getStatus().descricao());
					}
				}				
				}catch(NullPointerException e){
					//criar log
				}
			}
		});

		JButton jbExcluir = new JButton("Excluir");
		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				locacoes.remove(jtListaLocacao.getSelectedRow());
				jtListaLocacao.setModel(new LocacaoFilmeTableModel(locacoes));
				jspLocacao.setViewportView(jtListaLocacao);
				getContentPane().setLayout(groupLayout);
			}
		});

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton jbOk = new JButton("Ok");
		jbOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Locacao locacao : locacoes) {
					try {
						new LocacaoController().salvarLocacao(locacao);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro ao tentar efetuar loca��o!" + e.getMessage());
					}
				}
			}
		});

		JCheckBox jcbPago = new JCheckBox("Pago");

		JLabel jlTotal = new JLabel("Total R$ 0,00");

		JLabel jlLocacoes = new JLabel("Loca\u00E7\u00F5es");

		JSeparator separator = new JSeparator();

		JSeparator separator_1 = new JSeparator();

		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGroup(
										groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(jbExcluir))
												.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(jlLocacoes))
												.addGroup(
														groupLayout
																.createSequentialGroup()
																.addGap(10)
																.addGroup(
																		groupLayout.createParallelGroup(Alignment.LEADING).addComponent(jlFilme)
																				.addComponent(jlCliente))
																.addGap(21)
																.addGroup(
																		groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(
																						groupLayout
																								.createSequentialGroup()
																								.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, 175,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addComponent(jbInserir))
																				.addComponent(jcbClientes, GroupLayout.PREFERRED_SIZE, 427,
																						GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														groupLayout
																.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		groupLayout
																				.createParallelGroup(Alignment.LEADING, false)
																				.addGroup(
																						groupLayout
																								.createSequentialGroup()
																								.addComponent(jbCancelar)
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addComponent(jbOk)
																								.addGap(18)
																								.addComponent(jcbPago)
																								.addPreferredGap(ComponentPlacement.RELATED,
																										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(jlTotal))
																				.addComponent(jspLocacao, GroupLayout.PREFERRED_SIZE, 514,
																						GroupLayout.PREFERRED_SIZE)))).addContainerGap(20, Short.MAX_VALUE))
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
				.addGroup(
						groupLayout.createSequentialGroup().addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(15)
								.addGroup(
										groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlCliente)
												.addComponent(jcbClientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(8)
								.addGroup(
										groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(jlFilme))
												.addGroup(
														groupLayout
																.createSequentialGroup()
																.addGap(1)
																.addGroup(
																		groupLayout
																				.createParallelGroup(Alignment.BASELINE)
																				.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE).addComponent(jbInserir))))
								.addGap(23)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(jlLocacoes)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jspLocacao, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jbExcluir)
								.addGap(9)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jcbPago).addComponent(jlTotal))
												.addGroup(
														groupLayout
																.createParallelGroup(Alignment.TRAILING)
																.addGroup(
																		groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jbOk)
																				.addComponent(jbCancelar))
																.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))));

		if (jtListaLocacao == null) {
			jtListaLocacao = new JTable();
			jtListaLocacao.setModel(new LocacaoFilmeTableModel(locacoes));
			jtListaLocacao.getColumnModel().getColumn(0).setPreferredWidth(200);
			jtListaLocacao.getColumnModel().getColumn(1).setPreferredWidth(100);
		}
		jspLocacao.setViewportView(jtListaLocacao);
		getContentPane().setLayout(groupLayout);

	}
}
