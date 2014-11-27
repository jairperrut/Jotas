package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.jotas.sc.controller.DevolucaoController;
import br.jotas.sc.controller.ExemplarController;
import br.jotas.sc.controller.LocacaoController;
import br.jotas.sc.exception.CampoObrigatorioException;
import br.jotas.sc.exception.NaoEncontradoException;
import br.jotas.sc.model.Devolucao;
import br.jotas.sc.model.Locacao;
import br.jotas.sc.model.StatusExemplarEnum;
import br.jotas.sc.util.DataUtil;
import br.jotas.sc.util.DevolucaoFilmeTableModel;

public class DevolucaoUI extends JInternalFrame {
	private JTextField jtfFilme;
	private JTable jtListaDevolucao;
	private double total;
	private double multa;

	public DevolucaoUI() {
		setClosable(true);
		setTitle("Devolu\u00E7\u00E3o");
		setBounds(100, 100, 550, 400);
		total = 0.00;
		multa = 0.00;

		final ArrayList<Locacao> locacoes = new ArrayList<Locacao>();

		final JLabel jlTotal = new JLabel("Total R$ 0,00");

		final JLabel jlFilme = new JLabel("Filme");

		jtfFilme = new JTextField();
		jtfFilme.setColumns(10);

		final GroupLayout groupLayout = new GroupLayout(getContentPane());
		final JScrollPane jspDevolucoes = new JScrollPane();

		JButton jbInserir = new JButton("Inserir");
		jbInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (jtfFilme.getText().equals("")) {
						throw new CampoObrigatorioException(jlFilme.getText());
					} else {
						Locacao locacao = new LocacaoController().obterLocacaoPorExemplar(Integer.parseInt(jtfFilme.getText()));
						if (locacao.getId() == 0)
							throw new NaoEncontradoException("Filme não está locado!");
						if (locacao.isPago() == false)
							total += locacao.getValor();
						if (DataUtil.diferencaEmdias(new Date(), locacao.getPrazo()) > 0)
							multa += DataUtil.diferencaEmdias(new Date() , locacao.getPrazo());
						locacoes.add(locacao);
						jtListaDevolucao.setModel(new DevolucaoFilmeTableModel(locacoes));
						jspDevolucoes.setViewportView(jtListaDevolucao);
						getContentPane().setLayout(groupLayout);
						jtfFilme.setText("");
						jlTotal.setText("Total R$" + (total + multa));
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Exemplar inválido");
				} catch (CampoObrigatorioException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NaoEncontradoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					// Log
				}
			}
		});

		JSeparator separator = new JSeparator();

		JLabel jlDevolucoes = new JLabel("Devolu\u00E7\u00F5es");

		JButton jbExcluir = new JButton("Excluir");
		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					Locacao locacao = locacoes.get(jtListaDevolucao.getSelectedRow());
					if (!locacao.isPago())
						total -= locacao.getValor();
					if (DataUtil.diferencaEmdias(new Date(), locacao.getPrazo()) > 0)
						multa -= locacao.getValor();
					locacoes.remove(jtListaDevolucao.getSelectedRow());
					jtListaDevolucao.setModel(new DevolucaoFilmeTableModel(locacoes));
					jspDevolucoes.setViewportView(jtListaDevolucao);
					getContentPane().setLayout(groupLayout);
					jlTotal.setText("Total R$" + (total + multa));
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Nenhum exemplar selecionado!");
				} catch (Exception e) {
					// Log
				}
			}
		});

		JSeparator separator_1 = new JSeparator();

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton jbOk = new JButton("Ok");
		jbOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (Locacao locacao : locacoes) {
						Devolucao devolucao = new Devolucao();
						devolucao.setDataRealDevolucao(new Date());
						devolucao.setLocacao(locacao);						
						if (DataUtil.diferencaEmdias(new Date(), locacao.getPrazo()) > 0){
							devolucao.setMulta((double) DataUtil.diferencaEmdias(new Date() , locacao.getPrazo()));
						}else{
							devolucao.setMulta(0.00);
						}							
						devolucao.setValorTotal(locacao.getValor()+devolucao.getMulta());
						new DevolucaoController().salvarDevolucao(devolucao);
						locacao.getExemplar().setStatus(StatusExemplarEnum.DISPONIVEL);
						new ExemplarController().salvarExemplar(locacao.getExemplar());
					}
					JOptionPane.showMessageDialog(null, "Devolução efetuada com sucesso!");
					dispose();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					// Log
				}
			}
		});

		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(
														groupLayout.createSequentialGroup().addComponent(jlFilme).addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE).addComponent(jbInserir))
												.addComponent(jlDevolucoes)
												.addComponent(jspDevolucoes, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(jbExcluir).addContainerGap(461, Short.MAX_VALUE))
				.addGroup(
						groupLayout.createSequentialGroup().addContainerGap().addComponent(jbCancelar).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jbOk).addPreferredGap(ComponentPlacement.RELATED, 323, Short.MAX_VALUE).addComponent(jlTotal).addContainerGap())
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jlFilme)
										.addComponent(jtfFilme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(jbInserir)).addGap(18).addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(jlDevolucoes).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jspDevolucoes, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jbExcluir).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(jbCancelar).addComponent(jbOk).addComponent(jlTotal))
						.addContainerGap(26, Short.MAX_VALUE)));

		if (jtListaDevolucao == null) {
			jtListaDevolucao = new JTable();
			jtListaDevolucao.setModel(new DevolucaoFilmeTableModel(locacoes));
			jtListaDevolucao.getColumnModel().getColumn(0).setPreferredWidth(50);
			jtListaDevolucao.getColumnModel().getColumn(1).setPreferredWidth(200);
			jtListaDevolucao.getColumnModel().getColumn(2).setPreferredWidth(50);
			jtListaDevolucao.getColumnModel().getColumn(3).setPreferredWidth(50);
			jtListaDevolucao.getColumnModel().getColumn(4).setPreferredWidth(50);
		}
		jspDevolucoes.setViewportView(jtListaDevolucao);
		getContentPane().setLayout(groupLayout);

	}
}
