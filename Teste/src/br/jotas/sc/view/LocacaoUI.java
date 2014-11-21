package br.jotas.sc.view;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComboBox;

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.controller.FilmeController;
import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Exemplar;
import br.jotas.sc.model.Locacao;
import br.jotas.sc.util.ConsultaFilmeTableModel;
import br.jotas.sc.util.ExemplarTableModel;
import br.jotas.sc.util.LocacaoFilmeTableModel;

public class LocacaoUI extends JInternalFrame {
	private JTextField jtfFilme;
	private ArrayList<Cliente> listaClientes = new ClienteController().listarClientes();
	private JTable jtListaLocacao;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocacaoUI frame = new LocacaoUI();
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
	public LocacaoUI() {
		setClosable(true);
		setTitle("Loca\u00E7\u00E3o");
		setBounds(100, 100, 550, 450);
		
		JLabel jlCliente = new JLabel("Cliente");
		
		JLabel jlFilme = new JLabel("Cod Exemplar");
		
		jtfFilme = new JTextField();
		jtfFilme.setColumns(10);
		
		JButton jbInserir = new JButton("Inserir");
		jbInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Locacao> locacoes = new ArrayList<Locacao>();
				Locacao locacao = new Locacao();
				Exemplar exemplar = new ExemplarController().obterExemplar(Integer.parseInt(jtfFilme.getText()));
				locacao.setExemplar(exemplar);
				locacoes.add(locacao);
				LocacaoFilmeTableModel lftm = new LocacaoFilmeTableModel(locacoes);
				
			}
		});
		
		JDesktopPane desktopPane = new JDesktopPane();
		
		JScrollPane jspLocacao = new JScrollPane();
		
		JButton jbExcluir = new JButton("Excluir");
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton jbOk = new JButton("Ok");
		
		JCheckBox jcbPago = new JCheckBox("Pago");
		
		JLabel jlTotal = new JLabel("Total R$ 0,00");
		
		JLabel jlLocacoes = new JLabel("Loca\u00E7\u00F5es");
		
		JComboBox jcbClientes = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}
		jcbClientes.setModel(modelCliente);
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jbExcluir))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jlLocacoes))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jlFilme)
								.addComponent(jlCliente))
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jbInserir))
								.addComponent(jcbClientes, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jbCancelar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jbOk)
									.addGap(18)
									.addComponent(jcbPago)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(jlTotal))
								.addComponent(jspLocacao, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(20, Short.MAX_VALUE))
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlCliente)
						.addComponent(jcbClientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(jlFilme))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jbInserir))))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jcbPago)
							.addComponent(jlTotal))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jbOk)
								.addComponent(jbCancelar))
							.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))))
		);
		
		if (jtListaLocacao == null) {
			jtListaLocacao = new JTable();
			jtListaLocacao.setModel(new LocacaoFilmeTableModel(new LocacaoController().listarLocacoes()));
			jtListaLocacao.getColumnModel().getColumn(0).setPreferredWidth(200);
			jtListaLocacao.getColumnModel().getColumn(1).setPreferredWidth(100);
		}
		jspLocacao.setViewportView(jtListaLocacao);
		getContentPane().setLayout(groupLayout);

	}
}
