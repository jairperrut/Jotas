package br.jotas.sc.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PrincipalUI extends JFrame {

	private JPanel contentPane;
	private static PrincipalUI instancia;

	// Implementação de SINGLETON
	public static PrincipalUI obterInstancia() {
		if (instancia == null) {
			instancia = new PrincipalUI();
		}
		return instancia;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = obterInstancia();
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
	public PrincipalUI() {
		setTitle("Jota\u00B4s Locadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu jmMovimento = new JMenu("Movimento");
		menuBar.add(jmMovimento);
		
		JMenuItem jmiLocacao = new JMenuItem("Loca\u00E7\u00E3o");
		jmiLocacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locacao loc = new Locacao();
				loc.setFocusable(true);
				loc.moveToFront();
				loc.requestFocus();
				getContentPane().add(loc, 0);
				try {
					loc.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				loc.setVisible(true);
			}
		});
		jmMovimento.add(jmiLocacao);
		
		JMenuItem jmiDevolucao = new JMenuItem("Devolu\u00E7\u00E3o");
		jmiDevolucao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Devolucao dev = new Devolucao();
				dev.setFocusable(true);
				dev.moveToFront();
				dev.requestFocus();
				getContentPane().add(dev, 0);
				try {
					dev.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dev.setVisible(true);
			}
		});
		jmMovimento.add(jmiDevolucao);
		
		JMenu jmCadastro = new JMenu("Cadastro");
		menuBar.add(jmCadastro);
		
		JMenuItem jmiCliente = new JMenuItem("Cliente");
		jmiCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroClienteUI cadcliUI = new CadastroClienteUI(null);
				cadcliUI.setFocusable(true);
				cadcliUI.moveToFront();
				cadcliUI.requestFocus();
				getContentPane().add(cadcliUI, 0);
				try {
					cadcliUI.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cadcliUI.setVisible(true);
			}
		});
		jmCadastro.add(jmiCliente);
		
		JMenuItem jmiFilme = new JMenuItem("Filme");
		jmiFilme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFilmeUI cadprodUI = new CadastroFilmeUI();
				cadprodUI.setFocusable(true);
				cadprodUI.moveToFront();
				cadprodUI.requestFocus();
				getContentPane().add(cadprodUI, 0);
				try {
					cadprodUI.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cadprodUI.setVisible(true);
			}
		});
		jmCadastro.add(jmiFilme);
		
		JMenu jmConsulta = new JMenu("Consulta");
		menuBar.add(jmConsulta);
		
		JMenuItem jmiClienteConsulta = new JMenuItem("Cliente");
		jmiClienteConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaClienteUI conCli = new ConsultaClienteUI();
				conCli.setFocusable(true);
				conCli.moveToFront();
				conCli.requestFocus();
				PrincipalUI.obterInstancia().getContentPane().add(conCli, 0);
				try {
					conCli.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				conCli.setVisible(true);
			}
		});
		jmConsulta.add(jmiClienteConsulta);
		
		JMenuItem jmiExemplar = new JMenuItem("Exemplar");
		jmiExemplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaExemplarUI conExe = new ConsultaExemplarUI();
				conExe.setFocusable(true);
				conExe.moveToFront();
				conExe.requestFocus();
				PrincipalUI.obterInstancia().getContentPane().add(conExe, 0);
				try {
					conExe.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				conExe.setVisible(true);
			}
		});
		jmConsulta.add(jmiExemplar);
		
		JMenuItem jmiFilmeConsulta = new JMenuItem("Filme");
		jmiFilmeConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaFilmeUI conFil = new ConsultaFilmeUI();
				conFil.setFocusable(true);
				conFil.moveToFront();
				conFil.requestFocus();
				PrincipalUI.obterInstancia().getContentPane().add(conFil, 0);
				try {
					conFil.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				conFil.setVisible(true);
			}
		});
		jmConsulta.add(jmiFilmeConsulta);
		
		JMenu jmRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(jmRelatrio);
		
		JMenuItem jmiAtraso = new JMenuItem("Atraso");
		jmiAtraso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioAtraso telaRelAtra = new RelatorioAtraso();
				telaRelAtra.setFocusable(true);
				telaRelAtra.moveToFront();
				telaRelAtra.requestFocus();
				getContentPane().add(telaRelAtra, 0);
				telaRelAtra.setVisible(true);
			}
		});
		jmRelatrio.add(jmiAtraso);
		
		JMenuItem jmiClienteRelatorio = new JMenuItem("Cliente");
		jmiClienteRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioCliente telaRelCli = new RelatorioCliente();
				telaRelCli.setFocusable(true);
				telaRelCli.moveToFront();
				telaRelCli.requestFocus();
				getContentPane().add(telaRelCli, 0);
				telaRelCli.setVisible(true);
			}
		});
		jmRelatrio.add(jmiClienteRelatorio);
		
		JMenuItem jmiLocacoesRelatorio = new JMenuItem("Filme");
		jmiLocacoesRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioFilme telaRelFil = new RelatorioFilme();
				telaRelFil.setFocusable(true);
				telaRelFil.moveToFront();
				telaRelFil.requestFocus();
				getContentPane().add(telaRelFil, 0);
				telaRelFil.setVisible(true);
			}
		});
		jmRelatrio.add(jmiLocacoesRelatorio);
		
		JMenu jmSair = new JMenu("Sair");
		menuBar.add(jmSair);
		
		JMenuItem jmiSair = new JMenuItem("Sair");
		jmiSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Sair telaSair = new Sair();
					telaSair.setFocusable(true);
					telaSair.moveToFront();
					telaSair.requestFocus();
					getContentPane().add(telaSair, 0);
					telaSair.setVisible(true);
			}
		});
		jmSair.add(jmiSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 252, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

}
