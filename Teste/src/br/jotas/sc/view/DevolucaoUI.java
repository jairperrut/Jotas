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
	private JTextField jtfExemplar;
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

		final JLabel jlExemplar = new JLabel("Exemplar");

		jtfExemplar = new JTextField();
		jtfExemplar.setColumns(10);
		final JScrollPane jspDevolucoes = new JScrollPane();

		JButton jbInserir = new JButton("Inserir");
		jbInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (jtfExemplar.getText().equals("")) {
						throw new CampoObrigatorioException(jlExemplar.getText());
					} else {
						for (Locacao loc : locacoes) {
							if(jtfExemplar.getText().equals(Integer.toString(loc.getExemplar().getIdExemplar())))
								throw new Exception("Exemplar já está incluso na lista");
						}
						Locacao locacao = new LocacaoController().obterLocacaoPorExemplar(Integer.parseInt(jtfExemplar.getText()));
						if (locacao.getId() == 0)
							throw new NaoEncontradoException("Filme não está locado!");
						if (!locacao.isPago())
							total += locacao.getValor();
						if (DataUtil.diferencaEmdias(new Date(), locacao.getPrazo()) > 0)
							multa += DataUtil.diferencaEmdias(new Date() , locacao.getPrazo());
						locacoes.add(locacao);
						jtListaDevolucao.setModel(new DevolucaoFilmeTableModel(locacoes));
						jspDevolucoes.setViewportView(jtListaDevolucao);
						getContentPane().setLayout(getLayout());
						jtfExemplar.setText("");
						jlTotal.setText("Total R$" + (total + multa));
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Exemplar inválido");
				} catch (CampoObrigatorioException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NaoEncontradoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
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
						multa -= DataUtil.diferencaEmdias(new Date() , locacao.getPrazo());
					locacoes.remove(jtListaDevolucao.getSelectedRow());
					jtListaDevolucao.setModel(new DevolucaoFilmeTableModel(locacoes));
					jspDevolucoes.setViewportView(jtListaDevolucao);
					getContentPane().setLayout(getLayout());
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(jlExemplar)
							.addGap(10)
							.addComponent(jtfExemplar, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jbInserir))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(jlDevolucoes))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(jbExcluir))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jspDevolucoes, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(jbCancelar)
							.addGap(6)
							.addComponent(jbOk)
							.addPreferredGap(ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
							.addComponent(jlTotal, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(jlExemplar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfExemplar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jbInserir))))
					.addGap(20)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(jlDevolucoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jspDevolucoes, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbExcluir)
					.addGap(11)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jbCancelar)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jbOk)
							.addComponent(jlTotal))))
		);
		getContentPane().setLayout(groupLayout);

	}
}
