package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.exception.CampoObrigatorioException;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.util.ClienteTableModel;

public class CadastroClienteUI extends JInternalFrame {
	private JTextField jtfTelefone;
	private JTextField jtfCpf;
	private JTextField jtfNome;
	private JTextField jtfEndereco;
	private JTextField jtfDataNasc;
	private JButton jbCancelar;
	private JButton jbSalvar;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public CadastroClienteUI(final Cliente cli) {
		setClosable(true);
		setTitle("Cliente");
		setBounds(100, 100, 400, 225);

		JLabel jlNome = new JLabel("Nome");

		JLabel jlCpf = new JLabel("CPF");

		JLabel jlTelefone = new JLabel("Telefone");

		final JLabel jlDataNasc = new JLabel("Data Nasc");

		JLabel jlEndereo = new JLabel("Endere\u00E7o");

		jtfTelefone = new JTextField();
		jtfTelefone.setColumns(10);

		jtfCpf = new JTextField();
		jtfCpf.setColumns(10);

		jtfNome = new JTextField();
		jtfNome.setColumns(10);

		jtfEndereco = new JTextField();
		jtfEndereco.setColumns(10);

		jtfDataNasc = new JTextField();
		jtfDataNasc.setColumns(10);

		if (cli != null) {
			jtfNome.setText(cli.getNome());
			jtfTelefone.setText(cli.getTelefone());
			jtfCpf.setText(cli.getCpf());
			jtfEndereco.setText(cli.getEndereco());
			jtfDataNasc.setText(sdf.format(cli.getDataNascimento()));
		}
		jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente cliente = new Cliente();
				try {
					if (cli != null)
						cliente = cli;
					if (jtfDataNasc.getText().equals(""))
						throw new CampoObrigatorioException(jlDataNasc.getText());
					cliente.setNome(jtfNome.getText());
					cliente.setCpf(jtfCpf.getText());
					cliente.setTelefone(jtfTelefone.getText());
					cliente.setEndereco(jtfEndereco.getText());
					cliente.setDataNascimento(sdf.parse(jtfDataNasc.getText()));
					new ClienteController().salvarCliente(cliente);
					JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
					ConsultaClienteUI.obterInstancia().jtListaCliente.setModel(new ClienteTableModel(new ClienteController().listarClientes()));
					dispose();
				} catch (CampoObrigatorioException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Formato de Dt. Nasc. inv�lido: "+jtfDataNasc.getText());
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
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
										.addGroup(Alignment.TRAILING,
												groupLayout.createSequentialGroup().addComponent(jbSalvar).addGap(18).addComponent(jbCancelar))
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addGroup(
																groupLayout.createParallelGroup(Alignment.LEADING).addComponent(jlTelefone)
																		.addComponent(jlDataNasc).addComponent(jlNome).addComponent(jlEndereo)
																		.addComponent(jlCpf))
														.addGap(18)
														.addGroup(
																groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(jtfDataNasc, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(Alignment.TRAILING, false)
																						.addComponent(jtfCpf, Alignment.LEADING)
																						.addComponent(jtfTelefone, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
																								161, Short.MAX_VALUE))
																		.addComponent(jtfEndereco, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
																		.addComponent(jtfNome, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlNome)
										.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jtfEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(jlEndereo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlCpf)
										.addComponent(jtfCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jtfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(jlTelefone))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlDataNasc)
										.addComponent(jtfDataNasc, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jbCancelar).addComponent(jbSalvar))
						.addContainerGap(25, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}
}
